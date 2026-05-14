package com.hemant.banking.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenantId; // Bank identifier

    private String accountHolderName;

    @Column(unique = true)
    private String accountNumber;

    private Double balance;
}

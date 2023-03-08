package com.nbr.trp.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "bankname")
public class BankName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bankname_id;

    @Column(name = "bank_name")
    private String bank_name;

    public BankName(long bankname_id, String bank_name) {
        this.bankname_id = bankname_id;
        this.bank_name = bank_name;
    }
}

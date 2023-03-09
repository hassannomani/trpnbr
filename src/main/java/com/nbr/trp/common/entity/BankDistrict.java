package com.nbr.trp.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "bankdistrict")
public class BankDistrict {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bankdist_id;

    @Column(name = "dist_name")
    private String dist_name;

    public BankDistrict(long bankdist_id, String dist_name) {
        this.bankdist_id = bankdist_id;
        this.dist_name = dist_name;
    }
}

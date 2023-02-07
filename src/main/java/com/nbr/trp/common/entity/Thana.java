package com.nbr.trp.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "thana")
public class Thana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;


    @Column(name="division_id")
    private String divisionId;

    @Column(name="district_id")
    private String districtId;

    @Column(name="thana_id",nullable = false,unique = true)
    private String thanaId;

    @Column(name = "name")
    private String name;

    public Thana( String div_id, String district_id, String thanaId, String name) {
        this.divisionId = div_id;
        this.districtId = district_id;
        this.thanaId = thanaId;
        this.name = name;
    }
}

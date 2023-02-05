package com.nbr.trp.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "district")
public class District {
    @Id
    @Column(name="id",nullable = false,unique = true)
    private String id;

    @Column(name="div_id")
    private String divId;

    @Column(name = "name")
    private String name;

    public District(String id, String div_id, String name){
        this.id = id;
        this.divId = div_id;
        this.name = name;
    }
}

package com.nbr.trp.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "division")
public class Division {
    @Id
    @Column(name="id",nullable = false,unique = true)
    private String id;

    @Column(name = "name")
    private String name;

    public Division(String id, String name){
        this.id = id;
        this.name = name;
    }

}

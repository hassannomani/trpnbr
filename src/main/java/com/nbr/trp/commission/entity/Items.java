package com.nbr.trp.commission.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "items")
@NoArgsConstructor
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;

    @Column(name = "item_code",nullable = false)
    private String itemCode;

    @Column(name = "item_head",nullable = false)
    private String itemHead;

    @Column(name = "item_nature",nullable = false)
    private String itemNature;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_grp_head")
    private String itemGrpHead;


    public Items(long id, String itemCode, String itemHead, String itemNature, String itemDescription, String itemGrpHead) {
        this.id = id;
        this.itemCode = itemCode;
        this.itemHead = itemHead;
        this.itemNature = itemNature;
        this.itemDescription = itemDescription;
        this.itemGrpHead = itemGrpHead;
    }

}

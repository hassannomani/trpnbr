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
@Table(name = "banks")
public class Bank {
    @Id
    @Column(name="id",nullable = false,unique = true)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "branch_name")
    private String branchName;

    @Column(name = "routing_no")
    private String routingNo;

    public Bank(String id, String name, String branchName, String routingNo) {
        this.id = id;
        this.name = name;
        this.branchName = branchName;
        this.routingNo = routingNo;
    }
}

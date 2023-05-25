package com.nbr.trp.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "addresses")
public class Address {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String uuid;

    @Column(name = "type", nullable = false)
    public String type;

    @Column(name = "thana")
    public String thana;

    @Column(name = "district")
    public String district;

    @Column(name = "house")
    public String house;

    @Column(name = "road")
    public String road;

    @Column(name = "block")
    public String block;

    @Column(name = "ward")
    public String ward;

    @Column(name = "added_by")
    public String addedBy;

    @Column(name = "added_date", nullable = false, updatable = false)
    @CreationTimestamp
    public LocalDateTime addedDate;

    @Column(name = "added_from_ip")
    public String addedFromIP;

    @Column(name = "updated_by")
    public String updatedBy;

    @Column(name = "Updated_date")
    @UpdateTimestamp
    public LocalDateTime  updatedDate;

    @Column(name = "Updated_from_ip")
    public String updatedFromIP;

    public Address(String uuid, String type, String thana, String district, String house, String road, String block, String ward, String addedBy, LocalDateTime addedDate, String addedFromIP, String updatedBy, LocalDateTime updatedDate, String updatedFromIP) {
        this.uuid = uuid;
        this.type = type;
        this.thana = thana;
        this.district = district;
        this.house = house;
        this.road = road;
        this.block = block;
        this.ward = ward;
        this.addedBy = addedBy;
        this.addedDate = addedDate;
        this.addedFromIP = addedFromIP;
        this.updatedBy = updatedBy;
        this.updatedDate = updatedDate;
        this.updatedFromIP = updatedFromIP;
    }
}

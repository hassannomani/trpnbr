package com.nbr.trp.trp_agent_change.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "transfer")
public class TRPAgentChange {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String transferid;

    @Column(name = "requested_by",nullable = false)
    public String requestedBy;   // the one who initiated the req

    @Column(name = "requested_by_type",nullable = false)
    public String requestedType;  // his type

    @Column(name = "request_for",nullable = false)
    public String requestFor;   // the one he chooses

    @Column(name = "request_for_type",nullable = false)
    public String requestForType; // the one he chooses type

    @Column(name = "previously_assigned",nullable = false)
    public String previouslyAssigned;  // his ex

    @Column(name = "reason",nullable = false)
    public String reason;

    @Column(name="file_path", nullable = false)
    public String filePath;

    @Column(name = "status",nullable = false)
    public String status;

    @Column(name = "created_at")
    @CreationTimestamp
    public Timestamp createdAt;

    @Column(name="decision_at")
    @Temporal(TemporalType.TIMESTAMP)
    private String decisionAt;

}

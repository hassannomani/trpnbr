package com.nbr.trp.action.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uniqueidentifier default newid()")
    public String action_id;

    @Column(name = "sender",nullable = false)
    public String sender;

    @Column(name = "receiver",nullable = false)
    public String receiver;

    @Column(name = "message",nullable = false)
    public String message;

    @Column(name = "action_type",nullable = false)
    public String messageType;

    @Column(name = "attachment")
    public String attachment;

    @Column(name = "action_from")
    @Temporal(TemporalType.TIMESTAMP)
    public Date actionFrom;

    @Column(name = "action_to")
    @Temporal(TemporalType.TIMESTAMP)
    public Date actionTo;

    @Column(name = "action_sent")
    @CreationTimestamp
    public Date actionSent;

    @Column(name = "action_read")
    @Temporal(TemporalType.TIMESTAMP)
    public Date actionRead;


    public Action(String msg_id, String sender, String receiver, String message, String messageType, String attachment, Date actionFrom, Date actionTo, Date actionSent, Date actionRead) {
        this.action_id = msg_id;
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.messageType = messageType;
        this.attachment = attachment;
        this.actionFrom = actionFrom;
        this.actionTo = actionTo;
        this.actionSent = actionSent;
        this.actionRead = actionRead;
    }

}

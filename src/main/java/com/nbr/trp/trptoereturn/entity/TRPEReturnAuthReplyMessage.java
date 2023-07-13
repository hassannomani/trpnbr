package com.nbr.trp.trptoereturn.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TRPEReturnAuthReplyMessage {

    private TRPEReturnAuthBody trpeReturnAuthBody;
    private String statusCode;
    private Integer statusCodeValue;
}

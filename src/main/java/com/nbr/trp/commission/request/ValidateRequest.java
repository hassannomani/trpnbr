package com.nbr.trp.commission.request;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateRequest {
    String id;
    String ledger;
    String role;
}

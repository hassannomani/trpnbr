package com.nbr.trp.commission.entity;

import java.util.Date;

public interface CommissionBillView {

    String getCreationNo();

    String getCreation_date();

    String getLedger_id();

    String getPayee();

    String getPayee_type();

    String getTaxpayer_id();

    String getTaxpayer_name();

    Double getAgent_commission();

    Double getRepresentative_commission();

    String getFirst_name();

    String getLast_name();

    String getStatus();


}

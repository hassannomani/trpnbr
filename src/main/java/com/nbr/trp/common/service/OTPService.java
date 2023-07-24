package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.OTPResponseModel;
import org.springframework.stereotype.Service;

@Service
public interface OTPService {
    OTPResponseModel sendOTP(String mobile);

    Boolean validateOTP(String mobile, String otp);
}

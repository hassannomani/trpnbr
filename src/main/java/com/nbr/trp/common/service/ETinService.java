package com.nbr.trp.common.service;

import com.nbr.trp.common.entity.ETinAuthModel;
import com.nbr.trp.common.entity.ETinAuthRequestModel;
import com.nbr.trp.common.entity.ETinResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public interface ETinService {

     ETinAuthModel getETinAuthModel();

     ETinResponseModel getTinResponse(String value);

     HttpEntity createHttpHeaders();
}

package com.nbr.trp.trptoereturn.service;

import com.google.gson.Gson;
import com.nbr.trp.common.entity.ETinAuthModel;
import com.nbr.trp.common.entity.ETinAuthRequestModel;
import com.nbr.trp.common.entity.ETinResponseModel;
import com.nbr.trp.trptoereturn.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class TRPEReturnServiceImpl implements TRPEReturnService{

    @Value("${trpereturn.username}")
    private String userName;

    @Value("${trpereturn.password}")
    private String password;

    @Value("${trpereturn.systoken}")
    private String authTokenURL;

    @Value("${trpereturn.base-url}")
    private String baseURL;

    @Value("${trpereturn.usertoken}")
    private String ereturnURL;

    @Autowired
    private Gson gson;

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public TRPEReturnAuthResponseModel getAuthResponse() {
        TRPEReturnAuthRequestModel request = new TRPEReturnAuthRequestModel(userName, password,"password",true,"string");
        String url = baseURL + authTokenURL;
        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_JSON);
        //System.out.println("url : {}"+ url);
        //System.out.println(request);
        HttpEntity<?> httpEntity = new HttpEntity<>(request, headers);

        ResponseEntity<TRPEReturnAuthResponseModel> model = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TRPEReturnAuthResponseModel.class);
        if (model != null) {
            TRPEReturnAuthResponseModel auth = model.getBody();
            System.out.println(auth.getReplyMessage().toString());
            return auth;

        }else{
            System.out.println("null");
            System.out.println(model);
            return null;
        }
    }

    @Override
    public HttpEntity createHttpHeaders() {
        TRPEReturnAuthResponseModel authModel = getAuthResponse();
        System.out.println("printing----");
        System.out.println(authModel);
        TRPEReturnAuthReplyMessage msg = authModel.getReplyMessage();
        System.out.println(msg);
        TRPEReturnAuthBody body = msg.getBody();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "bearer " + body.getId_token());
        return new HttpEntity<>("parameters", httpHeaders);

    }

    @Override
    public TRPEReturnOTPReponseModel getEReturnResponse(TRPEReturnOTPRequestModel request){

        HttpEntity headers = createHttpHeaders();
        String url = baseURL + ereturnURL;
        //System.out.println("url : " + url);
        HttpEntity<?> httpEntity = new HttpEntity<>(request, headers.getHeaders());
        ResponseEntity<TRPEReturnOTPReponseModel> eReturnResponse;
        TRPEReturnOTPReponseModel finalAlternateResponseToReturn = new TRPEReturnOTPReponseModel();

        try {
             eReturnResponse = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TRPEReturnOTPReponseModel.class);

            //eReturnResponse = restTemplate.exchange(url, HttpMethod.GET, httpHeadersEntity, String.class).getBody();
        } catch (HttpStatusCodeException ex) {
            System.out.println(ex.getMessage());
            finalAlternateResponseToReturn.setErrorCode(String.valueOf(ex.getRawStatusCode()));
            finalAlternateResponseToReturn.setErrorMessage(String.valueOf(ex.getResponseBodyAsString()));

            return finalAlternateResponseToReturn;
        }
        TRPEReturnOTPReponseModel finalResponseToReturn;
        finalResponseToReturn = eReturnResponse.getBody();
        //eReturnResponse = new Gson().fromJson(tinResponse, ETinResponseModel.class);
//        if (finalResponseToReturn == null) {
//            return null;
//        }
        System.out.println(eReturnResponse.toString());
        return finalResponseToReturn;
    }


    @Override
    public TRPEReturnOTPValidatedResponse validateOTP(TRPEReturnOTPValidateModel model){

        HttpEntity headers = createHttpHeaders();
        String url = baseURL + ereturnURL;
        HttpEntity<?> httpEntity = new HttpEntity<>(model, headers.getHeaders());
        ResponseEntity<TRPEReturnOTPValidatedResponse> eReturnResponse;

        try {
            eReturnResponse = restTemplate.exchange(url, HttpMethod.POST, httpEntity, TRPEReturnOTPValidatedResponse.class);

            //eReturnResponse = restTemplate.exchange(url, HttpMethod.GET, httpHeadersEntity, String.class).getBody();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
        TRPEReturnOTPValidatedResponse finalResponseToReturn;
        finalResponseToReturn = eReturnResponse.getBody();
        //eReturnResponse = new Gson().fromJson(tinResponse, ETinResponseModel.class);
        if (finalResponseToReturn == null) {
            return null;
        }
        //System.out.println(eReturnResponse.toString());
        return finalResponseToReturn;
    }
}
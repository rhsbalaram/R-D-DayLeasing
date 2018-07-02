package com.dayLeasing.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import io.swagger.annotations.Api;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dayLeasing.configuration.InValidRequestException;
import com.dayLeasing.service.DayLeasingUserService;
import com.dayLeasing.service.dto.DayLeasingUserDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Balance;
import com.stripe.model.Charge;
import com.stripe.model.Refund;
import com.stripe.net.*;
import com.stripe.net.RequestOptions;
import com.stripe.net.RequestOptions.RequestOptionsBuilder;
// TODO: Auto-generated Javadoc
@RestController
public class StripeCheck {

//	protected void configure (HttpSecurity http) throws Exception {
//	    http.csrf().disable();
//	}
	@RequestMapping(value="/check",method = RequestMethod.POST)
    public void payment(@RequestBody Map request){
//		System.out.println(request);
		String tokenId = (String)request.get("id");
        RequestOptions requestOptions = (new RequestOptionsBuilder()).setApiKey("sk_test_fY7mcp5UxG3zKh3whKTyapWA").build();
       /*create a Map object which is  used for charging the customer and to provide refund to the customer.
        * This object needs a source as a key and token from the front end as value(this field is mandatory).
        * and it is passed as a parameter to create a charge object.
        * 
        * 
        * 
        * */
        Map<String, Object> chargeMap = new HashMap<String, Object>();
        Stripe.apiKey="sk_test_fY7mcp5UxG3zKh3whKTyapWA";
//        System.out.println("hai "+request);
        chargeMap.put("amount", 5000);
        chargeMap.put("currency", "usd");
        chargeMap.put("source", tokenId);
        
//        chargeMap.put("source",reque);
//        Map<String, Object> cardMap = new HashMap<String, Object>();
//        cardMap.put("number", "4242424242424242");
//        cardMap.put("exp_month", 12);
//        cardMap.put("exp_year", 2020);
//        chargeMap.put("card", cardMap);
        
        try {
        	//charge object is created which takes a map object as a parameter
//        	charge object has a charge id which is used for both payment and refund of the transaction.
            Charge charge = Charge.create(chargeMap, requestOptions);//store charge id in database and use this as refund
//            System.out.println(charge);
            
//            System.out.println(charge);
            Map<String, Object> refundParams = new HashMap<String, Object>();
            refundParams.put("charge", charge.getId());
//            refund object which uses the charge id from the charge object created.
            Refund refund=Refund.create(refundParams);
            System.out.println(refund);
        } catch (StripeException e) {
            e.printStackTrace();
        }
	
	
}
}
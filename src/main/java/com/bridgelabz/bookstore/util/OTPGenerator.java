package com.bridgelabz.bookstore.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;

@Component
public class OTPGenerator {
	
//	Generate OTP 
    private StringBuilder generatedOTP = new StringBuilder();
    private SecureRandom secureRandom = new SecureRandom();
    private int lengthOfOTP = 6;

    public String generateOTP() {
        
        try{

            generatedOTP = new StringBuilder();
            secureRandom = SecureRandom.getInstance(secureRandom.getAlgorithm());
            for (int i = 0; i < lengthOfOTP; i++) {
                generatedOTP.append(secureRandom.nextInt(9));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return generatedOTP.toString();
    }

}
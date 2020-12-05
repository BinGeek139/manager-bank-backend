package com.ptit.managerbank.common;

import io.swagger.models.auth.In;

import java.util.Random;
import java.util.UUID;

public class StringUtils {
    public static String randomNumberString(){
        Random random=new Random();
        int result=random.nextInt(89)+10;
        return String.valueOf(result);
    }
    public static String getCodeFromId(Integer id){

        String result=String.valueOf(id);
        int length=result.length();
        for (int i = 0; i <10-length; i++) {
            result="0"+result;
        }
        return result;
    }
    public  static String generateCodeFree(){
       return  UUID.randomUUID().toString();
    }

}

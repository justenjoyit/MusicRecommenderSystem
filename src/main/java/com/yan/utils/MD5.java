package com.yan.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by YZT on 2018/4/18.
 */
public class MD5 {

    public static String md5(String input){
        byte[] code;
        try{
            code = MessageDigest.getInstance("md5").digest(input.getBytes());
        }catch (NoSuchAlgorithmException e){
            code = input.getBytes();
        }
        BigInteger bigInteger = new BigInteger(code);
        return bigInteger.abs().toString(32);
    }
}

package com.yan.persist.service;

/**
 * Created by YZT on 2018/5/23.
 */
public interface VerifyService {
    String generateCode(String target, String verify_method, int codeLength);

    void verify(String verify_method, String code);

}

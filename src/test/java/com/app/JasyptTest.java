package com.app;

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.junit.jupiter.api.Test;

public class JasyptTest {

    @Test
    public void jasyptTest(){
        String password = "aiejoaifp##@*!&@^aoweuiai372786";

        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setPoolSize(4);
        encryptor.setPassword(password);
        encryptor.setAlgorithm("PBEWithMD5AndTripleDES");

        String content = "35872468"; // 암호화할 내용 (넣어서 출력되는 결과물을 ENC() 활용하여 yml에 적용)
        String encryptedContent = encryptor.encrypt(content); // 암호화
        String decryptContent = encryptor.decrypt(encryptedContent); // 복호화

        System.out.println("Enc: " + encryptedContent + ", Dec: " + decryptContent);
    }
}

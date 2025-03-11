package com.ndmitrenko.passwordservice.util;

import com.ndmitrenko.passwordservice.exception.AppException;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@Component
@Scope(value = "prototype")
public class PasswordUtil {

    public byte[] encryptPassword(String password, String algorithm) {
        return encryptPassword(password);
    }

    private byte[] encryptPassword(String password) {

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        SecretKeyFactory factory;
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            return factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new AppException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.rest.examples.authentication;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.testng.annotations.Test;

public class Base64Encoding {

    @Test
    public void base64encoding() {
        String usernameColumnPassword = "volodymyr:password";
        String encoded = Base64.getEncoder().encodeToString(usernameColumnPassword.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        byte[] decode = Base64.getDecoder().decode(encoded.getBytes(StandardCharsets.UTF_8));
        String decoded = new String(decode);
        System.out.println(decoded);
    }

}

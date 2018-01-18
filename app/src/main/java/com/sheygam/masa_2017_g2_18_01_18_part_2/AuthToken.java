package com.sheygam.masa_2017_g2_18_01_18_part_2;

/**
 * Created by gregorysheygam on 18/01/2018.
 */

public class AuthToken {
    private String token;

    public AuthToken() {
    }

    public AuthToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "token='" + token + '\'' +
                '}';
    }
}

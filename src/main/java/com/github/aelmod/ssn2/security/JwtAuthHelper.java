package com.github.aelmod.ssn2.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;

class JwtAuthHelper {
    private static final String ISSUER = "SimpleSocialNetwork2";
    private static final String SIGNING_KEY = "^@(Jopka)LolKekCheburek@^($%*$%(((";

    String createJwt(Integer id) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SIGNING_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withClaim("id", id)
                    .sign(algorithm);
        } catch (UnsupportedEncodingException | JWTCreationException exception) {
            throw new IllegalStateException(exception);
        }
    }

    DecodedJWT verifyToken(String token) throws JWTVerificationException {
        try {
            Algorithm algorithm = Algorithm.HMAC512(SIGNING_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();
            return verifier.verify(token);
        } catch (UnsupportedEncodingException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
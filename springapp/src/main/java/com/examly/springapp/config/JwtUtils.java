package com.examly.springapp.config;

import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtUtils {


      private static final String secretKey="12924D8E77907FDE5D5FEF873E304F74B204061DB3F1C03925619EDEFAB6A888D29E7E1DC86EFD5804AB20B18D3B1240653D39E5FB4FD98A58678A8C651A0FA1";
      private static final long valitdTime=TimeUnit.MINUTES.toMillis(5);
      

        public String generateToken(UserDetails userDetails){

            Map<String,String> claims=new HashMap<>();
            claims.put("roleofuser","admin");          //these are used to send addtiton data by using jeson token

            return Jwts.builder()
             .claims(claims)
             .subject(userDetails.getUsername())
             .issuedAt(Date.from(Instant.now()))
             .expiration(Date.from(Instant.now().plusMillis(valitdTime)))
             .signWith(generateKey())
             .compact();
             
        }

        private SecretKey generateKey(){

          byte[]  decodeKey= Base64.getDecoder().decode(secretKey);
            
            return  Keys.hmacShaKeyFor(decodeKey);
        }

      public String extractUsername(String jwt){

       Claims claims=   this.getClaims(jwt);

                  return  claims.getSubject();
                  

        }

        public Claims getClaims(String jwt){

      return   Jwts.parser()
            .verifyWith(generateKey())
            .build()
            .parseSignedClaims(jwt)
            .getPayload();
     
        }

        public boolean isTokenValid(String jwt){

            Claims claims=this.getClaims(jwt);

            return claims.getExpiration().after(Date.from(Instant.now()));
              
        }
}

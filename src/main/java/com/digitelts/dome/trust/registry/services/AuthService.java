package com.digitelts.dome.trust.registry.services;

import org.springframework.stereotype.Service;

import com.digitelts.dome.trust.registry.exceptions.AuthException;

import java.util.*;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.*;

@Service
public class AuthService {
    private JwtDecoder decoder;
    @Value("${auth.issuer}")
    private String issuer;
    @Value("${auth.mandators}")
    private String validMandatorString;
    private List<String> validMandators;
    private String validPowerAction = "Execute";
    private String validPowerDomain = "DOME";
    private String validPowerFunction = "Onboarding";
    private String validType = "LEARCredentialEmployee";

    @PostConstruct
    public void init(){
        this.decoder = JwtDecoders.fromIssuerLocation(this.issuer);
        this.validMandators = Arrays.asList(validMandatorString.split(";"));
    }

    public JwtDecoder getDecoder() {
        return decoder;
    }

    public void setDecoder(JwtDecoder decoder) {
        this.decoder = decoder;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @SuppressWarnings("unchecked")
    public void validateToken(String bearerToken) throws Exception{ 
        String action, domain, function, mandator;
        List<String> types;
        try{
            String token = bearerToken.replace("Bearer", "");
            Jwt jwt = decoder.decode(token);
            Map<String, Object> claims = jwt.getClaims();
            Map<String, Object> credentialSubject = (Map<String,Object>) claims.get("credentialSubject");
            if(credentialSubject == null || credentialSubject.isEmpty()){
                throw new Exception("No credential subject found");
            }
            Map<String, Object> mandatorInfo = (Map<String,Object>) credentialSubject.get("mandator");
            if(mandatorInfo == null || mandatorInfo.isEmpty()){
                throw new Exception("No mandator found");
            }
            // Check credentialSubject.mandator.organizationIdentifier
            mandator = (String) mandatorInfo.get("organizationIdentifier");
            if(mandator == null || mandator.isBlank()){
                throw new Exception("No organization identifier found");
            }
            if(!validMandators.contains(mandator)){
                throw new Exception("Invalid organization identidier");
            }
            Map<String,Object> power = (Map<String, Object>) credentialSubject.get("power");
            if(power == null || power.isEmpty()){
                throw new Exception("No power found");
            }
            // Check credentialSubject.power.action
            action = (String) power.get("action");
            if(action == null || action.isBlank()){
                throw new Exception("No action found");
            }
            if(!action.equals(validPowerAction)){
                throw new Exception("Invalid power action");
            }
            // Check credentialSubject.power.domain
            domain = (String) power.get("domain");
            if(domain == null || domain.isBlank()){
                throw new Exception("No domain found");
            }
            if(!domain.equals(validPowerDomain)){
                throw new Exception("Invalid power domain");
            }
            // Check credentialSubject.power.function
            function = (String) power.get("function");
            if(function == null || function.isBlank()){
                throw new Exception("No function found");
            }
            if(!function.equals(validPowerFunction)){
                throw new Exception("Invalid power function");
            }
            // Check credentialSubject.type
            types = (List<String>) credentialSubject.get("type");
            if(types == null || types.isEmpty()){
                throw new Exception("No types found");
            }
            if(!types.contains(validType)){
                throw new Exception("Invalid credential type");
            }
        }catch (Exception e){
            throw new AuthException(e);
        }
    }
}

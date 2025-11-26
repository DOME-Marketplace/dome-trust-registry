package com.digitelts.dome.trust.registry.model;

import java.util.*;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;

import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Table(name = "services")
public class ServiceDetails extends TrustedRegistryDetails{
    
    @Nullable
    private String url;
    @NotNull
    @ElementCollection
    private List<String> redirectUris;
    @NotNull
    @ElementCollection
    private List<String> scopes;
    @NotNull
    @ElementCollection
    private List<String> clientAuthenticationMethods;
    @NotNull
    @ElementCollection
    private List<String> postLogoutRedirectUris;
    @NotNull
    private boolean requireAuthorizationConsent;
    @NotNull
    private boolean requireProofKey;
    @Nullable
    private String jwkSetUrl;
    @Nullable
    private String tokenEndpointAuthenticationSigningAlgorithm;
    @NotNull
    @ElementCollection
    private List<String> authorizationGrantTypes;

    public ServiceDetails(String client_id, @Nullable String url,
            @NotNull List<String> redirectUris, @NotNull List<String> scopes,
            @NotNull List<String> clientAuthenticationMethods, @NotNull List<String> postLogoutRedirectUris,
            @NotNull boolean requireAuthorizationConsent, @NotNull boolean requireProofKey, @NotNull String jwkSetUrl,
            @NotNull String tokenEndpointAuthenticationSigningAlgorithm, @NotNull List<String> authorizationGrantTypes) {
        super(client_id);
        this.url = url;
        this.redirectUris = redirectUris;
        this.scopes = scopes;
        this.clientAuthenticationMethods = clientAuthenticationMethods;
        this.postLogoutRedirectUris = postLogoutRedirectUris;
        this.requireAuthorizationConsent = requireAuthorizationConsent;
        this.requireProofKey = requireProofKey;
        this.jwkSetUrl = jwkSetUrl;
        this.tokenEndpointAuthenticationSigningAlgorithm = tokenEndpointAuthenticationSigningAlgorithm;
        this.authorizationGrantTypes = authorizationGrantTypes;
    }

    public ServiceDetails(){}

    @Override
    @Schema(name = "client_id", description = "A unique identifier of this trusted service's client.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("client_id")
    public String getId(){
        return this.id;
    }

    @Override
    public ServiceSummary getSummary(String url) {
        return new ServiceSummary(this.id,url+this.id);
    }

    @Schema(name = "url", description = "The base URL of your service or application.", requiredMode = RequiredMode.REQUIRED)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    @Schema(name = "redirectUris", description = "Must include all the URLs where you expect to receive authentication responses. These should be HTTPS URLs to ensure secure communication.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("redirectUris")
    public List<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }
    
    @Schema(name = "scopes", description = "Currently, only openid_learcredential is accepted. This scope allows your service to request the necessary credentials.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("scopes")
    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }
    
    @Schema(name = "clientAuthenticationMethods", description = "Must be set to [\"client_secret_jwt\"], as this is the only supported authentication method.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("clientAuthenticationMethods")
    public List<String> getClientAuthenticationMethods() {
        return clientAuthenticationMethods;
    }

    public void setClientAuthenticationMethods(List<String> clientAuthenticationMethods) {
        this.clientAuthenticationMethods = clientAuthenticationMethods;
    }
    
    @Schema(name = "postLogoutRedirectUris", description = "Include URLs where users should be redirected after they log out from your service.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("postLogoutRedirectUris")
    public List<String> getPostLogoutRedirectUris() {
        return postLogoutRedirectUris;
    }

    public void setPostLogoutRedirectUris(List<String> postLogoutRedirectUris) {
        this.postLogoutRedirectUris = postLogoutRedirectUris;
    }
    
    @Schema(name = "requireAuthorizationConsent", description = "Set to false because explicit user consent is not required in this flow.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("requireAuthorizationConsent")
    public boolean isRequireAuthorizationConsent() {
        return requireAuthorizationConsent;
    }

    public void setRequireAuthorizationConsent(boolean requireAuthorizationConsent) {
        this.requireAuthorizationConsent = requireAuthorizationConsent;
    }
    
    @Schema(name = "requireProofKey", description = "Set to false as PKCE is not utilized.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("requireProofKey")
    public boolean isRequireProofKey() {
        return requireProofKey;
    }

    public void setRequireProofKey(boolean requireProofKey) {
        this.requireProofKey = requireProofKey;
    }
    
    @Schema(name = "jwkSetUrl", description = "If you're using a did:key for your clientId, you do not need to provide a jwkSetUrl because the verifier can derive your JWKS directly from the did:key. However, if you're not using a unique identifier, you must provide the jwkSetUrl where your public keys can be fetched.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("jwkSetUrl")
    public String getJwkSetUrl() {
        return jwkSetUrl;
    }

    public void setJwkSetUrl(String jwkSetUrl) {
        this.jwkSetUrl = jwkSetUrl;
    }
    
    @Schema(name = "tokenEndpointAuthenticationSigningAlgorithm", description = "Must be set to ES256, as this is the only supported algorithm.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("tokenEndpointAuthenticationSigningAlgorithm")
    public String getTokenEndpointAuthenticationSigningAlgorithm() {
        return tokenEndpointAuthenticationSigningAlgorithm;
    }

    public void setTokenEndpointAuthenticationSigningAlgorithm(String tokenEndpointAuthenticationSigningAlgorithm) {
        this.tokenEndpointAuthenticationSigningAlgorithm = tokenEndpointAuthenticationSigningAlgorithm;
    }

    @Schema(name = "authorizationGrantTypes", description = "Must be set to [\"authorization_code\"], as this is the only supported grant type.", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("authorizationGrantTypes")
    public List<String> getAuthorizationGrantTypes() {
        return authorizationGrantTypes;
    }

    public void setAuthorizationGrantTypes(List<String> authorizationGrantTypes) {
        this.authorizationGrantTypes = authorizationGrantTypes;
    }    
}

package com.digitelts.dome.trust.registry.model;

import org.springframework.http.HttpStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Clase para manejar las respuestas del servidor con
 * código 4xx
 */
public class WrongRequest {

    /**
     * Código de error (400, 404, etc.)
     */
    private int errCode;
    /**
     * Mensaje que describe el error
     */
    private String errMessage;

    public WrongRequest(int code, String message){
        this.errCode = code;
        this.errMessage = message;
    }

    public WrongRequest(HttpStatus status, String message){
        this.errCode = status.value();
        this.errMessage = message;
    }

    public void setErrCode(int code){
        this.errCode = code;
    }

    public void setErrMessage(String message){
        this.errMessage = message;
    }

    @Schema(name = "errCode", description = "The HTTP status code",requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("errCode")
    public int getErrCode(){
        return errCode;
    }

    @Schema(name = "errMessage", description = "A message about the error", requiredMode = Schema.RequiredMode.REQUIRED)
    @JsonProperty("errMessage")
    public String getErrMessage(){
        return errMessage;
    }
}

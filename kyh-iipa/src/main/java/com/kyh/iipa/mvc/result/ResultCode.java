package com.kyh.iipa.mvc.result;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResultCode {

    @JsonProperty("Result")
    private int result;

    @JsonProperty("Message")
    private String message;
    
  
    public ResultCode() {
        this.result = 0;
        this.message = "";
    }
    
    /**
     * 생성자
     * @param result
     * @param message
     */
    public ResultCode(final Integer result, final String message) {
        
        this.result = result;
        this.message = message;
    }

}

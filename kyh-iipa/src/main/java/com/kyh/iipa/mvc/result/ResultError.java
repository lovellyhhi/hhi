package com.kyh.iipa.mvc.result;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResultError {

    @JsonProperty("errCd")
    private int errCd;

    @JsonProperty("errMsg")
    private String[] errMsg;
    
  
    public ResultError() {
        this.errCd = 0;
        this.errMsg = new String[0];
    }
    
    /**
     * 생성자
     * @param result
     * @param message
     */
    public ResultError(final int errCd, final String[] errMsg) {
        
        this.errCd = errCd;
        this.errMsg = errMsg;
    }
    

}

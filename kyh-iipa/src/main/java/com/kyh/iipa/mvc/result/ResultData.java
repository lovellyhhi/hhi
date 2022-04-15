package com.kyh.iipa.mvc.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ResultData<D, R, E> extends Result {

    private D data;
    
    @JsonProperty("Total")
    private long total = 0;
    
    
    
    /**
     * 생성자 
     * @param data
     */
    public ResultData(final D data) {
        super();
        this.data = data;
    }
  
    public ResultData(final D data, long total) {
      super();
      this.data = data;
      this.total = total;
    }
    
    /**
     * 생성자
     * @param IO
     * @param result
     */
    public ResultData(final D data, final ResultCode IO) {
        
        super(IO);
        this.data = data;
    }
    
    /**
     * 생성자
     * @param data
     * @param etc
     */
    public ResultData(final D data, final E etc) {
        
        super(new ResultCode(), etc);
        this.data = data;
    }

      
    public ResultData(final D data, final E etc, long Total) {
        super(new ResultCode(), etc);
        this.data = data;
        this.total = total;
    }

}

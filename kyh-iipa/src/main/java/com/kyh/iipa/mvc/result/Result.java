package com.kyh.iipa.mvc.result;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * 
 * <pre tip="Result 상세 설명">
 *  
 * </pre>
 * @history
 * <pre tip="변경사항기재"> 
 *      1) 2019.12.19 이길환 : 신규생성
 *      2) 2020.01.20 한혜인 : IBSheet8 저장 규격에 맞춰 변경
 * </pre>
 *
 */
@Data
public class Result<R, E> {

    @JsonProperty("IO")
    private ResultCode IO;
    
//    private Pageable pageable;

    private E etc;
    
    @JsonProperty("ERR")
    private ResultError err;
    
//    private R cmmn;
    
    public Result() {
        this.IO = new ResultCode();
    }
    
    
    /**
     * 생성자 
     * @param IO
     */
    public Result(final ResultCode IO) {
        this.IO = IO;
    }
    
    /**
     * 생성자  
     * @param IO
     * @param etc
     */
    public Result(final ResultCode IO, final E etc) {
        
        this.IO = IO;
        this.etc = etc;
    }
    
    
    
    /**
     * 생성자  
     * @param err
     */
    public Result(final ResultError err) {
        this.err = err;
    }
    
    /**
     * 생성자  
     * @param err
     * @param etc
     */
    public Result(final ResultError err,  final E etc) {
        this.err = err;
        this.etc = etc;
    }
    
    
//    public Result( Pageable pageable) {
//        this.IO = new ResultCode();
//        this.pageable = pageable;
//    }
//
//
//    /**
//     * 생성자 
//     * @param IO
//     */
//    public Result(final ResultCode IO,  Pageable pageable) {
//        this.IO = IO;
//        this.pageable = pageable;
//    }
//
//    /**
//     * 생성자  
//     * @param IO
//     * @param etc
//     */
//    public Result(final ResultCode IO, final E etc,  Pageable pageable) {
//
//        this.IO = IO;
//        this.etc = etc;
//        this.pageable = pageable;
//    }
}

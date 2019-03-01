package me.robbie.spring.demo.response;


import me.robbie.spring.demo.exception.AbstractException;
import me.robbie.spring.demo.exception.ErrorCode;

import java.io.Serializable;
import java.util.HashMap;


public class RobbieResponse<T> implements Serializable {

    /** 成功代码 **/
    private static final String SUCCESS_CODE = "10000";
    /** 成功的消息  **/
    private static final String SUCCESS_MESSAGE = "success";
    /** 返回代码  **/
    private String code;
    /** 返回代码描述，给用户看  **/
    private String message;
    /** 给开发者用： 有利于调试用的信息：出错时返回的其它信息，在什么条件下打开 **/
    private String detail;
    /** 成功时返回的数据  **/
    private T content;

    /**
     * 私有构造函数，防止用户自己定义错误接口
     *
     * @param code errorCode
     * @param message 错误信息
     * @param detail 原因
     * @param content 内容
     */
    public RobbieResponse(String code, String message, String detail, T content) {
        this.code = code;
        this.message = message;
        this.detail = detail;
        this.content = content;
    }

    /**
     * ErrorCode初始化
     *
     * @param errorCode
     * @param detail
     * @return
     */
    private RobbieResponse(ErrorCode errorCode, String detail) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMsg();
        this.detail = detail;
        this.content = (T)new HashMap();
    }

    /**
     * 出错时返回的其它信息
     *
     * @param mogoException
     *            截获的异常信息 必须
     * @param detail
     *            出错时返回的其它信息,非必须
     * @return  RobbieResponse
     */
    private RobbieResponse(AbstractException mogoException, String detail) {
        this.code = mogoException.getErrorCode().getCode();
        this.message = mogoException.getMessage();
        this.detail = detail == null ? mogoException.getErrorCode().getDesc() : detail;
        this.content = (T)new HashMap();
    }

    /**
     * 成功时返回的结果
     *
     * @param content
     * @return RobbieResponse
     */
    private RobbieResponse(T content) {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_MESSAGE;
        this.detail = null;
        this.content = content;
    }
    
    /**
     * bulidSuccessResponseBody 静态工厂方式
     *  成功返回的Body
     * @param content 非必须
     * @return RobbieResponse
     */
    public static <T> RobbieResponse<T> bulidSuccessResponseBody(T content) {
        return new RobbieResponse(content);
    }

    /**
     * bulidErrorResponseBody  静态工厂方式
     * 组装有ErrorCode的ResponseBody
     * @param errorCode  ErrorCode 必须
     * @param detail 非必须
     * @return
     */
    public static <T> RobbieResponse<T> bulidErrorResponseBody(ErrorCode errorCode, String detail) {
        return new RobbieResponse<>(errorCode, detail);
    }

    /**
     * bulidExceptionResponseBody 静态工厂方式
     * 组装有异常的ResponseBody
     * @param mogoException mogo异常类
     * @param detail 开发者定位错误信息 非必须
     * @return
     */
    public static <T> RobbieResponse<T> bulidExceptionResponseBody(AbstractException mogoException, String detail) {
        return new RobbieResponse<>(mogoException, detail);
    }

    /**
     * bulidDefaultResponseBody 静态工厂方式，如果传null，都会返回默认的空String或对象
     * eg:
     * bulidDefaultResponseBody(null,null,null,null);
     * 得到的json将会是
     * {
     *     code:"",
     *     message:"",
     *     detail:"",
     *     content:{},
     * }
     * @param code
     * @param message
     * @param detail
     * @param content
     * @param <T>
     * @return RobbieResponse
     * @author 戴德荣
     * @since 1.2.0 @ 2018-03-30
     */
    public static <T> RobbieResponse<T> bulidDefaultResponseBody(String code, String message, String detail, T content) {
        if(code == null){
            code = "";
        }
        if(message == null){
            message = "";
        }
        if(detail == null){
            detail = "";
        }
        if(content == null){
            content = (T) new HashMap<>(1);
        }
        return new RobbieResponse(code, message, detail, content);
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}


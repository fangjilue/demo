package me.robbie.spring.demo.exception;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-03-01 10:47
 * @since [产品/模块版本]
 */
public class ErrorCode implements Serializable {
    private static final long serialVersionUID = -8591118040630654936L;

    private String code;

    private String msg;

    private String desc;

    public ErrorCode() {

    }

    public ErrorCode(String code, String msg) {
        this(code,msg,msg);
    }

    public ErrorCode(String code, String msg, String desc) {
        this.code = code;
        this.msg = msg;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public static ErrorCode newParamError(String desc){
        return new ErrorCode("100","参数非法",desc);
    }

    public static ErrorCode newValidateError(String desc){
        return new ErrorCode("110","参数验证异常",desc);
    }

    public static ErrorCode newBizError(String desc){
        return new ErrorCode("200","业务异常",desc);
    }

    public static ErrorCode newDBError(String desc){
        return new ErrorCode("300","数据库异常",desc);
    }

    public static ErrorCode newNetError(String desc){
        return new ErrorCode("400","网络异常",desc);
    }

    public static ErrorCode newUnknownError(String desc){
        return new ErrorCode("500","未知异常",desc);
    }
}

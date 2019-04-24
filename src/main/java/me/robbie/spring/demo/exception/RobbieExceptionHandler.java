package me.robbie.spring.demo.exception;

import me.robbie.spring.demo.response.RobbieResponse;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ValidationException;
import java.util.List;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-03-01 11:15
 * @since [产品/模块版本]
 */
@ControllerAdvice
public class RobbieExceptionHandler {

    @ResponseBody
    @ExceptionHandler({BindException.class})
    public RobbieResponse handleBindException(BindException e) {
        return bindingResultToResponseBody(e.getBindingResult());
    }

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RobbieResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return bindingResultToResponseBody(e.getBindingResult());
    }

    @ResponseBody
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public RobbieResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return bulidExceptionResponseBody(ErrorCode.newParamError(e.getMessage()), e);
    }

    @ResponseBody
    @ExceptionHandler({ServletRequestBindingException.class})
    public RobbieResponse handleMissingServletRequestParameterException(ServletRequestBindingException e) {
        return bulidExceptionResponseBody(ErrorCode.newParamError(e.getMessage()), e);
    }

    
    @ResponseBody
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public RobbieResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        RobbieResponse responseBody = RobbieResponse.bulidErrorResponseBody(ErrorCode.newUnknownError(e.getMessage()), e.getMessage());
        responseBody.setMessage("访问方式非法");
        return responseBody;
    }

    //@ResponseBody
    //@ExceptionHandler({NoHandlerFoundException.class})
    public RobbieResponse handleHttpRequestMethodNotSupportedException(NoHandlerFoundException e) {
        RobbieResponse responseBody = RobbieResponse.bulidErrorResponseBody(ErrorCode.newUnknownError(e.getMessage()), e.getMessage());
        responseBody.setMessage("访问路径非法");
        return responseBody;
    }


    @ResponseBody
    @ExceptionHandler({IllegalArgumentException.class,ValidateException.class, ValidationException.class})
    public RobbieResponse handleIllegalArgumentException(IllegalArgumentException e) {
        RobbieResponse responseBody = RobbieResponse.bulidErrorResponseBody(ErrorCode.newValidateError(e.getMessage()), e.getMessage());
        responseBody.setMessage("参数不正确");
        return responseBody;
    }

    @ResponseBody
    @ExceptionHandler({AbstractException.class})
    public RobbieResponse handleMogoException(AbstractException e) {
        // 业务异常显示3行异常栈
        return RobbieResponse.bulidExceptionResponseBody(e, e.getMessage());
    }

    //交给spring 默认处理：可以内容协商进行 html, json
   // @ResponseBody
   // @ExceptionHandler({Exception.class})
    public RobbieResponse handleException(Exception e) {
        return bulidExceptionResponseBody(ErrorCode.newUnknownError(e.getMessage()), e);
    }


    private RobbieResponse bindingResultToResponseBody(BindingResult bindingResult) {
        FieldError fieldError = bindingResult.getFieldError();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        StringBuilder errorMsg = new StringBuilder();
        for (FieldError error : fieldErrors) {
            errorMsg.append(error.getField()).append(":").append(error.getDefaultMessage()).append(";");
        }
        return RobbieResponse.bulidErrorResponseBody(ErrorCode.newParamError(fieldError.getDefaultMessage()) , errorMsg.toString());
    }

    private RobbieResponse bulidExceptionResponseBody(ErrorCode errorCode, Exception e) {

        return RobbieResponse.bulidErrorResponseBody(errorCode, e.getMessage());
    }

}

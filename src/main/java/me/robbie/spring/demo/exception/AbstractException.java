package me.robbie.spring.demo.exception;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-03-01 11:00
 * @since [产品/模块版本]
 */
public abstract class AbstractException extends Exception {

    public AbstractException() {

    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract ErrorCode getErrorCode();
}

package me.robbie.spring.demo.exception;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author:闻西
 * @see: [相关类/方法]
 * @date 2019-03-01 10:47
 * @since [产品/模块版本]
 */
public class BizException extends AbstractException {

    @Override
    public ErrorCode getErrorCode() {

        return ErrorCode.newBizError(this.getMessage());
    }
}

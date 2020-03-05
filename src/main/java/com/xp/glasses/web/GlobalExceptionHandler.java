package com.xp.glasses.web;

import com.xp.glasses.constant.ResponseCode;
import com.xp.glasses.utils.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * 全局异常配置类
 *
 * @author Mrxiong
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 参数校验错误 异常处理
     *
     * @param ce
     * @return
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public BaseResponse paramErrorHandler(ConstraintViolationException ce) {
        ce.printStackTrace();
        Set<ConstraintViolation<?>> constraintViolations = ce.getConstraintViolations();
        String errorMsg = "参数校验错误，未知错误.";
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            errorMsg = constraintViolations.iterator().next().getMessage();
        }
        BaseResponse response = BaseResponse.build(ResponseCode.INVALID_PARAMS, errorMsg, null);
        return response;
    }

    /**
     * 参数校验错误 异常处理
     *
     * @param ce
     * @return
     */
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public BaseResponse globalErrorHandler(Exception ce) {
        logger.error("系统错误：{}", ce.getMessage());
        BaseResponse response = BaseResponse.build(ResponseCode.FAIL, "系统未知错误", null);
        return response;
    }
}

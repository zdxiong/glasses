package com.xp.glasses.apo.validate;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 参数校验切面类
 *
 * @author Mrxiong
 * @date 2020/01/10
 */
@Aspect
@Component
public class ParamValidateAspect {

    /**
     * 被validated标记的方法需要进行校验
     */
    @Pointcut("@annotation(ParamValidated)")
    public void validateParam() {
    }


    @Before("validateParam()")
    public void validateBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object obj : args) {
            ParamValidationUtils.validate(obj);
        }
    }
}

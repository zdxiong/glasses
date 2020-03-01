package com.xp.glasses.apo.validate;

import java.lang.annotation.*;

/**
 * Methods modified by this annotation (mostly controller) will trigger parameter validation
 *
 * @author Mrxiong
 * @date 2020/01/10
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ParamValidated {
}

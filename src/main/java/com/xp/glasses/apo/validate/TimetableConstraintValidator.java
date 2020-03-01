package com.xp.glasses.apo.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Mrxiong
 */
public class TimetableConstraintValidator implements ConstraintValidator<Timetable,String> {
    /**
     * 时间
     */
    private Pattern  pattern =  Pattern.compile("^\\d{2}:\\d{2}$");

    @Override
    public void initialize(Timetable constraintAnnotation) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }
}

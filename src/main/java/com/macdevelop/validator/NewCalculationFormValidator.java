package com.macdevelop.validator;

import com.macdevelop.form.NewCalculationForm;
import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NewCalculationFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return NewCalculationForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        NewCalculationForm form = (NewCalculationForm) o;
        try {
            if (form.getInvestedMoney().intValue() <= 0) {
                errors.rejectValue("investedMoney", "newCalculationForm.validator.field.negativeValue");
            }
        } catch (NullPointerException e) {
            errors.rejectValue("investedMoney", "newCalculationForm.validator.field.empty");
        }
        try {
            if (!GenericValidator.isDate(form.getDate(), "yyyy-MM-dd", true)) {
                errors.rejectValue("date", "newCalculationForm.validator.field.wrongFormat");
            }
        } catch (NullPointerException e) {
            errors.rejectValue("date", "newCalculationForm.validator.field.empty");

        }

    }
}

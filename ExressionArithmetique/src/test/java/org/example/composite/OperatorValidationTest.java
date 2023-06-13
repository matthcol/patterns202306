package org.example.composite;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class OperatorValidationTest {

    static Validator validator;
    @BeforeAll
    static void initValidator(){
        ValidatorFactory validatorFactory = Validation
                .buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    void testOperatorHasAtLeastOneOperand_OK(){
        var operand = Variable.of("x");
        var operator = Operator.of("-");
        operator.addOperand(operand);
        var constraintsViolations = validator.validate(operator);
        assertEquals(0, constraintsViolations.size());
    }

    @Test
    void testOperatorHasAtLeastOneOperand_KO(){
        var operator = Operator.of("-");
        var constraintsViolations = validator.validate(operator);
        assertTrue(constraintsViolations.size() > 0);
    }
}
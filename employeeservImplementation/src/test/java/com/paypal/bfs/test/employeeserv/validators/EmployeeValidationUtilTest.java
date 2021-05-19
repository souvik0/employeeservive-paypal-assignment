package com.paypal.bfs.test.employeeserv.validators;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.paypal.bfs.test.employeeserv.exceptions.BadInputException;
import com.paypal.bfs.test.employeeserv.exceptions.InvalidDateFormatException;

import static com.paypal.bfs.test.employeeserv.exceptions.ValidationMessages.*;

public class EmployeeValidationUtilTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void testFirstName() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_FIRSTNAME);
        ValidationUtil.VALIDATE_FIRSTNAME.accept(null);
    }

    @Test
    public void testLastName() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_LASTNAME);
        ValidationUtil.VALIDATE_LASTNAME.accept(null);
    }

    @Test
    public void testAddressLine1() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_LINE1);
        ValidationUtil.VALIDATE_LINE1.accept(null);
    }

    @Test
    public void testCity() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_CITY);
        ValidationUtil.VALIDATE_CITY.accept(null);
    }

    @Test
    public void testState() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_STATE);
        ValidationUtil.VALIDATE_STATE.accept(null);
    }

    @Test
    public void testCountry() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_COUNTRY);
        ValidationUtil.VALIDATE_COUNTRY.accept(null);
    }

    @Test
    public void testZipcode() {
        exception.expect(BadInputException.class);
        exception.expectMessage(EMPTY_OR_NULL_ZIPCODE);
        ValidationUtil.VALIDATE_ZIPCODE.accept(null);
    }

    @Test
    public void testDateOfBirthWithNull() {
        exception.expect(InvalidDateFormatException.class);
        exception.expectMessage(EMPTY_OR_NULL_DATE);
        ValidationUtil.VALIDATE_DATE.accept(null);
    }

    @Test
    public void testDateOfBirthWithInvalidDate() {
        exception.expect(InvalidDateFormatException.class);
        exception.expectMessage(INVALID_DATE_FORMAT);
        ValidationUtil.VALIDATE_DATE_OF_BIRTH.apply("21/01/2019");
        ValidationUtil.VALIDATE_DATE_OF_BIRTH.apply("21-01-2019");
    }
}

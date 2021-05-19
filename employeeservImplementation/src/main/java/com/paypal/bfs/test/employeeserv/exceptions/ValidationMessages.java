package com.paypal.bfs.test.employeeserv.exceptions;

public final class ValidationMessages {

    private ValidationMessages() {
    }

    public static final String EMPTY_OR_NULL_EMPLOYEE = "Employee details can't be null or empty";
    public static final String EMPTY_OR_NULL_ADDRESS = "Address details can't be null or empty";
    public static final String EMPTY_OR_NULL_FIRSTNAME = "FirstName can't be null or empty";
    public static final String EMPTY_OR_NULL_LASTNAME = "LastName can't be null or empty";
    public static final String EMPTY_OR_NULL_LINE1= "Address line1 can't be null or empty";
    public static final String EMPTY_OR_NULL_CITY = "City can't be null or empty";
    public static final String EMPTY_OR_NULL_STATE = "State can't be null or empty";
    public static final String EMPTY_OR_NULL_COUNTRY = "Country can't be null or empty";
    public static final String EMPTY_OR_NULL_ZIPCODE = "Zipcode can't be null or empty";
    public static final String EMPTY_OR_NULL_DATE= "Date can't be null or empty";
    public static final String INVALID_DATE_FORMAT= "Invalid Date format for DOB. Date format should be DD-MM-YYYY";
    public static final String INVALID_EMPLOYEE_ID= "Employee id can't be String. It should be Valid number";
    public static final String NO_EMPLOYEE_PRESENT= "No Employee found with Id ";
    public static final String EMPLOYEE_ALREADY_EXIST= "Employee details alraedy present";
}

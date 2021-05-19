package com.paypal.bfs.test.employeeserv.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.dto.EntityToModelTransferObject;
import com.paypal.bfs.test.employeeserv.dto.ModeltoEntityTransferObject;
import com.paypal.bfs.test.employeeserv.exceptions.BadInputException;
import com.paypal.bfs.test.employeeserv.exceptions.ConflictException;
import com.paypal.bfs.test.employeeserv.exceptions.InvalidDateFormatException;
import com.paypal.bfs.test.employeeserv.exceptions.NotFoundException;
import com.paypal.bfs.test.employeeserv.handler.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.handler.entity.EmployeesEntity;
import com.paypal.bfs.test.employeeserv.validators.EmployeeValidationService;

import io.swagger.annotations.Api;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import static com.paypal.bfs.test.employeeserv.exceptions.ValidationMessages.INVALID_EMPLOYEE_ID;
import static com.paypal.bfs.test.employeeserv.exceptions.ValidationMessages.NO_EMPLOYEE_PRESENT;

@Api(value = "Paypal Employee Operation", tags = {"Paypal Employee Operation" })
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

    private final EmployeeValidationService employeeValidationService;
    private final EmployeeRepository employeeRepository;

    public EmployeeResourceImpl(EmployeeValidationService employeeValidationService,
                                EmployeeRepository employeeRepository) {
        this.employeeValidationService = employeeValidationService;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ResponseEntity<Employee> employeeGetById(String id) {
        Optional<EmployeesEntity> employeesEntityOptional = employeeRepository.findById(Integer.valueOf(id));

        if(employeesEntityOptional.isPresent()){
            Employee employee = EntityToModelTransferObject.
                                tranformEmployeeEntityToModel(employeesEntityOptional.get());
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }else {
            throw new NotFoundException(NO_EMPLOYEE_PRESENT + Integer.valueOf(id));
        }
    }

    @Override
    public ResponseEntity<Employee> createEmployee(Employee employee) {
        // Doing general validation of input request
        employeeValidationService.validate(employee);
        // handling idempotency
        employeeValidationService.checkIfuserAlreadyExist(employee);

        EmployeesEntity employeesEntity = ModeltoEntityTransferObject.transformEmployeeModelToEntity(employee);
        employeesEntity = employeeRepository.save(employeesEntity);
        ResponseEntity<Employee> newlyCreatedEmployee = employeeGetById(String.valueOf(employeesEntity.getId()));

        return new ResponseEntity<>(newlyCreatedEmployee.getBody(), HttpStatus.CREATED);
    }

    @ExceptionHandler({BadInputException.class})
    public ResponseEntity<Object> handleException(BadInputException exception) {
        Map<Object, Object> headers = new HashMap<>();
        headers.put("error", exception.getMessage());
        headers.put("status", BAD_REQUEST);
        return new ResponseEntity<>(headers, BAD_REQUEST);
    }

    @ExceptionHandler({NumberFormatException.class})
    public ResponseEntity<Object> handleException() {
        Map<Object, Object> responseHeader = new HashMap<>();
        responseHeader.put("error", INVALID_EMPLOYEE_ID);
        responseHeader.put("status", BAD_REQUEST);
        return new ResponseEntity<>(responseHeader, BAD_REQUEST);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleException(ConflictException exception) {
        Map<Object, Object> responseHeader = new HashMap<>();
        responseHeader.put("error", exception.getMessage());
        responseHeader.put("status", CONFLICT);
        return new ResponseEntity<>(responseHeader, CONFLICT);
    }

    @ExceptionHandler({InvalidDateFormatException.class})
    public ResponseEntity<Object> handleException(InvalidDateFormatException exception) {
        Map<Object, Object> responseHeader = new HashMap<>();
        responseHeader.put("error", exception.getMessage());
        responseHeader.put("status", NOT_ACCEPTABLE);
        return new ResponseEntity<>(responseHeader, NOT_ACCEPTABLE);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleException(NotFoundException exception) {
        Map<Object, Object> responseHeader = new HashMap<>();
        responseHeader.put("error", exception.getMessage());
        responseHeader.put("status", NOT_FOUND);
        return new ResponseEntity<>(responseHeader, NOT_FOUND);
    }
}

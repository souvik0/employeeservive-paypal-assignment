package com.paypal.bfs.test.employeeserv.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Interface for employee resource operations.
 */
@Api(value = "Paypal Employee Operation", tags = {"Paypal Employee Operation" })
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */

    @ApiOperation(value = "To get employee details.", 
            response = com.paypal.bfs.test.employeeserv.api.model.Employee.class, 
            tags = {"Paypal Employee Operation" })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Issue"),
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 400, message = "Bad Input request") })
    @RequestMapping(value = "/v1/bfs/employees/{id}", method = RequestMethod.GET)
    ResponseEntity<Employee> employeeGetById(@PathVariable("id") String id);

    @ApiOperation(value = "To create employee details in paypal.", 
            response = com.paypal.bfs.test.employeeserv.api.model.Employee.class, 
            tags = {"Paypal Employee Operation" })
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Issue"),
            @ApiResponse(code = 400, message = "Bad Input request"),
            @ApiResponse(code = 406, message = "Not acceptable date format"),
            @ApiResponse(code = 409, message = "Already employee details exist"),
            })
    @RequestMapping(value = "/v1/bfs/employees/create", method = RequestMethod.POST)
    ResponseEntity<Employee> createEmployee(@RequestBody Employee employee);
}

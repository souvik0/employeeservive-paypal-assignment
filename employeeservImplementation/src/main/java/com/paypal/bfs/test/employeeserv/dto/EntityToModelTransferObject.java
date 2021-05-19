package com.paypal.bfs.test.employeeserv.dto;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.handler.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.handler.entity.EmployeesEntity;

import static com.paypal.bfs.test.employeeserv.validators.ValidationUtil.CONVERT_DATE_STRING;

public final class EntityToModelTransferObject {

    private EntityToModelTransferObject() {
    }

    public static Employee tranformEmployeeEntityToModel(EmployeesEntity employeesEntity){
        Employee employee = new Employee();
        employee.setId(employeesEntity.getId());
        employee.setFirstName(employeesEntity.getFirstName());
        employee.setLastName(employeesEntity.getLastName());
        employee.setDateOfBirth(CONVERT_DATE_STRING.apply(employeesEntity.getDateOdBirth()));
        employee.setAddress(transformAddressEntityToModel(employeesEntity.getAddressEntity()));
        return employee;
    }

    public static Address transformAddressEntityToModel(AddressEntity addressEntity){
        Address address = new Address();
        address.setLine1(addressEntity.getLine1());
        address.setLine2(addressEntity.getLine2());
        address.setCity(addressEntity.getCity());
        address.setState(addressEntity.getState());
        address.setCountry(addressEntity.getCountry());
        address.setZipcode(addressEntity.getZipcode());
        return address;
    }
}

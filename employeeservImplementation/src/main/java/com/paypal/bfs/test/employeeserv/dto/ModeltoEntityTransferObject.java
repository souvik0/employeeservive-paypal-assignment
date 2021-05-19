package com.paypal.bfs.test.employeeserv.dto;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.handler.entity.AddressEntity;
import com.paypal.bfs.test.employeeserv.handler.entity.EmployeesEntity;

import static com.paypal.bfs.test.employeeserv.validators.ValidationUtil.VALIDATE_DATE_OF_BIRTH;

public final class ModeltoEntityTransferObject {

    private ModeltoEntityTransferObject() {
    }

    public static EmployeesEntity transformEmployeeModelToEntity(Employee employee){
        EmployeesEntity employeesEntity = new EmployeesEntity();
        employeesEntity.setFirstName(employee.getFirstName());
        employeesEntity.setLastName(employee.getLastName());
        employeesEntity.setDateOdBirth(VALIDATE_DATE_OF_BIRTH.apply(employee.getDateOfBirth()));
        employeesEntity.setAddressEntity(transformAddressModelToEntity(employee.getAddress()));
        return employeesEntity;
    }
    public static AddressEntity transformAddressModelToEntity(Address address){
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(address.getCity());
        addressEntity.setCountry(address.getCountry());
        addressEntity.setState(address.getState());
        addressEntity.setZipcode(address.getZipcode());
        addressEntity.setLine1(address.getLine1());
        addressEntity.setLine2(address.getLine2());
        return addressEntity;
    }
}

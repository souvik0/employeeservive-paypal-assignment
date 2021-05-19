package com.paypal.bfs.test.employeeserv.handler;

import com.paypal.bfs.test.employeeserv.handler.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeesEntity,Integer> {

    // This is used get employee details from DB by Id
    Optional<EmployeesEntity> findById(Integer id);

    // This method is checking existence of employee with first & last name to tackle Idempotency
    Optional<EmployeesEntity> findByFirstNameAndLastName(String firstName, String lastName);
}

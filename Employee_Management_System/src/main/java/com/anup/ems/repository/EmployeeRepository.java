package com.anup.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anup.ems.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

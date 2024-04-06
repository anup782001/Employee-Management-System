package com.anup.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anup.ems.dto.EmployeeDto;
import com.anup.ems.service.EmployeeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService employeeService ;
	
	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}


	//Build Add Employee Rest API
	@PostMapping("/add")
	public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
		EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
		return new ResponseEntity<>(savedEmployee , HttpStatus.CREATED);
	}
	
	//Build Get Employee Rest API
	@GetMapping("/get/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
		
	EmployeeDto employeeDto =	employeeService.getEmployeeById(employeeId);
	return ResponseEntity.ok(employeeDto);
	}
	
	//Build All employee Rest API
	@GetMapping("/get/emp")
	public ResponseEntity<List<EmployeeDto>> getEmployees(){
		
		List<EmployeeDto> employeesList = employeeService.getAllEmployees();
		
		return ResponseEntity.ok(employeesList);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId , @RequestBody EmployeeDto updatedEmployee){
		
	EmployeeDto employeeDto =	employeeService.updateEmployee(employeeId, updatedEmployee);
	
	return ResponseEntity.ok(employeeDto);
	}
	//Build Delete Rest API
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long employeeId){
		
		employeeService.deleteEmployee(employeeId);
		
		return ResponseEntity.ok("Employee deleted successfully !!");
	}
}

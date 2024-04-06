package com.anup.ems.service.impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anup.ems.dto.EmployeeDto;
import com.anup.ems.entity.Employee;
import com.anup.ems.exception.ResourceNotFoundException;
import com.anup.ems.mapper.EmployeeMapper;
import com.anup.ems.repository.EmployeeRepository;
import com.anup.ems.service.EmployeeService;

@Service
public class EmployeeServiceImpl  implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository ;
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}


	@Override
	public EmployeeDto createEmployee(EmployeeDto employeeDto) {
		
		Employee employee  = EmployeeMapper.mapToEmployee(employeeDto);
		
		Employee savedEmployee = employeeRepository.save(employee);
		return EmployeeMapper.mapToEmployeeDto(savedEmployee);
	}


	@Override
	public EmployeeDto getEmployeeById(Long employeeId) {
		
	Employee employee =	employeeRepository.findById(employeeId).orElseThrow(()->
			new ResourceNotFoundException ("Employee not exist : "));
		return EmployeeMapper.mapToEmployeeDto(employee); 
	}


	@Override	
	public List<EmployeeDto> getAllEmployees() {
				List<Employee> employeesList = employeeRepository.findAll();
	
	System.out.println(employeesList);
		return employeesList.stream().map((employee)-> EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());	
		}


	@Override
	public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedemployee) {
		
		Employee employee = employeeRepository.findById(employeeId).orElseThrow(()-> new ResourceNotFoundException("Employee not found with given id : " + employeeId));
		
		employee.setFirstName(updatedemployee.getFirstName());
		employee.setLastName(updatedemployee.getLastName());
		employee.setEmail(updatedemployee.getEmail());
		
		Employee updatedEmployee  = employeeRepository.save(employee);
		return  EmployeeMapper.mapToEmployeeDto(updatedEmployee);
	}


	@Override
	public void deleteEmployee(Long employeeId) {
		// TODO Auto-generated method stub
		
		employeeRepository.findById(employeeId).orElseThrow(
				()-> new ResourceNotFoundException("Employee is not exists with given id :"+employeeId)
				);
		
		employeeRepository.deleteById(employeeId);
		
	}


//	@Override
//	public void deleteEmployee (Long employeeId) {
//		
//		employeeRepository.findById(employeeId).orElseThrow(
//				()-> new ResourceNotFoundException("Employee is not exists with given id :"+employeeId)
//				);
//		
//		employeeRepository.deleteById(employeeId);
//		
//	}




}

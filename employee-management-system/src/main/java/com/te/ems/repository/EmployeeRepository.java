package com.te.ems.repository;

import java.util.Optional;

import com.te.ems.dto.ChangePasswordDTO;
import com.te.ems.dto.EmployeeRegistrationDTO;
import com.te.ems.entity.AppUser;
import com.te.ems.entity.Employee;

public interface EmployeeRepository {

	String saveEmployee(Employee employee, AppUser appUser);

	boolean changePassword(ChangePasswordDTO changePasswordDTO);

	Optional<Employee> getEmployee(String employeeID);

	String updateEmployee(Employee employee, String employeeID);

	
}

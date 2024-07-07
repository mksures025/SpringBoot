package com.te.ems.service;

import com.te.ems.dto.ChangePasswordDTO;
import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeRegistrationDTO;

public interface EmployeeService {

	String saveEmployee(EmployeeRegistrationDTO employeeRegistrationDTO);

	boolean changePassword(ChangePasswordDTO changePasswordDTO);

	EmployeeDTO getEmployee(String employeeID);

	String updateEmployee(EmployeeDTO employeeDTO, String employeeID);

}

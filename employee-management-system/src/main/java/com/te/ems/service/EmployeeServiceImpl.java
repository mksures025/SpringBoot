package com.te.ems.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.te.ems.dto.ChangePasswordDTO;
import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeRegistrationDTO;
import com.te.ems.entity.AppUser;
import com.te.ems.entity.Employee;
import com.te.ems.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
	private final EmployeeRepository employeeRepository;

	@Override
	public String saveEmployee(EmployeeRegistrationDTO employeeRegistrationDTO) {
		Random random = new Random();
		Employee employee = Employee.builder()
									.id(UUID.randomUUID().toString())
									.employeeID("TYSS" + random.nextInt(10000))
									.employeeFN(employeeRegistrationDTO.getEmployeeFN())
									.employeeLN(employeeRegistrationDTO.getEmployeeLN())
									.employeeDOJ(LocalDate.now())
									.build();

		AppUser appUser = AppUser.builder()
								 .username(employee.getEmployeeID())
								 .password(employeeRegistrationDTO.getPassword())
								 .build();

		return employeeRepository.saveEmployee(employee, appUser);
	}

	@Override
	public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
		return employeeRepository.changePassword(changePasswordDTO);
	}

	@Override
	public EmployeeDTO getEmployee(String employeeID) {
		Optional<Employee> employeeOp = employeeRepository.getEmployee(employeeID);
		if (employeeOp.isPresent()) {
			Employee employee = employeeOp.get();
			return EmployeeDTO.builder().employeeID(employeeID)
					                    .employeeFN(employee.getEmployeeFN())
					                    .employeeLN(employee.getEmployeeLN())
					                    .build();
		}
		return null;
	}

	@Override
	public String updateEmployee(EmployeeDTO employeeDTO,String employeeID) {
		
		Employee employee = Employee.builder().employeeID(employeeDTO.getEmployeeID())
				                    .employeeFN(employeeDTO.getEmployeeFN())
				                    .employeeLN(employeeDTO.getEmployeeLN())
				                    .build();
		return employeeRepository.updateEmployee(employee, employeeID);
	}

}

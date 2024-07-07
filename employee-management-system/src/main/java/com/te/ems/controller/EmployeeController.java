package com.te.ems.controller;

import java.time.LocalDateTime;
import java.util.List;

import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_PROVIDED_FOR_ID;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_PROVIDED_FOR_IDS;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_SAVED;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_CHANGEDPASSWORD;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_UPDATE;
import static com.te.ems.constant.EmployeeConstant.EMPLOYEE_DATA_DELETE;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.te.ems.constant.EmployeeConstant;
import com.te.ems.dto.ChangePasswordDTO;
import com.te.ems.dto.DeleteEmployeeDTO;
import com.te.ems.dto.EmployeeDTO;
import com.te.ems.dto.EmployeeRegistrationDTO;
import com.te.ems.entity.Employee;
import com.te.ems.repository.EmployeeRepository;
import com.te.ems.response.SuccessResponse;
import com.te.ems.service.EmployeeService;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RequestMapping(path = "/api/employees")
@RestController
public record EmployeeController(
		EmployeeService employeeservice
		) 
{
	
	@GetMapping(path = "/dummymethod")
	public String dummy1() {
		return "dummy method";
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = "/dummy")
	public ResponseEntity<SuccessResponse> dummy() {
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				             .message("Some dummy response")
				             .data(null)
				             .timestamp(LocalDateTime.now())
				             .build());
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = "/{employeeID}")

		public ResponseEntity<SuccessResponse> getEmployee(@PathVariable String employeeID){
				
		EmployeeDTO employeeDTO = employeeservice.getEmployee(employeeID);
		
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				             .message(EMPLOYEE_DATA_PROVIDED_FOR_ID + employeeID)
				             .data(employeeDTO)
				             .timestamp(LocalDateTime.now())
				             .build());
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@GetMapping(path = "ids")
	public ResponseEntity<SuccessResponse> getAllEmployess(@RequestBody List<String> ids){
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
							 .message(EMPLOYEE_DATA_PROVIDED_FOR_IDS + ids)
							 .data(List.of())
							 .timestamp(LocalDateTime.now())
							 .build());
	}
	
	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(path = "")
	public ResponseEntity<SuccessResponse> saveEmployee(@RequestBody EmployeeRegistrationDTO employeeRegistrationDTO){
		
		String employeeId = employeeservice.saveEmployee(employeeRegistrationDTO);
		
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				             .message(EMPLOYEE_DATA_SAVED)
				             .data(employeeId)
				             .timestamp(LocalDateTime.now())
				             .build());
		
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping(path = "/change-password")
	public ResponseEntity<SuccessResponse> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO){
               System.out.println("hii");
		boolean isChangeIn = employeeservice.changePassword(changePasswordDTO);
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
					         .message(EMPLOYEE_DATA_CHANGEDPASSWORD)
					         .data(isChangeIn)
					         .timestamp(LocalDateTime.now())
					         .build());
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@PutMapping(path = "/{employeeID}")
	public ResponseEntity<SuccessResponse> updateEmployee(@RequestBody EmployeeDTO employeeDTO, @PathVariable String employeeID){
		String result = employeeservice.updateEmployee(employeeDTO, employeeID);
		
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
							 .message(EMPLOYEE_DATA_UPDATE)
							 .data(employeeDTO)
							 .timestamp(LocalDateTime.now())
							 .build());
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@DeleteMapping	(path = "/")
	public ResponseEntity<SuccessResponse> deleteEmployee(@RequestBody DeleteEmployeeDTO deleteEmployeeDTO){
		return ResponseEntity.<SuccessResponse>ofNullable(SuccessResponse.builder()
				             .message(EMPLOYEE_DATA_DELETE)
				             .data(deleteEmployeeDTO.employeeId())
				             .timestamp(LocalDateTime.now())
				             .build());
	}
	
}

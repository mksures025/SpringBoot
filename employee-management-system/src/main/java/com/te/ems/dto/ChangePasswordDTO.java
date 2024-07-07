package com.te.ems.dto;

public record ChangePasswordDTO(String employeeID, 
								String password, 
								String newPassword, 
								String resetPassword) {

}

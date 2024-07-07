package com.te.ems.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Employee {
	@Id
	private String id;
	private String employeeID;
	private String employeeFN;
	private String employeeLN;
	private LocalDate employeeDOJ;

}

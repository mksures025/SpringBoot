package com.te.ems.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.catalina.User;
import org.springframework.stereotype.Repository;

import com.te.ems.dto.ChangePasswordDTO;
import com.te.ems.entity.AppUser;
import com.te.ems.entity.Employee;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {
	private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MySQL01");
	private EntityManager entityManager = entityManagerFactory.createEntityManager();

	@Override
	public String saveEmployee(Employee employee, AppUser appUser) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(employee);
		entityManager.persist(appUser);
		entityTransaction.commit();
		return employee.getEmployeeID();
	}

//	@Override
//	public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
//		EntityTransaction entityTransaction = entityManager.getTransaction();
//		entityTransaction.begin();
//		Employee employee = Employee.builder().employeeID(changePasswordDTO.employeeId()).build();
//		if (employee != null && employee.getEmployeeID() != null
//				&& employee.getEmployeeID().equals(changePasswordDTO.employeeId())) {
//			AppUser appUser = entityManager.find(AppUser.class, employee.getEmployeeID());
//			if (appUser != null && appUser.getUsername() != null && appUser.getPassword() != null
//					&& appUser.getPassword().equalsIgnoreCase(changePasswordDTO.oldPassword())) {
//
//				appUser.setPassword(changePasswordDTO.newPassword());
//				entityManager.persist(appUser);
//				entityTransaction.commit();
//				return true;
//			}
//		}
//		return false;
//	}
	
	@Override
	public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Employee employee = entityManager.find(Employee.class, changePasswordDTO.employeeID());
		if (employee != null && employee.getEmployeeID() != null
                && employee.getEmployeeID().equals(changePasswordDTO.employeeID())) {
			AppUser appUser = entityManager.find(AppUser.class, employee.getEmployeeID());
			if (appUser != null && appUser.getUsername() != null && appUser.getPassword() != null
					&& appUser.getPassword().equalsIgnoreCase(changePasswordDTO.password())) {

				appUser.setPassword(changePasswordDTO.newPassword());
                entityManager.persist(appUser);
				entityTransaction.commit();
				return true;
			}
		}
		return false;
	}

	@Override
	public Optional<Employee> getEmployee(String employeeID) {
		return Optional.<Employee>ofNullable(entityManager.find(Employee.class, employeeID));
	}

	@Override
	public String updateEmployee(Employee employee, String employeeID) {
		EntityTransaction entityTransaction = entityManager.getTransaction();
		Employee employee2 = entityManager.find(Employee.class, employeeID);
		if(employee2!=null) {
			entityTransaction.begin();
			employee2.setEmployeeFN(employee.getEmployeeFN());
			entityTransaction.commit();	
			return "update sucessfully";
		}
		return null;
	}

}

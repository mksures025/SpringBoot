package com.te.employeemanagementsystem.controllertest;


import com.te.ems.controller.EmployeeController;
import com.te.ems.dto.EmployeeDTO;
import com.te.ems.service.EmployeeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = EmployeeController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;
    private EmployeeDTO employeeDTO;

    @BeforeEach
    void setUp(){
         employeeDTO=new EmployeeDTO();
        employeeDTO.setEmployeeFN("suresh");
        employeeDTO.setEmployeeLN("mk");
        employeeDTO.setEmployeeID("123");

    }

    @Test
    void getEmployeeTest() throws Exception {

        when(employeeService.getEmployee("123"))
                .thenReturn(employeeDTO);
        this.mockMvc.perform(get("/api/employees/123")).andDo(print()
        ).andExpect(status().isOk());

    }
}

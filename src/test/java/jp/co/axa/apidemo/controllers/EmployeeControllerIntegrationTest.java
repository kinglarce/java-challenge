package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.request.EmployeeRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port + "/api/v1/employees/";
    }

    @Test
    public void testGetAllEmployees() {
        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Employee[]> response = restTemplate.getForEntity(getRootUrl(), Employee[].class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testCreateEmployee() {
        EmployeeRequest employee = new EmployeeRequest();
        employee.setName("Larce");
        employee.setSalary(8000);
        employee.setDepartment("Infra");
        ResponseEntity<String> postResponse = restTemplate.postForEntity(getRootUrl(), employee, String.class);
        assertNotNull(postResponse);
        assertEquals(postResponse.getBody(), "Employee Saved Successfully");
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = restTemplate.getForObject(getRootUrl() + "1", Employee.class);
        System.out.println(employee.getName());
        assertNotNull(employee);
        assertEquals(employee.getName(), "John");
    }

    @Test
    public void testUpdateEmployee() {
        int id = 1;
        final String uri = getRootUrl() + "{id}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);

        Employee employee = restTemplate.getForObject(uri, Employee.class, params);
        employee.setName("Jones");
        employee.setSalary(100);
        employee.setDepartment("Nintendo");

        restTemplate.put(uri, employee, params);
        Employee updatedEmployee = restTemplate.getForObject(uri, Employee.class, params);
        assertNotNull(updatedEmployee);
        assertEquals(updatedEmployee.getName(), "Jones");
    }

    @Test
    public void testDeleteEmployee() {
        int id = 2;
        final String uri = getRootUrl() + "{id}";
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("id", id);

        Employee employee = restTemplate.getForObject(uri, Employee.class, params);
        assertNotNull(employee);

        restTemplate.delete(uri, params);
        employee = restTemplate.getForObject(uri, Employee.class, params);
        assertEquals(employee, null);
    }

}
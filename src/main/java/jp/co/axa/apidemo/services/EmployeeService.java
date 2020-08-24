package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> retrieveEmployees();

    public Employee getEmployeeById(Long employeeId);

    void saveAllEmployee(List<Employee> employee);

    public Employee saveEmployee(Employee employee);

    public void deleteEmployeeBy(Long employeeId);

    public Employee updateEmployee(Employee employee);
}
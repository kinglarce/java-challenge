package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.model.request.EmployeeRequest;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<String> saveEmployee(@RequestBody @Valid EmployeeRequest request) {
        Employee employee = employeeService.saveEmployee(
                Employee.builder()
                        .name(request.getName())
                        .salary(request.getSalary())
                        .department(request.getDepartment())
                        .build()
        );

        if (Objects.nonNull(employee)) {
            return new ResponseEntity<String>("Employee Saved Successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        employeeService.deleteEmployeeBy(employeeId);
        return new ResponseEntity<String>("Employee Deleted Successfully", HttpStatus.OK);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<String> updateEmployee(@RequestBody @Valid EmployeeRequest request,
                                                 @PathVariable(name = "employeeId") Long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (Objects.nonNull(employee)) {
            employeeService.updateEmployee(
                    employee.builder()
                            .id(employeeId)
                            .name(request.getName())
                            .salary(request.getSalary())
                            .department(request.getDepartment())
                            .build()
            );
            return new ResponseEntity<String>("Employee Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }

}

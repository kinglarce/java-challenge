package jp.co.axa.apidemo;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ApiDemoApplication implements CommandLineRunner {

    private final EmployeeService employeeService;

    public static void main(String[] args) {
        SpringApplication.run(ApiDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        employeeService.saveEmployee(
                Employee.builder()
                        .name("John")
                        .salary(1000)
                        .department("DevOps")
                        .build()
        );
        employeeService.saveEmployee(
                Employee.builder()
                        .name("Doe")
                        .salary(2000)
                        .department("BigData")
                        .build()
        );
    }
}

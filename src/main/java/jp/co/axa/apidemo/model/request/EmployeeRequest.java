package jp.co.axa.apidemo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EmployeeRequest {

    @JsonProperty("name")
    @NotBlank(message = "name cannot be empty")
    private String name;

    @JsonProperty("salary")
    @NotNull(message = "salary cannot be empty")
    private Integer salary;

    @JsonProperty("department")
    @NotBlank(message = "department cannot be empty")
    private String department;
}

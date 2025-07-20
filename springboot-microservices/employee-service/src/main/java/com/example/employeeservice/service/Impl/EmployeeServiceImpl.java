package com.example.employeeservice.service.Impl;

import com.example.employeeservice.dto.APIResponseDto;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.dto.EmployeeDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRespository;
import com.example.employeeservice.service.APIClient;
import  com.example.employeeservice.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Block;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;


@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRespository employeeRespository;

   // private RestTemplate restTemplate;
   //private WebClient webClient;
 private APIClient apiClient;

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail(),
                employeeDto.getDepartmentCode()
        );
 Employee saveDemployee = employeeRespository.save(employee);

 EmployeeDto savedEmployeeDto = new EmployeeDto(
         saveDemployee.getId(),
         saveDemployee.getFirstName(),
         saveDemployee.getLastName(),
         saveDemployee.getEmail(),
         saveDemployee.getDepartmentCode()
 );
        return savedEmployeeDto;
    }

    @Override
    public APIResponseDto getEmployeeById(Long employeeId) {

      Employee employee = employeeRespository.findById(employeeId).get();

//      ResponseEntity<DepartmentDto> responseEntity =restTemplate.getForEntity("http://localhost:8080/api/departments/" + employee.getDepartmentCode(),DepartmentDto.class);
//
//      DepartmentDto departmentDto = responseEntity.getBody();

        //DepartmentDto departmentDto = webClient.get()
           //     .uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode())
            //    .retrieve()
             //   .bodyToMono(DepartmentDto.class)
               // .block();

        DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());
       EmployeeDto employeeDto = new EmployeeDto(
               employee.getId(),
               employee.getFirstName(),
               employee.getLastName(),
               employee.getEmail(),
               employee.getDepartmentCode()

       );
        APIResponseDto apiResponseDto=new APIResponseDto();
        apiResponseDto.setEmployee(employeeDto);
        apiResponseDto.setDepartment(departmentDto);



        return apiResponseDto;
    }
}

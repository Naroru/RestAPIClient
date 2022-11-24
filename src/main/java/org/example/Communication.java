package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {

    //вспомогательный класс для отправки запросов из REST клиента на сервер
    @Autowired
   private RestTemplate restTemplate;

    private final String URL = "http://localhost:8080/SpringMVC_RestAPI_2_war_exploded/api/employees/";

    public List<Employee>  getAllEmployees()
    {
        //  с помощью restTemplate мы можем отправлять http запросы. 3-тий параметр - это тело запроса ( для GET - пустое)
        // последний параметр new ParameterizedTypeReference предназначен для отправки типа генерика, который будет
        //в ResponseEntity
        ResponseEntity<List<Employee>> response = restTemplate.exchange(URL, HttpMethod.GET,
            null, new ParameterizedTypeReference<List<Employee>>() {
    });

        List<Employee> employeeList = response.getBody();
       return employeeList;
    }

    public Employee getEmployeeByID(int id) {
        //  с помощью restTemplate мы можем отправлять http запросы.
        Employee emp = restTemplate.getForObject(URL + "/" + id, Employee.class);
        return emp;

    }

    public void deleteEmployee(int id) {
        restTemplate.delete(URL + "/" + id);
        System.out.println("Emp with id "+id+" was deleted");
    }

    public void saveEmployee(Employee emp)
    {
             int id = emp.getId();
             if (id==0)
             {
                 //save
                 //объект emp будет помещен в responseEntity в виде json, поэтому мы используе String
                 //3-тий параметр - это класс объекта который будет возвращен в responseEntity
                 //а если мы там укажем Employee.class, то будет преобразование в Employee
                 ResponseEntity<String> response = restTemplate.postForEntity(URL,emp,String.class);
                 System.out.println("New emp was addded");
                 System.out.println(response.getBody());
             }
             else
             {
                 // update
                 restTemplate.put(URL,emp);
                 System.out.println("Emp with id "+id+" was updated");
             }
    }
}

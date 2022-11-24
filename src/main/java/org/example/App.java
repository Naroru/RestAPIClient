package org.example;

import org.example.configuration.MyConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        Communication communication = context.getBean("communication", Communication.class);

        /*List<Employee> list = communication.getAllEmployees();
        System.out.println(list);*/


        communication.deleteEmployee(10);
    }
}

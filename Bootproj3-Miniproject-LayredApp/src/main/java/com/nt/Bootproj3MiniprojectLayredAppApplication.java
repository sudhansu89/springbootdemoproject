package com.nt;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.nt.controller.PayrollSystemController;
import com.nt.model.Employee;

@SpringBootApplication
public class Bootproj3MiniprojectLayredAppApplication {

	public static void main(String[] args) {
		//get ioc contener
		
ApplicationContext ctx=SpringApplication.run(Bootproj3MiniprojectLayredAppApplication.class, args);

//get controller class object
PayrollSystemController controller=ctx.getBean("controller",PayrollSystemController.class);

//geather input from end user
Scanner scn=new Scanner(System.in);
System.out.println("Enter designation 1");
String desg1=scn.next().toUpperCase();

System.out.println("Enter designation 2");
String desg2=scn.next().toUpperCase();

System.out.println("Enter designation 3");
String desg3=scn.next().toUpperCase();

//INVOKE BUSINESS METHOD
   try {
	   List<Employee>listEmps=controller.showEmpDetailsByDesg(desg1,desg2,desg3);
	   
	   for(Employee e:listEmps) {
		   System.out.println(e);
	   }//end of for loop
   }   
	catch(SQLException se) {
		se.printStackTrace();
		System.out.println("Internal db problem");
	}   
   catch(Exception e) {
		e.printStackTrace();
		System.out.println("Internal UNKNOWN problem");
	}   //end of catch block
   //close contener
   ((ConfigurableApplicationContext) ctx).close();
	}//end of main method

} //end of class

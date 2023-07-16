package com.nt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nt.model.Employee;
@Repository
public class EmployeeDAOimpl implements IEmployeedao {

	private static final String GET_EMPS_BY_DESGS="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN(?,?,?)ORDER BY JOB ";
	@Autowired
private DataSource ds;
	@Override
	public List<Employee> getEmpsByDesgs(String desg1, String desg2, String desg3) throws Exception {
	List <Employee>listEmps=null;
	//get pooled jdbc con object
	try(Connection con =ds.getConnection();
			PreparedStatement ps=con.prepareStatement(GET_EMPS_BY_DESGS)){
		//set quary param values
		ps.setString(1,desg1);
		ps.setString(2,desg2);
		ps.setString(3,desg3);
		
		//exucute the quary
		try(ResultSet rs=ps.executeQuery()){
			//copy resultset object record to collection as
			listEmps=new ArrayList();
			while(rs.next()) {
				Employee emp=new Employee();
				emp.setEmpno(rs.getInt(1));
				emp.setEname(rs.getString(2));
				emp.setJob(rs.getString(3));
				emp.setSal(rs.getFloat(4));
				listEmps.add(emp);
			}//while
		}//try2
	}//try1
	catch(SQLException se) {
		se.printStackTrace();
		throw se;//exception re throwinbbg
	}
	catch(Exception e) {
		e.printStackTrace();
		throw e;
	
	}
	return listEmps;
	}
}

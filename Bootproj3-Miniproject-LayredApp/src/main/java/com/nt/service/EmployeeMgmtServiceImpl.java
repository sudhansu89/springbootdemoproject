package com.nt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.dao.IEmployeedao;
import com.nt.model.Employee;

@Service
public class EmployeeMgmtServiceImpl implements IEmployeeMgmtService {
	
	@Autowired
	IEmployeedao dao;//for calling dao class method
	
	@Override
	public List<Employee>fatchEmployeeByDesgs(String desg1, String desg2, String desg3) throws Exception {
		
		//use dao class method
		List<Employee>listimpl=dao.getEmpsByDesgs(desg1, desg2, desg3);
		return listimpl;
	}

}

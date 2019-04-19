package mcd.biz;

import java.util.List;

import mcd.domain.Employee;

public interface EmpBiz {
	//员工登录
	public Employee elogin(String account,String password);
	//人脸登录
	public Employee elogin(String account);
	//员工注册
	public String eregist(int id,String account,String password,String name,int mid,int dinid);
	//查询所有员工
	public List<Employee> findAllEmp();
	//查询单个员工
	public Employee findrEmployeeByid(int eid);
	//辞退 即删除员工
		public String delEmployee(int eid);
}

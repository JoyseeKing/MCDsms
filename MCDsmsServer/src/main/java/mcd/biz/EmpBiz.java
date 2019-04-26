package mcd.biz;

import java.util.List;

import mcd.domain.Employee;
import mcd.domain.Manager;

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
	public Employee findEmployeeByid(int eid);
	//辞退 即删除员工
	public String delEmployee(int eid);
	//查询下属
	public List<Employee> findEmployeesByid(int mid);
	//修改员工信息 只可修改密码 名字和上级 修改上级即转让此员工（要查询表里是否有这个上级的id）
	public String updateEmployee(int eid,String password,String name,int mid);
	
	
	
	
	//人脸登录
	public Employee elogin(String account,byte[] b);
	//员工注册
	public String eregist(int id,String account,String password,String name,int mid,int dinid,byte[] b);
}




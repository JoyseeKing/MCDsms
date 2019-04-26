package mcd.biz.impl;

import java.util.List;

import mcd.biz.EmpBiz;
import mcd.dao.EmpDao;
import mcd.dao.impl.EmpDaoimp;
import mcd.domain.Employee;

public class EmpBizimp implements EmpBiz{
	private EmpDao empDao;
	
	public EmpBizimp() {
		this.empDao = new EmpDaoimp();
	}

	public Employee elogin(String account, String password) {
		// TODO Auto-generated method stub
		return this.empDao.elogin(account, password);
	}

	public Employee elogin(String account) {
		// TODO Auto-generated method stub
		return this.empDao.elogin(account);
	}

	public String eregist(int id, String account, String password, String name, int mid, int dinid) {
		// TODO Auto-generated method stub
		return this.empDao.eregist(id, account, password, name, mid, dinid)?"注册成功":"注册失败";
	}

	public List<Employee> findAllEmp() {
		// TODO Auto-generated method stub
		return this.empDao.findAllEmp();
	}

	public Employee findEmployeeByid(int eid) {
		// TODO Auto-generated method stub
		return this.empDao.findEmployeeByid(eid);
	}

	public String delEmployee(int eid) {
		// TODO Auto-generated method stub
		return this.empDao.delEmployee(eid)?"已辞退":"辞退失败";
	}

	public List<Employee> findEmployeesByid(int mid) {
		// TODO Auto-generated method stub
		return this.empDao.findEmployeesByid(mid);
	}

	public String updateEmployee(int eid, String password, String name, int mid) {
		// TODO Auto-generated method stub
		return this.empDao.updateEmployee(eid, password, name, mid)?"更改成功":"更改失败";
	}

	@Override
	public Employee elogin(String account, byte[] b) {
		// TODO Auto-generated method stub
		return this.empDao.elogin(account, b);
	}

	@Override
	public String eregist(int id, String account, String password, String name, int mid, int dinid, byte[] b) {
		// TODO Auto-generated method stub
		return this.empDao.eregist(id, account, password, name, mid, dinid, b)?"注册成功":"注册失败";
	}

}

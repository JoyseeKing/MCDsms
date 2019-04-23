package mcd.biz.impl;

import java.util.List;

import mcd.biz.ManagerBiz;
import mcd.dao.MangerDao;
import mcd.dao.impl.ManagerDaoimp;
import mcd.domain.Manager;

public class ManagerBizimp implements ManagerBiz{
	private MangerDao mangerDao;
	
	public ManagerBizimp() {
		this.mangerDao = new ManagerDaoimp();
	}

	public Manager mlogin(String account, String password) {
		// TODO Auto-generated method stub
		return this.mangerDao.mlogin(account, password);
	}

	public List<Manager> findAllMgr() {
		// TODO Auto-generated method stub
		return this.mangerDao.findAllMgr();
	}

	public Manager findMgrByid(int mid) {
		// TODO Auto-generated method stub
		return this.mangerDao.findMgrByid(mid);
	}

	public Manager mlogin(String account) {
		// TODO Auto-generated method stub
		return this.mangerDao.mlogin(account);
	}

	public String mregist(int id, String account, String password, String name) {
		// TODO Auto-generated method stub
		return this.mangerDao.mregist(id, account, password, name)?"注册成功":"注册失败";
	}
	
}

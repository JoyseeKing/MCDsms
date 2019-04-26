package mcd.biz;

import java.util.List;

import mcd.domain.Employee;
import mcd.domain.Manager;

public interface ManagerBiz {
	//管理员登录
	public Manager mlogin(String account,String password);
	//查询所有管理员
	public List<Manager> findAllMgr();
	//查询所有管理员
	public Manager findMgrByid(int mid);
	//人脸登录
	public Manager mlogin(String account);
	//管理员注册
	public String mregist(int id,String account,String password,String name);
	
	
	
	

	//人脸登录
	public Manager mlogin(String account,byte[] b) ;
	//管理员注册
	public String mregist(int id,String account,String password,String name,byte[] b);

}

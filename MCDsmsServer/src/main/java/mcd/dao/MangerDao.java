package mcd.dao;

import java.util.List;

import mcd.domain.Manager;

public interface MangerDao {
	//管理员登录
	public Manager mlogin(String account,String password);
	//查询所有管理员
	public List<Manager> findAllMgr();
	//查询所有管理员
	public Manager findMgrByid(int mid);
	//人脸登录
	public Manager mlogin(String account);
	//管理员注册
	public boolean mregist(int id,String account,String password,String name);
}

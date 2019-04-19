package mcd.dao;

import mcd.domain.Manager;

public interface MangerDao {
	//使用账号密码登录
	public Manager login1(String account,String password);
	//使用id查经理 查看个人信息和修改对比的时候使用
	public Manager Findbyid(int mid);
	
	public Manager Update(int mid,String account);
}

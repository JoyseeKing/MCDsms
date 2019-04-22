package mcd.biz.impl;

import java.util.List;

import mcd.biz.CustomBiz;
import mcd.dao.CustomDao;
import mcd.dao.impl.CustomDaoimp;
import mcd.domain.Custom;

public class CustomBizimp implements CustomBiz{
	private CustomDao customDao;
	
	public CustomBizimp() {
		
		this.customDao = new CustomDaoimp();
	}

	public List<Custom> findAllCustoms() {
		// TODO Auto-generated method stub
		return this.customDao.findAllCustoms();
	}

	public Custom findCustomByid(int id) {
		// TODO Auto-generated method stub
		return this.customDao.findCustomByid(id);
	}

	public String addCustom(int cusid, String cusname, int vipid, int viplevel, double balance) {
		// TODO Auto-generated method stub
		return this.customDao.addCustom(cusid, cusname, vipid, viplevel, balance)?"客户开卡成功":"客户开卡失败";
	}

	public boolean updatebalance(int vipid, double pay) {
		// TODO Auto-generated method stub
		return this.updatebalance(vipid, pay);
	}

	public String updateisfrezz(int vipid, String isfrezz) {
		// TODO Auto-generated method stub
		return this.customDao.updateisfrezz(vipid, isfrezz)?"会员卡状态已更改":"会员卡状态更改失败";
	}

}

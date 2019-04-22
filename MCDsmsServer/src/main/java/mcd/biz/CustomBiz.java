package mcd.biz;

import java.util.List;

import mcd.domain.Custom;

public interface CustomBiz {
	//客户全部查看
	public List<Custom> findAllCustoms();
	//查询某一个客户
	public Custom findCustomByid(int id);
	//开卡
	public String addCustom(int cusid, String cusname, int vipid, int viplevel, double balance);
	//充值
	public boolean updatebalance(int vipid,double pay);
	//挂失冻结会员卡
	public String updateisfrezz(int vipid,String isfrezz);
}

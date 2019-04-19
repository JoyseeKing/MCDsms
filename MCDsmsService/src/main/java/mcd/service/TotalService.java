package mcd.service;

import java.util.Date;
import java.util.List;

import mcd.domain.Cart;
import mcd.domain.Custom;
import mcd.domain.Employee;
import mcd.domain.Food;
import mcd.domain.Manager;
import mcd.domain.Order;

public interface TotalService {
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
	//订单增加
	public String addOrder(String oid, double ototal, double ocollect, double ochange, Date date, int eid);
	//购物车添加
	public String addCart(int sid,int fid,int onum);
	//菜品查询
	public List<Food> findAllFoods();
	//购物车查询
	public List<Cart> findCarts(String oid);
	//菜品删除
	public String delCart(int sid);
	//菜品数量修改
	public String updateCart(int sid,int onum);
	//打印小票需要两个方法  查询单个 Order 和此 order对应的多个购物车信息即购物车查询
	public Order FindOrderbyid(int oid);
	//开卡
	public String addCustom(int cusid, String cusname, int vipid, int viplevel, double balance);
	//充值
	public String updatebalance(int vipid,double pay);
	//挂失冻结会员卡
	public String updateisfrezz(int vipid,String isfrezz);
	//导出订单
	public String outXML(int eid);
	//客户全部查看
	public List<Custom> findAllCustoms();
	//查询某一个客户
	public Custom findCustomByid(int id);
	//更新销量  不要调用  在daoimp我自己调用
	public void updatesellnum();
	//菜品修改折扣 输入折扣
	public String foodCutOff(int cut);
	//菜品信息修改信息 通过菜品Id
	public String updateFood(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid);
	//辞退 即删除员工
	public String delEmployee(int eid);
	//修改员工信息 只可修改密码 名字和上级 修改上级即转让此员工（要查询表里是否有这个上级的id）
	public String updateEmployee(int eid,String password,String name,int mid);
	
}

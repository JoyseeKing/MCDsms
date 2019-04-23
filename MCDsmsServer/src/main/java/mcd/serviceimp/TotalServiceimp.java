package mcd.serviceimp;


import java.util.Date;
import java.util.List;

import mcd.biz.CartBiz;
import mcd.biz.CustomBiz;
import mcd.biz.DinBiz;
import mcd.biz.EmpBiz;
import mcd.biz.FoodBiz;
import mcd.biz.ManagerBiz;
import mcd.biz.OrderBiz;
import mcd.biz.impl.CartBizimp;
import mcd.biz.impl.CustomBizimp;
import mcd.biz.impl.DinBizimp;
import mcd.biz.impl.EmpBizimp;
import mcd.biz.impl.FoodBizimp;
import mcd.biz.impl.ManagerBizimp;
import mcd.biz.impl.OrderBizimp;
import mcd.domain.Cart;
import mcd.domain.Custom;
import mcd.domain.Dining;
import mcd.domain.Employee;
import mcd.domain.Food;
import mcd.domain.Manager;
import mcd.domain.Order;
import mcd.service.TotalService;

public class TotalServiceimp implements TotalService{
	private EmpBiz empBiz;
	private ManagerBiz managerbiz;
	private DinBiz dinBiz;
	private CartBiz cartBiz;
	private OrderBiz orderBiz;
	private CustomBiz customBiz;
	private FoodBiz foodBiz;
	public TotalServiceimp() {
		super();
		this.empBiz =new EmpBizimp();
		this.managerbiz = new ManagerBizimp();
		this.dinBiz = new DinBizimp();
		this.cartBiz = new CartBizimp();
		this.orderBiz = new OrderBizimp();
		this.customBiz = new CustomBizimp();
		this.foodBiz = new FoodBizimp();
	}

	public Employee elogin(String account, String password) {
		// TODO Auto-generated method stub
		return this.empBiz.elogin(account, password);
	}

	public Employee elogin(String account) {
		// TODO Auto-generated method stub
		return this.empBiz.elogin(account);
	}

	public String eregist(int id, String account, String password, String name, int mid, int dinid) {
		// TODO Auto-generated method stub
		return this.empBiz.eregist(id, account, password, name, mid, dinid);
	}

	public List<Employee> findAllEmp() {
		// TODO Auto-generated method stub
		return this.empBiz.findAllEmp();
	}

	public Employee findEmployeeByid(int eid) {
		// TODO Auto-generated method stub
		return this.empBiz.findEmployeeByid(eid);
	}

	public Manager mlogin(String account, String password) {
		// TODO Auto-generated method stub
		return this.managerbiz.mlogin(account, password);
	}

	public List<Manager> findAllMgr() {
		// TODO Auto-generated method stub
		return this.managerbiz.findAllMgr();
	}

	public Manager findMgrByid(int mid) {
		// TODO Auto-generated method stub
		return this.managerbiz.findMgrByid(mid);
	}

	public Manager mlogin(String account) {
		// TODO Auto-generated method stub
		return this.managerbiz.mlogin(account);
	}

	public String mregist(int id, String account, String password, String name) {
		// TODO Auto-generated method stub
		return this.managerbiz.mregist(id, account, password, name);
	}

	public String addOrder(String oid, double ototal, double ocollect, double ochange, String date, int eid) {
		// TODO Auto-generated method stub
		return this.orderBiz.addOrder(oid, ototal, ocollect, ochange, date, eid);
	}

	public String addCart(String oid, int fid, int onum) {
		// TODO Auto-generated method stub
		return this.cartBiz.addCart(oid, fid, onum);
	}

	public List<Food> findAllFoods() {
		// TODO Auto-generated method stub
		return this.foodBiz.findAllFoods();
	}

	public List<Cart> findCarts(String oid) {
		// TODO Auto-generated method stub
		return this.cartBiz.findCarts(oid);
	}

	public String delCart(int sid) {
		// TODO Auto-generated method stub
		return this.cartBiz.delCart(sid);
	}

	public String updateCart(int sid, int onum) {
		// TODO Auto-generated method stub
		return this.cartBiz.updateCart(sid, onum);
	}

	public Order FindOrderbyid(String oid) {
		// TODO Auto-generated method stub
		return this.orderBiz.FindOrderbyid(oid);
	}

	public String outXML(int eid) {
		// TODO Auto-generated method stub
		return this.orderBiz.outXML(eid);
	}

	public List<Custom> findAllCustoms() {
		// TODO Auto-generated method stub
		return this.customBiz.findAllCustoms();
	}

	public String addCustom(int cusid, String cusname, int vipid, int viplevel, double balance) {
		// TODO Auto-generated method stub
		return this.customBiz.addCustom(cusid, cusname, vipid, viplevel, balance);
	}

	public Custom findCustomByid(int id) {
		// TODO Auto-generated method stub
		return this.customBiz.findCustomByid(id);
	}

	public String updateisfrezz(int vipid, String isfrezz) {
		// TODO Auto-generated method stub
		return this.customBiz.updateisfrezz(vipid, isfrezz);
	}

	public String addFood(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid) {
		// TODO Auto-generated method stub
		return this.foodBiz.addFood(fid, fname, fprice, sellnum, fstatus, typeid);
	}

	public int updatesellnum(int fid,int num) {
		// TODO Auto-generated method stub
		return this.foodBiz.updatesellnum(fid, num);
	}

	public String foodCutOff(int fid, double cut) {
		// TODO Auto-generated method stub
		return this.foodBiz.foodCutOff(fid, cut);
	}

	public String updateFood(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid) {
		// TODO Auto-generated method stub
		return this.foodBiz.updateFood(fid, fname, fprice, sellnum, fstatus, typeid);
	}

	public List<Employee> findEmployeesByid(int mid) {
		// TODO Auto-generated method stub
		return this.empBiz.findEmployeesByid(mid);
	}

	public String delEmployee(int eid) {
		// TODO Auto-generated method stub
		return this.empBiz.delEmployee(eid);
	}

	public String updateEmployee(int eid, String password, String name, int mid) {
		// TODO Auto-generated method stub
		return this.empBiz.updateEmployee(eid, password, name, mid);
	}

	public Dining FindDinrbyid(int dinid) {
		// TODO Auto-generated method stub
		return this.dinBiz.FindDinrbyid(dinid);
	}

	public String updatestatus(int fid, int status) {
		// TODO Auto-generated method stub
		return this.foodBiz.updatestatus(fid, status);
	}

	public List<Food> findAllFoodTypes() {
		// TODO Auto-generated method stub
		return this.foodBiz.findAllFoodTypes();
	}

	public Food findFoodByid(int fid) {
		// TODO Auto-generated method stub
		return this.foodBiz.findFoodByid(fid);
	}

	public boolean updatebalance(int vipid, double pay) {
		// TODO Auto-generated method stub
		return this.customBiz.updatebalance(vipid, pay);
	}

	public String updateOrderByoid(String oid, double ototal, double ocollcet, double ochange, Date date) {
		// TODO Auto-generated method stub
		return this.orderBiz.updateOrderByoid(oid, ototal, ocollcet, ochange, date);
	}

	@Override
	public List<Food> findTopFiveMonth() {
		// TODO Auto-generated method stub
		return this.foodBiz.findTopFiveMonth();
	}

	
	
}

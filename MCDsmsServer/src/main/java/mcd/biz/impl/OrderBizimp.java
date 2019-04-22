package mcd.biz.impl;



import mcd.biz.OrderBiz;
import mcd.dao.OrderDao;
import mcd.dao.impl.OrderDaoimp;
import mcd.domain.Order;
	
public class OrderBizimp implements OrderBiz{
	private OrderDao orderDao;
	
	public OrderBizimp() {
		this.orderDao = new OrderDaoimp();
	}

	public String addOrder(String oid, double ototal, double ocollect, double ochange, String date, int eid) {
		// TODO Auto-generated method stub
		return this.orderDao.addOrder(oid, ototal, ocollect, ochange, date, eid)?"订单已生成":"订单生成失败";
	}

	public Order FindOrderbyid(String oid) {
		// TODO Auto-generated method stub
		return this.orderDao.FindOrderbyid(oid);
	}

	public String outXML(int eid) {
		// TODO Auto-generated method stub
		return this.orderDao.outXML(eid)?"导出成功":"导出失败";
	}

	public String updateOrderByoid(String oid, double ototal, double ocollcet, double ochange, String date) {
		// TODO Auto-generated method stub
		return this.orderDao.updateOrderByoid(oid, ototal, ocollcet, ochange, date)?"订单已生成":"订单生成失败";
	}

}

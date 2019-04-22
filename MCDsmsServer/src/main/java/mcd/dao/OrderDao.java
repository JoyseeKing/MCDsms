package mcd.dao;

import mcd.domain.Order;

public interface OrderDao {
	//订单增加
		public boolean addOrder(String oid, double ototal, double ocollect, double ochange, String date, int eid);
		//打印小票需要两个方法  查询单个 Order 和此 order对应的多个购物车信息即购物车查询
		public Order FindOrderbyid(String oid);
		//导出订单
		public boolean outXML(int eid);
		//根据oid修改订单
		public boolean updateOrderByoid(String oid,double ototal,double ocollcet,double ochange,String date);
}

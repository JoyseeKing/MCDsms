package mcd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import mcd.dao.OrderDao;
import mcd.domain.Order;
import mcd.util.DbUtil;

public class OrderDaoimp implements OrderDao {
	DbUtil db;
	public boolean addOrder(String oid, double ototal, double ocollect, double ochange, String date, int eid) {
		db=new DbUtil();
		String sql="insert into Order (oid,ototal,ocollect,ochange,date,eid) values (?,?,?,?,to_date(?,'yyyy-mm-dd'),?)";
		try {
			int i=this.db.Update(sql,oid,ototal,ocollect,ochange,date,eid);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		}		
		
	}

	public Order FindOrderbyid(String oid) {
		// TODO Auto-generated method stub
		db=new DbUtil();
		Order order=new Order();
		String sql="select * from Order where oid=?";
		ResultSet rs;
		try {
			rs = this.db.Query(sql, oid);
			if (rs.next()) {
				order.setOid(rs.getString(1));
				order.setOtotal(rs.getDouble(2));
				order.setOcollect( rs.getDouble(3));
				order.setOchange( rs.getDouble(4));
				String str = rs.getString(5);
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
				try {
					order.setDate(sdf.parse(str));
					order.setEid(rs.getInt(6));
					return order;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			this.db.close();
		}		
		return null;	
	}

	public boolean outXML(int eid) {
		// TODO Auto-generated method stub
		boolean flag=false;
		if (flag!=true) {
			String sql="select * from eid  INTO OUTFILE 'D:\\xinxi"+eid+".xls' ";
			try {
				ResultSet rs=this.db.Query(sql);
				flag=true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}finally {
				this.db.close();
			}		
			
		}	
		return flag;
	}

	public boolean updateOrderByoid(String oid, double ototal, double ocollcet, double ochange, String date) {
		// TODO Auto-generated method stub
		db=new DbUtil();
		String sql="update Order set ototal=?,ocollect=?,ochange=?,date=to_date(?,'yyyy-MM-dd hh:mm:ss') where oid=?";
		try {
			int i=this.db.Update(sql,ototal,ocollcet,ochange,date,oid);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		}		
	}
	

}

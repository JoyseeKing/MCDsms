package mcd.dao.impl;


import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mcd.dao.OrderDao;

import mcd.domain.Order;
import mcd.util.DbUtil;

public class OrderDaoimp implements OrderDao {
	DbUtil db;
	public boolean addOrder(String oid, double ototal, double ocollect, double ochange, String date, int eid) {
		db=new DbUtil();
		String sql="insert into Order1 (oid,ototal,ocollect,ochanage,odate,empid) values (?,?,?,?,to_date(?,'yyyy-MM-dd HH:mm:ss'),?)";
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
		String sql="select * from Order1 where oid=?";
		ResultSet rs;
		try {
			rs = this.db.Query(sql, oid);
			if (rs.next()) {
				order=new Order(rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDate(5), rs.getInt(6));
//				order.setOid(rs.getString(1));
//				order.setOtotal(rs.getDouble(2));
//				order.setOcollect( rs.getDouble(3));
//				order.setOchange( rs.getDouble(4));
//				String str = rs.getString(5);
//				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
//				order.setDate(sdf.parse(str));
//			    order.setEid(rs.getInt(6));
				return order;		
			}else {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			this.db.close();
		}		
		
	}

	public boolean outXML(int eid) {
		// TODO Auto-generate00d method stub
		boolean flag=false;
		BufferedWriter bw=null;
		List<Order> list1 =new ArrayList<Order>();
		List<String> list2=new ArrayList<String>();
		try {
			bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(eid+".xls")));
			this.db=new DbUtil();
			if (flag!=true) {
				String sql="select * from Order1 where empid=? ";

				ResultSet rs=this.db.Query(sql,eid);
				while(rs.next()){
					list1.add(new Order(rs.getString(1), rs.getDouble(2), rs.getDouble(3), rs.getDouble(4), rs.getDate(5), rs.getInt(6)));
				}

				for (Order order : list1) {
					list2.add(order.toString());
				}
				for (String string : list2) {
					bw.write(string);
					bw.newLine();
					bw.flush();
				}
				flag=true;
			} }catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return false;
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag=false;
			}finally {
				this.db.close();
				if (bw!=null) {
					try {
						bw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}		
		return flag;
	}	



	public boolean updateOrderByoid(String oid, double ototal, double ocollcet, double ochange, Date  date) {
		db=new DbUtil();
		try {	
			Timestamp timestamp = new Timestamp(date.getTime());
			String sql="update Order1 set ototal=?,ocollect=?,ochanage=?,odate=? where oid=?";
				int i=this.db.Update(sql,ototal,ocollcet,ochange,timestamp,oid);
				return i>0;

	}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		}		
	}


}

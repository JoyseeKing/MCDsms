package mcd.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mcd.biz.impl.FoodBizimp;
import mcd.dao.CartDao;
import mcd.dao.FoodDao;
import mcd.domain.Cart;
import mcd.util.DbUtil;

public class CartDaoimp implements CartDao{
	DbUtil db;
	FoodDao fd=new FoodDaoimp();  
	public List<Cart> findCarts(String oid) {
		// TODO Auto-generated method stub
		db=new DbUtil();
		List<Cart> list=new ArrayList<Cart>();
		//sql语句不确定
		String sql="select c.* from Cart c,or_ca oc where oc.oid=? and c.sid=oc.sid";
		try {
			ResultSet rs=this.db.Query(sql,oid);
			while(rs.next()) {
				list.add(new Cart(rs.getInt(1), rs.getInt(2),rs.getInt(3) ));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			this.db.close();

		}

	}

	public boolean delCart(int sid) {
		// TODO Auto-generated method stub
		db=new DbUtil();
		int foodid;
		int cartnum;
		String sql1="delete from or_ca where sid=?";
		try {
			int i1=this.db.Update(sql1, sid);
			if (i1>0) {
				String sql2="select fid,onum from cart where sid=?";
				ResultSet rs=this.db.Query(sql2, sid);
				if (rs.next()) {
					foodid=rs.getInt(1);
					cartnum=rs.getInt(2);
					this.fd.updatesellnum(foodid, -cartnum);
					
						String sql4="delete from cart where sid=?";
						int i3=this.db.Update(sql4, sid);
						return i3>0;

				}else {
					return false;
				}	
			}else {
				return false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	public boolean updateCart(int sid, int onum) {
		// TODO Auto-generated method stub
		db=new DbUtil();
		int oldnum;
		int foodid;
		String sql1="select onum from cart where sid=?";

		try {
			ResultSet rs1=this.db.Query(sql1, sid);
			if (rs1.next()) {
				oldnum=rs1.getInt(1);
				String sql2="update cart set onum=? where sid=?";
				int i1=this.db.Update(sql2, onum,sid);
				if (i1>0) {
					String sql3="select fid from cart where sid=?";
					ResultSet rs2=this.db.Query(sql3, sid);
					if (rs2.next()) {
						foodid=rs2.getInt(1);
						int i2=fd.updatesellnum(foodid,onum-oldnum);
						return i2>0;
					}else {
						return false;
					}

				}else {
					return false;
				}
			}else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();

		}
	}
	public boolean addCart(String oid, int fid, int onum) {
		// TODO Auto-generated method stub
		db=new DbUtil();
		int sid;
		String sql1="insert into Cart (fid,onum)values (?,?)";
		try {
			int i1=this.db.Update(sql1,fid,onum);
			if (i1>0) {
				fd.updatesellnum(fid, onum);
				String sql2 ="select sid from cart order by sid desc";
				ResultSet rs=this.db.Query(sql2);
				if (rs.next()) {
					sid=rs.getInt(1);
					String sql3="insert into or_ca (oid,sid) values(?,?)";
					int i2=this.db.Update(sql3, oid,sid);
					return i2>0;
				}else {
					return false;
				}
			}else {
				return false;
			}

		}	
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();

		}
	}



}

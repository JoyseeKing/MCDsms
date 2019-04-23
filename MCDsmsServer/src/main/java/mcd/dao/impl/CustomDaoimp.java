package mcd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import mcd.dao.CustomDao;
import mcd.domain.Custom;
import mcd.util.DbUtil;

public class CustomDaoimp implements CustomDao{
	DbUtil db;
	public List<Custom> findAllCustoms() {
		// TODO Auto-generated method stub
		List<Custom> list=new ArrayList<Custom>();
		this.db=new DbUtil();
		String sql=" select c.*,v.* from Custom c right join Vip v on v.vipid=c.vipid ";
		try {
			ResultSet rs=this.db.Query(sql);
			while(rs.next()) {
				list.add(new Custom(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(5), rs.getDouble(6), rs.getString(7)));
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

	public Custom findCustomByid(int id) {
		// TODO Auto-generated method stub
		Custom custom=new Custom();
		this.db=new DbUtil();
		String sql="select c.*,v.* from Custom c right join Vip v on v.vipid=c.vipid where v.vipid=? ";
		try {
			ResultSet rs=this.db.Query(sql,id);
			if(rs.next()) {
				custom=new Custom(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(5), rs.getDouble(6), rs.getString(7));
				return custom;
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
		
		
	}

	public boolean addCustom(int cusid, String cusname, int vipid, int viplevel, double balance) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		String sql1=" insert  into VIP values(?,?,?,'否')";
		int i1;
		try {
			i1 = this.db.Update(sql1,vipid,viplevel,balance);
			if (i1>0) {
				String sql2="insert into Custom values(?,?,?)";
				int i2=this.db.Update(sql2, cusid,cusname,vipid);
				return i2>0;
			}
			else {
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

	public boolean updatebalance(int vipid, double pay) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		int balance;
		String sql1="select balance from VIP where vipid=?"; 
		ResultSet rs;
		try {
			rs = this.db.Query(sql1, vipid);
			if (rs.next()) {
				balance=rs.getInt(1);
				String sql2="update VIP set balance=? where vipid=?"; 
				double total=balance+pay;
				int i=this.db.Update(sql2, total,vipid);
				return i>0;
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

	public boolean updateisfrezz(int vipid, String isfrezz) {
		this.db=new DbUtil();
		String sql="update vip set isfrezz=? where vipid=?";
		try {
			if ("y".equals(isfrezz)) {
				isfrezz="是";
			}else if ("n".equals(isfrezz)) {
				isfrezz="否";
			}
			int i = this.db.Update(sql,isfrezz,vipid);
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

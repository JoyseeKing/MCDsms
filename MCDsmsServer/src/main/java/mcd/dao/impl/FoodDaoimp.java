package mcd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bytedeco.javacpp.RealSense.intrinsics;

import mcd.dao.FoodDao;
import mcd.domain.Employee;
import mcd.domain.Food;
import mcd.util.DbUtil;

public class FoodDaoimp implements FoodDao{
	DbUtil db;
	public List<Food> findAllFoods() {
		List<Food> list=new ArrayList<Food>();
		this.db=new DbUtil();
		String sql="select f.*,ft.* from food f left join foodtype ft on f.typeid=ft.ft.typeid where fstatus=1";
		try {
			ResultSet rs=this.db.Query(sql);
			while(rs.next()) {
				list.add(new Food(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getInt(4), rs.getInt(5), rs.getInt(7),rs.getString(8)));
			}
			return list;
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}finally {
				this.db.close();
			}
	}

	public int updatesellnum(int fid,int num) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		int oldsellnum;
		String sql="select sellnum from food where fid=? and fstatus=1";
		ResultSet rs;
		try {
			rs = this.db.Query(sql,fid);
			if(rs.next()) {
				oldsellnum=rs.getInt(1);
				String sql1="update food set sellnum=? where fid=? and fstatus=1";
				int i=this.db.Update(sql1,num-oldsellnum, fid);
				return i;
			}else {
				return 0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}finally {
			this.db.close();
		}
		
	}
	public boolean updateFood(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		String sql="update food set fname=?,fprice=?,sellnum=?,fstatus=?,typeid=? where fid=?";
		try {
			int i=this.db.Update(sql,fname,fprice,sellnum,sellnum,fstatus,typeid,fid);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		}
		
	}

	public boolean addFood(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		String sql="insert food values(?,?,?,?,?,?)";
		try {
			int i=this.db.Update(sql,fid,fname,fprice,sellnum,fstatus,typeid);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		}
	}

	public boolean foodCutOff(int fid, int cut) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		double oldprice;
		String sql1="select fprice from food where fid=?"; 
		try {
			ResultSet rs=this.db.Query(sql1, fid);
			if (rs.next()) {
				oldprice=rs.getDouble(1);
				String sql2="update food set fprice=?";
				int i=this.db.Update(sql2, oldprice*cut/100);
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

	public boolean updatestatus(int fid, int status) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		String sql="update food set status=? where fid=?";
		try {
			int i=this.db.Update(sql,status,fid);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		}		
		
		
	}

	public List<Food> findAllFoodTypes() {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		List<Food> list=new ArrayList<Food>();
		String sql="select * from foodtype";
		try {
			ResultSet rs=this.db.Query(sql);
			while(rs.next()) {
				list.add(new Food(rs.getInt(1), rs.getString(2)));
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

	public Food findFoodByid(int fid) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		Food food=new Food();
		String sql="select f.*,ft.* from food f left join foodtype ft on f.typeid=ft.ft.typeid where fstatus=1 and fid=?";
		try {
			ResultSet rs=this.db.Query(sql, fid);
			food=new Food(rs.getInt(1), rs.getString(2), rs.getDouble(3),rs.getInt(4), rs.getInt(5), rs.getInt(7),rs.getString(8));
			return food;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			this.db.close();
		}		
		
	}

}

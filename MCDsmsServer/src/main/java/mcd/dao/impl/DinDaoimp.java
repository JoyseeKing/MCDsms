package mcd.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import mcd.dao.DinDao;
import mcd.domain.Dining;
import mcd.util.DbUtil;

public class DinDaoimp implements DinDao{
	DbUtil db;
	public Dining FindDinrbyid(int dinid) {
		// TODO Auto-generated method stub
		Dining dining=new Dining();
		db=new DbUtil();
		String sql="select * from dining where dinid=?";
		try {
			ResultSet rs=this.db.Query(sql,dinid);
			dining=(new Dining(rs.getInt(1),rs.getString(2)));
			return dining;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally {
			this.db.close();
		}
		
	}

}

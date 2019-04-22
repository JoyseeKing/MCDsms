package mcd.dao.impl;


import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mcd.dao.MangerDao;
import mcd.domain.Employee;
import mcd.domain.Manager;
import mcd.util.Camera;
import mcd.util.DbUtil;
import mcd.util.Face;

public class ManagerDaoimp implements MangerDao{
	DbUtil db;
	Camera camera;
	Face face;
	public Manager mlogin(String account, String password) {
		// TODO Auto-generated method stub
		Manager man=new Manager();
		this.db=new DbUtil();
		
		String sql="select * from manger where maccount=? and mpassword=?";
		try {
			ResultSet rs=this.db.Query(sql,account,password);
			if (rs.next()) {
				man=new Manager(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return man;
			}else {
				return null;
			}	
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}finally {
			this.db.close();
		}
	}

	public List<Manager> findAllMgr() {
		List<Manager> list=new ArrayList<Manager>();
		this.db=new DbUtil();
		String sql="select * from Manager";
		try {
			ResultSet rs=this.db.Query(sql);
			while(rs.next()) {
				list.add(new Manager(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
			return list;
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}finally {
				this.db.close();
			}
	}

	public Manager findMgrByid(int mid) {
		// TODO Auto-generated method stub
		Manager man=new Manager();
		this.db=new DbUtil();
		String sql="select * from Manager where mid=?";
		try {
			ResultSet rs=this.db.Query(sql,mid);
			if (rs.next()) {
				man=new Manager(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
				return  man;
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

	public Manager mlogin(String account) {
		// TODO Auto-generated method stub
		
		Manager man=new Manager();
				String sql="select * from Manager where maccount=?";
				try {
					ResultSet rs=this.db.Query(sql,account);
					if (rs.next()) {
						camera.face(account+"login");
						if (face.getConfidence(account, account+"login")>0.75) {
							File file = new File(account+"login.jpg");
					                file.delete();
					                man=new Manager(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
					                return man;
						}else {
							File file = new File(account+"login.jpg");
					                file.delete();
					                return null;
						}
					}else {
						return null;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}finally {
					this.db.close();
				
				}
	}

	public boolean mregist(int id, String account, String password, String name) {
		String sql="insert into manager values(?,?,?,?)";
		try {
			int i=this.db.Update(sql,id,account,password,name);
			camera.face(account);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		}
	}

	

}

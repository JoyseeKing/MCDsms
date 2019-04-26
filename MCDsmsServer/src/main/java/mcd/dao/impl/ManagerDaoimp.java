package mcd.dao.impl;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mcd.dao.MangerDao;
import mcd.domain.Manager;
import mcd.serviceimp.TotalServiceimp;
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

		String sql="select * from manager where maccount=? and mpassword=?";
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

		String sql="select * from Manager where manid=?";
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
//		this.db=new DbUtil();
//		this.camera=new Camera();
//		this.face=new Face();
//		Manager man=new Manager();
//		String sql="select * from Manager where maccount=?";
//		try {
//			ResultSet rs=this.db.Query(sql,account);
//			if (rs.next()) {
//				this.camera.face("man/"+account+"login");
//				if (this.face.getConfidence("man/"+account, "man/"+account+"login")>75) {
//					System.out.println(face.getConfidence("man/"+account, "man/"+account+"login"));
//					File file = new File("man/"+account+"login.jpg");
//					file.delete();
//					man=new Manager(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
//					return man;
//				}else {
//					File file = new File("man/"+account+"login.jpg");
//					file.delete();
//					return null;
//				}
//			}else {
//				return null;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}finally {
//			this.db.close();
//
//		}
		return null;
	}

	public boolean mregist(int id, String account, String password, String name) {
//		this.db=new DbUtil();
//		this.camera=new Camera();
//		String sql="insert into manager values(?,?,?,?)";
//		try {
//			int i=this.db.Update(sql,id,account,password,name);
//			//			ts.Camera(account+"login");
//			this.camera.face("man/"+account);
//			return i>0;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
		return false;
	}

	@Override
	public Manager mlogin(String account, byte[] b) {
		this.db=new DbUtil();
		this.camera=new Camera();
		this.face=new Face();
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file=null;
		Manager man=new Manager();
		String sql="select * from Manager where maccount=?";
		try {
			ResultSet rs=this.db.Query(sql,account);
			if (rs.next()) {
				file = new File("mgr/"+account+"login.jpg");  
				fos = new FileOutputStream(file);  
				bos = new BufferedOutputStream(fos);  
				bos.write(b, 0, b.length);
				bos.close();
				fos.close();
				double confidence=this.face.getConfidence("mgr/"+account, "mgr/"+account+"login");
				System.out.println("相似度:"+confidence+"%");
				if (confidence>75) {
					if (file.exists()) {
						file.delete();
					}   
					man=new Manager(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
					return man;
				}else {
					if (file.exists()) {
						file.delete();
					}
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

	@Override
	public boolean mregist(int id, String account, String password, String name, byte[] b) {
		this.db=new DbUtil();
		this.camera=new Camera();
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file=null;
		String sql="insert into manager values(?,?,?,?)";
		try {
			int i=this.db.Update(sql,id,account,password,name);
			file = new File("mgr/"+account+".jpg");  
			fos = new FileOutputStream(file);  
			bos = new BufferedOutputStream(fos);  
			bos.write(b, 0, b.length);  
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
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}  
			}
			if (fos!=null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}



}

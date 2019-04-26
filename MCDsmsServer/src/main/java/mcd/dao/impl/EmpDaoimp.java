package mcd.dao.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import mcd.util.Camera;
import mcd.util.DbUtil;
import mcd.util.Face;
import mcd.dao.EmpDao;
import mcd.domain.Employee;


public class EmpDaoimp implements EmpDao{
	DbUtil db;
	Camera camera;
	Face face;
	public Employee elogin(String account, String password) {
		// TODO Auto-generated method stub
		Employee emp=new Employee();
		this.db=new DbUtil();
		
		String sql="select * from  Employee  where eaccount=? and epassword=?";
		try {
			ResultSet rs=this.db.Query(sql,account,password);
			if (rs.next()) {
				emp=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
				return emp;
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


	public Employee elogin(String account) {
		return null;
//		// TODO Auto-generated method stub
//		Employee emp=new Employee();
//		this.db=new DbUtil();
//		this.camera=new Camera();
//		this.face=new Face();
//		String sql="select * from Employee where eaccount=?";
//		try {
//			ResultSet rs=this.db.Query(sql,account);
//			if (rs.next()) {
//				this.camera.face("emp/"+account+"login");
//				if (this.face.getConfidence("emp/"+account, "emp/"+account+"login")>75) {
//					System.out.println(face.getConfidence("emp/"+account, "emp/"+account+"login"));
//					File file = new File("emp/"+account+"login.jpg");
//			                file.delete();
//			                emp=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
//			                return emp;
//				}else {
//					File file = new File("emp/"+account+"login.jpg");
//			                file.delete();
//			                return null;
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
//		
		
		
	}

	public boolean eregist(int id, String account, String password, String name, int mid, int dinid) {
		return false;
//		// TODO Auto-generated method stub
//		this.db=new DbUtil();
//		this.camera=new Camera();
//		String sql="insert into employee values(?,?,?,?,?,?)";
//		try {
//			int i=this.db.Update(sql,id,account,password,name,mid,dinid);
//			camera.face(account);
//			return i>0;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}finally {
//			this.db.close();
//		}
		
	}

	public List<Employee> findAllEmp() {
		List<Employee> list=new ArrayList<Employee>();
		this.db=new DbUtil();
		String sql="select * from employee";
		try {
			ResultSet rs=this.db.Query(sql);
			while(rs.next()) {
				list.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
			}
			return list;
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}finally {
				this.db.close();
			}
		
	}

	public Employee findEmployeeByid(int eid) {
		// TODO Auto-generated method stub
		Employee emp=new Employee();
		this.db=new DbUtil();
		String sql="select * from employee where empid=?";
		try {
			ResultSet rs=this.db.Query(sql,eid);
			if (rs.next()) {
				emp=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
				return  emp;
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

	public boolean delEmployee(int eid) {
		// TODO Auto-generated method stub
		//清除库内存储的人脸图片
		
		Employee employee=findEmployeeByid(eid);
		File file = new File("emp/"+employee.getEaccount()+".jpg");
		if (file.exists()) {
			 file.delete();
		} 
		
		String sql="delete from employee where empid="+eid;
		try {		 
			this.db=new DbUtil();
			int i= this.db.Update(sql);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}

	public List<Employee> findEmployeesByid(int mid) {
		// TODO Auto-generated method stub
		List<Employee> list=new ArrayList<Employee>();
		this.db=new DbUtil();
		String sql="select * from employee where manid=?";
		try {
			ResultSet rs=this.db.Query(sql,mid);
			while(rs.next()) {
				list.add(new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6)));
			}
			return list;
		}catch (SQLException e) {
				// TODO Auto-generated catch block
				return null;
			}finally {
				this.db.close();
			}
	}

	public boolean updateEmployee(int eid, String password, String name, int mid) {
		// TODO Auto-generated method stub
		this.db=new DbUtil();
		String sql="update employee set epassword=?,empname=?,manid=? where empid=?";
		try {
			int i=this.db.Update(sql,password,name,mid,eid);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		
		}
		
	}


	@Override
	public Employee elogin(String account, byte[] b) {
		Employee emp=new Employee();
		this.db=new DbUtil();
		this.camera=new Camera();
		this.face=new Face();
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file=null;
		String sql="select * from Employee where eaccount=?";
		try {
			ResultSet rs=this.db.Query(sql,account);
			if (rs.next()) {
				file = new File("emp/"+account+"login.jpg");  
	            fos = new FileOutputStream(file);  
	            bos = new BufferedOutputStream(fos);  
	            bos.write(b, 0, b.length);
	            bos.close();
	            fos.close();
	            double confidence=this.face.getConfidence("emp/"+account, "emp/"+account+"login");
	            System.out.println("相似度:"+confidence+"%");
				if (confidence>75) {
						if (file.exists()) {
							  file.delete();
							  System.out.println("删除成功");
						}
			                emp=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			                return emp;
				}else {
					if (file.exists()) {
						  file.delete();
						  System.out.println("删除成功");
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
	public boolean eregist(int id, String account, String password, String name, int mid, int dinid, byte[] b) {
		this.db=new DbUtil();
		this.camera=new Camera();
		BufferedOutputStream bos=null;
		FileOutputStream fos=null;
		File file=null;
		String sql="insert into employee values(?,?,?,?,?,?)";
		try {
			int i=this.db.Update(sql,id,account,password,name,mid,dinid);
			file = new File("emp/"+account+".jpg");  
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

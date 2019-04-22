package mcd.dao.impl;

import java.io.File;
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
		// TODO Auto-generated method stub
		Employee emp=new Employee();
		String sql="select * from Employee where eaccount=?";
		try {
			ResultSet rs=this.db.Query(sql,account);
			if (rs.next()) {
				camera.face(account+"login");
				if (face.getConfidence(account, account+"login")>0.75) {
					File file = new File(account+"login.jpg");
			                file.delete();
			                emp=new Employee(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
			                return emp;
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

	public boolean eregist(int id, String account, String password, String name, int mid, int dinid) {
		// TODO Auto-generated method stub
		String sql="insert into employee values(?,?,?,?,?,?)";
		try {
			int i=this.db.Update(sql,id,account,password,name,mid,dinid);
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
		String sql="select * from employee where eid=?";
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
		File file = new File(employee.getEaccount()+"login.jpg");
        file.delete();
        String sql="delete from employee where eid=?";
		try {
			int i= this.db.Update(sql,eid);
			return i>0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}finally {
			this.db.close();
		}
		
		
	}

	public List<Employee> findEmployeesByid(int mid) {
		// TODO Auto-generated method stub
		List<Employee> list=new ArrayList<Employee>();
		this.db=new DbUtil();
		String sql="select * from employee where mid=?";
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
		String sql="update employee set epassword=?,ename=?,mid=? where id=?";
		try {
			int i=this.db.Update(sql,password,name,mid);
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
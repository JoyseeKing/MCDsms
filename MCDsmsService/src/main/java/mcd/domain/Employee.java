package mcd.domain;

import java.io.Serializable;

public class Employee implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10086;
	private int eid;
	private String eaccount;
	private String epassword;
	private String ename;
	private int mid;
	private int dinid;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(int eid, String eaccount, String epassword, String ename, int mid, int dinid) {
		super();
		this.eid = eid;
		this.eaccount = eaccount;
		this.epassword = epassword;
		this.ename = ename;
		this.mid = mid;
		this.dinid = dinid;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getEaccount() {
		return eaccount;
	}

	public void setEaccount(String eaccount) {
		this.eaccount = eaccount;
	}

	public String getEpassword() {
		return epassword;
	}

	public void setEpassword(String epassword) {
		this.epassword = epassword;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getDinid() {
		return dinid;
	}

	public void setDinid(int dinid) {
		this.dinid = dinid;
	}

	@Override
	public String toString() {
		return eid + "\t" + eaccount + "\t" + epassword + "\t" + ename
				+ "\t" + mid ;
	}
	
}

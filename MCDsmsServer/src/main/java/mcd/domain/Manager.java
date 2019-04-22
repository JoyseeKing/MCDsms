package mcd.domain;

import java.io.Serializable;

public class Manager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10086;
	private int mid;
	private String maccount;
	private String mpassword;
	private String mname;
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Manager(int mid, String maccount, String mpassword, String mname) {
		super();
		this.mid = mid;
		this.maccount = maccount;
		this.mpassword = mpassword;
		this.mname = mname;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMaccount() {
		return maccount;
	}
	public void setMaccount(String maccount) {
		this.maccount = maccount;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	@Override
	public String toString() {
		return  mid + "\t" + maccount + "\t" + mpassword + "\t" + mname ;
	}
	
}

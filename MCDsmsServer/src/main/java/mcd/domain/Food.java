package mcd.domain;

import java.io.Serializable;

public class Food implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10086;
	private int fid;
	private String fname;
	private double fprice;
	private int sellnum;
	private int fstatus;
	private int typeid;
	private String typename;
	public Food() {
		super();
	}
	public Food(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.fprice = fprice;
		this.sellnum = sellnum;
		this.fstatus = fstatus;
		this.typeid = typeid;
	}
	public Food(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid, String typename) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.fprice = fprice;
		this.sellnum = sellnum;
		this.fstatus = fstatus;
		this.typeid = typeid;
		this.typename = typename;
	}
	public Food(int typeid, String typename) {
		super();
		this.typeid = typeid;
		this.typename = typename;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public double getFprice() {
		return fprice;
	}
	public void setFprice(double fprice) {
		this.fprice = fprice;
	}
	public int getSellnum() {
		return sellnum;
	}
	public void setSellnum(int sellnum) {
		this.sellnum = sellnum;
	}
	public int getFstatus() {
		return fstatus;
	}
	public void setFstatus(int fstatus) {
		this.fstatus = fstatus;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return fid + "\t" + fname + "\t" + fprice + "\t" + sellnum+ "\t" + fstatus ;
	}
}

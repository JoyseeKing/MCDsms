package mcd.domain;

import java.io.Serializable;

public class Custom implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10086;
	private int cusid;
	private String cusname;
	private int vipid;
	private int viplevel;
	private double balance;
	private String isfrezz;
	public int getCusid() {
		return cusid;
	}
	public void setCusid(int cusid) {
		this.cusid = cusid;
	}
	public String getCusname() {
		return cusname;
	}
	public void setCusname(String cusname) {
		this.cusname = cusname;
	}
	public int getVipid() {
		return vipid;
	}
	public void setVipid(int vipid) {
		this.vipid = vipid;
	}
	public int getViplevel() {
		return viplevel;
	}
	public void setViplevel(int viplevel) {
		this.viplevel = viplevel;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getIsfrezz() {
		return isfrezz;
	}
	public void setIsfrezz(String isfrezz) {
		this.isfrezz = isfrezz;
	}
	public Custom() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Custom(int cusid, String cusname) {
		super();
		this.cusid = cusid;
		this.cusname = cusname;
	}
	public Custom(int cusid, String cusname, int vipid, int viplevel, double balance, String isfrezz) {
		super();
		this.cusid = cusid;
		this.cusname = cusname;
		this.vipid = vipid;
		this.viplevel = viplevel;
		this.balance = balance;
		this.isfrezz = isfrezz;
	}
	@Override
	public String toString() {
	// TODO Auto-generated method stub
	return cusid+"\t"+cusname+"\t"+vipid+"\t"+viplevel+"\t"+balance+"\t"+isfrezz;
	}
}

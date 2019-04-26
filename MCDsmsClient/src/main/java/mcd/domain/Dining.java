package mcd.domain;

import java.io.Serializable;

public class Dining implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10086;
	private int dinid;
	private String dinname;
	public Dining() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dining(int dinid, String dinname) {
		super();
		this.dinid = dinid;
		this.dinname = dinname;
	}
	public int getDinid() {
		return dinid;
	}
	public void setDinid(int dinid) {
		this.dinid = dinid;
	}
	public String getDinname() {
		return dinname;
	}
	public void setDinname(String dinname) {
		this.dinname = dinname;
	}
	@Override
	public String toString() {
		return "餐厅编号:" + dinid + "\t餐厅名称:" + dinname ;
	}
	
}

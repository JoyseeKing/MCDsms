package mcd.domain;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 10086;
	private String oid;
	private double ototal;
	private double ocollect;
	private double ochange;
	private Date date;
	private int eid;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(String oid, double ototal, double ocollect, double ochange, Date date, int eid) {
		super();
		this.oid = oid;
		this.ototal = ototal;
		this.ocollect = ocollect;
		this.ochange = ochange;
		this.date = date;
		this.eid = eid;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public double getOtotal() {
		return ototal;
	}
	public void setOtotal(double ototal) {
		this.ototal = ototal;
	}
	public double getOcollect() {
		return ocollect;
	}
	public void setOcollect(double ocollect) {
		this.ocollect = ocollect;
	}
	public double getOchange() {
		return ochange;
	}
	public void setOchange(double ochange) {
		this.ochange = ochange;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return oid+"\t"+ototal+"\t"+ocollect+"\t"+ochange+"\t"+date+"\t"+eid ;
	}
}

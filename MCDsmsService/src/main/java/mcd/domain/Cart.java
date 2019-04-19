package mcd.domain;

public class Cart {
	private int cid;
	private int fid;
	private int num;
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cart(int cid, int fid, int num) {
		super();
		this.cid = cid;
		this.fid = fid;
		this.num = num;
	}
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "菜品名称" + cid + "\t数量" + num + "]";
	}
	
	
}

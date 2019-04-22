package mcd.dao;

import java.util.List;

import mcd.domain.Cart;

public interface CartDao {
	//购物车查询
	public List<Cart> findCarts(String oid);
	//菜品删除
	public boolean delCart(int sid);
	//菜品数量修改
	public boolean updateCart(int sid,int onum);
	//购物车添加
	public boolean addCart(String oid,int fid,int onum);
}

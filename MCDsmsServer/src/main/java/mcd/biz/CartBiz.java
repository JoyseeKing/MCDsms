package mcd.biz;

import java.util.List;


import mcd.domain.Cart;

public interface CartBiz {
	//购物车查询
	public List<Cart> findCarts(String oid);
	//菜品删除
	public String delCart(int sid);
	//菜品数量修改
	public String updateCart(int sid,int onum);
	//购物车添加
	public String addCart(String oid,int fid,int onum);
}

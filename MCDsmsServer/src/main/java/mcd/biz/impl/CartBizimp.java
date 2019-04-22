package mcd.biz.impl;

import java.util.List;

import mcd.biz.CartBiz;
import mcd.dao.CartDao;
import mcd.dao.impl.CartDaoimp;
import mcd.domain.Cart;


public class CartBizimp implements CartBiz{
	private CartDao cartDao;
	
	public CartBizimp() {
		this.cartDao =new CartDaoimp();
	}

	public List<Cart> findCarts(String oid) {
		// TODO Auto-generated method stub
		return this.cartDao.findCarts(oid);
	}

	public String delCart(int sid) {
		// TODO Auto-generated method stub
		return this.cartDao.delCart(sid)?"移除购物车成功":"移除购物车失败";
	}

	public String updateCart(int sid, int onum) {
		// TODO Auto-generated method stub
		return this.cartDao.updateCart(sid, onum)?"更改数量成功":"更改数量失败";
	}

	public String addCart(String oid,int fid,int onum) {
		// TODO Auto-generated method stub
		return this.cartDao.addCart(oid, fid, onum)?"添加购物车成功":"添加购物车失败";
	}

	
}

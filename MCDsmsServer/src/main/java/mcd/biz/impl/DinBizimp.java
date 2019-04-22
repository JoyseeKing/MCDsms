package mcd.biz.impl;

import mcd.biz.DinBiz;
import mcd.dao.DinDao;
import mcd.dao.impl.DinDaoimp;
import mcd.domain.Dining;

public class DinBizimp implements DinBiz{
	private DinDao dinDao;
	
	public DinBizimp() {
		this.dinDao =new DinDaoimp();
	}

	public Dining FindDinrbyid(int dinid) {
		// TODO Auto-generated method stub
		return this.dinDao.FindDinrbyid(dinid);
	}

}

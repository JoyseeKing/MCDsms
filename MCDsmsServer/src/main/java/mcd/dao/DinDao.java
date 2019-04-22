package mcd.dao;

import mcd.domain.Dining;

public interface DinDao {
	//查询餐厅
		public Dining FindDinrbyid(int dinid);
}

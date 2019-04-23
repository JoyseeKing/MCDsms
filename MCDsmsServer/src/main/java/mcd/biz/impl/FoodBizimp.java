package mcd.biz.impl;

import java.util.List;


import mcd.biz.FoodBiz;
import mcd.dao.FoodDao;
import mcd.dao.impl.FoodDaoimp;
import mcd.domain.Food;

public class FoodBizimp implements FoodBiz{
	private FoodDao foodDao;
	
	public FoodBizimp() {
		this.foodDao = new FoodDaoimp();
	}
	public List<Food> findAllFoods() {
		// TODO Auto-generated method stub
		return this.foodDao.findAllFoods();
	}
	public String updateFood(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid) {
		// TODO Auto-generated method stub
		return this.foodDao.updateFood(fid, fname, fprice, sellnum, fstatus, typeid)?"菜品更改成功":"菜品更改失败";
	}

	public String addFood(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid) {
		// TODO Auto-generated method stub
		return this.foodDao.addFood(fid, fname, fprice, sellnum, fstatus, typeid)?"菜品更改成功":"菜品更改失败";
	}

	public String updatestatus(int fid, int status) {
		// TODO Auto-generated method stub
		return this.foodDao.updatestatus(fid, status)?"菜品状态已更改":"菜品状态更改失败";
	}

	public List<Food> findAllFoodTypes() {
		// TODO Auto-generated method stub
		return this.foodDao.findAllFoodTypes();
	}

	public Food findFoodByid(int fid) {
		// TODO Auto-generated method stub
		return this.foodDao.findFoodByid(fid);
	}

	public int updatesellnum(int fid,int num) {
		// TODO Auto-generated method stub
		return this.foodDao.updatesellnum(fid, num);
	}

	public String foodCutOff(int fid, double cut) {
		// TODO Auto-generated method stub
		return this.foodDao.foodCutOff(fid, cut)?"设置折扣成功":"设置折扣失败";
	}
	@Override
	public List<Food> findTopFiveMonth() {
		// TODO Auto-generated method stub
		return this.foodDao.findTopFiveMonth();
	}

	

}

package mcd.dao;

import java.util.List;

import mcd.domain.Food;

public interface FoodDao {
	//菜品查询
		public List<Food> findAllFoods();
		//更新销量  不要调用  在daoimp我自己调用
		public int updatesellnum(int fid,int num);
		//菜品修改折扣 输入折扣
		public boolean foodCutOff(int fid, int cut);
		//菜品信息修改信息 通过菜品Id
		public boolean updateFood(int fid, String fname, double fprice, int sellnum, int fstatus, int typeid);
		//菜品添加
		public boolean addFood(int fid, String fname, double fprice,int sellnum,int fstatus, int typeid);
		//菜品下架
		public boolean updatestatus(int fid,int status);
		//查询所有菜品种类
		public List<Food> findAllFoodTypes();
		//根据id查菜品
		public Food findFoodByid(int fid);
}

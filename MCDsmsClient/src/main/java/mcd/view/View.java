package mcd.view;

public class View {
	public void welcome() {
		System.out.println("\t金拱门连锁餐厅管理系统");
		System.out.println("====================================");
		System.out.println("\t1、员工登录");
		System.out.println("\t2、员工注册");
		System.out.println("\t3、管理员登录");
		System.out.println("\t4、管理员注册");
		System.out.println("\t0、退出系统");
		System.out.println("------------------------------------");
	}
	public void emplogin() {
		System.out.println("====================================");
		System.out.println("\t1、点餐系统");
		System.out.println("\t2、客户开卡");
		System.out.println("\t3、会员卡充值");
		System.out.println("\t4、客户会员卡挂失");
		System.out.println("\t5、人气菜品");
		System.out.println("\t6、订单导出");
		System.out.println("\t0、注销登录");
		System.out.println("------------------------------------");
	}
	public void orderFood() {
		System.out.println("====================================");
		System.out.println("\t1、购物车添加");
		System.out.println("\t2、购物车查询");
		System.out.println("\t3、菜品数量修改");
		System.out.println("\t4、菜品删除");		
		System.out.println("\t0、退出并打印小票");
		System.out.println("------------------------------------");
	}
	public void mgrlogin() {
		System.out.println("====================================");
		System.out.println("\t1、客户卡操作");
		System.out.println("\t2、菜品管理");
		System.out.println("\t3、员工管理");
		System.out.println("\t0、注销登录");
		System.out.println("------------------------------------");
	}
	public void cardOp() {
		System.out.println("====================================");
		System.out.println("\t1、查看客户卡");
		System.out.println("\t2、冻结或解冻客户卡");
		System.out.println("\t3、补办客户卡");
		System.out.println("\t0、退出");
		System.out.println("------------------------------------");
	}
	public void empOp() {
		System.out.println("====================================");
		System.out.println("\t1、查看员工信息");
		System.out.println("\t2、修改员工信息");
		System.out.println("\t3、员工辞退");
		System.out.println("\t0、退出");
		System.out.println("------------------------------------");
	}
	public void foodOp() {
		System.out.println("====================================");
		System.out.println("\t1、添加新式菜品");
		System.out.println("\t2、查询所有菜品");
		System.out.println("\t3、修改菜品信息");
		System.out.println("\t4、特价菜设置");
		System.out.println("\t5、菜品下架");	
		System.out.println("\t6、月营业额");
		System.out.println("\t0、退出");
		System.out.println("------------------------------------");
	}
	public void println(String msg) {
		System.out.println(msg);
	}
}

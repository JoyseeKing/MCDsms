package mcd.control;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;

import org.bytedeco.javacpp.RealSense.intrinsics;

import java.awt.GridLayout;
import java.io.*;

import mcd.domain.Cart;
import mcd.domain.Custom;
import mcd.domain.Dining;
import mcd.domain.Employee;
import mcd.domain.Food;
import mcd.domain.Manager;
import mcd.domain.Order;
import mcd.service.TotalService;
import mcd.util.Camera;
import mcd.util.PieChart;
import mcd.util.UserInput;
import mcd.view.View;

public class Control {
	//属性
	private View v;
	private UserInput ui;
	private TotalService ts;
	private String oid;
	public static final String IP="10.10.49.83";
	public static final int PORT=10086;
	Camera camera;
	PieChart p;
	public Control() {
		super();
		this.v = new View();
		this.ui = new UserInput();
		this.ts = new ProxyClient().getClient(TotalService.class, IP, PORT);
	}
	//系统开始菜单
	public void start() {
		while(true) {
			this.v.welcome();
			int select=this.ui.getInt("请选择：");
			if (select==0) {
				this.v.println("系统终止");
				System.exit(0);
			}else if (select==1) {
				//员工登录
				this.elogin();
				this.empWelcome();
			}else if (select==2) {
				//员工注册
				this.addEmp();
			}else if (select==3) {
				//管理员登录
				this.mlogin();
				this.mgrWelcome();
			}else if (select==4) {
				//管理员注册
				this.addMgr();
			}
		}
	}

	//员工登录后的菜单
	private void empWelcome() {
		while(true) {
			this.v.emplogin();
			int select=this.ui.getInt("请选择：");
			if (select==0) {
				this.v.println("退出登录");
				break;
			}else if (select==1) {
				//点餐系统
				this.orderWelcome();
			}else if (select==2) {
				//客户开卡
				this.addCustom();
			}else if (select==3) {
				//会员卡充值
				this.chargeCustom();
			}else if (select==4) {
				//客户会员卡挂失
				this.suspendCustom();
			}else if (select==5) {
				//人气菜品
				this.hotFood();
			}else if (select==6) {
				//订单导出
				this.outOrder();
			}
		}		
	}

	//员工进入点餐系统后的菜单
	private void orderWelcome() {
		UUID uuid=UUID.randomUUID();
		oid=uuid.toString().replace("-", "");
		this.ts.addOrder(oid, 0, 0, 0, null, emp.getEid());
		while(true) {
			this.v.orderFood();
			int select=this.ui.getInt("请选择：");
			if (select==0) {
				this.v.println("退出并打印小票：");
				this.printNote();
				break;
			}else if (select==1) {
				//购物车添加
				this.addCart();
			}else if (select==2) {
				//购物车查询
				this.selectCart();
			}else if (select==3) {
				//菜品修改
				this.updateCart();
			}else if (select==4) {
				//菜品删除
				this.deleteCart();
			}			
		}		
	}
	//打印小票
	private void printNote() {
		double ototal=0,t=0,ocollcet=0,totalNum=0;
		Custom custom = null;
		List<Cart> carts=this.ts.findCarts(oid);
		for (Cart cart : carts) {
			Food f = this.ts.findFoodByid(cart.getFid());
			t=cart.getNum()*(f.getFprice());
			ototal+=t;
		}		
		String s=this.ui.getString("是否会员卡支付？(y/n)");
		int vipid = 0;
		if ("y".equals(s)) {
			while(true) {
				vipid=this.ui.getInt("请输入会员卡号：");
				custom = this.ts.findCustomByid(vipid);
				if (custom.getCusname()==null) {
					System.out.println("该会员不存在！");
				}else {
					int i = custom.getViplevel();
					if (i==1) {
						System.out.print("原价"+ototal+"元\t普通会员九折："+ototal*0.9+"元");
						ototal=ototal*0.9;
					}else if (i==2) {
						System.out.print("原价"+ototal+"元\t超级会员八折："+ototal*0.8+"元");
						ototal=ototal*0.8;
					}
					ocollcet=custom.getBalance();
					break;
				}
			}				
		}else if("n".equals(s)) {
			ocollcet=this.ui.getDouble("请输入收款金额：");
		}
		double ochange=ocollcet-ototal;		
		Date date = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String date = sdf.format(d);	
		this.ts.updateOrderByoid(oid, ototal, ocollcet, ochange, date);	
		Order order = this.ts.FindOrderbyid(oid);
		System.out.println();
		System.out.println();
		this.v.println("\t MCD 连锁餐厅欢迎您");
		this.v.println("   Welcomes to Mcd chain restaurant");
		this.v.println("-----------------------------------------");
		Date d=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.v.println("下单时间："+sdf.format(d));
		this.v.println("订  单  号："+oid);
		this.v.println("-----------------------------------------");
		this.v.println("-----------------------------------------");
		//this.v.println("菜品编号\t菜品名称\t购买数量\t菜品种类\t单价\t小计");
		List<Cart> list = this.ts.findCarts(oid);	
		for (Cart cart : carts) {
			Food food = this.ts.findFoodByid(cart.getFid());
			System.out.println("\t"+food.getFid()+"\t\t"+food.getFname());
			System.out.println("\t"+food.getFprice()+"  ×  "+cart.getNum()+"  =  "+food.getFprice()*cart.getNum());
			System.out.println();
			totalNum+=cart.getNum();
		}
		this.v.println("-----------------------------------------");
		this.v.println("\t合    计："+totalNum+"件");
		this.v.println("\t总    计："+order.getOtotal()+"元");
		this.v.println("-----------------------------------------");
		this.v.println("\t会员卡："+custom.getVipid());
		this.v.println("\t收    银："+order.getOcollect()+"元");
		this.v.println("\t找    零："+order.getOchange()+"元");
		this.v.println("\t操作员："+emp.getEname());
		Dining dining = this.ts.FindDinrbyid(emp.getDinid());
		this.v.println("\t餐    厅："+dining.getDinname());
		System.out.println();
		this.v.println("\t"+"   "+"欢迎再次光临！");
		this.v.println("\t如有质量问题七天内凭小票解决");
		System.out.println();
		System.out.println();

		this.ts.updatebalance(vipid,-order.getOtotal());
	}
	//点餐系统购物车菜品删除
	private void deleteCart() {
		boolean flag=false;
		int cid=this.ui.getInt("请输入要删除的购物车编号：");
		List<Cart> carts=this.ts.findCarts(oid);
		for (Cart cart : carts) {
			if (cart.getCid()==cid) {
				String s = this.ts.delCart(cid);
				System.out.println(s);
				flag=true;
				break;
			}					
		}
		if (flag==false) {
			System.out.println("记录不存在");
		}	
	}
	//点餐系统购物车菜品数量修改(int sid,int onum);
	private void updateCart() {
		boolean flag=false;
		int cid=this.ui.getInt("请输入要修改的购物车编号：");
		int onum=0;
		List<Cart> carts=this.ts.findCarts(oid);
		for (Cart cart : carts) {
			if (cart.getCid()==cid) {
				onum=this.ui.getInt("请输入新的数量：");
				String s = this.ts.updateCart(cid, onum);
				System.out.println(s);
				flag=true;
				break;
			}					
		}		
		if(flag==false) {
			this.v.println("记录不存在！");
		}
	}
	//点餐系统购物车菜品查询
	private void selectCart() {
		List<Cart> carts=this.ts.findCarts(oid);
		//System.out.println(oid+"---2");
		this.v.println("购物车编号\t菜品编号\t菜品名称\t购买数量\t菜品种类\t单价\t小计");
		for (Cart cart : carts) {
			Food food = this.ts.findFoodByid(cart.getFid());
			System.out.println(cart.getCid()+"\t"+cart.getFid()+"\t"+food.getFname()+"\t"+cart.getNum()+"\t"+food.getTypename()+"\t"+food.getFprice()+"\t"+food.getFprice()*cart.getNum());
		}
	}
	//点餐系统购物车菜品添加
	private void addCart() {
		List<Food> foods = this.ts.findAllFoods();
		System.out.println("菜品编号\t菜品名称\t菜品价格\t销量\t种类\t状态");
		for (Food food : foods) {
			System.out.println(food);
		}
		while(true) {
			int fid=this.ui.getInt("请输入您要选择的菜品(0退出)：");
			if (fid==0) {
				break;
			}
			Food food = this.ts.findFoodByid(fid);
			if (food.getFname()==null) {
				System.out.println("菜品不存在！");
			}else {
				int onum=this.ui.getInt("请输入购买份数：");
				String s = this.ts.addCart(oid,fid, onum);
				System.out.println(s);
			}
		}		
	}
	//人气菜品
	private void hotFood() {
		List<Food> list = this.ts.findTopFiveMonth();
		System.out.println("名次\t菜品编号\t菜品名称\t单价\t销量\t类型名称\t状态");
		int i=0;
		for (Food food : list) {
			i++;
			System.out.println("第"+i+"名\t"+food);
		}
		System.out.println();
		System.out.println();
	}
	//客户开卡
	private void addCustom() {
		boolean flag=false;
		int cusid=this.ui.getInt("请输入会员编号：");
		Custom custom = this.ts.findCustomByid(cusid);
		if (custom!=null) {
			System.out.println("会员已存在！");
		}else {
			String cusname=this.ui.getString("请输入会员姓名：");
			int vipid=this.ui.getInt("请输入会员卡编号：");  
			List<Custom> customs = this.ts.findAllCustoms();
			for (Custom c : customs) {
				if (vipid==c.getVipid()) {
					System.out.println("会员卡已存在！");
					flag=true;
					break;
				}
			}
			if (flag==false) {
				int viplevel=this.ui.getInt("请输入会员卡等级：");  
				double balance=this.ui.getDouble("请输入会员卡余额：");
				String s=this.ts.addCustom(cusid, cusname, vipid, viplevel, balance);
				System.out.println(s);
			}
		}		
	}
	//客户会员卡挂失
	private void suspendCustom() {
		int vipid=this.ui.getInt("请输入要挂失的会员卡编号：");
		Custom custom = this.ts.findCustomByid(vipid);
		if (custom.getCusname()==null) {
			System.out.println("该会员不存在！");
		}else {
			String isfrezz="y";
			String s = this.ts.updateisfrezz(vipid, isfrezz);
			System.out.println(s);
		}	
	}
	//会员卡充值
	private void chargeCustom() {
		int vipid=this.ui.getInt("请输入要充值的会员卡编号：");
		Custom custom = this.ts.findCustomByid(vipid);
		if (custom.getCusname()==null) {
			System.out.println("该会员不存在！");
		}else {
			double pay=this.ui.getDouble("请输入要充值的金额(每个月28日充卡 冲200返50)：");
			Calendar now = Calendar.getInstance();
			//System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
			if (now.get(Calendar.DAY_OF_MONTH)==28) {
				if (pay>=200) {
					pay=pay+50;
				}
			}		
			boolean b = this.ts.updatebalance(vipid, pay);
			String s=b?"充值成功":"充值失败";
			System.out.println(s);
			Custom c = this.ts.findCustomByid(vipid);
			System.out.println("会员卡余额："+c.getBalance());
		}		
	}
	//订单导出
	private void outOrder() {
		byte[] bs = this.ts.outXML(emp.getEid());
		if (bs!=null) {
			this.v.println("导出成功！");
		}else {
			this.v.println("导出失败！");
		}
		BufferedOutputStream bos=null;
		try {
			bos = new BufferedOutputStream(new FileOutputStream("D:\\xinxi\\"+emp.getEid()+".xls"));
			bos.write(bs,0,bs.length);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(bos!=null)bos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
	}
	//经理登录后的菜单
	private void mgrWelcome() {
		while(true) {
			this.v.mgrlogin();
			int select=this.ui.getInt("请选择：");
			if (select==0) {
				this.v.println("退出登录");
				break;
			}else if (select==1) {
				//客户卡操作
				this.customWelcome();
			}else if (select==2) {				
				//菜品管理
				this.foodWelcome();
			}else if (select==3) {
				//员工管理
				this.empOpWelcome();
			}
		}		
	}
	//经理进入客户卡操作后的菜单
	private void customWelcome() {
		while(true) {
			this.v.cardOp();
			int select=this.ui.getInt("请选择：");
			if (select==0) {
				this.v.println("退出");
				break;
			}else if (select==1) {
				//查看客户卡
				this.selectCustom();
			}else if (select==2) {
				//冻结客户卡
				this.deleteCustom();
			}else if (select==3) {
				//补办客户卡
				this.replaceCustom();
			}
		}
	}	
	//查看客户卡
	private void selectCustom() {
		List<Custom> customs = this.ts.findAllCustoms();
		System.out.println("客户编号\t客户姓名\t会员卡编号\t会员卡等级\t余额\t是否挂失");
		for (Custom custom : customs) {
			System.out.println(custom);
		}		
	}

	//冻结或解冻客户卡
	private void deleteCustom() {
		int vipid=this.ui.getInt("请输入要冻结或解冻的会员卡编号：");
		Custom custom = this.ts.findCustomByid(vipid);
		if (custom.getCusname()==null) {
			System.out.println("该会员不存在！");
		}else {
			String isfrezz=this.ui.getString("更改状态(y冻结/n解冻)：");
			String s = this.ts.updateisfrezz(vipid, isfrezz);
			System.out.println(s);
		}		
	}

	//补办客户卡
	private void replaceCustom() {
		int c=this.ui.getInt("请输入要补办卡的客户编号：");
		Custom custom = this.ts.findCustomByid(c);
		if (custom.getCusname()==null) {
			System.out.println("客户不存在！");
		}else {
			String cusname=custom.getCusname();
			int vipid=this.ui.getInt("请输入新的会员卡编号：");
			int viplevel=custom.getViplevel();
			int cusid=this.ui.getInt("请输入新的客户编号：");
			double balance=custom.getBalance();
			String s = this.ts.addCustom(cusid, cusname, vipid, viplevel, balance);
			System.out.println(s);
		}		
	}
	//经理进入菜品管理后的菜单
	private void foodWelcome() {
		while(true) {
			this.v.foodOp();
			int select=this.ui.getInt("请选择：");
			if (select==0) {
				this.v.println("退出");
				break;
			}else if (select==1) {
				//添加新式菜品
				this.addFood();
			}else if (select==2) {
				//查询所有菜品
				this.selectFood();
			}else if (select==3) {
				//修改菜品信息
				this.updateFood();
			}else if (select==4) {
				//特价菜设置
				this.updateFoodPrice();
			}else if (select==5) {
				//菜品下架
				this.deleteFood();
			}else if (select==6) {
				//月营业额
				this.topMonth();
			}
		}
	}
	//月销售额
	private void topMonth() {
		double total=0;
		System.out.println("菜品编号\t菜品名称\t价格\t销量\t类型\t状态(0下架/1上架)");
		List<Food> list = this.ts.findTopFiveMonth();
		JFrame frame=new JFrame("餐厅营业额数据统计图");  
		this.p=new PieChart(list);
		p.getDataSet(list);
 	    frame.setLayout(new GridLayout(2,2,10,10));  
 	    frame.add(p.getChartPanel());//添加饼状图  
 	    frame.setBounds(100, 100, 750, 750);  
 	    frame.setVisible(true);  

		for (Food food : list) {
			total+=food.getFprice()*food.getSellnum();
			System.out.println(food);
		}
		System.out.println("月营业总额："+total+"元");
	}
	//特价菜设置
	private void updateFoodPrice() {
		int fid=this.ui.getInt("请输入想修改的菜品编号：");

		Food oldfood = this.ts.findFoodByid(fid);
		double oldprice=oldfood.getFprice();

		double cut=this.ui.getDouble("请输入特价菜的折扣(折扣%)：");
		String s = this.ts.foodCutOff(fid, cut);		
		System.out.println(s);		
		Food newfood = this.ts.findFoodByid(fid);
		double newprice=newfood.getFprice();
		System.out.println("原价："+oldprice+"\t现价:"+newprice);
	}
	//添加新式菜品
	private void addFood() {
		boolean flag1=false,flag2=false;
		int fid=this.ui.getInt("请输入新式菜品编号：");
		List<Food> foods = this.ts.findAllFoods();
		for (Food food : foods) {
			if (food.getFid()==fid) {
				System.out.println("编号已存在！");
				flag1=true;
				break;
			}
		}
		if (flag1==false) {
			String fname=this.ui.getString("请输入新式菜品名称：");
			double fprice=this.ui.getDouble("请设置新式菜品价格：");
			int sellnum=0;
			int fstatus=1;
			int typeid=this.ui.getInt("请输入新式菜品类型编号：");
			List<Food> types = this.ts.findAllFoodTypes();
			for (Food food : types) {
				if (food.getTypeid()==typeid) {
					String s=this.ts.addFood(fid, fname, fprice, sellnum, fstatus, typeid);
					System.out.println(s);
					flag2=true;
					break;
				}
			}
			if (flag2==false) {
				System.out.println("菜品类型不存在！");
			}	
		}		
	}
	//查询所有菜品
	private void selectFood() {
		List<Food> foods=this.ts.findAllFoods();
		System.out.println("菜品编号\t菜品名称\t价格\t销量\t类型\t状态(0下架/1上架)");
		for (Food food : foods) {
			System.out.println(food);
		}			
	}	
	//修改菜品信息
	private void updateFood() {
		boolean flag=false;
		int fid=this.ui.getInt("请输入要修改的菜品编号(编号不能修改)：");
		Food food = this.ts.findFoodByid(fid);
		if (food.getFname()==null) {
			System.out.println("菜品不存在！");
		}else {
			String fname=this.ui.getString("请输入新的菜品名称：");
			double fprice=this.ui.getDouble("请输入新的价格：");
			int sellnum=this.ui.getInt("请输入新的销售数量：");
			int fstatus=this.ui.getInt("请输入菜品状态(0下架/1上架)：");
			int typeid=this.ui.getInt("请输入菜品类别：");
			List<Food> types = this.ts.findAllFoodTypes();
			for (Food f: types) {
				if (f.getTypeid()==typeid) {
					String s=this.ts.updateFood(fid, fname, fprice, sellnum, fstatus, typeid);
					System.out.println(s);
					flag=true;
					break;
				}
			}
			if (flag==false) {
				System.out.println("菜品类型不存在！");
			}	
		}	
	}

	//菜品下架
	private void deleteFood() {
		int fid=this.ui.getInt("请输入要下架的菜品编号：");	
		Food food = this.ts.findFoodByid(fid);
		if (food.getFname()==null) {
			System.out.println("菜品不存在！");
		}else if (food.getFstatus()==0) {
			System.out.println("菜品已下架！");
		}else {
			String s=this.ts.updatestatus(fid, 0);
			System.out.println(s);
		}			
	}
	//经理进入员工管理后的菜单
	private void empOpWelcome() {
		while(true) {
			this.v.empOp();
			int select=this.ui.getInt("请选择：");
			if (select==0) {
				this.v.println("退出");
				break;
			}else if (select==1) {
				//查看员工信息
				findEmp();
			}else if (select==2) {
				//修改员工信息
				this.updateEmp();
			}else if (select==3) {
				//员工辞退
				this.deleteEmp();
			}
		}
	}

	//查看员工信息
	private void findEmp() {
		System.out.println(mgr.getMid());
		int mid=mgr.getMid();
		List<Employee> emps = this.ts.findEmployeesByid(mid);
		System.out.println("员工编号 \t员工账号\t员工密码\t员工姓名\t员工上级编号");
		for (Employee e : emps) {
			System.out.println(e);
		}
	}

	//修改员工信息
	private void updateEmp() {
		int eid=this.ui.getInt("请输入要修改的员工编号(编号不可修改)：");
		Employee e=this.ts.findEmployeeByid(eid);
		if (e.getEname()==null) {
			System.out.println("此员工不存在！");
		}else {
			String password=this.ui.getString("请输入新的密码：");
			String name=this.ui.getString("请输入新的姓名：");
			int mid=this.ui.getInt("请输入新的上级编号：");
			Manager mgr=this.ts.findMgrByid(mid);
			if (mgr.getMname()==null) {
				this.v.println("此经理不存在！");
			}else {
				String s = this.ts.updateEmployee(eid, password, name, mid);
				System.out.println(s);
			}
		}

	}
	//员工辞退
	private void deleteEmp() {
		int eid=this.ui.getInt("请输入要辞退的员工编号：");
		Employee e=this.ts.findEmployeeByid(eid);
		if (e.getEname()==null) {
			System.out.println("此员工不存在！");
		}else {
			String s = this.ts.delEmployee(eid);
			System.out.println(s);
		}		
	}

	//管理员注册	
	private void addMgr() {
		boolean flag=false;
		int id=this.ui.getInt("请输入管理员编号：");
		Manager mgr=this.ts.findMgrByid(id);
		if (mgr!=null) {
			this.v.println("管理员已存在！");
		}else {
			String account=this.ui.getString("请输入账号：");
			List<Manager> list=this.ts.findAllMgr();
			for (Manager m : list) {
				if (account.equals(m.getMaccount())) {
					this.v.println("此账号已被注册过！");
					flag=true;
					break;
				}
			}
			if (flag==false) {
				String password=this.ui.getString("请输入密码：");
				String name=this.ui.getString("请输入姓名：");
				try {
					System.out.println("请将摄像头对准脸部：");
					this.camera=new Camera();
					camera.face("mgr/"+account);
					File f=new File("mgr/"+account+".jpg");
					FileInputStream fis=new FileInputStream(f);		
					byte[] b=new byte[fis.available()];
					int n;
					while(((n = fis.read(b)) != -1)) {
						fis.read(b);
					}
					fis.close();
					if (f.exists()) {
						f.delete();
					}
					String s=this.ts.mregist(id, account, password, name,b);
					this.v.println(s);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}				
	}

	//管理员登录
	Manager mgr;
	private void mlogin() {
		loop:while(true) {
			this.v.println("\t1、账号密码登录");
			this.v.println("\t2、人脸识别登录");
			int select=this.ui.getInt("请选择登录方式：");
			if (select==1) {
				while(true) {
					String account=this.ui.getString("请输入账号");
					String password=this.ui.getString("请输入密码");
					mgr=this.ts.mlogin(account, password);
					if (mgr!=null) {
						this.v.println("欢迎管理员"+mgr.getMname()+"登录系统");	
						break loop;
					}
					this.v.println("用户名或密码错误，登录失败！");
				}
			}else if(select==2) {
				while(true) {
					String account=this.ui.getString("请输入账号");
					try {
						System.out.println("请将摄像头对准脸部：");
						this.camera=new Camera();
						camera.face("mgr/"+account+"login");
						File f=new File("mgr/"+account+"login.jpg");
						FileInputStream fis=new FileInputStream(f);
						byte[] b=new byte[fis.available()];
						int n;
						while(((n = fis.read(b)) != -1)) {
							fis.read(b);
						}
						fis.close();
						if (f.exists()) {
							f.delete();
						}
						mgr=this.ts.mlogin(account,b);
						if (mgr!=null) {
							this.v.println("欢迎管理员"+mgr.getMname()+"登录系统");	
							break loop;						
						}
						this.v.println("登录失败！");
					}	
					catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		}
	}
	//员工注册
	private void addEmp() {
		boolean flag=false;
		int id=this.ui.getInt("请输入员工编号：");
		Employee emp=this.ts.findEmployeeByid(id);
		if (emp!=null) {
			this.v.println("员工已存在！");
		}else {
			String account=this.ui.getString("请输入账号：");
			List<Employee> list=this.ts.findAllEmp();
			for (Employee e : list) {
				if (account.equals(e.getEaccount())) {
					this.v.println("此账号已被注册过！");
					flag=true;
					break;
				}
			}
			if (flag==false) {
				String password=this.ui.getString("请输入密码：");
				String name=this.ui.getString("请输入姓名：");
				int mid=this.ui.getInt("请输入经理编号：");
				Manager mgr=this.ts.findMgrByid(mid);
				if (mgr.getMname()==null) {
					this.v.println("此经理不存在！");
				}else {
					int dinid=this.ui.getInt("请输入所属餐厅编号：");
					Dining dining=this.ts.FindDinrbyid(dinid);
					if (dining.getDinname()==null) {
						this.v.println("此餐厅不存在！");
					}else {
						System.out.println("请将摄像头对准脸部：");
						this.camera=new Camera();				
						try {
							camera.face("emp/"+account);
							File f=new File("emp/"+account+".jpg");
							FileInputStream fis=new FileInputStream(f);
							byte[] b=new byte[fis.available()];
							int n;
							while(((n = fis.read(b)) != -1)) {
								fis.read(b);
							}
							fis.close();
							if (f.exists()) {
								f.delete();
							}
							String s=this.ts.eregist(id, account, password, name, mid, dinid,b);
							this.v.println(s);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}				
				}
			}
		}				
	}

	//员工登录
	Employee emp;
	private void elogin() {
		loop:while(true) {
			this.v.println("\t1、账号密码登录");
			this.v.println("\t2、人脸识别登录");
			int select=this.ui.getInt("请选择登录方式：");
			if (select==1) {
				while(true) {
					String account=this.ui.getString("请输入账号");
					String password=this.ui.getString("请输入密码");
					emp=this.ts.elogin(account, password);
					if (emp!=null) {
						this.v.println("欢迎员工"+emp.getEname()+"登录系统");
						break loop;
					}
					this.v.println("用户名或密码错误，登录失败！");
				}
			}else if(select==2) {
				while(true) {
					String account=this.ui.getString("请输入账号");
					System.out.println("请将摄像头对准脸部：");
					this.camera=new Camera();
					try {
						camera.face("emp/"+account+"login");
						File f=new File("emp/"+account+"login.jpg");
						FileInputStream fis=new FileInputStream(f);
						byte[] b=new byte[fis.available()];
						int n;
						while(((n = fis.read(b)) != -1)) {
							fis.read(b);
						}
						if (f.exists()) {
							f.delete();
						}
						fis.close();

						emp=this.ts.elogin(account,b);					
						if (emp!=null) {
							this.v.println("欢迎员工"+emp.getEname()+"登录系统");
							break loop;						
						}

						this.v.println("登录失败！");
					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
					
				}	
			}
		}		
	}
}

package mcd.Contorl;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


import mcd.service.TotalService;
import mcd.serviceimp.TotalServiceimp;

public class Contorl {private ServerSocket server;
public static final int PORT=10086;
List<Theard> list;
//private Watch watch;
private ExecutorService es;
private TotalService ts;
public Contorl() {	

	try {
		System.out.println("服务器正在开启。。。");
		this.server=new ServerSocket(PORT);
		Thread.sleep(1000);
		System.out.println("服务器已开启");	

		es=Executors.newCachedThreadPool();
		this.ts=new TotalServiceimp();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

} 

public void start() {
	while(true) {
		try {
			Socket client=this.server.accept();
			System.out.println("用户"+client.getInetAddress().getHostAddress());
			Theard ct=new Theard(client,ts);
			es.submit(ct);
			int activeCount=((ThreadPoolExecutor)es).getActiveCount();
			System.out.println("在线人数"+activeCount);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

}

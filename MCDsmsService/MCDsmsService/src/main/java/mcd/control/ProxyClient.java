package mcd.control;

import java.lang.reflect.Proxy;

//通过网络连接服务器端获取连接对象
public class ProxyClient {
	//1.当前对象的类加载器 2.客户端中介 3.实际代理接口
	public static<T> T getClient(Class<T> clazz,String ip,int port) {
		return (T)Proxy.newProxyInstance(ProxyClient.class.getClassLoader(), new Class[] {clazz}, new ClientHandler(ip,port));
	}
}

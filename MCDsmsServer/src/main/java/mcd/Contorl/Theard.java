package mcd.Contorl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class Theard  implements Runnable{
	private Socket client;
	private Object o;
	public Theard(Socket client,Object o) {
		super();
		this.client = client;
		this.o=o;
	}
	public void run() {
	
			try {
				ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
				//方法名称
				String methodName=ois.readUTF();
				//方法的参数列表 类
				Class<?>[] methodType=(Class<?>[]) ois.readObject();
				//方法的参数列表  参数名称
				Object[] methodParams=(Object[]) ois.readObject();
				//获取方法对象
				Method method=o.getClass().getMethod(methodName, methodType);
				//执行方法并返回结果     
				Object result=method.invoke(o, methodParams);
				//输出流
				ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(result);
				
				oos.flush();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
			}catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	
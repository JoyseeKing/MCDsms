package mcd.util;

import java.util.Scanner;

public class UserInput {
	public String getString(String msg) {
		while(true) {
			try {
				System.out.println(msg);
				Scanner scanner=new Scanner(System.in);
				return scanner.next();
			} catch (Exception e) {
				System.out.println("输入格式不正确");
			}
		}
	}
	public int getInt(String msg) {
		while(true) {
			try {
				System.out.println(msg);
				Scanner scanner=new Scanner(System.in);
				return scanner.nextInt();
			} catch (Exception e) {
				System.out.println("输入格式不正确");
			}
		}
	}
	public double getDouble(String msg) {
		while(true) {
			try {
				System.out.println(msg);
				Scanner scanner=new Scanner(System.in);
				return scanner.nextDouble();
			} catch (Exception e) {
				System.out.println("输入格式不正确");
			}
		}
	}
}

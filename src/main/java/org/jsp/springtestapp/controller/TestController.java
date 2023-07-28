package org.jsp.springtestapp.controller;

import java.util.Scanner;

import org.jsp.springtestapp.UserConfig;
import org.jsp.springtestapp.dao.ProductDao;
import org.jsp.springtestapp.dao.UserDao;
import org.jsp.springtestapp.dto.Product;
import org.jsp.springtestapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestController {
	@Autowired
static UserDao uDao;
	@Autowired
	static ProductDao pDao;
	static {
		ApplicationContext context=new AnnotationConfigApplicationContext("org.jsp.springtestapp");
		uDao=context.getBean(UserDao.class);
		pDao=context.getBean(ProductDao.class);
	}
static Scanner sc=new Scanner(System.in);
	public static void main(String[] args) {
		System.out.println("1:Register user");
		System.out.println("2:Update User");
		System.out.println("3:add products");
		System.out.println("4:find products by id");
		System.out.println("5:find products by category");
		System.out.println("6:varify by email and password");
		System.out.println("7:varify by phone and password");
		int choice=sc.nextInt();
		switch (choice) {
		case 1:save();
		break;
		case 2:update();
		break;
		case 3:add();
		break;
		case 4: findid();
		break;
		case 5: findcat();
		break;
		case 6: varifyUserByEmailandPassword();
		break;
		case 7: varifyUserByPhoneandPassword();
		break;
		}
		
	}

public static void add() {
	System.out.println("add");
	int user=sc.nextInt();
	
		System.out.println("add products name,brand, description,category,price");
		String name=sc.next();
		String brand=sc.next();
		String description=sc.next();
		String category=sc.next();
		double price=sc.nextDouble();
		Product p=new Product();
		p.setName(name);
		p.setBrand(brand);
		p.setCategory(category);
		p.setDescription(description);
		p.setPrice(price);
		p=pDao.addProduct(p, user);
	}
	public static void save() {
     System.out.println("ENTER UR NAME EMAIL PHONE AND PASSWOTRD");
     String name=sc.next();
     String email=sc.next();
     long phone=sc.nextLong();
     String password=sc.next();
     User u=new User();
     u.setName(name);
     u.setEmail(email);
     u.setPhone(phone);
     u.setPassword(password);
     u=uDao.saveUser(u);
	}
	public static void update() {
		System.out.println("enter ur id to update"); 
		int id=sc.nextInt();
	     System.out.println("ENTER UR NAME  EMAIL PHONE AND PASSWOTRD");
	     String name=sc.next();
	     String email=sc.next();
	     long phone=sc.nextLong();
	     String password=sc.next();
	     User u=new User();
	     u.setName(name);
	     u.setEmail(email);
	     u.setPhone(phone);
	     u.setPassword(password);
	     u=uDao.updateUser(u);
		}
	public static void varifyUserByEmailandPassword() {
		System.out.println("Enter email  and phone to varify");
		String email=sc.next();
		String password=sc.next();
		User u=uDao.varifyEamilAndPassword(email, password);
		System.out.println(u);
	}
	public static void findid() {
	System.out.println("enter ur id to find");	
	int id =sc.nextInt();
	Product p=pDao.findProduct(id);
	}
	public static void findcat() {
		System.out.println("enter ur cat to find");
		String category=sc.next();
		Product p=pDao.findCategory(category);
	}
	public static void varifyUserByPhoneandPassword() {
		System.out.println("Enter email  and phone to varify");
		String phone=sc.next();
		String password=sc.next();
		User u=uDao.varifyEamilAndPassword(phone, password);
		System.out.println(u);
	}
}


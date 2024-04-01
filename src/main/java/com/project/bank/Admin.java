package com.project.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Admin {
	static int count = 0;
	public static boolean adminLogin() throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Username:");
		String user = s.next();
		System.out.println("Enter Password:");
		String pswd = s.next();
		Connection con =  Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from Admin where email = ? and password = ?");
		ps.setString(1, user);
		ps.setString(2, pswd);
		ResultSet rs = ps.executeQuery();
		return rs.next();
	}

	public static void addAccount() throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Name:");
		String name = s.next();
		System.out.println("Enter Phone No:");
		long phone = s.nextLong();
		System.out.println("Enter First Default Balance (min.5000):");
		double bal = s.nextDouble();
		System.out.println("Enter new Pin:");
		int pin = s.nextInt();
		Connection con = Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into Account value(?,?,?,?,?,?)");
		ps.setInt(1, ++count);
		ps.setLong(2, getAccount());
		ps.setInt(3, pin);
		ps.setString(4, name);
		ps.setDouble(5, bal);
		ps.setLong(6, phone);
		
		ps.execute();
		System.out.println("Account Created");
	}

	private static long getAccount() throws Exception {
		String s = "";
		for(int i = 0;i<12;i++) {
			s += (int)(Math.random()*9)+1;
		}
		long l = Long.parseLong(s);
		Connection con = Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from Account where account_no =?");
		ps.setLong(1, l);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) return getAccount();
		return l;
		
	}

	public static void viewAccount() throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Account Number:");
		long acc = s.nextLong();
		Connection con = Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from Account where account_no =?");
		ps.setLong(1, acc);
		ResultSet r = ps.executeQuery();
		if (r.next()) {
			System.out.println("Account Number : " + r.getLong(2));
			System.out.println("Name : " + r.getString(4));
			System.out.println("Phone Number : " + r.getLong(6));
			System.out.println("Balance : " + r.getDouble(5));
		}
	}

	public static void viewAllAccount() throws Exception {
		Connection con = Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from Account");
		ResultSet r = ps.executeQuery();
		while (r.next()) {
			System.out.println("Id : " + r.getInt(1));
			System.out.println("***************");
			System.out.println("Account Number : " + r.getLong(2));
			System.out.println("Name : " + r.getString(4));
			System.out.println("Phone Number : " + r.getLong(6));
			System.out.println("Balance : " + r.getDouble(5));
			System.out.println();
		}
	}

	public static void doTransaction() {
		System.out.println("do transaction...");
	}

}

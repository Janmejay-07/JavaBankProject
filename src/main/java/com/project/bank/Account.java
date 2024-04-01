package com.project.bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Account {
	public static long acc;

	public static boolean userLogin() throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Account Number :");
		acc = s.nextLong();
		System.out.println("Enter Pin:");
		int pin = s.nextInt();
		Connection con = Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from Account where account_no = ? and pincode = ?");
		ps.setLong(1, acc);
		ps.setInt(2, pin);
		ResultSet rs = ps.executeQuery();
		
		return rs.next();
		
	}

	public static void depositMoney() throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter Deposit Amount :");
		double amount = s.nextDouble();
		Connection con = Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("select balance from Account where account_no = ?");
		ps.setLong(1, acc);
		ResultSet r = ps.executeQuery();
		r.next();
		double availBal = r.getDouble(1);
		PreparedStatement p = con.prepareStatement("update Account set balance = ? where account_no =?");
		p.setDouble(1, availBal + amount);
		p.setLong(2, acc);
		p.execute();
		System.out.println("Money Deposited");
		
	}

	public static void sendMoney() throws Exception {
		Scanner s = new Scanner(System.in);

		System.out.println("Enter Receivers Account Number :");
		long recAcc = s.nextLong();
		System.out.println("Enter Amount to be Sent :");
		double amount = s.nextDouble();

		// senders balance check % send
		Connection con = Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("select balance from Account where account_no = ?");
		ps.setLong(1, acc);

		ResultSet r = ps.executeQuery();
		r.next();
		double availBal = r.getDouble(1);
		if (availBal >= amount) {
			PreparedStatement p = con.prepareStatement("update Account set balance = ? where account_no =?");
			p.setDouble(1, availBal - amount);
			p.setLong(2, acc);
			p.execute();
			System.out.println("Money Sent");
		} else
			System.out.println("Insufficient Balance");

		PreparedStatement ps2 = con.prepareStatement("select balance from Account where account_no = ?");
		ps2.setLong(1, recAcc);

		ResultSet r2 = ps2.executeQuery();
		r2.next();
		double availBal2 = r2.getDouble(1);

		PreparedStatement p = con.prepareStatement("update Account set balance = ? where account_no =?");
		p.setDouble(1, availBal2 + amount);
		p.setLong(2, recAcc);
		p.execute();
		
	}

	public static void withdrawMoney() throws Exception {
		Scanner s = new Scanner(System.in);

		System.out.println("Enter Withdraw Amount :");
		double amount = s.nextDouble();

		Connection con = Helper.getConnection();
		PreparedStatement ps = con.prepareStatement("select balance from Account where account_no = ?");
		ps.setLong(1, acc);

		ResultSet r = ps.executeQuery();
		r.next();
		double availBal = r.getDouble(1);
		if (availBal >= amount) {
			PreparedStatement p = con.prepareStatement("update Account set balance = ? where account_no =?");
			p.setDouble(1, availBal - amount);
			p.setLong(2, acc);
			p.execute();
			System.out.println("Money Withdrawn");
		} else
			System.out.println("Insufficient Balance");
		
	}

	public static void updateAcc() throws Exception {
		Scanner s = new Scanner(System.in);
		System.out.println("\nUpdate Profile:- \n------------------");
		System.out.println(" 1.Update Phone Number \n 2.Update PinCode");
		int a = s.nextInt();
		Connection con = Helper.getConnection();
		switch (a) {
		case 1: {
			System.out.println("\nEnter New Phone Number:");
			long phone = s.nextLong();
			PreparedStatement ps = con.prepareStatement("update Account set phone_no = ? where account_no = ?");
			ps.setLong(1, phone);
			ps.setLong(2, acc);
			ps.execute();
			System.out.println(" Phone Number Updated");
			break;
		}
		case 2: {
			System.out.println("\nEnter New PinCode :");
			int pin = s.nextInt();
			PreparedStatement ps = con.prepareStatement("update Account set pincode = ? where account_no = ?");
			ps.setInt(1, pin);
			ps.setLong(2, acc);
			ps.execute();
			System.out.println(" PinCode Updated");
			break;
		}
		}
		
	}

}


package com.project.bank;

import java.util.Scanner;

public class BankDriver {
	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);

		System.out.println("Welcome to JSP Bank \n\nEnter Your Prefrence\n 1.Admin Login\n 2.Account Login");
		int a = s.nextInt();
		switch (a) {
		case 1: {
			boolean res = Admin.adminLogin();
			if (res) {
				while (res) {
					System.out.println(
							"\nActions:  \n----------\n 1.Add Account \n 2.View Account By Account Number \n 3.View All Accounts \n 4.Perform Transaction \n 5.Logout");
					int b = s.nextInt();
					switch (b) {
					case 1: {
						Admin.addAccount();
						break;
					}
					case 2: {
						Admin.viewAccount();
						break;
					}
					case 3: {
						Admin.viewAllAccount();
						break;
					}
					case 4: {
						Admin.doTransaction();
						break;
					}
					case 5: {
						res = false;
						break;
					}
					default: {
						System.out.println("Wrong Option Retry..");
					}
					}
				}
			} else {
				System.out.println("Wrong Credentials");
			}
			break;
		}
		case 2: {
			boolean res = Account.userLogin();
			if (res) {
				while (res) {
					System.out.println(
							"\nActions: \n---------- \n 1.Deposit Money \n 2.Send Money \n 3.Withdraw Money \n 4.Update Profile \n 5.Logout");
					int c = s.nextInt();
					switch (c) {
					case 1: {
						Account.depositMoney();
						break;
					}
					case 2: {
						Account.sendMoney();
						break;
					}
					case 3: {
						Account.withdrawMoney();
						break;
					}
					case 4: {
						Account.updateAcc();
						break;
					}
					case 5: {
						res = false;
						break;
					}
					default: {
						System.out.println("Wrong Choice Retry..");
					}
					}
				}
			} else {
				System.out.println("Invalid Credentials");
			}
		}
		}

	}
}

package onlineBanking;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static int randomBankID(int num) {
		return (int) Math.pow(10, num) + (int) ((Math.random() * Math.pow(10, num)) + 0.5);
	}
	
	private static int letterToNumber(String str) {
		int num = 0;
		if (str.compareTo("!") < 58) {
			num += 65; //uppercase
		} else {
			num += 97; //lowercase
		}
		
		str = str.toLowerCase();
		
		if (str.equals("a")) {
			num += 0;
		} else if (str.equals("b")) {
			num += 1;
		} else if (str.equals("c")) {
			num += 2;
		} else if (str.equals("d")) {
			num += 3;
		} else if (str.equals("e")) {
			num += 4;
		} else if (str.equals("f")) {
			num += 5;
		} else if (str.equals("g")) {
			num += 6;
		} else if (str.equals("h")) {
			num += 7;
		} else if (str.equals("i")) {
			num += 8;
		} else if (str.equals("j")) {
			num += 9;
		} else if (str.equals("k")) {
			num += 10;
		} else if (str.equals("l")) {
			num += 11;
		} else if (str.equals("m")) {
			num += 12;
		} else if (str.equals("n")) {
			num += 13;
		} else if (str.equals("o")) {
			num += 14;
		} else if (str.equals("p")) {
			num += 15;
		} else if (str.equals("q")) {
			num += 16;
		} else if (str.equals("r")) {
			num += 17;
		} else if (str.equals("s")) {
			num += 18;
		} else if (str.equals("t")) {
			num += 19;
		} else if (str.equals("u")) {
			num += 20;
		} else if (str.equals("v")) {
			num += 21;
		} else if (str.equals("w")) {
			num += 22;
		} else if (str.equals("x")) {
			num += 23;
		} else if (str.equals("y")) {
			num += 24;
		} else if (str.equals("z")) {
			num += 25;
		}
		return num;
	}
	
	private static String convertToBinary(int num) {
		
		String newNum = "";
		int current = 128;
		
		//2 bytes, so numbers >256 and <0 are rejected
		if (num > 256 || num < 0) { return "ERROR"; }
		
		while (current != 0) { //128 64 32 16 8 4 2 1
			
			if (num - current >= 0) { newNum += "1"; num -= current; } else { newNum += "0"; } 
			
			current /= 2;
			
		}
		
		return newNum;
		
	}
	
	public static String hashPassword(String password) {
		
		String hashed = "";
		boolean separated = false; //determines whether or not to add a : or ;
		
		for (int a = 0; a < password.length(); a++) {
			try { //if the selected character is a number, convert to binary and add a : for ASCII, else add a ; for decimal
			    int detect = Integer.parseInt(password.substring(a, a + 1));
			} catch (NumberFormatException e) {
				hashed += convertToBinary(letterToNumber(password.substring(a, a + 1))) + ":";
			    separated = true;
			}
			if (!separated) {
				hashed += convertToBinary(Integer.parseInt(password.substring(a, a + 1))) + ";";
			}
			separated = false;
		}
		
		for (int a = 0; a < hashed.length(); a++) { //swap 0's and 1's
			if (hashed.substring(a, a + 1).equals("1")){
				hashed = hashed.substring(0, a) + "0" + hashed.substring(a + 1);
			} else if (hashed.substring(a, a + 1).equals("0")){
				hashed = hashed.substring(0, a) + "1" + hashed.substring(a + 1);
			}
		}
		
		return hashed;
		
	}

	public static String systemMessage(int num) { //allows for system to easily access system messages
		
		if (num == 1)
			return "That was not a valid command.\n";
		
		if (num == 2)
			return "Seems like your username or password was incorrect. You will now be sent to the home page.\n";
		
		if (num == 3)
			return "Please type in the amount you'd like to withdraw.";
		
		if (num == 4)
			return "Please type in the amount you'd like to deposit.";
		
		if (num == 5)
			return "Please type in a numerical value.";
		
		return "ERROR";
	}
	
	public static boolean checkUsernames(ArrayList<userInfo> list, String user) {
		
		for (int a = 0; a < list.size(); a++)
			if (user.equals(list.get(a).getUsername()))
				return true;
		return false;
		
	}
	
	public static boolean checkPasswords(ArrayList<userInfo> list, String pass) {
		
		for (int a = 0; a < list.size(); a++)
			if (hashPassword(pass).equals(list.get(a).getPassword()))
				return true;
		return false;
		
	}
	
	public static ArrayList<userInfo> sort(ArrayList<userInfo> list) {
		
		for (int a = 0; a < list.size(); a++) {
			
			int min = Integer.MAX_VALUE, change = 0;
			
			//sets the lowest value integer to min
			for (int b = a; b < list.size(); b++)
				if (list.get(b).getBankID() < min) {
					min = list.get(b).getBankID();
					change = b;
				}
			
			//swap
			userInfo temp = list.get(change);
			list.set(change, list.get(a));
			list.set(a, temp);
			
		}
		
		return list;
	}

	public static int binarySearch(ArrayList<userInfo> list, int target, int min, int max) {
		
		int mid = (min + max) / 2;
		
		if (mid == max && mid == min)
			return -1;
		
		if (target == list.get(mid).getBankID())
			return mid;
		else if (target < list.get(mid).getBankID())
			return binarySearch(list, target, min, mid);
		else if (target > list.get(mid).getBankID())
			return binarySearch(list, target, mid, max);
		return -1;
		
	}

	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
		
		ArrayList<userInfo> bankClients = new ArrayList<userInfo>();
		
		//preregistered users to allow for admin functionality demonstration
		//username, password, bank ID#, checking balance, savings balance
		bankClients.add(new userInfo("aprilFoods", hashPassword("Pokemon24"), randomBankID(5), 543.87, 593.34)); bankClients.add(new userInfo("rockyRoads", hashPassword("Sculpter513"), randomBankID(5), 8654.09, 10468.97)); bankClients.add(new userInfo("MargaretHam75", hashPassword("1L0V3C0D1N6"), randomBankID(5), 27.98, 159.06)); bankClients.add(new userInfo("Username1", hashPassword("Password1"), randomBankID(5), -21.89, 0.00)); bankClients.add(new userInfo("j3ffb3z0s", hashPassword("t43b35t4M4z0NC30"), randomBankID(5), 50274.07, 100000.07)); bankClients.add(new userInfo("spaceholder", hashPassword("sp4c3h01d3r"), 200000, 0, 0));
		
		//loops until the user enters the log out command
		while (1 == 1) {
					
			//begins the login/registration process
			Scanner readCommand = new Scanner(System.in);
			System.out.println("Welcome to the Bank of AP. Please type in the corresponding number to what you'd like to do. \n1. Register an account\n2. Log in\n3. Close the program");
			String userCommand = readCommand.nextLine();
			boolean loggingOut = false;
			
			if (userCommand.equals("1")) { //registering an account
			
				userInfo newUser = new userInfo();
			
				//set username
				Scanner registerUser = new Scanner(System.in);
				System.out.println("Enter a username.");
				newUser.setUsername(registerUser.nextLine());
			
				//set password
				registerUser = new Scanner(System.in);
				System.out.println("Enter a password.");
				newUser.setPassword(hashPassword(registerUser.nextLine()));
			
				//set unique 6 digit bank ID # and balances
				newUser.setBankID(randomBankID(5));
				newUser.setCheckingBal(0);
				newUser.setSavingsBal(0);
			
				//sends to main menu
				System.out.println("Account creation successful. You will now be sent to the main menu.\n");
				
				bankClients.add(newUser);
			
			} else if (userCommand.equals("2")) { //log in
				
				//takes in username as an input
				System.out.println("Please enter your username.");
				userCommand = readCommand.nextLine();
				
				//log in for users
				for (int a = 0; a < bankClients.size(); a++) {
					//checks for correct username
					if (userCommand.equals(bankClients.get(a).getUsername())) {
						
						//sets the temp variable as the selected user to easily modify values
						userInfo currentUser = bankClients.get(a);
						//checks for correct password
						System.out.println("Please enter your password.");
						userCommand = readCommand.nextLine();
						
						if (hashPassword(userCommand).equals(currentUser.getPassword())) {
							
							System.out.println("Log in successful. What would you like to do today?");
							
							while (1 == 1) {
							
								System.out.println("Please type in the corresponding number to what you'd like to do. \n1. Manage Checking Account\n2. Manage Savings Account\n3. Change Username\n4. Change Password\n5. View Bank ID#\n6. Log Out");
							
								//reads command
								userCommand = readCommand.nextLine();
							
								//available commands for the user
								if (userCommand.equals("1")) { //manage checking
									
									System.out.println("Your current balance is : $" + currentUser.getCheckingBal() + ". What would you like to do today?\n1. Deposit Funds\n2. Withdraw Funds\n3. Return to menu");
								
									//reads command
									userCommand = readCommand.nextLine();
								
									if (userCommand.equals("1")) { //deposit funds
									
										System.out.println(systemMessage(4));
										
										while (1 == 1) {
											
											try {
												userCommand = readCommand.nextLine();
												currentUser.setCheckingBal(currentUser.getCheckingBal() + Double.parseDouble(userCommand));
												break;
											} catch(Exception e) {
												System.out.println(systemMessage(5));
											}
										
										}
									
										System.out.println("Your new balance is $" + currentUser.getCheckingBal());
									
									} else if (userCommand.equals("2")) { //withdraw funds
									
										System.out.println(systemMessage(3));
										
										while (1 == 1) {
											
											try {
												userCommand = readCommand.nextLine();
												currentUser.setCheckingBal(currentUser.getCheckingBal() - Double.parseDouble(userCommand));
												break;
											} catch(Exception e) {
												System.out.println(systemMessage(5));
											}
										
										}
									
										System.out.println("Your new balance is $" + currentUser.getCheckingBal());
									
									} else if (userCommand.equals("3")) { //return
									
										System.out.println("\n"); //spacing
									
									} else { systemMessage(1); } //if user inputs an invalid command, user is sent back
								
								} else if (userCommand.equals("2")) { //manage savings
								
									System.out.println("Your current balance is : $" + currentUser.getSavingsBal() + ". What would you like to do today?\n1. Deposit Funds\n2. Withdraw Funds\n3. Return to menu");
									
									//reads command
									userCommand = readCommand.nextLine();
								
									if (userCommand.equals("1")) { //deposit funds
									
										System.out.println(systemMessage(4));
										
										while (1 == 1) {
											
											try {
												userCommand = readCommand.nextLine();
												currentUser.setSavingsBal(currentUser.getSavingsBal() + Double.parseDouble(userCommand));
												break;
											} catch(Exception e) {
												System.out.println(systemMessage(5));
											}
										
										}
									
										System.out.println("Your new balance is $" + currentUser.getSavingsBal());
									
									} else if (userCommand.equals("2")) { //withdraw funds
									
										System.out.println(systemMessage(3));
										
										while (1 == 1) {
										
											try {
												userCommand = readCommand.nextLine();
												currentUser.setSavingsBal(currentUser.getSavingsBal() - Double.parseDouble(userCommand));
												break;
											} catch(Exception e) {
												System.out.println(systemMessage(5));
											}
										
										}
										
										System.out.println("Your new balance is $" + currentUser.getSavingsBal());
									
									} else if (userCommand.equals("3")) { //return
									
										System.out.println("\n"); //spacing
									
									} else { systemMessage(1); } //if user inputs an invalid command, user is sent back
									
								} else if (userCommand.equals("3")) { //change username
								
									System.out.println("Please enter your current username.");
									
									userCommand = readCommand.nextLine();
									
									if (userCommand.equals(currentUser.getUsername())) {
										
										while (1 == 1) {
											
											System.out.println("Please enter a new username.");
											
											userCommand = readCommand.nextLine();
										
											if (!checkUsernames(bankClients, userCommand)) {
											
												currentUser.setUsername(userCommand);
												System.out.println("Your username has been successfully changed.");
												break;
												
											} else {
											
												System.out.println("The username you entered is unavailable, please enter a new username.");
											
											}
										
										}
										
									} else {
										
										System.out.println(systemMessage(2)); //incorrect username
										
									}
									
								} else if (userCommand.equals("4")) { //change password
								
									System.out.println("Please enter your current password.");
									
									userCommand = readCommand.nextLine();
									
									if (hashPassword(userCommand).equals(currentUser.getPassword())) {
										
										System.out.println("Please enter a new password.");
										
										userCommand = readCommand.nextLine();
										
										currentUser.setPassword(hashPassword(userCommand));
										
										System.out.println("Your password has been successfully changed.");
										
									} else {
										
										System.out.println(systemMessage(2)); //incorrect password
										
									}
									
								} else if (userCommand.equals("5")) { // view bank ID#
								
									System.out.println("Your unique 6 Digit Bank ID# is " + currentUser.getBankID());
									
								} else if (userCommand.equals("6")) { //log out
								
									System.out.println("Log out successful.\n");
									loggingOut = true;
									break;
								
								} else { System.out.println(systemMessage(1)); } //if user inputs an invalid command, user is sent back
							
							}
							
						} else { 
						
							if (a >= bankClients.size() - 1 && !loggingOut) {
								System.out.println(systemMessage(2)); //incorrect user/pass
								loggingOut = true;
							}
						}
					}
					
					if (a >= bankClients.size() - 1 && !loggingOut) {
						System.out.println(systemMessage(2)); //incorrect user/pass
						loggingOut = true;
					}
					
				}
				
			} else if (userCommand.equals("3")) { //close the program
				
				System.out.println("Thank you for choosing to bank with the Bank of AP.");
				System.exit(0);
				
			} else if (userCommand.equals("adminLogIn")) { //admin login
				
				System.out.println("\nWelcome, Admin. What would you like to do today?");
				
				bankClients = sort(bankClients);
				
				while (1 == 1) {
					
					System.out.println("1. View Clients\n2. Manage Clients\n3. Log Out");
					userCommand = readCommand.nextLine();
					
					if (userCommand.equals("1")) {
						
						for (int a = 0; a < bankClients.size(); a++)
							System.out.println(bankClients.get(a).toString());
						
						
					} else if (userCommand.equals("2")) {
						
						System.out.println("Please enter the Client's 6 Digit BankID#.");
						
						while (1 == 1) {
						
							userCommand = readCommand.nextLine();
							
							int search = binarySearch(bankClients, Integer.parseInt(userCommand), 0, bankClients.size() - 1);
						
							if (search != -1) {
								
								userInfo tempUser = bankClients.get(search);
								System.out.println("Successfully retrieved Client Information.\n");
								
								while (1 == 1) {
									
									System.out.println("Please type in the corresponding number to what you'd like to do. \n1. Manage Checking Account\n2. Manage Savings Account\n3. Change Username\n4. Change Password\n5. Log Out");
									
									userCommand = readCommand.nextLine();
									
									if (userCommand.equals("1")) {
										
										System.out.println("Your current balance is : $" + tempUser.getSavingsBal() + ". What would you like to do today?\n1. Deposit Funds\n2. Withdraw Funds\n3. Return to menu");
										
										//reads command
										userCommand = readCommand.nextLine();
									
										if (userCommand.equals("1")) { //deposit funds
										
											System.out.println(systemMessage(4));
											
											while (1 == 1) {
												
												try {
													userCommand = readCommand.nextLine();
													tempUser.setSavingsBal(tempUser.getSavingsBal() + Double.parseDouble(userCommand));
													break;
												} catch(Exception e) {
													System.out.println(systemMessage(5));
												}
											
											}
										
											System.out.println("Your new balance is $" + tempUser.getSavingsBal());
										
										} else if (userCommand.equals("2")) { //withdraw funds
										
											System.out.println(systemMessage(3));
											
											while (1 == 1) {
											
												try {
													userCommand = readCommand.nextLine();
													tempUser.setSavingsBal(tempUser.getSavingsBal() - Double.parseDouble(userCommand));
													break;
												} catch(Exception e) {
													System.out.println(systemMessage(5));
												}
											
											}
											
											System.out.println("Your new balance is $" + tempUser.getSavingsBal());
										
										} else if (userCommand.equals("3")) { //return
										
											System.out.println("\n"); //spacing
										
										} else { systemMessage(1); } //if user inputs an invalid command, user is sent back
										
									} else if (userCommand.equals("2")) {
										
										System.out.println("Your current balance is : $" + tempUser.getSavingsBal() + ". What would you like to do today?\n1. Deposit Funds\n2. Withdraw Funds\n3. Return to menu");
										
										//reads command
										userCommand = readCommand.nextLine();
									
										if (userCommand.equals("1")) { //deposit funds
										
											System.out.println(systemMessage(4));
											
											while (1 == 1) {
												
												try {
													userCommand = readCommand.nextLine();
													tempUser.setSavingsBal(tempUser.getSavingsBal() + Double.parseDouble(userCommand));
													break;
												} catch(Exception e) {
													System.out.println(systemMessage(5));
												}
											
											}
										
											System.out.println("Your new balance is $" + tempUser.getSavingsBal());
										
										} else if (userCommand.equals("2")) { //withdraw funds
										
											System.out.println(systemMessage(3));
											
											while (1 == 1) {
											
												try {
													userCommand = readCommand.nextLine();
													tempUser.setSavingsBal(tempUser.getSavingsBal() - Double.parseDouble(userCommand));
													break;
												} catch(Exception e) {
													System.out.println(systemMessage(5));
												}
											
											}
											
											System.out.println("Your new balance is $" + tempUser.getSavingsBal());
										
										} else if (userCommand.equals("3")) { //return
										
											System.out.println("\n"); //spacing
										
										} else { systemMessage(1); } //if user inputs an invalid command, user is sent back
										
									} else if (userCommand.equals("3")) {
										
										while (1 == 1) {
											
											System.out.println("Please enter a new username.");
											
											userCommand = readCommand.nextLine();
										
											if (!checkUsernames(bankClients, userCommand)) {
											
												tempUser.setUsername(userCommand);
												System.out.println("Your username has been successfully changed.");
												break;
												
											} else {
											
												System.out.println("The username you entered is unavailable, please enter a new username.");
											
											}
										
										}
										
									} else if (userCommand.equals("4")) {
										
										System.out.println("Please enter a new password.");
										
										userCommand = readCommand.nextLine();
										
										tempUser.setPassword(hashPassword(userCommand));
										
										System.out.println("Your password has been successfully changed.");
										
									} else if (userCommand.equals("5")) {
										
										System.out.println("Log out successful.\n");
										loggingOut = true;
										break;
										
									} else {
										
										//if user inputs an invalid command, user is sent back
										System.out.println(systemMessage(1));
										
									}
									
								}
								
							} else {
							
								System.out.println("The BankID# you entered in not in our system. Please try again.");
							
							}
						
						}
						
					} else if (userCommand.equals("2")) {
						
						System.out.println("Log out successful.\n");
						loggingOut = true;
						break;
						
					} else {
						
						//if user inputs an invalid command, user is sent back
						System.out.println(systemMessage(1));
						
					}
					
				}
				
			} else {
				
				//if user inputs an invalid command, user is sent back
				System.out.println(systemMessage(1));
				
			}
			
		}
		
	}

}

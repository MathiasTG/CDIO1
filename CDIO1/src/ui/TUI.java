package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.UserDTO;
import exceptions.DALException;
import exceptions.DatabaseFullException;
import exceptions.EmptyStoreException;
import exceptions.InvalidCPRException;
import exceptions.InvalidIDException;
import exceptions.InvalidINIException;
import exceptions.InvalidPasswordException;
import exceptions.InvalidUserNameException;
import exceptions.NoRoleException;
import exceptions.UserNotFoundException;
import logic.ILogic;

public class TUI {
	Scanner input = new Scanner(System.in);
	private ILogic f;

	public TUI(ILogic f) {
		this.f = f;
	}

	public void menu() {
		System.out.println("Welcome.");
		while (true) {
			System.out.println("Choose an action:");
			System.out.println("1.\tCreate new user.\n" + "2.\tList users.\n" + "3.\tUpdate user.\n"
					+ "4.\tDelete user.\n" + "5.\tTerminate program.\n");
			int re = input.nextInt();
			input.nextLine();
			switch (re) {
			case 1:
				createUser();
				break;
			case 2:
				showAllUsers();
				break;
			case 3:
				updateUser();
				break;
			case 4:
				deleteUser();
				break;
			case 5:
				input.close();
				System.exit(1);
				break;
			default:
				System.out.println("Invalid input. Enter 1-5.");
			}
		}
	}

	public void createUser() {
		String userName, initials, cpr;
		int userID;

		List<String> roles = new ArrayList<String>();
		List<String> choices = new ArrayList<String>();
		choices.add("1.\tAdmin");
		choices.add("2.\tPharmacist");
		choices.add("3.\tForeman");
		choices.add("4.\tOperator");
		choices.add("5.\tRole selection done");
		
		while(true){
			System.out.println("Enter the ID of the new user, as an integer between 11 and 99:");
			userID = input.nextInt();
			input.nextLine();
			if(userID>=11&&userID<=99)
				break;
			else
				System.out.println(userID+" is not a valid ID.");
		}
		System.out.println("Enter the username of the new user:");
		userName = input.nextLine();
		System.out.println("Enter the initials of the new user:");
		initials = input.nextLine();
		System.out.println("Enter the CPR number of the new user (123456-7890): ");
		cpr = input.nextLine();
		System.out.println("Enter the roles of the new user:");

		while (true) {

			for (int i = 0; i < roles.size(); i++) {
				if (roles.get(i) == "Admin")
					choices.remove("1.\tAdmin");
				else if (roles.get(i) == "Pharmacist")
					choices.remove("2.\tPharmacist");
				else if (roles.get(i) == "Foreman")
					choices.remove("3.\tForeman");
				else if (roles.get(i) == "Operator")
					choices.remove("4.\tOperator");
			}
			System.out.println("Choose a role to add to the new user:");
			for (int i = 0; i < choices.size(); i++)
				System.out.println(choices.get(i));
			int choice = input.nextInt();
			input.nextLine();
			switch (choice) {
			case 1:
				roles.add("Admin");
				break;
			case 2:
				roles.add("Pharmacist");
				break;
			case 3:
				roles.add("Foreman");
				break;
			case 4:
				roles.add("Operator");
				break;
			default:
				if (choice == 5)
					break;
				System.out.println("Invalid input. Enter 1-5.");
			}
			if (choice == 5)
				break;
		}
		UserDTO temp = new UserDTO(userID, userName, initials, cpr, null, roles);
		sendUser(temp);
	}
	private void sendUser(UserDTO temp){
		try {
			f.createUser(temp);
			System.out.println(f.getUser(temp.getUserID()));
		} catch (DatabaseFullException e) {
			System.out.println("The database is full. Delete a user before entering a new one.");
		} catch (InvalidIDException e) {
			System.out.println(e.getMessage());
			System.out.println("Please enter an ID between 11-99");
			temp.setUserId(input.nextInt());
			input.nextLine();
			sendUser(temp);
		} catch (InvalidCPRException e) {
			System.out.println("You have entered a wrong CPR.\nPlease enter a correct one, in the form (123456-7890).\n");
			temp.setCpr(input.nextLine());
			sendUser(temp);
		}catch(InvalidINIException e){
			System.out.println("You have entered invalid initials.\nPlease enter some new ones.");
			temp.setIni(input.nextLine());
			sendUser(temp);
		}catch(InvalidUserNameException e ){
			System.out.println(e.getMessage());
			System.out.println("Enter a new username");
			temp.setUserName(input.nextLine());
			sendUser(temp);
		}catch(NoRoleException e){
			System.out.println(e);
			List<String> roles = new ArrayList<String>();
			List<String> choices = new ArrayList<String>();
			choices.add("1.\tAdmin");
			choices.add("2.\tPharmacist");
			choices.add("3.\tForeman");
			choices.add("4.\tOperator");
			choices.add("5.\tRole selection done");
			while (true) {
				for (int i = 0; i < roles.size(); i++) {
					if (roles.get(i) == "Admin")
						choices.remove("1.\tAdmin");
					else if (roles.get(i) == "Pharmacist")
						choices.remove("2.\tPharmacist");
					else if (roles.get(i) == "Foreman")
						choices.remove("3.\tForeman");
					else if (roles.get(i) == "Operator")
						choices.remove("4.\tOperator");
				}
				System.out.println("Choose a role to add to the new user:");
				for (int i = 0; i < choices.size(); i++)
					System.out.println(choices.get(i));
				int choice = input.nextInt();
				input.nextLine();
				switch (choice) {
				case 1:
					roles.add("Admin");
					break;
				case 2:
					roles.add("Pharmacist");
					break;
				case 3:
					roles.add("Foreman");
					break;
				case 4:
					roles.add("Operator");
					break;
				default:
					if (choice == 5)
						break;
					System.out.println("Invalid input. Enter 1-5.");
				}
				if (choice == 5)
					break;
			}
			temp.setRoles(roles);
			sendUser(temp);
		}catch(DALException e){
			e.printStackTrace();
		}
	}
	public void updateUserTUI(UserDTO temp){
			try {
				f.updateUser(temp);
				System.out.println(f.getUser(temp.getUserID()));
			} catch (InvalidCPRException e) {
				System.out.println("You have entered a wrong CPR.\nPlease enter a correct one, in the form (123456-7890).\n");
				temp.setCpr(input.nextLine());
				sendUser(temp);
			}catch(InvalidINIException e){
				System.out.println("You have entered invalid initials.\nPlease enter some new ones.");
				temp.setIni(input.nextLine());
				sendUser(temp);
			}catch(InvalidUserNameException e ){
				System.out.println(e.getMessage());
				System.out.println("Enter a new username");
				temp.setUserName(input.nextLine());
				sendUser(temp);
			}catch(NoRoleException e){
				System.out.println(e);
				List<String> roles = new ArrayList<String>();
				List<String> choices = new ArrayList<String>();
				choices.add("1.\tAdmin");
				choices.add("2.\tPharmacist");
				choices.add("3.\tForeman");
				choices.add("4.\tOperator");
				choices.add("5.\tRole selection done");
				while (true) {
					for (int i = 0; i < roles.size(); i++) {
						if (roles.get(i) == "Admin")
							choices.remove("1.\tAdmin");
						else if (roles.get(i) == "Pharmacist")
							choices.remove("2.\tPharmacist");
						else if (roles.get(i) == "Foreman")
							choices.remove("3.\tForeman");
						else if (roles.get(i) == "Operator")
							choices.remove("4.\tOperator");
					}
					System.out.println("Choose a role to add to the new user:");
					for (int i = 0; i < choices.size(); i++)
						System.out.println(choices.get(i));
					int choice = input.nextInt();
					input.nextLine();
					switch (choice) {
					case 1:
						roles.add("Admin");
						break;
					case 2:
						roles.add("Pharmacist");
						break;
					case 3:
						roles.add("Foreman");
						break;
					case 4:
						roles.add("Operator");
						break;
					default:
						if (choice == 5)
							break;
						System.out.println("Invalid input. Enter 1-5.");
					}
					if (choice == 5)
						break;
				}
				temp.setRoles(roles);
				sendUser(temp);
			}catch(InvalidPasswordException e){
				System.out.println(e.getMessage());
				System.out.println("Please enter a new password with the correct parameters.");
				temp.setPassword(input.nextLine());
				sendUser(temp);
			}catch(DALException e){
				e.printStackTrace();
			}
		}

	public void showAllUsers() {
		try {
			List<UserDTO> users = f.getUserList();
			for (int i = 0; i < users.size(); i++)
				System.out.println("User " + (i + 1) + "\n" + users.get(i).toString());
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateUser() {

		System.out.println("Enter the ID of the user you want to update: ");
		int id = input.nextInt();
		input.nextLine();
		UserDTO temp=null;
		try {
			temp = f.getUser(id);
			while (true) {
				System.out.println("Which attribute do you want to update?");
				System.out.println("1.\tUsername");
				System.out.println("2.\tInitials");
				System.out.println("3.\tCPR number");
				System.out.println("4.\tPassword");
				System.out.println("5.\tRoles");
				System.out.println("6.\tEnd update");
				int choice = input.nextInt();
				input.nextLine();
				switch (choice) {
				case 1:
					System.out.println("Enter the new name of the user:");
					temp.setUserName(input.nextLine());
					break;
				case 2:
					System.out.println("Enter the new initials of the user:");
					temp.setIni(input.nextLine());
					break;
				case 3:
					System.out.println("Enter the new CPR of the user:");
					temp.setCpr(input.nextLine());
					break;
				case 4:
					System.out.println("Enter the new password of the user:");
					temp.setPassword(input.nextLine());
					break;
				case 5:
					System.out.println("Do you want to add or delete roles?\n1.\tAdd roles\n2.\tDelete roles");
					int subchoice = input.nextInt();
					input.nextLine();
					switch (subchoice) {
					case 1:
						List<String> roles = temp.getRoles();
						List<String> choices = new ArrayList<String>();
						choices.add("1.\tAdmin");
						choices.add("2.\tPharmacist");
						choices.add("3.\tForeman");
						choices.add("4.\tOperator");
						choices.add("5.\tRole selection done");
						while (true) {
							for (int i = 0; i < roles.size(); i++) {
								if (roles.get(i) == "Admin")
									choices.remove("1.\tAdmin");
								else if (roles.get(i) == "Pharmacist")
									choices.remove("2.\tPharmacist");
								else if (roles.get(i) == "Foreman")
									choices.remove("3.\tForeman");
								else if (roles.get(i) == "Operator")
									choices.remove("4.\tOperator");
							}
							System.out.println("Choose a role to add to the user:");
							for (int i = 0; i < choices.size(); i++)
								System.out.println(choices.get(i));
							int subsubchoice = input.nextInt();
							input.nextLine();
							switch (subsubchoice) {
							case 1:
								roles.add("Admin");
								break;
							case 2:
								roles.add("Pharmacist");
								break;
							case 3:
								roles.add("Foreman");
								break;
							case 4:
								roles.add("Operator");
								break;
							default:
								if (subsubchoice == 5)
									break;
								System.out.println("Invalid input. Enter 1-5.");
							}
							if (subsubchoice == 5) {
								temp.setRoles(roles);
								break;
							}
						}
						break;
					case 2:
						List<String> roles2 = temp.getRoles();
						List<String> choices2 = new ArrayList<String>();
						while (true) {
							for (int i = 0; i < roles2.size(); i++) {
								if (roles2.get(i) == "Admin")
									choices2.add("1.\tAdmin");
								else if (roles2.get(i) == "Pharmacist")
									choices2.add("2.\tPharmacist");
								else if (roles2.get(i) == "Foreman")
									choices2.add("3.\tForeman");
								else if (roles2.get(i) == "Operator")
									choices2.add("4.\tOperator");
							}
							choices2.add("5.\tRole selection done");

							System.out.println("Choose a role to remove from the user:");
							for (int i = 0; i < choices2.size(); i++)
								System.out.println(choices2.get(i));
							int subsubchoice = input.nextInt();
							input.nextLine();
							switch (subsubchoice) {
							case 1:
								roles2.remove("Admin");
								break;
							case 2:
								roles2.remove("Pharmacist");
								break;
							case 3:
								roles2.remove("Foreman");
								break;
							case 4:
								roles2.remove("Operator");
								break;
							default:
								if (subsubchoice == 5)
									break;
								System.out.println("Invalid input. Enter 1-5.");
							}
							if (subsubchoice == 5) {
								temp.setRoles(roles2);
								break;
							}
						}
						break;
					default:
						if (subchoice == 3) {
							break;
						}
						System.out.println("Invalid input. Enter 1-3.");
						break;
					}
					if (subchoice == 3) {
						break;
					}
				default:
					if (choice == 6) {
						break;
					}
					System.out.println("Invalid input. Enter 1-6");
					break;
				}
				if (choice == 6) {
					break;
				}
			}
		}catch(EmptyStoreException e){
			System.out.println(e.getMessage());
		}catch(UserNotFoundException e){
			System.out.println("No user was found with that ID.");
		}catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		updateUserTUI(temp);
	}

	public void deleteUser() {
		System.out.println("Enter the ID of the user you want to delete: ");
		int id = input.nextInt();
		input.nextLine();
		try {
			f.deleteUser(id);
		} catch (DALException e) {
			e.printStackTrace();
		}
	}
}

package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.UserDTO;

public class TUI {
	Scanner input = new Scanner(System.in);
//	private IFunctinality f;
//
//	public TUI(IFunctionality f) {
//		this.f = f;
//	}

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
				//showAllUsers();
				break;
			case 3:
				//updateUser();
				break;
			case 4:
				//deleteUser();
				break;
			case 5:
				input.close();
				System.exit(1);
				break;
			}
		}
	}

	public void createUser() {
		String userName, initials, password,cpr;
		int userID;

		List<String> roles = new ArrayList<String>();
		List<String> choices = new ArrayList<String>();
		choices.add("1.\tAdmin");
		choices.add("2.\tPharmacist");
		choices.add("3.\tForeman");
		choices.add("4.\tOperator");
		choices.add("5.\tRole selection done");
		
		
		System.out.println("Enter the ID of the new user, as an integer between 11 and 99:");
		userID=input.nextInt();
		input.nextLine();
		System.out.println("Enter the name of the new user:");
		userName = input.nextLine();
		System.out.println("Enter the initials of the new user:");
		initials = input.nextLine();
		System.out.println("Enter the CPR number of the new user, as an integer: ");
		cpr = input.nextLine();
		System.out.println("Enter a password for the new user: ");
		password = input.nextLine();
		System.out.println("Enter the roles of the new user:");

		while(true){

			for(int i=0;i<roles.size();i++){
				if(roles.get(i)=="Admin")
					choices.remove("1.\tAdmin");
				else if(roles.get(i)=="Pharmacist")
					choices.remove("2.\tPharmacist");
				else if(roles.get(i)=="Foreman")
					choices.remove("3.\tForeman");
				else if(roles.get(i)=="Operator")
					choices.remove("4.\tOperator");
			}
			System.out.println(choices.toString());
			int choice=input.nextInt();
			input.nextLine();
			switch(choice){
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
			}
			if(choice==5)
				break;
		}
//		UserDTO temp = new UserDTO(userID, userName, initials, cpr, password, roles);
//		try {
//			f.createUser(temp);
//		} catch (IllegalIdException e){
//			System.out.println("You have entered an Illegal id");
//			temp.setUserId(input.nextInt());
//			input.nextLine();
//		}catch (IllegalCprException e) {
//			System.out.println("You have entered a wrong CPR.\nPlease enter a correct one, in 12 integers.\n");
//			temp.setCpr();
//		}
	}
}

package academy.prog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("Welcome to Chat \n" +
					"Input login and password(>5 symbols):");
			String login = scanner.nextLine();
			String password = scanner.nextLine();

			if(login.isEmpty()) {
				System.out.println("Invalid login");
				return;
			}

			if(password.length() <= 5){
				System.out.println("Password must be >5 symbols");
				return;
			}


			Login loginObj = new Login(login, password);
			if(!loginObj.loginStatus()){
				System.out.println("You are not logged in. Do you want to sing up?(y/n)");
				String answer = scanner.nextLine().toLowerCase();
				if(answer.equals("y")){
					Register registr = new Register(login, password);
					registr.registerClients();
				} else {
					System.out.println("You are not logged in.");
					return;
				}
			}

			Thread th = new Thread(new GetThread());
			th.setDaemon(true);
			th.start();

            System.out.println("Enter your message: ");
			while (true) {
				String text = scanner.nextLine();
				if (text.isEmpty()) break;

				if (text.equals("/logout")){
					Logout logout = new Logout(login);
					logout.logout();
					return;
				}

				if (text.equals("/online")){
					OnlineUsers onlineUsers = new OnlineUsers();
					onlineUsers.updateOnlineUsers();
					System.out.println(onlineUsers.getListOfOnlineUsers());
					continue;
				}

				if (text.equals("/private")){
					OnlineUsers onlineUsers = new OnlineUsers();
					onlineUsers.updateOnlineUsers();
					System.out.println("Choose user:");
					System.out.println(onlineUsers.getListOfOnlineUsers());
					String answer = scanner.nextLine();

					System.out.println("Enter your message: ");
					String message = scanner.nextLine();
					Message m = new Message(login, answer, message);
					int res = m.send(Utils.getURL() + "/add");
					continue;
				}

				Message m = new Message(login, text);
				int res = m.send(Utils.getURL() + "/add");

				if (res != 200) { // 200 OK
					System.out.println("HTTP error occurred: " + res);
					return;
				}
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			scanner.close();
		}
	}
}

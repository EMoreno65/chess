package ui;

import Request.LoginRequest;
import Request.RegisterRequest;
import RequestandResult.LoginResult;
import RequestandResult.RegisterResult;
import dataAccess.ResponseException;
import model.UserData;
import server.LoginHandler;
import server.RegisterHandler;
import serverFacade.serverFacade;

import java.util.Scanner;

public class createMenu {

  public serverFacade server = new serverFacade("http://localhost:8080");

  public static void main(String[] args) {
    // Create a Scanner object to read input from the console
    Scanner scanner = new Scanner(System.in);
    createMenu.writeHelpScreen();
    System.out.print("Input Number Here: ");
    while (true) {
      String userInput = scanner.nextLine();
      if (userInput.equals("1")) {
        System.out.println(" Help - By Pressing Help you can show all the options available and what each option does");
        System.out.println(" Quit - This option will make you exit out of the program");
        System.out.println(" Login - This option will give you a prompt to input your username and password and have you login");
        System.out.println(" Register - This option will give you the option to input a username, password and an email to create a user");
      } else if (userInput.equals("2")) {
          break;
      } else if (userInput.equals("3")) {
          System.out.print("Enter Username: ");
          System.out.print("Enter Password: ");
          String userInputUsername = scanner.nextLine();
          String userInputPassword = scanner.nextLine();
          if (loginCommand(userInputUsername, userInputPassword)){
            // go to printSecondMenu function
          }
          else {
            // Send error message
          }
      } else if (userInput.equals("4")) {
          System.out.println("Enter Username: ");
          System.out.println("Enter Password: ");
          System.out.println("Enter Email: ");
        String userInputUsername = scanner.nextLine();
        String userInputPassword = scanner.nextLine();
        String userInputEmail = scanner.nextLine();
        if (registerCommand(userInputUsername, userInputPassword, userInputEmail)){
          // go to printSecondMenu function
        }
        else {
          // Send error message
        }
      }
    }

    // Close the scanner
    scanner.close();
  }
  // Print things to the terminal for the user to select
  // some kinda switch statement
  // login -> give username and password, put inside login request
  // give login request to server facade call .login
  // Print out results in terminal by calling methods and receiving objects

  public static void writeHelpScreen(){
    System.out.println("Welcome to CS240 Chess");
    System.out.println("Type in Corresponding Number to Begin");
    System.out.println("1 - Help");
    System.out.println("2 - Quit");
    System.out.println("3 - Login");
    System.out.println("4 - Register");
    // Login Option
    // Get information from user
    // Check if we have the correct parameters, ask one at a time and press enter
  }
  public void helpCommand(){

  }
  public void quitCommand(){

  }
  public boolean loginCommand(String username, String password) throws ResponseException {
    UserData userData = new UserData(username, password, "");
    LoginRequest receivedRequest = server.login(userData);
    LoginHandler loginHandler = new LoginHandler();
    LoginResult receivedResult = (LoginResult) loginHandler.login(receivedRequest, null, null, null);
    if (receivedResult.getAuthToken() != null){
      return true;
    }
    else {
      return false;
    }
    // Login Option
    // Get information from user
    // Check if we have the correct parameters, ask one at a time and press enter
  }
  public boolean registerCommand(String username, String password, String email) throws ResponseException {
    UserData userData = new UserData(username, password, email);
    RegisterRequest receivedRequest = server.register(userData);
    RegisterHandler registerHandler = new RegisterHandler(); // Create an instance
    RegisterResult receivedResult =(RegisterResult) registerHandler.register(receivedRequest, null, null, null);
    // Pass request into handler, let it do it's thing, receive result. Check if result has an authToken, if it does, move it to the next menu
    if (receivedResult.getAuthToken() != null){
      return true;
    }
    else {
      return false;
    }
  }

}



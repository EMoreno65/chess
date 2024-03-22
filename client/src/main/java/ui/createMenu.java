package ui;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dataAccess.DataAccessException;
import model.AuthData;
import model.Request.LoginRequest;
import model.Request.RegisterRequest;
import model.RequestandResult.CreateResult;
import model.RequestandResult.LoginResult;
import model.RequestandResult.LogoutResult;
import model.RequestandResult.RegisterResult;
import model.UserData;
import serverFacade.serverFacade;

import java.util.Scanner;

public class createMenu {

  public static serverFacade server = new serverFacade("http://localhost:8080");
  static String savedAuthToken;

  public static void main(String[] args) throws ResponseException {
    operateFirstMenu();
    operateSecondMenu();
  }
  // Print things to the terminal for the user to select
  // some kinda switch statement
  // login -> give username and password, put inside login request
  // give login request to server facade call .login
  // Print out results in terminal by calling methods and receiving objects

  public static void writeHelpScreen1(){
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

  public static void writeHelpScreen2(){
    System.out.println("1 - Help");
    System.out.println("2 - Logout");
    System.out.println("3 - Create Game");
    System.out.println("4 - List Games");
    System.out.println("5 - Join Game");
    System.out.println("6 - Join as an Observer");
  }

  public void helpCommand(){

  }
  public void quitCommand(){

  }
  public static boolean loginCommand(String username, String password) throws ResponseException {
    UserData userData = new UserData(username, password, "");
    LoginResult receivedResult = server.login(userData);
    savedAuthToken = receivedResult.getAuthToken();
    if (savedAuthToken != null){
      return true;
    }
    else {
      return false;
    }
    // Login Option
    // Get information from user
    // Check if we have the correct parameters, ask one at a time and press enter
  }
  public static boolean registerCommand(String username, String password, String email) throws ResponseException {
    UserData userData = new UserData(username, password, email);
    RegisterResult receivedResult = server.register(userData);
    savedAuthToken = receivedResult.getAuthToken();
    if (savedAuthToken != null){
      return true;
    }
    else {
      return false;
    }
  }

  public static void operateFirstMenu() throws ResponseException {
    Scanner scanner = new Scanner(System.in);
    createMenu.writeHelpScreen1();
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
        String userInputUsername = scanner.nextLine();
        System.out.print("Enter Password: ");
        String userInputPassword = scanner.nextLine();
        if (loginCommand(userInputUsername, userInputPassword)){
          createMenu.operateSecondMenu();
        }
        else {
          // Send error message
        }
      } else if (userInput.equals("4")) {
        System.out.println("Enter Username: ");
        String userInputUsername = scanner.nextLine();
        System.out.println("Enter Password: ");
        String userInputPassword = scanner.nextLine();
        System.out.println("Enter Email: ");
        String userInputEmail = scanner.nextLine();
        if (registerCommand(userInputUsername, userInputPassword, userInputEmail)){
          createMenu.operateSecondMenu();
        }
        else {
          throw new ResponseException(400, "Can't Register");
        }
      }
    }

    // Close the scanner
    scanner.close();
  }

  public static void operateSecondMenu() throws ResponseException {
    Scanner scanner = new Scanner(System.in);
    createMenu.writeHelpScreen2();
    System.out.print("Input Number Here: ");
    while (true) {
      String userInput = scanner.nextLine();
      if (userInput.equals("1")) {
        System.out.println(" Help - By Pressing Help you can show all the options available and what each option does");
        System.out.println(" Logout - This option will log you out of your account and return you to the main menu");
        System.out.println(" Create Game - This will create a chess game with a unique ID");
        System.out.println(" List Games - This option will show all the available games");
        System.out.println(" Join Game - You will join an available game as a player");
        System.out.println(" Join as an Observer - You will join an available game as an observer");
      }
      else if (userInput.equals("2")){
        createMenu.logoutCommand();
      }
      else if (userInput.equals("3")){
        System.out.print("Insert Game Name Here: ");
        String userInputGameName = scanner.nextLine();
        if(createCommand(userInputGameName, savedAuthToken)){
          System.out.println("Game Created");
        }
        else{
          System.out.println("Game Unable to be Created");
        }
      }
      else if (userInput.equals("4")){

      }
      else if (userInput.equals("5")){

      }
      else if (userInput.equals("6")){

      }
    }
    }
    public static void logoutCommand() throws ResponseException {
      if (savedAuthToken == null){
        return;
      }
      AuthData authData = new AuthData(savedAuthToken, "", "");
      LogoutResult logoutResult = server.logout(authData);
      savedAuthToken = null;
      createMenu.operateFirstMenu();
    }

    public static boolean createCommand(String gameName, String authToken) throws ResponseException {
      try {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("gameName", gameName); // Add the gameName to the JSON object

        // Serialize the JSON object to a string
        String requestBodyString = new Gson().toJson(requestBody);
        CreateResult createResult = server.create(requestBodyString, authToken);
        if (createResult != null) {
          return true;
        } else {
          return false;
        }
      } catch (ResponseException e) {
        System.out.println("Error creating game: " + e.getMessage());
        return false;
      }
    }
  // Print things to the terminal for the user to select
  // some kinda switch statement
  // login -> give username and password, put inside login request
  // give login request to server facade call .login
  // Print out results in terminal by calling methods and receiving objects
  }



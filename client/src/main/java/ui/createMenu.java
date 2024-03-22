package ui;

import chess.ChessBoard;
import chess.ChessGame;
import com.google.gson.Gson;
import model.AuthData;
import model.GameData;
import model.RequestandResult.*;
import model.UserData;
import serverFacade.serverFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static chess.ChessGame.TeamColor.BLACK;
import static chess.ChessGame.TeamColor.WHITE;

public class createMenu {

  public static serverFacade server = new serverFacade("http://localhost:8080");
  static String savedAuthToken;
  static List<GameData> games = new ArrayList<>();
  static String userName;

  public static void main(String[] args) throws ResponseException {
    operateFirstMenu();
    operateSecondMenu();
  }
  // Print things to the terminal for the user to select
  // some kinda switch statement
  // login -> give username and password, put inside login request
  // give login request to server facade call .login
  // Print out results in terminal by calling methods and receiving objects

    public static ChessGame.TeamColor fromString(String color) {
      return switch (color.toLowerCase()) {
        case "black" -> BLACK;
        case "white" -> WHITE;
        default -> throw new IllegalArgumentException("Invalid team color: " + color);
      };
    }


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
        userName = userInputUsername;
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
        userName = userInputUsername;
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
          createMenu.operateSecondMenu();
        }
        else{
          System.out.println("Game Unable to be Created");
          createMenu.operateSecondMenu();
        }
      }
      else if (userInput.equals("4")){
        System.out.println("Current Games");
        List<GameData> gamesToShow = createMenu.listCommand(savedAuthToken);
        int index = 1;

        for (GameData game : gamesToShow) { // Enhanced for loop to iterate over gamesToShow
          System.out.println(index++ + ". " + "Game Name is " + game.gameName() + " Game ID is " + game.gameID() + " , " + "White Team Player = " + game.whiteUsername()+ " , " + "Black Team Player = " + game.blackUsername());
        }
        createMenu.operateSecondMenu();
      }
      else if (userInput.equals("5")){

        System.out.print("Enter Number of game you'd like to join: ");
        String userInputGameNumber = scanner.nextLine();
        int selectedGameIndex = Integer.parseInt(userInputGameNumber);
        System.out.println("Enter which color you'd like to be (Please type all lowercase either 'black' or 'white'): ");
        String userInputChosenTeam = scanner.nextLine();
        int index = 0; // Initialize index to start from 0
        for (GameData game : games) {
          if (++index == selectedGameIndex) { // Increment index and compare
            GameData updatedGame;
            ChessGame.TeamColor color = fromString(userInputChosenTeam);
            server.join(savedAuthToken, color, game.gameID());
            if (userInputChosenTeam.equals("white")) {
              updatedGame = new GameData(game.gameID(), userName, game.blackUsername(), game.gameName(), game.game());
            } else if (userInputChosenTeam.equals("black")) {
              updatedGame = new GameData(game.gameID(), game.whiteUsername(), userName, game.gameName(), game.game());
            } else {
              // Handle invalid team choice
              break;
            }
            games.set(index - 1, updatedGame);
            break; // Exit the loop once the desired game is found and updated
          }
        }
        createMenu.operateSecondMenu();
      }
      else if (userInput.equals("6")){
        System.out.print("Enter Number of game you'd like to join as an observer: ");
        String userInputGameNumber = scanner.nextLine();
        int selectedGameIndex = Integer.parseInt(userInputGameNumber);
        int index = 0;
        for (GameData game : games) {
          if (++index == selectedGameIndex) {
             // Update the game in the list
            server.join(savedAuthToken, null, game.gameID());
            System.out.println("Joined as Observer");
            printObject.drawWhiteBoard(game.game());
            printObject.drawBlackBoard(game.game());
            createMenu.operateSecondMenu();
            break;
          }
        }
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
//        JsonObject requestBody = new JsonObject();
//        requestBody.addProperty("gameName", gameName); // Add the gameName to the JSON object

        // Serialize the JSON object to a string
        String requestBodyString = new Gson().toJson(gameName);
        CreateResult createResult = server.create(requestBodyString, authToken);
        if (createResult != null) {
          GameData gameData = new GameData(createResult.getGameID(), null, null, gameName, new ChessGame());
          ChessBoard board = (gameData.game()).getBoard();
          board.resetBoard();
          games.add(gameData);
          return true;
        } else {
          return false;
        }
      } catch (ResponseException e) {
        System.out.println("Error creating game: " + e.getMessage());
        return false;
      }
    }
    public static List<GameData> listCommand(String authToken) throws ResponseException {
      ListResult listResult = server.list(games, authToken);
      return games;
    }
  // Print things to the terminal for the user to select
  // some kinda switch statement
  // login -> give username and password, put inside login request
  // give login request to server facade call .login
  // Print out results in terminal by calling methods and receiving objects
  }



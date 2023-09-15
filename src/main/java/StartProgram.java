
import java.util.List;
import java.util.Scanner;

import model.VideoGame;
import controller.VideoGameHelper;

/**
 * @author Kenneth Nimmo - Knimmo
 * CIS175 - Fall 2021
 * Sep 13, 2023
 */
public class StartProgram {
	
	static Scanner userIn = new Scanner(System.in);
	static VideoGameHelper vgh = new VideoGameHelper();
	
	private static void addAnItem() {
		System.out.print("Enter a store: ");
		String store = userIn.nextLine();
		System.out.print("Enter a Game: ");
		String game = userIn.nextLine();
		System.out.print("Enter a price: ");
		double price = userIn.nextDouble();
		
		VideoGame toAdd = new VideoGame(store, game, price);
		vgh.insertItem(toAdd);
	}
	
	private static void deleteAnItem() {
		System.out.print("Enter the store to delete: ");
		String store = userIn.nextLine();
		System.out.print("Enter the game to delete: ");
		String game = userIn.nextLine();
		System.out.print("Enter the price to delete: ");
		double price = userIn.nextDouble();
		
		VideoGame toDelete = new VideoGame(store, game, price);
		vgh.deleteGame(toDelete);
	}
	
	private static void editAnItem() {
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Store");
		System.out.println("2 : Search by Game");
		System.out.println("3 : Search by Price");
		int searchBy = userIn.nextInt();
		userIn.nextLine();
		List<VideoGame> foundItems;
		if(searchBy == 1) {
			System.out.print("Enter the store name: ");
			String storeName = userIn.nextLine();
			foundItems = vgh.searchForGameByStore(storeName);
		} else if(searchBy == 2) {
			System.out.print("Enter the games name: ");
			String gameName = userIn.nextLine();
			foundItems = vgh.searchForGameByGameName(gameName);
		} else {
			System.out.print("Enter the price: ");
			double price = userIn.nextDouble();
			foundItems = vgh.searchForGameByPrice(price);
		}
		
		if(!foundItems.isEmpty()) {
			System.out.println("Found results.");
			for(VideoGame i : foundItems) {
				System.out.println(i.getId() + " : " + i.getGameDetails());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = userIn.nextInt();
			
			VideoGame toEdit = vgh.searchForGameById(idToEdit);
			System.out.println("Retrieved " + toEdit.getGame() + " from " + toEdit.getStore() + " price " + toEdit.getPrice());
			System.out.println("1 : Update Game Name");
			System.out.println("2 : Update Store Name");
			System.out.println("3 : Update Price");
			int update = userIn.nextInt();
			userIn.nextLine();
			
			if(update == 1) {
				System.out.print("New Game Name: ");
				String newGameName = userIn.nextLine();
				toEdit.setGame(newGameName);
			} else if(update == 2) {
				System.out.print("New Store Name: ");
				String newStoreName = userIn.nextLine();
				toEdit.setStore(newStoreName);
			} else if(update == 3) {
				System.out.print("New Price: ");
				double newPrice = userIn.nextDouble();
				toEdit.setPrice(newPrice);
			}
			
			vgh.updateItem(toEdit);
			
		} else {
			System.out.println("---- No results found");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();
	}
	
	public static void runMenu() {
		//TODO
		boolean goAgain = true;
		System.out.println("");
		while(goAgain) {
			System.out.println("* Select a menu item:");
			System.out.println("* 1 -- Add a game");
			System.out.println("* 2 -- Edit a game");
			System.out.println("* 3 -- Delete a game");
			System.out.println("* 4 -- View the list of games");
			System.out.println("* 5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int userSelection = userIn.nextInt();
			userIn.nextLine();
			
			if(userSelection == 1) {
				addAnItem();
			} else if(userSelection == 2) {
				editAnItem();
			} else if(userSelection == 3) {
				deleteAnItem();
			} else if(userSelection == 4) {
				viewTheList();
			} else {
				vgh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}
		}
	}
	
	private static void viewTheList() {
		List<VideoGame> allItems = vgh.showAllGames();
		for(VideoGame singleItem : allItems) {
			System.out.println(singleItem.getGameDetails());
		}
	}

}

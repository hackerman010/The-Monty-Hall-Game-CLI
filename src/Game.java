import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    static Scanner mainInput = new Scanner(System.in);
    static int sportsCar, userGuess, montysDoor;
    static int i, j; // Variables for controlling loops
    
    public static void main(String args[]) {
	// Print title to the screen
	System.out.println
	(
		" _____ _          __  __         _          _  _      _ _    ___                \n" +
		"|_   _| |_  ___  |  \\/  |___ _ _| |_ _  _  | || |__ _| | |  / __|__ _ _ __  ___ \n" +
		"  | | | ' \\/ -_) | |\\/| / _ \\ ' \\  _| || | | __ / _` | | | | (_ / _` | '  \\/ -_)\n" +
		"  |_| |_||_\\___| |_|  |_\\___/_||_\\__|\\_, | |_||_\\__,_|_|_|  \\___\\__,_|_|_|_\\___|\n" +
		"                                     |__/                                       "
	);
	System.out.println();
	
	System.out.print("Welcome to The Monty Hall Game! Press ENTER to begin.");
	mainInput.nextLine();
	System.out.println();
	
	// Start the game after the user pressed Enter
	initializeGame();
    }
    
    public static void initializeGame() {
	for(i = 0; i < Door.closedDoor().length; i++) {
	    for(j = 0; j < 3; j++) {
		System.out.print(Door.closedDoor()[i]);
		if(j < 2) System.out.print("\t");
	    }
	    System.out.print('\n');
	}
	System.out.println();
	System.out.println("Monty shows you three doors.");
	System.out.println("Behind one is a sports car, behind the other two is a goat.");
	System.out.println("You have to guess behind which door the sports car is.");
	play();
    }
    
    public static void play() {
	/*
	 * Generate a number between 1 and 3
	 * ie. 'place the sportscar behind one of the doors'
	 * 
	 * The generated number is the winner
	 */
	sportsCar = (int) (Math.random() * 3 + 1);
	
	boolean validInput = false;
	do {
	    System.out.print("\nEnter an integer between 1 and 3: ");
	    
	    // Handle non-integer inputs
	    try {
		userGuess = mainInput.nextInt();
	    } catch(InputMismatchException e) {
		userGuess = 0;
		mainInput.next();
	    }
	    
	 // Check if the user gives an integer input between 1 and 3
	 if(userGuess >= 1 && userGuess <= 3)
	     validInput = true;
	 else
	     System.out.print("\nThe guess you entered is not valid. Please try again.");
	} while(!validInput);
	System.out.println();
	
	// Assign a door which has a goat behind to open
	for(i = 1; i <= 3; i++) {
	    if(i != sportsCar && i != userGuess) {
		montysDoor = i;
		break;
	    }
	}
	
	// Find a door which the user hasn't guessed and has a goat behind
	for(i = 0; i < Door.closedDoor().length; i++) {
	    for(j = 1; j <= 3; j++) {
		if(j == montysDoor) {
		    System.out.print(Door.goatDoor()[i]);
		} else {
		    System.out.print(Door.closedDoor()[i]);
		}
		
		if(j < 3) System.out.print("\t");
	    }
	    System.out.print('\n');	    
	}
	
	System.out.println();
	System.out.printf("Monty opens door number %s, which hides a goat.", montysDoor);
	
	int switchTo = 0; // The door the user can switch to
	for(i = 1; i <= 3; i++) {
	    if(i != montysDoor && i != userGuess) {
		switchTo = i;
		break;
	    }
	}
	
	System.out.printf("\nHe gives you the chance to switch to door %s.\n", switchTo);
	
	validInput = false;
	char yOrN;
	
	// Read and validate user input
	do {
	    System.out.print("\nDo you switch (y/n)? ");
	    yOrN = mainInput.next(".").charAt(0);
	    yOrN = Character.toLowerCase(yOrN);
	    
	    if(yOrN == 'y' || yOrN == 'n') validInput = true;
	    else System.out.println("Please type either 'y' or 'n'!");
	} while(!validInput);
	
	if(yOrN == 'y') {
	    userGuess = switchTo;
	    System.out.printf("You chose to switch to door %s.\n", userGuess);
	} else {
	    System.out.printf("You chose to stay with door %s.\n", userGuess);
	}
	
	System.out.println();
	System.out.printf("Now, Monty opens your door, door %s.\n", userGuess);
	System.out.println("Let's see what you take home...");
	
	for(i = 0; i < Door.closedDoor().length; i++) {
	    if(userGuess == sportsCar) {
		System.out.println(Door.carDoor()[i]);
	    } else {
		System.out.println(Door.goatDoor()[i]);
	    }
	}
	System.out.print('\n');
	
	if(userGuess == sportsCar) {
	    System.out.println("Congratualtions, enjoy your brand new soprtscar!");
	} else {
	    System.out.println("Unlucky. You go home with a goat.");
	}
	
	
	validInput = false;
	do {
	    System.out.print("\nWould you like to play again(y/n)? ");
	    yOrN = mainInput.next(".").charAt(0);
	    yOrN = Character.toLowerCase(yOrN);
	    
	    if(yOrN == 'y' || yOrN == 'n') validInput = true;
	    else System.out.println("Please type either 'y' or 'n'!");
	} while(!validInput);
	
	if(yOrN == 'y') initializeGame();
    }
}
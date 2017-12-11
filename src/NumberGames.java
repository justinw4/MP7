import java.util.Scanner;

public class NumberGames {
	
	public static void main(String[] args) {
		
		int inputNumber = 1;
		Scanner reader = new Scanner(System.in); 
		System.out.println("Pick a number game! Type '1' for Collatz Conjecture or type '2' for Guess The Number" 
				+ "or type '3' for Magic Maths or type '4' for DEAL OR NO DEAL! ");
		
		inputNumber = reader.nextInt();
		
		while (inputNumber != 1 && inputNumber != 2 && inputNumber != 3 && inputNumber != 4) {
			System.out.println("Please pick either '1','2','3', or '4'.");
			inputNumber = reader.nextInt();
		}
		
		if (inputNumber == 1) {
			Collatz_Conjecture_Proof a = new Collatz_Conjecture_Proof();
			String[] b = {};
			a.main(b);
		}
		
		if (inputNumber == 2) {
			System.out.println("Choose the upper bound for the game! ");
			int bound = reader.nextInt();
			while (bound <= 1) {
				System.out.println("Please enter a bound greater than 1.");
				System.out.println("Your new bound: ");
				bound = reader.nextInt();
			}
			System.out.println("Can you guess the number I am thinking of between 1 and " + bound + "? ");
			guessTheNumber(bound);
		}
		
		if (inputNumber == 3) {
			magicMaths();
		}
		
		if (inputNumber == 4) {
			dealOrNoDeal();
		}
	}
	
	
	
	
	
	// Guess the Number
	
	
	
	
	
	public static void guessTheNumber(final int bound) {
		
		int correctNumber = (int) (Math.random() * (bound - 1) + 1); 
		Scanner sc = new Scanner(System.in);
		System.out.println("Your guess: ");
		int guess = sc.nextInt(); 
		while (guess != correctNumber) {
			if (guess < 1 || guess > bound) {
				System.out.println("Please guess between the bounds.");
			} else {
				if (guess < correctNumber) {
					System.out.println("That's incorrect. The correct value is greater...");
				}
				if (guess > correctNumber) {
					System.out.println("That's incorrect. The correct value is smaller...");
				}
			}
			System.out.println("Your new guess: ");
			guess = sc.nextInt(); 
		}
		System.out.println("You win!!!");
	}
	
	
	
	
	
	
	// Magic Maths
	
	
	
	
	
	public static void magicMaths() {
		int MAX_BOUND = 32;
		int number = 0;
		System.out.println("Pick a number between 1-"+	(MAX_BOUND-1) + ". Don't reveal it.");
		Scanner input = new Scanner(System.in);
		for(int i = 0; i < Math.log10(MAX_BOUND)/Math.log10(2); i++) {
			int count = 0;
			System.out.println("Is your number in this following list:");
			for (int j = 1; j < MAX_BOUND; j++) {
				if(((j >> i ) & 1) == 1) {
					System.out.printf("%5s", (j + " "));
					
					if(++count % 4 == 0) {
						System.out.println();
					}
				}
			}
			String confirmation = null;
			if((confirmation = input.next()).toLowerCase().charAt(0) == 'y') {
				number += Math.pow(2, i);
			}
			else if(confirmation.toLowerCase().charAt(0) != 'n') {
				System.out.println("You didn't enter a valid number!\nThat's a no for that list.");				
			}
			System.out.println("\n");
		}
		System.out.println("Your number is " + number +".");
	}

	
	
	
	
	
	// Deal or No Deal
	
	
	
	
	
	
	public static void dealOrNoDeal() {
		
		boolean gameOver = false; 
		Scanner howieMandel = new Scanner(System.in);
		int[] briefcases = assignCaseValues();
		
		System.out.println("Welcome to Deal or No Deal. The goal of the game is to eliminate cases to try and win a million dollars. ");
		System.out.println("To start off, choose the case that you think has the 1 million dollars in it and you will hold on to this for the rest of the game.");
		System.out.println("Choose your case! ");
		
		int yourCase = howieMandel.nextInt();
		
		int loopCount = 0; 
		
		while (!gameOver) {
			
			while (loopCount < 3) {
				System.out.println("Now it's time to choose 6 cases to eliminate from the board. Here are the values in the cases");
				showValuesLeft(briefcases);
			
				int count = 6; 
				while (count > 0) {
			
					System.out.println("Select a case!");
					showAvailableCases(briefcases, yourCase);
			
					int yourPick = howieMandel.nextInt(); 
					while (caseAlreadyChosen(yourPick, briefcases) || yourPick == yourCase) {
						System.out.println("That case was already selected. Pick a new case: ");
						yourPick = howieMandel.nextInt();
					}
					System.out.println("The value in the case you selected is: " + briefcases[yourPick - 1]);
					briefcases[yourPick - 1] = 0; 
					count--;
					showValuesLeft(briefcases);
				}
		
				gameOver = Banker(briefcases, gameOver);
				loopCount++; 
				if (gameOver) {
					break;
				}
			}
			if (gameOver) {
				break;
			}
			
			System.out.println("Now you only need to choose 5 cases. Here are the values that are left.");
			int count = 5; 
			while (count > 0) {
		
				System.out.println("Select a case!");
				showAvailableCases(briefcases, yourCase);
		
				int yourPick = howieMandel.nextInt(); 
				while (caseAlreadyChosen(yourPick, briefcases) || yourPick == yourCase) {
					System.out.println("That case was already selected. Pick a new case: ");
					yourPick = howieMandel.nextInt();
				}
				System.out.println("The value in the case you selected is: " + briefcases[yourPick - 1]);
				briefcases[yourPick - 1] = 0; 
				count--;
				showValuesLeft(briefcases);
			}
			gameOver = Banker(briefcases, gameOver);
			if (gameOver) {
				break;
			}
			
			System.out.println("We are now down to 2 cases: Your case, and the final case left on the board.");
			System.out.println("You can either stick with the case you chose from the beginning, or swap with the other remaining case.");
			System.out.println("The one you pick will determine the amount of money you will be going home with.");
			System.out.println("Type 'Y' to swap. Type 'N' to stay with your original case.");
			String choice = howieMandel.next().toUpperCase();
			while (!(choice.equals("Y")) && !(choice.equals("N"))) {
				System.out.println("Please type 'Y' or 'N'.");
				choice = howieMandel.next().toUpperCase();
			}
			if (choice.equals("N")) {
				System.out.print("You are going home with... $" + briefcases[yourCase- 1]);
				if (briefcases[yourCase - 1] <= 1000) {
					System.out.println("....Sorry");
				} else {
					System.out.println("!!!!! Congratulations!!!");
				}
				gameOver = true;
				
				System.out.print("The value that you could have swapped with was... $");
				int lastCase = findLastCase(briefcases, yourCase);
				System.out.print(briefcases[lastCase - 1] + ".");
				
			} else {
				int lastCase = findLastCase(briefcases, yourCase);
				System.out.print("You are going home with... $" + briefcases[lastCase - 1]);
				if (briefcases[lastCase - 1] <= 1000) {
					System.out.println("....Sorry");
				} else {
					System.out.println("!!!!! Congratulations!!!");
				}
				gameOver = true;
				
				System.out.print("The value that you could have swapped with was... $");
				System.out.print(briefcases[yourCase - 1] + ".");
			}
			
			
		}
		
	}
	
	public static boolean Banker(int[] values, boolean isGameOver) {
		
		Scanner banker = new Scanner(System.in);
		System.out.println("The banker is calling. He has an offer for you.");
		System.out.println();
		long sum = 0; 
		int count = 0; 
		for (int i = 0; i < values.length; i++) {
			sum += values[i];
			if (values[i] != 0) {
				count++;
			}
		}
		int offer = (int) (sum / count);
		System.out.println("I am the banker. I am willing to offer $" + offer + "."); 
		System.out.println("You can either take my offer or continue playing for a chance at the highest remaining value in the briefcases.");
		
		showValuesLeft(values);
		System.out.println();
		System.out.println("---- OR ----");
		System.out.println();
		System.out.println("$" + offer);
		System.out.println();
		System.out.println("It's your choice. Deal or No Deal... ('Y' for Deal, 'N' for No Deal)");
		
		String choice = banker.next().toUpperCase();
		
		while (!(choice.equals("Y")) && !(choice.equals("N"))) {
			System.out.println("Please type 'Y' or 'N'.");
			choice = banker.next().toUpperCase();
		}
		if (choice.equals("Y")) {
			System.out.println("Congratulations! You're going home with $" + offer + "!!!");
			isGameOver = true;
		} else {
			if (choice.equals("N")) {
				System.out.println("Bold move. Let's keep playing!");
			}
		}
		return isGameOver;
	}
	
	
	public static boolean caseAlreadyChosen(int c, int[] cases) {
		
		if (cases[c - 1] == 0) {
			return true;
		}
		return false;
	}
	
	
	public static void showAvailableCases(int[] values, int yourCase) {
		
		for (int i = 24; i >= 0; i--) {
			if (values[i] == 0) {
				System.out.printf("%8s", "--");
			} else {
				if ((i + 1) == yourCase) {
					System.out.printf("%8s", "**");
				} else {
					System.out.printf("%8s", (i + 1));
				}
			}
			
			if (i % 5 == 0) {
				System.out.println();
			}
		}
	}
	
	
	
	public static void showValuesLeft(int[] cases) {
		
		
		int[] values = createValueArray();
		
		for (int i = 0; i < values.length; i++) {
			boolean valueExists = false; 
			if (i % 5 == 0) {
				System.out.println(); 
			}
			for (int j = 0; j < values.length; j++) {
				
				if (values[i] == cases[j]) {
					System.out.printf("%15s", values[i]);
					valueExists = true;
				}
			}
			if (!valueExists) {
				System.out.printf("%15s", "----");
			}
		}
		System.out.println();
	}
	
	
	public static int[] createValueArray() {
		
		int[] values = new int[25];
		values[0] = 1;
		values[1] = 5;
		values[2] = 10;
		values[3] = 25;
		values[4] = 50;
		values[5] = 75;
		values[6] = 100;
		values[7] = 200;
		values[8] = 300;
		values[9] = 400;
		values[10] = 500;
		values[11] = 750;
		values[12] = 1000;
		values[13] = 5000;
		values[14] = 10000;
		values[15] = 25000;
		values[16] = 50000;
		values[17] = 75000;
		values[18] = 100000;
		values[19] = 200000;
		values[20] = 300000;
		values[21] = 400000;
		values[22] = 500000;
		values[23] = 750000;
		values[24] = 1000000;
		
		return values;
	}
	
	
	public static int[] assignCaseValues() {
		
		int[] values = createValueArray();

		int[] cases = new int[25];
		for (int row = 0; row < cases.length; row++) {
				
			int r = (int) (Math.random() * 25); 
			while (values[r] == 0) {
				r = (int) (Math.random() * 25); 
			}
			cases[row] = values[r];
			values[r] = 0; 
			}
		return cases; 
	}
	
	
	
	public static int findLastCase(int[] values, int yourCase) {
		
		int lastCase = 0;
		for (int i = 0; i < values.length; i++) {
			if (values[i] != 0 && (i + 1) != yourCase) {
				lastCase = i + 1; 
				break;
			}
		}
		
		return lastCase; 
	}
	
}

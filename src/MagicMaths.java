import java.util.Scanner;
public class MagicMaths {
	private static int MAX_BOUND = 32;
	private static int number = 0;
	public static void main(String[] args) {
		System.out.println("Pick a number between 1-"+	(MAX_BOUND-1) + ". Don't reveal it.");
		Scanner input = new Scanner(System.in);
		for(int i = 0; i < Math.log10(MAX_BOUND)/Math.log10(2); i++) {
			int count = 0;
			System.out.println("Is your number in this following list:");
			for (int j = 1; j < MAX_BOUND; j++) {
				if(((j >> i ) & 1) == 1) {
					System.out.print(j + " ");
					if(Math.log10(j) < (int) Math.log10(MAX_BOUND)) {
						System.out.print(" ");
					}
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
		System.out.println("Your number is " + number+".");
	}
}

import java.util.Scanner;
public class Collatz_Conjecture_Proof {
	/**
	 * number the user enters 
	 */
	private static long NUMBER_ENTERED;
	
	/**
	 * intro!
	 * @param args if they enter as a flag!
	 */
	static String input_num;
	public static void main(String[] args) {
		Scanner input = null;
		System.out.println("Suppose F is a function defined by\nF(n) = n/2 if n%2 == 0, else (3n-1)/2.");
		if(args.length == 0) {
			System.out.println("If you think this has an ending number always, try your luck and enter a number");
			input = new Scanner(System.in);
			input_num = input.next();
			
		} else {
			input_num = args[0];
		}
		while(true) {
			try {
				NUMBER_ENTERED = new Long(input_num);
				if(NUMBER_ENTERED > 0) {
					break;
				}
				throw new Exception();
			}
			catch (Exception e) {
				System.out.println("You didn't enter a valid number. Try again.");
				input_num = input.next();
			}
		}
		input.close();
		System.out.println("Let's start");
		collatz_ing();
		
	}
	
	public static void collatz_ing() {
		System.out.println(NUMBER_ENTERED);
		if(NUMBER_ENTERED == 1) {
			System.out.println("Finished!");
			return;
		} else {
			if(NUMBER_ENTERED % 2 == 0) {
				NUMBER_ENTERED /= 2;
			} else {
				NUMBER_ENTERED *= 3;
				NUMBER_ENTERED -= -1;
				NUMBER_ENTERED /= 2;
			}
		}
		collatz_ing();
	}
}

package com.ekvip.oop;

import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

public class RunnerApplication {

	// Stack to maintain history for undo operation
	private static final Deque<Integer> history = new ArrayDeque<>();

	// operation instances
	private static final IncrementValue incrementOp = new IncrementValue();
	private static final DecrementValue decrementOp = new DecrementValue();
	private static final DoubleValue doubleOp = new DoubleValue();
	private static final RandAddValue randAddOp = new RandAddValue();
	private static final UndoOperation undoOp = new UndoOperation();

	public static void main(String[] args) {

		// since we are taking infinite command, hence not closing the scanner which
		// might cause memory leak.
		Scanner scanner = new Scanner(System.in);
		int currentValue = 0;

		// Welcome message
		System.out.println("Welcome to the OOP Runner Application! \nPlease enter the intial value of your choice : ");

		// Simplified initial value input: keep prompting until a valid integer is entered
		while (!scanner.hasNextInt()) {
			System.out.println("Invalid input. Please enter a valid integer.");
			scanner.next(); // Clear invalid token
		}
		int initialValue = scanner.nextInt();
		currentValue = initialValue;
		System.out.println("You have entered: " + initialValue);

		// choose your command
		System.out.println(
				"Please choose your command: \n1. Increment \n2. Decrement \n3. Double \n4. randadd \n5. undo");

		int command = 0;

		// infinite command loop
		while (true) {

			if (scanner.hasNextInt()) {

				command = scanner.nextInt();

				if (command >= 1 && command <= 5) {
					// Push current value before performing any operation except undo
					if (command != 5) {
						history.push(currentValue);
					}
					currentValue = performOperation(command, currentValue);
					System.out.println("Current value: " + currentValue);
					System.out.println(
							"\nPlease choose your next command: \n");
				} else {
					System.out.println("Invalid command. Please enter a number between 1 and 5.");
					// Do not consume next token here; just reprint the prompt
					System.out.println("\nPlease choose your next command: \n");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid integer.");
				scanner.next();
			}
		}

	}

	private static int performOperation(int command, int value) {

		int newValue = value;

		switch (command) {
		case 1:
			System.out.println("Result before increment: " + value);
			newValue = incrementOp.incrementValue(value);
			System.out.println("Result after increment: " + newValue);
			break;
		case 2:
			System.out.println("Result before decrement: " + value);
			newValue = decrementOp.decrementValue(value);
			System.out.println("Result after decrement: " + newValue);
			break;
		case 3:
			System.out.println("Result before double: " + value);
			newValue = doubleOp.doubleValue(value);
			System.out.println("Result after double: " + newValue);
			break;
		case 4:
			System.out.println("Result before adding random value: " + value);
			newValue = randAddOp.randAddValue(value);
			System.out.println("Result after adding random value:: " + newValue);
			break;
		case 5:
			newValue = undoOp.undoOperation(value, history);
			break;
		default:
			break;
		}

		return newValue;

	}
}
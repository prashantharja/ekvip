package com.ekvip.oop;

import java.util.Deque;

public class UndoOperation {

	int undoOperation(int value, Deque<Integer> history) {
		
		if (!history.isEmpty()) {
			int previousValue = history.pop();
			System.out.println("Undoing last operation. Restored value: " + previousValue);
			return previousValue;
		} else {
			System.out.println("No operations to undo. Value remains: " + value);
			return value;
		}
		
	}
}

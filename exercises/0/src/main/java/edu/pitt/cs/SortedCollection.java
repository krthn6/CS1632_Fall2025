package edu.pitt.cs;

//TODO: Import libraries as needed
import java.util.ArrayList;
import java.util.NoSuchElementException;


public class SortedCollection {
	// TODO: Add member variables or methods as needed
	private ArrayList<Integer> lst;
	public SortedCollection(){
		lst = new ArrayList<>();
	}

	/**
	 * Adds the number n to the collection.
	 * 
	 * @param n the number to add to the collection
	 * @return always returns true
	 */
	public boolean add(int n) {
		int idx = 0;
		while(idx < lst.size() && lst.get(idx) <n){
			idx ++;
		}
		lst.add(idx,n);
		return true;
	}

	/**
	 * Removes the smallest number in the collection and returns it.
	 * If the collection is empty, throws a NoSuchElementException.
	 * 
	 * @return the smallest number in the collection
	 */
	public int remove() throws NoSuchElementException {
		if (lst.isEmpty()) {
            throw new NoSuchElementException();
        }
		return lst.remove(0);
	}

	/**
	 * Prints usage information.
	 */
	public static void showUsage() {
		System.out.println("Usage: java SortedCollection [num1] [num2] [num3] ...");
	}

	/**
	 * Main method. Receives a list of numbers as commandline arguments and prints
	 * out the list in sorted order from smallest to largest.
	 * 
	 * @param args commandline arguments; see showUsage() for detailed information
	 */
	public static void main(String[] args) {
		SortedCollection collection = new SortedCollection();
		if (args.length == 0) {
			showUsage();
			return;
		}
	
        for (String arg : args) {
            try {
                int num = Integer.parseInt(arg);
                collection.add(num);
            } catch (NumberFormatException e) {
                showUsage();
                return;
            }
        }

        System.out.print("sorted: ");
        while (true) {
            try {
                int num = collection.remove();
                System.out.print(num + " ");
            } catch (NoSuchElementException e) {
                break;
            }
        }
        System.out.println();
    }
}
		// TODO: add numbers in commandline arguments to collection using the add(int) method.
		// If any commandline argument is not a number, call showUsage() and return.
		
	

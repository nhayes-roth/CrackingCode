import java.util.*;

public class ArraysAndStrings {
	/* 1.1
	 * (a) Implement an algorithm to determine if a string has all 
	 * unique characters.
	 * (b) What if you can not use additional data structures?
	 */
	public static Boolean noRepeats(String str){
		HashMap<Character, Boolean> table = new HashMap<Character, Boolean>();
		for (int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			try {
				if (table.get(c)){
					return false;
				}
			} catch (NullPointerException e) {
				// ignore null pointer exceptions
			} table.put(c, true);
		}
		return true;
	}

	public static Boolean noRepeatsNoAdditionalStructures(String str){
		for (int i = 0; i < str.length(); i++){
			for (int j = i+1; j < str.length(); j++){
				if (str.charAt(i) == str.charAt(j)){
					return false;
				}
			}
		}
		return true;
	}

	public static void testNoRepeats(){
		String[] to_test = {"", "abcdeef", "abcdefghijklmnopqrstuvwxyz"};
		for (String str : to_test){
			System.out.println(noRepeats(str) + "\t" + noRepeatsNoAdditionalStructures(str));
		}
	}

	/* 1.2
	 * Write code to reverse a C-Style String (C-String means that “abcd” is 
	 * represented as five characters, including the null character )
	 */
	public static String reverse(String str){
		// base case
		if (str.length() <= 1) {
			return str;
		}
		// recursive call
		return str.charAt(str.length()-1) + reverse(str.substring(1, str.length()-1)) + str.charAt(0);
	}

	public static void testReverse(){
		String [] to_test = {"",
							 "a",
							 "aa",
							 "abcdefghijklmnopqrstuvwxyz"};
		for (String str : to_test) {
			System.out.println(reverse(str));
		}
	}

	/* 1.3
	 * Design an algorithm and write code to remove the duplicate characters 
	 * in a string without using any additional buffer.
	 * NOTE: One or two additional variables are fine. 
	 * 		 An extra copy of the array is not.
	 */
	public static String removeDuplicateCharacters(String str){
		for (int i = 0; i < str.length(); i++){
			for (int j = i + 1; j < str.length(); j++){
				if (str.charAt(i) == str.charAt(j)){
					str = str.substring(0, j) + str.substring(j+1);
				}
			}
		}
		return str;
	}

	public static void testRemoveDuplicates(){
		String [] to_test = {"", "a", "abbaccd", "abcdeff"};
		for (String str : to_test) {
			System.out.println(removeDuplicateCharacters(str));
		}
	}

	/* main method */
	public static void main(String[] args) {
		// 1.1
		// testNoRepeats();
		// 1.2
		// testReverse();
		// 1.3
		testRemoveDuplicates();
	}
}
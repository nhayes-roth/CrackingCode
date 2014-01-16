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

	public static void main(String[] args) {
		// 1.1
		testNoRepeats();
	}
}
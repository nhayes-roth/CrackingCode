import java.util.*;

class SortSearch {

	int BUFFER_VAL = 0;

	// 9.1
	// given two sorted arrays A and B
	// A has a large enough buffer at the end to hold B
		// - assume buffer is filled BUFFER_VAL
	// write a method to merge B into A in sorted order
	public static void sortIntoA(int[] A, int[] B){

		int A_index = firstIndex(A, 0) - 1;	// last real value of A
		int B_index = B.length - 1;			// last real value of B
		int index = A.length - 1;			// index of A to fill
		

		// step backwards from the end of both, filling A from the back
		while(A_index >= 0 && B_index >= 0){
			if (A[A_index] > B[B_index]){
				A[index] = A[A_index];
				A_index--;
			} else {
				A[index] = B[A_index];
				B_index--;
			}
			index--;
		}
		if (A_index >= 0){
			while (index >= 0){
				A[index] = A[A_index];
				index--;
				A_index--;
			}
		} else {
			while (index >= 0){
				A[index] = B[B_index];
				index--;
				B_index--;
			}

		}
	}

	private static int firstIndex(int[] A, int val){
		for (int i=0; i<A.length; i++){
			if (A[i]==val){
				return i;
			}
		}
		return -1;
	}

	
	private static String toString(int[] arr){
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++)
			sb.append(arr[i] + " ");
		return sb.toString();
	}

	private static void test1(){
		int[] A = {1, 3, 5, 0, 0, 0};
		int[] B = {2, 4, 6};
		sortIntoA(A, B);
		System.out.println(toString(A));
	}

	// 9.2
	// write a method to sort an array of strings
	// so that all the anagrams are next to each other
	private static void sortStringArray(String[] arr){
		// sort each individual string
		arr = sortEach(arr);
		// sort the array based on string comparison
		arr = Arrays.sort(arr);
	}

	private static String[] sortEach(String[] arr){
		for (int i = 0; i < arr.length; i++){
			char[] str = arr[i].toCharArray();
			arr[i] = new String(quicksort(str));
		}
		return null;
	}

	// quicksort
	private static char[] quicksort(char[] arr){
		// measure length and choose a pivot in the middle
		int length = arr.length;
		int pivot = length/2;

		// base case
		if (length <= 1){
			return arr;
		}

		// move inwards from left/right until
		// - all smaller values are on the left
		// - all larger values are on the right
		int l = 0;
		int r = length-1;
		while (l <= pivot && r >= pivot){
			if (arr[l] > arr[r]){
				char tmp = arr[r];
				arr[r] = arr[l];
				arr[l] = tmp;
				r--;
			} else {
				l++;
			}
		}
		// recurse
		char[] left = quicksort(Arrays.copyOfRange(arr, 0, pivot));
		char[] right = quicksort(Arrays.copyOfRange(arr, pivot, length));
		char[] to_return = new char[length];
		copyInto(to_return, left, 0);
		copyInto(to_return, right, pivot);
		return to_return;
	}

	private static void copyInto(char[] target, char[] source, int index){
		for (int i=0; i<source.length; i++){
			target[index+i] = source[i];
		}
	}

	private static void test2(){
		String[] arr = {"zyx", "srq", "jea", "zyx", "srq", "jea", "zyx", "srq", "jea"};
		sortStringArray(arr);
		for (int i=0; i<arr.length; i++){
			System.out.print((String)arr[i] + " ");
		}
	}

	// 9.3 given a sorted array of n integers that has been
	// rotated an unknown number of times, 
	// 	- give an O(log(n)) argorithm that finds an element
	// 	in the array. 
	// 	- you may assume the array was originally sorted 
	// 	in increasing order
	private int findInRotated(int[] rotated, int target){
		int left = 0;
		int right = rotated.length - 1;
		int middle;
		while (left<=right){
			middle = (left + right)/2;
			if (rotated[middle] == target){
				return middle;
			}

			// break within [left, middle]
			if (rotated[left] <= rotated[middle]){
				if (target > rotated[middle]){
					left = middle + 1;
				} else {
					left = middle + 1;
				}
			}
			// sorted within [left, middle]
			} else {
				// target is on right of middle
				if (target < rotated[middle]){
					right = middle - 1;
				// target is between left and middle
				} else if (target < rotated[right]){
					left = middle + 1 ;
				}
			}
		}
		return -1;
	}

	/* main */
	public static void main(String[] args){
		// test1();
		test2();
	}
}
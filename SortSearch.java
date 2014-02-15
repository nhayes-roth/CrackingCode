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

	/* main */
	public static void main(String[] args){
		int[] A = {1, 3, 5, 0, 0, 0};
		int[] B = {2, 4, 6};
		sortIntoA(A, B);
		System.out.println(toString(A));
	}
}
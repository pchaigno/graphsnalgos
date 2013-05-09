package main;

import java.util.HashSet;
import java.util.Set;

public class Tools {

	/**
	 * @param matrix The matrix.
	 * @return A string representation of an integer matrix.
	 */
	public static String matrixToString(int[][] matrix) {
		String str = "";
		int length_matrix = matrix.length;
		int length_vector;
		for(int i=0 ; i<length_matrix ; i++) {
			length_vector = matrix[i].length;
			for(int j=0 ; j<length_vector ; j++) {
				if(matrix[i][j]>=0) {
					str += " ";
				}
				if(matrix[i][j]==Integer.MAX_VALUE || matrix[i][j]==Integer.MIN_VALUE) {
					str += "- ";
				} else {
					str += matrix[i][j]+" ";
				}
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * @param matrix The matrix.
	 * @return A string representation of an integer matrix.
	 */
	public static String matrixToString(double[][] matrix) {
		String str = "";
		int length_matrix = matrix.length;
		int length_vector;
		for(int i=0 ; i<length_matrix ; i++) {
			length_vector = matrix[i].length;
			for(int j=0 ; j<length_vector ; j++) {
				if(matrix[i][j]>=0) {
					str += " ";
				}
				if(matrix[i][j]==Double.MAX_VALUE || matrix[i][j]==Double.MIN_VALUE) {
					str += "- ";
				} else {
					str += matrix[i][j]+" ";
				}
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * Build a copy of a set of integers.
	 * @param set The set to clone.
	 * @return The cloned set.
	 */
	public static Set<Integer> clone(Set<Integer> set) {
		Set<Integer> result = new HashSet<Integer>();
		result.addAll(set);
		return result;
	}
}

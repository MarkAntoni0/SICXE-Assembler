import java.io.File;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;

public class SupportingFunctions {
	// ana men 34an el daragaaaat
	public static void printNameID() {
		System.out.println("****************************");
		System.out.println("Name: Mark Tharwat Samir");
		System.out.println("ID: 19200164");
		System.out.println("****************************");
	}

	// line break kber kda
	public static void lineBreak() {
		System.out.println("***************************" + "*****************************");
	}

	// printing any 2d array
	public static void print2D(String mat[][]) {
		// Loop through all rows
		for (int i = 0; i < mat.length; i++) {

			// Loop through all elements of current row
			for (int j = 0; j < mat[i].length; j++) {
				// System.out.print(mat[i][j] + ", ");
				System.out.print(StringUtils.leftPad("" + mat[i][j], 16, " ") + ",");

			}
			System.out.println(" ");
		}
	}

	// printing the size of any 2d matrix
	public static void analyzeMatrix(String mat[][]) {
		int lengthOne = mat.length;
		int lengthTwo = mat[0].length;
		System.out.print(lengthOne + " " + lengthTwo);
	}

	// getting rows count in 2d matrix
	public static int analyzeMatrixGetRows(String mat[][]) {
		return mat.length;
	}

	// getting columns count in 2d matrix
	public static int analyzeMatrixGetColumns(String mat[][]) {
		return mat[0].length;
	}

	// linear search
	public static boolean linearSearch(String[] array, String key) {
		for (int y = 0; y < array.length; y++) {
			if (array[y].equals(key)) {
				return true;
			}
		}
		return false;
	}

	public static int linearSearchWithKey(String arr[][], String string) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][0].equals(string)) {
				return i;
			}
		}
		return -1;
	}

	public static int binaryToDecimal(long binary) {

		// variable to store the converted
		// binary number
		int decimalNumber = 0, i = 0;

		// loop to extract the digits of the binary
		while (binary > 0) {

			// extracting the digits by getting
			// remainder on dividing by 10 and
			// multiplying by increasing integral
			// powers of 2
			decimalNumber += Math.pow(2, i++) * (binary % 10);

			// updating the binary by eliminating
			// the last digit on division by 10
			binary /= 10;
		}

		// returning the decimal number
		return decimalNumber;
	}

	// method to convert decimal to hexadecimal
	public static String decimalToHex(long binary) {
		// variable to store the output of the
		// binaryToDecimal() method
		int decimalNumber = binaryToDecimal(binary);

		// converting the integer to the desired
		// hex string using toHexString() method
		String hexNumber = Integer.toHexString(decimalNumber);

		// converting the string to uppercase
		// for uniformity
		hexNumber = hexNumber.toUpperCase();

		// returning the final hex string
		return hexNumber;
	}

	public static String hexToBinary(String hex) {

		// variable to store the converted
		// Binary Sequence
		String binary = "";

		// converting the accepted Hexadecimal
		// string to upper case
		hex = hex.toUpperCase();

		// initializing the HashMap class
		HashMap<Character, String> hashMap = new HashMap<Character, String>();

		// storing the key value pairs
		hashMap.put('0', "0000");
		hashMap.put('1', "0001");
		hashMap.put('2', "0010");
		hashMap.put('3', "0011");
		hashMap.put('4', "0100");
		hashMap.put('5', "0101");
		hashMap.put('6', "0110");
		hashMap.put('7', "0111");
		hashMap.put('8', "1000");
		hashMap.put('9', "1001");
		hashMap.put('A', "1010");
		hashMap.put('B', "1011");
		hashMap.put('C', "1100");
		hashMap.put('D', "1101");
		hashMap.put('E', "1110");
		hashMap.put('F', "1111");

		int i;
		char ch;

		// loop to iterate through the length
		// of the Hexadecimal String
		for (i = 0; i < hex.length(); i++) {
			// extracting each character
			ch = hex.charAt(i);

			// checking if the character is
			// present in the keys
			if (hashMap.containsKey(ch))

				// adding to the Binary Sequence
				// the corresponding value of
				// the key
				binary += hashMap.get(ch);

			// returning Invalid Hexadecimal
			// String if the character is
			// not present in the keys
			else {
				binary = "Invalid Hexadecimal String";
				return binary;
			}
		}

		// returning the converted Binary
		return binary;
	}

	public static String StringManipulator(String receivedValue, int segments) {
		String str = receivedValue;

		// Stores the length of the string
		int len = str.length();
		// n determines the variable that divide the string in 'n' equal parts
		int n = segments;
		int temp = 0, chars = len / n;

		// Stores the array of string
		String[] equalStr = new String[n];

		// Check whether a string can be divided into n equal parts
		if (len % n != 0) {
			System.out.println("Sorry this string cannot be divided into " + n + " equal parts.");
		} else {
			for (int i = 0; i < len; i = i + chars) {
				// Dividing string in n equal part using substring()
				String part = str.substring(i, i + chars);
				equalStr[temp] = part;
				temp++;
			}

		}
		
		
		String returnValue = "";
		for (int i = 0; i < equalStr.length; i++) {
			String temp2 = String.valueOf(Integer.parseInt(equalStr[i]));
			int decimal=Integer.parseInt(temp2,2);
			String hexa2 = Integer.toHexString(decimal);  

			
			
			returnValue+=hexa2;
		}

		return returnValue;

	}
}

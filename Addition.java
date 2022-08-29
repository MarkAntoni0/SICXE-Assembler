import java.math.BigInteger;

//This class performs the hexadecimal addition 
public class Addition {
	// This function translated the characters into the corresponding values
	public static int decimalValue(char x) {
		if (x == 'A' || x == 'a') {
			return 10;
		}
		if (x == 'B' || x == 'b') {
			return 11;
		}
		if (x == 'C' || x == 'c') {
			return 12;
		}
		if (x == 'D' || x == 'd') {
			return 13;
		}
		if (x == 'E' || x == 'e') {
			return 14;
		}
		if (x == 'F' || x == 'f') {
			return 15;
		}

		return x - '0';
	}

	// Accepting two hexadecimal numbers and perform addition
	public static String addHexadecimal(String a, String b) {
		int n = a.length();
		int m = b.length();
		int i = n - 1;
		int j = m - 1;
		int temp = 0;
		int carry = 0;
		String result = "";

		char[] hexaValue = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

		while (i >= 0 || j >= 0) {
			if (i >= 0 && j >= 0) {

				temp = decimalValue(a.charAt(i)) + decimalValue(b.charAt(j)) + carry;

				i--;
				j--;
			} else if (i >= 0) {
				temp = decimalValue(a.charAt(i)) + carry;
				i--;
			} else {
				temp = decimalValue(b.charAt(j)) + carry;
				j--;
			}

			result = hexaValue[(temp % 16)] + result;

			carry = temp / 16;
		}

		if (carry != 0) {
			result = hexaValue[carry] + result;
		}

		return result;

	}

}
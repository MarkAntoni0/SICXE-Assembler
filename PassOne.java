import java.math.BigInteger;

public class PassOne {

	public static String[][] calculateLocation(String code[][]) {
		System.out.println("[System] Pass One begins");
		String startingAddress = code[0][3];
		System.out.println("[System] Starting Address is: " + startingAddress);

		String[] format1 = new String[] { "FIX", "FLOAT", "HIO", "NORM", "SIO", "TIO" };
		String[] format2 = new String[] { "ADDR", "CLEAR", "COMPR", "DIVR", "MULR", "RMO", "SHIFTL", "SHIFTR", "SUBR",
				"SVC", "TIXR" };

		// creating a new matrix that holds the format in column 4 and having empty
		// column 5 for object code
		String[][] newCode = new String[SupportingFunctions
				.analyzeMatrixGetRows(code)][SupportingFunctions.analyzeMatrixGetColumns(code) + 2];

		// copying code to newCode (The new matrix that has the format type column)
		for (int j = 0; j < SupportingFunctions.analyzeMatrixGetRows(code); j++) {
			for (int k = 0; k < SupportingFunctions.analyzeMatrixGetColumns(code); k++) {
				newCode[j][k] = code[j][k];
			}
		}

		// finding the format type for the entire code provided
		for (int i = 0; i < SupportingFunctions.analyzeMatrixGetRows(newCode); i++) {

			// format 0 is the start
			if (newCode[i][2].equals("START")) {
				System.out.println("[System] START  found at: " + i);
				newCode[i][0] = startingAddress;
				newCode[i][5] = "0";
			} else if ((SupportingFunctions.linearSearch(RegisterNames.strAr1, newCode[i][2]))) {
				System.out.println("[System] Register  found at: " + i);
				newCode[i][5] = "0";

			} else if (newCode[i][2].equals("END")) {
				System.out.println("[System] END  found at: " + i);
				newCode[i][5] = "0";

				// format 4 starts with +
			} else if (newCode[i][2].charAt(0) == '+') {
				System.out.println("[System] format 4 found at: " + i);
				newCode[i][5] = "4";

				// RESW calculations
			} else if (newCode[i][2].equals("RESW")) {
				int count = 3 * (int) (Float.parseFloat(newCode[i][3]));
				newCode[i][5] = Integer.toHexString(count);

				// BYTE calculations
			} else if (newCode[i][2].equals("BYTE")) {
				String objectCode = "";
				if(newCode[i][3].charAt(0)=='C') {
					String lettersWritten = newCode[i][3].substring(1, newCode[i][3].length() - 1);
					
					char[] ch = new char[lettersWritten.length()];
					
					
					
					
					
					
					////////////////////// ahoooooooooooooooooooooooooooooooo
					newCode[i][5] = String.valueOf(lettersWritten.length());		
					
					
					
					
					
					
					for (int j = 0; j < lettersWritten.length(); j++) {
			            ch[i] = lettersWritten.charAt(j);
			            objectCode += (int)ch[i];
			        }
					
				}else if(newCode[i][3].charAt(0)=='X') {
					//convert the value to hex 
					
					
					
					
					
					
					
					//ahooooooooooooooooooooooooooooooooooooooooo
					objectCode += SupportingFunctions.decimalToHex(Long.parseLong(newCode[i][3].substring(1, newCode[i][3].length() - 1)));
					int hexLength = newCode[i][3].substring(1, newCode[i][3].length() - 1).length();
					newCode[i][5] = String.valueOf(hexLength/2);
					
					
					
					
					
					
					
					
					
					
					
				}
				
				
				newCode[i][6] = objectCode;
				
				
				
				
				
				
				

			} else if (newCode[i][2].equals("RESB")) {
				int count = (int) (Float.parseFloat(newCode[i][3]));
				newCode[i][5] = Integer.toHexString(count);

			} else if (newCode[i][2].equals("WORD")) {				
					newCode[i][5] = "3";
				

			}

			// To this point it isn't (START, Format 4, RESW, BYTE, RESB, WORD) - which
			// means it's Format 1, 2, or 3
			// We will check for Format 1 and 2 and if neither then it's format 3
			else if ((SupportingFunctions.linearSearch(format1, newCode[i][2]))) {
				System.out.println("[System] format 1 found at: " + i);
				newCode[i][5] = "1";
			} else if ((SupportingFunctions.linearSearch(format2, newCode[i][2]))) {
				System.out.println("[System] format 2 found at: " + i);
				newCode[i][5] = "2";
			} else { // this is format 3
				System.out.println("[System] format 3 found at: " + i);
				newCode[i][5] = "3";
			}
		}

		SupportingFunctions.lineBreak();

		// Location counting process
		for (int w = 1; w < newCode.length; w++) {
			newCode[w][0] = Addition.addHexadecimal(newCode[w - 1][0], newCode[w - 1][5]);
		}

		SupportingFunctions.print2D(newCode);
		return newCode;
	}

}

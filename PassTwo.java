import java.math.BigInteger;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

public class PassTwo {
	public static String[][] passTwo(String newCode[][], String instructionSet[][], Map<String, String> symbolTable) {
		SupportingFunctions.lineBreak();

		for (int i = 0; i < newCode.length; i++) {
			System.out.println("");
			System.out.println("[System] Pass Two Itration number: " + i);

			int locationOfMnemonic = -1; // The location in the instruction set
			boolean indexed = false;
			boolean immediate = false;
			boolean indirect = false;
			String OPCODE_Modified;
			String pc = null;
			String base = null;
			RegisterNames.BASE = symbolTable.get("TABLE2");

			switch (newCode[i][5]) {
			case "0":
				if (newCode[i][2].equals("BASE")) {

					System.out.println("The value of base is " + symbolTable.get("TABLE2"));
				}
				break;
			case "1":
				// Searching instruction set sheet for the mnemonic
				locationOfMnemonic = SupportingFunctions.linearSearchWithKey(instructionSet, newCode[i][2]);

				// properties of instruction
				if (newCode[i][3].contains(",")) {
					indexed = true;
				} else if (newCode[i][3].contains("#")) {
					immediate = true;
				} else if (newCode[i][3].contains("@")) {
					indirect = true;
				}

				System.out.println("-The location in instruction set is " + locationOfMnemonic);
				System.out.println("-OPCODE HEX: " + instructionSet[locationOfMnemonic][1].toString());
				System.out.println("-Indexed? " + indexed);

				OPCODE_Modified = instructionSet[locationOfMnemonic][1].toString();
				if (OPCODE_Modified.contains(".")) { // Removing the floating point in case it was added by excel
					OPCODE_Modified = OPCODE_Modified.substring(0, OPCODE_Modified.length() - 2);
				}
				newCode[i][6] = createFormatOne(OPCODE_Modified);

				break;
			case "2":

				// Searching instruction set sheet for the mnemonic
				locationOfMnemonic = SupportingFunctions.linearSearchWithKey(instructionSet, newCode[i][2]);

				// properties of instruction
				if (newCode[i][3].contains(",")) {
					indexed = true;
				} else if (newCode[i][3].contains("#")) {
					immediate = true;
				} else if (newCode[i][3].contains("@")) {
					indirect = true;
				}

				System.out.println("-The location in instruction set is " + locationOfMnemonic);
				System.out.println("-OPCODE HEX: " + instructionSet[locationOfMnemonic][1].toString());
				System.out.println("-Indexed? " + indexed);

				OPCODE_Modified = instructionSet[locationOfMnemonic][1].toString();
				if (OPCODE_Modified.contains(".")) { // Removing the floating point in case it was added by excel
					OPCODE_Modified = OPCODE_Modified.substring(0, OPCODE_Modified.length() - 2);
				}
				newCode[i][6] = createFormatTwo(OPCODE_Modified);

				break;
			case "3":
				if (newCode[i][2].equals("RESW") || newCode[i][2].equals("RESB")) { // Avoiding conflict with 3 that
																					// doesn't mean F#
					break;
				}
				// Searching instruction set sheet for the mnemonic
				locationOfMnemonic = SupportingFunctions.linearSearchWithKey(instructionSet, newCode[i][2]);
				boolean isWord = false;
				String wordValue = null;
				// properties of instruction
				if (newCode[i][3].contains(",")) {
					indexed = true;
				} else if (newCode[i][2].contains("#")) {
					immediate = true;
				} else if (newCode[i][2].contains("@")) {
					indirect = true;
				} else if (newCode[i][2].equals("WORD")) {
					isWord = true;
					wordValue = newCode[i][3];
				}

				System.out.println("-The location in instruction set is " + locationOfMnemonic);
				System.out.println("-OPCODE HEX: " + instructionSet[locationOfMnemonic][1].toString());
				System.out.println("-Indexed? " + indexed);

				OPCODE_Modified = instructionSet[locationOfMnemonic][1].toString(); // using the opcode that was found
																					// based on the location
				if (OPCODE_Modified.contains(".")) { // Removing the floating point in case it was added by excel
					OPCODE_Modified = OPCODE_Modified.substring(0, OPCODE_Modified.length() - 2);
				}
				newCode[i][6] = createFormatThree(OPCODE_Modified, indirect, immediate, indexed, newCode[i + 1][0],
						RegisterNames.BASE, newCode[i][3], symbolTable, isWord, wordValue);
				break;
			case "4":
				locationOfMnemonic = SupportingFunctions.linearSearchWithKey(instructionSet,
						newCode[i][2].substring(1));
				// properties of instruction
				if (newCode[i][3].contains(",")) {
					indexed = true;
				} else if (newCode[i][2].contains("#")) {
					immediate = true;
				} else if (newCode[i][2].contains("@")) {
					indirect = true;
				}

				System.out.println("-The location in instruction set is " + locationOfMnemonic);
				System.out.println("-OPCODE HEX: " + instructionSet[locationOfMnemonic][1].toString());
				System.out.println("-Indexed? " + indexed);

				OPCODE_Modified = instructionSet[locationOfMnemonic][1].toString(); // using the opcode that was found
																					// based on the location
				if (OPCODE_Modified.contains(".")) { // Removing the floating point in case it was added by excel
					OPCODE_Modified = OPCODE_Modified.substring(0, OPCODE_Modified.length() - 2);
				}
				newCode[i][6] = createFormatFour(OPCODE_Modified, indirect, immediate, indexed, newCode[i][3],
						symbolTable);

				break;

			default:
			}

			//was written for Aya quiz 
			if (!newCode[i][4].isEmpty()) {
				// find the words and place them in an array
				String objectCode = "";
				String[] split = newCode[i][3].split(",", -1);
				for (int r = 0; r < split.length; r++) {
					int valueFound = Integer.parseInt(split[r]);
					String binaryFound = Integer.toBinaryString(valueFound);
					String modifiedBinaryFound = StringUtils.leftPad("" + binaryFound, 24, "0");
					objectCode = objectCode.concat(modifiedBinaryFound);
				}

				newCode[i][6] = objectCode;
			}
			
			//All four cases handled at this point 
			//Now we convert to HEX OBJECT CODE 
			if(newCode[i][6]!=null) {
				//newCode[i][4] = String.valueOf(Integer.toHexString(Integer.parseInt(newCode[i][6])));
				//newCode[i][4] = SupportingFunctions.decimalToHex(Long.parseLong(newCode[i][6]));
				//String hexa = Double.toHexString(Double.parseDouble(newCode[i][6]));
				//System.out.println("the hex value is: "+hexa);
				if(newCode[i][5].equals("3")) {
					newCode[i][4] = SupportingFunctions.StringManipulator(newCode[i][6], 6);
				}else if(newCode[i][5].equals("4")) {
					newCode[i][4] = SupportingFunctions.StringManipulator(newCode[i][6], 8);
				}


				
			}
		}

		return newCode;
	}

	static String createFormatOne(String OPCODE) {
		return SupportingFunctions.hexToBinary(OPCODE);
	}

	static String createFormatTwo(String OPCODE) {
		return SupportingFunctions.hexToBinary(OPCODE) + "00000000";
	}

	static String createFormatThree(String OPCODE, boolean indirect, boolean immediate, boolean indexed, String pc,
			String base, String targetAddress, Map<String, String> symbolTable, boolean isWord, String wordValue) {

		// Handling is word case
		if (isWord) {
			int objectCode = Integer.parseInt(wordValue);

			String Temp = String.valueOf(Integer.toBinaryString(objectCode));
			return org.apache.commons.lang.StringUtils.leftPad(Temp, 24, "0");

		}

		// OPCODE (6)
		String opcodeFull = SupportingFunctions.hexToBinary(OPCODE);
		opcodeFull = opcodeFull.substring(0, opcodeFull.length() - 2); // removing the last 2 binary elements
		System.out.println("OPCODE(6): " + opcodeFull);

		// NIXBPE(6)
		String NIXBPE = "000000";
		char[] nixbpe = NIXBPE.toCharArray();

		if (indirect) {
			nixbpe[0] = '1';
		} else if (immediate) {
			nixbpe[1] = '1';
		} else {
			nixbpe[0] = '1';
			nixbpe[1] = '1';
		}

		if (indexed) {
			nixbpe[2] = '1';
		}
		System.out.println("NIXBPE(6): " + String.valueOf(nixbpe));

		// DISP(12) TA = Disp + (PC or BASE) Disp = TA - PC
		String Disp = null;
		String ModifiedDisp = null;
		// Finding the TA value
		String TARGETADDRESS = null;
		if (targetAddress.contains("#") || targetAddress.contains("@")) {
			ModifiedDisp = targetAddress.substring(1);
		} else if (targetAddress.contains(",")) {
			TARGETADDRESS = targetAddress.substring(0, targetAddress.length() - 2);
		} else {
			TARGETADDRESS = targetAddress;
		}

		TARGETADDRESS = symbolTable.get(TARGETADDRESS); // now it holds the real hex value
		System.out.println("TA is: " + TARGETADDRESS);

		// converting to decimals
		int PC = Integer.parseInt(pc, 16);
		int BASE = Integer.parseInt(base, 16);

		if (TARGETADDRESS == null) {
			Disp = "000000000000";
		} else {
			int TARGETADDRESS2 = Integer.parseInt(TARGETADDRESS, 16); // conversion to decimal
			if ((TARGETADDRESS2 - PC) > -2048 && (TARGETADDRESS2 - PC) < 2047) {
				nixbpe[4] = '1';
				int temp = TARGETADDRESS2 - PC;
				Disp = Integer.toBinaryString(temp); // in decimal
				ModifiedDisp = org.apache.commons.lang.StringUtils.leftPad(Disp, 12, "0");

			} else if ((TARGETADDRESS2 - BASE) < 4095) {
				nixbpe[3] = '1';
				int temp = TARGETADDRESS2 - BASE;
				Disp = Integer.toBinaryString(temp); // in decimal
				ModifiedDisp = org.apache.commons.lang.StringUtils.leftPad(Disp, 12, "0");

			} else {
				// createFormatFour();
			}

		}
		NIXBPE = String.valueOf(nixbpe);

		// handling cases without operands
		if (ModifiedDisp == null) {
			ModifiedDisp = "00000000";
		}

		// handling cases with negative displacement (Many ones)
		if (ModifiedDisp.length() > 12) {
			ModifiedDisp = ModifiedDisp.substring(20);
		}

		return opcodeFull + NIXBPE + StringUtils.leftPad("" + ModifiedDisp, 12, "0");
	}

	static String createFormatFour(String OPCODE, boolean indirect, boolean immediate, boolean indexed,
			String targetAddress, Map<String, String> symbolTable) {

		// OPCODE (6)
		String opcodeFull = SupportingFunctions.hexToBinary(OPCODE);
		opcodeFull = opcodeFull.substring(0, opcodeFull.length() - 2); // removing the last 2 binary elements
		System.out.println("OPCODE(6): " + opcodeFull);

		// NIXBPE(6)
		String NIXBPE = "000000";
		char[] nixbpe = NIXBPE.toCharArray();

		if (indirect) {
			nixbpe[0] = '1';
		} else if (immediate) {
			nixbpe[1] = '1';
		} else {
			nixbpe[0] = '1';
			nixbpe[1] = '1';
		}

		// placing 1 for f4 flag
		nixbpe[5] = '1';

		if (indexed) {
			nixbpe[2] = '1';
		}
		System.out.println("NIXBPE(6): " + String.valueOf(nixbpe));

		// Finding the TA value
		String TA = null;
		//String TARGETADDRESS = null;
		if (targetAddress.contains("#") || targetAddress.contains("@")) {
			TA = targetAddress.substring(1);
		} else if (targetAddress.contains(",")) {
			TA = targetAddress.substring(0, targetAddress.length() - 2);
		} else {
			TA = targetAddress;
		}

		TA = symbolTable.get(TA); // now it holds the real hex value
		System.out.println("TA is: " + TA);
		
		String convertedHexToBinary = SupportingFunctions.hexToBinary(TA);
		
		String toBeWritten = org.apache.commons.lang.StringUtils.leftPad(convertedHexToBinary, 20, "0");


		return opcodeFull + NIXBPE + toBeWritten;
	}
}

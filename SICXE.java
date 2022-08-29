import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SICXE {

	public static void main(String[] args) {
		// Printing ana men 34an el daragat
		SupportingFunctions.printNameID();

		// Receiving the full instruction set
		String[][] instructinSet = null;
		instructinSet = ExtractData.readExcel("InstructionSet.xlsx", "InstructionSet");

		// printing the received instruction set from the XLSX file
		System.out.println("[System] Instruction set received");
		System.out.print("[System] Matrix size: ");
		SupportingFunctions.analyzeMatrix(instructinSet);
		System.out.println(" ");
		System.out.println(" ");
		SupportingFunctions.print2D(instructinSet);
		SupportingFunctions.lineBreak();

		// Receiving the code from user
		String[][] code = null;
		code = ExtractData.readExcel("Input.xlsx", "Code");

		// printing the code from the XLSX file
		System.out.println("[System] Code set received");
		System.out.print("[System] Matrix size: ");
		SupportingFunctions.analyzeMatrix(code);
		System.out.println(" ");
		System.out.println(" ");
		SupportingFunctions.print2D(code);
		System.out.println(" ");
		SupportingFunctions.lineBreak();

		// calculating pass1
		String startingAddress = code[1][3];
		String[][] newCode = PassOne.calculateLocation(code);

		Map<String, String> symbolTable = new HashMap<>();
		for (int t = 0; t < newCode.length; t++) {
			if (newCode[t][1].equals("")) {
				// Do nothing, it has no symbol
			} else {
				symbolTable.put(newCode[t][1], newCode[t][0]);
			}
		}

		SupportingFunctions.lineBreak();
		System.out.println("Symbol table keys:");
        System.out.println(symbolTable.keySet().toString());
		System.out.println("Corresponding values:");
        System.out.println(symbolTable.values().toString());
        
        // calculating pass2
        PassTwo.passTwo(newCode, instructinSet, symbolTable);
        
		SupportingFunctions.lineBreak();
		System.out.println(SupportingFunctions.analyzeMatrixGetColumns(newCode));
        SupportingFunctions.print2D(newCode);
        
        //printing the HTE record 
        HTErecord.printHTErecord(newCode);
	}

}

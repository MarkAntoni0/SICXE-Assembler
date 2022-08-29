
public class HTErecord {
	public static void printHTErecord(String code[][]) {
		//printing the H letter alongside the program name
		System.out.print("H^");	System.out.print(org.apache.commons.lang.StringUtils.leftPad(code[0][2], 6, "X")+"^");
		
		//printing the last address
		System.out.print(org.apache.commons.lang.StringUtils.leftPad(code[0][0], 6, "0")+"^");
		
		//calculating the program length
		String firstAddressBinary = SupportingFunctions.hexToBinary(code[0][0]);
		String lastAddressBinary = SupportingFunctions.hexToBinary(code[code.length-1][0]);
		
		
		int b1 = Integer.parseInt(firstAddressBinary, 2);
	    int b2 = Integer.parseInt(lastAddressBinary, 2);
	    int sum = b2 - b1;
	    String programLength = String.valueOf(Integer.toBinaryString(sum));
	    
	    int decimal=Integer.parseInt(programLength,2);
		String hexa2 = Integer.toHexString(decimal);  
	    
		System.out.print(org.apache.commons.lang.StringUtils.leftPad(hexa2, 6, "0")+"^");

	    
		System.out.println("");

		System.out.print("T^");
		for(int i=0; i<code.length;i++) {
			if(code[i][6]!=null) {
				
				System.out.print(code[i][4]+"^");
			}
		}
		
		System.out.println("");
		
		
		
		//looking for modification records
		System.out.print("M^");
		for(int k=0;k<code.length;k++) {
			if(code[k][5].equals("4")) {
				//find the address of this F4 
				//String format4AddressBinary = SupportingFunctions.hexToBinary(code[k][0]);
				String format4AddressBinary = Addition.addHexadecimal(code[k][0], "1");
			    

				System.out.print(org.apache.commons.lang.StringUtils.leftPad(String.valueOf(format4AddressBinary), 6, "0")+"^");
				System.out.print("05^");

			}
		}
		

		
		
		
		System.out.println("");

		//printing the E record
		System.out.print("E^");
		System.out.print(org.apache.commons.lang.StringUtils.leftPad(code[0][0], 6, "0")+"^");

	}
}

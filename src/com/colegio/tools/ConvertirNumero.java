package com.colegio.tools;

public class ConvertirNumero {
	
	
	public static Integer aInteger(String s) {
		Integer result = null;
			
			if(s != null) {
				try {
					result = Integer.valueOf(s);
				}catch(NumberFormatException ex){
					
				}
			}
			return result;
		}

		public static Double aDouble(String s) {
			Double result = null;
				
				if(s != null) {
					try {
						result = Double.valueOf(s);
					}catch(NumberFormatException ex){
						
					}
				}
				return result;
			}
}

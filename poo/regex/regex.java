package poo.regex;

public class regex {
	
	public static void main(String...args) {
		
		String primaparte="[1-9]*?"+"x?"+"(\\^[1-9]+)?";
		String operatore="[\\+\\-\\*/]";
		String Regex="[-]*?"+primaparte + "("+operatore+primaparte+")*?";
		
		String x="x^2+4";
		String x2="2";
		System.out.println(x.matches(Regex));
		System.out.println(x2.matches(primaparte));
	}

}

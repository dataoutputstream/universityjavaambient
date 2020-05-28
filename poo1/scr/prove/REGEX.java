package prove;

public class REGEX {
	public static void main(String[]args) {
		String regex="[0-9a-zA-Z]+";
		String a="54ey3yh6";
		if(a.matches(regex))System.out.println("la stringa è alfanumerica");
		else System.out.println("la stringa non è alfanumerica");
	}

}

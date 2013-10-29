import java.util.Scanner;



public class Interface {
	Scanner sc;
	
	
	
	public Interface() {
		sc = new Scanner(System.in);
	}


	public XY selectionneCase(String prompt) {
		System.out.print(prompt + " (format (X,Y)) : ");
		String str = sc.nextLine();
		str = str.substring(1, str.length()-1);
		String[] coord = str.split(",");
		int x = new Integer(coord[0]).intValue();
		int y = new Integer(coord[1]).intValue();
		return new XY(x,y);
	}
}

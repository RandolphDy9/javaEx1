package one.ex;
import java.util.Scanner;
import java.util.Random;

public class Exer1 {
	
	static final int MAX = 30;
	static String[][] table = new String[MAX][MAX];
	static int length = 0;
	static int newStrLen = 0;
	static int row = 3, col = 3;
	int rows[] = new int[MAX];
	int cols[] = new int[MAX];
	
	public static void createTable(int r, int c) {
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				String chars = "abcdefghijklmnopqrstuvwxyz";
				String randomStr = "";
				Random rand = new Random();		
				char[] text = new char[3];
				for(int y=0; y<3; y++) {
					text[y] = chars.charAt(rand.nextInt(chars.length()));
				}
				for(int z=0; z<text.length; z++) {
					randomStr += text[z];
				}
				table[i][j] = randomStr;
			}	
		}
	}
	
	public static void printTable(int row, int col) {
		for (int i=0; i<row; i++) {
			for (int j=0; j<col; j++) {
				System.out.print(table[i][j] + "\t\t");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		int option = 0;
		int rowLoc = 0, colLoc = 0;
		String newStr = "";
		String searchStr = "";
	
		createTable(row, col);	

		for (;;) {
			System.out.println("\n---------------------");
			System.out.println("[1] Display");
			System.out.println("[2] Edit");
			System.out.println("[3] Search");
			System.out.println("[4] Reset");
			System.out.println("[5] Exit");
			System.out.println("---------------------\n");	
			
			System.out.println("Select Option: ");	
			
			do {
				System.out.println("Please enter a valid Option value: ");
			    while (!reader.hasNextInt()) {
			        System.out.println("Oops! That's not a valid number!");
			        System.out.println("Please enter a valid option: ");
			        reader.next(); 
			    }
			    option = reader.nextInt();
			} while ((option < 0) || (option > 5));
			
			switch(option) {
				case 1:
					printTable(row, col);
					break;
				case 2:
					do {
						System.out.println("Please enter a valid Row value: ");
					    while (!reader.hasNextInt()) {
					        System.out.println("Oops! That's not a valid number!");
					        System.out.println("Please enter a valid row value: ");
					        reader.next(); 
					    }
					    rowLoc = reader.nextInt();
					} while ((rowLoc < 0) || (rowLoc >= row));
					
					do {
						System.out.println("Please enter a valid Column value: ");
					    while (!reader.hasNextInt()) {
					        System.out.println("Oops! That's not a valid number!");
					        System.out.println("Please enter a valid column value: ");
					        reader.next(); 
					    }
					    colLoc = reader.nextInt();
					} while ((colLoc < 0) || (colLoc >= col));

					System.out.println("Enter string: ");
					newStr = reader.next();
					
					while (newStr.matches("[;!*#?:^%<>^$]+")) {
						System.out.println("Invalid. The string must not contain special characters.");
						System.out.println("Please enter another string: ");
					    newStr = reader.next();
					}
					
					newStrLen = newStr.length();
					
					table[rowLoc][colLoc] = newStr;
					
					printTable(row, col);
					
					System.out.println("Changes made!");
					break;
				case 3:
					char[] tableArray = new char[30];
					String storeTableStr = "";
					int ins = 0;
					
					System.out.println("Seach for: ");
					searchStr = reader.next();	
					
					while (searchStr.matches("[;!*#?:^%<>^$]+")) {
						System.out.println("Invalid. The string must not contain special characters.");
						System.out.println("Please enter another string: ");
						searchStr = reader.next();
					}
					
					System.out.println("Results: ");
					
					for (int i=0; i<row; i++) {
						for (int j=0; j<col; j++) {
							if (table[i][j].length() <= 3) {
								length = 3;
								for (int z=0; z<3; z++) {
									tableArray[z] = table[i][j].charAt(z);
									storeTableStr += tableArray[z];
								}
							} else if (table[i][j].length() > 3) {
								length = table[i][j].length();
								for (int z=0; z<length; z++) {
									tableArray[z] = table[i][j].charAt(z);
									storeTableStr += tableArray[z];
								}
							}
							
							for (int x = -1; (x = storeTableStr.indexOf(searchStr, x)) != -1; x++) {
								ins++;
							}

							if (storeTableStr.contains(searchStr)) {
								System.out.println(searchStr + " is located @ (" + i + ", " + j + ") with " + ins + " instance(s)");	
							}	 		
							storeTableStr = "";
							ins = 0;
						}	
					}	
					break;
				case 4:
					do {
						System.out.println("Please enter a value for Row: ");
					    while (!reader.hasNextInt()) {
					        System.out.println("Oops! That's not a valid number!");
					        System.out.println("Please enter a valid row value: ");
					        reader.next(); 
					    }
					    row = reader.nextInt();
					} while (row <= 0 || row > MAX);
					
					do {
						System.out.println("Please enter a value for Column: ");
					    while (!reader.hasNextInt()) {
					        System.out.println("Oops! That's not a valid number!");
					        System.out.println("Please enter a valid column value: ");
					        reader.next(); 
					    }
					    col = reader.nextInt();
					} while (col <= 0 || col > MAX);
					
					System.out.println("Table changed!");
					
					createTable(row, col);
					break;
				case 5: 
					System.exit(0);
					reader.close();
					break;
			}
		} 
	} 
}

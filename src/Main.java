import java.util.Scanner;
public class Main 
{
	static String [][] board = new String[8][8];
	static String player_color; 
	static String chessman;
	static String emptySpace = "x:x ";
	
	public static void init(String color)
	{
		//initial position for black
		board[0][0] = "R:B ";
		board[0][1] = "N:B ";
		board[0][2] = "B:B ";
		board[0][3] = "Q:B ";
		board[0][4] = "K:B ";
		board[0][5] = "B:B ";
		board[0][6] = "N:B ";
		board[0][7] = "R:B ";
		for(int j = 0; j < 8; j++)
		{
			board[1][j] = "P:B ";
		}
		//initial position for white
		board[7][0] = "R:W ";
		board[7][1] = "N:W ";
		board[7][2] = "B:W ";
		board[7][3] = "Q:W ";
		board[7][4] = "K:W ";
		board[7][5] = "B:W ";
		board[7][6] = "N:W ";
		board[7][7] = "R:W ";
		for(int j = 0; j < 8; j++)
		{
			board[6][j] = "P:W ";
		}
	
		
		for(int i = 2; i <= 5; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				board[i][j] = emptySpace;
				//System.out.print(board[i][j]);
			}
			System.out.println();
		}
		player_color = color;
		//System.out.println(player_color);
		chessman = "";
		display();
	}//init
	
	public static void display()
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++ )
			{
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public static boolean checkMoveInput()
	{
		/**
		 * Prompt the user to make a move.
		 b. The user has to specify the current location of the piece (the row index,
		 and then the column index), and the desired destination of the piece (again, row and column index).
		 For example, if the user specifies 0, 0, and then 2,0, then (s)he wants to move the piece at location (0,0) to location (2,0), which is 2 squares vertically.
		 c. Check for valid row and column indexes.
		 */
		boolean current_validity = false;
		boolean move_validity = false;
		//check if there is a piece in the specified current position.
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the current postion of the piece for ROW");
		int currentRow = scan.nextInt();
		System.out.println("Enter the current postion of the piece for COLUMN");
		int currentColumn = scan.nextInt();
		if(isPiece(currentRow, currentColumn))
		{
			//Yes, there is some piece
			//So, ask where to move the piece
			System.out.println("Enter a move for the row");
			int row_move = scan.nextInt();
			System.out.println("Enter a move for the column");
			int column_move = scan.nextInt();
			
		}
		else
		{ 
			//No piece in the specified position
			System.out.println("ERROR: No piece in the specified position!");
			
		}
		
		
		
		if(move_validity && current_validity)
		{
			//both true
			System.out.println("Ready to move");
			return true;
			
		}
		else
		{ 
			System.out.println("Some inputs are wrong");
			return false;
		}
	}//CheckmoveInput
	
	public static void makeMove(int currentR, int currentC, int distinationR, int distinationC,String color)
	{
		/*A) Check if there is a piece of the correct color at the specified current location. */
		
	}
	
	public static boolean isPiece(int currentRow, int currentColumn)
	{
	
		boolean validity = false;

		if(emptySpace.equalsIgnoreCase(board[currentRow][currentColumn]))
		{
			//there is no piece at the specified area 
			System.out.println("Invalid Current Position");
			validity = false;
			
		}
		else
		{
			validity = true;
		}
	
		
		return validity;
	}
	
	public static String getPieceColor(int row, int column)
	{
		String pieceColor = "";//result 
		String[] piece;
		
		if(isPiece(row,column))
		{
			//if there is a piece, then get the piece of color
			piece = board[row][column].split(":");
			
			System.out.println(piece[1]);//show color of piece
			pieceColor = piece[1];
		}
		else
		{
			System.out.println("ERROR: getPieceColor");
			System.out.println("No piece in the specified position!");
		}
		return pieceColor;
	}
	public static String getPieceName(int row, int column)
	{
		String pieceName = "";
		String[] piece;
		if(isPiece(row, column))
		{
			piece = board[row][column].split(":");
			System.out.println(piece[0]);//show name of piece 
			pieceName = piece[0];
			
		}
		else
		{
			System.out.println("ERROR: getPieceName");
			System.out.println("No piece in the specified position!");
		}
		return pieceName;
	}
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		//System.out.println("Chose your chess color from black or white");
		//player_color = scan.next();
		init(player_color);
		System.out.println(isPiece(0,0));
		System.out.println("piece color");
		getPieceColor(0,0);
		System.out.println("piece name");
		getPieceName(5,3);
		
	}
}

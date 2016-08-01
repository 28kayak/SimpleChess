import java.util.Scanner;
public class Main 
{
	static String [][] board = new String[8][8];
	static String player_color; 
	static String chessman;
	static String emptySpace = "x:x ";
	static String white = "W ";
	static String black = "B ";
	
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
		boolean validity = false;
		boolean move_validity = false;
		//check if there is a piece in the specified current position.
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the current postion of the piece for ROW");
		int currentRow = scan.nextInt();
		System.out.println("Enter the current postion of the piece for COLUMN");
		int currentColumn = scan.nextInt();
		
		
		if(!isEmpty(currentRow, currentColumn))
		{
			//Yes, there is some piece
			//So, ask where to move the piece
			System.out.println("Enter a move for the row");
			int row_move = scan.nextInt();
			System.out.println("Enter a move for the column");
			int column_move = scan.nextInt();
			if((0 <= column_move && column_move < 8) &&(0 <= row_move && row_move <8))
			{
				//row and column are valid 
				/*CALL makeMove()*/
				System.out.println("===== piece is moved!!!=====");
				validity = true;
			}
			else
			{
				System.out.println("Error: checkMoveInput()");
				System.out.println("specified desitination is out of range");
				validity = false;
			}
		}
		else
		{ 
			//No piece in the specified position
			System.out.println("ERROR: checkMoveInput() "
					+ "\nNo piece in the specified position!");
			validity = false;
			
		}
		return validity;
		
		
		
		
	}//CheckmoveInput
	
	public static void makeMove(int currentR, int currentC, int distinationR, int distinationC,String color)
	{
		/*A) Check if there is a piece of the correct color at the specified current location.*/
		/*B) Check if the destination location is empty.*/
		
	}
	
	public static boolean isEmpty(int currentRow, int currentColumn)
	{
	
		boolean validity = false;

		if(emptySpace.equalsIgnoreCase(board[currentRow][currentColumn]))
		{
			//there is no piece at the specified area 
			System.out.println("ERROR: isPiece");
			System.out.println("no peice at the specified position");
			validity = true;
			
		}
		else
		{
			//yes, there is a piece 
			validity = false;
		}
	
		
		return validity;
	}//isPiece
	
	public static String getPieceColor(int row, int column)
	{
		String pieceColor = "";//result 
		String[] piece;
		
		if(!isEmpty(row,column))
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
	}//getPieceColor
	public static String getPieceName(int row, int column)
	{
		String pieceName = "";
		String[] piece;
		if(!isEmpty(row, column))
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
	}//getPieceName
	public static boolean isOwnPiece(int destinationR, int destinationC,String userColor)
	{
		/*if true then */
		boolean myTerritory = false;
		String pieceColor = getPieceColor(destinationR, destinationC);
		
		if(pieceColor.equalsIgnoreCase(userColor))
		{
			//the piece is mine
			myTerritory = true;
		}
		else
		{
			//the piece is not mine
			myTerritory = false;
		}
		return myTerritory;
	}
	
	/*move each type of pieces
	 * Precondition: destination coordinates are [0,7]
	 * 			   : destination is empty space
	 * */
	
	public static void moveKing(int currentR, int currentC,int destinationR, int destinationC)
	{
		
		String color = getPieceColor(currentR,currentC);
		if(color.equalsIgnoreCase(white))
		{	
			//check if required destination is valid move for black king 
			if(currentR+1 == destinationR)
			{	
				//(x+1, ???)
				//possible cases 1:(x+1, y-1)  4:(x+1, y) 6: (x+1, y+1)
				if(currentC-1 == destinationC)
				{//case 1 (x+1, y-1)
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:W ";
				}
				else if(currentC == destinationC)
				{//case 4 (x+1, y)
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:W ";
				}
				else if(currentC + 1 == destinationC)
				{//case 6 (x+1, y+1)
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:W ";
				}
				else 
				{
					System.out.println("ERROR: moveKing (black)");
					System.out.println("destination for column [ " + destinationC +" ] is invalid");
				}
			}
			else if(currentR == destinationR)
			{ //possible cases 2:(x, y -1),  7:(x, y + 1) 
				if(currentC - 1 == destinationC)
				{
					//case 2
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:W ";
				}else if(currentC + 1 == destinationC)
				{
					//case 7
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:W ";
				}
				else
				{
					System.out.println("ERROR: moveKing (black)");
					System.out.println("destination for column [ " + destinationC +" ] is invalid");
				}
			}
			else if(currentR - 1 == destinationR)
			{//possible cases 3:(x-1, y-1), 5:(x-1, y),8:(x-1,y+1)
				if(currentC - 1 == destinationC)
				{
					//case 3 
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:W ";
				}
				else if(currentC == destinationC)
				{
					//case 5
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:W ";
				}
				else if(currentC + 1 == destinationC )
				{
					//case 8
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:W ";
				}
				else
				{
					System.out.println("ERROR: moveKing (black)");
					System.out.println("destination for column [ " + destinationC +" ] is invalid");
				}
				
			}
			else
			{
				System.out.println("ERROR: moveKing (black)");
				System.out.println("destination for ROW [ " + destinationR +" ] is invalid");
			}
		}//white case
		else if(color.equalsIgnoreCase(black))
		{
			System.out.println("black" + color);
			
			//check if required destination is valid move for black king 
			if(currentR+1 == destinationR)
			{	
				//(x+1, ???)
				//possible cases 1:(x+1, y-1)  4:(x+1, y) 6: (x+1, y+1)
				if(currentC-1 == destinationC)
				{//case 1 (x+1, y-1)
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:B ";
				}
				else if(currentC == destinationC)
				{//case 4 (x+1, y)
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:B ";
				}
				else if(currentC + 1 == destinationC)
				{//case 6 (x+1, y+1)
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:B ";
				}
				else 
				{
					System.out.println("ERROR: moveKing (black)");
					System.out.println("destination for column [ " + destinationC +" ] is invalid");
				}
			}//if
			else if(currentR == destinationR)
			{ //possible cases 2:(x, y -1),  7:(x, y + 1) 
				if(currentC - 1 == destinationC)
				{
					//case 2
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:B ";
				}else if(currentC + 1 == destinationC)
				{
					//case 7
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:B ";
				}
				else
				{
					System.out.println("ERROR: moveKing (black)");
					System.out.println("destination for column [ " + destinationC +" ] is invalid");
				}
			}
			else if(currentR - 1 == destinationR)
			{//possible cases 3:(x-1, y-1), 5:(x-1, y),8:(x-1,y+1)
				if(currentC - 1 == destinationC)
				{
					//case 3 
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:B ";
				}
				else if(currentC == destinationC)
				{
					//case 5
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:B ";
				}
				else if(currentC + 1 == destinationC )
				{
					//case 8
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="K:B ";
				}
				else
				{
					System.out.println("ERROR: moveKing (black)");
					System.out.println("destination for column [ " + destinationC +" ] is invalid");
				}
					
			}
			else
			{
				System.out.println("ERROR: moveKing (black)");
				System.out.println("destination for ROW [ " + destinationR +" ] is invalid");
			}
			
		}
		
	}//moveKing
	
	public static void moveRook(int currentR, int currentC,int destinationR, int destinationC)
	{
		//Rook can move horizontally and vertically but not diagonally
		String color = getPieceColor(currentR,currentC);
		if(color.equalsIgnoreCase(white))
		{
			//if(isOwnTerritory(destinationR,destinationC,white))
			//{
				//System.out.println("the piece is yours. Cannot move there");
			//}//T:isOwn(white)
			//else
			//{
				if(destinationR == currentR || destinationC == currentC)
				{
					board[currentR][currentC] = emptySpace;
					board[destinationR][destinationC] ="R:W ";
				}
				else
				{
					System.out.println("ERROR: moveRook (white)");
					System.out.println("destination is invalid");
				}
			//}
		}
		else
		{
			//System.out.println("Black move ROOK");
			if(destinationR == currentR || destinationC == currentC)
			{
				board[currentR][currentC] = emptySpace;
				board[destinationR][destinationC] ="R:B ";
			}
			else
			{
				System.out.println("ERROR: moveRook (black)");
				System.out.println("destination is invalid");
			}
		}
	}//moveRook
	public static void movePawn(int currentR, int currentC,int destinationR, int destinationC)
	{
		String color = getPieceColor(currentR,currentC);
		//check if required destination is equal to valid move of pawn
		if(color.equalsIgnoreCase(white))
		{//move for white pawn
			if((currentR - 1) == destinationR && currentC == destinationC)
			{//request is valid 
				board[currentR][currentC] = emptySpace;
				board[destinationR][destinationC] = "P:W ";
			}
			else
			{
				System.out.println("ERROR: movePawn (White)");
				System.out.println("Invalid destination");
			}
		}
		else if(color.equalsIgnoreCase(black))
		{//move for black pawn
			if((currentR + 1) == destinationR && currentC == destinationC)
			{
				board[currentR][currentC] = emptySpace;
				board[destinationR][destinationC] = "P:B ";
			}
			else
			{
				System.out.println("ERROR: movePawn (Black)");
				System.out.println("Invalid destination");
			}
		}
		else
		{
			System.out.println("Fetal Error: input user color is invalid");
		}
	}//movePawn
	
	public static void moveBishop(int currentR, int currentC, int destinationR, int destinationC)
	{
		//The bishop can move any number of squares diagonally.
		String color = getPieceColor(currentR,currentC);
		int diffR = destinationR - currentR;
		int diffC = destinationC - currentC;
		if(color.equalsIgnoreCase(white))
		{
			if(diffR == diffC)
			{
				//ok to move
				board[currentR][currentC] = emptySpace;
				board[destinationR][destinationC] = "B:W ";
				
			}
			
		}
		else if(color.equalsIgnoreCase(black))
		{
			if(diffR == diffC)
			{
				//ok to move
				board[currentR][currentC] = emptySpace;
				board[destinationR][destinationC] = "B:W ";
				
			}
		}
		else
		{
			System.out.println("Fetal Error: dead code");
		}
		
	}//moveBishop
	
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		//System.out.println("Chose your chess color from black or white");
		//player_color = scan.next();
		init(player_color);
		
		System.out.println(isEmpty(7,7));
		
		System.out.println("piece color");
		getPieceColor(0,0);
		
		System.out.println("piece name");
		getPieceName(6,3);
		//System.out.println("calling check Move input");
		//System.out.println(checkMoveInput());
		
		
		/*movePawn(1,3, 2,3);
		display();
		movePawn(1,5, 3,5);*/
		moveKing(7,4,6,3);
		moveKing(0,4,1,4);
		System.out.println("after move king");
		display();
		init(player_color);
		moveRook(7,7,7,4);
		display();
		moveRook(0,0,3,0);
		display();
		//System.out.println("after move rook");
		//moveRook(7,0,3,0);
		//System.out.println("after move rook");
		//display();
		
	}
}

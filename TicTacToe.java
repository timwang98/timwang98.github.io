
//Use Scanner to take inputs from the user.
import java.util.Scanner;
public class TicTacToe {
	/*
	 Name: Xiao Wang
	 McGill ID: 260776403
	 */
	public static char[][] createBoard(int n) {
		//Create a n*n board.
		char[][] arr=new char[n][n];
		for (int i=0;i<n;i++) {
			for (int j=0;j<n;j++) {
				//Initialize the board with space characters.
				arr[i][j]=' ';
			}
		}
		//Return the initialized board.
		return arr;
	}
	public static void displayBoard(char[][] c) {
		//Create a board which will display all the moves made by the user and AI.
		for (int i=0;i<2*c.length+1;i++) {
			for (int j=0;j<2*c.length+1;j++) {
				//Print out the horizontal boundaries of the board.
				if (i%2==0) {
					if (j%2==0) {
						System.out.print("+");
					}else if (j%2!=0){
						System.out.print("-");
					}
				//Print out the vertical boundaries of the board.
				}else if (i%2!=0) {
					if (j%2==0) {
						System.out.print("|");
					}else if (j%2!=0) {
						//Print out the moves made by the user and AI.
						char character=c[(i-1)/2][(j-1)/2];
						System.out.print(character);
					}
				}
			}
			//Change to another row to print out the boundaries.
			System.out.print("\n");
		}
	}
	public static void writeOnBoard(char[][]asChar,char e,int x,int y) {
		//If the coordinates received represent a cell outside of the board, throw an IllegalArgumentException.
		if (x>=asChar.length||x<0||y>=asChar.length||y<0) {
			throw new IllegalArgumentException("The position (x,y) does not represent a cell on the board");
		//If the cell already contains a character that is not the space character, throw an IllegalArgumentException.
		}else if (asChar[x][y]!=' ') {
			throw new IllegalArgumentException("The cell in position (x,y) contains a character other than the space character");
		//If the input is valid, add the character received as input on the board in position (x,y).
		}else {
			asChar[x][y]=e;
		}
	}
	public static void getUserMove(char[][]board) {
		//Declare the variable user to represent the user's move symbol.
		char user='x';
		//Declare and initialize the variable result to identify whether the user's input is valid.
		boolean result=false;
		//Use Scanner to get move from the user.
		Scanner scanner=new Scanner(System.in);
		System.out.println("Please enter your move: ");
		//Declare and initialize the variables a and b to represent move of the user.
		int a=scanner.nextInt();
		int b=scanner.nextInt();
		while (result==false) {
			//If the coordinates received represent a cell outside of the board, inform the user and let the user enter a new move.
			if (a>=board.length||a<0||b>=board.length||b<0) {
				System.out.println("Please enter a new move!");
				a=scanner.nextInt();
				b=scanner.nextInt();
				result=false;
			//If the cell already contains a character that is not the space character, inform the user and let the user enter a new move.
			}else if (board[a][b]!=' ') {
				System.out.println("Please enter a new move!");
				a=scanner.nextInt();
				b=scanner.nextInt();
				result=false;
			//If the move is valid, receive the move as input.
			}else {
				result=true;
			}			
		}
		//Update the user's move on the board.
		writeOnBoard(board,user,a,b);
	}
	public static boolean checkForObviousMove(char[][]asBoard) {
		//The methods checkRow, checkColumn and checkDiagonal are used to identify whether there is an obvious move for the AI to do and if there exists an obvious move, return true in the end.
		if (checkRow(asBoard)==true||checkColumn(asBoard)==true||checkDiagonal(asBoard)==true) {
			//-1 means that there is not an obvious move in rows for the AI to win.
			if (toWinRow(asBoard)!=-1) {
				//The methods toWinRow and checkRowSpace are used there to represent the obvious move the AI can use to win in a row.
				writeOnBoard(asBoard,'o',toWinRow(asBoard),checkRowSpace(asBoard,toWinRow(asBoard)));
			//-1 means that there is not an obvious move in columns for the AI to win.
			}else if (toWinColumn(asBoard)!=-1) {
				//The methods toWinColumn and checkColumnSpace are used there to represent the obvious move the AI can use to win in a column.
				writeOnBoard(asBoard,'o',checkColumnSpace(asBoard,toWinColumn(asBoard)),toWinColumn(asBoard));
			//-1 means that there is not an obvious move in the diagonal whose coordinates are (i,i) for the AI to win.
			}else if (toWinDiagonalFirst(asBoard)!=-1) {
				//The method toWinDiagonalFirst is used to represent the obvious move the AI can use to win in the diagonal whose coordinates are (i,i).
				writeOnBoard(asBoard,'o',toWinDiagonalFirst(asBoard),toWinDiagonalFirst(asBoard));
			//-1 means that there is not an obvious move in the diagonal whose coordinates are (i,board.length-i-1) for the AI to win.
			}else if (toWinDiagonalSecond(asBoard)!=-1) {
				//The method toWinDiagonalSecond is used to represent the obvious move the AI can use to win in the diagonal whose coordinates are (i,board.length-i-1).
				writeOnBoard(asBoard,'o',toWinDiagonalSecond(asBoard),asBoard.length-toWinDiagonalSecond(asBoard)-1);
			//-1 means that there is not an obvious move in rows for the AI to block an obvious win for the user.
			}else if (toBlockRow(asBoard)!=-1) {
				//The methods toBlockRow and checkRowSpace are used there to represent the obvious move the AI can use to block an obvious win for the user in a row.
				writeOnBoard(asBoard,'o',toBlockRow(asBoard),checkRowSpace(asBoard,toBlockRow(asBoard)));
			//-1 means that there is not an obvious move in columns for the AI to block an obvious win for the user.
			}else if (toBlockColumn(asBoard)!=-1) {
				//The methods toBlockColumn and checkColumnSpace are used there to represent the obvious move the AI can use to block an obvious win for the user in a column.
				writeOnBoard(asBoard,'o',checkColumnSpace(asBoard,toBlockColumn(asBoard)),toBlockColumn(asBoard));
			//-1 means that there is not an obvious move in the diagonal whose coordinates are (i,i) for the AI to block an obvious win for the user.
			}else if (toBlockDiagonalFirst(asBoard)!=-1) {
				//The method toBlockDiagonalFirst is used to represent the obvious move the AI can use to block an obvious win for the user in the diagonal whose coordinates are (i,i).
				writeOnBoard(asBoard,'o',toBlockDiagonalFirst(asBoard),toBlockDiagonalFirst(asBoard));
			//-1 means that there is not an obvious move in the diagonal whose coordinates are (i,board.length-i-1) for the AI to block an obvious win for the user.
			}else if (toBlockDiagonalSecond(asBoard)!=-1) {
				//The method toBlockDiagonalSecond is used to represent the obvious move the AI can use to block an obvious win for the user in the diagonal whose coordinates are (i,board.length-i-1).
				writeOnBoard(asBoard,'o',toBlockDiagonalSecond(asBoard),asBoard.length-toBlockDiagonalSecond(asBoard)-1);
			}
			return true;
		//If there is not an obvious move for the AI to do, return false.
		}else {
			return false;
		}	
	}
	//The method checkRow is to check whether there is an obvious move in rows. 
	public static boolean checkRow(char[][]board1) {
		//Declare and initialize variables count, counter, counting to represent the numbers of 'x', 'o', ' ' respectively in a row.
		int count=0;
		int counter=0;
		int counting=0;
		for (int i=0;i<board1.length;i++) {
			for (int j=0;j<board1.length;j++) {
				//If there is an 'x' in a row, add one to count.
				if (board1[i][j]=='x') {
					count++;
				//If there is a 'o' in a row, add one to counter.
				}else if (board1[i][j]=='o') {
					counter++;
				//If there is a ' ' in a row, add one to counting.
				}else if (board1[i][j]==' ') {
					counting++;
				}
			}
			//If there is an obvious move in a row, return true.
			if (counter==board1.length-1||count==board1.length-1) {
				if (counting==1) {
					return true;
				}
			}
			//If there is not an obvious move in this row, reset count, counter, counting to zero and try another row.
			count=0;
			counter=0;
			counting=0;
		}
		//If there is not an obvious move in any rows, return false.
		return false;
	}
	//The method checkColumn is to check whether there is an obvious move in columns. 
	public static boolean checkColumn(char[][]board2) {
		//Declare and initialize variables count1, count2, counting1 to represent the numbers of 'x', 'o', ' ' respectively in a column.
		int count1=0;
		int count2=0;
		int counting1=0;
		for (int i=0;i<board2.length;i++) {
			for (int j=0;j<board2.length;j++) {
				//If there is an 'x' in a column, add one to count1.
				if (board2[j][i]=='x') {
					count1++;
				//If there is a 'o' in a column, add one to count2.
				}else if (board2[j][i]=='o') {
					count2++;
				//If there is a ' ' in a column, add one to counting1.
				}else if (board2[j][i]==' ') {
					counting1++;
				}
			}
			//If there is an obvious move in a column, return true.
			if (count2==board2.length-1||count1==board2.length-1) {
				if (counting1==1) {
					return true;
				}
			}
			//If there is not an obvious move in this column, reset count1, count2, counting1 to zero and try another column.
			count1=0;
			count2=0;
			counting1=0;
		}
		//If there is not an obvious move in any columns, return false.
		return false;
	}
	//The method checkDiagonal is to check whether there is an obvious move in diagonals. 
	public static boolean checkDiagonal(char[][]board3) {
		//Declare and initialize the variables asCount, asCounter, counting2 to represent the numbers of 'x', 'o', ' ' respectively in the diagonal whose coordinates are (i,i).
		//Declare and initialize the variables asCount1, asCounter1, counting3 to represent the numbers of 'x', 'o', ' ' respectively in the diagonal whose coordinates are (i,board.length-i-1).
		//Declare the variables diagonal and diagonal1 to represent a move in a diagonal whose coordinates are (i,i) and (i,board.length-i-1) respectively.
		int asCount=0;
		int asCounter=0;
		int asCount1=0;
		int asCounter1=0;
		int counting2=0;
		int counting3=0;
		char diagonal;
		char diagonal1;
		for (int i=0;i<board3.length;i++) {
			for (int j=0;j<board3.length;j++) {
				//Consider the diagonal whose coordinates are (i,i).
				if (i==j) {
					diagonal=board3[i][j];
					//Add one respectively for asCount, asCounter, counting2 if there is an 'x', 'o', ' ' in the diagonal.
					if (diagonal=='x') {
						asCount++;
					}else if (diagonal=='o') {
						asCounter++;
					}else if (diagonal==' ') {
						counting2++;
					}
				}
				//Consider the diagonal whose coordinates are (i,board.length-i-1).
				if (j==board3.length-i-1) {
					diagonal1=board3[i][j];
					//Add one respectively for asCount1, asCounter1, counting3 if there is an 'x', 'o', ' ' in the diagonal.
					if (diagonal1=='x') {
						asCount1++;
					}else if (diagonal1=='o') {
						asCounter1++;
					}else if (diagonal1==' ') {
						counting3++;
					}
				}
			}
		}
		//If there is an obvious move in the diagonal whose coordinates are (i,i), return true.
		if (asCount==board3.length-1||asCounter==board3.length-1) {
			if (counting2==1) {
				return true;
			}
		}
		//If there is an obvious move in the diagonal whose coordinates are (i,board.length-i-1), return true.
		if (asCount1==board3.length-1||asCounter1==board3.length-1) {
			if (counting3==1) {
				return true;
			}
		}
		//If there is not an obvious move in any diagonals, return false.
		return false;
	}
	//The method toWinRow is used to return the coordinate x of (x,y) for an obvious winning move in a row. 
	public static int toWinRow(char[][]board6) {
		//Declare and initialize the variables number and counting4 to represent the numbers of 'o' and ' ' in a row.
		int number=0;
		int counting4=0;
		if (checkRow(board6)==true) {
			for (int i=0;i<board6.length;i++) {
				for (int j=0;j<board6.length;j++) {
					//Add one respectively for number and counting4 if there is a 'o' and ' ' in a row.
					if (board6[i][j]=='o') {
						number++;
					}else if (board6[i][j]==' ') {
						counting4++;
					}
				}
				//If there exists an obvious winning move in a row, return the coordinate x of (x,y).
				if (number==board6.length-1&&counting4==1) {
					return i;
				}
				//If there does not exist an obvious winning move in a row, reset number and counting4 for another row.
				number=0;
				counting4=0;
			}
		}
		//If there does not exist an obvious winning move in any rows, return -1.
		return -1;
	}
	//The method checkRowSpace is used to receive a coordinate x of (x,y) and then return y.
	//The returned y corresponds to the coordinates (x,y) which is representing a space in a row.
	public static int checkRowSpace(char[][]board7,int x) {
		for (int j=0;j<board7.length;j++) {
			//If there is a ' ' in the row, return its y of (x,y).
			if (board7[x][j]==' ') {
				return j;
			}
		}
		//If there is not a ' ' in the row, return -1.
		return -1;	
	}
	//The method toWinColumn is used to return the coordinate y of (x,y) for an obvious winning move in a column. 
	public static int toWinColumn(char[][]board8) {
		//Declare and initialize the variables number1 and counting5 to represent the numbers of 'o' and ' ' in a column.
		int number1=0;
		int counting5=0;
		if (checkColumn(board8)==true) {
			for (int i=0;i<board8.length;i++) {
				for (int j=0;j<board8.length;j++) {
					//Add one respectively for number1 and counting5 if there is a 'o' and ' ' in a column.
					if (board8[j][i]=='o') {
						number1++;
					}else if (board8[j][i]==' ') {
						counting5++;
					}
				}
				//If there exists an obvious winning move in a column, return the coordinate y of (x,y).
				if (number1==board8.length-1&&counting5==1) {
					return i;
				}
				//If there does not exist an obvious winning move in a column, reset number1 and counting5 for another column.
				number1=0;
				counting5=0;
			}
		}
		//If there does not exist an obvious winning move in any columns, return -1.
		return -1;		
	}
	//The method checkColumnSpace is used to receive a coordinate y of (x,y) and then return x.
	//The returned x corresponds to the coordinates (x,y) which is representing a space in a column.
	public static int checkColumnSpace(char[][]board9,int y) {
		for (int i=0;i<board9.length;i++) {
			//If there is a ' ' in the column, return its x of (x,y).
			if (board9[i][y]==' ') {
				return i;
			}
		}
		//If there is not a ' ' in the column, return -1.
		return -1;
	}
	//The method toWinDiagonalFirst is used to return the coordinate x of (x,y) for an obvious winning move in the diagonal whose coordinates are (i,i). 
	public static int toWinDiagonalFirst(char[][]board10) {
		//Declare and initialize the variable number2 to represent the numbers of 'o' in the diagonal.
		int number2=0;
		//Declare the variable diagonal2 to represent a cell in the diagonal.
		char diagonal2;
		if (checkDiagonal(board10)==true) {
			for (int i=0;i<board10.length;i++) {
				for (int j=0;j<board10.length;j++) {
					//Consider the diagonal whose coordinates are (i,i).
					if (i==j) {
						diagonal2=board10[i][j];
						//Add one to number2 if there is a 'o'.
						if (diagonal2=='o') {
							number2++;
						}
					}
				}
			}
			//If the numbers of 'o' are proper to have a ' ' in the diagonal.
			if (number2==board10.length-1) {
				for (int k=0;k<board10.length;k++) {
					for (int l=0;l<board10.length;l++) {
						//Consider the diagonal whose coordinates are (i,i).
						if (k==l) {
							diagonal2=board10[k][l];
							//If there is a ' ', return the coordinate x of (x,y).
							if (diagonal2==' ') {
								return k;
							}
						}
					}
				}
			}
		}
		//If there is not an obvious winning move in this diagonal, return -1.
		return -1;
	}
	//The method toWinDiagonalSecond is used to return the coordinate x of (x,y) for an obvious winning move in the diagonal whose coordinates are (i,board.length-i-1). 
	public static int toWinDiagonalSecond(char[][]board11) {
		//Declare and initialize the variable number3 to represent the numbers of 'o' in the diagonal.
		int number3=0;
		//Declare the variable diagonal3 to represent a cell in the diagonal.
		char diagonal3;
		if (checkDiagonal(board11)==true) {
			for (int i=0;i<board11.length;i++) {
				for (int j=0;j<board11.length;j++) {
					//Consider the diagonal whose coordinates are (i,board.length-i-1).
					if (j==board11.length-i-1) {
						diagonal3=board11[i][j];
						//Add one to number3 if there is a 'o'.
						if (diagonal3=='o') {
						number3++;
						}
					}
				}
			}
			//If the numbers of 'o' are proper to have a ' ' in the diagonal.
			if (number3==board11.length-1) {
				for (int k=0;k<board11.length;k++) {
					for (int l=0;l<board11.length;l++) {
						//Consider the diagonal whose coordinates are (i,board.length-i-1).
						if (l==board11.length-k-1) {
							diagonal3=board11[k][l];
							//If there is a ' ', return the coordinate x of (x,y).
							if (diagonal3==' ') {
								return k;
							}
						}
					}
				}
			}
		}
		//If there is not an obvious winning move in this diagonal, return -1.
		return -1;
	}
	//The method toBlockRow is used to return the coordinate x of (x,y) for an obvious blocking move in a row. 
	public static int toBlockRow(char[][]board11) {
		//Declare and initialize the variables number4 and counting6 to represent the numbers of 'x' and ' ' in a row.
		int number4=0;
		int counting6=0;
		if (checkRow(board11)==true) {
			for (int i=0;i<board11.length;i++) {
				for (int j=0;j<board11.length;j++) {
					//Add one respectively for number4 and counting6 if there is a 'x' and ' ' in a row.
					if (board11[i][j]=='x') {
						number4++;
					}else if (board11[i][j]==' ') {
						counting6++;
					}
				}
				//If there exists an obvious blocking move in a row, return the coordinate x of (x,y).
				if (number4==board11.length-1&&counting6==1) {
					return i;
				}
				//If there does not exist an obvious blocking move in a row, reset number4 and counting6 for another row.
				number4=0;
				counting6=0;
			}
		}
		//If there does not exist an obvious blocking move in any rows, return -1.
		return -1;
	}
	//The method toBlockColumn is used to return the coordinate y of (x,y) for an obvious blocking move in a column. 
	public static int toBlockColumn(char[][]board12) {
		//Declare and initialize the variables number5 and counting7 to represent the numbers of 'x' and ' ' in a column.
		int number5=0;
		int counting7=0;
		if (checkColumn(board12)==true) {
			for (int i=0;i<board12.length;i++) {
				for (int j=0;j<board12.length;j++) {
					//Add one respectively for number5 and counting7 if there is a 'x' and ' ' in a column.
					if (board12[j][i]=='x') {
						number5++;
					}else if (board12[j][i]==' ') {
						counting7++;
					}
				}
				//If there exists an obvious blocking move in a column, return the coordinate y of (x,y).
				if (number5==board12.length-1&&counting7==1) {
					return i;
				}
				//If there does not exist an obvious blocking move in a column, reset number5 and counting7 for another column.
				number5=0;
				counting7=0;
			}
		}
		//If there does not exist an obvious blocking move in any columns, return -1.
		return -1;	
	}
	//The method toBlockDiagonalFirst is used to return the coordinate x of (x,y) for an obvious blocking move in the diagonal whose coordinates are (i,i). 
	public static int toBlockDiagonalFirst(char[][]board13) {
		//Declare and initialize the variable number6 to represent the numbers of 'x' in the diagonal.
		int number6=0;
		//Declare the variable diagonal4 to represent a cell in the diagonal.
		char diagonal4;
		if (checkDiagonal(board13)==true) {
			for (int i=0;i<board13.length;i++) {
				for (int j=0;j<board13.length;j++) {
					//Consider the diagonal whose coordinates are (i,i).
					if (i==j) {
						diagonal4=board13[i][j];
						//Add one to number6 if there is a 'x'.
						if (diagonal4=='x') {
							number6++;
						}
					}
				}
			}
			//If the numbers of 'x' are proper to have a ' ' in the diagonal.
			if (number6==board13.length-1) {
				for (int k=0;k<board13.length;k++) {
					for (int l=0;l<board13.length;l++) {
						//Consider the diagonal whose coordinates are (i,i).
						if (k==l) {
							diagonal4=board13[k][l];
							//If there is a ' ', return the coordinate x of (x,y).
							if (diagonal4==' ') {
								return k;
							}
						}
					}
				}
			}
		}
		//If there is not an obvious blocking move in this diagonal, return -1.
		return -1;
	}
	//The method toBlockDiagonalSecond is used to return the coordinate x of (x,y) for an obvious blocking move in the diagonal whose coordinates are (i,board.length-i-1). 
	public static int toBlockDiagonalSecond(char[][]board14) {
		//Declare and initialize the variable number7 to represent the numbers of 'x' in the diagonal.
		int number7=0;
		//Declare the variable diagonal5 to represent a cell in the diagonal.
		char diagonal5;
		if (checkDiagonal(board14)==true) {
			for (int i=0;i<board14.length;i++) {
				for (int j=0;j<board14.length;j++) {
					//Consider the diagonal whose coordinates are (i,board.length-i-1).
					if (j==board14.length-i-1) {
						diagonal5=board14[i][j];
						//Add one to number7 if there is a 'x'.
						if (diagonal5=='x') {
						number7++;
						}
					}
				}
			}
			//If the numbers of 'x' are proper to have a ' ' in the diagonal.
			if (number7==board14.length-1) {
				for (int k=0;k<board14.length;k++) {
					for (int l=0;l<board14.length;l++) {
						//Consider the diagonal whose coordinates are (i,board.length-i-1).
						if (l==board14.length-k-1) {
							diagonal5=board14[k][l];
							//If there is a ' ', return the coordinate x of (x,y).
							if (diagonal5==' ') {
								return k;
							}
						}
					}
				}
			}
		}
		//If there is not an obvious blocking move in this diagonal, return -1.
		return -1;
	}
	public static void getAIMove(char[][]board4) {
		//If there is not an obvious move for the AI to do.
		if (checkForObviousMove(board4)==false) {
			//Generate a random move for the AI.
			int i=(int)(Math.random()*board4.length);
			int j=(int)(Math.random()*board4.length);
			//If the generated move is invalid, keep generating a new move until the move is valid.
			while (board4[i][j]!=' ') {
				i=(int)(Math.random()*board4.length);
				j=(int)(Math.random()*board4.length);			
			}
			//Update the board.
			writeOnBoard(board4,'o',i,j);
		}
	}
	public static char checkForWinner(char[][]board5) {
		//Declare and initialize the variables num and num1 to represent the numbers of 'o' and 'x' respectively in a row.
		int num=0;
		int num1=0;
		//Declare and initialize the variables num2 and num3 to represent the numbers of 'o' and 'x' respectively in a column.
		int num2=0;
		int num3=0;
		//Declare and initialize the variables num4 and num5 to represent the numbers of 'o' and 'x' respectively in a diagonal whose coordinates are (i,i).
		int num4=0;
		int num5=0;
		//Declare and initialize the variables num6 and num7 to represent the numbers of 'o' and 'x' respectively in a diagonal whose coordinates are (i,board.length-i-1).
		int num6=0;
		int num7=0;
		for (int i=0;i<board5.length;i++) {
			for (int j=0;j<board5.length;j++) {
				//Add one respectively to num and num1 if there is a 'o' and 'x' in a row.
				if (board5[i][j]=='o') {
					num++;
				}else if (board5[i][j]=='x') {
					num1++;
				}
				//Add one respectively to num2 and num3 if there is a 'o' and 'x' in a column.
				if (board5[j][i]=='o') {
					num2++;
				}else if (board5[j][i]=='x') {
					num3++;
				}
			}
			//If the AI won in a row or a column, return 'o'.
			if (num==board5.length||num2==board5.length) {
				return 'o';
			//If the user won in a row or a column, return 'x'.
			}else if (num1==board5.length||num3==board5.length) {
				return 'x';
			}
			//If there is not a winner in this row or column, reset num, num1, num2, num3 to another row or line.
			num=0;
			num1=0;
			num2=0;
			num3=0;
		}
		for (int k=0;k<board5.length;k++) {
			for (int l=0;l<board5.length;l++) {
				//Consider the diagonal whose coordinates are （i,i).
				if (k==l) {
					//Add one respectively to num4 and num5 if there is a 'o' and 'x' in the diagonal.
					if (board5[k][l]=='o') {
						num4++;
					}else if (board5[k][l]=='x') {
						num5++;
					}
				}
				//Consider the diagonal whose coordinates are (i,board.length-i-1);
				if (l==board5.length-k-1) {
					//Add one respectively to num6 and num7 if there is a 'o' and 'x' in the diagonal.
					if (board5[k][l]=='o') {
						num6++;
					}else if (board5[k][l]=='x') {
						num7++;
					}
				}
			}
		}
		//If the AI won in either diagonal, return 'o'.
		if (num4==board5.length||num6==board5.length) {
			return 'o';
		//If the user won in either diagonal, return 'x'.
		}else if (num5==board5.length||num7==board5.length) {
			return 'x';
		}
		//If there is not a winner when all the cells have been filled, return ' '.
		return ' ';
	}
	//The method flipACoin is used to decide whether the user or the AI should start to play.
	public static int flipACoin() {
		//The variable coin has two values(0 or 1), return coin.
		int coin=(int)(Math.random()*2);
		return coin;
	}
	public static void play() {
		//Use Scanner to receive input from the user.
		Scanner s=new Scanner(System.in);
		//Inform the user to enter his or her name.
		System.out.println("Please enter your name:");
		String name=s.nextLine();
		System.out.println("Welcome, "+name+"! Are you ready to play?");
		//Inform the user to enter the dimension.
		System.out.println("Please choose the dimension of your board:");
		//If the user does not input an integer for the dimension, keep asking for an input of the correct type until it receives one.
		while (!s.hasNextInt()) {
			System.out.println("You entered a wrong input."+"\n"+"Please enter a new input.");
			s.next();
		}
		//Declare the variable n to receive the dimension.
		int n=s.nextInt();
		//If the dimension is 0 or negative, keep asking for a new dimension until the received dimension is positive.
		while (n<=0) {
			System.out.println("You entered a wrong input."+"\n"+"Please enter a new input.");
			n=s.nextInt();
		}
		//Create a board and assign it to the new declared variable newBoard.
		char[][]newBoard=createBoard(n);
		//Declare and initialize the variable winner. 
		char winner=' ';
		//Flip a coin to decide the game order and assign the result to the new declared variable toss.
		int toss=flipACoin();
		System.out.println("The result of the coin toss is: "+toss);
		//1 means that the AI has the first move.
		if (toss==1) {
			System.out.println("The AI has the first move");
			for (int i=0;i<n*n;i++) {
				//The AI makes its move.
				if (i%2==0) {
					System.out.println("The AI made its move:");
					//If there is an obvious move for the AI to make.
					if (checkForObviousMove(newBoard)==true) {
						//Print out the updated board.
						displayBoard(newBoard);
						winner=checkForWinner(newBoard);
						//If the winner is the AI, print out the result and end up the game.
						if (winner=='o') {
							System.out.println("\n"+"GAME OVER!"+"\n"+"You lost");
							break;
						}
					//If there is not an obvious move for the AI to make.
					}else if (checkForObviousMove(newBoard)==false) {
						//Generate a move randomly.
						getAIMove(newBoard);
						displayBoard(newBoard);
						winner=checkForWinner(newBoard);
						//If the winner is the AI, print out the result and end up the game.
						if (winner=='o') {
							System.out.println("\n"+"GAME OVER!"+"\n"+"You lost");
							break;
						}
					}
				//The user makes his or her move.
				}else if (i%2==1) {
					getUserMove(newBoard);
					displayBoard(newBoard);
					winner=checkForWinner(newBoard);
					//If the winner is the user, print out the result and end up the game.
					if (winner=='x') {
						System.out.println("\n"+"GAME OVER!"+"\n"+"You won");
						break;
					}
				}		
			}
		//0 means that the user has the first move.
		}else if (toss==0) {
			System.out.println("You have the first move");
			for (int i=0;i<n*n;i++) {
				//The user makes his or her move.
				if (i%2==0) {
					getUserMove(newBoard);
					displayBoard(newBoard);
					winner=checkForWinner(newBoard);
					//If the winner is the user, print out the result and end up the game.
					if (winner=='x') {
						System.out.println("\n"+"GAME OVER!"+"\n"+"You won");
						break;
					}
				//The AI makes its move.
				}else if (i%2==1) {
					System.out.println("The AI made its move:");
					//If there is an obvious move for the AI to make.
					if (checkForObviousMove(newBoard)==true) {
						displayBoard(newBoard);
						winner=checkForWinner(newBoard);
						//If the winner is the AI, print out the result and end up the game.
						if (winner=='o') {
							System.out.println("\n"+"GAME OVER!"+"\n"+"You lost");
							break;
						}
					//If there is not an obvious move for the AI to make.
					}else if (checkForObviousMove(newBoard)==false) {
						//Generate a move randomly.
						getAIMove(newBoard);
						displayBoard(newBoard);
						winner=checkForWinner(newBoard);
						//If the winner is the AI, print out the result and end up the game.
						if (winner=='o') {
							System.out.println("\n"+"GAME OVER!"+"\n"+"You lost");
							break;
						}
					}
				}
			}
		}
		//If it is a tie, print out the result and end up the game.
		if (winner==' ') {
			System.out.println("\n"+"GAME OVER!"+"\n"+"It was a tie");
		}
		//Close the Scanner s.
		s.close();
	}
	public static void main(String[] args) {
		//Begin the game.
		play();
	}
}

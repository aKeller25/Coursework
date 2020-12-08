package reversi;

import java.util.NoSuchElementException;
import java.lang.IllegalArgumentException;

/*Name: Alexander Keller
 * Class: CMSC132
 * TA: Qian (10 am)
 * Section: 303
 * Time last edited: 7:21 PM 2/27/2015
 */

/**This class was made to hold a board and whose turn it is. This class can be 
 * used to get/set whose turn it is, get/set the contents of the square, count
 * how many of each piece is on the board, reset the board, return a string 
 * representation, determine if a move is valid, and enact a move and its 
 * consequences 
 */

public class Reversi {
	private Piece[][] board;
	private Piece turn;

	//Constructor that creates an empty board and the turn to black
	public Reversi() {
		board = new Piece[8][8];
		setTurn(Piece.BLACK);
		emptyBoard();
	}

	//Copy constructor that copies the current board
	public Reversi(Reversi otherGame) {
		this.board = new Piece[8][8];
		for (int row = 0; row<otherGame.board.length; row++){
			for (int col = 0; col<otherGame.board[row].length; col++){
				board[row][col] = otherGame.board[row][col];
			}
		}
	}

	//Sets the turn to type type
	public void setTurn(Piece type) throws IllegalArgumentException {
		if (type == Piece.NONE){
			throw new IllegalArgumentException();
		}
		else if (type == Piece.BLACK){
			turn = Piece.BLACK;
		}
		else if (type == Piece.WHITE){
			turn = Piece.WHITE;
		}
	}

	//Returns whose turn it is
	public Piece getTurn() {
		return turn;
	}

	//Put a piece of type type in the specific row, col regardless of the
	//current contents of that square and whose turn it is. If the row or col
	//isn't valid then it throws an exception.
	public void setSquare(int row, int col, Piece type)
			throws NoSuchElementException {
		if (row<0 || row>7 || col<0 || col>7){
			throw new NoSuchElementException();
		}
		else {
			board[7-row][col] = type;
		}
	}

	//Returns the contents of the square, given a valid row, col. Otherwise
	//it throws an exception.
	public Piece getSquare(int row, int col) throws NoSuchElementException {
		if (row<0 || row>7 || col<0 || col>7){
			throw new NoSuchElementException();
		}
		else {
			return board[7-row][col];
		}
	}

	//Counts how many of each piece is on the board and returns the number of
	//type type
	public int count(Piece type) {
		int black = 0; 
		int white = 0; 
		int none = 0;

		//Finds and adds up the number of all pieces regardless of which piece
		//type is asked for
		for (int row = 0; row<board.length; row++){
			for (int col = 0; col<board[row].length; col++){
				if (getSquare(row, col) == Piece.BLACK){
					black++;
				}
				else if (getSquare(row, col) == Piece.WHITE){
					white++;
				}
				else if (getSquare(row, col) == Piece.NONE){
					none++;
				}
			}
		}

		if (type == Piece.BLACK){
			return black;
		}
		else if (type == Piece.WHITE){
			return white;
		}
		else {
			return none;
		}
	}

	//Resets the board to the the pieces, seen below, and sets the turn
	//to type type. If type piece.NONE, then it throws an exception
	public void reset(Piece type) throws IllegalArgumentException {
		emptyBoard();
		setSquare(4,3, Piece.WHITE);
		setSquare(3,4, Piece.WHITE);
		setSquare(3,3, Piece.BLACK);
		setSquare(4,4, Piece.BLACK);

		if(type == Piece.BLACK){
			setTurn(Piece.BLACK);
		}
		else if(type == Piece.WHITE){
			setTurn(Piece.WHITE);
		}
		else if(type == Piece.NONE){
			throw new IllegalArgumentException();
		}
	}

	//Writes a string representation of the board
	public String toString() {
		int line = 7;
		String boardRep = "";
		for (int row = 0; row<8; row++){
			if (line >= 0){
				boardRep += line-- + " ";
			}
			for (int col = 0; col<8; col++){

				if (col == 7){
					boardRep += board[row][col];
				} else {
					boardRep += board[row][col] + " ";
				}
			}
			boardRep += "\n";
		}
		boardRep +=  "  0 1 2 3 4 5 6 7\n";

		return boardRep;
	}

	//Checks if placing a piece of type type in row,col would be a valid move.
	//A valid move means that the: the row or col is valid, 
	//the type of piece is not none, and the square that's going to be filled
	//is empty
	public boolean validMove(int row, int col, Piece type) {
		boolean valid = false;
		if (!(row<0) && !(row>7) && !(col<0) && !(col>7) && type != Piece.NONE
				&& getSquare(row, col) == Piece.NONE){

			//Checking horizontally
			if ((checkRight(row, col, type) || checkLeft(row, col, type))  
					&& !valid){ 
				valid = true;
			}

			//Checking vertically
			else if ((checkAbove(row, col, type) || checkBelow(row, col, type))
					&& !valid){ //Checking vertically
				valid = true;
			}

			//Checking diagonally
			else if ((checkUpRight(row, col, type) || 
					checkDownLeft(row, col, type) || 
					checkUpLeft(row, col, type) || 
					checkDownRight(row, col, type)) && !valid){
				valid = true;
			}
		}
		return valid;
	}

	//Puts a piece of whoever's turn it is at row, col, flips all of the pieces
	//necessary and then sets the turn to the other color, only if the move is
	//valid. 
	public void move(int row, int col) {
		Piece nextTurn = null;

		if (validMove(row, col, getTurn())){
			flip(row, col, getTurn());
			nextTurn = (getTurn() == Piece.WHITE) ? 
					Piece.BLACK : (getTurn() == Piece.NONE ? 
							Piece.NONE: Piece.WHITE);	

			if (nextTurn != Piece.NONE){
				setTurn(nextTurn);
			}
		}		
	}

	//Moves a piece depending on if it's type type's turn. If it is their turn
	//it calls the move(int, int) method
	public void move(int row, int col, Piece type) {
		if (type == getTurn()){
			move(row, col);
		}
	}

	//A helper method that sets the board to an empty board
	private void emptyBoard(){
		for (int row = 0; row<8; row++){
			for (int col = 0; col<8; col++){
				this.board[row][col] = Piece.NONE;
			}
		}
	}

	//A helper method that checks if there is a valid move on the right
	private boolean checkRight(int row, int col, Piece type){
		boolean right = false;
		Piece searchFor = (type == Piece.WHITE) ? Piece.BLACK : Piece.WHITE; 

		for (int p = col+1; p<board[row].length && !right && 
				getSquare(row, p) != Piece.NONE; p++){ 
			if (getSquare(row, p) == searchFor && p+1>=0 && p+1<=7 &&
					getSquare(row, p+1) == type){
				right = true;
			}
		}
		return right;
	}

	//A helper method that checks if there is a valid move on the left
	private boolean checkLeft(int row, int col, Piece type){
		boolean left = false;
		Piece searchFor = (type == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;

		for (int p = col-1; p>0  && !left && 
				getSquare(row, p) != Piece.NONE; p--){
			if (getSquare(row, p) == searchFor && p-1>=0 && p-1<=7 &&
					getSquare(row, p-1) == type){
				left = true;
			}
		}
		return left;
	}

	//A helper method that checks if there is a valid move above
	private boolean checkAbove(int row, int col, Piece type){
		boolean up = false;
		Piece searchFor = (type == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;

		for (int i = row+1; i<board.length && !up && 
				getSquare(i, col) != Piece.NONE; i++){
			if (getSquare(i, col) == searchFor && i+1>=0 && i+1<=7 &&
					getSquare(i+1, col) == type ){
				up = true;
			}
		}
		return up;
	}

	//A helper method that checks if there is a valid move below
	private boolean checkBelow(int row, int col, Piece type){
		boolean down = false;
		Piece searchFor = (type == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;

		for (int i = row-1; i>0 && !down && 
				getSquare(i, col) != Piece.NONE; i--){
			if (getSquare(i, col) == searchFor && i-1>=0 && i-1<=7 &&
					getSquare(i-1, col) == type){
				down = true;
			}
		}
		return down;
	}

	//A helper method that checks if there is a valid move in the 
	//up-right direction
	private boolean checkUpRight(int row, int col, Piece type){
		boolean upRight = false;
		Piece searchFor = (type == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;

		for (int i = row+1, p = col+1; i<board.length  && p<board[row].length
				&& !upRight &&	getSquare(i, p) != Piece.NONE; i++, p++){ 
			if (getSquare(i, p) == searchFor && i+1>=0 && i+1<=7 && p+1>=0 && 
					p+1<=7 && getSquare(i+1, p+1) == type  
					){
				upRight = true;
			}
		}
		return upRight;
	}

	//A helper method that checks if there is a valid move in the 
	//up-left direction
	private boolean checkUpLeft(int row, int col, Piece type){
		boolean upLeft = false;
		Piece searchFor = (type == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;

		for (int i = row+1, p = col-1; i<board.length  && p<board[row].length
				&& i>0 && p>0 && !upLeft &&	
				getSquare(i, p) != Piece.NONE; i++, p--){ 
			if (getSquare(i, p) == searchFor &&  i+1>=0 && i+1<=7 && p-1>=0 
					&& p-1<=7 && getSquare(i+1, p-1) == type){
				upLeft = true;
			}
		}
		return upLeft;
	}

	//A helper method that checks if there is a valid move in the 
	//down-left direction
	private boolean checkDownLeft(int row, int col, Piece type){
		boolean downLeft = false;
		Piece searchFor = (type == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;

		for (int i = row-1, p = col-1; i>0 && p>0  && !downLeft && 
				getSquare(i, p) != Piece.NONE; i--, p--){
			if (getSquare(i, p) == searchFor && i-1>=0 && i-1<=7 && p-1>=0 && 
					p-1<=7 && getSquare(i-1, p-1) == type){
				downLeft = true;
			}
		}
		return downLeft;
	}

	//A helper method that checks if there is a valid move in the 
	//down-right direction
	private boolean checkDownRight(int row, int col, Piece type){
		boolean downRight = false;
		Piece searchFor = (type == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;

		for (int i = row-1, p = col+1; i>0 && p>0 && i<board.length  && 
				p<board[row].length&& !downRight && 
				getSquare(i, p) != Piece.NONE; i--, p++){
			if (getSquare(i, p) == searchFor && i-1>=0 && i-1<=7 && p+1>=0 && 
					p+1<=7 && getSquare(i-1, p+1) == type){
				downRight = true;
			}
		}
		return downRight;
	}


	//Flips all of the pieces in the appropriate direction
	private void flip(int row, int col, Piece type){

		//Flips pieces if there are pieces to be flipped to the right
		if (checkRight(row, col, type)){ 
			int p = col;
			do{
				setSquare(row, p, type);
				p++;
			}
			while(getSquare(row,p) != type);
		}

		//Flips pieces if there are pieces to be flipped to the left
		if (checkLeft(row, col, type)){
			int p = col;
			do{
				setSquare(row, p, type);
				p--;
			}
			while(getSquare(row,p) != type);
		}

		//Flips pieces if there are pieces to be flipped below
		if (checkBelow(row, col, type)){
			int i = row;
			do{
				setSquare(i, col, type);
				i--;
			}
			while(getSquare(i,col) != type);
		}

		//Flips pieces if there are pieces to be flipped above
		if (checkAbove(row, col, type)){
			int i = row;
			do{
				setSquare(i, col, type);
				i++;
			}
			while(getSquare(i,col) != type);
		}

		//flips pieces if there are pieces to be flipped
		//in the down-left direction
		if (checkDownLeft(row, col, type)){
			int i = row;
			int p = col;
			do{
				setSquare(i, p, type);
				i--;
				p--;
			}
			while(getSquare(i,p) != type);
		}

		//flips pieces if there are pieces to be flipped
		//in the up-right direction
		if (checkUpRight(row, col, type)){
			int i = row;
			int p = col;
			do {
				setSquare(i, p, type);
				i++;
				p++;
			}
			while(getSquare(i,p) != type);
		}

		//flips pieces if there are pieces to be flipped
		//in the up-left direction
		if (checkUpLeft(row, col, type)){
			int i = row;
			int p = col;
			do {
				setSquare(i, p, type);
				i++;
				p--;
			}
			while(getSquare(i,p) != type);
		}

		//flips pieces if there are pieces to be flipped
		//in the up-left direction
		if (checkDownRight(row, col, type)){
			int i = row;
			int p = col;
			do {
				setSquare(i, p, type);
				i--;
				p++;
			}
			while(getSquare(i,p) != type);
		}
	}
}


package game;

import entities.Board;
import entities.Piece;

/**
 * Engine for Stratego game
 * 
 * @author Camilo Sanchez
 * 
 */
public class Engine
{
	/**
	 * Moves the piece to the specified position
	 * 
	 * @param x
	 * @param y
	 * @return true if piece has moved to (x,y)
	 */
	public static boolean movePiece(Piece piece, byte x, byte y)
	{
		if (!Rules.isValidMove(piece, x, y))
			return false;
		
		Board board = Board.getBoard();
		
		if (!board.isEmptyPos(x, y))
		{
			if (!battle(piece, board.getPieceAt(x, y)))
				return false;
			board.removePiece(board.getPieceAt(x, y));
		}
		
		return board.movePiece(piece, x, y);
	}
	
	/**
	 * Moves the piece in (xi, yi) to the specified position
	 * 
	 * @param xi
	 * @param yi
	 * @param xf
	 * @param yf
	 * @return true if piece in (xi,yi) has moved to (xf,yf)
	 * @deprecated
	 */
	public static boolean movePiece(byte xi, byte yi, byte xf, byte yf)
	{
		Board board = Board.getBoard();
		
		if (board.isEmptyPos(xi, yi))
			return false;
		
		return movePiece(board.getPieceAt(xi, yi), xf, yf);
	}
	
	/**
	 * Simulates a battle between ally piece an enemy piece
	 * 
	 * @param ally
	 * @param enemy
	 * @return true if ally wins
	 *         <p>
	 *         false if ally loses
	 */
	public static boolean battle(Piece ally, Piece enemy)
	{
		
		if (ally.getOwner() == enemy.getOwner())
			return false;
		return ((ally.getLevel() - enemy.getLevel()) > 0);
	}
}

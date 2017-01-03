package cs2114.minesweeper;
import java.util.Random;

// -------------------------------------------------------------------------
/**
 * Here's my class
 *
 * @author derekhuber
 * @version Feb 24, 2016
 */
public class MineSweeperBoard
    extends MineSweeperBoardBase
{
    private int                   width;
    private int                   height;
    /**
     * board
     */
    protected MineSweeperCell[][] board;


    // ----------------------------------------------------------
    /**
     * Create a new MineSweeperBoard object.
     *
     * @param width
     *            is width
     * @param height
     *            is height
     * @param numMines
     *            is numMines
     */
    public MineSweeperBoard(int width, int height, int numMines)
    {
        this.width = width;
        this.height = height;
        board = new MineSweeperCell[width][height];
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                board[i][j] = MineSweeperCell.COVERED_CELL;
            }
        }
        Random r = new Random();
        int count = 0;
        while (count < numMines) {
            int w = r.nextInt(width);
            int h = r.nextInt(height);
            if (board[w][h] != MineSweeperCell.MINE) {
                board[w][h] = MineSweeperCell.MINE;
                count++;
            }
        }



        // Constructor
    }

// ~ Methods ...............................................................


// ----------------------------------------------------------
    /**
     * Get the number of columns in this MineSweeperBoard.
     *
     * @return the number of columns in this MineSweeperBoard.
     */
    public int width()
    {
        return width;
    }


    /**
     * Get the number of rows in this MineSweeperBoard.
     *
     * @return the number of rows in this MineSweeperBoard
     */
    public int height()
    {
        return height;
    }


    /**
     * Get the contents of the specified cell on this MineSweeperBoard. The
     * value returned from this method must be one of the values from the
     * {@link MineSweeperCell} enumerated type.
     *
     * @param x
     *            the column containing the cell.
     * @param y
     *            the row containing the cell.
     * @return the value contained in the cell specified by x and y, or
     *         INVALID_CELL if the specified cell does not exist.
     */
    public MineSweeperCell getCell(int x, int y)
    {
        if (x > width || y > height)
        {
            return MineSweeperCell.INVALID_CELL;
        }
        else
        {
            return board[x][y];
        }
    }


    /**
     * Uncover the specified cell. If the cell already contains a flag it should
     * not be uncovered. If there is not a mine under the specified cell then
     * the value in that cell is changed to the number of mines that appear in
     * adjacent cells. If there is a mine under the specified cell the game is
     * over and the player has lost. If the specified cell is already uncovered
     * or is invalid, no change is made to the board.
     *
     * @param x
     *            the column of the cell to be uncovered.
     * @param y
     *            the row of the cell to be uncovered.
     */
    public void uncoverCell(int x, int y)
    {
        if (board[x][y] == MineSweeperCell.FLAG)
        {
            return;
        }
        else if (board[x][y] == MineSweeperCell.MINE)
        {
            board[x][y] = MineSweeperCell.UNCOVERED_MINE;
        }
        else if (
            board[x][y] != MineSweeperCell.FLAGGED_MINE
                && board[x][y] != MineSweeperCell.UNCOVERED_MINE)
        {
            if (numberOfAdjacentMines(x, y) == 0)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_0;
            }
            if (numberOfAdjacentMines(x, y) == 1)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_1;
            }
            if (numberOfAdjacentMines(x, y) == 2)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_2;
            }
            if (numberOfAdjacentMines(x, y) == 3)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_3;
            }
            if (numberOfAdjacentMines(x, y) == 4)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_4;
            }
            if (numberOfAdjacentMines(x, y) == 5)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_5;
            }
            if (numberOfAdjacentMines(x, y) == 6)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_6;
            }
            if (numberOfAdjacentMines(x, y) == 7)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_7;
            }
            if (numberOfAdjacentMines(x, y) == 8)
            {
                board[x][y] = MineSweeperCell.ADJACENT_TO_8;
            }
        }
    }


    /**
     * Place or remove a flag from the specified cell. If the cell currently
     * covered then place a flag on the cell. If the cell currently contains a
     * flag, remove that flag but do not uncover the cell. If the cell has
     * already been uncovered or is invalid, no change is made to the board.
     *
     * @param x
     *            the column of the cell to be flagged/unflagged
     * @param y
     *            the row of the cell to be flagged/unflagged
     */
    public void flagCell(int x, int y)
    {
        if (board[x][y] == MineSweeperCell.COVERED_CELL)
        {
            board[x][y] = MineSweeperCell.FLAG;
        }
        else if (board[x][y] == MineSweeperCell.FLAG)
        {
            board[x][y] = MineSweeperCell.COVERED_CELL;
        }
        else if (board[x][y] == MineSweeperCell.MINE)
        {
            board[x][y] = MineSweeperCell.FLAGGED_MINE;
        }
        else if (board[x][y] == MineSweeperCell.FLAGGED_MINE)
        {
            board[x][y] = MineSweeperCell.MINE;
        }
    }


    /**
     * Determine if the player has lost the current game. The game is lost if
     * the player has uncovered a mine.
     *
     * @return true if the current game has been lost and false otherwise
     */
    public boolean isGameLost()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (board[i][j] == MineSweeperCell.UNCOVERED_MINE)
                {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Determine if the player has won the current game. The game is won when
     * three conditions are met:
     * <ol>
     * <li>Flags have been placed on all of the mines.</li>
     * <li>No flags have been placed incorrectly.</li>
     * <li>All non-flagged cells have been uncovered.</li>
     * </ol>
     *
     * @return true if the current game has been won and false otherwise.
     */
    public boolean isGameWon()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (board[i][j] == MineSweeperCell.UNCOVERED_MINE
                    || board[i][j] == MineSweeperCell.MINE
                    || board[i][j] == MineSweeperCell.FLAG
                    || board[i][j] == MineSweeperCell.COVERED_CELL)
                {
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Count the number of mines that appear in cells that are adjacent to the
     * specified cell.
     *
     * @param x
     *            the column of the cell.
     * @param y
     *            the row of the cell.
     * @return the number of mines adjacent to the specified cell.
     */
    public int numberOfAdjacentMines(int x, int y)
    {
        int count = 0;
        if (x < width - 1 && (board[x + 1][y] == MineSweeperCell.MINE
            || board[x + 1][y] == MineSweeperCell.FLAGGED_MINE
            || board[x + 1][y] == MineSweeperCell.UNCOVERED_MINE))
        {
            count++;
        }
        if (x > 0 && (board[x - 1][y] == MineSweeperCell.MINE
            || board[x - 1][y] == MineSweeperCell.FLAGGED_MINE
            || board[x - 1][y] == MineSweeperCell.UNCOVERED_MINE))
        {
            count++;
        }
        if (y < width - 1 && (board[x][y + 1] == MineSweeperCell.MINE
            || board[x][y + 1] == MineSweeperCell.FLAGGED_MINE
            || board[x][y + 1] == MineSweeperCell.UNCOVERED_MINE))
        {
            count++;
        }
        if (y > 0 && (board[x][y - 1] == MineSweeperCell.MINE
            || board[x][y - 1] == MineSweeperCell.FLAGGED_MINE
            || board[x][y - 1] == MineSweeperCell.UNCOVERED_MINE))
        {
            count++;
        }
        if (x < width - 1 && y < height - 1
            && (board[x + 1][y + 1] == MineSweeperCell.MINE
            || board[x + 1][y + 1] == MineSweeperCell.FLAGGED_MINE
                || board[x + 1][y + 1] == MineSweeperCell.UNCOVERED_MINE))
        {
            count++;
        }
        if (x > 0 && y < height - 1
            && (board[x - 1][y + 1] == MineSweeperCell.MINE
            || board[x - 1][y + 1] == MineSweeperCell.FLAGGED_MINE
                || board[x - 1][y + 1] == MineSweeperCell.UNCOVERED_MINE))
        {
            count++;
        }
        if (x < width - 1 && y > 0
            && (board[x + 1][y - 1] == MineSweeperCell.MINE
            || board[x + 1][y - 1] == MineSweeperCell.FLAGGED_MINE
                || board[x + 1][y - 1] == MineSweeperCell.UNCOVERED_MINE))
        {
            count++;
        }
        if (x > 0 && y > 0
            && (board[x - 1][y - 1] == MineSweeperCell.MINE
            || board[x - 1][y - 1] == MineSweeperCell.FLAGGED_MINE
                || board[x - 1][y - 1] == MineSweeperCell.UNCOVERED_MINE))
        {
            count++;
        }

        return count;
    }


    /**
     * Uncover all of the cells on the board.
     */
    public void revealBoard()
    {
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < width; j++)
            {
                uncoverCell(i, j);
             /*   if (board[i][j] == MineSweeperCell.COVERED_CELL)
                {
                    uncoverCell(i, j);
                }
                if (board[i][j] == MineSweeperCell.MINE
                    || board[i][j] == MineSweeperCell.FLAGGED_MINE)
                {
                    board[i][j] = MineSweeperCell.UNCOVERED_MINE;
                } */
            }
        }
    }


    /**
     * Set the contents of the specified cell on this MineSweeperBoard. The
     * value passed in should be one of the defined constants in the
     * {@link MineSweeperCell} enumerated type.
     *
     * @param x
     *            the column containing the cell
     * @param y
     *            the row containing the cell
     * @param value
     *            the value to place in the cell
     */
    protected void setCell(int x, int y, MineSweeperCell value)
    {
        board[x][y] = value;
    }
}

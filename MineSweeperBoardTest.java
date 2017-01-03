package cs2114.minesweeper;

// -------------------------------------------------------------------------
/**
 * Class here
 *
 * @author derekhuber
 * @version Feb 24, 2016
 */
public class MineSweeperBoardTest
    extends student.TestCase
{
    /**
     * mine
     */
    MineSweeperBoard m;


    // ----------------------------------------------------------
    /**
     * Create a new MineSweeperBoardTest object.
     */
    public MineSweeperBoardTest()
    {
        // Constructor Empty
    }


// Methods....................................................................
    /**
     * Set up
     */
    public void setUp()
    {
        m = new MineSweeperBoard(5, 5, 0);
    }


    // ----------------------------------------------------------
    /**
     * test height
     */
    public void testHeight()
    {
        assertEquals(5, m.height());
    }


    // ----------------------------------------------------------
    /**
     * get cell.
     */
    public void testGetCell()
    {
        assertEquals(MineSweeperCell.COVERED_CELL, m.getCell(1, 1));
        assertEquals(MineSweeperCell.INVALID_CELL, m.getCell(50, 4));
        assertEquals(MineSweeperCell.INVALID_CELL, m.getCell(5, 40));
    }


    // ----------------------------------------------------------
    /**
     * uncover cell
     */
    public void testUncoverCell()
    {
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_0, m.getCell(2, 2));
        m.setCell(2, 3, MineSweeperCell.MINE);
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_1, m.getCell(2, 2));
        m.setCell(2, 1, MineSweeperCell.MINE);
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_2, m.getCell(2, 2));
        m.setCell(1, 2, MineSweeperCell.MINE);
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_3, m.getCell(2, 2));
        m.setCell(3, 2, MineSweeperCell.MINE);
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_4, m.getCell(2, 2));
        m.setCell(1, 1, MineSweeperCell.MINE);
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_5, m.getCell(2, 2));
        m.setCell(1, 3, MineSweeperCell.MINE);
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_6, m.getCell(2, 2));
        m.setCell(3, 1, MineSweeperCell.MINE);
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_7, m.getCell(2, 2));
        m.setCell(3, 3, MineSweeperCell.MINE);
        m.uncoverCell(2, 2);
        assertEquals(MineSweeperCell.ADJACENT_TO_8, m.getCell(2, 2));

        m.setCell(4, 4, MineSweeperCell.FLAG);
        m.uncoverCell(4, 4);
        assertEquals(MineSweeperCell.FLAG, m.getCell(4, 4));

        m.setCell(4, 4, MineSweeperCell.MINE);
        m.uncoverCell(4, 4);
        assertEquals(MineSweeperCell.UNCOVERED_MINE, m.getCell(4, 4));

        m.setCell(4, 4, MineSweeperCell.FLAGGED_MINE);
        m.uncoverCell(4, 4);
        assertEquals(MineSweeperCell.FLAGGED_MINE, m.getCell(4, 4));

        m.setCell(4, 4, MineSweeperCell.UNCOVERED_MINE);
        m.uncoverCell(4, 4);
        assertEquals(MineSweeperCell.UNCOVERED_MINE, m.getCell(4, 4));
    }


    // ----------------------------------------------------------
    /**
     * flag cell.
     */
    public void testFlagCell()
    {
        m.setCell(1, 1, MineSweeperCell.COVERED_CELL);
        m.flagCell(1, 1);
        assertEquals(MineSweeperCell.FLAG, m.getCell(1, 1));
        m.setCell(1, 1, MineSweeperCell.FLAG);
        m.flagCell(1, 1);
        assertEquals(MineSweeperCell.COVERED_CELL, m.getCell(1, 1));
        m.setCell(1, 1, MineSweeperCell.MINE);
        m.flagCell(1, 1);
        assertEquals(MineSweeperCell.FLAGGED_MINE, m.getCell(1, 1));
        m.setCell(1, 1, MineSweeperCell.FLAGGED_MINE);
        m.flagCell(1, 1);
        assertEquals(MineSweeperCell.MINE, m.getCell(1, 1));
        MineSweeperBoard a = new MineSweeperBoard(4, 4, 3);
        a.loadBoardState("O123", "4567", "8*+M", "FO  ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                a.flagCell(i, j);
            }
        }
    }


    // ----------------------------------------------------------
    /**
     * is game lost.
     */
    public void testIsGameLost()
    {
        assertEquals(false, m.isGameLost());
        m.setCell(2, 2, MineSweeperCell.UNCOVERED_MINE);
        assertEquals(true, m.isGameLost());
    }


    // ----------------------------------------------------------
    /**
     * is game won.
     */
    public void testIsGameWon()
    {
        assertEquals(false, m.isGameWon());
        m.setCell(2, 2, MineSweeperCell.FLAG);
        assertEquals(false, m.isGameWon());
        m.setCell(2, 2, MineSweeperCell.UNCOVERED_MINE);
        assertEquals(false, m.isGameWon());
        m.setCell(2, 2, MineSweeperCell.MINE);
        assertEquals(false, m.isGameWon());
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                m.setCell(i, j, MineSweeperCell.ADJACENT_TO_0);
            }
        }
        assertEquals(true, m.isGameWon());
        MineSweeperBoard b = new MineSweeperBoard(4, 4, 3);
        b.loadBoardState("O123", "4567", "8*+M", "FO  ");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                b.isGameWon();
            }
        }


    }


    // ----------------------------------------------------------
    /**
     * number of adjacent mines.
     */
    public void testNumberOfAdjacentMines()
    {
        assertEquals(0, m.numberOfAdjacentMines(2, 2));
        m.setCell(2, 3, MineSweeperCell.MINE);
        assertEquals(1, m.numberOfAdjacentMines(2, 2));
        m.setCell(2, 1, MineSweeperCell.MINE);
        assertEquals(2, m.numberOfAdjacentMines(2, 2));
        m.setCell(1, 2, MineSweeperCell.MINE);
        assertEquals(3, m.numberOfAdjacentMines(2, 2));
        m.setCell(3, 2, MineSweeperCell.MINE);
        assertEquals(4, m.numberOfAdjacentMines(2, 2));
        m.setCell(1, 1, MineSweeperCell.MINE);
        assertEquals(5, m.numberOfAdjacentMines(2, 2));
        m.setCell(1, 3, MineSweeperCell.MINE);
        assertEquals(6, m.numberOfAdjacentMines(2, 2));
        m.setCell(3, 1, MineSweeperCell.MINE);
        assertEquals(7, m.numberOfAdjacentMines(2, 2));
        m.setCell(3, 3, MineSweeperCell.MINE);
        assertEquals(8, m.numberOfAdjacentMines(2, 2));

    }


    // ----------------------------------------------------------
    /**
     * reveal board.
     */
    public void testRevealBoard()
    {
        m.setCell(2, 2, MineSweeperCell.MINE);
        m.revealBoard();
        assertEquals(MineSweeperCell.UNCOVERED_MINE, m.getCell(2, 2));
    }


    // ----------------------------------------------------------
    /**
     * set cell.
     */
    public void testSetCell()
    {
        m.setCell(1, 1, MineSweeperCell.MINE);
        assertEquals(MineSweeperCell.MINE, m.getCell(1, 1));
    }


    /**
     * test for adjacent to
     */
    public void testAdjacentTo()
    {
        MineSweeperCell c = MineSweeperCell.ADJACENT_TO_0;
        assertNotNull(c);
        // testing for exception
        Exception thrown = null;
        try
        {
            c = MineSweeperCell.adjacentTo(10);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);

        thrown = null;
        try
        {
            MineSweeperCell.adjacentTo(-1);
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        assertNotNull(MineSweeperCell.values());
        assertNotNull(MineSweeperCell.valueOf(
            MineSweeperCell.ADJACENT_TO_0.toString()));

    }


    // ----------------------------------------------------------
    /**
     * board state
     */
    public void testloadBoardState()
    {
        MineSweeperBoard a = new MineSweeperBoard(2, 2, 1);
        Exception thrown = null;
        // loadBoardState testing
        // wrong number of rows
        try
        {
            a.loadBoardState("00");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        thrown = null;
        // wrong number of columns
        try
        {
            a.loadBoardState("0000 ", " ");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
        // Wrong symbol in cell
        try
        {
            a.loadBoardState("00", "$+");
        }
        catch (Exception e)
        {
            thrown = e;
        }
        assertTrue(thrown instanceof IllegalArgumentException);
    }


    /**
     * * This method test Equals.
     */
    public void testEqual()
    {
        MineSweeperBoard mBoard1 = new MineSweeperBoard(4, 4, 6);
        MineSweeperBoard mBoard2 = new MineSweeperBoard(4, 4, 6);
        mBoard1.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        mBoard2.loadBoardState("    ", "OOOO", "O++O", "OOOO");
        // test the same board same dimensions
        assertTrue(mBoard1.equals(mBoard2));
        // same board testing same board
        assertTrue(mBoard1.equals(mBoard1));
        // testing same dimensions board with different cell
        MineSweeperBoard mBoard3 = new MineSweeperBoard(4, 4, 6);
        mBoard3.loadBoardState("    ", "O+OO", "O++O", "OOOO");
        assertFalse(mBoard1.equals(mBoard3));
        MineSweeperBoard mBoard4 = new MineSweeperBoard(15, 1, 0);
        mBoard4.loadBoardState("OFM+* 123456788");
        assertFalse(mBoard1.toString().equals(mBoard3.toString()));
        // testing two string against a board
        assertFalse(mBoard4.toString().equals(mBoard2.toString()));
        // testing against a string
        assertFalse(mBoard1.equals("abc"));
        // same width but different height
        MineSweeperBoard mBoard6 = new MineSweeperBoard(4, 5, 6);
        mBoard6.loadBoardState("    ", "O+OO", "O++O", "OOOO", "OOOO");
        assertFalse(mBoard6.equals(mBoard1));
        // different width same height
        MineSweeperBoard mBoard5 = new MineSweeperBoard(5, 4, 6);
        mBoard5.loadBoardState("     ", "O+OOO", "O++OO", "OOOOO");
        assertFalse(mBoard5.equals(mBoard1));
    }
}

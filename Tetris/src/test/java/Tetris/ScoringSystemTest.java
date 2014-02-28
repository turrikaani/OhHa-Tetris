/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Tetris;

import Tetris.DataTypes.TetrominoType;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author admin
 */
public class ScoringSystemTest {
    
    private ScoringSystem scoringSystem = new ScoringSystem(5);
    
    private ArrayList<Integer> oneRow = new ArrayList<Integer>();
    private ArrayList<Integer> twoRows = new ArrayList<Integer>();
    private ArrayList<Integer> threeRows = new ArrayList<Integer>();
    private ArrayList<Integer> fourRows = new ArrayList<Integer>();
    
    public ScoringSystemTest() {
        for (int i=0; i<1; i++) oneRow.add(i);
        for (int i=0; i<2; i++) twoRows.add(i);
        for (int i=0; i<3; i++) threeRows.add(i);
        for (int i=0; i<4; i++) fourRows.add(i);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testInitialState() {
        assertEquals(0, scoringSystem.getScore());
        assertEquals(0, scoringSystem.getSingleLineClears());
        assertEquals(0, scoringSystem.getDoubleLineClears());
        assertEquals(0, scoringSystem.getTripleLineClears());
        assertEquals(0, scoringSystem.getQuadrupleLineClears());
        assertEquals(0, scoringSystem.getTotalLinesCleared());
        assertEquals(0, scoringSystem.getNumTetrominoes_I());
        assertEquals(0, scoringSystem.getNumTetrominoes_J());
        assertEquals(0, scoringSystem.getNumTetrominoes_L());
        assertEquals(0, scoringSystem.getNumTetrominoes_O());
        assertEquals(0, scoringSystem.getNumTetrominoes_S());
        assertEquals(0, scoringSystem.getNumTetrominoes_T());
        assertEquals(0, scoringSystem.getNumTetrominoes_Z());
        assertEquals(0, scoringSystem.getTotalNumberOfTetrominoes());
        assertEquals(5, scoringSystem.getGravityLevel());
    }
    
    @Test
    public void testVariousGravityValuesInConstructor() {
        ScoringSystem scorer1 = new ScoringSystem(0);
        ScoringSystem scorer2 = new ScoringSystem(11);
        ScoringSystem scorer3 = new ScoringSystem(1);
        ScoringSystem scorer4 = new ScoringSystem(10);
        assertEquals(5, scorer1.getGravityLevel());
        assertEquals(5, scorer2.getGravityLevel());
        assertEquals(1, scorer3.getGravityLevel());
        assertEquals(10, scorer4.getGravityLevel());
    }
    
    @Test
    public void testAddingSingleLineClearToScore() {
        scoringSystem.addClearedRowsToScore(oneRow);
        assertEquals(5*100, scoringSystem.getScore());
        assertEquals(1, scoringSystem.getSingleLineClears());
        assertEquals(0, scoringSystem.getDoubleLineClears());
        assertEquals(0, scoringSystem.getTripleLineClears());
        assertEquals(0, scoringSystem.getQuadrupleLineClears());
        assertEquals(1, scoringSystem.getTotalLinesCleared());
    }
    
    @Test
    public void testAddingDoubleLineClearToScore() {
        scoringSystem.addClearedRowsToScore(twoRows);
        assertEquals(5*300, scoringSystem.getScore());
        assertEquals(0, scoringSystem.getSingleLineClears());
        assertEquals(1, scoringSystem.getDoubleLineClears());
        assertEquals(0, scoringSystem.getTripleLineClears());
        assertEquals(0, scoringSystem.getQuadrupleLineClears());
        assertEquals(2, scoringSystem.getTotalLinesCleared());
    }
    
    @Test
    public void testAddingTripleLineClearToScore() {
        scoringSystem.addClearedRowsToScore(threeRows);
        assertEquals(5*750, scoringSystem.getScore());
        assertEquals(0, scoringSystem.getSingleLineClears());
        assertEquals(0, scoringSystem.getDoubleLineClears());
        assertEquals(1, scoringSystem.getTripleLineClears());
        assertEquals(0, scoringSystem.getQuadrupleLineClears());
        assertEquals(3, scoringSystem.getTotalLinesCleared());
    }
    
    @Test
    public void testAddingQuadrupleLineClearToScore() {
        scoringSystem.addClearedRowsToScore(fourRows);
        assertEquals(5*1600, scoringSystem.getScore());
        assertEquals(0, scoringSystem.getSingleLineClears());
        assertEquals(0, scoringSystem.getDoubleLineClears());
        assertEquals(0, scoringSystem.getTripleLineClears());
        assertEquals(1, scoringSystem.getQuadrupleLineClears());
        assertEquals(4, scoringSystem.getTotalLinesCleared());
    }
    
    @Test
    public void testAddingType_I_TetrominoToStats() {
        scoringSystem.addTetrominoToStats(TetrominoType.I);
        assertEquals(1, scoringSystem.getNumTetrominoes_I());
        assertEquals(0, scoringSystem.getNumTetrominoes_J());
        assertEquals(0, scoringSystem.getNumTetrominoes_L());
        assertEquals(0, scoringSystem.getNumTetrominoes_O());
        assertEquals(0, scoringSystem.getNumTetrominoes_S());
        assertEquals(0, scoringSystem.getNumTetrominoes_T());
        assertEquals(0, scoringSystem.getNumTetrominoes_Z());
        assertEquals(1, scoringSystem.getTotalNumberOfTetrominoes());
    }
    
    @Test
    public void testAddingType_J_TetrominoToStats() {
        scoringSystem.addTetrominoToStats(TetrominoType.J);
        assertEquals(0, scoringSystem.getNumTetrominoes_I());
        assertEquals(1, scoringSystem.getNumTetrominoes_J());
        assertEquals(0, scoringSystem.getNumTetrominoes_L());
        assertEquals(0, scoringSystem.getNumTetrominoes_O());
        assertEquals(0, scoringSystem.getNumTetrominoes_S());
        assertEquals(0, scoringSystem.getNumTetrominoes_T());
        assertEquals(0, scoringSystem.getNumTetrominoes_Z());
        assertEquals(1, scoringSystem.getTotalNumberOfTetrominoes());
    }
    
    @Test
    public void testAddingType_L_TetrominoToStats() {
        scoringSystem.addTetrominoToStats(TetrominoType.L);
        assertEquals(0, scoringSystem.getNumTetrominoes_I());
        assertEquals(0, scoringSystem.getNumTetrominoes_J());
        assertEquals(1, scoringSystem.getNumTetrominoes_L());
        assertEquals(0, scoringSystem.getNumTetrominoes_O());
        assertEquals(0, scoringSystem.getNumTetrominoes_S());
        assertEquals(0, scoringSystem.getNumTetrominoes_T());
        assertEquals(0, scoringSystem.getNumTetrominoes_Z());
        assertEquals(1, scoringSystem.getTotalNumberOfTetrominoes());
    }
    
    @Test
    public void testAddingType_O_TetrominoToStats() {
        scoringSystem.addTetrominoToStats(TetrominoType.O);
        assertEquals(0, scoringSystem.getNumTetrominoes_I());
        assertEquals(0, scoringSystem.getNumTetrominoes_J());
        assertEquals(0, scoringSystem.getNumTetrominoes_L());
        assertEquals(1, scoringSystem.getNumTetrominoes_O());
        assertEquals(0, scoringSystem.getNumTetrominoes_S());
        assertEquals(0, scoringSystem.getNumTetrominoes_T());
        assertEquals(0, scoringSystem.getNumTetrominoes_Z());
        assertEquals(1, scoringSystem.getTotalNumberOfTetrominoes());
    }
    
    @Test
    public void testAddingType_S_TetrominoToStats() {
        scoringSystem.addTetrominoToStats(TetrominoType.S);
        assertEquals(0, scoringSystem.getNumTetrominoes_I());
        assertEquals(0, scoringSystem.getNumTetrominoes_J());
        assertEquals(0, scoringSystem.getNumTetrominoes_L());
        assertEquals(0, scoringSystem.getNumTetrominoes_O());
        assertEquals(1, scoringSystem.getNumTetrominoes_S());
        assertEquals(0, scoringSystem.getNumTetrominoes_T());
        assertEquals(0, scoringSystem.getNumTetrominoes_Z());
        assertEquals(1, scoringSystem.getTotalNumberOfTetrominoes());
    }
    
    @Test
    public void testAddingType_T_TetrominoToStats() {
        scoringSystem.addTetrominoToStats(TetrominoType.T);
        assertEquals(0, scoringSystem.getNumTetrominoes_I());
        assertEquals(0, scoringSystem.getNumTetrominoes_J());
        assertEquals(0, scoringSystem.getNumTetrominoes_L());
        assertEquals(0, scoringSystem.getNumTetrominoes_O());
        assertEquals(0, scoringSystem.getNumTetrominoes_S());
        assertEquals(1, scoringSystem.getNumTetrominoes_T());
        assertEquals(0, scoringSystem.getNumTetrominoes_Z());
        assertEquals(1, scoringSystem.getTotalNumberOfTetrominoes());
    }
    
    @Test
    public void testAddingType_Z_TetrominoToStats() {
        scoringSystem.addTetrominoToStats(TetrominoType.Z);
        assertEquals(0, scoringSystem.getNumTetrominoes_I());
        assertEquals(0, scoringSystem.getNumTetrominoes_J());
        assertEquals(0, scoringSystem.getNumTetrominoes_L());
        assertEquals(0, scoringSystem.getNumTetrominoes_O());
        assertEquals(0, scoringSystem.getNumTetrominoes_S());
        assertEquals(0, scoringSystem.getNumTetrominoes_T());
        assertEquals(1, scoringSystem.getNumTetrominoes_Z());
        assertEquals(1, scoringSystem.getTotalNumberOfTetrominoes());
    }
    
    @Test
    public void addFastDropBonusToScoreTest1() {
        scoringSystem.addFastDropBonusToScore(27);
        assertEquals(20*2*5, scoringSystem.getScore());
    }
    
    @Test
    public void addFastDropBonusToScoreTest2() {
        scoringSystem.addFastDropBonusToScore(56);
        assertEquals(18*2*5, scoringSystem.getScore());
    }
    
    @Test
    public void addFastDropBonusToScoreTest3() {
        scoringSystem.addFastDropBonusToScore(280);
        assertEquals(10*2*5, scoringSystem.getScore());
    }
    
    @Test
    public void addFastDropBonusToScoreTest4() {
        scoringSystem.addFastDropBonusToScore(307);
        assertEquals(10*2*5, scoringSystem.getScore());
    }
    
    @Test
    public void addFastDropBonusToScoreTest5() {
        scoringSystem.addFastDropBonusToScore(308);
        assertEquals(0, scoringSystem.getScore());
    }
}

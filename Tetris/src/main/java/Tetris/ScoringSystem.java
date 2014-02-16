package Tetris;

import Tetris.DataTypes.*;
import java.util.List;

public class ScoringSystem {

    private int score = 0;
    private int gravityLevel;

    private int totalLinesCleared = 0;
    private int singleLineClears = 0;
    private int doubleLineClears = 0;
    private int tripleLineClears = 0;
    private int quadrupleLineClears = 0;

    int totalNumberOfTetrominoes = 0;
    int numTetrominoes_I = 0;
    int numTetrominoes_J = 0;
    int numTetrominoes_L = 0;
    int numTetrominoes_O = 0;
    int numTetrominoes_S = 0;
    int numTetrominoes_T = 0;
    int numTetrominoes_Z = 0;

    public ScoringSystem(int gravityLevel) {

        if (gravityLevel < 1 || gravityLevel > 10) this.gravityLevel = 1;
        else this.gravityLevel = gravityLevel;
    }

    public void addClearedRowsToScore(List<Integer> rowList) {

        switch (rowList.size()) {

            case 1:
                this.singleLineClears++;
                this.score += 100*this.gravityLevel;
                break;

            case 2:
                this.doubleLineClears++;
                this.score += 300*this.gravityLevel;
                break;

            case 3:
                this.tripleLineClears++;
                this.score += 750*this.gravityLevel;
                break;

            case 4:
                this.quadrupleLineClears++;
                this.score += 1600*this.gravityLevel;
                break;
        }

        this.totalLinesCleared += rowList.size();
    }

    public void addFastDropBonusToScore(int gravityDropsSinceAppearance) {

        if (gravityDropsSinceAppearance < 15) {
            this.score += (20-gravityDropsSinceAppearance)*2*this.gravityLevel;
        }
    }

    public void addTetrominoToStats(TetrominoType type) {

        switch (type) {

            case I:
                this.numTetrominoes_I++;
                break;
            case J:
                this.numTetrominoes_J++;
                break;
            case L:
                this.numTetrominoes_L++;
                break;
            case O:
                this.numTetrominoes_O++;
                break;
            case S:
                this.numTetrominoes_S++;
                break;
            case T:
                this.numTetrominoes_T++;
                break;
            case Z:
                this.numTetrominoes_Z++;
                break;
        }

        this.totalNumberOfTetrominoes++;
    }

    public int getScore() {
        return this.score;
    }

    public int getGravityLevel() {
        return this.gravityLevel;
    }

    public int getTotalLinesCleared() {
        return this.totalLinesCleared;
    }

    public int getSingleLineClears() {
        return this.singleLineClears;
    }

    public int getDoubleLineClears() {
        return this.doubleLineClears;
    }

    public int getTripleLineClears() {
        return this.tripleLineClears;
    }

    public int getQuadrupleLineClears() {
        return this.quadrupleLineClears;
    }

    public int getTotalNumberOfTetrominoes() {
        return this.totalNumberOfTetrominoes;
    }

    public int getNumTetrominoes_I() {
        return this.numTetrominoes_I;
    }

    public int getNumTetrominoes_J() {
        return this.numTetrominoes_J;
    }

    public int getNumTetrominoes_L() {
        return this.numTetrominoes_L;
    }

    public int getNumTetrominoes_O() {
        return this.numTetrominoes_O;
    }

    public int getNumTetrominoes_S() {
        return this.numTetrominoes_S;
    }

    public int getNumTetrominoes_T() {
        return this.numTetrominoes_T;
    }

    public int getNumTetrominoes_Z() {
        return this.numTetrominoes_Z;
    }
}

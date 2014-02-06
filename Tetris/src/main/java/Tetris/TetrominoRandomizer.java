package Tetris;

import Tetris.DataTypes.*;
import java.util.Random;

public class TetrominoRandomizer {

    private Random randomizer;

    public TetrominoRandomizer() {
        this.randomizer = new Random();
    }

    public TetrominoType getRandomTetrominoType() {

        int selector = this.randomizer.nextInt(7);

        switch (selector) {

            case 0 : return TetrominoType.I;
            case 1 : return TetrominoType.J;
            case 2 : return TetrominoType.L;
            case 3 : return TetrominoType.O;
            case 4 : return TetrominoType.S;
            case 5 : return TetrominoType.T;
            default: return TetrominoType.Z;
        }
    }
}

package Tetris;

import Tetris.DataTypes.*;
import java.util.Random;

public class TetrominoRandomizer {

    private Random randomizer;
    private int previousSelector = -1;

    public TetrominoRandomizer() {
        this.randomizer = new Random();
    }

    public TetrominoType getRandomTetrominoType() {

        int selector;

        do {
            selector = randomizer.nextInt(7);
        }
        while (selector == previousSelector);

        previousSelector = selector;

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

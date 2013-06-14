package com.isaacgrande.ultimatepuzzletimer.scramblers;

import com.isaacgrande.ultimatepuzzletimer.models.Scramble;
import com.isaacgrande.ultimatepuzzletimer.models.ScramblerInfo;
import com.isaacgrande.ultimatepuzzletimer.solvers.Square1Solver;

import java.util.Random;

public class Square1CubeShapeScrambler implements Scrambler {
    private ScramblerInfo scramblerInfo;
    private Square1Solver solver;
    private Random random;

    public Square1CubeShapeScrambler(ScramblerInfo scramblerInfo) {
        this.scramblerInfo = scramblerInfo;
        this.solver = new Square1Solver();
        this.random = new Random();
    }

    @Override
    public ScramblerInfo getScramblerInfo() {
        return this.scramblerInfo;
    }

    @Override
    public Scramble getNextScramble() {
        return new Scramble(
            getScramblerInfo().getScramblerId(),
            this.solver.generate(
                this.solver.getRandomState(Square1Solver.State.id, this.random)));
    }

    @Override
    public String toString() {
        return getScramblerInfo().getDescription();
    }
}
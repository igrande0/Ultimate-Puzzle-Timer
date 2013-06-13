package com.isaacgrande.ultimatepuzzletimer.scramblers;

import com.isaacgrande.ultimatepuzzletimer.models.Scramble;
import com.isaacgrande.ultimatepuzzletimer.models.ScramblerInfo;
import com.isaacgrande.ultimatepuzzletimer.solvers.SkewbSolver;

import java.util.Random;

public class SkewbRandomScrambler implements Scrambler {
    private ScramblerInfo scramblerInfo;
    private SkewbSolver solver;
    private Random random;

    public SkewbRandomScrambler(ScramblerInfo scramblerInfo) {
        this.scramblerInfo = scramblerInfo;
        this.solver = new SkewbSolver();
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
                this.solver.getRandomState(this.random)));
    }

    @Override
    public String toString() {
        return getScramblerInfo().getDescription();
    }
}
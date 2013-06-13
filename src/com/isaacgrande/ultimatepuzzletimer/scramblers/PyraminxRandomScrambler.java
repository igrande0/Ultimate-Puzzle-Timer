package com.isaacgrande.ultimatepuzzletimer.scramblers;

import com.isaacgrande.ultimatepuzzletimer.models.Scramble;
import com.isaacgrande.ultimatepuzzletimer.models.ScramblerInfo;
import com.isaacgrande.ultimatepuzzletimer.solvers.PyraminxSolver;

import java.util.Random;

public class PyraminxRandomScrambler implements Scrambler {
    private ScramblerInfo scramblerInfo;
    private PyraminxSolver solver;
    private Random random;

    public PyraminxRandomScrambler(ScramblerInfo scramblerInfo, int minScrambleLength) {
        this.scramblerInfo = scramblerInfo;
        this.solver = new PyraminxSolver(minScrambleLength);
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

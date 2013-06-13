package com.isaacgrande.ultimatepuzzletimer.scramblers;

import com.isaacgrande.ultimatepuzzletimer.models.Scramble;
import com.isaacgrande.ultimatepuzzletimer.models.ScramblerInfo;
import com.isaacgrande.ultimatepuzzletimer.solvers.FloppyCubeSolver;

import java.util.Random;

public class FloppyCubeRandomScrambler implements Scrambler {
    private ScramblerInfo scramblerInfo;
    private Random random;

    public FloppyCubeRandomScrambler(ScramblerInfo scramblerInfo) {
        this.scramblerInfo = scramblerInfo;
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
            FloppyCubeSolver.generate(
                FloppyCubeSolver.getRandomState(this.random)));
    }

    @Override
    public String toString() {
        return getScramblerInfo().getDescription();
    }
}
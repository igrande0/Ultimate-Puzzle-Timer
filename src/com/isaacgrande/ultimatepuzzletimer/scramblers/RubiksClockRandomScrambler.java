package com.isaacgrande.ultimatepuzzletimer.scramblers;

import com.isaacgrande.ultimatepuzzletimer.models.Scramble;
import com.isaacgrande.ultimatepuzzletimer.models.ScramblerInfo;
import com.isaacgrande.ultimatepuzzletimer.solvers.RubiksClockSolver;

import java.util.Random;

public class RubiksClockRandomScrambler implements Scrambler {
    private ScramblerInfo scramblerInfo;
    private Random random;

    public RubiksClockRandomScrambler(ScramblerInfo scramblerInfo) {
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
            RubiksClockSolver.getRandomSequence(this.random));
    }

    @Override
    public String toString() {
        return getScramblerInfo().getDescription();
    }
}
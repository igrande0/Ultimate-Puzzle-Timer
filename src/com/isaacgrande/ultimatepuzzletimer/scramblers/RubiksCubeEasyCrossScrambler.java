package com.isaacgrande.ultimatepuzzletimer.scramblers;

import com.isaacgrande.ultimatepuzzletimer.models.Scramble;
import com.isaacgrande.ultimatepuzzletimer.models.ScramblerInfo;
import com.isaacgrande.ultimatepuzzletimer.solvers.RubiksCubeCrossSolver;
import com.isaacgrande.ultimatepuzzletimer.solvers.RubiksCubeSolver;
import com.isaacgrande.ultimatepuzzletimer.solvers.RubiksCubeSolver.State;

import java.util.ArrayList;

public class RubiksCubeEasyCrossScrambler implements Scrambler {
    private ScramblerInfo scramblerInfo;
    private int maxDistance;
    private RubiksCubeRandomScrambler rubiksCubeRandomScrambler;

    public RubiksCubeEasyCrossScrambler(ScramblerInfo scramblerInfo, int maxDistance) {
        this.scramblerInfo = scramblerInfo;
        this.maxDistance = maxDistance;
        this.rubiksCubeRandomScrambler =
            new RubiksCubeRandomScrambler(
                scramblerInfo,
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 });
    }

    @Override
    public ScramblerInfo getScramblerInfo() {
        return this.scramblerInfo;
    }

    @Override
    public Scramble getNextScramble() {
        State x = new State(
            new byte[] { 3, 2, 6, 7, 0, 1, 5, 4 },
            new byte[] { 2, 1, 2, 1, 1, 2, 1, 2 },
            new byte[] { 7, 5, 9, 11, 6, 2, 10, 3, 4, 1, 8, 0 },
            new byte[] { 0, 0, 0,  0, 1, 0,  1, 0, 1, 0, 1, 0 });

        for (;;) {
            State state = this.rubiksCubeRandomScrambler.getRandomState();

            ArrayList<String[]> solution =
                RubiksCubeCrossSolver.solve(
                    x.multiply(x).multiply(state).multiply(x).multiply(x));
            if (solution.get(0).length <= this.maxDistance) {
                return new Scramble(
                    getScramblerInfo().getScramblerId(),
                    RubiksCubeSolver.generate(state));
            }
        }
    }

    @Override
    public String toString() {
        return getScramblerInfo().getDescription();
    }
}
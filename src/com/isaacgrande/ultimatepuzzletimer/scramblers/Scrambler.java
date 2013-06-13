package com.isaacgrande.ultimatepuzzletimer.scramblers;

import com.isaacgrande.ultimatepuzzletimer.models.Scramble;
import com.isaacgrande.ultimatepuzzletimer.models.ScramblerInfo;

public interface Scrambler {
    ScramblerInfo getScramblerInfo();
    Scramble getNextScramble();
}

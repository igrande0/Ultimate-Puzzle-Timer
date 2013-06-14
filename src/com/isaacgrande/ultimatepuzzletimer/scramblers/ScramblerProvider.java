package com.isaacgrande.ultimatepuzzletimer.scramblers;

import com.isaacgrande.ultimatepuzzletimer.App;
import com.isaacgrande.ultimatepuzzletimer.models.ScramblerInfo;
import com.isaacgrande.ultimatepuzzletimer.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ScramblerProvider {
    private Scrambler[] scramblers;
    private HashMap<String, Scrambler> scramblerMap;

    public ScramblerProvider() {
    	
        // 2x2x2 importer
        Scrambler rubiksPocketCubeImporter =
            new EmptyScrambler(
                new ScramblerInfo("2x2x2-CUBE-IMPORTER", "2x2x2-CUBE", ""));

        // 2x2x2 random
        Scrambler rubiksPocketCubeRandom =
            new RubiksPocketCubeRandomScrambler(
                new ScramblerInfo("2x2x2-CUBE-RANDOM", "2x2x2-CUBE", 
                App.getContext().getResources().getString(R.string.x222_CUBE_RANDOM)),
                0,
                new String[] { "U", "D", "L", "R", "F", "B" });

        // 2x2x2 <U, R, F>
        Scrambler rubiksPocketCubeURF =
            new RubiksPocketCubeRandomScrambler(
                new ScramblerInfo("2x2x2-CUBE-URF", "2x2x2-CUBE", 
                App.getContext().getResources().getString(R.string.x222_CUBE_URF)),
                0,
                new String[] { "U", "R", "F" });

        // 2x2x2 sub optimal <U, R, F>
        Scrambler rubiksPocketCubeSuboptimalURF =
            new RubiksPocketCubeRandomScrambler(
                new ScramblerInfo("2x2x2-CUBE-SUBOPTIMAL-URF", "2x2x2-CUBE", 
                App.getContext().getResources().getString(R.string.x222_CUBE_SUBOPTIMAL_URF)),
                11,
                new String[] { "U", "R", "F" });

        // 3x3x3 importer
        Scrambler rubiksCubeImporter =
            new EmptyScrambler(
                new ScramblerInfo("RUBIKS-CUBE-IMPORTER", "RUBIKS-CUBE", ""));

        // 3x3x3 random
        Scrambler rubiksCubeRandom =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-RANDOM", "RUBIKS-CUBE",
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_RANDOM)),
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 });

        // 3x3x3 <L, U>
        Scrambler rubiksCubeLU =
            new RubiksCubeLUScrambler(
                new ScramblerInfo("RUBIKS-CUBE-LU", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_LU)));

        // 3x3x3 <R, U>
        Scrambler rubiksCubeRU =
            new RubiksCubeRUScrambler(
                new ScramblerInfo("RUBIKS-CUBE-RU", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_RU)));

        // 3x3x3 CLL training
        Scrambler rubiksCubeCLLTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-CLL-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_CLL_TRAINING)),
                new byte[] { -1, -1, -1, -1,  4,  5,  6,  7 },
                new byte[] { -1, -1, -1, -1,  0,  0,  0,  0 },
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 });

        // 3x3x3 ELL training
        Scrambler rubiksCubeELLTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-ELL-TRAINING", "RUBIKS-CUBE",
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_ELL_TRAINING)),
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0 },
                new byte[] {  0,  1,  2,  3, -1, -1, -1, -1,  8,  9, 10, 11 },
                new byte[] {  0,  0,  0,  0, -1, -1, -1, -1,  0,  0,  0,  0 });

        // 3x3x3 fridrich f2l training
        Scrambler rubiksCubeFridrichF2LTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-FRIDRICH-F2L-TRAINING", "RUBIKS-CUBE",
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_FRIDRICH_F2L_TRAINING)),
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1,  8,  9, 10, 11 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1,  0,  0,  0,  0 });

        // 3x3x3 fridrich oll training
        Scrambler rubiksCubeFridrichOLLTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-FRIDRICH-OLL-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_FRIDRICH_OLL_TRAINING)),
                new byte[] { -1, -1, -1, -1,  4,  5,  6,  7 },
                new byte[] { -1, -1, -1, -1,  0,  0,  0,  0 },
                new byte[] {  0,  1,  2,  3, -1, -1, -1, -1,  8,  9, 10, 11 },
                new byte[] {  0,  0,  0,  0, -1, -1, -1, -1,  0,  0,  0,  0 });

        // 3x3x3 fridrich pll training
        Scrambler rubiksCubeFridrichPLLTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-FRIDRICH-PLL-TRAINING", "RUBIKS-CUBE",
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_FRIDRICH_PLL_TRAINING)),
                new byte[] { -1, -1, -1, -1,  4,  5,  6,  7 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0 },
                new byte[] {  0,  1,  2,  3, -1, -1, -1, -1,  8,  9, 10, 11 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 });

        // 3x3x3 3op corners training
        Scrambler rubiksCube3OPCornersTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-3OP-CORNERS-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_3OP_CORNERS_TRAINING)),
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 });

        // 3x3x3 3op corners permutation training
        Scrambler rubiksCube3OPCornersPermutationTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-3OP-CORNERS-PERMUTATION-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_3OP_CORNERS_PERMUTATION_TRAINING)),
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0 },
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 });

        // 3x3x3 3op corners orientation training
        Scrambler rubiksCube3OPCornersOrientationTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-3OP-CORNERS-ORIENTATION-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_3OP_CORNERS_ORIENTATION_TRAINING)),
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 });

        // 3x3x3 3op edges training
        Scrambler rubiksCube3OPEdgesTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-3OP-EDGES-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_3OP_EDGES_TRAINING)),
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 });

        // 3x3x3 3op edges permutation training
        Scrambler rubiksCube3OPEdgesPermutationTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-3OP-EDGES-PERMUTATION-TRAINING", "RUBIKS-CUBE",
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_3OP_EDGES_PERMUTATION_TRAINING)),
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 });

        // 3x3x3 3op edges orientation training
        Scrambler rubiksCube3OPEdgesOrientationTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-3OP-EDGES-ORIENTATION-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_3OP_EDGES_ORIENTATION_TRAINING)),
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0 },
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 });

        // 3x3x3 3op orientation training
        Scrambler rubiksCube3OPOrientationTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-3OP-ORIENTATION-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_3OP_ORIENTATION_TRAINING)),
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] {  0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10, 11 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 });

        // 3x3x3 3op permutation training
        Scrambler rubiksCube3OPPermutationTraining =
            new RubiksCubeRandomScrambler(
                new ScramblerInfo("RUBIKS-CUBE-3OP-PERMUTATION-TRAINING", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_3OP_PERMUTATION_TRAINING)),
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0 },
                new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                new byte[] {  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0 });

        // 3x3x3 bld single sticker cycle
        Scrambler rubiksCubeBLDSingleStickerCycle =
            new RubiksCubeSingleStickerCycleScrambler(
                new ScramblerInfo("RUBIKS-CUBE-BLD-SINGLE-STICKER-CYCLE", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_BLD_SINGLE_STICKER_CYCLE)));

        // 3x3x3 easy cross
        Scrambler rubiksCubeEasyCross =
            new RubiksCubeEasyCrossScrambler(
                new ScramblerInfo("RUBIKS-CUBE-EASY-CROSS", "RUBIKS-CUBE", 
                App.getContext().getResources().getString(R.string.RUBIKS_CUBE_EASY_CROSS)),
                3);

        // 4x4x4 importer
        Scrambler rubiksRevengeImporter =
            new EmptyScrambler(
                new ScramblerInfo("4x4x4-CUBE-IMPORTER", "4x4x4-CUBE", ""));

        // 4x4x4 random
        Scrambler rubiksRevengeRandom =
            new RubiksRevengeRandomScrambler(
                new ScramblerInfo("4x4x4-CUBE-RANDOM", "4x4x4-CUBE", 
                App.getContext().getResources().getString(R.string.x444_CUBE_RANDOM)),
                40);

        // 5x5x5 importer
        Scrambler professorsCubeImporter =
            new EmptyScrambler(
                new ScramblerInfo("5x5x5-CUBE-IMPORTER", "5x5x5-CUBE", ""));

        // 5x5x5 random
        Scrambler professorsCubeRandom =
            new ProfessorsCubeRandomScrambler(
                new ScramblerInfo("5x5x5-CUBE-RANDOM", "5x5x5-CUBE", 
                App.getContext().getResources().getString(R.string.x555_CUBE_RANDOM)),
                60);

        // 6x6x6 importer
        Scrambler vCube6Importer =
            new EmptyScrambler(
                new ScramblerInfo("6x6x6-CUBE-IMPORTER", "6x6x6-CUBE", ""));

        // 6x6x6 random
        Scrambler vCube6Random =
            new VCube6RandomScrambler(
                new ScramblerInfo("6x6x6-CUBE-RANDOM", "6x6x6-CUBE", 
                App.getContext().getResources().getString(R.string.x666_CUBE_RANDOM)),
                80);

        // 7x7x7 importer
        Scrambler vCube7Importer =
            new EmptyScrambler(
                new ScramblerInfo("7x7x7-CUBE-IMPORTER", "7x7x7-CUBE", ""));

        // 7x7x7 random
        Scrambler vCube7Random =
            new VCube7RandomScrambler(
                new ScramblerInfo("7x7x7-CUBE-RANDOM", "7x7x7-CUBE", 
                App.getContext().getResources().getString(R.string.x777_CUBE_RANDOM)),
                100);

        // rubiks clock importer
        Scrambler rubiksClockImporter =
            new EmptyScrambler(
                new ScramblerInfo("RUBIKS-CLOCK-IMPORTER", "RUBIKS-CLOCK", ""));

        // rubiks clock random
        Scrambler rubiksClockRandom =
            new RubiksClockRandomScrambler(
                new ScramblerInfo("RUBIKS-CLOCK-RANDOM", "RUBIKS-CLOCK", 
                App.getContext().getResources().getString(R.string.RUBIKS_CLOCK_RANDOM)));

        // megaminx importer
        Scrambler megaminxImporter =
            new EmptyScrambler(
                new ScramblerInfo("MEGAMINX-IMPORTER", "MEGAMINX", ""));

        // megaminx random
        Scrambler megaminxRandom =
            new MegaminxRandomScrambler(
                new ScramblerInfo("MEGAMINX-RANDOM", "MEGAMINX", 
                App.getContext().getResources().getString(R.string.MEGAMINX_RANDOM)));

        // pyraminx importer
        Scrambler pyraminxImporter =
            new EmptyScrambler(
                new ScramblerInfo("PYRAMINX-IMPORTER", "PYRAMINX", ""));

        // pyraminx random
        Scrambler pyraminxRandom =
            new PyraminxRandomScrambler(
                new ScramblerInfo("PYRAMINX-RANDOM", "PYRAMINX",
                App.getContext().getResources().getString(R.string.PYRAMINX_RANDOM)),
                0);

        // pyraminx random
        Scrambler pyraminxSuboptimalRandom =
            new PyraminxRandomScrambler(
                new ScramblerInfo("PYRAMINX-SUBOPTIMAL-RANDOM", "PYRAMINX", 
                App.getContext().getResources().getString(R.string.PYRAMINX_SUBOPTIMAL_RANDOM)),
                11);

        // pyraminx importer
        Scrambler square1Importer =
            new EmptyScrambler(
                new ScramblerInfo("SQUARE-1-IMPORTER", "SQUARE-1", ""));

        // square-1 random
        Scrambler square1Random =
            new Square1RandomScrambler(
                new ScramblerInfo("SQUARE-1-RANDOM", "SQUARE-1", 
                App.getContext().getResources().getString(R.string.SQUARE_1_RANDOM)));

        // square-1 cube shape
        Scrambler square1CubeShape =
            new Square1CubeShapeScrambler(
                new ScramblerInfo("SQUARE-1-CUBE-SHAPE", "SQUARE-1", 
                App.getContext().getResources().getString(R.string.SQUARE_1_CUBE_SHAPE)));

        // skewb importer
        Scrambler skewbImporter =
            new EmptyScrambler(
                new ScramblerInfo("SKEWB-IMPORTER", "SKEWB", ""));

        // skewb random
        Scrambler skewbRandom =
            new SkewbRandomScrambler(
                new ScramblerInfo("SKEWB-RANDOM", "SKEWB",
                App.getContext().getResources().getString(R.string.SKEWB_RANDOM)));

        // floppy cube importer
        Scrambler floppyCubeImporter =
            new EmptyScrambler(
                new ScramblerInfo("FLOPPY-CUBE-IMPORTER", "FLOPPY-CUBE", ""));

        // floppy cube random
        Scrambler floppyCubeRandom =
            new FloppyCubeRandomScrambler(
                new ScramblerInfo("FLOPPY-CUBE-RANDOM", "FLOPPY-CUBE", 
                App.getContext().getResources().getString(R.string.FLOPPY_CUBE_RANDOM)));

        // tower cube importer
        Scrambler towerCubeImporter =
            new EmptyScrambler(
                new ScramblerInfo("TOWER-CUBE-IMPORTER", "TOWER-CUBE", ""));

        // tower cube random
        Scrambler towerCubeRandom =
            new TowerCubeRandomScrambler(
                new ScramblerInfo("TOWER-CUBE-RANDOM", "TOWER-CUBE", 
                App.getContext().getResources().getString(R.string.TOWER_CUBE_RANDOM)));

        // rubiks tower importer
        Scrambler rubiksTowerImporter =
            new EmptyScrambler(
                new ScramblerInfo("RUBIKS-TOWER-IMPORTER", "RUBIKS-TOWER", ""));

        // rubiks tower random
        Scrambler rubiksTowerRandom =
            new RubiksTowerRandomScrambler(
                new ScramblerInfo("RUBIKS-TOWER-RANDOM", "RUBIKS-TOWER", 
                App.getContext().getResources().getString(R.string.RUBIKS_TOWER_RANDOM)));

        // rubik's domino importer
        Scrambler rubiksDominoImporter =
            new EmptyScrambler(
                new ScramblerInfo("RUBIKS-DOMINO-IMPORTER", "RUBIKS-DOMINO", ""));

        // rubik's domino random
        Scrambler rubiksDominoRandom =
            new RubiksDominoRandomScrambler(
                new ScramblerInfo("RUBIKS-DOMINO-RANDOM", "RUBIKS-DOMINO", 
                App.getContext().getResources().getString(R.string.RUBIKS_DOMINO_RANDOM)));

        // other importer
        Scrambler otherImporter =
            new EmptyScrambler(
                new ScramblerInfo("OTHER-IMPORTER", "OTHER", ""));

        // empty
        Scrambler empty =
            new EmptyScrambler(
                new ScramblerInfo("EMPTY", "OTHER", 
                App.getContext().getResources().getString(R.string.EMPTY)));

        this.scramblers = new Scrambler[] {
            rubiksPocketCubeImporter,
            rubiksPocketCubeRandom,
            rubiksPocketCubeURF,
            rubiksPocketCubeSuboptimalURF,
            rubiksCubeImporter,
            rubiksCubeRandom,
            rubiksCubeLU,
            rubiksCubeRU,
            rubiksCubeCLLTraining,
            rubiksCubeELLTraining,
            rubiksCubeFridrichF2LTraining,
            rubiksCubeFridrichOLLTraining,
            rubiksCubeFridrichPLLTraining,
            rubiksCube3OPCornersTraining,
            rubiksCube3OPCornersPermutationTraining,
            rubiksCube3OPCornersOrientationTraining,
            rubiksCube3OPEdgesTraining,
            rubiksCube3OPEdgesPermutationTraining,
            rubiksCube3OPEdgesOrientationTraining,
            rubiksCube3OPOrientationTraining,
            rubiksCube3OPPermutationTraining,
            rubiksCubeBLDSingleStickerCycle,
            rubiksCubeEasyCross,
            rubiksRevengeImporter,
            rubiksRevengeRandom,
            professorsCubeImporter,
            professorsCubeRandom,
            vCube6Importer,
            vCube6Random,
            vCube7Importer,
            vCube7Random,
            rubiksClockImporter,
            rubiksClockRandom,
            megaminxImporter,
            megaminxRandom,
            pyraminxImporter,
            pyraminxRandom,
            pyraminxSuboptimalRandom,
            skewbImporter,
            skewbRandom,
            square1Importer,
            square1Random,
            square1CubeShape,
            floppyCubeImporter,
            floppyCubeRandom,
            towerCubeImporter,
            towerCubeRandom,
            rubiksTowerImporter,
            rubiksTowerRandom,
            rubiksDominoImporter,
            rubiksDominoRandom,
            otherImporter,
            empty,
        };

        this.scramblerMap = new HashMap<String, Scrambler>();
        for (Scrambler scrambler : this.scramblers) {
            this.scramblerMap.put(scrambler.getScramblerInfo().getScramblerId(), scrambler);
        }
    }

    public Scrambler[] getAll() {
        ArrayList<Scrambler> scramblers = new ArrayList<Scrambler>();
        for (Scrambler scrambler : this.scramblers) {
            if (!scrambler.getScramblerInfo().getScramblerId().endsWith("-IMPORTER")) {
                scramblers.add(scrambler);
            }
        }

        Scrambler[] scramblersArray = new Scrambler[scramblers.size()];
        scramblers.toArray(scramblersArray);

        return scramblersArray;
    }

    public Scrambler get(String scramblerId) {
        return this.scramblerMap.get(scramblerId);
    }
}
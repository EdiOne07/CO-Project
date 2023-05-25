package Backend;


import java.util.Arrays;

public class GetInfo {
    private HashMap<String, Integer> information;
    private int scoreDigitsOfPi;
    private int scoreMatrixMultiplication;
    private int scoreMonteCarlo;
    private int scoreRandomAcces;
    private int scoreHddWrite;

    public GetInfo() {
        this.information = new HashMap<>();
        this.scoreDigitsOfPi = 0;
        this.scoreMatrixMultiplication = 0;
        this.scoreMonteCarlo = 0;
        this.scoreRandomAcces = 0;
        this.scoreHddWrite = 0;
        information.put("Monte Carlo",0);
        information.put("Gauss-Legendre", 0);
        information.put("Matrix Multiplication", 0);
        information.put("Read/Write Memory", 0);
        information.put("Read/Write Memory Random", 0);
    }

    public void setInformation(String test, Integer score) {
       information.put(test, score);
    }

    public int getScoreDigitsOfPi() {
        return scoreDigitsOfPi;
    }

    public void setScoreDigitsOfPi(int scoreDigitsOfPi) {
        this.scoreDigitsOfPi = scoreDigitsOfPi;
    }

    public int getScoreMatrixMultiplication() {
        return scoreMatrixMultiplication;
    }

    public void setScoreMatrixMultiplication(int scoreMatrixMultiplication) {
        this.scoreMatrixMultiplication = scoreMatrixMultiplication;
    }

    public int getScoreMonteCarlo() {
        return scoreMonteCarlo;
    }

    public void setScoreMonteCarlo(int scoreMonteCarlo) {
        this.scoreMonteCarlo = scoreMonteCarlo;
    }

    public int getScoreRandomAcces() {
        return scoreRandomAcces;
    }

    public void setScoreRandomAcces(int scoreRandomAcces) {
        this.scoreRandomAcces = scoreRandomAcces;
    }

    public int getScoreHddWrite() {
        return scoreHddWrite;
    }

    public void setScoreHddWrite(int scoreHddWrite) {
        this.scoreHddWrite = scoreHddWrite;
    }

    public HashMap<String, Integer> getInformation() {
        return information;
    }
}

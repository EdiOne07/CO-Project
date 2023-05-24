package Backend;


public class GetInfo {
    protected String[] information= new String[7];
    protected String scoreDigitsOfPi="";
    protected String scoreMatrixMultiplication="";
    protected String scoreMonteCarlo="";
    protected String scoreRandomAcces="";
    protected String scoreHddWrite="";

    public void setScoreDigitsOfPi(int scoreDigitsOfPi) {
        String v=Integer.toString(scoreDigitsOfPi);
        this.scoreDigitsOfPi = v;
        this.information[0]=v;
        System.out.println("Test:"+ information[0]);
    }

    public String setScoreMatrixMultiplication(int scoreMatrixMultiplication) {
        String v=Integer.toString(scoreMatrixMultiplication);
        this.scoreMatrixMultiplication = v;
       return this.information[1]=v;
    }

    public String setScoreMonteCarlo(int scoreMonteCarlo) {
        String v=Integer.toString(scoreMonteCarlo);
        this.scoreMonteCarlo = v;
        return information[2]=v;
    }

    public String setScoreRandomAcces(int scoreRandomAcces) {
        String v=Integer.toString(scoreRandomAcces);
        this.scoreRandomAcces = v;
        return this.information[3]=v;

    }

    public String setScoreHddWrite(int scoreHddWrite) {
        String v=Integer.toString(scoreHddWrite);
        this.scoreHddWrite = v;
        return this.information[4]=v;
    }

    /*public void storeInfo(){
        information[0]=scoreDigitsOfPi;
        System.out.println("Problem here??:"+ information[0]);
        information[1]=scoreMatrixMultiplication;
        information[2]=scoreMonteCarlo;
        information[3]=scoreHddWrite;
        information[4]=scoreRandomAcces;

    }*/

    public String[] getInfo(){
        return this.information;
    }
}

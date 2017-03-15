package model;

/**
 * Created by brian on 15/03/17.
 */
public class Breaking {


    private double breakingEfficiency;
    private int numberSkidMarks;

    public Breaking() { }


    public double getBreakingEfficiency() {
        return breakingEfficiency;
    }


    public int getNumberSkidMarks(Skid skid) {
        return numberSkidMarks = skid.getNumberOfSkids();
    }


    public double setBreakingEfficiency(int numberSkidMarks) {
        if (numberSkidMarks == 4) {
            breakingEfficiency = 1.0;
        } else if (numberSkidMarks == 3) {
            breakingEfficiency = 0.7;
        } else if (numberSkidMarks == 2) {
            breakingEfficiency = 0.6;
        } else if (numberSkidMarks == 1) {
            breakingEfficiency = 0.3;
        }
        return this.breakingEfficiency;
    }

}

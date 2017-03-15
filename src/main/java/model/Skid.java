package model;

import java.util.ArrayList;

/**
 * Created by brian on 15/03/17.
 */
public class Skid {

    private int numberOfSkids;
    private ArrayList<Double> skidList;
    private double averageSkidDistance;


    public Skid() { }

    public Skid(int numberOfSkids, ArrayList<Double> skidList) {
        this.numberOfSkids = numberOfSkids;
        this.skidList = skidList;
        setAverageSkidDistance(skidList);
    }

    public void addSkidLengths(double skidLength) {
        this.skidList.add(skidLength);
    }

    public ArrayList<Double> getSkidList() {
        return skidList;
    }

    public void setSkidList(ArrayList<Double> skidList) {
        this.skidList = skidList;
    }

    public int getNumberOfSkids() {
        return numberOfSkids;
    }

    public void setNumberOfSkids(int numberOfSkids) {
        this.numberOfSkids = numberOfSkids;
    }


    public double getAverageSkidDistance() {
        return this.averageSkidDistance;
    }

    public void setAverageSkidDistance(ArrayList<Double> skidList) {

        double sum = 0;
        for(double length : skidList) {
            sum += length;
        }

        this.averageSkidDistance = sum / this.numberOfSkids;
    }


}

package beans;

import java.util.List;

/**
 * Created by brian on 23/02/17.
 */
public class CarBean {

    // class properties
    private String carName;
    private int numSkidMarks = 0;
    private double skidMark1;
    private double skidMark2;
    private double skidMark3;
    private double skidMark4;
    private String surfaceType;
    private List<Double> skidmarkList;

    // class variables
    private String title = "to be changed";
    private String numericRegex = "\\d+";
    private String stringRegex = "^[A-Za-z, ]++$";
    private String errorMessage = "";


    /*** no arg constructor is required ***/
    public CarBean() {
    }


    public CarBean(String carName, int numSkidMarks, double skidMark1, double skidMark2, double skidMark3,
                   double skidMark4, String surfaceType) {
        this.carName = carName;
        this.numSkidMarks = numSkidMarks;
        this.skidMark1 = skidMark1;
        this.skidMark2 = skidMark2;
        this.skidMark3 = skidMark3;
        this.skidMark4 = skidMark4;
        this.surfaceType = surfaceType;
    }

    // accessor method - allow the client to retrieve the state of the object
    public String getCarName() {
        return carName;
    }

    // mutator method - allow the client change the state of the object
    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setNumSkidMarks(int numSkidMarks) {
        this.numSkidMarks = numSkidMarks;
    }

    public int getNumSkidMarks() {
        return numSkidMarks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getSkidMark1() {
        return skidMark1;
    }

    public void setSkidMark1(double skidMark1) {
        this.skidMark1 = skidMark1;
    }

    public double getSkidMark2() {
        return skidMark2;
    }

    public void setSkidMark2(double skidMark2) {
        this.skidMark2 = skidMark2;
    }

    public double getSkidMark3() {
        return skidMark3;
    }

    public void setSkidMark3(double skidMark3) {
        this.skidMark3 = skidMark3;
    }

    public double getSkidMark4() {
        return skidMark4;
    }

    public void setSkidMark4(double skidMark4) {
        this.skidMark4 = skidMark4;
    }

    public String getSurfaceType() {
        return surfaceType;
    }

    public void setSurfaceType(String surfaceType) {
        this.surfaceType = surfaceType;
    }

    public List<Double> getSkidmarkList() {
        return skidmarkList;
    }

    public void setSkidmarkList(List<Double> skidmarkList) {
        this.skidmarkList = skidmarkList;
    }


    public boolean isCarNameValid() {
        if (this.carName.matches(stringRegex)) {
            return true;
        }
        return false;
    }


    public boolean isSkidMarkLengthValid(Double skidLength) {
        if (skidLength > 0) {
            return true;
        }
        return false;
    }
}


























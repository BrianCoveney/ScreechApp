package model;

import java.util.List;

/**
 * Created by brian on 23/02/17.
 */
public class CarBean {

    // class properties
    private String carName;
    private int numSkidMarks = 0;
    private String surfaceType;
    private List<Double> skidmarkList;

    // class variables
    private String title = "to be changed";
    private String stringRegex = "^[A-Za-z, ]++$";


    /*** no arg constructor is required ***/
    public CarBean() {
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


























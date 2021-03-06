/*
    Author: Brian Coveney
    Date: 24/02/2017

    COMP8007 OO Server Side Programming
    Assignment 1
 */

package model;

public class CarBean {

    // class properties
    private String carName;
    private int numSkidMarks = 0;

    // class variable
    private String stringRegex = "^[A-Za-z, ]++$";


    // A JavaBean is a public class with default (no argument) constructor.
    // @link { http://wiki.apidesign.org/wiki/JavaBean }
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


    // Validation methods

    public boolean isCarNameValid() {
        if (this.carName.matches(stringRegex)) {
            return true;
        }
        return false;
    }


    public boolean isSkidMarkLengthValid(Double skidLength) {
        if (skidLength >= 0) {
            return true;
        }
        return false;
    }
}


























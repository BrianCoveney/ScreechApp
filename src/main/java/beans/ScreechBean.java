package beans;

/**
 * Created by brian on 23/02/17.
 */
public class ScreechBean {

    // encapsulate properties / attributes of the class
    private String title = "to be changed";
    private String carName;
    private int numSkidMarks = 0;
    private String numericRegex = "\\d+";
    private String errorMessage = "";


    /*** no arg constructor is required ***/
    public ScreechBean() {  }


    public ScreechBean(String carName, int numSkidMarks) {
        this.carName = carName;
        setNumSkidMarks(numSkidMarks);
    }


    // accessor method - allow the client to retrieve the state of the object
    public String getCarName() { return carName; }

    // mutator method - allow the client change the state of the object
    public void setCarName(String carName) { this.carName = carName; }

    public void setNumSkidMarks(int numSkidMarks) {
        validateSkidMarks();
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


    public boolean validateSkidMarks() {
        String skidMarks = String.valueOf(this.numSkidMarks);
        if(!skidMarks.equals(numericRegex)) {
            errorMessage = "Number of skid marks must be numeric";
            return false;
        }
        return true;
    }


    public String getMessage() {
        return errorMessage;
    }




}

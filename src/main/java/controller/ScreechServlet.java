package controller;

import model.CarBean;
import model.Surface;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    COMP8007 OO Server Side Programming
    Assignment 1
    Student: Brian Coveney
    Date: 24/02/2017
    Rev: 4

    (Ref James O. Harris at: https://drive.google.com/file/d/0B63wvMT2tuKhZWNOa2swMHZsMlU/view)
    The minimum skid speed formula
    S = √30 * D * f * n
    Where -
    S = Speed, in miles per hour.
    30 = A constant value used in this equation.
    D = Skid Distance, in decimal feet and inches.
    f = Drag factor for the road surface.
    n = Braking efficiency as a percent.
 */

@WebServlet("/ScreechServlet")
public class ScreechServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // a constant value used in this equation.
    private static final int EQUATION_CONST = 30;
    // array of road surface types
    private String surfaceTypes[] = {"Cement", "Asphalt", "Gravel", "Ice", "Snow"};
    private String carName;
    private double skidLen1, skidLen2, skidLen3, skidLen4, skid1, skid2, skid3, skid4;
    private int numOfSkidMarks;
    private double averageSkidLength = 0;
    private String surfaceType;
    private double breakingEfficiency;
    private double dragFactor;
    private CarBean carBean;


    public ScreechServlet() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // set vars equal to form parameters
        carName = request.getParameter("carname");

        try {
            numOfSkidMarks = Integer.parseInt(request.getParameter("skidmarks"));
            request.getSession().removeAttribute("message");
        } catch (Exception e) {
            // set error message
            request.getSession().setAttribute("message", "Must be a number!!");
            // redirect to index.jsp
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }



        List<Double> skidList = new ArrayList<>(Arrays.asList(skidLen1, skidLen2, skidLen3, skidLen4));

        // checks that input is a number, and display error(s) if not
        int x = 1;
        for (Double skid : skidList) {
            try {
                skid = Double.parseDouble(request.getParameter("skidmarklength" + x));
                request.getSession().removeAttribute("errorMsgSkid" + x);

            } catch (NumberFormatException e) {
                request.getSession().setAttribute("errorMsgSkid"+ x, "Must be a number!!");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
            x++;
        }


        skid1 = Double.parseDouble(request.getParameter("skidmarklength1"));
        skid2 = Double.parseDouble(request.getParameter("skidmarklength2"));
        skid3 = Double.parseDouble(request.getParameter("skidmarklength3"));
        skid4 = Double.parseDouble(request.getParameter("skidmarklength4"));

        List<Double> skidListCopy = new ArrayList<>(Arrays.asList(skid1, skid2, skid3, skid4));

        carBean = new CarBean(
                carName, numOfSkidMarks, skid1, skid2, skid3, skid4, surfaceType);


        // check that each skid length is > 0, and display error(s) if not
        for(int i = 0; i < skidListCopy.size(); i++) {

            if(carBean.isSkidMarkLengthValid(skidListCopy.get(i))) {
                request.getSession().removeAttribute("errorMsg2Skid" + i);
            } else {
                // set error message
                request.getSession().setAttribute("errorMsg2Skid" + i, "Please check your skid length entry");
                // redirect to index.jsp
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }


        surfaceType = request.getParameter("surface");



        /*** Validating that car name is a string ***/
        //if car name is a string - remove the errorMessage on page load, and after user corrects input value
        if (carBean.isCarNameValid()) {
            request.getSession().removeAttribute("errorMsg");
        } else {
            request.getSession().setAttribute("errorMsg", "Please check you car name entry");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }


        // set variable equal to the return from the method No. 1 calculateAverageSkidDistance()
        double avgSkidLength = calculateAverageSkidDistance(numOfSkidMarks, skid1, skid2, skid3, skid4);

        // drag factor
        double dragFactor = setDragFactor(surfaceType);





        // breaking efficiency
        double breakEff = calculateBreakingEfficiency(numOfSkidMarks);

        // set variable equal to the return from the method No. 3 calculateSpeed(...)
        double result = calculateSpeed(avgSkidLength, dragFactor, breakEff);

        // create html from the method No. 4 createHTMLDoc(...)
        StringBuffer stringBuffer = createHTMLDoc(result, skid1, skid2, skid3, skid4, surfaceType);

        response.setContentType("text/html"); // content type
        PrintWriter printWriter = response.getWriter();
        printWriter.println(stringBuffer);
        printWriter.close();
    }


    // set value based on checkbox selection
    public double setDragFactor(String choice) {
        if (choice.equalsIgnoreCase(Surface.CEMENT.toString())){
            dragFactor = Surface.CEMENT.setDragFactor(dragFactor);
        }
        else if (choice.equalsIgnoreCase(Surface.ASPHALT.toString())) {
            dragFactor = Surface.ASPHALT.setDragFactor(dragFactor);
        }
        else if (choice.equalsIgnoreCase(Surface.GRAVEL.toString())) {
            dragFactor = Surface.GRAVEL.setDragFactor(dragFactor);
        }
        else if (choice.equalsIgnoreCase(Surface.SNOW.toString())) {
            dragFactor = Surface.SNOW.setDragFactor(dragFactor);
        }
        else if (choice.equalsIgnoreCase(Surface.ICE.toString())) {
            dragFactor = Surface.ICE.setDragFactor(dragFactor);
        }
        return dragFactor;
    }


    // calculate the average skid distance
    public double calculateAverageSkidDistance(int numberOfSkidMarks, double sk1, double sk2, double sk3, double sk4) {
        return averageSkidLength = (sk1 + sk2 + sk3 + sk4) / numberOfSkidMarks;
    }


    // calculate breaking efficiency based on number of skid marks
    public double calculateBreakingEfficiency(int numberOfSkidMarks) {
        if (numberOfSkidMarks == 4) {
            breakingEfficiency = 1.0;
        } else if (numberOfSkidMarks == 3) {
            breakingEfficiency = 0.7;
        } else if (numberOfSkidMarks == 2) {
            breakingEfficiency = 0.6;
        } else if (numberOfSkidMarks == 1) {
            breakingEfficiency = 0.3;
        }
        return breakingEfficiency;
    }


    // calculate the result, using the formula:   S = √30 * D * f * n
    public double calculateSpeed(double skidDist, double dragFact, double brakeEfficiency) {
        double product = EQUATION_CONST * skidDist * dragFact;
        double total = Math.sqrt(product);
        double totalRounded = Math.round(total * 10) / 10.0; // to one decimal place
        return totalRounded;
    }


    // create and send HTML to the client
    public StringBuffer createHTMLDoc(double res, double sm1, double sm2, double sm3, double sm4, String sur) {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("<html><head>\n");
        stringBuff.append("<title>Screech GET Result</title>\n");
        stringBuff.append("<style> table, th, td { border: 1px solid black; border-collapse: collapse; } th, tr,td { padding: 10px; }</style>");
        stringBuff.append("</head><body>\n");
        stringBuff.append("<jsp:getProperty name='CarBean' property='skidMarks'/>");
        stringBuff.append("<h3>Base on your figures, the skid details for the " + carName + " are:</h3>\n");
        stringBuff.append("<table>");
        stringBuff.append("<tr><th>Avg skid distance</th><th>Surface type</th><th>Calculated Speed</th></tr>");
        stringBuff.append("<tr><td>" + averageSkidLength + "feet</td><td>" + sur + "</td><td>" + res + "mph</td></tr>");
        stringBuff.append("<table>");
        stringBuff.append("</body></html>");
        return stringBuff;
    }

}
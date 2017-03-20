/*
    Author: Brian Coveney
    Date: 24/02/2017

    COMP8007 OO Server Side Programming
    Assignment 1

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

package controller;

import helpers.Const;
import model.Breaking;
import model.CarBean;
import model.Skid;
import model.SurfaceType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;



@WebServlet("/ScreechServlet")
public class ScreechServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // a constant value used in the equation
    private static final int EQUATION_CONST = 30;
    private String surfaceTypes[] = {"Cement", "Asphalt", "Gravel", "Ice", "Snow"};
    private String carName;
    private double skid1, skid2, skid3, skid4;
    private int numOfSkidMarks;
    private int averageSkidLength = 0;
    private String surfaceChoice;
    private double breakingEfficiency;
    private double dragFactor;
    private double speed;
    private ArrayList<Double> skidList;
    private Cookie cookie;
    private Cookie cookies[];
    private String cookieValue = "";
    private String cookieString;

    // references to objects
    private Skid skid;
    private CarBean carBean;
    private Breaking breaking;


    public ScreechServlet() {
        super();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter output;

        cookieString = request.getParameter("surface");
        cookie = new Cookie("surface", getSurfaceCookies(cookieString));
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        cookies = request.getCookies();

        response.setContentType( "text/html" );
        output = response.getWriter();

        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                cookieValue = cookies[i].getValue();
            }

            String surfaceCookie = getSurfaceCookies(cookieValue);
            output.print(surfaceCookie);
        }



        /*** Validation ***/

        // Validate number of skid marks
        try {
            numOfSkidMarks = Integer.parseInt(request.getParameter("skidmarks"));
            request.getSession().removeAttribute("message");
        } catch (Exception e) {
            // set error message
            request.getSession().setAttribute("message", "Must be a number!!");
            // redirect to index.jsp
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        if (!(numOfSkidMarks < 1)) {
            request.getSession().removeAttribute("message");
        } else {
            request.getSession().setAttribute("message", "There must be a least one skid!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }


        // Validate the skid marks - checks that input is a number, and display error(s) if not
        skidList = new ArrayList<>(Arrays.asList(skid1, skid2, skid3, skid4));

        // Loop through the list and try to extract a Double
        // - if successful remove the error message from the current session associated with this request
        // - otherwise, set an error message along side the offending input
        int i = 0; // my counter
        for (Double skid : skidList) {
            try {
                skid = Double.parseDouble(request.getParameter("skidmarklength" + i));
                request.getSession().removeAttribute("errorMsgSkid" + i);

            } catch (NumberFormatException e) {
                // set error message
                request.getSession().setAttribute("errorMsgSkid"+ i, "Needs to be a number!!");
                // redirect to index.jsp
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
            i++; // increment the loop
        }



        /*** set vars equal to form parameters ***/
        carName = request.getParameter("carname");
        skid1 = Double.parseDouble(request.getParameter("skidmarklength0"));
        skid2 = Double.parseDouble(request.getParameter("skidmarklength1"));
        skid3 = Double.parseDouble(request.getParameter("skidmarklength2"));
        skid4 = Double.parseDouble(request.getParameter("skidmarklength3"));
        surfaceChoice = request.getParameter("surface");

        // As the list was only attempting to extract a Double for validation purposes,
        // we will remove all elements currently the list
        skidList.clear();

        // We can now use this empty list to hold the above parameters
        skidList = new ArrayList<>(Arrays.asList(skid1, skid2, skid3, skid4));



        // Create 'CarBean' object and populate it
        carBean = new CarBean();
        carBean.setCarName(carName);
        carBean.setNumSkidMarks(numOfSkidMarks);


        // check that each skid length is > 0, and display error(s) if not
        for(int j = 0; j < skidList.size(); j++) {

            if(carBean.isSkidMarkLengthValid(skidList.get(j))) {
                request.getSession().removeAttribute("errorMsg2Skid" + j);
            } else {
                request.getSession().setAttribute("errorMsg2Skid" + j, "Input error!");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }



        /*** Validating that car name is a string ***/
        //if car name is a string - remove the errorMessage on page load, and after user corrects input value
        if (carBean.isCarNameValid()) {
            request.getSession().removeAttribute("errorMsg");
        } else {
            request.getSession().setAttribute("errorMsg", "Please check you car name entry");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        /*** end Validation ***/



        // calculation methods
        skidDistance();

        breakingEfficiency();

        setDragFactor(surfaceChoice);

        calculateSpeed(averageSkidLength, dragFactor, breakingEfficiency);


        // create page containing calculation results
        StringBuffer stringBuffer = createHTMLDoc();

        response.setContentType("text/html"); // content type
        PrintWriter printWriter = response.getWriter();
        printWriter.println(stringBuffer);
        printWriter.close();

    } // end doGet()



    private double skidDistance() {
        // Create 'Skid' object and populate it
        // then set class scoped variable equal to the objects getAverageSkidDistance()
        skid = new Skid();
        skid.setNumberOfSkids(carBean.getNumSkidMarks());
        skid.setSkidList(skidList);
        skid.setAverageSkidDistance();

        // set class scoped variable equal to the returned avg skid distance
        return averageSkidLength = skid.getAverageSkidDistance();
    }


    // return braking efficiency
    private double breakingEfficiency() {
        // Create 'Breaking' object and populate it
        // set breaking efficiency based on number of skid marks
        breaking = new Breaking();
        breaking.getNumberSkidMarks(skid);
        breaking.setBreakingEfficiency(skid.getNumberOfSkids());
        return breakingEfficiency = breaking.getBreakingEfficiency();
    }


    // set drag factor value based on users checkbox selection
    public double setDragFactor(String surfaceChoice) {
        SurfaceType surfaceType = new SurfaceType();
        surfaceType.setDragFactor(surfaceChoice);
        dragFactor = surfaceType.getDragFactor();
        return dragFactor;
    }


    // calculate the result, using the formula:   S = √30 * D * f * n
    public double calculateSpeed(double skidDist, double dragFact, double brakeEfficiency) {
        double product = EQUATION_CONST * skidDist * dragFact;
        double total = Math.sqrt(product);
        speed = Math.round(total * 10) / 10.0; // round to one decimal place
        return speed;
    }


    public String getSurfaceCookies(String surface) {
        for (int i = 0; i < surfaceTypes.length; i++) {
            if(surface.equals(surfaceTypes[i])) {
                return surfaceTypes[i];
            }
        }
        return "";
    }




    // create and send HTML to the client
    public StringBuffer createHTMLDoc() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("<html><head>\n");
        stringBuff.append("<title>Screech GET Result</title>\n");
        stringBuff.append("<style> table, th, td { border: 1px solid black; border-collapse: collapse; } th, tr,td { padding: 10px; }</style>");
        stringBuff.append("</head><body>\n");
        stringBuff.append("<jsp:getProperty name='CarBean' property='skidMarks'/>");
        stringBuff.append("<h3>Base on your figures, the skid details for the " + carName + " are:</h3>\n\n");
        stringBuff.append("<table>");
        stringBuff.append("<tr><th>Avg skid distance</th><th>Surface type</th><th>Breaking Efficiency</th><th>Calculated Speed</th></tr>");
        stringBuff.append("<tr><td>" + averageSkidLength + "'</td>");
        stringBuff.append("<td>" + getSurfaceCookies(cookieString) + "</td>");
        stringBuff.append("<td>"+ Const.displayPercent(Locale.ENGLISH, breakingEfficiency) +"</td>");
        stringBuff.append("<td>" + speed + "mph</td></tr>");
        stringBuff.append("<table>");
        stringBuff.append("</body></html>");
        return stringBuff;
    }

}
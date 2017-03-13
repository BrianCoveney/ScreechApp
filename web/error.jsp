<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:useBean id="ScreechBean" scope="session" class="model.CarBean"/>
    <jsp:setProperty name="ScreechBean" property="*"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Screech</title>

    <%-- 1/5 Implicit object:
    'pageContext' provides access to the request object, which recives the contextPath  --%>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet.css">

</head>
<form method="get" action="ScreechServlet" method="post">
    <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>
    <fieldset>
        <p class="text">Calculate speed of your car from it's skid marks</p>
    </fieldset>
    <fieldset>
        <label class="carname_lable">Name of car:</label>

        <%-- For each form input we use request.getParameter(...) to retrieve and display the user input value from index.jsp  --%>
        <input type="text" name="carname" value="<%= request.getParameter("carname") %>"><span>${errorMsg}</span><br>
        <label>Number of skid marks:</label>
        <input type="text" value="<%= request.getParameter("skidmarks") %>" name="skidmarks" class="skid_marks">
        <span>${errorMsg2}</span><span>${message}</span><br>
        <div>
            <label>Skidmark length no. 1:</label>
            <input type="text" name="skidmarklength1" value="<%= request.getParameter("skidmarklength1") %>"  class="skid_lenghts one">
            <span>${errorMsgSkid1}</span><span>${errorMsg2Skid0}</span><br>

            <label>Skidmark length no. 2:</label>
            <input type="text" name="skidmarklength2" value="<%= request.getParameter("skidmarklength2") %>"  class="skid_lenghts">
            <span>${errorMsgSkid2}</span><span>${errorMsg2Skid1}</span><br>

            <label>Skidmark length no. 3:</label>
            <input type="text" name="skidmarklength3" value="<%= request.getParameter("skidmarklength3") %>"  class="skid_lenghts">
            <span>${errorMsgSkid3}</span><span>${errorMsg2Skid2}</span><br>

            <label>Skidmark length no. 4:</label>
            <input type="text"  name="skidmarklength4" value="<%= request.getParameter("skidmarklength4") %>"  class="skid_lenghts">
            <span>${errorMsgSkid4}</span><span>${errorMsg2Skid3}</span><br>
        </div>
        <label>Enter type of road surface:</label><br>
        <input type="radio" name="surface" value="Cement" class="radio_btn" checked>Portland Cement<br>
        <input type="radio" name="surface" value="Asphalt" class="radio_btn">Asphalt<br>
        <input type="radio" name="surface" value="Gravel" class="radio_btn">Gravel<br>
        <input type="radio" name="surface" value="Ice" class="radio_btn">Ice<br>
        <input type="radio" name="surface" value="Snow" class="radio_btn">Snow<br>
    </fieldset>
    <fieldset>
        <input class="submit_btn" type="submit" name="submit" value="Submit">
        <input class="reset_btn" type="reset" name="reset" value="Reset">
    </fieldset>
</form>
</body>
</html>
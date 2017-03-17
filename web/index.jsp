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
<jsp:include page="headder.jsp"></jsp:include>
<form method="get" action="ScreechServlet" method="post">

    <%--Hidden form field is used to maintain the session--%>
    <input type="hidden" name="from" value="${pageContext.request.requestURI}"/>

    <label class="carname_lable">Name of car:</label>
        <input type="text" name="carname"><span>${errorMsg}</span><br>
        <label>Number of skid marks:</label>
        <input type="text" name="skidmarks" class="skid_marks"><span
            style="color: #FF0000;"><span>${errorMsg2}</span></span><br>
        <div>
            <label>Skidmark length no. 1:</label>
            <input type="text" value="0" name="skidmarklength1" class="skid_lenghts one">
            <span>${errorMsgSkid1}</span><br>
            <label>Skidmark length no. 2:</label>
            <input type="test" value="0" name="skidmarklength2" class="skid_lenghts">
            <span>${errorMsgSkid2}</span><br>
            <label>Skidmark length no. 3:</label>
            <input type="text" value="0" name="skidmarklength3" class="skid_lenghts">
            <span>${errorMsgSkid3}</span><br>
            <label>Skidmark length no. 4:</label>
            <input type="text" value="0" name="skidmarklength4" class="skid_lenghts">
            <span>${errorMsgSkid4}</span><br>
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
<footer>
    <%-- include Directive  --%>
    <%@include file="footer.jsp"%>
</footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:useBean id="ScreechBean" scope="session" class="beans.ScreechBean"/>
    <jsp:setProperty name="ScreechBean" property="*"/>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Screech</title>

    <style type="text/css">

        * {
            margin-bottom: 10px;
        }

        .submit_btn{
            margin-right: 30px;
        }

        .skid_lenghts{
            display: block;
            min-width: 150px;
        }

        input {
            margin-left: 20px;
        }
    </style>

</head>
<form method="get" action="ScreechServlet" method="post">

    <strong class="test">Calculate speed of your car from it's skid marks<br>
    </strong><br>


    <label>Enter name of car:</label><br>
    <input type="text" name="skidlength"><br>

    <label>Enter Number of skid marks:</label><br>
    <input type="text" name="skidmark">

    <div>
        <label>Skidmark Length No. 1:</label>
        <input type="number" min="0" max="100" step="0.5" value="0" name="skidmarklength1" class="skid_lenghts">
        <label>Skidmark Length No. 2:</label>
        <input type="number" min="0" max="100" step="0.5" value="0" name="skidmarklength2" class="skid_lenghts">
        <label>Skidmark Length No. 3:</label>
        <input type="number" min="0" max="100" step="0.5" value="0" name="skidmarklength3" class="skid_lenghts">
        <label>Skidmark Length No. 4:</label>
        <input type="number" min="0" max="100" step="0.5" value="0" name="skidmarklength4" class="skid_lenghts">
    </div>

    <label>Enter type of road surface:</label><br>
    <input type="radio" name="surface" value="Cement">Portland Cement<br>
    <input type="radio" name="surface" value="Asphalt">Asphalt<br>
    <input type="radio" name="surface" value="Gravel">Gravel<br>
    <input type="radio" name="surface" value="Ice">Ice<br>
    <input type="radio" name="surface" value="Snow">Snow<br>


    <input class="submit_btn" type="submit" name="submit" value="Submit">
    <input type="reset" name="reset" value="Reset">

</form>


</body>
</html>
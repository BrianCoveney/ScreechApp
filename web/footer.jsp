<!--
- Author: Brian Coveney
- Date: 20/03/2017
-->
<%-- page Directives --%>
<%@ page language="java"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>


    <%-- Declarations --%>
    <%!
        private final String STU_NAME = "Brian Coveney";
        private final String STU_ID = "R00105727";
        private final String STU_COLLEGE = "CIT";
        private final String YEAR_PETTERN = "yyy";
        private String year;
        private Calendar cal = Calendar.getInstance();
        private SimpleDateFormat simpleDateFormat;
    %>

    <div style="margin-left: 150px; margin-top: 50px">

        <%-- Expressions --%>
        <%= STU_COLLEGE + " | " +  STU_NAME + " | " + STU_ID + " | " %>


        <%-- Scriptlet --%>
        <%
            simpleDateFormat = new SimpleDateFormat(YEAR_PETTERN);
            year = simpleDateFormat.format(cal.getTime());
            out.print(year);
        %>

    </div>

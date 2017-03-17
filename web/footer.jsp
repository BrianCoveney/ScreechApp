
<%-- page Directives --%>
<%@ page language="java"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>


    <%-- Declarations --%>
    <%!
        private String name = "Brian Coveney";
        private String id = "R00105727";
        private String college = "CIT";
        private Calendar cal = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        String year = simpleDateFormat.format(cal.getTime());
    %>

    <div style="margin-left: 150px; margin-top: 50px">

        <%-- Expressions --%>
        <%= college + " | " +  name + " | " + id + " | " %>

        <%-- Scriptlet --%>
        <% out.println(year);  %>

    </div>
</body>
</html>
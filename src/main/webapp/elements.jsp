<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body {
            background-color: floralwhite;
            font-family: sans-serif;
            font-size: larger;
        }
        table {
            border: 2px dashed saddlebrown;
        }
        td {
            border: 1px solid sandybrown;
            padding: 5px;
            text-align: center;
        }
    </style>
    <title>JSP Elements: <%= new java.util.Date() %></title>
</head>
<body>
    <jsp:include page="header.jsp" />

    <%!
        public String createTableDetail(int num) {
            return "<td>" + num + "</td>";
        }
    %>

    <table>
        <%
            for (int row = 1; row < 5; row++) {
                out.print("<tr>");
                for (int col = 1; col < 5; col++) {
                   out.print(createTableDetail(row * col));
                }
                out.print("</tr>");
            }
        %>
    </table>

    <%@include file="footer.jsp"%>
</body>
</html>

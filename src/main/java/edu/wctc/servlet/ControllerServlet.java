package edu.wctc.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ControllerServlet", urlPatterns = "/controller")
public class ControllerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageIndex = request.getParameter("page");
        String jsp = "notFound.jsp";
        switch (pageIndex) {
            case "one":
                jsp = "one.jsp";
                break;
            case "two":
                jsp = "two.jsp";
                break;
            case "three":
                jsp = "three.jsp";
                break;
            case "four":
                jsp = "four.jsp";
                break;
        }

        request.getRequestDispatcher(jsp).forward(request, response);
    }
}

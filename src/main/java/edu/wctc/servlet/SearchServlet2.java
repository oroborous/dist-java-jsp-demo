package edu.wctc.servlet;

import edu.wctc.DatabaseUtils;
import edu.wctc.entity.Pet;
import edu.wctc.entity.PetDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet2", urlPatterns = "/search2")
public class SearchServlet2 extends HttpServlet {
    private final String DRIVER_NAME = "jdbc:derby:";
    private final String DATABASE_PATH = "../../db";
    private final String SCHEMA = "stacy";
    private final String PASSWORD = "stacy";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        try {
            String searchTerm = request.getParameter("speciesName");

            // Load the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            // Find the absolute path of the database folder
            String absPath = getServletContext().getRealPath("/") + DATABASE_PATH;

            // Build the query as a String
            StringBuilder sql = new StringBuilder("select nm, age, favorite_toy, weight, nickname ");
            sql.append("from pet ");
            sql.append("join pet_detail on (pet.pet_id = pet_detail.pet_id)");
            sql.append("where species_nm = ?"); // Don't end SQL with semicolon!

            // Create a connection
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, SCHEMA, PASSWORD);
            // Create a statement to execute SQL
            pstmt = conn.prepareStatement(sql.toString());
            // Fill the prepared statement params
            pstmt.setString(1, searchTerm);
            // Execute a SELECT query and get a result set
            rset = pstmt.executeQuery();

            List<Pet> petList = new ArrayList<Pet>();

            // Loop while the result set has more rows
            while (rset.next()) {
                Pet pet = new Pet();
                pet.setName(rset.getString(1));
                pet.setAge(rset.getInt(2));

                PetDetail detail = new PetDetail();
                pet.setDetail(detail);

                detail.setFavoriteToy(rset.getString(3));
                detail.setWeight(rset.getInt(4));
                detail.setNickname(rset.getString(5));
                petList.add(pet);
            }

            request.setAttribute("pets", petList);
            request.getRequestDispatcher("petSearch.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            // If there's an exception locating the driver, send IT as the response
            response.getWriter().print(e.getMessage());
            e.printStackTrace();
        } finally {
            DatabaseUtils.closeAll(conn, pstmt, rset);
        }
    }
}

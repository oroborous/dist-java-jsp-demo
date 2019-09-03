import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "DatabaseDemo", urlPatterns = "/db")
public class DatabaseDemo extends HttpServlet {
    private final String DRIVER_NAME = "jdbc:derby:";
    private final String DATABASE_PATH = "/WEB-INF/lib/dbdemo";
    private final String USERNAME = "stacy";
    private final String PASSWORD = "stacy";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Declare outside the try/catch so the variables are in scope in the finally block
        Connection conn = null;
        Statement stmt = null;
        ResultSet rset = null;

        try {
            // Load the driver
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            // Find the absolute path of the database folder
            String absPath = getServletContext().getRealPath(DATABASE_PATH);

            StringBuilder sql = new StringBuilder("SELECT pet.nm, food.nm");
            sql.append(" FROM pet_food, pet, food");
            sql.append(" WHERE pet_food.pet_id = pet.pet_id");
            sql.append(" AND pet_food.food_id = food.food_id");
            sql.append(" ORDER BY pet.nm"); // Don't end SQL with semicolon!

            // Create a connection
            conn = DriverManager.getConnection(DRIVER_NAME + absPath, USERNAME, PASSWORD);
            // Create a statement to execute SQL
            stmt = conn.createStatement();
            // Execute a SELECT query and get a result set
            rset = stmt.executeQuery(sql.toString());

            // Create a StringBuilder for ease of appending strings
            StringBuilder output = new StringBuilder();

            // HTML to create a simple web page
            output.append("<html><body><ul>");

            // Loop while the result set has more rows
            while (rset.next()) {
                // Get the first string (the pet name) from each record
                String petName = rset.getString(1);
                String foodName = rset.getString(2);
                // Append it as a list item
                output.append("<li>").append(petName + ": " + foodName).append("</li>");
            }
            // Close all those opening tags
            output.append("</ul></body></html>");

            // Send the HTML as the response
            response.getWriter().print(output.toString());

        } catch (SQLException | ClassNotFoundException e) {
            // If there's an exception locating the driver, send IT as the response
            response.getWriter().print(e.getMessage());
            e.printStackTrace();
        } finally {
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

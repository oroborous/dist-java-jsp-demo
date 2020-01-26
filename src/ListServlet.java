import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ListServlet",
urlPatterns = "/list")
public class ListServlet extends HttpServlet {
    private final String PATH = "/WEB-INF/lib/dbdemo";
    private final String USER = "stacy";
    private final String PW = "stacy";
    private final String DRIVER = "jdbc:derby:";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        ResultSet rset = null;
        Statement stmt = null;

        try {
          Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

          String path = getServletContext().getRealPath(PATH);

          conn = DriverManager.getConnection(DRIVER + path, USER, PW);

          stmt = conn.createStatement();

          rset = stmt.executeQuery("SELECT pet_id, nm FROM pet");

          StringBuilder html = new StringBuilder("<html><body>");

          while (rset.next()) {
              int id = rset.getInt("pet_id");
              String nm = rset.getString(2);
              html.append("<p>").append(id).append(",").append(nm).append("</p>");
          }

          html.append("</body></html>");

          response.getWriter().print(html.toString());

        } catch (SQLException | ClassNotFoundException ex) {
            response.getWriter().print(ex.getMessage());
            ex.printStackTrace();
        } finally {
            try {
                if (rset != null) {
                    rset.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
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

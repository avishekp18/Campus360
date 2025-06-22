import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class dltServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json"); // Return JSON response
        response.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("email");

        // Initialize resources
        Connection con = null;
        PreparedStatement ps = null;
        PrintWriter out = null;

        try {
            out = response.getWriter();

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentregistration", "root", "");

            // Prepare the SQL DELETE statement
            ps = con.prepareStatement("DELETE FROM registration WHERE email = ?");
            ps.setString(1, email);

            // Execute the update
            int result = ps.executeUpdate();

            // Send JSON response based on deletion result
            if (result > 0) {
                // Invalidate session if deletion is successful
                HttpSession session = request.getSession(false); // Get current session (if exists)
                if (session != null) {
                    session.invalidate(); // Invalidate session
                }

                // Return success message in JSON
                out.write("{\"status\": \"success\", \"message\": \"Account deleted successfully.\"}");
            } else {
                // Return failure message in JSON
                out.write("{\"status\": \"error\", \"message\": \"Account not found.\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (out != null) {
                // Return error message in case of exception
                out.write("{\"status\": \"error\", \"message\": \"An error occurred.\"}");
            }
        } finally {
            // Close resources
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for deleting an account";
    }
}

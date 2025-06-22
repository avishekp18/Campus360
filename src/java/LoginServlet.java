import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (PrintWriter out = response.getWriter()) {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentregistration", "root", "")) {
                // Prepare the SQL statement to prevent SQL injection
                PreparedStatement ps = con.prepareStatement("SELECT * FROM registration WHERE email=? AND password=?");
                ps.setString(1, email);
                ps.setString(2, password);

                // Execute the query
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    // Get user's full name from the result set
                    String fullName = rs.getString("full name").trim(); // Use trim to remove any leading/trailing spaces

                    // Extract the first name
                    String firstName = fullName.split(" ")[0]; // Split by space and take the first part

                    // Create or get the session and set the user details
                    HttpSession session = request.getSession();
                    session.setAttribute("userEmail", email);
                    session.setAttribute("userName", firstName); // Store first name instead of full name
                    session.setAttribute("isLoggedIn", true); // Indicate user is logged in
                    session.setMaxInactiveInterval(3 * 60);
                    session.setAttribute("loginStatus", "success");
            session.setAttribute("message", "Login successful!");

                    // Redirect to dashboard
                    response.sendRedirect("index.html");
                } else {
                    // Invalid login credentials
                    out.println("<html><body>");
                    out.println("<h3>Invalid email or password</h3>");
                    out.println("<a href='log.html'>Go back to login</a>");
                    out.println("</body></html>");
                }

                // Close the ResultSet and PreparedStatement
                rs.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace(out);
                out.println("<html><body>");
                out.println("<h3>Database connection error. Please try again later.</h3>");
                out.println("</body></html>");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
        return "Login Servlet";
    }
}
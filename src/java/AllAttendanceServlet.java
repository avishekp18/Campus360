import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import java.sql.*;

public class AllAttendanceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try {
            HttpSession session = request.getSession(false); // Don't create a new session if it doesn't exist
            JSONObject jsonResponse = new JSONObject();

            if (session != null && session.getAttribute("userEmail") != null) {
                String userEmail = (String) session.getAttribute("userEmail");

                // Database connection
                try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentregistration", "root", "")) {
                    // Prepare SQL query to fetch user details based on the email
                    String query = "SELECT `full name`, `email`, `gender`, `dob`, `telephone` FROM registration WHERE email=?";
                    PreparedStatement ps = con.prepareStatement(query);
                    ps.setString(1, userEmail);

                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        // Fetch user details from the database
                        String fullName = rs.getString("full name");
                        String email = rs.getString("email");
                        String gender = rs.getString("gender");
                        String dob = rs.getString("dob");
                        String telephone = rs.getString("telephone");

                        // Prepare JSON response
                        jsonResponse.put("status", "success");
                        jsonResponse.put("fullName", fullName);
                        jsonResponse.put("email", email);
                        jsonResponse.put("gender", gender);
                        jsonResponse.put("dob", dob);
                        jsonResponse.put("telephone", telephone);
                    } else {
                        // If no data found for the given email
                        jsonResponse.put("status", "error");
                        jsonResponse.put("message", "No user data found for the provided email.");
                    }

                    rs.close();
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();

                    // Database connection error
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Database error occurred while fetching user details.");
                }
            } else {
                // If session does not contain userEmail
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "User not logged in");
            }

            // Output JSON response
            response.getWriter().print(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();

            // Create an error JSON response
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("status", "error");
            errorResponse.put("message", "An error occurred while processing the request");
            response.getWriter().print(errorResponse);
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
        return "User Info Servlet";
    }
}

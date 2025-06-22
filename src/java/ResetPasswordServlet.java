import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.json.JSONObject; // Import JSON library

public class ResetPasswordServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); // Set response type to JSON
        
        String newPassword = request.getParameter("newPassword");
        String email = request.getParameter("email");

        try (PrintWriter out = response.getWriter()) {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentregistration", "root", "");

            // Prepare SQL update statement
            PreparedStatement ps = con.prepareStatement("UPDATE registration SET password = ? WHERE email = ?");
            ps.setString(1, newPassword); // Note: In a real app, hash the password before storing it
            ps.setString(2, email);
            int result = ps.executeUpdate();

            JSONObject jsonResponse = new JSONObject(); // Create JSON response object

            // Check the result of the update operation
            if (result > 0) {
                jsonResponse.put("status", "success");
                jsonResponse.put("message", "Your password has been reset successfully!");
            } else {
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "Error resetting password! Please check if the email is correct.");
            }

            out.write(jsonResponse.toString()); // Send JSON response

            ps.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "An unexpected error occurred. Please try again later.");
            response.getWriter().write(jsonResponse.toString());
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
        return "Short description";
    }
}

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.json.JSONObject;

public class Contact extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); // Set content type to JSON
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        JSONObject jsonResponse = new JSONObject(); // Create a JSON object

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentregistration", "root", "");
                 PreparedStatement ps = con.prepareStatement("INSERT INTO contact_us (name, email, subject, message) VALUES (?, ?, ?, ?)")) {
                 
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, subject);
                ps.setString(4, message);

                int result = ps.executeUpdate();
                if (result > 0) {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Your message has been sent successfully!"); // Add success message
                } else {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Failed to send your message. Please try again.");
                }
            }
        } catch (Exception e) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "An error occurred: " + e.getMessage()); // Add error message
        } finally {
            out.write(jsonResponse.toString()); // Write the JSON response
            out.flush(); // Ensure all data is sent
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles Contact Us form submissions";
    }
}

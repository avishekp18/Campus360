import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import org.json.JSONObject;

public class ADDCourse extends HttpServlet {

    // Method to process both GET and POST requests
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); // Set content type to JSON
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("courseName");
        String instructor = request.getParameter("instructorName");
        
        JSONObject jsonResponse = new JSONObject(); // Create a JSON object for the response

        // Validate input (simple check for empty values)
        if (name == null || name.isEmpty() || instructor == null || instructor.isEmpty()) {
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "Course name and instructor name are required.");
            out.write(jsonResponse.toString());
            return;
        }

        try {
            // Load JDBC driver and connect to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentregistration", "root", "");
                 PreparedStatement ps = con.prepareStatement("INSERT INTO courses VALUES (?, ?)")) {
                
                // Set the values for the SQL statement
                ps.setString(1, name);
                ps.setString(2, instructor);
                
                // Execute the insert query
                int result = ps.executeUpdate();
                if (result > 0) {
                    jsonResponse.put("status", "success");
                    jsonResponse.put("message", "Course added successfully!"); // Success message
                } else {
                    jsonResponse.put("status", "error");
                    jsonResponse.put("message", "Failed to add course. Please try again."); // Error message
                }
            }
        } catch (Exception e) {
            // Handle any SQL or connection exceptions
            jsonResponse.put("status", "error");
            jsonResponse.put("message", "An error occurred: " + e.getMessage()); // Error message
        } finally {
            out.write(jsonResponse.toString()); // Write the JSON response
            out.flush(); // Ensure all data is sent
        }
    }

    // Overriding doPost to call processRequest for POST requests
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to handle adding new courses to the database.";
    }
}

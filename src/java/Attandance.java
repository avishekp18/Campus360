import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Define the servlet URL mapping
@WebServlet("/attendance")
public class Attandance extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject jsonResponse = new JSONObject();

        // Read the request body
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        JSONObject requestData = new JSONObject(requestBody);

        // Parse the input JSON
        String email = requestData.optString("email");
        String status = requestData.optString("status");

        if (email.isEmpty() || status.isEmpty()) {
            jsonResponse.put("message", "Email and status fields are required.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.print(jsonResponse);
            out.flush();
            return;
        }

        try {
            // Establish database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/studentregistration", "root", "")) {
                
                // Insert attendance record
                String sql = "INSERT INTO attend (email, status, date) VALUES (?, ?, NOW())";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, status);

                int rowsInserted = ps.executeUpdate();
                if (rowsInserted > 0) {
                    jsonResponse.put("message", "Attendance recorded successfully.");
                } else {
                    jsonResponse.put("message", "Failed to record attendance.");
                }
            }
        } catch (SQLException e) {
            jsonResponse.put("message", "Database error: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } catch (ClassNotFoundException e) {
            jsonResponse.put("message", "Database driver not found: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        out.print(jsonResponse);
        out.flush();
    }
}

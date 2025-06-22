import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/GetCoursesServlet")
public class GetCoursesServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8"); // Set content type to JSON
        PrintWriter out = response.getWriter();
        
        JSONArray coursesArray = new JSONArray(); // This will hold the courses in JSON format
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentregistration", "root", "");
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT courseName, instructorName FROM courses")) {
                
                while (rs.next()) {
                    // Creating a JSON object for each course
                    JSONObject course = new JSONObject();
                    course.put("course_name", rs.getString("courseName"));
                    course.put("instructor_name", rs.getString("instructorName"));
                    coursesArray.put(course); // Adding the course object to the array
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Log the error for debugging
        } finally {
            out.write(coursesArray.toString()); // Write the JSON response
            out.flush();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet to get all courses";
    }
}

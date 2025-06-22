import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;

public class UserInfoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        try {
            HttpSession session = request.getSession(false); // Don't create a new session if it doesn't exist
            JSONObject jsonResponse = new JSONObject();

            if (session != null && session.getAttribute("userName") != null) {
                String userName = (String) session.getAttribute("userName");
                String userEmail = (String) session.getAttribute("userEmail");
                jsonResponse.put("status", "success");
                jsonResponse.put("userName", userName);
                jsonResponse.put("userEmail", userEmail);
                
            } else {
                jsonResponse.put("status", "error");
                jsonResponse.put("message", "User not logged in");
//                jsonResponse.put("userName","User"); // Return empty string instead of undefined
                jsonResponse.put("userEmail", "Email"); // Return empty string instead of undefined
            }

            response.getWriter().print(jsonResponse);
        }  catch (Exception e) {
            e.printStackTrace();

            // Create an error JSON response without setting the status
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
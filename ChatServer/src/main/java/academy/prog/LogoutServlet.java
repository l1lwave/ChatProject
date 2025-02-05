package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.getWriter().println("Logged out successfully");

        String login = request.getParameter("login");

        User user = UserMap.getUser(login);
        if (user != null) {
            OnlineUsersList.removeUser(login);
            session = request.getSession();
            session.setAttribute("user", login);
            response.getWriter().write("You are logged out");
        } else {
            response.getWriter().write("You are not logged out");
        }

    }
}

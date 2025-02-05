package academy.prog;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        System.out.println("Received registration request: login=" + login + ", password=" + password);

        if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Username and password cannot be empty");
            return;
        }

        if (UserMap.getUser(login) != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            response.getWriter().println("User already exists");
            return;
        }

        UserMap.addUser(login, password);
        OnlineUsersList.addUser(login);
        System.out.println("User registered successfully: " + login);
        response.getWriter().println("Registration successful");
    }
}


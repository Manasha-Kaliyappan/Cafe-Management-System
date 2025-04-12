package com.user;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("add".equals(action)) {
                User user = new User();
                user.setFirstName(req.getParameter("first_name"));
                user.setLastName(req.getParameter("last_name"));
                user.setGender(req.getParameter("gender"));

                // Fetch all selected skills
                String[] skillsArray = req.getParameterValues("skills");
                String skills = String.join(", ", skillsArray);
                user.setSkills(skills);

                UserDAO.saveUser(user);

            } else if ("update".equals(action)) {
                User user = new User();
                user.setId(Integer.parseInt(req.getParameter("id")));
                user.setFirstName(req.getParameter("first_name"));
                user.setLastName(req.getParameter("last_name"));
                user.setGender(req.getParameter("gender"));

                String[] skillsArray = req.getParameterValues("skills");
                String skills = String.join(", ", skillsArray);
                user.setSkills(skills);

                UserDAO.updateUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                User user = UserDAO.getUserById(id);
                req.setAttribute("editUser", user);
                req.getRequestDispatcher("index.jsp").forward(req, res);
                return;
            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(req.getParameter("id"));
                UserDAO.deleteUser(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        res.sendRedirect("index.jsp");
    }
}

package com.idiot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editScreen")
public class EditScreenServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        PrintWriter printWriter = res.getWriter();
        res.setContentType("text/html");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "akshay@123");

            PreparedStatement preparedStatement = connection.prepareStatement("Select ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM bookdata WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            printWriter.println("<form action = 'editurl?id = " + id + "' method ='post'>");
            printWriter.println("<table align ='center'>");
            printWriter.println("<tr>");
            printWriter.println("<td>Book Id</td>");
            printWriter.println("<td><input type ='number' name='id' value= '" + resultSet.getInt(1) + "'</td>");
            printWriter.println("</tr>");

            printWriter.println("<tr>");
            printWriter.println("<td>Book Name</td>");
            printWriter.println("<td><input type ='text' name='bookName' value= '" + resultSet.getString(2) + "'</td>");
            printWriter.println("</tr>");

            printWriter.println("<tr>");
            printWriter.println("<td>Book Edition</td>");
            printWriter.println("<td><input type ='text' name='bookEdition' value= '" + resultSet.getString(3) + "'</td>");
            printWriter.println("</tr>");

            printWriter.println("<tr>");
            printWriter.println("<td>Book Price</td>");
            printWriter.println("<td><input type ='text' name='bookPrice' value= '" + resultSet.getFloat(4) + "'</td>");
            printWriter.println("</tr>");

            printWriter.println("<tr>");
            printWriter.println("<td><input type = 'submit' value = 'Edit'></td>");
            printWriter.println("<td><input type = 'reset' value = 'cancel'></td>");
            printWriter.println("</tr>");


            printWriter.println("</table>");
            printWriter.println("</form>");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        printWriter.println("<a href='home.html'>Home</a>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}



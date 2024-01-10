package com.idiot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {

    // String query = "Select ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM bookdata";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter printWriter;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "akshay@123");

            PreparedStatement preparedStatement = connection.prepareStatement("Select ID,BOOKNAME,BOOKEDITION,BOOKPRICE FROM bookdata");
            ResultSet resultSet = preparedStatement.executeQuery();
            printWriter = res.getWriter();
            printWriter.println("<table border='1' align='center'>");
            printWriter.println("<tr>");
            printWriter.println("<th>Book ID</th>");
            printWriter.println("<th>Book Name</th>");
            printWriter.println("<th>Book Edition</th>");
            printWriter.println("<th>Book Price</th>");
            printWriter.println("<th>Edit</th>");
            printWriter.println("<th>Delete</th>");
            printWriter.println("<tr>");

            while (resultSet.next()) {
                printWriter.println("<tr>");
                printWriter.println("<td>" + resultSet.getInt(1) + "</td>");
                printWriter.println("<td>" + resultSet.getString(2) + "</td>");
                printWriter.println("<td>" + resultSet.getString(3) + "</td>");
                printWriter.println("<td>" + resultSet.getFloat(4) + "</td>");
                printWriter.println("<td><a href='editScreen?id="+resultSet.getInt(1)+"'>Edit</a></td>");
                printWriter.println("<td><a href='deleteurl?id="+resultSet.getInt(1)+"'>Delete</a></td>");
                printWriter.println();
                printWriter.println("</tr>");
            }
            printWriter.println("<table>");

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



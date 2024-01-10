package com.idiot.servlet;

import com.mysql.cj.jdbc.Driver;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/register")
public class RegisterServlet  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        String id = req.getParameter("id");
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

        PrintWriter pw;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "akshay@123");


            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO  bookdata(ID,BOOKNAME,BOOKEDITION,BOOKPRICE)VALUES(?,?,?,?)");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, bookName);
            preparedStatement.setString(3, bookEdition);
            preparedStatement.setFloat(4, bookPrice);

            preparedStatement.executeUpdate();
            pw = res.getWriter();
            pw.println("Record Inserted Successfully");

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        pw.println("<a href ='home.html'> Home </a>");
        pw.println("<br>");
        pw.println("<a href='bookList'>Book List</a>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}

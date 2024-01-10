package com.idiot.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/editurl")
public class EditServlet extends HttpServlet {

    PrintWriter printWriter ;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        PrintWriter printWriter = res.getWriter();
        res.setContentType("text/html");

        int bookId = Integer.parseInt(req.getParameter("id"));
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        Float bookPrice = Float.valueOf(req.getParameter("bookPrice"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/book", "root", "akshay@123");

            PreparedStatement preparedStatement = connection.prepareStatement(" UPDATE bookdata set BOOKNAME=?,BOOKEDITION=?,BOOKPRICE=? WHERE id=?");

            preparedStatement.setString(1, bookName);
            preparedStatement.setString(2, bookEdition);
            preparedStatement.setFloat(3, bookPrice);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();

            printWriter.println("<h2>Record is edited Succesfully</h2>");


        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        printWriter.println("<a href='home.html'>Home</a>");
        printWriter.println("<br>");
        printWriter.println("<a href='bookList'>Book List</a>");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }
}

package com.luminahi.servlets;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class StaticServlet extends HttpServlet {
    private static final String HTML_DIR = 
        new File("").getAbsolutePath() + "/src/main/webapp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {       
        String requestURI = req.getRequestURI();
        requestURI = "/".equals(requestURI) ? "index.html": requestURI;
        String filename = requestURI.substring(requestURI.lastIndexOf("/") + 1);

        File file = new File(HTML_DIR, filename);

        if (file.exists() && file.isFile()) {
            res.setContentType("text/html");
            Files.copy(file.toPath(), res.getOutputStream());
        } else {
            res.setContentType("text/html");
            Files.copy(new File(HTML_DIR, "error.html").toPath(), res.getOutputStream());
            res.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
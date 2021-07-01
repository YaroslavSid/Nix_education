package ua.com.alevel;

import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(value = "/servletApp")
public class ServletApp extends HttpServlet {
    Map<String, String> userAgentAndIP;

    @Override
    public void init() {
        userAgentAndIP = new ConcurrentHashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter responseBody = resp.getWriter();

        resp.setContentType("text/html");


        userAgentAndIP.put(req.getHeader("User-Agent"), req.getRemoteAddr());
        responseBody.println("<h1>users:</h1>");
        for(Map.Entry<String, String> entry : userAgentAndIP.entrySet()){
            if(req.getRemoteAddr().equals(entry.getValue())){
                responseBody.println("<p align=\"center\"> <b>" + entry.getValue() + " :: " + entry.getKey() + "</b> </p>");
            } else {
                responseBody.println("<p align=\"center\"> " + entry.getValue() + " :: " + entry.getKey() + " </p>");
            }
        }
    }
}
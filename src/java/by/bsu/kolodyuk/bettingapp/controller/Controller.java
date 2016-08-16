
package by.bsu.kolodyuk.bettingapp.controller;

import by.bsu.kolodyuk.bettingapp.command.Command;
import by.bsu.kolodyuk.bettingapp.command.CommandFactory;
import by.bsu.kolodyuk.bettingapp.constant.RequestParameterType;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.createCommand(request.getParameter(RequestParameterType.COMMAND.getName()));
        SessionRequestContent requestWrapper = new SessionRequestContent(request, response);
        String page = command.execute(requestWrapper);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
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

}
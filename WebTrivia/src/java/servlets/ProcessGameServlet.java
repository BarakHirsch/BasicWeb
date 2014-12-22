/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import enums.*;
import helpers.ParseHelper;
import java.util.ArrayList;
import logic.*;
import java.util.Enumeration;

public class ProcessGameServlet extends HttpServlet {
    Manager manager = new Manager();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int cnt=0;
        
        for (Category cat : Category.values()) 
        {
                if (cat.name().equalsIgnoreCase("None")) 
                    continue;
                if (request.getParameter("checkBox"+cat)!=null)
                    cnt++;
        }
        //String[] CatsChosenByUser = new String[cnt];
        ArrayList<Category> categoriesToPlay = new ArrayList<>();
        int i=0;
            for (Category cat : Category.values())
                if (request.getParameter("checkBox"+cat)!=null)
                     categoriesToPlay.add(cat);
//CatsChosenByUser[i++]=cat.toString()
                   
            
         TriviaGame currentGame = manager.startGame(categoriesToPlay);   
        
        
        
              
        
        
        
        
        
        
        
        
        
        /*response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String docType = "<!DOCTYPE HTML>\n";
            String title = "Reading All Request Parameters";
            out.println("<!DOCTYPE HTML><html><head><title>" + title + "</title></head>");
            out.println("<BODY BGCOLOR=\"#FDF5E6\">\n");
            out.println("<H1 ALIGN=CENTER>" + title + "</H1>\n");
            out.println("<TABLE BORDER=1 ALIGN=CENTER>\n");
            out.println("<TR BGCOLOR=\"#FFAD00\">\n");
            out.println("<TH>Parameter Name<TH>Parameter Value(s)");

            Enumeration paramNames = request.getParameterNames();

            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                out.print("<TR><TD>" + paramName + "\n<TD>");
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues.length == 1) {
                    String paramValue = paramValues[0];
                    if (paramValue.length() == 0) {
                        out.println("<I>No Value</I>");
                    } else {
                        out.println(paramValue);
                    }
                } else {
                    out.println("<UL>");
                    for (int i = 0; i < paramValues.length; i++) {
                        out.println("<LI>" + paramValues[i]);
                    }
                    out.println("</UL>");
                }
            }
            out.println("</TABLE>\n</BODY></HTML>");

            out.println("</body>");
            out.println("</html>");
        }
        request.getParameter("radioButtonGeneral");
                */
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

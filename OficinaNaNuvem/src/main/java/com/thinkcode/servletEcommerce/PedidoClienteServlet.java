/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servletEcommerce;

import APIs.Apis;
import Controller.ItensVendaController;
import Controller.VendaController;
import ControllerEcommerce.BoletoController;
import ControllerEcommerce.EnderecoClienteController;
import Infrastructure.Access;
import com.thinkcode.models.ItensVenda;
import com.thinkcode.models.VendaModel;
import com.thinkcode.servlet.UsuarioServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelEcommerce.EnderecoClienteModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Leonardo Moreno
 */
@WebServlet(name = "PedidoClienteServlet", urlPatterns = {"/PedidoClienteServlet"})
public class PedidoClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Forma de enviar carácteres especiais
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String tarefa = request.getParameter("tarefa");
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        int idcliente = Access.Id_USUARIO_CLIENTE(cookies);

        VendaController vendacon = new VendaController();

        List<VendaModel> pedidos = vendacon.listaPedidosCliente(idcliente);
        String url = "/pedidoCliente.jsp";

        if (tarefa != null) {
            if (tarefa.equals("boleto")) {
                
                BoletoController bol = new BoletoController();
                int id = Integer.parseInt(request.getParameter("id"));
                String base = bol.boletoBase64(id);

                request.setAttribute("base64", base);
                url = "/boleto.jsp";
                
            }

        }
         
        request.setAttribute("pedidos", pedidos);

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            String erro = e.toString();
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

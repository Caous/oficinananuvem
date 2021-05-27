/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servletEcommerce;

import ControllerEcommerce.ClienteController;
import Infrastructure.Access;
import com.thinkcode.models.ClienteModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gustavo Nascimento
 */
@WebServlet(name = "LoginLogoutServlet", urlPatterns = {"/LoginLogoutServlet"})
public class LoginLogoutServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Forma de enviar carácteres especiais
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //Declaração de variável 
        String tarefa = "";
        String url = "";
        ClienteModel _clienteM = new ClienteModel();
        ClienteController _clienteC = new ClienteController();
        if (request.getParameter("tarefa") != null && !request.getParameter("tarefa").equals("")) {
            tarefa = request.getParameter("tarefa");
        }
        //Fun de declaração
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        boolean logado = Access.LoginCliente(cookies);
        if (logado) {
            url = "/homeCliente.jsp";
        }

        if (!logado) {

            if (request.getParameter("email") != null && request.getParameter("senha") != null) {
                //Pegando parâmetros e atribuindo a model
                _clienteM.setEmail(request.getParameter("email"));
                _clienteM.setSenha(request.getParameter("senha"));
                //_clienteM.setEmail("eduarto@teste.com.br");
                //_clienteM.setSenha("123456");
                //Fim atribuição
                boolean ok = _clienteC.Login(_clienteM);
                //Fim retorno

                if (ok) {
                    //Atribuindo usuário a model caso cadastrado
                    _clienteM = _clienteC.UsuarioPropriedades(_clienteM);
                    //Fim atribuição
                    Cookie cook = new Cookie("ID_CLIENTE", Integer.toString(_clienteM.getId_cliente()));
                    cook.setMaxAge(60 * 60 * 8);
                    Cookie cook1 = new Cookie("CPFCli", _clienteM.getCpf_cnpj());
                    cook1.setMaxAge(60 * 60 * 8);
                    Cookie cook2 = new Cookie("NomeCli", _clienteM.getNome());
                    cook2.setMaxAge(60 * 60 * 8);
                    response.addCookie(cook);
                    response.addCookie(cook1);
                    response.addCookie(cook2);
                    request.setAttribute("NomeCliente", _clienteM.getNome());
                    //url = "/index.jsp";
                    url = "/homeCliente.jsp";
                } else {
                    url = "/index.jsp";

                }
            }
        }
        if (!tarefa.equals("")) {
            if (tarefa.equals("sair")) {

                if (cookies != null) {
                    Access.LogoutCliente(cookies, response);
                    url = "/ProdutosWebServlet";
                }

            }
        }

        try {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } catch (Exception e) {
            String error = e.toString();
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

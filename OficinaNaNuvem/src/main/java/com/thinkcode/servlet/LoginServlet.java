/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servlet;

import Controller.UsuarioController;
import Infrastructure.Access;
import com.thinkcode.DAO.UsuarioDAO;
import com.thinkcode.models.UsuarioModel;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Leonardo Silva
 */
@WebServlet(name = "/LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        //Instância de objetos
        UsuarioModel usuario = new UsuarioModel();
        String tarefa = "";
        UsuarioController usuarioController = new UsuarioController();
        String url = "/login.jsp";
        if (request.getParameter("tarefa") != null && !request.getParameter("tarefa").equals("")) {
            tarefa = request.getParameter("tarefa");
        }
        //Fim instância

        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        boolean logado = Access.Login(cookies);
        if (logado) {
            url = "/home.jsp";
        }

        if (!logado) {

            if (request.getParameter("email") != null && request.getParameter("senha") != null) {
                //Pegando parâmetros e atribuindo a model
                usuario.setEmail(request.getParameter("email"));
                usuario.setSenha(request.getParameter("senha"));
                //Fim atribuição

                //Retornando usuário cadastrado
                boolean ok = usuarioController.Login(usuario);
                //Fim retorno

                if (ok) {
                    //Atribuindo usuário a model caso cadastrado
                    usuario = usuarioController.UsuarioPropriedades(usuario);
                    //Fim atribuição
                    Cookie cook = new Cookie("ID_USUARIO", Integer.toString(usuario.getIdUsuario()));
                    cook.setMaxAge(60 * 60 * 8);
                    Cookie cook1 = new Cookie("CPF", usuario.getCpfCnpj());
                    cook1.setMaxAge(60 * 60 * 8);
                    Cookie cook2 = new Cookie("Nome", usuario.getNome());
                    cook2.setMaxAge(60 * 60 * 8);
                    Cookie cook3 = new Cookie("Perfil", Integer.toString(usuario.getIdPerfil()));
                    cook3.setMaxAge(60 * 60 * 8);
                    response.addCookie(cook);
                    response.addCookie(cook1);
                    response.addCookie(cook2);
                    response.addCookie(cook3);

                    //url = "/index.jsp";
                    url = "/home.jsp";
                } else {
                    url = "/login.jsp";
                }
            }
        }
        if (!tarefa.equals("")) {
            if (tarefa.equals("sair")) {

                if (cookies != null) {
                    Access.Logout(cookies, response);
                    url = "/login.jsp";
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

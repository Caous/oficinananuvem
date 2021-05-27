/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servlet;

import Controller.MenuController;
import Controller.PedidoController;
import Infrastructure.Access;
import com.thinkcode.models.PedidoModel;
import com.thinkcode.models.TB_MENU;
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
 * @author Gustavo Nascimento
 */
@WebServlet(name = "MenuServlet", urlPatterns = {"/MenuServlet"})
public class MenuServlet extends HttpServlet {

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

        String tarefa = request.getParameter("tarefa");
        String id = request.getParameter("ID_MENU");

        Cookie cook = null;
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        String url = "/login.jsp";
        boolean logado = false;
        TB_MENU _menuM = new TB_MENU();
        MenuController _menuC = new MenuController();

        cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        logado = Access.Login(cookies);
        //Fim Varredura
        //Se usuário estiver logado pode prosseguir para página
        if (logado) {
            url = "/gerenciarMenu.jsp";
            if (tarefa != null) {

                if (tarefa.equals("")) {

                }
                if (tarefa.equals("Cancelar")) {
                    url = "/gerenciarMenu.jsp";
                }
                if (tarefa.equals("Editar")) {
                    _menuM.setIdMenu(Integer.parseInt(request.getParameter("ID_MENU")));
                    _menuM.setidUsuarioInclusao(Access.Id_USUARIO(cookies));
                    _menuM.setPagina(request.getParameter("pagina"));
                    _menuM.setDescricao(request.getParameter("descricao"));
                    _menuM.settipoMenu(Integer.parseInt(request.getParameter("tipoMenu")));
                    _menuM.seturl(request.getParameter("url"));
                    _menuM.setidUsuarioInclusao(Access.Id_USUARIO(cookies));
                    url = "/gerenciarMenu.jsp";
                    _menuC.update(_menuM);
                }
                if (tarefa.equals("Editando")) {
                    _menuM.setIdMenu(Integer.parseInt(request.getParameter("id")));
                    _menuM = _menuC.propertyMenu(_menuM);
                    request.setAttribute("ID_MENU", _menuM.getIdMenu());
                    request.setAttribute("dsDescricao", _menuM.getDescricao());
                    request.setAttribute("dsPagina", _menuM.getPagina());
                    request.setAttribute("dsUrl", _menuM.geturl());
                    request.setAttribute("tarefa", "Editar");
                    url = "/cadastrarMenu.jsp";
                }
                if (tarefa.equals("Cadastro")) {
                    _menuM.setidUsuarioInclusao(Access.Id_USUARIO(cookies));
                    _menuM.setPagina(request.getParameter("pagina"));
                    _menuM.setDescricao(request.getParameter("descricao"));
                    _menuM.settipoMenu(Integer.parseInt(request.getParameter("tipoMenu")));
                    _menuM.seturl(request.getParameter("url"));
                    url = "/gerenciarMenu.jsp";
                    _menuC.save(_menuM);
                }
                if (tarefa.equals("Cadastrando")) {
                    url = "/cadastrarMenu.jsp";
                }
                if (tarefa.equals("Excluir")) {
                    _menuM.setIdMenu(Integer.parseInt(request.getParameter("id")));
                    _menuM.setidUsuarioExclusao(Access.Id_USUARIO(cookies));
                    url = "/gerenciarMenu.jsp";
                    _menuC.delete(_menuM);
                }
            }

            TB_MENU _menuMFiltro = new TB_MENU();
            if (request.getParameter("tipoMenuFiltro") != null && !request.getParameter("tipoMenuFiltro").equals("")) {
                _menuMFiltro.settipoMenu(Integer.parseInt(request.getParameter("tipoMenuFiltro")));
            }
            if (request.getParameter("filtroDescricao") != null && !request.getParameter("filtroDescricao").equals("")) {
                _menuMFiltro.setDescricao(request.getParameter("filtroDescricao"));
            }
            
                     
            List<TB_MENU> _lsMenu = new ArrayList<TB_MENU>();            
            _lsMenu = _menuC.listAll(_menuMFiltro);
            request.setAttribute("menus", _lsMenu);
            PedidoController pedidoC = new PedidoController();
            List<PedidoModel> pedidos = pedidoC.todosPedidos("", "", "");
            request.setAttribute("pedidos", pedidos);
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

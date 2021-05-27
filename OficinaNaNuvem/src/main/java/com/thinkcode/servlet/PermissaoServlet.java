/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servlet;

import Controller.MenuController;
import Controller.PedidoController;
import Controller.PerfilController;
import Controller.PermissaoController;
import Infrastructure.Access;
import com.thinkcode.models.PedidoModel;
import com.thinkcode.models.PerfilModel;
import com.thinkcode.models.PermissaoModel;
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
@WebServlet(name = "PermissaoServlet", urlPatterns = {"/PermissaoServlet"})
public class PermissaoServlet extends HttpServlet {

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
        PermissaoModel _permissaoM = new PermissaoModel();
        PermissaoController _permissaoC = new PermissaoController();

        cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        logado = Access.Login(cookies);
        //Fim Varredura
        //Se usuário estiver logado pode prosseguir para página
        if (logado) {
            url = "/gerenciarPermissao.jsp";
            if (tarefa != null) {

                if (tarefa.equals("")) {

                }
                if (tarefa.equals("Cancelar")) {
                    url = "/gerenciarPermissao.jsp";
                }
                if (tarefa.equals("Editar")) {

                    url = "/gerenciarPermissao.jsp";
                }
                if (tarefa.equals("Editando")) {
//                    _menuM.setIdMenu(Integer.parseInt(request.getParameter("id")));
//                    _menuM = _menuC.MenuCadastrado(_menuM);
//                    request.setAttribute("ID_MENU", _menuM.getIdMenu());
//                    request.setAttribute("dsDescricao", _menuM.getDescricao());
//                    request.setAttribute("dsPagina", _menuM.getPagina());
//                    request.setAttribute("dsUrl", _menuM.geturl());
                    request.setAttribute("tarefa", "Editar");
                    url = "/cadastrarPermissao.jsp";
                }
                if (tarefa.equals("Cadastro")) {
                    _permissaoM.setIdPerfil(Integer.parseInt(request.getParameter("perfil")));
                    _permissaoM.setIdMenu(Integer.parseInt(request.getParameter("pagina")));
                    _permissaoM.setIdUsuarioInclusao(Access.Id_USUARIO(cookies));
                    _permissaoC.save(_permissaoM);
                    url = "/gerenciarPermissao.jsp";
                }
                if (tarefa.equals("Cadastrando")) {
                    url = "/cadastrarPermissao.jsp";
                }
                if (tarefa.equals("Excluir")) {
                    _permissaoM.setIdPermissao(Integer.parseInt(request.getParameter("id")));
                    _permissaoM.setIdUsuarioExclusao(Access.Id_USUARIO(cookies));
                    _permissaoC.deletePermissao(_permissaoM);
                    url = "/gerenciarPermissao.jsp";

                }
            }

            List<TB_MENU> _lsMenu = new ArrayList<TB_MENU>();
            _lsMenu = _menuC.listAll(_menuM);
            request.setAttribute("menus", _lsMenu);
            
            PerfilController perfilController = new PerfilController();
            PerfilModel _perfilFiltro = new PerfilModel();
            
            List<PerfilModel> perfis = perfilController.listAll(_perfilFiltro);
            request.setAttribute("perfis", perfis);
            
            List<PermissaoModel> _lsPermissao = new ArrayList<PermissaoModel>();
            PermissaoModel perfilFiltro = new PermissaoModel();
            if (request.getParameter("filtroPerfil") != null && !request.getParameter("filtroPerfil").equals("")) {
                perfilFiltro.setIdPerfil(Integer.parseInt(request.getParameter("filtroPerfil")));
            }
            if (request.getParameter("filtroPagina") != null && !request.getParameter("filtroPagina").equals("")) {
                perfilFiltro.setIdMenu(Integer.parseInt(request.getParameter("filtroPagina")));
            }
            _lsPermissao = _permissaoC.listAll(perfilFiltro);
            request.setAttribute("permissoes", _lsPermissao);
            
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

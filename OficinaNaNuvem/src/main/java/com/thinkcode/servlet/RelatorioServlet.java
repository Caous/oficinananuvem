/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servlet;

import Controller.FilialController;
import Controller.PedidoController;
import Controller.RelatorioController;
import Controller.UsuarioController;
import Infrastructure.Access;
import com.thinkcode.models.FilialModel;
import com.thinkcode.models.PedidoModel;
import com.thinkcode.models.ProdutoModel;
import com.thinkcode.models.RelatorioModel;
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
 * @author Gustavo Nascimento
 */
@WebServlet(name = "RelatorioServlet", urlPatterns = {"/RelatorioServlet"})
public class RelatorioServlet extends HttpServlet {

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
        

        //Instância de objetos
        String tarefa = request.getParameter("tarefa");
        RelatorioModel relatorioModel = new RelatorioModel();
        RelatorioController relatorioController = new RelatorioController();
        PedidoController pedidoC = new PedidoController();
        UsuarioModel usuario = new UsuarioModel();
        UsuarioController usuarioController = new UsuarioController();
        Cookie cook = null;
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        String url = "/login.jsp";
        boolean logado = false;
        //Fim instância       

        //Varredura de cookie para verificar se usuário está logado
        cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        logado = Access.Login(cookies);
        if (logado) {
            url = "/home.jsp";
        }

        //Fim Varredura        
        if (logado) {
            url = "/relatorio.jsp";
            FilialController FilialController = new FilialController();
            List<FilialModel> filiais = FilialController.listAll(null);
            request.setAttribute("filiais", filiais);
            List<UsuarioModel> vendedores = usuarioController.UsuariosCadastrados("", "","");
            request.setAttribute("vendedores", vendedores);
            List<UsuarioModel> clientes = usuarioController.UsuariosCadastrados("", "","");
            request.setAttribute("clientes", clientes);
            String id_filial = request.getParameter("filtroFiliais");
            if (id_filial != null && !id_filial.equals("")) {
                relatorioModel.setIdFilial(Integer.parseInt(id_filial));
            }
            String id_vendedor = request.getParameter("filtroVendedor");
            if (id_vendedor != null && !id_vendedor.equals("")) {
                relatorioModel.setidVendedor((Integer.parseInt(id_vendedor)));
            }
            String cpf_cliente = request.getParameter("filtroCliente");
            if (cpf_cliente != null && !cpf_cliente.equals("")) {
                relatorioModel.setCpfCliente(cpf_cliente);
            }
            String id_pagamento = request.getParameter("filtroPagamento");
            if (id_pagamento != null && !id_pagamento.equals("")) {
                relatorioModel.setidPagamento((Integer.parseInt(id_pagamento)));
            }

            List<RelatorioModel> _relatorio = relatorioController.ProdutosCadastrados(relatorioModel);
            request.setAttribute("relatorio", _relatorio);
            ProdutoModel produto = relatorioModel.getProduto();
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

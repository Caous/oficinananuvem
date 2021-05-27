/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servlet;

import Controller.FilialController;
import Controller.PedidoController;
import Controller.RelatorioController;
import Controller.StatusVendaController;
import Controller.UsuarioController;
import Controller.VendaController;
import Infrastructure.Access;
import com.thinkcode.models.FilialModel;
import com.thinkcode.models.PedidoModel;
import com.thinkcode.models.ProdutoModel;
import com.thinkcode.models.RelatorioModel;
import com.thinkcode.models.Status_Venda;
import com.thinkcode.models.UsuarioModel;
import com.thinkcode.models.VendaModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
@WebServlet(name = "StatusVendaServlet", urlPatterns = {"/StatusVendaServlet"})
public class StatusVendaServlet extends HttpServlet {

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

        Cookie cook = null;
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        String url = "/login.jsp";
        boolean logado = false;
        cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        logado = Access.Login(cookies);
        String tarefa = request.getParameter("tarefa");
        RelatorioModel relatorioModel = new RelatorioModel();
        RelatorioController relatorioController = new RelatorioController();
        PedidoController pedidoC = new PedidoController();
        UsuarioModel usuario = new UsuarioModel();
        UsuarioController usuarioController = new UsuarioController();
        StatusVendaController _status = new StatusVendaController();

        if (logado) {
            url = "/gerenciarStatusVenda.jsp";
        }

        if (logado) {

            if (tarefa != null && !tarefa.equals("")) {
                if (tarefa.equals("atualizarStatus")) {
                    url = "/cadastroStatus.jsp";
                    int idVenda = Integer.parseInt(request.getParameter("idVenda"));
                    FilialController FilialController = new FilialController();
                    relatorioModel.setIdVenda(idVenda);

                    List<RelatorioModel> _relatorio = relatorioController.VendasEcommerce(relatorioModel);
                    request.setAttribute("relatorio", _relatorio);
                    ProdutoModel produto = relatorioModel.getProduto();
                    List<PedidoModel> pedidos = pedidoC.todosPedidos("", "", "");
                    request.setAttribute("pedidos", pedidos);

                }
                if (tarefa.equals("atualizando")) {
                    VendaController _vendaC = new VendaController();
                    VendaModel _vendaM = new VendaModel();
                    int idVenda = Integer.parseInt(request.getParameter("idVenda"));
                    int idStatusVenda = Integer.parseInt(request.getParameter("statusSelecionado"));
                    _vendaM.setIdStatus(idStatusVenda);
                    _vendaM.setIdVenda(idVenda);
                    _vendaM.setIdUsuario(Access.Id_USUARIO(cookies));
                    Date dataIncl = new Date();
                    _vendaM.setData(dataIncl.toInstant().toString().substring(0, 10));
                    boolean atualizado = _vendaC.updateStatus(_vendaM);
                    if (atualizado) {
                        url = "/gerenciarStatusVenda.jsp";
                    }
                }
                if (tarefa.equals("gerenciarStatus")) {
                    url = "/gerenciarStatus.jsp";

                }
                if (tarefa.equals("atualizandoStatusVenda")) {
                    url = "/cadastroStatusVenda.jsp";
                    int idStatusVenda = Integer.parseInt(request.getParameter("idStatus"));
                    Status_Venda _statusM = new Status_Venda();
                    StatusVendaController _statusC = new StatusVendaController();
                    _statusM.setId_status(idStatusVenda);
                    _statusM = _statusC.propertyStatus(_statusM);
                    request.setAttribute("propStatus", _statusM);
                    request.setAttribute("tarefa", "alterandoValorStatusVenda");
                }
                if (tarefa.equals("excluindoStatusVenda")) {
                    url = "/gerenciarStatus.jsp";
                    int idStatusVenda = Integer.parseInt(request.getParameter("idStatus"));
                    Status_Venda _statusM = new Status_Venda();
                    StatusVendaController _statusC = new StatusVendaController();
                    _statusM.setId_status(idStatusVenda);
                    _statusM.setUsrExclusao(Access.Id_USUARIO(cookies));
                    boolean excluir = _statusC.delete(_statusM);
                    if (excluir) {
                        url = "/gerenciarStatus.jsp";
                    }
                }
                if (tarefa.equals("Cadastrando")) {
                    url = "/cadastroStatusVenda.jsp";
                }
                if (tarefa.equals("alterandoValorStatusVenda")) {
                    Status_Venda _statusM = new Status_Venda();
                    StatusVendaController _statusC = new StatusVendaController();
                    int idStatusVenda = Integer.parseInt(request.getParameter("idStatus"));
                    _statusM.setId_status(idStatusVenda);
                    _statusM.setDescricao(request.getParameter("descricao"));
                    _statusM.setStatus(request.getParameter("titulo"));
                    Date dataIncl = new Date();
                    _statusM.setDataInclusao(dataIncl.toInstant().toString().substring(0, 10));
                    _statusM.setUsrInclusao(Access.Id_USUARIO(cookies));
                    boolean salvo = _statusC.updateStatus(_statusM);
                    if (salvo) {
                        url = "/gerenciarStatus.jsp";
                    }
                }
                if (tarefa.equals("registrar")) {
                    Status_Venda _statusM = new Status_Venda();
                    StatusVendaController _statusC = new StatusVendaController();
                    _statusM.setDescricao(request.getParameter("descricao"));
                    _statusM.setStatus(request.getParameter("titulo"));
                    Date dataIncl = new Date();
                    _statusM.setDataInclusao(dataIncl.toInstant().toString().substring(0, 10));
                    _statusM.setUsrInclusao(Access.Id_USUARIO(cookies));
                    boolean salvo = _statusC.save(_statusM);
                    if (salvo) {
                        url = "/gerenciarStatus.jsp";
                    }
                }
            }
            FilialController FilialController = new FilialController();
            List<FilialModel> filiais = FilialController.listAll(null);
            request.setAttribute("filiais", filiais);
            List<UsuarioModel> vendedores = usuarioController.UsuariosCadastrados("", "", "");
            request.setAttribute("vendedores", vendedores);
            List<UsuarioModel> clientes = usuarioController.UsuariosCadastrados("", "", "");
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

            List<RelatorioModel> _relatorio = relatorioController.VendasEcommerce(relatorioModel);
            request.setAttribute("relatorio", _relatorio);
            ProdutoModel produto = relatorioModel.getProduto();
            List<PedidoModel> pedidos = pedidoC.todosPedidos("", "", "");
            request.setAttribute("pedidos", pedidos);
            List<Status_Venda> _listStatus = new ArrayList<Status_Venda>();

            Status_Venda filtro_status = new Status_Venda();
            if (request.getParameter("filtroTitulos") != null && !request.getParameter("filtroTitulos").equals("")) {
                filtro_status.setId_status(Integer.parseInt(request.getParameter("filtroTitulos")));
            }
            if (request.getParameter("filtroDescricao") != null && !request.getParameter("filtroDescricao").equals("")) {
                filtro_status.setDescricao(request.getParameter("filtroDescricao"));
            }
            
            List<Status_Venda> ls_filtro =  _status.listAll(null);
            request.setAttribute("filtrostatus", ls_filtro);

            _listStatus = _status.listAll(filtro_status);
            request.setAttribute("statusLista", _listStatus);
        }

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

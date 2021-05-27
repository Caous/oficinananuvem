/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servlet;

import Controller.EnderecoController;
import Controller.OrdemServicoController;
import Controller.PedidoController;
import Controller.PerfilController;
import Controller.ProdutoController;
import Controller.UsuarioController;
import Infrastructure.Access;
import com.thinkcode.models.EnderecoModel;
import com.thinkcode.models.PedidoModel;
import com.thinkcode.models.PerfilModel;
import com.thinkcode.models.ProdutoModel;
import com.thinkcode.models.RelatorioModel;
import com.thinkcode.models.TB_ORDEM_SERVICO;
import com.thinkcode.models.TB_ORDEM_SERVICO_MANUTENCAO;
import com.thinkcode.models.TB_ORDEM_SERVICO_PRODUTOS;
import com.thinkcode.models.UsuarioModel;
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
@WebServlet(name = "OrdemServicoServlet", urlPatterns = {"/OrdemServicoServlet"})
public class OrdemServicoServlet extends HttpServlet {

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

        String tarefa = "";
        tarefa = request.getParameter("tarefa");
        String id = request.getParameter("id");
        String url = "/login.jsp";
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        boolean logado = Access.Login(cookies);
        if (logado) {
            url = "/gerarOrdemServico.jsp";
            if (tarefa != null) {
                if (tarefa.equals("Relatorio")) {

                    url = "/acompanhamentoOrdemServico.jsp";
                    tarefa = null;
                }
                if (tarefa != null && tarefa.equals("NF")) {

                    url = "/nf.jsp";
                    tarefa = null;

                }

            }
        }
        if (tarefa == null) {
            OrdemServicoController ordemC = new OrdemServicoController();

            if (url.equals("/acompanhamentoOrdemServico.jsp")) {
                List<RelatorioModel> _relatorio = ordemC.OrdensCriadas();
                request.setAttribute("relatorio", _relatorio);
                PedidoController pedidoC = new PedidoController();
                List<PedidoModel> pedidos = pedidoC.todosPedidos("", "", "");
                request.setAttribute("pedidos", pedidos);
            }
            if (url.equals("/nf.jsp")) {
                TB_ORDEM_SERVICO _tbOrdemServico = new TB_ORDEM_SERVICO();
                _tbOrdemServico.setIdOrderServico(Integer.parseInt(id));
                RelatorioModel ordemServico = ordemC.consultarOrdem(_tbOrdemServico);
                request.setAttribute("ordem", ordemServico);
            }
            try {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
                dispatcher.forward(request, response);
            } catch (Exception e) {
                String erro = e.toString();
            }
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
        Cookie cook = null;
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());

        String HTML = "";
        String Tarefa = request.getParameter("tarefa");
        if (Tarefa != null && !Tarefa.equals("")) {

            if (Tarefa.equals("recuperaCliente")) {

                UsuarioModel _clienteInfos = new UsuarioModel();
                UsuarioController _usuarioC = new UsuarioController();
                _clienteInfos.setCpfCnpj(request.getParameter("buscarCPF").replace("-", ""));
                _clienteInfos = _usuarioC.UsuarioPropriedades(_clienteInfos);

                if (_clienteInfos.getIdPerfil() != 0) {
                    PerfilModel _perfil = new PerfilModel();
                    PerfilController _perfilC = new PerfilController();
                    _perfil.setTipo("Cliente");
                    _perfil = _perfilC.propertyPerfil(_perfil);
                    EnderecoModel _endereco = new EnderecoModel();
                    EnderecoController _enderecoC = new EnderecoController();
                    _endereco = _enderecoC.EnderecoUsuario(_clienteInfos.getIdUsuario());

                    if (_perfil.getIdPerfil() == _clienteInfos.getIdPerfil()) {
                        HTML = "<div class=\"row col-lg-12\">\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">Nome</label>\n"
                                + "                                                                            <input type=\"text\" id=\"nomeCad\"\n"
                                + "                                                                                   placeholder=\"Nome\" value='" + _clienteInfos.getNome() + "' class=\"form-control\"\n"
                                + "                                                                                   name=\"nomeCad\" disabled/>\n"
                                + "\n"
                                + "                                                                        </div>\n"
                                + "\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">CPF</label>\n"
                                + "                                                                            <input type=\"text\" id=\"cpfCad\"\n"
                                + "                                                                                   placeholder=\"000-000-000-00\"\n"
                                + "                                                                                   class=\"form-control input-mask-cpf\"\n"
                                + "                                                                                   name=\"cpfCad\" value='" + _clienteInfos.getCpfCnpj() + "'disabled/>\n"
                                + "                                                                        </div>\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">RG</label>\n"
                                + "                                                                            <input type=\"text\" id=\"rgCad\"\n"
                                + "                                                                                   placeholder=\"00-000-000-0\"\n"
                                + "                                                                                   class=\"form-control input-mask-rg\"\n"
                                + "                                                                                   name=\"rgCad\" value='" + _clienteInfos.getRg() + "' disabled/>\n"
                                + "                                                                        </div>\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "\n"
                                + "                                                                            <label for=\"number-button\" class=\"block\">Data\n"
                                + "                                                                                Nascimento</label>\n"
                                + "\n"
                                + "                                                                            <div class=\"input-group\">\n"
                                + "                                                                                <input class=\"form-control date-picker\"\n"
                                + "                                                                                       id=\"dataCad\" type=\"text\"\n"
                                + "                                                                                       data-date-format=\"dd-mm-yyyy\"\n"
                                + "                                                                                       name=\"dataCad\" value='" + _clienteInfos.getDataNasc() + "' disabled/>\n"
                                + "                                                                                <span class=\"input-group-addon\">\n"
                                + "                                                                                    <i\n"
                                + "                                                                                        class=\"fa fa-calendar bigger-110\"></i>\n"
                                + "                                                                                </span>\n"
                                + "                                                                            </div>\n"
                                + "\n"
                                + "\n"
                                + "                                                                        </div>\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">Telefone</label>\n"
                                + "                                                                            <div class=\"input-group\">\n"
                                + "                                                                                <span class=\"input-group-addon\">\n"
                                + "                                                                                    <i class=\"ace-icon fa fa-phone\"></i>\n"
                                + "                                                                                </span>\n"
                                + "\n"
                                + "                                                                                <input class=\"form-control input-mask-phone\"\n"
                                + "                                                                                       type=\"telCad\" id=\"telCad\"\n"
                                + "                                                                                       name=\"telCad\" value='" + _clienteInfos.getTelefone() + "' disabled/>\n"
                                + "                                                                            </div>\n"
                                + "                                                                        </div>\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">E-mail</label>\n"
                                + "                                                                            <input type=\"text\" id=\"emailCad\"\n"
                                + "                                                                                   placeholder=\"E-mail\" class=\"form-control\"\n"
                                + "                                                                                   name=\"emailCad\" value='" + _clienteInfos.getEmail() + "' disabled/>\n"
                                + "                                                                        </div>\n"
                                + "\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">Sexo</label>\n"
                                + "                                                                            <div class=\"radio\">\n"
                                + "                                                                                <label>\n"
                                + "                                                                                    <input name=\"sexoRadio\" type=\"radio\"\n"
                                + "                                                                                           class=\"form-control ace\" value='" + _clienteInfos.getSexo() + "' checked  disabled/>\n"
                                + "                                                                                    <span class='lbl'> " + _clienteInfos.getSexo() + " </span>\n"
                                + "                                                                                </label>\n"
                                + "                                                                            </div>\n"
                                + "\n"
                                + "                                                                        </div>\n"
                                + "                                                                    </div>\n"
                                + "                                                                    <div class=\"row  col-lg-12\">\n"
                                + "\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">Rua</label>\n"
                                + "                                                                            <div class=\"input-group\">\n"
                                + "                                                                                <span class=\"input-group-addon\">\n"
                                + "                                                                                    <i class=\"ace-icon fa fa-map\"></i>\n"
                                + "                                                                                </span>\n"
                                + "                                                                                <input type=\"text\" id=\"ruaCad\"\n"
                                + "                                                                                       placeholder=\"Nome da rua\"\n"
                                + "                                                                                       class=\"form-control\" name=\"ruaCad\" value='" + _endereco.getRua() + "'  disabled/>\n"
                                + "                                                                            </div>\n"
                                + "                                                                        </div>\n"
                                + "\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">CEP</label>\n"
                                + "\n"
                                + "                                                                            <input type=\"text\" id=\"cepCad\"\n"
                                + "                                                                                   placeholder=\"Nome\" placeholder=\"000.00-000\"\n"
                                + "                                                                                   class=\"form-control input-mask-cep\"\n"
                                + "                                                                                   name=\"cepCad\" value='" + _endereco.getCep() + "' disabled/>\n"
                                + "                                                                        </div>\n"
                                + "\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">Bairro</label>\n"
                                + "                                                                            <input type=\"text\" id=\"bairroCad\"\n"
                                + "                                                                                   placeholder=\"Nome do bairro\"\n"
                                + "                                                                                   class=\"form-control\" name=\"bairroCad\" value='" + _endereco.getBairro() + "' disabled/>\n"
                                + "                                                                        </div>\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "\n"
                                + "                                                                            <label for=\"number-button\" class=\"block\">Numero\n"
                                + "                                                                            </label>\n"
                                + "                                                                            <input class=\"form-control\" type=\"number\"\n"
                                + "                                                                                   placeholder=\"Número da casa/apto\"\n"
                                + "                                                                                   id=\"numeroCad\" name=\"numeroCad\" value='" + _endereco.getNumero() + "'  disabled/>\n"
                                + "                                                                        </div>\n"
                                + "                                                                        <div class=\"col-lg-3\">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">Complemento</label>\n"
                                + "\n"
                                + "                                                                            <input class=\"form-control\" type=\"text\"\n"
                                + "                                                                                   id=\"complementoCad\" name=\"complementoCad\" value='" + _endereco.getComplemento() + "' disabled/>\n"
                                + "\n"
                                + "                                                                        </div>\n"
                                + "<input type=\"text\" id=\"idCliente\"\n"
                                + "                                                                                  value='" + _clienteInfos.getIdUsuario() + "' class=\"form-control\"\n"
                                + "                                                                                   disabled style='display:none'/>\n"
                                + "                                                                    </div>";
                    }
                }
            }
            if (Tarefa.equals("BuscaProduto")) {
                ProdutoModel _produtoFiltro = new ProdutoModel();
                if (request.getParameter("nomeProduto") != null && !request.getParameter("nomeProduto").equals("")) {
                    _produtoFiltro.setNome(request.getParameter("nomeProduto"));
                }

                ProdutoController produto = new ProdutoController();
                List<ProdutoModel> produtos = produto.listAll(_produtoFiltro);

                if (produtos != null) {
                    for (ProdutoModel produtosHTML : produtos) {
                        HTML += "<div class=\"col-lg-3 thumbnail produtosVenda\" id=\"" + produtosHTML.getIdProduto() + "\">\n"
                                + ""
                                + "                                                                    <div class=\"clearfix\">                                                                    \n"
                                + "                                                                    </div>\n"
                                + "\n"
                                + "                                                                    <h3 class=\"search-title\">\n"
                                + "                                                                        <a href=\"#\" class=\"blue\">" + produtosHTML.getNome() + "</a>\n"
                                + "                                                                    </h3>\n"
                                + "                                                                    <p>" + produtosHTML.getDescricao() + "</p><br><span type=\"text\" id=\"valor_produto\" value=\"" + produtosHTML.getValor() + "\" class=\"row_currency\"> " + produtosHTML.getValor() + "</span>\n"
                                + "                                                                    <input type=\"hidden\" id=\"id_produto\" value=\"" + produtosHTML.getIdProduto() + "\"\\>\n"
                                + "                                                                    <div class=\"col-lg-12\">\n"
                                + "                                                                        <div class=\"col-lg-6 \">\n"
                                + "                                                                            &nbsp;\n"
                                + "                                                                            <br>\n"
                                + "                                                                            <button class=\"btn btn-info btn-adicionar btn-bold\" onclick=\"window.displaymessage('" + produtosHTML.getNome() + "'," + produtosHTML.getIdProduto() + "," + produtosHTML.getValor() + ");\" >\n"
                                + "                                                                                <i\n"
                                + "                                                                                    class=\"ace-icon fa fa-plus icon-on-right\"></i>\n"
                                + "                                                                                Adicionar\n"
                                + "                                                                            </button>\n"
                                + "                                                                        </div>\n"
                                + "                                                                        <div class=\"col-lg-6 \">\n"
                                + "                                                                            <label for=\"number-button\"\n"
                                + "                                                                                   class=\"block\">Quantidade</label>\n"
                                + "                                                                            <input type=\"text\" id=\"quantiaCompra" + produtosHTML.getIdProduto() + "\" placeholder =\"Qtd disponivel: " + produtosHTML.getQuantidade() + "\" class=\"spinner1\" />\n"
                                + "                                                    <input type=\"hidden\" id=\"quantia" + produtosHTML.getIdProduto() + "\" value =\"" + produtosHTML.getQuantidade() + "\" />\n"
                                + "                                                                            <div class=\"space-6\"></div>\n"
                                + "                                                                        </div>\n"
                                + "                                                                    </div>\n"
                                + "                                                                    &nbsp;\n"
                                + "                                                                </div>\n";
                    }
                }
            }
            if (Tarefa.equals("Salvar")) {
                OrdemServicoController ordemC = new OrdemServicoController();
                TB_ORDEM_SERVICO _tbOrdemServico = new TB_ORDEM_SERVICO();
                TB_ORDEM_SERVICO_MANUTENCAO _tbOrdemServicoManutencao = new TB_ORDEM_SERVICO_MANUTENCAO();
                TB_ORDEM_SERVICO_PRODUTOS _tbOrdemServicoProdutos = new TB_ORDEM_SERVICO_PRODUTOS();

                //Ordem Servico
                _tbOrdemServico.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
                _tbOrdemServico.setDsMarcaCarro(request.getParameter("marcaCarro"));
                _tbOrdemServico.setDsModeloCarro(request.getParameter("modelCarro"));
                _tbOrdemServico.setDsPlacaCarro(request.getParameter("placaCarro"));
                String valor = request.getParameter("valorOrdem").replace("R$", "").replace(".", "").replace(",", ".");

                _tbOrdemServico.setValorOrdem(Double.parseDouble(valor.substring(1, valor.length())));
                _tbOrdemServico.setPAGAMENTO(Integer.parseInt(request.getParameter("pagamento")));
                _tbOrdemServico.setIdUsuarioInclusao(Access.Id_USUARIO(cookies));
                Date dataIncl = new Date();
                _tbOrdemServico.setDtInclusao(dataIncl.toInstant().toString().substring(0, 10));

                //Ordem Servico Manutencao
                _tbOrdemServicoManutencao.setIdUsuarioInclusao(Access.Id_USUARIO(cookies));
                _tbOrdemServicoManutencao.setDtInclusao(dataIncl.toInstant().toString().substring(0, 10));
                String[] valorSerivoc = request.getParameter("valor_servico").replace("R$", "").replace(".", "").replace(",", ".").split(";");

                for (String item : valorSerivoc) {
                    String auxlios = item;
                    Double valorTentado = Double.parseDouble(auxlios);
                    _tbOrdemServicoManutencao.setValor_Servico(valorTentado);
                }

                String[] string_auxlio = request.getParameter("desc_servico").split(";");
                for (String item : string_auxlio) {
                    _tbOrdemServicoManutencao.set_Ds_Servico(item);
                }

                //Ordem Servico Produtos
                String[] string_id_produto = request.getParameter("id_produto").split(";");
                for (String item : string_id_produto) {
                    _tbOrdemServicoProdutos.setIdProduto(Integer.parseInt(item));
                }

                _tbOrdemServicoProdutos.setIdUsuarioInclusao(Access.Id_USUARIO(cookies));
                _tbOrdemServicoProdutos.setDtInclusao(dataIncl.toInstant().toString().substring(0, 10));

                String[] string_qtd_produto = request.getParameter("qtd_produto").split(";");
                for (String item : string_qtd_produto) {
                    _tbOrdemServicoProdutos.setQtdProduto(Integer.parseInt(item));
                }

                String[] string_valor_total = request.getParameter("total_produto").replace("R$", "").replace(".", "").replace(",", ".").split(";");

                for (String item : string_valor_total) {
                    _tbOrdemServicoProdutos.setValorTotal(Double.parseDouble(item.substring(1, item.length())));
                }

                int salvo = ordemC.save(_tbOrdemServico, _tbOrdemServicoManutencao, _tbOrdemServicoProdutos);
                if (salvo != 0) {
                    HTML += salvo;
                } else {
                    HTML += "Erro ao gerar ordem de serviço";
                }
            }
        }
        PrintWriter out = response.getWriter();
        out.print(HTML);
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

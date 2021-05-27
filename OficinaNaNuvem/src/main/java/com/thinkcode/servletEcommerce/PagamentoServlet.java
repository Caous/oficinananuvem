/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servletEcommerce;

import APIs.Apis;
import Controller.CategoriaController;
import Controller.ImgController;
import Controller.ItensVendaController;
import Controller.ProdutoController;
import Controller.VendaController;
import ControllerEcommerce.BoletoController;
import ControllerEcommerce.ClienteController;
import ControllerEcommerce.EnderecoClienteController;
import Infrastructure.Access;
import com.thinkcode.models.CategoriaModel;
import com.thinkcode.models.ClienteModel;
import com.thinkcode.models.ItensVenda;
import com.thinkcode.models.ProdutoImgModel;
import com.thinkcode.models.ProdutoModel;
import com.thinkcode.models.VendaModel;
import com.thinkcode.servlet.UsuarioServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import modelEcommerce.BoletoModel;
import modelEcommerce.EnderecoClienteModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Leonardo Moreno
 */
@WebServlet(name = "PagamentoServlet", urlPatterns = {"/PagamentoServlet"})
public class PagamentoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Forma de enviar carácteres especiais
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String tarefa = request.getParameter("tarefa");
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        int idcliente = Access.Id_USUARIO_CLIENTE(cookies);
        boolean logado = Access.LoginCliente(cookies);
        EnderecoClienteController enderecoC = new EnderecoClienteController();
        List<EnderecoClienteModel> Endereco = new ArrayList<EnderecoClienteModel>();
        String url = "/endereco_Pagamento.jsp";
        EnderecoClienteController endController = new EnderecoClienteController();
        ProdutoController prodController = new ProdutoController();
        Double valorFinalCompra = 0.0;
        List<ItensVenda> _itensVenda = new ArrayList<ItensVenda>();
        ItensVendaController _itensVendaC = new ItensVendaController();
        String carrinhoAtual = Access.CarrinhoIdProduto(cookies);
        ImgController imgController = new ImgController();
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
        int qtdcarrinho = 0;
        int id_endCad = 0;

        if (!logado) {
            url = "/loginCliente.jsp";
        } else {
            boolean ok;
            if (tarefa != null) {

                if (tarefa.equals("BuscaCep")) {
                    //Exemplod de consumo da api
                    EnderecoClienteModel cadastrando = new EnderecoClienteModel();
                    String cepapi = request.getParameter("cep");
                    //chamadno a api de consultar cep que retorna um json em string 
                    String jsonCep = Apis.ConsultaCep(cepapi.replace("-", ""));
                    // try cat para ver se o reotrno é realmente um json ou messagem de erro
                    try {
                        //converte essa string para um json
                        JSONObject obj = new JSONObject(jsonCep);
                        //verifica se tem a chave Data(padrão da api)
                        if (obj.has("Data")) {
                            String data = obj.getString("Data");
                            JSONObject obj2 = new JSONObject(data);
                            if (obj2.has("Cep")) {
                                cadastrando.setCep(obj2.getString("Cep"));

                            }
                            if (obj2.has("Logradouro")) {
                                cadastrando.setRua(obj2.getString("Logradouro"));

                            }
                            if (obj2.has("Bairro")) {
                                cadastrando.setBairro(obj2.getString("Bairro"));

                            }
                            if (obj2.has("Localidade")) {
                                cadastrando.setCidade(obj2.getString("Localidade"));

                            }
                            if (obj2.has("UF")) {
                                cadastrando.setEstado(obj2.getString("UF"));

                            }

                            request.setAttribute("cadastrando", cadastrando);
                        }
                    } catch (JSONException ex) {
                        Logger.getLogger(UsuarioServlet.class
                                .getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (tarefa.equals("cadEndereco")) {
                    String endCadastratado = request.getParameter("enderecoCadastrado");

                    EnderecoClienteModel endereco = new EnderecoClienteModel();
                    if (endCadastratado == null || endCadastratado.equals("")) {

                        endereco.setId_cliente(idcliente);
                        endereco.setCep(request.getParameter("cep"));
                        endereco.setNome_endereco(request.getParameter("nomeendereco"));
                        endereco.setNome_receptor(request.getParameter("nomereceptor"));
                        endereco.setNumero(Integer.parseInt(request.getParameter("numero")));
                        endereco.setEstado(request.getParameter("estado"));
                        endereco.setCidade(request.getParameter("cidade"));
                        endereco.setRua(request.getParameter("rua"));
                        endereco.setBairro(request.getParameter("bairro"));
                        endereco.setComplemento(request.getParameter("complemento"));
                        endereco.setReferencia_entrega(request.getParameter("refentrega"));
                        endereco.setTelefone(request.getParameter("telcontato").replace("(", "").replace(")", "").replace("-", ""));
                        //endereco = enderecoC.enderecoFatura(endereco);

                        boolean jatem = endController.PrimeiroRegistro(idcliente);
                        if (jatem) {
                            endereco.setFatura(false);
                        } else {
                            endereco.setFatura(true);
                        }

                        ok = endController.Save(endereco);
                        if (ok) {
                            id_endCad = endController.LastRegister(endereco);
                            url = "/finalizarPedido.jsp";
                            tarefa = "verificarPedido";

                        }
                    }
                }
                if (tarefa.equals("FinalizarCompra")) {

                    int idEndereco = 0;
                    idEndereco = Integer.parseInt(request.getParameter("idEndereco"));
                    EnderecoClienteModel _end = new EnderecoClienteModel();
                    _end.setId_endereco(idEndereco);
                    _end = enderecoC.enderecoPropriedade(_end);
                    VendaModel vendaM = new VendaModel();
                    VendaController vendaC = new VendaController();

                    ClienteModel _clienteM = new ClienteModel();
                    _clienteM.setId_cliente(idcliente);
                    ClienteController _clienteC = new ClienteController();
                    _clienteM = _clienteC.UsuarioPropriedades(_clienteM);

                    vendaM.setIdUsuario(idcliente);
                    vendaM.setIdCliente(idcliente);
                    vendaM.setCpfCnpj(_clienteM.getCpf_cnpj());

                    vendaM.setPagamento(0);

                    int parcelas = 0;
                    if (request.getParameter("parcelas") != null && !request.getParameter("parcelas").equals("")) {
                        parcelas = Integer.parseInt(request.getParameter("parcelas"));
                    }
                    vendaM.setParcelas(parcelas);
                    vendaM.setIdMetodo(Integer.parseInt(request.getParameter("metodoPagamento")));

                    vendaM.setIdEndereco(idEndereco);

                    vendaM.setIdEndFatura(_end.getId_endereco());

                    vendaM.setIdStatus(4);
                    vendaM.setIdFilial(2);

                    if (!carrinhoAtual.equals("")) {

                        String carrinhoSeparador[] = carrinhoAtual.split("&");
                        List<String> separadorIdProduto = new ArrayList<String>();
                        List<String> separadorQtdProduto = new ArrayList<String>();
                        for (int i = 0; i < carrinhoSeparador.length; i++) {
                            separadorIdProduto.add(carrinhoSeparador[i].split(",")[0]);
                            separadorQtdProduto.add(carrinhoSeparador[i].split(",")[1]);
                        }

                        List<String> PopularCarrinho = separadorIdProduto;

                        for (int i = 0; i < PopularCarrinho.size(); i++) {
                            ProdutoModel _produto = new ProdutoModel();
                            ItensVenda _itens = new ItensVenda();
                            Double valorFinalProduto = 0.0;
                            _produto.setIdProduto(Integer.parseInt(PopularCarrinho.get(i)));
                            _produto = prodController.propertyProduto(_produto);
                            valorFinalProduto = _produto.getValor() * Integer.parseInt(separadorQtdProduto.get(i));

                            valorFinalCompra += valorFinalProduto;

                            _itens.setIdProduto(_produto.getIdProduto());
                            _itens.setQntd(Integer.parseInt(separadorQtdProduto.get(i)));
                            _itens.setValor(_produto.getValor());
                            _itens.setIdUsuario(idcliente);
                            _itens.setNomeProduto(_produto.getNome());
                            _itensVenda.add(_itens);
                        }

                    }
                    vendaM.setTotal(valorFinalCompra);

                    Date dataIncl = new Date();
                    vendaM.setData(dataIncl.toInstant().toString().substring(0, 10));

                    vendaM.setFrete(1);
                    vendaM.setEccomerce(true);

                    vendaC.save(vendaM);
                    for (int i = 0; i < _itensVenda.size(); i++) {
                        prodController.UpdateQtde(_itensVenda.get(i).getIdProduto(), _itensVenda.get(i).getQntd());
                    }

                    int lastIdVenda = vendaC.consultaUltimoId();

                    for (int i = 0; i < _itensVenda.size(); i++) {
                        ItensVenda _item = _itensVenda.get(i);
                        _item.setIdVenda(lastIdVenda);
                        _itensVendaC.save(_item);
                    }
                    String identificador = "";
                    switch (Integer.parseInt(request.getParameter("metodoPagamento"))) {
                        case 1:
                            String boleto = Apis.GerarBoleto(_clienteM.getCpf_cnpj(), _clienteM.getNome(), _clienteM.getEmail(), 100);
                            String nossoNumero = "";
                            String pdf = "";

                            // try cat para ver se o reotrno é realmente um json ou messagem de erro
                            try {
                                //converte essa string para um json
                                JSONObject obj = new JSONObject(boleto);
                                //verifica se tem a chave Data(padrão da api)
                                if (obj.has("Data")) {
                                    String data = obj.getString("Data");
                                    JSONObject obj2 = new JSONObject(data);
                                    if (obj2.has("NossoNumero")) {
                                        nossoNumero = obj2.getString("NossoNumero");

                                    }
                                    if (obj2.has("LinhaDigitavel")) {
                                        boleto = obj2.getString("LinhaDigitavel");

                                    }
                                    if (obj2.has("Base64")) {
                                        pdf = obj2.getString("Base64");

                                    }
                                }
                            } catch (JSONException ex) {
                                Logger.getLogger(UsuarioServlet.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }

                            BoletoController _boletoC = new BoletoController();
                            BoletoModel _boletoM = new BoletoModel();
                            identificador = nossoNumero;
                            _boletoM.setIdapi(nossoNumero);
                            _boletoM.setIdvenda(lastIdVenda);
                            _boletoM.setPdf(pdf);
                            _boletoM.setIdcliente(idcliente);
                            _boletoC.save(_boletoM);
                            break;
                        case 2:
                            String transacao = Apis.EfetuarTransacao(request.getParameter("nomeCartaoImpresso"), request.getParameter("dtValidade"), request.getParameter("nrCartao"), request.getParameter("cvv"), 100, Integer.parseInt(request.getParameter("parcelas")));
                            // try cat para ver se o reotrno é realmente um json ou messagem de erro
                            try {
                                //converte essa string para um json
                                JSONObject obj = new JSONObject(transacao);
                                //verifica se tem a chave Data(padrão da api)

                                if (obj.has("CodigoAutorizacao")) {
                                    transacao = obj.getString("CodigoAutorizacao");

                                }
                                if (obj.has("NsuOperacao")) {
                                    identificador = obj.getString("NsuOperacao");

                                }

                            } catch (JSONException ex) {
                                Logger.getLogger(UsuarioServlet.class
                                        .getName()).log(Level.SEVERE, null, ex);
                            }
                            break;
                    }

                    vendaM.setIdentificador(identificador);
                    vendaM.setIdVenda(lastIdVenda);
                    vendaC.updateIdenficador(vendaM);

                    url = "/PedidoClienteServlet";
                    Access.limparCarrinho(cookies, response);
                    carrinhoAtual = "";
                }
                if (tarefa.equals("verificarPedido")) {
                    url = "/finalizarPedido.jsp";
                    int idEnd = 0;
                    if (id_endCad == 0) {
                        idEnd = Integer.parseInt(request.getParameter("enderecoCadastrado"));
                    } else {
                        idEnd = id_endCad;
                    }                    
                    EnderecoClienteModel _end = new EnderecoClienteModel();
                    _end.setId_endereco(idEnd);
                    _end = enderecoC.enderecoPropriedade(_end);

                    if (!carrinhoAtual.equals("")) {

                        String carrinhoSeparador[] = carrinhoAtual.split("&");
                        List<String> separadorIdProduto = new ArrayList<String>();
                        List<String> separadorQtdProduto = new ArrayList<String>();
                        for (int i = 0; i < carrinhoSeparador.length; i++) {
                            separadorIdProduto.add(carrinhoSeparador[i].split(",")[0]);
                            separadorQtdProduto.add(carrinhoSeparador[i].split(",")[1]);
                        }

                        List<String> PopularCarrinho = separadorIdProduto;

                        for (int i = 0; i < PopularCarrinho.size(); i++) {
                            ProdutoModel _produto = new ProdutoModel();
                            Double valorFinalProduto = 0.0;
                            ProdutoImgModel _imgProduto = new ProdutoImgModel();
                            _imgProduto.setIdProduto(Integer.parseInt(PopularCarrinho.get(i)));
                            _imgProduto = imgController.Select(_imgProduto);
                            _produto.setIdProduto(Integer.parseInt(PopularCarrinho.get(i)));
                            _produto = prodController.propertyProduto(_produto);
                            _produto.setImgCapa(_imgProduto.getNome());
                            _produto.setQuantidadeCarrinho(Integer.parseInt(separadorQtdProduto.get(i)));
                            valorFinalProduto = _produto.getValor() * Integer.parseInt(separadorQtdProduto.get(i));
                            _produto.setTotal(valorFinalProduto);
                            produtos.add(_produto);
                            valorFinalCompra += valorFinalProduto;
                        }

                        qtdcarrinho = carrinhoSeparador.length;
                    }
                    request.setAttribute("enderco", _end);
                }

            }
            if (!carrinhoAtual.equals("")) {
                qtdcarrinho = Access.qtdCarrinho(cookies);
            }
            CategoriaController catController = new CategoriaController();
            List<CategoriaModel> categorias;
            int idCliente = Access.Id_USUARIO_CLIENTE(cookies);
            Endereco = enderecoC.EnderecosCadastrados(idCliente);

            categorias = catController.PerfisCadastrados("", "");
            request.setAttribute("categorias", categorias);
            request.setAttribute("countCarrinho", qtdcarrinho);
            request.setAttribute("enderecos", Endereco);
            request.setAttribute("produtos", produtos);
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

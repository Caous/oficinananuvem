/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servletEcommerce;

import Controller.CategoriaController;
import Controller.ImgController;
import Controller.ProdutoController;
import Infrastructure.Access;
import com.thinkcode.models.CategoriaModel;
import com.thinkcode.models.ProdutoImgModel;
import com.thinkcode.models.ProdutoModel;
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
@WebServlet(name = "CarrinhoServlet", urlPatterns = {"/CarrinhoServlet"})
public class CarrinhoServlet extends HttpServlet {

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
        
        ProdutoController prodController = new ProdutoController();
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();
        ImgController imgController = new ImgController();
        String url = "/carrinho.jsp";
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        Double valorFinalCompra = 0.0;
        String carrinhoAtual = Access.CarrinhoIdProduto(cookies);
        int qtdcarrinho = 0;

        String tarefa = request.getParameter("tarefa");

        if (tarefa != null && !tarefa.equals("")) {
            if (tarefa.equals("adicionarCarrinho")) {
                int idProduto = Integer.parseInt(request.getParameter("idProduto"));
                int qtdProduto = Integer.parseInt(request.getParameter("qtdProduto"));

                if (!carrinhoAtual.equals("")) {
                    List<String> carrinhoSeparador = new ArrayList<String>();
                    carrinhoSeparador = Arrays.asList(carrinhoAtual.split("&"));
                    carrinhoAtual = "";
                    boolean itemNaoExiste = false;
                    for (int i = 0; i < carrinhoSeparador.size(); i++) {
                        if (carrinhoSeparador.get(i).split(",")[0].equals(Integer.toString(idProduto))) {
                            int qtdExistente = Integer.parseInt(carrinhoSeparador.get(i).split(",")[1]);
                            String valorAux = carrinhoSeparador.get(i).split(",")[0] + "," + (qtdExistente+qtdProduto) ;
                            carrinhoSeparador.set(i, valorAux);
                            itemNaoExiste = true;
                        }

                    }
                    if (!itemNaoExiste) {
                        carrinhoAtual += idProduto + "," + qtdProduto + "&";
                    }
                    for (int i = 0; i < carrinhoSeparador.size(); i++) {
                        carrinhoAtual += carrinhoSeparador.get(i) + "&";
                    }
                } else {
                    carrinhoAtual = carrinhoAtual + idProduto + "," + qtdProduto + "&";
                }

                Cookie cook = new Cookie("CarrinhoProduto", carrinhoAtual);
                cook.setMaxAge(60 * 60 * 1);
                response.addCookie(cook);

            }
            if (tarefa.equals("Pagar")) {
                String carrinhoNovoQtd = request.getParameter("qtdProdutoNova");
                String carrinhoNovo = "";
                if (!carrinhoNovoQtd.equals("")) {

                    String carrinhoSeparador[] = carrinhoAtual.split("&");
                    String carrinhoNovoQtds[] = carrinhoNovoQtd.split("&");

                    for (int i = 0; i < carrinhoSeparador.length; i++) {
                        for (int j = 0; j < carrinhoNovoQtds.length; j++) {
                            if (carrinhoSeparador[i].split(",")[0].equals(carrinhoNovoQtds[j].split(",")[0])) {
                                carrinhoNovo += carrinhoSeparador[i].split(",")[0] + "," + carrinhoNovoQtds[j].split(",")[1] + "&";
                            }
                        }
                    }
                    if (carrinhoAtual.length() == carrinhoNovo.length()) {

                        carrinhoAtual = carrinhoNovo;
                    } else {
                        String carrinhoAuxilio[] = carrinhoAtual.split("&");
                        String carrinhoFinal = "";
                        for (int i = 0; i < carrinhoAuxilio.length; i++) {
                            String idComp[] = carrinhoNovo.split(",");
                            if (carrinhoAuxilio[i].split(",")[0].equals(idComp[0])) {
                                carrinhoAuxilio[i] = carrinhoAuxilio[i].split(",")[0] + "," + idComp[1].replace("&", "");
                            }
                        }
                        for (int i = 0; i < carrinhoAuxilio.length; i++) {
                            carrinhoFinal += carrinhoAuxilio[i] + "&";
                        }
                        carrinhoAtual = carrinhoFinal;

                    }

                    Cookie cook = new Cookie("CarrinhoProduto", carrinhoAtual);
                    cook.setMaxAge(60 * 60 * 1);
                    response.addCookie(cook);
                }
                url = "/PagamentoServlet";
            }
            if (tarefa.equals("excluindo")) {
                String idProduto = request.getParameter("idProduto");

                String carrinhoSeparador[] = carrinhoAtual.split("&");
                String carrinhoNovo = "";
                for (int i = 0; i < carrinhoSeparador.length; i++) {
                    if (!carrinhoSeparador[i].split(",")[0].equals(idProduto)) {
                        carrinhoNovo = carrinhoNovo + carrinhoSeparador[i].split(",")[0] + "," + carrinhoSeparador[i].split(",")[1] + "&";
                    }
                }
                carrinhoAtual = carrinhoNovo;
                Cookie cook = new Cookie("CarrinhoProduto", carrinhoAtual);
                cook.setMaxAge(60 * 60 * 1);
                response.addCookie(cook);
            }
        }
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

        CategoriaController catController = new CategoriaController();
        List<CategoriaModel> categorias;

        categorias = catController.PerfisCadastrados("", "");

        request.setAttribute("countCarrinho", qtdcarrinho);
        request.setAttribute("categorias", categorias);
        request.setAttribute("produtos", produtos);
        request.setAttribute("compraFinal", valorFinalCompra);
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

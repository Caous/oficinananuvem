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

@WebServlet(name = "ProdutosWebServlet", urlPatterns = {"/ProdutosWebServlet"})
public class ProdutosWebServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Forma de enviar carácteres especiais
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProdutoController prodController = new ProdutoController();
        List<ProdutoModel> produtos;

        produtos = prodController.ProdutosCadastradosHome("", "", "");

        CategoriaController catController = new CategoriaController();
        List<CategoriaModel> categorias;

        categorias = catController.PerfisCadastrados("", "");

        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());

        int qtdcarrinho = Access.qtdCarrinho(cookies);
        request.setAttribute("categorias", categorias);
        request.setAttribute("produtos", produtos);
        request.setAttribute("countCarrinho", qtdcarrinho);
        RequestDispatcher dispatcher = request.getRequestDispatcher("produtos.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

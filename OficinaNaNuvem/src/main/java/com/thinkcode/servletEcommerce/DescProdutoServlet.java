package com.thinkcode.servletEcommerce;

import Controller.CategoriaController;
import Controller.EspecificacaoController;
import Controller.ImgController;
import Controller.PerguntasController;
import Controller.ProdutoController;
import Infrastructure.Access;
import com.thinkcode.models.CategoriaModel;
import com.thinkcode.models.ProdutoEspecificacaoModel;
import com.thinkcode.models.ProdutoImgModel;
import com.thinkcode.models.ProdutoModel;
import com.thinkcode.models.ProdutoPerguntaModel;
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
 * @author Leonardo Silva
 */
@WebServlet(name = "DescProdutoServlet", urlPatterns = {"/detalhes-produto"})
public class DescProdutoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Forma de enviar carácteres especiais
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        

        ProdutoModel produto = new ProdutoModel();
        ProdutoController prodController = new ProdutoController();
        produto.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));
        produto = prodController.propertyProduto(produto);

        List<ProdutoEspecificacaoModel> espProd = new ArrayList<>();
        EspecificacaoController espController = new EspecificacaoController();
        espProd = espController.SelectList(produto.getIdProduto());

        List<ProdutoPerguntaModel> pergProd = new ArrayList<>();
        PerguntasController pergController = new PerguntasController();
        pergProd = pergController.SelectList(produto.getIdProduto());
        CategoriaController catController = new CategoriaController();
        List<CategoriaModel> categorias;

        categorias = catController.PerfisCadastrados("", "");
        ImgController imgC = new ImgController();
        List<ProdutoImgModel> imgModel = new ArrayList<ProdutoImgModel>();
        imgModel = imgC.SelectList(produto.getIdProduto());

        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());

        int qtdcarrinho = Access.qtdCarrinho(cookies);
        
        request.setAttribute("categorias", categorias);
        request.setAttribute("produto", produto);
        request.setAttribute("espProduto", espProd);
        request.setAttribute("pergProduto", pergProd);
        request.setAttribute("imgProduto", imgModel);
        request.setAttribute("imgPrincipal", imgModel.get(0));
        request.setAttribute("countCarrinho", qtdcarrinho);

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/detalhes_produto.jsp");
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

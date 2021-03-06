package com.thinkcode.servlet;

import Controller.PedidoController;
import Controller.PerfilController;
import Infrastructure.Access;
import com.thinkcode.models.PedidoModel;
import com.thinkcode.models.PerfilModel;
import java.util.Date;
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
 * @author gusta
 */
@WebServlet(name = "PerfilServlet", urlPatterns = {"/PerfilServlet"})
public class PerfilServlet extends HttpServlet {

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
        String id = request.getParameter("id");

        //Instância de objetos
        PerfilModel perfil = new PerfilModel();
        PerfilController perfilController = new PerfilController();
        PedidoController pedidoC = new PedidoController();
        String url = "/login.jsp";
        boolean logado = false;
        Date dataIncl = new Date();
        Cookie cook = null;
        //Fim instância   

        //Varredura de cookie para verificar se usuário está logado
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        logado = Access.Login(cookies);
        //Fim varredura login

        //Se usuário estiver logado pode prosseguir para página
        if (logado) {
            url = "/gerenciarPerfis.jsp";
            //Se houve alguma tarefa a ser feita Seja Edita/Editar/Criando/Criar/Atualizar entra no IF
            if (tarefa != null) {
                //Tarefa editando se estiver edirando será redirecionado com os campos preenchidos
                if (tarefa.equals("Editando")) {

                    perfil.setIdPerfil(Integer.parseInt(id));
                    perfil = perfilController.propertyPerfil(perfil);
                    url = "/cadastroPerfil.jsp";
                    request.setAttribute("ID_PERFIL", perfil.getIdPerfil());
                    request.setAttribute("descricaoPerfil", perfil.getDescricao());
                    request.setAttribute("tipoPerfil", perfil.getTipo());
                    request.setAttribute("tarefa", "Editar");
                    url = "/cadastroPerfil.jsp";
                }
                //Fim tarefa editando
                //Excluíndo perfil
                if (tarefa.equals("Excluir")) {
                    perfil.setIdPerfil(Integer.parseInt(request.getParameter("id")));
                    perfil.setUsrInclusao(Access.Id_USUARIO(cookies));
                    boolean ok = perfilController.delete(perfil);
                    if (ok) {
                        url = "/gerenciarPerfis.jsp";
                    }
                }
                //Fim exclusão
                //Se estiver acabado a edição envia para atualização 
                if (tarefa.equals("Editar")) {
                    if (request.getParameter("ID_PERFIL") != null && !request.getParameter("ID_PERFIL").equals("")) {
                        perfil.setIdPerfil(Integer.parseInt(request.getParameter("ID_PERFIL")));
                    }
                    perfil.setTipo(request.getParameter("tipoPerfil"));
                    perfil.setDescricao(request.getParameter("descricaoPerfil"));

                    perfil.setDataInclusao(dataIncl.toInstant().toString().substring(0, 10));
                    perfil.setUsrInclusao(Access.Id_USUARIO(cookies));
                    boolean ok = perfilController.update(perfil);
                    //Fim 
                    if (ok) {
                        url = "/gerenciarPerfis.jsp";
                    }
                }
                //Fim atualização
                //Se estiver criando um produto
                if (tarefa.equals("cadastro")) {
                    perfil.setTipo(request.getParameter("tipoPerfil"));
                    perfil.setDescricao(request.getParameter("descricaoPerfil"));

                    perfil.setDataInclusao(dataIncl.toInstant().toString().substring(0, 10));
                    perfil.setUsrInclusao(Access.Id_USUARIO(cookies));
                    //Salvando produto
                    boolean ok = perfilController.save(perfil);
                    //Fim 
                    if (ok) {
                        url = "/gerenciarPerfis.jsp";
                    }
                }
                //Fim registro produto

            }
            //Fim se estiver logado

            String filtroIDFilial = "";
            String filtroNome = "";
            PerfilModel perfilFiltro = new PerfilModel();
            if (request.getParameter("filtroPerfilDescricao") != null) {
                if (!request.getParameter("filtroPerfilDescricao").equals("")) {
                    perfilFiltro.setDescricao(request.getParameter("filtroPerfilDescricao"));
                }
            }
            if (request.getParameter("filtroTipoPerfil") != null) {
                if (!request.getParameter("filtroTipoPerfil").equals("")) {
                    perfilFiltro.setIdPerfil(Integer.parseInt(request.getParameter("filtroTipoPerfil")));
                }
            }

            List<PerfilModel> perfisFiltro = perfilController.listAll(null);
            request.setAttribute("perfisFiltro", perfisFiltro);

            List<PerfilModel> perfis = perfilController.listAll(perfilFiltro);
            request.setAttribute("perfis", perfis);

            List<PedidoModel> pedidos = pedidoC.todosPedidos("", "", "");
            request.setAttribute("pedidos", pedidos);

        }
        //Fim usuario Logado
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

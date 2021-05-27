/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servletEcommerce;

import APIs.Apis;
import ControllerEcommerce.ClienteController;
import Infrastructure.Access;
import com.thinkcode.models.ClienteModel;
import com.thinkcode.servlet.UsuarioServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import modelEcommerce.EnderecoClienteModel;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Leonardo Moreno
 */
@WebServlet(name = "ClienteServlet", urlPatterns = {"/ClienteServlet"})
public class ClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Forma de enviar carácteres especiais
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        

        String tarefa = request.getParameter("tarefa");
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        int idcliente = Access.Id_USUARIO_CLIENTE(cookies);
        ClienteController clienteController = new ClienteController();

        String url = "/dadosCliente.jsp";

        if (tarefa != null) {
            if (tarefa.equals("Editando")) {
                ClienteModel ed = new ClienteModel();
                ed.setId_cliente(idcliente);
                ed.setNome(request.getParameter("nomeCliente"));
                ed.setRg(request.getParameter("rgCliente"));
                ed.setSexo(request.getParameter("sexoCliente"));
                ed.setTelefone(request.getParameter("telCliente"));
                ed.setData_nascimento(request.getParameter("dataCliente"));

                boolean ok = clienteController.Update(ed);
            }

            if (tarefa.equals("Cadastrando")) {

                ClienteModel cliente = new ClienteModel();

                cliente.setCpf_cnpj(request.getParameter("cpf"));
                cliente.setNome(request.getParameter("nomeCompleto"));
                cliente.setEmail(request.getParameter("email"));
                cliente.setSenha(request.getParameter("senha"));
                cliente.setTelefone(request.getParameter("telefone"));
                cliente.setSexo(request.getParameter("sexo"));
                cliente.setData_nascimento(request.getParameter("dataNasc"));
                cliente.setRg(request.getParameter("rg"));

                boolean ok = clienteController.PreCadastro(cliente.getEmail(), cliente.getCpf_cnpj());

                if (ok == true) {
                    request.setAttribute("aviso", "CPF/Email já cadastrado");
                    request.setAttribute("cliente2", cliente);
                    url = "/cadastro.jsp";

                } else {

                    ok = clienteController.Save(cliente);
                    url = "/homeCliente.jsp";
                }

            }

        }
        if (tarefa.equals("BuscaCep")) {
            //Exemplod de consumo da api
            EnderecoClienteModel cadastrando = new EnderecoClienteModel();
            String cepapi = request.getParameter("cep");
            //chamadno a api de consultar cep que retorna um json em string 
            String jsonCep = APIs.Apis.ConsultaCep(cepapi);
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

        com.thinkcode.models.ClienteModel cliente = clienteController.SelectById(idcliente);

        request.setAttribute("cliente", cliente);
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

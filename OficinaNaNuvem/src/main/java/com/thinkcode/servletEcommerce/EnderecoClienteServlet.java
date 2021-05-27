/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.servletEcommerce;

import APIs.Apis;
import Controller.StatusVendaController;
import ControllerEcommerce.EnderecoClienteController;
import Infrastructure.Access;
import com.thinkcode.models.Status_Venda;
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
@WebServlet(name = "EnderecoClienteServlet", urlPatterns = {"/EnderecoClienteServlet"})
public class EnderecoClienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Forma de enviar carácteres especiais
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String tarefa = request.getParameter("tarefa");
        List<Cookie> cookies = new ArrayList<Cookie>();
        cookies = Arrays.asList(request.getCookies());
        int idcliente = Access.Id_USUARIO_CLIENTE(cookies);
        
        List<EnderecoClienteModel> enderecos = new ArrayList<>();
        EnderecoClienteController endController = new EnderecoClienteController();
        
        
     
        
          boolean ok ;
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
            if (tarefa.equals("Cadastrando")) {
                EnderecoClienteModel endereco = new EnderecoClienteModel();
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
           
                boolean jatem = endController.PrimeiroRegistro(idcliente);
                if (jatem) {
                    endereco.setFatura(false);
                }
                else{
                     endereco.setFatura(true);
                }
                
              
                 ok = endController.Save(endereco);
            }
            if(tarefa.equals("Excluindo")) {
                int idEndereco= Integer.parseInt(request.getParameter("id"));
                boolean foi =  endController.Exluir(idEndereco);
            }
        }

        enderecos = endController.EnderecosCadastrados(idcliente);
        request.setAttribute("enderecos", enderecos);

        try {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/dadosEnderecoCliente.jsp");
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

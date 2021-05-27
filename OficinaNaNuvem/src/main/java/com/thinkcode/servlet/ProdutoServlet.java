package com.thinkcode.servlet;

import Controller.CategoriaController;
import Controller.EspecificacaoController;
import Controller.FilialController;
import Controller.ImgController;
import Controller.PedidoController;
import Controller.PerguntasController;
import Controller.ProdutoController;
import Controller.UsuarioController;
import Infrastructure.Access;
import com.thinkcode.models.CategoriaModel;
import com.thinkcode.models.FilialModel;
import com.thinkcode.models.PedidoModel;
import com.thinkcode.models.ProdutoEspecificacaoModel;
import com.thinkcode.models.ProdutoImgModel;
import com.thinkcode.models.ProdutoModel;
import com.thinkcode.models.ProdutoPerguntaModel;
import com.thinkcode.models.UsuarioModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
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
@WebServlet(name = "ProdutoServlet", urlPatterns = {"/ProdutoServlet"})
public class ProdutoServlet extends HttpServlet {

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
        ProdutoModel produto = new ProdutoModel();
        ProdutoController produtoController = new ProdutoController();
        FilialController FilialController = new FilialController();
        UsuarioModel usuario = new UsuarioModel();
        UsuarioController usuarioController = new UsuarioController();
        PedidoController pedidoC = new PedidoController();
        CategoriaController categoriaC = new CategoriaController();
        EspecificacaoController especificacaoC = new EspecificacaoController();
        ImgController imgC = new ImgController();
        List<ProdutoEspecificacaoModel> especificacaoM = new ArrayList<ProdutoEspecificacaoModel>();
        List<ProdutoPerguntaModel> perguntaM = new ArrayList<ProdutoPerguntaModel>();
        PerguntasController perguntaC = new PerguntasController();
        
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

        //Fim Varredura
        //Se usuário estiver logado pode prosseguir para página
        if (logado) {
            url = "/gerenciarProdutos.jsp";

            //Se houve alguma tarefa a ser feita Seja Edita/Editar/Criando/Criar/Atualizar entra no IF
            if (tarefa != null) {
                //Se ele estiver editando um cadastro irá entra nesta condição e preencher todos campos
                if (tarefa.equals("Editando")) {
                    
                    produto.setIdProduto(Integer.parseInt(id));
                    produto = produtoController.propertyProduto(produto);
                    url = "/cadastroUsuario.jsp";
                    request.setAttribute("ID_PRODUTO", produto.getIdProduto());
                    request.setAttribute("nomeProduto", produto.getNome());
                    request.setAttribute("tipoProduto", produto.getTipo());
                    request.setAttribute("quantidadeProduto", produto.getQuantidade());
                    request.setAttribute("descricaoProduto", produto.getDescricao());
                    request.setAttribute("valorProduto", produto.getValor());
                    request.setAttribute("idFilial", produto.getIdFilial());
                    request.setAttribute("tarefa", "Editar");
                    url = "/cadastroProduto.jsp";
                }
                // Fim edição
                //Se estiver cadastrando um usuário novo será redirecionado diretamente
                if (tarefa.equals("Cadastrando")) {
                    url = "/cadastroProduto.jsp";
                }
                //Fim redirecionamento
                //Se estiver excluindo um usuário
                if (tarefa.equals("Excluir")) {
                    produto.setIdProduto(Integer.parseInt(id));
                    produto.setIdUsuario(Access.Id_USUARIO(cookies));
                    boolean ok = produtoController.delete(produto);
                    if (ok) {
                        url = "/gerenciarProdutos.jsp";
                    }
                }
                String excluirPerguntas = request.getParameter("PerguntasDeletar");
                if (excluirPerguntas != null && !excluirPerguntas.equals("")) {
                    List<String> perguntas = Arrays.asList(excluirPerguntas.split(";"));
                    for (int i = 0; i < perguntas.size(); i++) {
                        String[] perguntasAux = perguntas.get(i).split("&");
                        ProdutoPerguntaModel especModelAux = new ProdutoPerguntaModel();
                        especModelAux.setIdPergunta(Integer.parseInt(perguntasAux[0]));
                        especModelAux.setPergunta(perguntasAux[1]);
                        especModelAux.setResposta(perguntasAux[2]);
                        especModelAux.setPontos(Integer.parseInt(perguntasAux[3]));
                        especModelAux.setId_produto(Integer.parseInt(request.getParameter("ID_PRODUTO").toString()));
                        boolean ok = perguntaC.Delete(especModelAux);
                        if (ok) {
                            url = "/gerenciarProdutos.jsp";
                        }
                    }
                }
                String excluirEspecificao = request.getParameter("EspecificacoesDeletar");
                if (excluirEspecificao != null && !excluirEspecificao.equals("")) {
                    List<String> especificacoes = Arrays.asList(excluirEspecificao.split(";"));
                    for (int i = 0; i < especificacoes.size(); i++) {
                        String[] especAux = especificacoes.get(i).split("&");
                        ProdutoEspecificacaoModel especModelAux = new ProdutoEspecificacaoModel();
                        especModelAux.setIdEspecificacao(Integer.parseInt(especAux[0]));
                        especModelAux.setEspecificacao(especAux[1]);
                        especModelAux.setResposta(especAux[2]);
                        especModelAux.setIdProduto(Integer.parseInt(request.getParameter("ID_PRODUTO").toString()));
                        boolean ok = especificacaoC.Delete(especModelAux);
                        if (ok) {
                            url = "/gerenciarProdutos.jsp";
                        }
                    }
                    
                }
                //Fim exclusão
                //Cadastro de produtos
                if (request.getParameter("nomeProduto") != null && request.getParameter("quantidadeProduto") != null) {
                    
                    usuario.setId(Access.Id_USUARIO(cookies));
                    
                    usuario = usuarioController.UsuarioPropriedades(usuario);
                    if (request.getParameter("ID_PRODUTO") != null && !request.getParameter("ID_PRODUTO").equals("")) {
                        produto.setIdProduto(Integer.parseInt(request.getParameter("ID_PRODUTO")));
                    }
                    
                    produto.setNome(request.getParameter("nomeProduto"));
                    produto.setQuantidade(Integer.parseInt(request.getParameter("quantidadeProduto")));
                    produto.setIdCategoria(Integer.parseInt(request.getParameter("categoriaProduto")));
                    produto.setTipo(request.getParameter("tipoProduto"));
                    produto.setValor(Double.parseDouble(request.getParameter("valorProduto").replace(".", "").replace(",", ".")));
                    produto.setDescricao(request.getParameter("descricaoProduto"));
                    produto.setIdUsuario(usuario.getIdUsuario());
                    produto.setIdFilial(Integer.parseInt(request.getParameter("filialProduto")));
                    
                    if (tarefa.equals("cadastro")) {
                        //Salvando produto
                        boolean ok = produtoController.save(produto);
                        
                        int idLastProduto = produtoController.lastRegister(produto);
                        
                        String tableEspecificacao = request.getParameter("Especificacoes");
                        String tablePerguntas = request.getParameter("Perguntas");
                        String img1 = request.getParameter("InputImg1");
                        String img2 = request.getParameter("InputImg2");
                        String img3 = request.getParameter("InputImg3");
                        String img4 = request.getParameter("InputImg4");
                        
                        List<String> especificacoes = Arrays.asList(tableEspecificacao.split(";"));
                        for (int i = 0; i < especificacoes.size(); i++) {
                            String[] especAux = especificacoes.get(i).split("&");
                            ProdutoEspecificacaoModel especModelAux = new ProdutoEspecificacaoModel();
                            especModelAux.setEspecificacao(especAux[0]);
                            especModelAux.setResposta(especAux[1]);
                            especModelAux.setIdProduto(idLastProduto);
                            especificacaoM.add(especModelAux);
                        }
                        
                        List<String> perguntas = Arrays.asList(tablePerguntas.split(";"));
                        for (int i = 0; i < perguntas.size(); i++) {
                            String[] perguntasAux = perguntas.get(i).split("&");
                            ProdutoPerguntaModel especModelAux = new ProdutoPerguntaModel();
                            especModelAux.setPergunta(perguntasAux[0]);
                            especModelAux.setResposta(perguntasAux[1]);
                            especModelAux.setPontos(Integer.parseInt(perguntasAux[2]));
                            especModelAux.setId_produto(idLastProduto);
                            perguntaM.add(especModelAux);
                        }

                        //Cadastro das imagens
                        ProdutoImgModel img = new ProdutoImgModel();
                        boolean home = true;
                        
                        if (!img1.isEmpty()) {
                            String desc1 = request.getParameter("DescImg1");
                            img.setNome(img1);
                            img.setIdProduto(idLastProduto);
                            img.setDescricao(desc1);
                            if (home) {
                                img.setHome(home);
                                home = false;
                            } else {
                                img.setHome(false);
                            }
                            ok = imgC.Save(img);
                            
                        }
                        if (!img2.isEmpty()) {
                            String desc2 = request.getParameter("DescImg2");
                            img.setNome(img2);
                            img.setIdProduto(idLastProduto);
                            img.setDescricao(desc2);
                            if (home) {
                                img.setHome(home);
                                home = false;
                            } else {
                                img.setHome(false);
                            }
                            ok = imgC.Save(img);
                            
                        }
                        if (!img3.isEmpty()) {
                            String desc3 = request.getParameter("DescImg3");
                            img.setNome(img3);
                            img.setIdProduto(idLastProduto);
                            img.setDescricao(desc3);
                            if (home) {
                                img.setHome(home);
                                home = false;
                            } else {
                                img.setHome(false);
                            }
                            ok = imgC.Save(img);
                            
                        }
                        if (!img4.isEmpty()) {
                            String desc4 = request.getParameter("DescImg4");
                            img.setNome(img4);
                            img.setIdProduto(idLastProduto);
                            img.setDescricao(desc4);
                            if (home) {
                                img.setHome(home);
                                home = false;
                            } else {
                                img.setHome(false);
                            }
                            ok = imgC.Save(img);
                            
                        }
                        ok = especificacaoC.Save(especificacaoM);
                        ok = perguntaC.Save(perguntaM);
                        //Fim
                        if (ok) {
                            url = "/gerenciarProdutos.jsp";
                        }
                    } else if (tarefa.equals("Editar")) { //Atualizando produto     
                        boolean ok = produtoController.update(produto);
                        
                        int idLastProduto = produtoController.lastRegister(produto);
                        
                        String tableEspecificacao = request.getParameter("Especificacoes");
                        String tablePerguntas = request.getParameter("Perguntas");
                        
                        List<String> especificacoes = Arrays.asList(tableEspecificacao.split(";"));
                        for (int i = 0; i < especificacoes.size(); i++) {
                            String[] especAux = especificacoes.get(i).split("&");
                            ProdutoEspecificacaoModel especModelAux = new ProdutoEspecificacaoModel();
                            especModelAux.setIdEspecificacao(Integer.parseInt(especAux[0]));
                            especModelAux.setEspecificacao(especAux[1]);
                            especModelAux.setResposta(especAux[2]);
                            especModelAux.setIdProduto(idLastProduto);
                            especificacaoM.add(especModelAux);
                        }
                        
                        List<String> perguntas = Arrays.asList(tablePerguntas.split(";"));
                        for (int i = 0; i < perguntas.size(); i++) {
                            String[] perguntasAux = perguntas.get(i).split("&");
                            ProdutoPerguntaModel especModelAux = new ProdutoPerguntaModel();
                            especModelAux.setIdPergunta(Integer.parseInt(perguntasAux[0]));
                            especModelAux.setPergunta(perguntasAux[1]);
                            especModelAux.setResposta(perguntasAux[2]);
                            especModelAux.setPontos(Integer.parseInt(perguntasAux[3]));
                            especModelAux.setId_produto(idLastProduto);
                            perguntaM.add(especModelAux);
                        }
                        
                        ok = especificacaoC.Update(especificacaoM);
                        
                        ok = perguntaC.Update(perguntaM);
                        
                        if (ok) {
                            url = "/gerenciarProdutos.jsp";
                        }
                        
                    }
                    
                }
                //Fim cadastro

            }
             //Fim tarefas

            //Filtro para tela de gerenciamento de usuário
            String filtroIDFilial = "";
            String filtrotipoProduto = "";
            ProdutoModel _produtoFiltro = new ProdutoModel();
            if (request.getParameter("filtroFiliais") != null || request.getParameter("categoriaProduto") != null) {
                if (!request.getParameter("filtroFiliais").equals("")) {
                    _produtoFiltro.setIdFilial(Integer.parseInt(request.getParameter("filtroFiliais")));
                }
                if (!request.getParameter("categoriaProduto").equals("")) {
                    _produtoFiltro.setIdCategoria(Integer.parseInt(request.getParameter("categoriaProduto")));
                }
            }
            List<ProdutoModel> produtos = produtoController.listAll(_produtoFiltro);
            request.setAttribute("produtos", produtos);
            
            List<FilialModel> filiais = FilialController.listAll(null);
            request.setAttribute("filiais", filiais);
            
            List<PedidoModel> pedidos = pedidoC.todosPedidos("", "", "");
            request.setAttribute("pedidos", pedidos);
            
            List<CategoriaModel> categorias = categoriaC.PerfisCadastrados("", "");
            request.setAttribute("categorias", categorias);
            if (id != null) {
                List<ProdutoEspecificacaoModel> _especificacoes = especificacaoC.SelectList(Integer.parseInt(id));
                request.setAttribute("especificacoes", _especificacoes);
                
                List<ProdutoPerguntaModel> _perguntas = perguntaC.SelectList(Integer.parseInt(id));
                request.setAttribute("perguntas", _perguntas);
            }
            //Fim filtros
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

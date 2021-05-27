/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.thinkcode.DAO.ProdutoDAO;
import com.thinkcode.models.ProdutoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public class ProdutoController {

    public boolean save(ProdutoModel produto) {
        return ProdutoDAO.saveProduto(produto);
    }

    public boolean update(ProdutoModel produto) {
        return ProdutoDAO.updateProduto(produto);
    }

    public ProdutoModel propertyProduto(ProdutoModel produto) {
        return ProdutoDAO.propertyProduto(produto);
    }

    public List<ProdutoModel> listAll(ProdutoModel produto) {
        return ProdutoDAO.listAllProdutos(produto);
    }
    public List<ProdutoModel> ProdutosCadastradosHome(String filtroFilial, String filtroTipo, String filtroNome) {
        return ProdutoDAO.produtosCadastradosHome(filtroFilial, filtroTipo, filtroNome);
    }

    public boolean delete(ProdutoModel produto) {
        return ProdutoDAO.deleteProduto(produto);
    }

    public boolean UpdateQtde(int id, int qtde) {
        return ProdutoDAO.atualizarQtdeProduto(id, qtde);
    }
    
    public int lastRegister(ProdutoModel produto) {
        return ProdutoDAO.lastRegister(produto);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.thinkcode.DAO.ProdutoEspecificacaoDAO;
import com.thinkcode.models.ProdutoEspecificacaoModel;
import java.util.List;

/**
 *
 * @author Leonardo Moreno
 */
public class EspecificacaoController {
         public boolean Save(List<ProdutoEspecificacaoModel> esp) {
        return ProdutoEspecificacaoDAO.cadastrarEspecificacao(esp);
    }

    public boolean Update(List<ProdutoEspecificacaoModel> esp) {
        return ProdutoEspecificacaoDAO.atualizarEspecificacao(esp);
    }

    public ProdutoEspecificacaoModel Select(ProdutoEspecificacaoModel esp) {
        return ProdutoEspecificacaoDAO.consultarEspecificacao(esp);
    }

    public List<ProdutoEspecificacaoModel> SelectList(int id_produto) {
        return ProdutoEspecificacaoDAO.EspecificacoesCadastradas(id_produto);
    }
    
    public boolean Delete(ProdutoEspecificacaoModel id) {
        return ProdutoEspecificacaoDAO.Delete(id);
    }
    
}

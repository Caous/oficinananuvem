/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.thinkcode.DAO.ProdutoPerguntaDAO;
import com.thinkcode.models.ProdutoPerguntaModel;
import java.util.List;

/**
 *
 * @author Leonardo Moreno
 */
public class PerguntasController {
     public boolean Save(List<ProdutoPerguntaModel> pergunta) {
        return ProdutoPerguntaDAO.cadastrarPergunta(pergunta);
    }

    public boolean Update(List<ProdutoPerguntaModel> pergunta) {
        return ProdutoPerguntaDAO.atualizarPergunta(pergunta);
    }

    public ProdutoPerguntaModel Select(ProdutoPerguntaModel pergunta) {
        return ProdutoPerguntaDAO.consultarPergunta(pergunta);
    }

    public List<ProdutoPerguntaModel> SelectList(int id_produto) {
        return ProdutoPerguntaDAO.PerguntasCadastradas(id_produto);
    }
    
    public boolean Delete(ProdutoPerguntaModel id) {
        return ProdutoPerguntaDAO.Delete(id);
    }
}

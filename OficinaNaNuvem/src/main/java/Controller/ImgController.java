/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.thinkcode.DAO.ProdutoImgDAO;
import com.thinkcode.models.ProdutoImgModel;
import java.util.List;

/**
 *
 * @author Leonardo Moreno
 */
public class ImgController {
     public boolean Save(ProdutoImgModel img) {
        return ProdutoImgDAO.cadastrarImg(img);
    }

    public boolean Update(ProdutoImgModel img) {
        return ProdutoImgDAO.atualizarImg(img);
    }

    public ProdutoImgModel Select(ProdutoImgModel img) {
        return ProdutoImgDAO.consultarImg(img);
    }

    public List<ProdutoImgModel> SelectList(int id_produto) {
        return ProdutoImgDAO.ImgsCadastradas(id_produto);
    }
    
    public boolean Delete(int id) {
        return ProdutoImgDAO.Delete(id);
    }
}

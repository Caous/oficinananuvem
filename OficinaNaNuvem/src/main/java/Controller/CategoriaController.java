/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.thinkcode.DAO.CategoriaDAO;
import com.thinkcode.models.CategoriaModel;
import java.util.List;

/**
 *
 * @author Leonardo Morebno
 */
public class CategoriaController {

    public boolean Save(CategoriaModel categoria) {
        return CategoriaDAO.cadastrarCategoria(categoria);
    }

    public boolean Update(CategoriaModel categoria) {
        return CategoriaDAO.atualizarCategoria(categoria);
    }

    public CategoriaModel   CategoriaPropriedades(CategoriaModel categoria) {
        return CategoriaDAO.consultarCategoria(categoria);
    }

    public List<CategoriaModel> PerfisCadastrados(String filtroDescricao, String filtroTipo) {
        return CategoriaDAO.CategoriasCadastradas(filtroDescricao, filtroTipo);
    }
    
    public boolean Exluir(int id , int user) {
        return CategoriaDAO.ExcluiCategoria(id,user);
    }
    public boolean Delete(int id) {
        return CategoriaDAO.Delete(id);
    }
}

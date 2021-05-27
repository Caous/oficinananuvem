/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import com.thinkcode.DAO.ItensVendaDAO;
import com.thinkcode.models.ItensVenda;
import java.util.List;

/**
 *
 * @author Leonardo Moreno
 */
public class ItensVendaController {
    public boolean save(ItensVenda itens){
        return ItensVendaDAO.cadastrarProduto(itens);
    }
    public List<ItensVenda> consultaItensdaVenda(int id){
        return ItensVendaDAO.consultarTodosItensPedido(id);
    }
}

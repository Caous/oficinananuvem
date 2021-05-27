/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.thinkcode.DAO.StatusVendaDAO;
import com.thinkcode.models.Status_Venda;
import java.util.List;

/**
 *
 * @author Leonardo Moreno
 */
public class StatusVendaController {

    public boolean save(Status_Venda status) {
        return StatusVendaDAO.saveStatusVenda(status);
    }

    public List<Status_Venda> listAll(Status_Venda status) {
        return StatusVendaDAO.listAllStatus(status);
    }

    public boolean delete(Status_Venda status) {
        return StatusVendaDAO.deleteStatus(status);
    }

    public boolean deleteDB(int idStatus) {
        return StatusVendaDAO.deleteDBStatus(idStatus);
    }

    public Status_Venda propertyStatus(Status_Venda _Status) {
        return StatusVendaDAO.propertyStatus(_Status);
    }

    public boolean updateStatus(Status_Venda _Status) {
        return StatusVendaDAO.updateStatus(_Status);
    }

}

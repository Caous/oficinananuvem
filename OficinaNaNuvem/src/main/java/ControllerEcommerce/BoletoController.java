/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerEcommerce;

import DAOEcommerce.BoletoDAO;
import modelEcommerce.BoletoModel;

/**
 *
 * @author Leonardo Moreno
 */
public class BoletoController {

    public String boletoBase64(int idVenda) {
        return BoletoDAO.BoletoBase64(idVenda);
    }

    public boolean save(BoletoModel _boleto) {
        return BoletoDAO.save(_boleto);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllerEcommerce;

import DAOEcommerce.EnderecoClienteDAO;
import java.util.List;
import modelEcommerce.EnderecoClienteModel;

/**
 *
 * @author Leonardo Moreno
 */
public class EnderecoClienteController {

    public boolean Save(EnderecoClienteModel endereco) {
        return EnderecoClienteDAO.cadastrarEnderecoCliente(endereco);
    }

    public List<EnderecoClienteModel> EnderecosCadastrados(int id) {
        return EnderecoClienteDAO.EnderecosCadastradosById(id);
    }

    public boolean PrimeiroRegistro(int id) {
        return EnderecoClienteDAO.EnderecosCadastradosByIdForFatura(id);
    }

    public boolean Exluir(int id) {
        return EnderecoClienteDAO.ExcluiCategoria(id);
    }

    public boolean Delete(int id) {
        return EnderecoClienteDAO.Delete(id);
    }

    public int LastRegister(EnderecoClienteModel endereco) {
        return EnderecoClienteDAO.ultimoRegistro(endereco);
    }

    public EnderecoClienteModel enderecoFatura(EnderecoClienteModel endereco) {
        return EnderecoClienteDAO.EnderecoFaturaCadastrado(endereco);
    }

    public EnderecoClienteModel enderecoPropriedade(EnderecoClienteModel endereco) {
        return EnderecoClienteDAO.EnderecoFaturaPropriedade(endereco);
    }

}

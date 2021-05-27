package ControllerEcommerce;

import DAOEcommerce.ClienteDAO;
import com.thinkcode.models.ClienteModel;
import java.util.List;

/**
 *
 * @author Gustavo Nascimento
 */
public class ClienteController {

    public boolean Save(ClienteModel Usuario) {
        return ClienteDAO.cadastrarCliente(Usuario);

    }

    public boolean Update(ClienteModel Usuario) {
        return ClienteDAO.atualizarCliente(Usuario);

    }

    public boolean Login(ClienteModel Usuario) {
        return ClienteDAO.consultarCliente(Usuario);
    }

    public boolean PreCadastro(String email ,String cpf) {
        return ClienteDAO.consultarClienteCpfEmail(email,cpf);
    }

    public ClienteModel UsuarioPropriedades(ClienteModel user) {
        ClienteDAO.consultarPropriedadesCliente(user);
        return user;
    }

    public ClienteModel SelectById(int id) {
        return ClienteDAO.consultarClienteById(id);
    }

    public ClienteModel SelectByCpf(String cpf) {
        return ClienteDAO.consultarClienteByCPF(cpf);
    }

    public List<ClienteModel> UsuariosCadastrados(ClienteModel user) {
        return ClienteDAO.clientesCadastrados(user);
    }

    public boolean Delete(ClienteModel user) {
        return ClienteDAO.deletarCliente(user);
    }

}

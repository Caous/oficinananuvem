package Controller;

import com.thinkcode.DAO.UsuarioDAO;
import com.thinkcode.models.UsuarioModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public class UsuarioController {

    public boolean Save(UsuarioModel Usuario) {
        return UsuarioDAO.cadastrarUsuario(Usuario);

    }

    public boolean Update(UsuarioModel Usuario) {
        return UsuarioDAO.atualizarUsuario(Usuario);

    }

    public boolean Login(UsuarioModel Usuario) {
        return UsuarioDAO.consultarUsuarioCadastrado(Usuario);
    }

    public UsuarioModel UsuarioPropriedades(UsuarioModel user) {
        UsuarioDAO.consultarUsuario(user);
        return user;
    }

    public List<UsuarioModel> UsuariosCadastrados(String filtroFilial, String filtroPerfil, String nomePerfil) {
        return UsuarioDAO.UsuariosCadastrados(filtroFilial, filtroPerfil, nomePerfil);
    }

    public boolean Delete(int idUsuarioExclusao, int idUsuarioExcluindo) {
        return UsuarioDAO.excluirUsuario(idUsuarioExclusao, idUsuarioExcluindo);
    }

    public static UsuarioModel selectFromCPFPERFIL(String cpf, String filtroPerfil) {
        return UsuarioDAO.UsuariosPorCPFPERFIL(cpf, filtroPerfil);
    }
}

package Controller;

import com.thinkcode.DAO.PerfilDAO;
import com.thinkcode.models.PerfilModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public class PerfilController {

    public boolean save(PerfilModel perfil) {
        return PerfilDAO.savePerfil(perfil);
    }

    public boolean update(PerfilModel perfil) {
        return PerfilDAO.updatePerfil(perfil);
    }

    public PerfilModel propertyPerfil(PerfilModel perfil) {
        return PerfilDAO.propertyPerfil(perfil);
    }

    public List<PerfilModel> listAll(PerfilModel perfil) {
        return PerfilDAO.listAllPerfil(perfil);
    }

    public boolean delete(PerfilModel perfil) {
        return PerfilDAO.deletePerfil(perfil);
    }

}

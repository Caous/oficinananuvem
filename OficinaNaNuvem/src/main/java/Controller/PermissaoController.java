package Controller;

import com.thinkcode.DAO.PermissaoDAO;
import com.thinkcode.models.PermissaoModel;
import java.util.List;

/**
 *
 * @author Leonardo Moreno
 */
public class PermissaoController {
      public boolean save(PermissaoModel permissao) {
        return PermissaoDAO.save(permissao);
    }

    public List<PermissaoModel> listAll(PermissaoModel permissao) {
        return PermissaoDAO.listAll(permissao);
    }

    public List<PermissaoModel> propertyPermissao(PermissaoModel permissao) {
        return PermissaoDAO.propertyPermissao(permissao);
    }
    public boolean deletePermissao(PermissaoModel permissao) {
        return PermissaoDAO.deletePermissao(permissao);
    }
    
    public boolean deleteBD(int id) {
        return PermissaoDAO.deleteBD(id);
    }
    
}

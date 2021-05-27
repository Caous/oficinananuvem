package Controller;

import com.thinkcode.DAO.FilialDAO;
import com.thinkcode.DAO.ProdutoDAO;
import com.thinkcode.models.FilialModel;
import com.thinkcode.models.ProdutoModel;
import java.util.List;

/**
 *
 * @author gusta
 */
public class FilialController {

    public boolean save(FilialModel filial) {
        return FilialDAO.saveFilial(filial);
    }

    public boolean update(FilialModel Filial) {
        return FilialDAO.updateFilial(Filial);

    }

    public boolean delete(FilialModel filial) {
        return FilialDAO.deleteFilial(filial);
    }

    public List<FilialModel> listAll (FilialModel filial) {
        return FilialDAO.listAllFiliais(filial);
    }

    public FilialModel propertyFilial (FilialModel filial) {
        FilialDAO.propertyFilial(filial);
        return filial;
    }
}

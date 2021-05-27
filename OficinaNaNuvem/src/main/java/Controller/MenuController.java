package Controller;

import com.thinkcode.DAO.MenuDAO;
import com.thinkcode.models.TB_MENU;
import java.util.List;


public class MenuController {
    public boolean save(TB_MENU menu) {
        return MenuDAO.saveMenu(menu);
    }

    public boolean update(TB_MENU menu) {
        return MenuDAO.updateMenu(menu);
    }
    
    public List<TB_MENU> listAll(TB_MENU menu) {
        return MenuDAO.listAllMenus(menu);
    }
    
    public boolean delete(TB_MENU menu) {
        return MenuDAO.deleteMenu(menu);
    }
    
    public TB_MENU propertyMenu(TB_MENU menu){
        return MenuDAO.propertyMenu(menu);
    }
}

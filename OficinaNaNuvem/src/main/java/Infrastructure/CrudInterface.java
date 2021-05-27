/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Infrastructure;

import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author Gustavo Nascimento
 */
public interface CrudInterface {
    
    public boolean insert(Object x);
    public boolean update(Object x);
    public boolean delete(Object x);
    public List<Object> listAll();    
}

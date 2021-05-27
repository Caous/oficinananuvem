/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOEcommerce;

import Infrastructure.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelEcommerce.BoletoModel;

/**
 *
 * @author Leonardo Moreno
 */
public class BoletoDAO extends DBConnection {

    public static String BoletoBase64(int idVenda) {
        Connection con;
        String base = "";
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("SELECT pdf FROM tb_boletos WHERE id_venda =" + idVenda);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                base = rs.getString("pdf");
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(BoletoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return base;
    }

    public static boolean save(BoletoModel _boleto) {
        boolean save = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "insert into tb_boletos (id_venda, id_cliente, id_api, pago,pdf)"
                    + " values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, _boleto.getIdvenda());
            ps.setInt(2, _boleto.getIdcliente());
            ps.setString(3, _boleto.getIdapi());
            ps.setBoolean(4, false);
            ps.setString(5, _boleto.getPdf());
            ps.execute();
            save = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            String guts = ex.toString();
            System.out.println(ex);
        }
        return save;

    }

}

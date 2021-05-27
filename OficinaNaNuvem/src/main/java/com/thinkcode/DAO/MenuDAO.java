/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.ProdutoImgModel;
import com.thinkcode.models.TB_MENU;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author leona
 */
public class MenuDAO {

    public static boolean saveMenu(TB_MENU menu) {
        boolean ok = false;
        Connection con;

        long millis = System.currentTimeMillis();

        java.sql.Date date = new java.sql.Date(millis);

        try {
            con = DBConnection.obterConexao();

            String sql = menu.insertInto();

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, menu.getPagina());
            ps.setString(2, menu.getDescricao());
            ps.setDate(3, date);
            ps.setInt(4, menu.getidUsuarioInclusao());
            ps.setInt(5, menu.gettipoMenu());
            ps.setString(6, menu.geturl());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static TB_MENU propertyMenu(TB_MENU menu) {
        Connection con;
        TB_MENU _menu = new TB_MENU();
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_menu where id_menu = " + menu.getIdMenu();

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                _menu.setIdMenu(rs.getInt("id_menu"));
                _menu.setPagina(rs.getString("pagina"));
                _menu.setDescricao(rs.getString("descricao"));
                _menu.seturl(rs.getString("url"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _menu;
    }

    public static boolean deleteMenu(TB_MENU menu) {
        Connection con;
        Date date = new Date();
        String data = date.toInstant().toString().substring(0, 10);

        try {
            con = DBConnection.obterConexao();
            String sql = menu.delete();

            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, data);
            ps.setInt(2, menu.getidUsuarioExclusao());
            ps.setInt(3, menu.getIdMenu());

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean DeleteBD(int id) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete  from tb_menu where id_menu =" + id);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<TB_MENU> listAllMenus(TB_MENU menu) {
        Connection con;
        List<TB_MENU> menus = new ArrayList<>();

        try {
            String sqlState = menu.listAllInto();

            if (menu != null) {

                if (menu.getIdMenu() != 0) {
                    sqlState = " and id_menu = " + menu.getIdMenu();
                }
                if (menu.gettipoMenu() != 0) {
                    sqlState = sqlState + " and TIPOMENU = " + menu.gettipoMenu();
                }
                if (menu.getDescricao() != null && !menu.getDescricao().equals("")) {
                   sqlState += " and descricao like  '%" + menu.getDescricao() + "%'"; 
                }
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TB_MENU _menu = new TB_MENU();
                _menu.setIdMenu(rs.getInt("MU.id_menu"));
                _menu.setPagina(rs.getString("MU.pagina"));
                _menu.setDescricao(rs.getString("MU.descricao"));
                _menu.setdescUsuario(rs.getString("US.NOME"));
                _menu.seturl(rs.getString("MU.URL"));
                switch (rs.getInt("MU.TIPOMENU")) {
                    case 1:
                        _menu.setdescTipoMenu("Relatório");
                        break;
                    case 2:
                        _menu.setdescTipoMenu("Cadastro");
                        break;
                    case 3:
                        _menu.setdescTipoMenu("Compras/Vendas");
                        break;
                    case 4:
                        _menu.setdescTipoMenu("Dashboard");
                        break;
                    case 5:
                        _menu.setdescTipoMenu("Outros");
                        break;
                }
                menus.add(_menu);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menus;
    }

    public static boolean updateMenu(TB_MENU menu) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = menu.update();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, menu.getPagina());
            ps.setString(2, menu.getDescricao());
            ps.setString(3, menu.geturl());
            ps.setInt(4, menu.gettipoMenu());
            ps.setInt(5, menu.getIdMenu());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MenuDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

}

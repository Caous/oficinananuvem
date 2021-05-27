/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.PermissaoModel;
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
 * @author Leonardo Moreno
 */
public class PermissaoDAO {

    public static boolean save(PermissaoModel permissao) {
        boolean ok = false;
        Connection con;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        try {
            con = DBConnection.obterConexao();
            String sql = permissao.insertInto();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, permissao.getIdPerfil());
            ps.setInt(2, permissao.getIdMenu());
            ps.setDate(3, date);
            ps.setInt(4, permissao.getIdUsuarioInclusao());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static List<PermissaoModel> listAll(PermissaoModel permissaoM) {
        Connection con;
        List<PermissaoModel> permissoes = new ArrayList<>();

        try {
            String sqlState = permissaoM.listAllInto();
            
            if (permissaoM != null) {
                if (permissaoM.getIdPermissao() != 0) {
                    sqlState = sqlState + " and PE.id_permissao = " + permissaoM.getIdPermissao();
                }
                if (permissaoM.getIdMenu()!= 0) {
                    sqlState = sqlState + " and MU.id_menu = " + permissaoM.getIdMenu();
                }
                if (permissaoM.getIdPerfil()!= 0) {
                    sqlState = sqlState + " and PF.id_perfil = " + permissaoM.getIdPerfil();
                }
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PermissaoModel permissao = new PermissaoModel();
                permissao.setIdPermissao(rs.getInt("id_permissao"));
                permissao.setIdPerfil(rs.getInt("id_perfil"));
                permissao.setIdMenu(rs.getInt("id_menu"));
                permissao.setdescUsuario(rs.getString("US.NOME"));
                permissao.setperfil(rs.getString("PF.TIPO"));
                permissao.setpagina(rs.getString("MU.PAGINA"));
                permissoes.add(permissao);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return permissoes;
    }

    public static List<PermissaoModel> propertyPermissao(PermissaoModel permissaoM) {
        Connection con;
        List<PermissaoModel> permissoes = new ArrayList<>();

        try {
            String sqlState = permissaoM.property() + permissaoM.getIdMenu();

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PermissaoModel permissao = new PermissaoModel();
                permissao.setIdPermissao(rs.getInt("id_permissao"));
                permissao.setIdPerfil(rs.getInt("id_perfil"));
                permissao.setIdMenu(rs.getInt("id_menu"));
                permissoes.add(permissao);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return permissoes;
    }

    public static boolean deletePermissao(PermissaoModel permisaoM) {
        Connection con;
        Date date = new Date();
        String data = date.toInstant().toString().substring(0, 10);

        try {
            con = DBConnection.obterConexao();
            String sql = permisaoM.delete();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, data);
            ps.setInt(2, permisaoM.getIdUsuarioExclusao());
            ps.setInt(3, permisaoM.getIdPerfil());

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteBD(int id) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete  from tb_permissao where id_permissao =" + id);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PermissaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.PerfilModel;
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
 * @author Devakian
 */
public class PerfilDAO extends DBConnection {

    public static boolean savePerfil(PerfilModel perfil) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = perfil.insertInto();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, perfil.getIdPerfil());
            ps.setString(2, perfil.getTipo());
            ps.setString(3, perfil.getDescricao());
            ps.setString(4, perfil.getDataInclusao());
            ps.setInt(5, perfil.getUsrInclusao());
            ps.setString(6, perfil.getDataExclusao());
            ps.setInt(7, perfil.getUsrExclusao());

            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static PerfilModel propertyPerfil(PerfilModel perfil) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_perfil where data_exclusao is null";
            if (perfil.getIdPerfil() != 0) {
                sqlState += " and id_perfil = " + perfil.getIdPerfil();
            }
            if (perfil.getTipo() != null) {
                sqlState += " and tipo like '%" + perfil.getTipo() + "%'";
            }
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                perfil.setIdPerfil(rs.getInt("id_perfil"));
                perfil.setTipo(rs.getString("tipo"));
                perfil.setDescricao(rs.getString("descricao"));
                perfil.setDataInclusao(rs.getString("data_inclusao"));
                perfil.setUsrInclusao(Integer.parseInt(rs.getString("usr_inclusao")));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return perfil;
    }

    public static boolean deletePerfil(PerfilModel perfil) {
        Connection con;
        Date date = new Date();
        String data = date.toInstant().toString().substring(0, 10);

        try {
            con = DBConnection.obterConexao();

            String sql = perfil.delete();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, data);
            ps.setInt(2, perfil.getUsrExclusao());
            ps.setInt(3, perfil.getIdPerfil());

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean DeleteBD(int idPerfil) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete tb_id_perfil from perfil where id_perfil like '%" + idPerfil + "%'");
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PerfilDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<PerfilModel> listAllPerfil(PerfilModel perfil) {
        Connection con;
        List<PerfilModel> perfis = new ArrayList<>();

        try {
            String sqlState = perfil.listAllInto();
            if (perfil != null) {

                if (perfil.getDescricao() != null && !perfil.getDescricao().equals("")) {
                    sqlState += " and descricao like '%" + perfil.getDescricao() + "%'";
                }
                if (perfil.getIdPerfil() != 0) {
                    sqlState += " and id_perfil = " + perfil.getIdPerfil();
                }
            }
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PerfilModel perfilProperty = new PerfilModel();
                perfilProperty.setIdPerfil(rs.getInt("id_perfil"));
                perfilProperty.setTipo(rs.getString("tipo"));
                perfilProperty.setDescricao(rs.getString("descricao"));
                perfis.add(perfilProperty);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return perfis;
    }

    public static boolean updatePerfil(PerfilModel perfil) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = perfil.update();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, perfil.getTipo());
            ps.setString(2, perfil.getDescricao());
            ps.setInt(3, perfil.getIdPerfil());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
}

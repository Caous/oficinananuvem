/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.PerfilModel;
import com.thinkcode.models.CategoriaModel;
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
public class CategoriaDAO extends DBConnection {

    public static boolean cadastrarCategoria(CategoriaModel categoria) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "insert into tb_categoria (nome, descricao)"
                    + " values (?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescricao());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static CategoriaModel consultarCategoria(CategoriaModel categoria) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_categoria where data_exclusao is null";
            if (categoria.getIdCategoria() != 0) {
                sqlState += " AND id_categoria = " + categoria.getIdCategoria();
            } else if (categoria.getNome() != null) {
                sqlState += " AND nome like '%" + categoria.getNome() + "%'";
            }
            

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("tipo"));
                categoria.setDescricao(rs.getString("descricao"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }

    public static boolean Delete(int idCategoria) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete from tb_categoria where id_categoria like '%" + idCategoria + "%'");
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<CategoriaModel> CategoriasCadastradas(String filtroDescricao, String filtroNome) {
        Connection con;
        List<CategoriaModel> categorias = new ArrayList<>();

        try {
            String sqlState = "select * from tb_categoria where data_exclusao is null";
            if (filtroDescricao != null && !filtroDescricao.equals("")) {
                sqlState += " and descricao like '%" + filtroDescricao + "%'";
            }
            if (filtroNome != null && !filtroNome.equals("")) {
                sqlState += " and nome = " + filtroNome;
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CategoriaModel categoria = new CategoriaModel();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNome(rs.getString("nome"));
                categoria.setDescricao(rs.getString("descricao"));
                categorias.add(categoria);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }

    public static boolean atualizarCategoria(CategoriaModel categoria) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "update tb_categoria set nome = ?, descricao = ? where id_categoria = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, categoria.getNome());
            ps.setString(2, categoria.getDescricao());
            ps.setInt(3, categoria.getIdCategoria());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
    public static boolean ExcluiCategoria(int idCategoria , int userExclusao) {
         Connection con;
        Date date = new Date();
        String data = date.toInstant().toString().substring(0, 10);

        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("update tb_categoria set data_exclusao = '" + data + "', usr_exclusao = " + userExclusao + " where id_categoria = " + idCategoria);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}

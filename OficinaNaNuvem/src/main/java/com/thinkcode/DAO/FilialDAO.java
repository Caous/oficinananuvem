/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.FilialModel;
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
public class FilialDAO extends DBConnection {

    public static boolean saveFilial(FilialModel filial) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = filial.insertInto();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, filial.getNome());
            ps.setString(2, filial.getDescricao());
            ps.setLong(3, filial.getCnpj());
            ps.setLong(4, filial.getTelefone());
            ps.setInt(5, filial.getCep());
            ps.setString(6, filial.getRua());
            ps.setString(7, filial.getBairro());
            ps.setString(8, filial.getNumero());
            ps.setString(9, filial.getComplemento());
            ps.setString(10, filial.getDataInclusao());
            ps.setInt(11, filial.getUserInclusao());

            ps.execute();

            ok = true;

            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilialDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
        return ok;
    }

    public static boolean updateFilial(FilialModel filial) {

        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = filial.update();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, filial.getNome());
            ps.setString(2, filial.getDescricao());
            ps.setString(3, filial.getCnpj().toString());
            ps.setLong(4, filial.getTelefone());
            ps.setInt(5, filial.getCep());
            ps.setString(6, filial.getRua());
            ps.setString(7, filial.getBairro());
            ps.setString(8, filial.getNumero());
            ps.setString(9, filial.getComplemento());
            ps.setString(10, filial.getDataInclusao());
            ps.setInt(11, filial.getUserInclusao());
            ps.setInt(12, filial.getIdFilial());

            ps.execute();

            ok = true;

            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            String guts = ex.toString();
            System.out.println(ex);
        }
        return ok;
    }

    public static boolean deleteFilial(FilialModel filial) {
        Connection con;
        Date date = new Date();
        try {
            con = DBConnection.obterConexao();

            String sql = FilialModel.delete();

            PreparedStatement ps = con.prepareStatement(sql);
            String data = date.toInstant().toString().substring(0, 10);
            ps.setString(1, data);
            ps.setInt(2, filial.getUserExclusao());
            ps.setInt(3, filial.getIdFilial());

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean DeleteBD(int idFilial) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete id_filial from tb_filial where id_filial like '%" + idFilial + "%'");
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FilialDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<FilialModel> listAllFiliais(FilialModel filial) {
        Connection con;
        List<FilialModel> filiais = new ArrayList<FilialModel>();

        try {
            String sqlState = filial.listAllInto();

            if (filial != null) {

                if (filial.getIdFilial() != 0) {
                    sqlState += " and id_filial = " + filial.getIdFilial();
                }

                if (filial.getRua() != null && !filial.getRua().equals("")) {
                    sqlState += " and rua like '%" + filial.getRua() + "%'";
                }
            }
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                FilialModel filialProperty = new FilialModel();
                filialProperty.setBairro(rs.getString("bairro"));
                filialProperty.setCep(rs.getInt("cep"));
                filialProperty.setCnpj(rs.getLong("cnpj"));
                filialProperty.setComplemento(rs.getString("complemento"));
                filialProperty.setDataExclusao(rs.getString("data_exclusao"));
                filialProperty.setDataInclusao(rs.getString("data_inclusao"));
                filialProperty.setDescricao(rs.getString("Descricao"));
                filialProperty.setIdFilial(rs.getInt("id_filial"));
                filialProperty.setNome(rs.getString("Nome"));
                filialProperty.setNumero(rs.getString("numero"));
                filialProperty.setRua(rs.getString("rua"));
                filialProperty.setTelefone(rs.getLong("telefone"));
                filialProperty.setUserExclusao(rs.getInt("usr_exclusao"));
                filialProperty.setUserInclusao(rs.getInt("usr_inclusao"));
                filiais.add(filialProperty);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filiais;
    }

    public static FilialModel propertyFilial(FilialModel filial) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_filial";

            if (filial.getCnpj() != null) {
                sqlState += " where cnpj = '" + filial.getCnpj() + "'";
            }
            if (filial.getNome() != null) {
                if (filial.getCnpj() != null) {
                    sqlState += " and Nome = '" + filial.getNome() + "' ";
                } else {
                    sqlState += " where Nome = '" + filial.getNome() + "' ";
                }
            }
            if (filial.getIdFilial() != 0) {
                if (filial.getNome() != null && filial.getCnpj() != null) {
                    sqlState += " and id_filial = '" + filial.getIdFilial() + "' ";
                } else {
                    sqlState += " where id_filial = '" + filial.getIdFilial() + "' ";
                }

            }
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                filial.setIdFilial(rs.getInt("id_filial"));
                filial.setNome(rs.getString("Nome"));
                filial.setTelefone(rs.getLong("telefone"));
                filial.setDescricao(rs.getString("Descricao"));
                filial.setCnpj(rs.getLong("cnpj"));
                filial.setCep(rs.getInt("cep"));
                filial.setRua(rs.getString("rua"));
                filial.setBairro(rs.getString("bairro"));
                filial.setNumero(rs.getString("numero"));
                filial.setComplemento(rs.getString("complemento"));
                filial.setDataInclusao(rs.getString("data_inclusao"));
                filial.setDataExclusao(rs.getString("data_exclusao"));
                filial.setUserExclusao(rs.getInt("usr_exclusao"));
                filial.setUserInclusao(rs.getInt("usr_inclusao"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return filial;
    }

}

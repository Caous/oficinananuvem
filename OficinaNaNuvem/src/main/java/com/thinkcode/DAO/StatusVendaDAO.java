/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.Status_Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Moreno
 */
public class StatusVendaDAO extends DBConnection {

    public static boolean saveStatusVenda(Status_Venda statusVenda) {
        boolean ok = false;
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formatter = formmat1.format(ldt);
        statusVenda.setDataInclusao(formatter);

        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = statusVenda.insertInto();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, statusVenda.getStatus());
            ps.setString(2, statusVenda.getDescricao());
            ps.setString(3, statusVenda.getDataInclusao());
            ps.setInt(4, statusVenda.getUsrInclusao());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatusVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static List<Status_Venda> listAllStatus(Status_Venda statusVenda) {
        Connection con;
        List<Status_Venda> status = new ArrayList<>();
        try {
            con = DBConnection.obterConexao();
            String sql = statusVenda.listAllInto();
            if (statusVenda != null) {
                
                if (statusVenda.getId_status() != 0) {
                    sql += " and SV.id_status = " + statusVenda.getId_status();
                }
                if (statusVenda.getDescricao() != null && !statusVenda.getDescricao().equals("")) {
                    sql += " and SV.descricao like '%" + statusVenda.getDescricao() + "%'";
                }
            }
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Status_Venda statu = new Status_Venda();
                statu.setId_status(rs.getInt("SV.id_status"));
                statu.setStatus(rs.getString("SV.status"));
                statu.setDescricao(rs.getString("SV.descricao"));
                statu.setUsrInclusao(Integer.parseInt(rs.getString("SV.usr_inclusao")));
                statu.setDataInclusao(rs.getString("SV.data_inclusao"));
                statu.setUsrDsUsuarioInclusao(rs.getString("US.nome"));
                status.add(statu);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatusVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static boolean deleteStatus(Status_Venda statusVenda) {
        Connection con;
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formatter = formmat1.format(ldt);

        try {
            con = DBConnection.obterConexao();
            String sql = statusVenda.delete();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(0, formatter.substring(0, 10));
            ps.setInt(1, statusVenda.getUsrExclusao());
            ps.setInt(2, statusVenda.getId_status());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatusVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean deleteDBStatus(int idStatus) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("DELETE FROM tb_status_venda WHERE `id_status = " + idStatus);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatusVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static Status_Venda propertyStatus(Status_Venda _Status) {
        Connection con;
        Status_Venda status = new Status_Venda();
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("SELECT SV.*, US.nome FROM `tb_status_venda` as SV INNER JOIN tb_usuario as US on SV.usr_inclusao = US.id_usuario WHERE SV.data_exclusao is null and SV.id_status = " + _Status.getId_status() + " limit 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                status.setId_status(rs.getInt("SV.id_status"));
                status.setStatus(rs.getString("SV.status"));
                status.setDescricao(rs.getString("SV.descricao"));
                status.setUsrInclusao(Integer.parseInt(rs.getString("SV.usr_inclusao")));
                status.setDataInclusao(rs.getString("SV.data_inclusao"));
                status.setUsrDsUsuarioInclusao(rs.getString("US.nome"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatusVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public static boolean updateStatus(Status_Venda _status) {
        Connection con;
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);
        DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        String formatter = formmat1.format(ldt);

        try {
            con = DBConnection.obterConexao();
            String sql = _status.update();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, _status.getStatus());
            ps.setString(2, _status.getDescricao());
            ps.setInt(3, _status.getUsrInclusao());
            ps.setString(4, _status.getDataInclusao());
            ps.setInt(5, _status.getId_status());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StatusVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.DetalhePedidoModel;
import com.thinkcode.models.ItensVenda;
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
 * @author Leonardo Silva
 */
public class ItensVendaDAO extends DBConnection {

    public static boolean cadastrarProduto(ItensVenda itensVenda) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "insert into tb_item_venda (id_item, id_produto, id_venda, quantidade, valor)"
                    + " values (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, itensVenda.getIdItem());
            ps.setInt(2, itensVenda.getIdProduto());
            ps.setInt(3, itensVenda.getIdVenda());
            ps.setInt(4, itensVenda.getQntd());
            ps.setDouble(5, itensVenda.getValor());

            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ItensVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static boolean consultaItensProduto(int idVenda) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("select tb_id_venda from item_venda where id_venda like '%" + idVenda + "%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("idVenda"));
            }
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ItensVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public static List<ItensVenda> consultarTodosItensPedido(int idpedido) {
        Connection con;
        List<ItensVenda> itens = new ArrayList<ItensVenda>();

        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("SELECT tb_item_venda.*,tb_produto.nome FROM tb_item_venda inner join tb_produto on tb_item_venda.id_produto = tb_produto.id_produto where id_venda="+idpedido);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItensVenda item = new ItensVenda();
               item.setIdItem(Integer.parseInt(rs.getString("id_item")));
               item.setIdVenda(Integer.parseInt(rs.getString("id_venda")));
               item.setQntd(Integer.parseInt(rs.getString("quantidade")));
               item.setValor(Double.parseDouble(rs.getString("valor")));
               item.setNomeProduto(rs.getString("nome"));
               itens.add(item);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DetalhePedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itens;
    }

    public static boolean excluirItensVenda(int idVendas) {
        Connection con;
        Date date = new Date();
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("update tb_item_venda set dt_exclusao = " + date + " where id_item like '%" + idVendas + "%'");
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ItensVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean Delete(int idVendas) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete id_item from tb_item_venda where id_item like '%" + idVendas + "%'");
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ItensVendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

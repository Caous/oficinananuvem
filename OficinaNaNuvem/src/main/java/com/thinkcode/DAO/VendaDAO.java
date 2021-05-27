/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Controller.ItensVendaController;
import Infrastructure.DBConnection;
import com.thinkcode.models.ItensVenda;
import com.thinkcode.models.VendaModel;
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
public class VendaDAO extends DBConnection {

    public static boolean cadastraraVenda(VendaModel venda) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "INSERT INTO tb_venda ( id_status, id_endereco, id_usuario, id_filial, cpf_cnpj, pagamento, parcelas, total, data, identificador, eccommerce, id_endfatura, id_cliente, id_metodo, frete) VALUES (? ,? ,? ,? ,? , ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, venda.getIdStatus());
            ps.setInt(2, venda.getIdEndereco());
            ps.setInt(3, venda.getIdUsuario());
            ps.setInt(4, venda.getIdFilial());
            ps.setString(5, venda.getCpfCnpj());
            ps.setInt(6, venda.getPagamento());
            ps.setDouble(7, venda.getParcelas());
            ps.setDouble(8, venda.getTotal());
            ps.setString(9, venda.getData());
            ps.setString(10, venda.getIdentificador());
            ps.setBoolean(11, venda.getEccomerce());
            ps.setInt(12, venda.getIdEndFatura());
            ps.setInt(13, venda.getIdCliente());
            ps.setInt(14, venda.getIdMetodo());
            ps.setDouble(15, venda.getFrete());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static boolean consultarVenda(String idVenda) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("select nome from tb_venda where id_venda like '%" + idVenda + "%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("id_usuario"));
            }
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean excluirVenda(String idVenda) {
        Connection con;
        Date date = new Date();
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("update tb_venda set dt_exlusao = " + date + " where id_venda like '%" + idVenda + "%'");
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean Delete(String idVenda) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete nome from tb_venda where id_venda like '%" + idVenda + "%'");
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<VendaModel> consultarVendaCompleta(String idVenda) {
        Connection con;
        List<VendaModel> venda = new ArrayList<VendaModel>();
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("SELEC * from tb_venda where id_venda = '" + idVenda + "'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VendaModel vendaDB = new VendaModel();
                vendaDB.setIdVenda(Integer.parseInt(idVenda));
                vendaDB.setIdStatus(Integer.parseInt(rs.getString("id_status")));
                vendaDB.setIdEndereco(Integer.parseInt(rs.getString("id_endereco")));
                vendaDB.setIdUsuario(Integer.parseInt(rs.getString("id_usuario")));
                vendaDB.setIdFilial(Integer.parseInt(rs.getString("venda.pagamento")));
                vendaDB.setCpfCnpj(rs.getString("cpf_cnpj"));
                vendaDB.setPagamento(Integer.parseInt(rs.getString("pagamento")));
                vendaDB.setParcelas(Integer.parseInt(rs.getString("parcelas")));
                vendaDB.setTotal(Double.parseDouble(rs.getString("total")));
                vendaDB.setData(rs.getString("data"));
                vendaDB.setDataExclusao(rs.getString("data_exclusao"));
                vendaDB.setUserExclusao(rs.getString("usr_exclusao"));
                vendaDB.setCodRastreio(rs.getString("codigo_rastreio"));
                venda.add(vendaDB);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return venda;
    }

    public static List<VendaModel> consultarPedidos(int idCliente) {
        Connection con;
        List<VendaModel> pedidos = new ArrayList<VendaModel>();
        try {
            con = DBConnection.obterConexao();
             PreparedStatement ps = con.prepareStatement("SELECT tb_venda.*,tb_status_venda.status,tb_metodo_pagamento.metodo,tb_endereco_cliente.nome_endereco FROM tb_venda inner join tb_status_venda on tb_venda.id_status = tb_status_venda.id_status inner join tb_metodo_pagamento on tb_venda.id_metodo = tb_metodo_pagamento.id_metodo inner join tb_endereco_cliente on tb_venda.id_endereco = tb_endereco_cliente.id_endereco where tb_venda.eccommerce = 1 and tb_venda.id_cliente = " + idCliente + " and tb_venda.usr_exclusao is null ORDER BY id_venda desc");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                VendaModel vendaDB = new VendaModel();
                vendaDB.setIdVenda(Integer.parseInt(rs.getString("id_venda")));
                vendaDB.setIdStatus(Integer.parseInt(rs.getString("id_status")));
                vendaDB.setIdEndereco(Integer.parseInt(rs.getString("id_endereco")));
                vendaDB.setIdUsuario(Integer.parseInt(rs.getString("id_usuario")));
                vendaDB.setIdFilial(Integer.parseInt(rs.getString("id_filial")));
                vendaDB.setCpfCnpj(rs.getString("cpf_cnpj"));
                vendaDB.setPagamento(Integer.parseInt(rs.getString("pagamento")));
                vendaDB.setParcelas(Integer.parseInt(rs.getString("parcelas")));
                vendaDB.setTotal(Double.parseDouble(rs.getString("total")));
                vendaDB.setData(rs.getString("data"));
                vendaDB.setDataExclusao(rs.getString("data_exclusao"));
                vendaDB.setUserExclusao(rs.getString("usr_exclusao"));
                vendaDB.setCodRastreio(rs.getString("codigo_rastreio"));
                vendaDB.setIdMetodo(Integer.parseInt(rs.getString("id_metodo")));
                vendaDB.setIdCliente(Integer.parseInt(rs.getString("id_cliente")));
                vendaDB.setEccomerce(true);
                vendaDB.setFrete(Double.parseDouble(rs.getString("frete")));
                vendaDB.setStatus(rs.getString("status"));
                vendaDB.setMetodo(rs.getString("metodo"));
                vendaDB.setEndentrega(rs.getString("nome_endereco"));
                ItensVendaController itensVenda = new ItensVendaController();
                List<ItensVenda> itens = itensVenda.consultaItensdaVenda(vendaDB.getIdVenda());
                vendaDB.setListaItens(itens);
                pedidos.add(vendaDB);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedidos;
    }

    public static int consultarUltimoIdVenda() {
        Connection con;
        int id = 1;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("select id_venda from tb_venda order by id_venda desc limit 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id_venda");
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public static boolean updateIdenficador(VendaModel _venda) {

        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = "UPDATE tb_venda SET identificador = '" + _venda.getIdentificador() + "' WHERE id_venda = " + _venda.getIdVenda();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            String guts = ex.toString();
            System.out.println(ex);
        }
        return ok;
    }
    
    
    public static boolean updateStatus(VendaModel _venda) {

        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = "UPDATE tb_venda SET id_status = " + _venda.getIdStatus()+ ", usr_alteracao = " +_venda.getIdUsuario()+ ", data_alteracao = '"+_venda.getData()+"' WHERE id_venda = " + _venda.getIdVenda();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            String guts = ex.toString();
            System.out.println(ex);
        }
        return ok;
    }

}

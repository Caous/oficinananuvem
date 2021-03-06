/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.RelatorioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo Nascimento
 */
public class RelatorioDAO extends DBConnection {

    public static List<RelatorioModel> VendasEfetuadas(RelatorioModel _filtro) {
        Connection con;
        List<RelatorioModel> relatorios = new ArrayList<RelatorioModel>();

        try {
            String sqlState = "SELECT \n"
                    + "	tb_venda.id_venda,\n"
                    + "	fi.nome as nomeFilial,\n"
                    + "	us.nome as nomeVendedor, \n"
                    + "	cli.nome as nomeCliente,	\n"
                    + "	tb_venda.cpf_cnpj,\n"
                    + "	tb_venda.pagamento,\n"
                    + "	tb_venda.parcelas,\n"
                    + "	tb_venda.total,\n"
                    + "	tb_venda.data\n"
                    + "FROM tb_venda  \n"
                    + "INNER JOIN tb_usuario as us ON tb_venda.id_usuario = us.id_usuario  \n"
                    + "INNER JOIN tb_usuario as cli ON tb_venda.cpf_cnpj = cli.cpf_cnpj  \n"
                    + "INNER JOIN tb_filial as fi ON tb_venda.id_filial = fi.id_filial";

            if (_filtro != null && _filtro.getIdFilial() != 0) {
                sqlState += " where fi.id_filial = " + _filtro.getIdFilial();
            }
            if (_filtro != null && _filtro.getidVendedor() != 0) {
                if (_filtro.getIdFilial() != 0) {
                    sqlState += " and us.id_usuario = " + _filtro.getidVendedor();
                } else {

                    sqlState += " where us.id_usuario = " + _filtro.getidVendedor();
                }
            }
            if (_filtro != null && _filtro.getCpfCliente() != null) {
                if (_filtro.getIdFilial() != 0 || _filtro.getidVendedor() != 0) {
                    sqlState += " and cli.cpf_cnpj = '" + _filtro.getCpfCliente() + "'";
                } else {

                    sqlState += " where cli.cpf_cnpj = '" + _filtro.getCpfCliente() + "'";
                }

            }
            if (_filtro != null && _filtro.getidPagamento() != 0) {
                if (_filtro.getIdFilial() != 0 || _filtro.getidVendedor() != 0 || _filtro.getCpfCliente() != null) {
                    sqlState += " and tb_venda.pagamento = " + _filtro.getidPagamento();
                } else {

                    sqlState += " where tb_venda.pagamento = " + _filtro.getidPagamento();
                }
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RelatorioModel relatorio = new RelatorioModel();
                relatorio.setIdVenda(Integer.parseInt(rs.getString("tb_venda.id_venda")));
                relatorio.setfilialNome(rs.getString("nomeFilial"));
                relatorio.setusuarioNome(rs.getString("nomeVendedor"));
                relatorio.setnomeCliente(rs.getString("nomeCliente"));
                relatorio.setcpfCnpj(rs.getString("tb_venda.cpf_cnpj"));
                if (Integer.parseInt(rs.getString("tb_venda.pagamento")) == 1) {
                    relatorio.setformaPagamento("Cart??o de Cr??dito");
                } else if (Integer.parseInt(rs.getString("tb_venda.pagamento")) == 2) {
                    relatorio.setformaPagamento("Cart??o de D??bito");
                } else {
                    relatorio.setformaPagamento("Dinheiro");
                }

                relatorio.setparcelas(Integer.parseInt(rs.getString("tb_venda.parcelas")));
                relatorio.settotal(Double.parseDouble(rs.getString("tb_venda.total")));
                relatorio.setdata(rs.getString("tb_venda.data").toString());

                relatorios.add(relatorio);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return relatorios;
    }

    public static List<RelatorioModel> EcommerceVendas(RelatorioModel _filtro) {
        Connection con;
        List<RelatorioModel> relatorios = new ArrayList<RelatorioModel>();

        try {
            String sqlState = "SELECT \n"
                    + "	tb_venda.id_venda,\n"
                    + "	fi.nome as nomeFilial,\n"
                    + "	us.nome as nomeVendedor, \n"
                    + "	cli.nome as nomeCliente,	\n"
                    + "	cli.cpf_cnpj,\n"
                    + "	tb_venda.pagamento,\n"
                    + "	tb_venda.parcelas,\n"
                    + "	tb_venda.total,\n"
                    + "	tb_venda.data,\n"
                    + "	tb_venda.eccommerce,\n"
                    + "	SV.status, \n"
                    + "	SV.descricao,\n"
                    + "	MP.metodo \n"
                    + "FROM tb_venda  \n"
                    + "INNER JOIN tb_usuario as us ON tb_venda.id_usuario = us.id_usuario  \n"
                    + "INNER JOIN tb_cliente as cli ON tb_venda.id_cliente = cli.id_cliente  \n"
                    + "INNER JOIN tb_filial as fi ON tb_venda.id_filial = fi.id_filial \n"
                    + "INNER JOIN tb_status_venda as SV ON tb_venda.id_status = SV.id_status \n"
                    + "INNER JOIN tb_metodo_pagamento as MP ON tb_venda.id_metodo = MP.id_metodo";

            if (_filtro != null && _filtro.getIdFilial() != 0) {
                sqlState += " where fi.id_filial = " + _filtro.getIdFilial();
            }
            if (_filtro != null && _filtro.getIdVenda() != 0) {
                if (_filtro.getIdFilial() != 0) {
                    sqlState += " and tb_venda.id_venda = " + _filtro.getIdVenda();
                } else {

                    sqlState += " where tb_venda.id_venda = " + _filtro.getIdVenda();
                }
            }
            if (_filtro != null && _filtro.getidVendedor() != 0) {
                if (_filtro.getIdFilial() != 0) {
                    sqlState += " and us.id_usuario = " + _filtro.getidVendedor();
                } else {

                    sqlState += " where us.id_usuario = " + _filtro.getidVendedor();
                }
            }
            if (_filtro != null && _filtro.getCpfCliente() != null) {
                if (_filtro.getIdFilial() != 0 || _filtro.getidVendedor() != 0) {
                    sqlState += " and cli.cpf_cnpj = '" + _filtro.getCpfCliente() + "'";
                } else {

                    sqlState += " where cli.cpf_cnpj = '" + _filtro.getCpfCliente() + "'";
                }

            }
            if (_filtro != null && _filtro.getidPagamento() != 0) {
                if (_filtro.getIdFilial() != 0 || _filtro.getidVendedor() != 0 || _filtro.getCpfCliente() != null) {
                    sqlState += " and tb_venda.pagamento = " + _filtro.getidPagamento();
                } else {

                    sqlState += " where tb_venda.pagamento = " + _filtro.getidPagamento();
                }
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RelatorioModel relatorio = new RelatorioModel();
                relatorio.setIdVenda(Integer.parseInt(rs.getString("tb_venda.id_venda")));
                relatorio.setfilialNome(rs.getString("nomeFilial"));
                relatorio.setusuarioNome(rs.getString("nomeVendedor"));
                relatorio.setnomeCliente(rs.getString("nomeCliente"));
                relatorio.setcpfCnpj(rs.getString("cli.cpf_cnpj"));
                relatorio.setmetodoPagamento(rs.getString("MP.metodo"));
                relatorio.setparcelas(Integer.parseInt(rs.getString("tb_venda.parcelas")));
                relatorio.settotal(Double.parseDouble(rs.getString("tb_venda.total")));
                relatorio.setdata(rs.getString("tb_venda.data").toString());
                relatorio.setsistema(rs.getBoolean("tb_venda.eccommerce"));
                relatorio.setdsStatus(rs.getString("SV.status"));
                relatorio.setdsStatusVenda(rs.getString("SV.descricao"));

                relatorios.add(relatorio);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return relatorios;
    }
}

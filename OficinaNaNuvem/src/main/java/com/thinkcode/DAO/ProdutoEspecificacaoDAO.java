/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.ProdutoEspecificacaoModel;
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
 * @author Leonardo Moreno
 */
public class ProdutoEspecificacaoDAO {

    public static boolean cadastrarEspecificacao(List<ProdutoEspecificacaoModel> espec) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            for (int i = 0; i < espec.size(); i++) {
                ProdutoEspecificacaoModel esp = new ProdutoEspecificacaoModel();
                esp = espec.get(i);
                String sql = "insert into tb_produto_especificacao (id_produto,especificacao,respostas)"
                        + " values (?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, esp.getIdProduto());
                ps.setString(2, esp.getEspecificacao());
                ps.setString(3, esp.getResposta());
                ps.execute();
                ok = true;
                ps.close();
            }

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoEspecificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static ProdutoEspecificacaoModel consultarEspecificacao(ProdutoEspecificacaoModel esp) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_produto_especificacao where id_especificacao = " + esp.getIdEspecificacao();

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                esp.setAux(rs.getInt("id_especificacao"));
                esp.setIdProduto(rs.getInt("id_produto"));
                esp.setEspecificacao(rs.getString("especificacao"));
                esp.setResposta(rs.getString("resposta"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoEspecificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return esp;
    }

    public static boolean Delete(ProdutoEspecificacaoModel espec) {
        Connection con;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("update  tb_produto_especificacao set data_exclusao = '" + date.toString().substring(0, 10) + "' where id_especificacao = " + espec.getIdEspecificacao());
            ps.execute();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoEspecificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<ProdutoEspecificacaoModel> EspecificacoesCadastradas(int id_produto) {
        Connection con;
        List<ProdutoEspecificacaoModel> perguntas = new ArrayList<>();

        try {
            String sqlState = "select * from tb_produto_especificacao WHERE id_produto=" + id_produto + " and data_exclusao is null";

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoEspecificacaoModel pergunta = new ProdutoEspecificacaoModel();
                pergunta.setIdEspecificacao(rs.getInt("id_especificacao"));
                pergunta.setIdProduto(rs.getInt("id_produto"));
                pergunta.setEspecificacao(rs.getString("especificacao"));
                pergunta.setResposta(rs.getString("respostas"));
                perguntas.add(pergunta);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoPerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return perguntas;
    }

    public static boolean atualizarEspecificacao(List<ProdutoEspecificacaoModel> espec) {
        boolean ok = false;
        Connection con;
        try {

            for (int i = 0; i < espec.size(); i++) {
                ProdutoEspecificacaoModel esp = new ProdutoEspecificacaoModel();
                esp = espec.get(i);
                List<ProdutoEspecificacaoModel> _produtosAuxilio = new ArrayList<ProdutoEspecificacaoModel>();

                boolean insert = true;
                con = DBConnection.obterConexao();
                String sql = "select COUNT(id_especificacao) as QTD from tb_produto_especificacao where id_especificacao = ? and id_produto =  ?";
                PreparedStatement ps = con.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = ps.executeQuery();
                ps = con.prepareStatement(sql);
                ps.setInt(1, esp.getIdEspecificacao());
                ps.setInt(2, esp.getIdProduto());
                rs = ps.executeQuery();

                while (rs.next()) {
                    int qtd = rs.getInt("QTD");
                    if (qtd > 0) {
                        insert = false;
                    }
                }
                if (insert) {
                    _produtosAuxilio.add(esp);
                    cadastrarEspecificacao(_produtosAuxilio);
                }
//                sql = "update tb_produto_especificacao set pergunta = ?, resposta = ? where id_especificacao = ?";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, esp.getEspecificacao());
//                ps.setString(2, esp.getResposta());
//                ps.setInt(3, esp.getIdEspecificacao());
//                ps.execute();
                ok = true;
                ps.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoEspecificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

}

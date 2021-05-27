/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.ProdutoPerguntaModel;
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
public class ProdutoPerguntaDAO {

    public static boolean cadastrarPergunta(List<ProdutoPerguntaModel> perguntas) {
        boolean ok = false;
        Connection con;
        try {

            for (int i = 0; i < perguntas.size(); i++) {
                ProdutoPerguntaModel pergunta = new ProdutoPerguntaModel();
                pergunta = perguntas.get(i);
                con = DBConnection.obterConexao();
                String sql = "insert into tb_produto_pergunta (id_produto,pergunta,resposta,pontos)"
                        + " values (?,?,?,?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, pergunta.getId_produto());
                ps.setString(2, pergunta.getPergunta());
                ps.setString(3, pergunta.getResposta());
                ps.setInt(4, pergunta.getPontos());
                ps.execute();
                ok = true;
                ps.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoPerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static ProdutoPerguntaModel consultarPergunta(ProdutoPerguntaModel pergunta) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_produto_pergunta where id_img = " + pergunta.getIdPergunta();

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                pergunta.setIdPergunta(rs.getInt("id_pergunta"));
                pergunta.setId_produto(rs.getInt("id_produto"));
                pergunta.setPergunta(rs.getString("pergunta"));
                pergunta.setResposta(rs.getString("resposta"));
                pergunta.setPontos(rs.getInt("pontos"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoPerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pergunta;
    }

    public static boolean Delete(ProdutoPerguntaModel pergunta) {
        Connection con;
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("update  tb_produto_pergunta set data_exclusao = '" + date.toString().substring(0, 10) + "' where id_pergunta = " + pergunta.getIdPergunta());
            ps.execute();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoPerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<ProdutoPerguntaModel> PerguntasCadastradas(int id_produto) {
        Connection con;
        List<ProdutoPerguntaModel> perguntas = new ArrayList<>();
        if (id_produto != 0) {

            try {
                String sqlState = "select * from tb_produto_pergunta WHERE id_produto=" + id_produto + " and data_exclusao is null";

                con = DBConnection.obterConexao();
                PreparedStatement ps = con.prepareStatement(sqlState,
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    ProdutoPerguntaModel pergunta = new ProdutoPerguntaModel();
                    pergunta.setIdPergunta(rs.getInt("id_pergunta"));
                    pergunta.setId_produto(rs.getInt("id_produto"));
                    pergunta.setPergunta(rs.getString("pergunta"));
                    pergunta.setResposta(rs.getString("resposta"));
                    pergunta.setPontos(rs.getInt("pontos"));
                    perguntas.add(pergunta);
                }
                ps.close();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(ProdutoPerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return perguntas;
    }

    public static boolean atualizarPergunta(List<ProdutoPerguntaModel> perguntas) {
        boolean ok = false;
        Connection con;

        try {

            for (int i = 0; i < perguntas.size(); i++) {

                ProdutoPerguntaModel pergunta = new ProdutoPerguntaModel();
                List<ProdutoPerguntaModel> perguntasAuxilio = new ArrayList<ProdutoPerguntaModel>();
                pergunta = perguntas.get(i);

                con = DBConnection.obterConexao();

                boolean insert = true;

                String sql = "select COUNT(id_pergunta) as QTD from tb_produto_pergunta where id_pergunta = ? and id_produto =  ?";
                con = DBConnection.obterConexao();
                PreparedStatement ps = con.prepareStatement(sql,
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ps = con.prepareStatement(sql);
                ps.setInt(1, pergunta.getIdPergunta());
                ps.setInt(2, pergunta.getId_produto());
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int qtd = rs.getInt("QTD");
                    if (qtd > 0) {
                        insert = false;
                    }
                }
                if (insert) {
                    perguntasAuxilio.add(pergunta);
                    cadastrarPergunta(perguntasAuxilio);
                }

//                sql = "update tb_produto_pergunta set pergunta = ?, resposta = ? where id_pergunta = ?";
//                ps = con.prepareStatement(sql);
//                ps.setString(1, pergunta.getPergunta());
//                ps.setString(2, pergunta.getResposta());
//                ps.setInt(3, pergunta.getIdPergunta());
//                ps.execute();
                ok = true;
                ps.close();
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoPerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static boolean consultarPerguntaExiste(int idpergunta, int idproduto) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_produto_pergunta WHERE id_pergunta = " + idpergunta + " and id_produto = " + idproduto,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                ps.close();
                return true;
            }
            return false;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoPerguntaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.ProdutoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Silva
 */
public class ProdutoDAO extends DBConnection {

    public static boolean saveProduto(ProdutoModel produto) {
        boolean ok = false;
        Connection con;
        try {

            con = DBConnection.obterConexao();
            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);

            String sql = produto.insertInto();

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, produto.getIdUsuario());
            ps.setInt(2, produto.getIdFilial());
            ps.setString(3, produto.getTipo());
            ps.setString(4, produto.getNome());
            ps.setInt(5, produto.getQuantidade());
            ps.setString(6, produto.getDescricao());
            ps.setDouble(7, produto.getValor());
            ps.setInt(8, produto.getIdUsuario());
            ps.setInt(9, produto.getIdCategoria());
            ps.setDate(10, date);
            ps.setInt(11, 5);
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static ProdutoModel propertyProduto(ProdutoModel produto) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("select * from tb_produto where id_produto = " + produto.getIdProduto(),
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                produto.setDescricao(rs.getString("descricao"));
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(Integer.parseInt(rs.getString("qtde")));
                produto.setTipo(rs.getString("tipo"));
                produto.setValor(Double.parseDouble(rs.getString("valor")));
                produto.setIdProduto(rs.getInt("id_produto"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produto;
    }

    public static int lastRegister(ProdutoModel produto) {
        Connection con;

        int lastIdProduto = 0;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("select id_produto from tb_produto where id_usuario = " + produto.getIdUsuario() + " order by id_produto desc limit 1;",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                lastIdProduto = rs.getInt("id_produto");
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastIdProduto;
    }

    public static boolean updateProduto(ProdutoModel produto) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            //Date date = new Date();

            long millis = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(millis);
            
            String sql = produto.update();
            
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getIdUsuario());
            ps.setInt(2, produto.getIdFilial());
            ps.setString(3, produto.getTipo());
            ps.setString(4, produto.getNome());
            ps.setInt(5, produto.getQuantidade());
            ps.setString(6, produto.getDescricao());
            ps.setDouble(7, produto.getValor());
            ps.setInt(8, produto.getIdUsuario());
            ps.setInt(9, produto.getIdCategoria());
            ps.setDate(10, date);
            ps.setInt(11, 5);
            ps.setDouble(12, produto.getIdProduto());
            
            ps.executeUpdate();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static boolean deleteProduto(ProdutoModel produto) {
        Connection con;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        try {
            con = DBConnection.obterConexao();
            String dataAux = formatter.format(date);
            String sqlState = produto.delete();
            PreparedStatement ps = con.prepareStatement(sqlState);
            
            ps.setString(0, date.toInstant().toString().substring(0, 10));
            ps.setInt(1, produto.getUserExclusao());
            ps.setInt(2, produto.getIdProduto());
            
            ps.execute();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean DeleteBD(ProdutoModel produto) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete nome from tb_produto where id_produto like '%" + produto.getIdProduto() + "%'",
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<ProdutoModel> listAllProdutos(ProdutoModel produto) {
        Connection con;
        List<ProdutoModel> produtos = new ArrayList<>();

        try {
            String sqlState = produto.listAllInto();
            if (produto != null) {
                if (produto.getIdFilial() != 0) {
                    sqlState += " and PO.id_filial = " + produto.getIdFilial();
                }
                if (produto.getTipo() != null && !produto.getTipo().equals("")) {
                    sqlState += " and PO.tipo = '" + produto.getTipo() + "'";
                }
                if (produto.getNome()!= null && !produto.getNome().equals("")) {
                    sqlState += " and PO.nome like '%" + produto.getNome() + "%' and PO.qtde > 0 LIMIT 1";
                }
                if (produto.getIdCategoria() != 0) {
                    sqlState += " and PO.id_categoria = " + produto.getIdCategoria();
                }
            }
            sqlState += " order by PO.nome";
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoModel produtoM = new ProdutoModel();
                produtoM.setIdProduto(rs.getInt("PO.id_produto"));
                produtoM.setIdUsuario(rs.getInt("PO.id_usuario"));
                produtoM.setIdFilial(rs.getInt("PO.id_filial"));
                produtoM.setTipo(rs.getString("PO.tipo"));
                produtoM.setNome(rs.getString("PO.nome"));
                produtoM.setQuantidade(rs.getInt("PO.qtde"));
                produtoM.setDescricao(rs.getString("PO.descricao"));
                produtoM.setFilial(rs.getString("FI.FILIAL"));
                produtoM.setCategoria(rs.getString("CG.CATEGORIA"));
                produtoM.setValor(rs.getDouble("PO.valor"));
                produtos.add(produtoM);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }

    public static List<ProdutoModel> produtosCadastradosHome(String filtroFilial, String filtroTipo, String filtroNome) {
        Connection con;
        List<ProdutoModel> produtos = new ArrayList<>();

        try {
            String sqlState = "select\n"
                    + "PO.*\n"
                    + ",FI.NOME as FILIAL\n"
                    + ",CG.NOME as CATEGORIA\n"
                    + ",IM.img\n"
                    + "from tb_produto PO\n"
                    + "inner join tb_filial FI on FI.ID_FILIAL = PO.ID_FILIAL\n"
                    + "inner join tb_categoria CG on CG.ID_CATEGORIA = PO.ID_CATEGORIA \n"
                    + "left join tb_produto_img IM on IM.ID_PRODUTO = PO.ID_PRODUTO\n"
                    + "                    	where PO.data_exclusao is null and IM.home = true";
            if (filtroFilial != null && !filtroFilial.equals("")) {
                sqlState += " and PO.id_filial = " + filtroFilial;
            }
            if (filtroTipo != null && !filtroTipo.equals("")) {
                sqlState += " and PO.tipo = '" + filtroTipo + "'";
            }
            if (filtroNome != null && !filtroNome.equals("")) {
                sqlState += " and PO.nome like '%" + filtroNome + "%' and PO.qtde > 0";
            }
            sqlState += " order by nome";
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoModel produto = new ProdutoModel();
                produto.setIdProduto(rs.getInt("PO.id_produto"));
                produto.setIdUsuario(rs.getInt("PO.id_usuario"));
                produto.setIdFilial(rs.getInt("PO.id_filial"));
                produto.setTipo(rs.getString("PO.tipo"));
                produto.setNome(rs.getString("PO.nome"));
                produto.setQuantidade(rs.getInt("PO.qtde"));
                produto.setDescricao(rs.getString("PO.descricao"));
                produto.setFilial(rs.getString("FI.FILIAL"));
                produto.setCategoria(rs.getString("CG.CATEGORIA"));
                produto.setValor(rs.getDouble("PO.valor"));
                produto.setImgCapa(rs.getString("IM.img"));
                produtos.add(produto);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produtos;
    }

    public static boolean atualizarQtdeProduto(int id, int qtde) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "update tb_produto  set qtde = qtde-(?)  where id_produto = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, qtde);
            ps.setInt(2, id);
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
}

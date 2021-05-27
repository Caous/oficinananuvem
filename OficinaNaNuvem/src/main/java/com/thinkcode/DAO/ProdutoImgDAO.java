package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.ProdutoImgModel;
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
public class ProdutoImgDAO extends DBConnection {

    public static boolean cadastrarImg(ProdutoImgModel img) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "insert into tb_produto_img (id_produto,img, descricao,home)"
                    + " values (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, img.getIdProduto());
            ps.setString(2, img.getNome());
            ps.setString(3, img.getDescricao());
            ps.setBoolean(4, img.getHome());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static ProdutoImgModel consultarImg(ProdutoImgModel img) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_produto_img";

            if (img.getIdProdutoImg() != 0) {
                sqlState += " where id_img = " + img.getIdProdutoImg();
            }

            if (img.getIdProduto() != 0) {
                sqlState += " where id_produto = " + img.getIdProduto();
            }

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                img.setIdProdutoImg(rs.getInt("id_img"));
                img.setIdProduto(rs.getInt("id_produto"));
                img.setDescricao(rs.getString("descricao"));
                img.setNome(rs.getString("img"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return img;
    }

    public static boolean Delete(int idImg) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete  from tb_produto_img where id_img =" + idImg);
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<ProdutoImgModel> ImgsCadastradas(int id_produto) {
        Connection con;
        List<ProdutoImgModel> imgs = new ArrayList<>();

        try {
            String sqlState = "select * from tb_produto_img WHERE id_produto=" + id_produto;

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProdutoImgModel img = new ProdutoImgModel();
                img.setIdProdutoImg(rs.getInt("id_img"));
                img.setIdProduto(rs.getInt("id_produto"));
                img.setNome(rs.getString("img"));
                img.setDescricao(rs.getString("descricao"));
                imgs.add(img);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return imgs;
    }

    public static boolean atualizarImg(ProdutoImgModel img) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "update tb_produto_img set nome = ?, descricao = ? where id_img = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, img.getNome());
            ps.setString(2, img.getDescricao());
            ps.setInt(3, img.getIdProdutoImg());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ProdutoImgDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOEcommerce;

import Infrastructure.DBConnection;
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
import modelEcommerce.EnderecoClienteModel;

/**
 *
 * @author Leonardo Moreno
 */
public class EnderecoClienteDAO extends DBConnection {

    public static boolean cadastrarEnderecoCliente(EnderecoClienteModel endereco) {
        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sql = "INSERT INTO tb_endereco_cliente ( id_cliente, nome_endereco, nome_receptor, cep, rua, numero, complemento, bairro, cidade, Estado, telefone, referencia_entrega,fatura) "
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, endereco.getId_cliente());
            ps.setString(2, endereco.getNome_endereco());
            ps.setString(3, endereco.getNome_receptor());
            ps.setString(4, endereco.getCep());
            ps.setString(5, endereco.getRua());
            ps.setInt(6, endereco.getNumero());
            ps.setString(7, endereco.getComplemento());
            ps.setString(8, endereco.getBairro());
            ps.setString(9, endereco.getCidade());
            ps.setString(10, endereco.getEstado());
            ps.setString(11, endereco.getTelefone());
            ps.setString(12, endereco.getReferencia_entrega());
            ps.setBoolean(13, endereco.getFatura());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ok;
    }

    public static List<EnderecoClienteModel> EnderecosCadastradosById(int id) {
        Connection con;
        List<EnderecoClienteModel> enderecos = new ArrayList<>();

        try {
            String sqlState = "select * from tb_endereco_cliente  where data_exclusao is null";
            if (id > 0 && id != 0) {
                sqlState += " and id_cliente = " + id;
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                EnderecoClienteModel end = new EnderecoClienteModel();
                end.setId_endereco(rs.getInt("id_endereco"));
                end.setNome_endereco(rs.getString("nome_endereco"));
                end.setNome_receptor(rs.getString("nome_receptor"));
                end.setCep(rs.getString("cep"));
                end.setNumero(rs.getInt("numero"));
                end.setRua(rs.getString("rua"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setTelefone(rs.getString("telefone"));
                end.setReferencia_entrega(rs.getString("referencia_entrega"));
                end.setFatura(rs.getBoolean("fatura"));
                enderecos.add(end);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enderecos;
    }

    public static boolean EnderecosCadastradosByIdForFatura(int id) {
        Connection con;
        boolean existe = false;

        try {
            String sqlState = "select * from tb_endereco_cliente  where data_exclusao is null and fatura=true";
            if (id > 0 && id != 0) {
                sqlState += " and id_cliente = " + id;
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                existe = true;
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    public static boolean ExcluiCategoria(int idEndereco) {
        Connection con;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        try {
            con = DBConnection.obterConexao();
            String dataAux = formatter.format(date);
            PreparedStatement ps = con.prepareStatement("update tb_endereco_cliente set data_exclusao =" + dataAux + " where id_endereco = " + idEndereco);

            ps.executeUpdate();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static boolean Delete(int idEndereco) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement("delete from tb_endereco_cliente where id_endereco like '%" + idEndereco + "%'");
            ResultSet rs = ps.executeQuery();
            ps.close();
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static int ultimoRegistro(EnderecoClienteModel endereco) {
        Connection con;
        int idLastEndereco = 0;

        try {
            String sqlState = "select * from tb_endereco_cliente  where data_exclusao is null";

            if (endereco.getId_cliente() > 0 && endereco.getId_cliente() != 0) {
                sqlState += " and id_cliente = " + endereco.getId_cliente();
            }
            if (endereco.getCep() != null && !endereco.getCep().equals("")) {
                sqlState += " and cep = '" + endereco.getCep() + "'";
            }

            sqlState += " ORDER BY id_endereco DESC LIMIT 1";

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.first()) {
                idLastEndereco = rs.getInt("id_endereco");
                break;
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idLastEndereco;
    }

    public static EnderecoClienteModel EnderecoFaturaCadastrado(EnderecoClienteModel endereco) {
        Connection con;
        EnderecoClienteModel end = new EnderecoClienteModel();

        try {
            String sqlState = "select * from tb_endereco_cliente  where data_exclusao is null and fatura=true";
            if (endereco.getId_cliente() > 0 && endereco.getId_cliente() != 0) {
                sqlState += " and id_cliente = " + endereco.getId_cliente() + " ORDER BY id_endereco DESC LIMIT 1;";
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                end.setId_endereco(rs.getInt("id_endereco"));
                end.setNome_endereco(rs.getString("nome_endereco"));
                end.setNome_receptor(rs.getString("nome_receptor"));
                end.setCep(rs.getString("cep"));
                end.setNumero(rs.getInt("numero"));
                end.setRua(rs.getString("rua"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setTelefone(rs.getString("telefone"));
                end.setReferencia_entrega(rs.getString("referencia_entrega"));
                end.setFatura(rs.getBoolean("fatura"));
                end.setId_cliente(rs.getInt("id_cliente"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return end;
    }
    public static EnderecoClienteModel EnderecoFaturaPropriedade(EnderecoClienteModel endereco) {
        Connection con;
        EnderecoClienteModel end = new EnderecoClienteModel();

        try {
            String sqlState = "select * from tb_endereco_cliente  where data_exclusao is null ";
            if (endereco.getId_endereco() > 0 && endereco.getId_endereco() != 0) {
                sqlState += " and id_endereco = " + endereco.getId_endereco()+ " ORDER BY id_endereco DESC LIMIT 1;";
            }

            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                end.setId_endereco(rs.getInt("id_endereco"));
                end.setNome_endereco(rs.getString("nome_endereco"));
                end.setNome_receptor(rs.getString("nome_receptor"));
                end.setCep(rs.getString("cep"));
                end.setNumero(rs.getInt("numero"));
                end.setRua(rs.getString("rua"));
                end.setComplemento(rs.getString("complemento"));
                end.setBairro(rs.getString("bairro"));
                end.setCidade(rs.getString("cidade"));
                end.setEstado(rs.getString("estado"));
                end.setTelefone(rs.getString("telefone"));
                end.setReferencia_entrega(rs.getString("referencia_entrega"));
                end.setFatura(rs.getBoolean("fatura"));
                end.setId_cliente(rs.getInt("id_cliente"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return end;
    }

}

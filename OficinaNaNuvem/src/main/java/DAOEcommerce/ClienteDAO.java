package DAOEcommerce;

import Infrastructure.DBConnection;
import com.thinkcode.models.ClienteModel;
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
public class ClienteDAO extends DBConnection {

    public static boolean cadastrarCliente(ClienteModel usuario) {

        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = "insert into tb_cliente (cpf_cnpj, nome, email, senha, telefone, sexo, data_nascimento, rg)"
                    + " values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getCpf_cnpj());
            ps.setString(2, usuario.getNome());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getSenha());
            ps.setString(5, usuario.getTelefone());
            ps.setString(6, usuario.getSexo());
            ps.setString(7, usuario.getData_nascimento());
            ps.setString(8, usuario.getRg());

            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            String guts = ex.toString();
            System.out.println(ex);
        }
        return ok;
    }
   public static ClienteModel consultarClienteByCPF(String cpf) {
        ClienteModel usuario = new ClienteModel();
            Connection con;
        
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_cliente where data_exclusao is null and cpf="+cpf;

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                usuario.setData_nascimento(rs.getString("data_nascimento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setId_cliente(rs.getInt("id_cliente"));
                usuario.setNome(rs.getString("nome"));                
                usuario.setRg(rs.getString("rg"));                
                usuario.setSenha(rs.getString("senha"));
                usuario.setSexo(rs.getString("sexo"));                
                usuario.setTelefone(rs.getString("telefone"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
        public static ClienteModel consultarClienteById(int id) {
        ClienteModel usuario = new ClienteModel();
            Connection con;
        
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_cliente where data_exclusao is null and id_cliente="+id;

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                usuario.setCpf_cnpj(rs.getString("cpf_cnpj"));
                usuario.setData_nascimento(rs.getString("data_nascimento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setId_cliente(rs.getInt("id_cliente"));
                usuario.setNome(rs.getString("nome"));                
                usuario.setRg(rs.getString("rg"));                
                usuario.setSenha(rs.getString("senha"));
                usuario.setSexo(rs.getString("sexo"));                
                usuario.setTelefone(rs.getString("telefone"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }

    public static boolean atualizarCliente(ClienteModel usuario) {

        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = "UPDATE tb_cliente SET nome = ?,telefone = ?,rg = ?, sexo= ?, data_nascimento = ? WHERE id_cliente = "+usuario.getId_cliente();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNome());
            ps.setString(2, usuario.getTelefone());
            ps.setString(3, usuario.getRg());
            ps.setString(4, usuario.getSexo());
            ps.setString(5, usuario.getData_nascimento());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            String guts = ex.toString();
            System.out.println(ex);
        }
        return ok;
    }

    public static boolean deletarCliente(ClienteModel usuario) {

        boolean ok = false;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = "insert into tb_usuario (id_perfil, id_filial, cpf_cnpj, rg, nome, email, senha, telefone, sexo, empresa, data_nascimento, data_inclusao, usr_inclusao)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
//            ps.setInt(1, usuario.getIdPerfil());
//            ps.setInt(2, usuario.getIdFilial());
//            ps.setString(3, usuario.getCpfCnpj());
//            ps.setString(4, usuario.getRg());
//            ps.setString(5, usuario.getNome());
//            ps.setString(6, usuario.getEmail());
//            if (usuario.getIdPerfil() == 3) {
//                ps.setString(7, "0");
//            } else {
//                ps.setString(7, usuario.getSenha());
//            }
//            ps.setLong(8, usuario.getTelefone());
//            ps.setString(9, usuario.getSexo());
//            ps.setInt(10, 1);

//            ps.setString(11, converteData(usuario.getDataNasc()));
//            String datinha = usuario.getDataNasc();
//            ps.setString(12, usuario.getDataInclusao());
//            ps.setInt(13, usuario.getUserInclusao());
            ps.execute();
            ok = true;
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            String guts = ex.toString();
            System.out.println(ex);
        }
        return ok;
    }

    public static List<ClienteModel> clientesCadastrados(ClienteModel usuario) {

        Connection con;
        List<ClienteModel> usuarios = new ArrayList<ClienteModel>();

        try {
            String sqlState = "select * from tb_usuario as us"
                    + " left join tb_filial as fi on us.id_filial = fi.id_filial"
                    + " left join tb_perfil as pe on us.id_perfil = pe.id_perfil"
                    + " where us.data_exclusao is null";
//            if (filtroFilial != null && !filtroFilial.equals("")) {
//                sqlState += " and us.id_filial = " + filtroFilial;
//            }
//            if (filtroPerfil != null && !filtroPerfil.equals("")) {
//                sqlState += " and us.id_perfil = " + filtroPerfil;
//            }
//            if (nomePerfil != null && !nomePerfil.equals("")) {
//                sqlState += " and pe.tipo like '%" + nomePerfil + "%'";
//            }
            con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ClienteModel clientes = new ClienteModel();
//                usuario.setCpfCnpj(rs.getString("cpf_cnpj"));
//                usuario.setDataExclusao(rs.getString("data_exclusao"));
//                usuario.setDataInclusao(rs.getString("data_inclusao"));
//                usuario.setDataNasc(rs.getDate("data_nascimento").toString());
//                usuario.setEmail(rs.getString("email"));
//                usuario.setEmpresa(rs.getInt("empresa"));
//                usuario.setId(rs.getInt("id_usuario"));
//                usuario.setIdFilial(rs.getInt("id_filial"));
//                usuario.setIdPerfil(rs.getInt("id_perfil"));
//                usuario.setNome(rs.getString("nome"));
//                usuario.setRg(rs.getString("rg"));
//                usuario.setSenha(rs.getString("senha"));
//                usuario.setSexo(rs.getString("sexo"));
//                usuario.setTelefone(rs.getLong("telefone"));
//                usuario.setUserExclusao(rs.getInt("usr_exclusao"));
//                usuario.setUserInclusao(rs.getInt("usr_inclusao"));
//                usuario.setNomeFilial(rs.getString("fi.Nome"));
//                usuario.setNomePerfil(rs.getString("pe.tipo"));
                usuarios.add(clientes);
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuarios;
    }

    public static boolean consultarCliente(ClienteModel usuario) {
        Connection con;
        boolean _usuarioExistente = false;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_cliente where data_exclusao is null ";
            if (usuario.getId_cliente() != 0) {
                sqlState += " and id_cliente = '" + usuario.getId_cliente() + "'";
            }
            if (usuario.getEmail() != null) {
                sqlState += " and email = '" + usuario.getEmail() + "' ";
            }
            if (usuario.getSenha() != null) {
                sqlState += " and senha = '" + usuario.getSenha() + "' ";
            }

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                _usuarioExistente = true;
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _usuarioExistente;
    }
    public static boolean consultarClienteCpfEmail(String email , String cpf) {
        Connection con;
        boolean _usuarioExistente = false;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_cliente where data_exclusao is null ";
            if (email != "") {
                sqlState += " and email = '" + email + "' ";
            }
            if (cpf != "") {
                sqlState += " or tb_cliente.cpf_cnpj = '" + cpf + "' ";
            }

            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                _usuarioExistente = true;
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _usuarioExistente;
    }

    public static ClienteModel consultarPropriedadesCliente(ClienteModel usuario) {
        Connection con;
        try {
            con = DBConnection.obterConexao();
            String sqlState = "select * from tb_cliente where data_exclusao is null ";
            if (usuario.getId_cliente() != 0) {
                sqlState += " and id_cliente = '" + usuario.getId_cliente() + "'";
            }
            if (usuario.getEmail() != null) {
                sqlState += " and email = '" + usuario.getEmail() + "' ";
            }
            if (usuario.getSenha() != null) {
                sqlState += " and senha = '" + usuario.getSenha() + "' ";
            }
            PreparedStatement ps = con.prepareStatement(sqlState,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            if (rs.first()) {
                usuario.setCpf_cnpj(rs.getString("cpf_cnpj"));
                usuario.setData_nascimento(rs.getString("data_nascimento"));
                usuario.setEmail(rs.getString("email"));
                usuario.setId_cliente(rs.getInt("id_cliente"));
                usuario.setNome(rs.getString("nome"));                
                usuario.setRg(rs.getString("rg"));                
                usuario.setSenha(rs.getString("senha"));
                usuario.setSexo(rs.getString("sexo"));                
                usuario.setTelefone(rs.getString("telefone"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}

package com.thinkcode.DAO;

import Infrastructure.DBConnection;
import com.thinkcode.models.RelatorioModel;
import com.thinkcode.models.TB_ORDEM_SERVICO;
import com.thinkcode.models.TB_ORDEM_SERVICO_MANUTENCAO;
import com.thinkcode.models.TB_ORDEM_SERVICO_PRODUTOS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderServicoDAO extends DBConnection {

    public static int cadastrarOrdemServico(TB_ORDEM_SERVICO _tbOrdemServico, TB_ORDEM_SERVICO_MANUTENCAO _tbOrdemServicoManutencao, TB_ORDEM_SERVICO_PRODUTOS _tbOrdemServicoProdutos) {

        int ok = 0;
        Connection con;
        try {
            con = DBConnection.obterConexao();

            String sql = "insert into tb_ordem_servico (id_cliente, ds_marca_carro, ds_modelo_carro, ds_placa_carro, valor_ordem, pagamento, id_usuario_inclusao, dt_inclusao)"
                    + " values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, _tbOrdemServico.getIdCliente());
            ps.setString(2, _tbOrdemServico.getDsMarcaCarro());
            ps.setString(3, _tbOrdemServico.getDsModeloCarro());
            ps.setString(4, _tbOrdemServico.getDsPlacaCarro());
            ps.setDouble(5, _tbOrdemServico.getValorOrdem());
            ps.setInt(6, _tbOrdemServico.getPAGAMENTO());
            ps.setInt(7, _tbOrdemServico.getIdUsuarioInclusao());
            ps.setString(8, converteData(_tbOrdemServico.getDtInclusao()));

            ps.execute();
            ok = 1;
            sql = "";
            sql = "SELECT ID_ORDEM_SERVICO FROM tb_ordem_servico WHERE ID_USUARIO_INCLUSAO = " + Integer.toString(_tbOrdemServico.getIdUsuarioInclusao()) + " AND DT_INCLUSAO = '" + _tbOrdemServico.getDtInclusao() + "' ORDER BY ID_ORDEM_SERVICO DESC LIMIT 1";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int id_Ordem_Servico = 0;
            while (rs.next()) {
                id_Ordem_Servico = Integer.parseInt(rs.getString("ID_ORDEM_SERVICO"));
            }

            if (id_Ordem_Servico > 0) {
                List<Integer> Lista_Id_Produtos = _tbOrdemServicoProdutos.getIdProduto();
                for (int i = 0; i < Lista_Id_Produtos.size(); i++) {
                    List<Integer> Lista_QTD_Produto = _tbOrdemServicoProdutos.getQtdProduto();
                    List<Double> Lista_Valor_Produto = _tbOrdemServicoProdutos.getValorTotal();
                    sql = "";
                    sql = "insert into tb_ordem_servico_produtos (id_ordem_servico, id_produto, qtd_produto, valor_total, id_usuario_inclusao, dt_inclusao)"
                            + " values (?,?,?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, id_Ordem_Servico);
                    ps.setInt(2, Lista_Id_Produtos.get(i));
                    ps.setInt(3, Lista_QTD_Produto.get(i));
                    ps.setDouble(4, Lista_Valor_Produto.get(i));
                    ps.setInt(5, _tbOrdemServicoProdutos.getIdUsuarioInclusao());
                    ps.setString(6, converteData(_tbOrdemServicoProdutos.getDtInclusao()));

                    ps.execute();
                    ok = 1;
                }
                sql = "";

                List<String> Lista_Manutencao_Servico = _tbOrdemServicoManutencao.get_Ds_Servico();
                for (int i = 0; i < Lista_Manutencao_Servico.size(); i++) {
                    List<Double> Lista_Valor_Manutencao = _tbOrdemServicoManutencao.getValor_Servico();
                    sql = "insert into tb_ordem_servico_manutencao (id_ordem_servico, ds_servico, valor_servico, id_usuario_inclusao, dt_inclusao)"
                            + " values (?,?,?,?,?)";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, id_Ordem_Servico);
                    ps.setString(2, Lista_Manutencao_Servico.get(i));
                    ps.setDouble(3, Lista_Valor_Manutencao.get(i));
                    ps.setInt(4, _tbOrdemServicoManutencao.getIdUsuarioInclusao());
                    ps.setString(5, converteData(_tbOrdemServicoManutencao.getDtInclusao()));

                    ps.execute();
                    sql = "";

                    ok = id_Ordem_Servico;
                }
            }
            ps.close();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(EnderecoDAO.class.getName()).log(Level.SEVERE, null, ex);
            String error = ex.toString();
            ok = 0;
        }

        return ok;
    }

    public static List<RelatorioModel> ListaOrdens() {
        List<RelatorioModel> _ListaOdens = new ArrayList<RelatorioModel>();

        String sql = "SELECT DISTINCT(OS.ID_ORDEM_SERVICO), FI.NOME AS NOME_FILIAL, USM.NOME AS NOME_MECANICO, US.NOME AS NOME_CLIENTE, US.CPF_CNPJ, OS.PAGAMENTO, OS.VALOR_ORDEM, OS.DT_INCLUSAO FROM tb_ordem_servico as OS\n"
                + "	INNER JOIN tb_usuario as US ON OS.ID_CLIENTE = US.ID_USUARIO \n"
                + "	INNER JOIN tb_usuario AS USM ON OS.ID_USUARIO_INCLUSAO = USM.ID_USUARIO\n"
                + "	INNER JOIN tb_filial AS FI ON USM.ID_FILIAL = FI.ID_FILIAL"
                + " WHERE OS.DT_EXCLUSAO IS NULL";
        try {
            Connection con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                RelatorioModel relatorio_ = new RelatorioModel();
                relatorio_.setIdOrdemServioc(rs.getInt("ID_ORDEM_SERVICO"));
                relatorio_.setfilialNome(rs.getString("NOME_FILIAL"));
                relatorio_.setusuarioNome(rs.getString("NOME_MECANICO"));
                relatorio_.setnomeCliente(rs.getString("NOME_CLIENTE"));
                relatorio_.setcpfCnpj(rs.getString("CPF_CNPJ"));
                relatorio_.setidPagamento(rs.getInt("PAGAMENTO"));
                relatorio_.setValorOrdem(rs.getDouble("VALOR_ORDEM"));
                relatorio_.setdata(rs.getString("DT_INCLUSAO"));

                _ListaOdens.add(relatorio_);

            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _ListaOdens;
    }

    public static RelatorioModel consultarOrdem(TB_ORDEM_SERVICO _tbOrdeServico) {

        RelatorioModel relatorio = new RelatorioModel();

        String sql = "SELECT DISTINCT OSM.ID_ORDEM_SERVICO\n"
                + "	,US.NOME AS NOME_MECANICO\n"
                + "	,USC.NOME AS NOME_CLIENTE\n"
                + "	,USC.TELEFONE	\n"
                + "	,OS.ID_ORDEM_SERVICO\n"
                + "	,OS.VALOR_ORDEM\n"
                + "	,OS.DS_MARCA_CARRO\n"
                + "	,OS.DS_MODELO_CARRO\n"
                + "	,OS.DS_PLACA_CARRO\n"
                + "	,OSM.DS_SERVICO\n"
                + "	,OSM.VALOR_SERVICO\n"
                + "	,P.NOME AS NOME_PRODUTO\n"
                + "	,P.VALOR AS VALOR_UNITARIO\n"
                + "	,OSP.QTD_PRODUTO\n"
                + "	,OSP.VALOR_TOTAL AS VALOR_PRODUTOS\n"
                + "	 FROM tb_ordem_servico AS OS\n"
                + "		INNER JOIN tb_usuario AS US ON US.ID_USUARIO = OS.ID_USUARIO_INCLUSAO\n"
                + "		INNER JOIN tb_usuario AS USC ON USC.ID_USUARIO = OS.ID_CLIENTE\n"
                + "		INNER JOIN tb_ordem_servico_produtos AS OSP ON OS.ID_ORDEM_SERVICO = OSP.ID_ORDEM_SERVICO\n"
                + "		INNER JOIN tb_ordem_servico_manutencao AS OSM ON OS.ID_ORDEM_SERVICO = OSM.ID_ORDEM_SERVICO		\n"
                + "		INNER JOIN tb_produto AS P ON OSP.ID_PRODUTO = P.ID_PRODUTO\n"
                + "WHERE OS.ID_ORDEM_SERVICO = " + _tbOrdeServico.getIdOrderServico();

        try {
            Connection con = DBConnection.obterConexao();
            PreparedStatement ps = con.prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                relatorio.setIdOrdemServioc(rs.getInt("ID_ORDEM_SERVICO"));
                relatorio.setusuarioNome(rs.getString("NOME_MECANICO"));
                relatorio.setnomeCliente(rs.getString("NOME_CLIENTE"));
                relatorio.setTelefone(rs.getString("TELEFONE"));
                relatorio.setdsMarcaCarro(rs.getString("DS_MARCA_CARRO"));
                relatorio.setdsModeloCarro(rs.getString("DS_MODELO_CARRO"));
                relatorio.setdsModeloPlaca(rs.getString("DS_PLACA_CARRO"));
                relatorio.setDsServico(rs.getString("DS_SERVICO"));
                relatorio.setValorServico(rs.getDouble("VALOR_SERVICO"));
                relatorio.setNomeProdutos(rs.getString("NOME_PRODUTO"));
                relatorio.setQtdProdutos(rs.getInt("QTD_PRODUTO"));
                relatorio.setdsValorProdutos(rs.getDouble("VALOR_PRODUTOS"));
                relatorio.setValorOrdem(rs.getDouble("VALOR_ORDEM"));
                relatorio.setValorUnitarioProduto(rs.getDouble("VALOR_UNITARIO"));
            }
            ps.close();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return relatorio;
    }

    public static String converteData(String data) {

        try {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            Date dataNascimento = sdf1.parse(data);
            data = dataNascimento.toInstant().toString().substring(0, 10);
            return data;
        } catch (ParseException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
}

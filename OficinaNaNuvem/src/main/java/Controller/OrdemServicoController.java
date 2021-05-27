package Controller;

import com.thinkcode.DAO.OrderServicoDAO;
import com.thinkcode.models.RelatorioModel;
import com.thinkcode.models.TB_ORDEM_SERVICO;
import com.thinkcode.models.TB_ORDEM_SERVICO_MANUTENCAO;
import com.thinkcode.models.TB_ORDEM_SERVICO_PRODUTOS;
import java.util.List;

public class OrdemServicoController {

    public int save(TB_ORDEM_SERVICO _tbOrdemServico, TB_ORDEM_SERVICO_MANUTENCAO _tbOrdemServicoManutencao, TB_ORDEM_SERVICO_PRODUTOS _tbOrdemServicoProdutos) {
        return OrderServicoDAO.cadastrarOrdemServico(_tbOrdemServico, _tbOrdemServicoManutencao, _tbOrdemServicoProdutos);
    }

    public List<RelatorioModel> OrdensCriadas() {
        return OrderServicoDAO.ListaOrdens();
    }

    public RelatorioModel consultarOrdem(TB_ORDEM_SERVICO _tbOrdemServico) {
        return OrderServicoDAO.consultarOrdem(_tbOrdemServico);
    }
}

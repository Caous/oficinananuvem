package com.thinkcode.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gustavo Nascimento
 */
public class RelatorioModel {

    private int idProduto;
    private int idUsuario; // Chave estrangeira
    private int idFilial; // Chave estrangeira
    private String nomeProduto;
    private String qtdProduto;
    private int Valor;
    private VendaModel venda;
    private int idVenda;
    private ProdutoModel produtoModel;
    private String filialNome;
    private String usuarioNome;
    private String cpfCnpj;
    private int pagamento;
    private int parcelas;
    private Double total;
    private String data;
    private String NomeProduto;
    private int QuantidadeProduto;
    private Double ValorProduto;
    private int idVendedor;
    private String CpfCliente;
    private int idPagamento;
    private String formaPagamento;
    private String nomeCliente;
    private String descricao;
    private String tipoProduto;
    private int idOrdemServico;
    private Double valorOrdem;
    private String dsOrdemServico;
    private int idOrdemManutencao;
    private String telefone;
    private String dsMarcaCarro;
    private String dsModeloCarro;
    private String dsModeloPlaca;
    private List<String> dsServico;
    private List<Double> dsValor;
    private List<String> nomeProdutos;
    private List<Integer> QtdProdutos;
    private List<Double> dsValorProdutos;
    private List<Double> dsValorUnitarioProduto;
    private String dsStatus;
    private String dsStatusVenda;
    private String metodoPagamento;
    private boolean sistema;

    public RelatorioModel() {

    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    public int getIdOrdemServico() {
        return this.idOrdemServico;
    }

    public void setIdOrdemServioc(int _Ordem_Servico) {
        this.idOrdemServico = _Ordem_Servico;
    }

    public int getidOrdemManutencao() {
        return this.idOrdemManutencao;
    }

    public void setidOrdemManutencao(int _idOrdemManutencao) {
        this.idOrdemManutencao = _idOrdemManutencao;
    }

    public void setProduto(ProdutoModel _produto) {
        this.produtoModel = _produto;
    }

    public ProdutoModel getProduto() {
        return this.produtoModel;
    }

    public void setIdVenda(int _idVenda) {
        this.idVenda = _idVenda;
    }

    public int getIdVenda() {
        return this.idVenda;
    }

    public void setfilialNome(String filialNome) {
        this.filialNome = filialNome;
    }

    public String getfilialNome() {
        return this.filialNome;
    }

    public void setusuarioNome(String usuarioNome) {
        this.usuarioNome = usuarioNome;
    }

    public String getusuarioNome() {
        return this.usuarioNome;
    }

    public void setcpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getcpfCnpj() {
        return this.cpfCnpj;
    }

    public int getpagamento() {
        return this.pagamento;
    }

    public void setpagamento(int pagamento) {
        this.pagamento = pagamento;
    }

    public int getparcelas() {
        return this.parcelas;
    }

    public void setparcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public Double gettotal() {
        return this.total;
    }

    public void settotal(Double total) {
        this.total = total;
    }

    public void setdata(String data) {
        this.data = data;
    }

    public String getdata() {
        return this.data;
    }

    public void setNomeProduto(String _produto) {
        this.NomeProduto = _produto;
    }

    public String getNomeProduto() {
        return this.NomeProduto;
    }

    public Double getValorProduto() {
        return this.ValorProduto;
    }

    public void setValorProduto(Double valorProduto) {
        this.ValorProduto = valorProduto;
    }

    public int getQuantidadeProduto() {
        return this.QuantidadeProduto;
    }

    public void setQuantidadeProduto(int qtd) {
        this.QuantidadeProduto = qtd;
    }

    public int getidVendedor() {
        return this.idVendedor;
    }

    public void setidVendedor(int _idVendedor) {
        this.idVendedor = _idVendedor;
    }

    public String getCpfCliente() {
        return this.CpfCliente;
    }

    public void setCpfCliente(String CpfCliente) {
        this.CpfCliente = CpfCliente;
    }

    public int getidPagamento() {
        return this.idPagamento;
    }

    public void setidPagamento(int _idPagamento) {
        this.idPagamento = _idPagamento;
    }

    public String getformaPagamento() {
        return this.formaPagamento;
    }

    public void setformaPagamento(String _formaPagamento) {
        this.formaPagamento = _formaPagamento;
    }

    public String getnomeCliente() {
        return this.nomeCliente;
    }

    public void setnomeCliente(String _nomeCliente) {
        this.nomeCliente = _nomeCliente;
    }

    public String getdescricao() {
        return this.descricao;
    }

    public void setdescricao(String _descricao) {
        this.descricao = _descricao;
    }

    public String gettipoProduto() {
        return this.tipoProduto;
    }

    public void settipoProduto(String _tipoProduto) {
        this.tipoProduto = _tipoProduto;
    }

    public void setValorOrdem(Double _ValorOrdem) {
        this.valorOrdem = _ValorOrdem;
    }

    public Double getValorOrdem() {
        return this.valorOrdem;
    }

    public String getdsOrdemServico() {
        return this.dsOrdemServico;
    }

    public void setdsOrdemServico(String _dsOrdemServico) {
        this.dsOrdemServico = _dsOrdemServico;
    }

    public String getTelefone() {
        return this.telefone;
    }

    public void setTelefone(String _telefone) {
        this.telefone = _telefone;
    }

    public String getdsMarcaCarro() {
        return this.dsMarcaCarro;
    }

    public void setdsMarcaCarro(String _dsMarcaCarro) {
        this.dsMarcaCarro = _dsMarcaCarro;
    }

    public String getdsModeloCarro() {
        return this.dsModeloCarro;
    }

    public void setdsModeloCarro(String _dsModeloCarro) {
        this.dsModeloCarro = _dsModeloCarro;
    }

    public String getdsModeloPlaca() {
        return this.dsModeloPlaca;
    }

    public void setdsModeloPlaca(String _dsModeloPlaca) {
        this.dsModeloPlaca = _dsModeloPlaca;
    }

    public List<String> getDsServico() {
        return this.dsServico;
    }

    public void setDsServico(String _dsServico) {
        if (this.dsServico == null) {
            this.dsServico = new ArrayList<String>();
            this.dsServico.add(_dsServico);
        } else {
            this.dsServico.add(_dsServico);
        }
    }

    public List<Double> getValorServico() {
        return this.dsValor;
    }

    public void setValorServico(Double _dsValor) {
        if (this.dsValor == null) {
            this.dsValor = new ArrayList<Double>();
            this.dsValor.add(_dsValor);
        } else {
            this.dsValor.add(_dsValor);
        }
    }

    public List<String> getNomeProdutos() {
        return this.nomeProdutos;
    }

    public void setNomeProdutos(String NomeProdutos) {
        if (this.nomeProdutos == null) {
            this.nomeProdutos = new ArrayList<String>();
            this.nomeProdutos.add(NomeProdutos);
        } else {
            this.nomeProdutos.add(NomeProdutos);
        }
    }

    public List<Integer> getQtdProdutos() {
        return this.QtdProdutos;
    }

    public void setQtdProdutos(int _QtdProdutos) {
        if (this.QtdProdutos == null) {
            this.QtdProdutos = new ArrayList<Integer>();
            this.QtdProdutos.add(_QtdProdutos);
        } else {
            this.QtdProdutos.add(_QtdProdutos);
        }
    }

    public List<Double> getdsValorProdutos() {
        return this.dsValorProdutos;
    }

    public void setdsValorProdutos(Double _dsValorProdutos) {
        if (this.dsValorProdutos == null) {
            this.dsValorProdutos = new ArrayList<Double>();
            this.dsValorProdutos.add(_dsValorProdutos);
        } else {
            this.dsValorProdutos.add(_dsValorProdutos);
        }
    }

    public void setValorUnitarioProduto(Double _dsValorUnitario) {
        if (this.dsValorUnitarioProduto == null) {
            this.dsValorUnitarioProduto = new ArrayList<Double>();
            this.dsValorUnitarioProduto.add(_dsValorUnitario);
        } else {
            this.dsValorUnitarioProduto.add(_dsValorUnitario);
        }
    }

    public List<Double> getValorUnitarioProduto() {
        return this.dsValorUnitarioProduto;
    }

    public void setdsStatusVenda(String _dsStatusVenda) {
        this.dsStatusVenda = _dsStatusVenda;
    }

    public String getdsStatusVenda() {
        return this.dsStatusVenda;
    }

    public void setdsStatus(String _dsStatus) {
        this.dsStatus = _dsStatus;
    }

    public String getdsStatus() {
        return this.dsStatus;
    }

    public void setmetodoPagamento(String _metodoPagamento) {
        this.metodoPagamento = _metodoPagamento;
    }

    public String getmetodoPagamento() {
        return this.metodoPagamento;
    }

    public void setsistema(boolean _sistema) {
        this.sistema = _sistema;
    }

    public boolean getsistema() {
        return this.sistema;
    }
}

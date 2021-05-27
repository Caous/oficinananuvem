package com.thinkcode.models;

import java.util.ArrayList;
import java.util.List;

public class TB_ORDEM_SERVICO_PRODUTOS {

    private int Id_Ordem_Servico_Produtos;
    private int Id_Order_Servico;
    private List<Double> Valor_Total;
    private List<Integer> Id_Produto;
    private List<Integer> Qtd_Produto;
    private int Id_Usuario_Inclusao;
    private String Dt_Inclusao;
    private int Id_Usuario_Exclusao;
    private String Dt_Exclusao;

    public int getIdOrderServico() {
        return Id_Order_Servico;
    }

    public void setIdOrderServico(int _Id_Order_Servico) {
        this.Id_Order_Servico = _Id_Order_Servico;
    }

    public int getIdOrdemServicoProdutos() {
        return Id_Ordem_Servico_Produtos;
    }

    public void setIdOrdemServicoProdutos(int _Id_Ordem_Servico_Produtos) {
        this.Id_Ordem_Servico_Produtos = _Id_Ordem_Servico_Produtos;
    }

    public List<Double> getValorTotal() {
        return Valor_Total;
    }

    public void setValorTotal(Double _Valor_Total) {
        if (this.Valor_Total != null) {
            this.Valor_Total.add(_Valor_Total);
        } else {
            this.Valor_Total = new ArrayList<Double>();
            this.Valor_Total.add(_Valor_Total);
        }

    }

    public List<Integer> getIdProduto() {
        return Id_Produto;
    }

    public void setIdProduto(int _Id_Produto) {
        if (this.Id_Produto != null) {
            this.Id_Produto.add(_Id_Produto);
        } else {
            this.Id_Produto = new ArrayList<Integer>();
            this.Id_Produto.add(_Id_Produto);
        }

    }

    public List<Integer> getQtdProduto() {
        return Qtd_Produto;
    }

    public void setQtdProduto(int _Qtd_Produto) {
        if (this.Qtd_Produto != null) {
            this.Qtd_Produto.add(_Qtd_Produto);
        } else {
            this.Qtd_Produto = new ArrayList<Integer>();
            this.Qtd_Produto.add(_Qtd_Produto);
        }
    }

    public int getIdUsuarioInclusao() {
        return Id_Usuario_Inclusao;
    }

    public void setIdUsuarioInclusao(int _Id_Usuario_Inclusao) {
        this.Id_Usuario_Inclusao = _Id_Usuario_Inclusao;
    }

    public String getDtInclusao() {
        return Dt_Inclusao;
    }

    public void setDtInclusao(String _DT_INCLUSAO) {
        this.Dt_Inclusao = _DT_INCLUSAO;
    }

    public int getIdUsuarioExclusao() {
        return Id_Usuario_Exclusao;
    }

    public void setIdUsuarioExclusao(int _ID_USUARIO_EXCLUSAO) {
        this.Id_Usuario_Exclusao = _ID_USUARIO_EXCLUSAO;
    }

    public String getDtExclusao() {
        return Dt_Exclusao;
    }

    public void setDtExclusao(String _DT_EXCLUSAO) {
        this.Dt_Exclusao = _DT_EXCLUSAO;
    }
}

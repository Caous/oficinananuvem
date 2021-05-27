package com.thinkcode.models;

import java.util.ArrayList;
import java.util.List;

public class TB_ORDEM_SERVICO_MANUTENCAO {

    private int Id_Ordem_Servico_Manutencao;
    private int Id_Order_Servico;
    private List<Double> Valor_Servico;
    private List<String> Ds_Servico;
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

    public int getIdOrdemServicoManutencao() {
        return Id_Ordem_Servico_Manutencao;
    }

    public void setIdOrdemServicoManutencao(int _Id_Ordem_Servico_Manutencao) {
        this.Id_Ordem_Servico_Manutencao = _Id_Ordem_Servico_Manutencao;
    }

    public List<Double> getValor_Servico() {
        return Valor_Servico;
    }

    public void setValor_Servico(Double _Valor_Servico) {
        if (this.Valor_Servico != null) {
            this.Valor_Servico.add(_Valor_Servico);
        } else {

            this.Valor_Servico = new ArrayList<Double>();
            this.Valor_Servico.add(_Valor_Servico);
        }

    }

    public void set_Ds_Servico(String _Ds_Servico) {
        if (this.Ds_Servico != null) {
            this.Ds_Servico.add(_Ds_Servico);
        } else {
            this.Ds_Servico = new ArrayList<String>();
            this.Ds_Servico.add(_Ds_Servico);
        }

    }

    public List<String> get_Ds_Servico() {
        return Ds_Servico;
    }

    public void setIdUsuarioInclusao(int _Id_Usuario_Inclusao) {
        this.Id_Usuario_Inclusao = _Id_Usuario_Inclusao;
    }

    public int getIdUsuarioInclusao() {
        return this.Id_Usuario_Inclusao;
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

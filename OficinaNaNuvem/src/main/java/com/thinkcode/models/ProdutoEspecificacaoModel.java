/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.models;

/**
 *
 * @author Leonardo Moreno
 */
public class ProdutoEspecificacaoModel {

    private int idEspecificacao;
    private int idProduto;
    private String resposta;
    private String especificacao;
    private int Aux;
    private int pontos;

    public ProdutoEspecificacaoModel() {
    }

    public ProdutoEspecificacaoModel(int id_especificacao, int id_produto, String resposta, String especificacao) {
        this.idEspecificacao = id_especificacao;
        this.idProduto = id_produto;
        this.resposta = resposta;
        this.especificacao = especificacao;
    }

    public int getIdEspecificacao() {
        return idEspecificacao;
    }

    public void setIdEspecificacao(int idEspecificacao) {
        this.idEspecificacao = idEspecificacao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getEspecificacao() {
        return especificacao;
    }

    public void setEspecificacao(String especificacao) {
        this.especificacao = especificacao;
    }

    public void setAux(int _aux) {
        this.Aux = _aux;
    }

}

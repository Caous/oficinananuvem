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
public class ProdutoPerguntaModel {

    private int idPergunta;
    private int idProduto;
    private String resposta;
    private String pergunta;
    private int pontos;

    public ProdutoPerguntaModel() {
    }

    public ProdutoPerguntaModel(int id_pergunta, int id_produto, String resposta, String pergunta, int pontos) {
        this.idPergunta = id_pergunta;
        this.idProduto = id_produto;
        this.resposta = resposta;
        this.pergunta = pergunta;
        this.pontos = pontos;
    }

    public int getIdPergunta() {
        return idPergunta;
    }

    public void setIdPergunta(int idPergunta) {
        this.idPergunta = idPergunta;
    }

    public int getId_produto() {
        return idProduto;
    }

    public void setId_produto(int id_produto) {
        this.idProduto = id_produto;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

}

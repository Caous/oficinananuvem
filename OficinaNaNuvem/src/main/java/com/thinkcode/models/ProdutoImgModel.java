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
public class ProdutoImgModel {

    private int id_img;
    private int id_produto;
    private String nome;
    private String descricao;
     private boolean home;

    public ProdutoImgModel() {
    }

    public ProdutoImgModel(int id_img, int id_produto, String nome, String descricao,boolean home) {
        this.id_img = id_img;
        this.id_produto = id_produto;
        this.nome = nome;
        this.descricao = descricao;
        this.home = home;
    }

    public int getIdProdutoImg() {
        return id_img;
    }

    public void setIdProdutoImg(int id_img) {
        this.id_img = id_img;
    }

    public int getIdProduto() {
        return id_produto;
    }

    public void setIdProduto(int id_produto) {
        this.id_produto = id_produto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean getHome() {
        return home;
    }

    public void setHome(boolean home) {
        this.home = home;
    }
}

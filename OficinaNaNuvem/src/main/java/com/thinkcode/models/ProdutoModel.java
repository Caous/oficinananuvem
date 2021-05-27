/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.models;

import java.util.List;

/**
 *
 * @author Leonardo Silva
 */
public class ProdutoModel {

    private int idProduto;
    private int idUsuario; // Chave estrangeira
    private int idFilial; // Chave estrangeira
    private String tipo;
    private String nome;
    private int quantidade;
    private int quantidadeCarrinho;
    private String descricao;
    private double valor;
    private double total;
    private String dataExclusao;
    private int userExclusao;
    private int idCategoria;
    private String categoria;
    private String filial;
    private ProdutoImgModel img;
    private List<ProdutoImgModel> _imgs;

    public ProdutoModel() {

    }

    public ProdutoModel(int idUsuario, int idFilial, String tipo, String nome, int quantidade, String descricao, double valor) {
        this.idUsuario = idUsuario;
        this.idFilial = idFilial;
        this.tipo = tipo;
        this.nome = nome;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int _idCategoria) {
        this.idCategoria = _idCategoria;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeCarrinho() {
        return quantidadeCarrinho;
    }

    public void setQuantidadeCarrinho(int _quantidadeCarrinho) {
        this.quantidadeCarrinho = _quantidadeCarrinho;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double _total) {
        this.total = _total;
    }

    public String getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(String dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public int getUserExclusao() {
        return userExclusao;
    }

    public void setUserExclusao(int userExclusao) {
        this.userExclusao = userExclusao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String _categoria) {
        this.categoria = _categoria;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String _filial) {
        this.filial = _filial;
    }

    public void setImgCapa(String _img) {
        if (this.img == null) {
            this.img = new ProdutoImgModel();
            this.img.setNome(_img);
        } else {
            this.img.setNome(_img);
        }

    }

    public String getImgCapa() {
        return this.img.getNome();
    }

    public static String insertInto() {
        return " INSERT INTO tb_produto (id_usuario, id_filial, tipo, nome, qtde, descricao, valor, user_alteracao, id_categoria, data_alteracao, avaliacao) values (?, ?, ?, ?, ?, ?, ?);";
    }

    public static String update() {
        return " update tb_produto set id_usuario = ?, id_filial = ? , tipo = ? ,nome = ? ,qtde = ? ,descricao= ? ,valor = ?, user_alteracao = ?, id_categoria= ? ,data_alteracao= ?, avaliacao= ?    where id_produto = ?";
    }

    public static String delete() {
        return "update tb_produto set data_exclusao = ?, usr_exclusao = ? where id_produto = ?";
    }

    public static String listAllInto() {
        return " SELECT *, FI.nome as FILIAl, CG.nome as CATEGORIA  FROM tb_produto as PO inner join tb_categoria as CG on PO.id_categoria = CG.id_categoria inner join tb_filial as FI on PO.id_filial = FI.id_filial WHERE PO.data_exclusao is null";
    }
}

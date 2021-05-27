/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.models;

import java.util.List;

/**
 *
 * @author Leonardo Moreno
 */
public class VendaModel {

    private int idVenda;
    private int idStatus; // Chave Estrangeira 
    private int idEndereco; // Chave Estrangeira
    private int idUsuario; // Chave Estrangeira
    private int idCliente; // Chave Estrangeira
    private int idMetodo; // Chave Estrangeira
    private int idEndFatura; // Chave Estrangeira
    private int idFilial; // Chave Estrangeira
    private String cpfCnpj;
    private int pagamento;
    private int parcelas;
    private double total;
    private double frete;
    private String data;
    private String dataExclusao;
    private String userExclusao;
    private String codRastreio;
    // campos do eccomerce
    private boolean eccomerce;
    private String identificador;
    private String status;
    private String metodo;
    private String endentrega;
    private String endfatura;
    private List<ItensVenda> listaItens;

    public VendaModel(int idVenda, int idStatus, int idEndereco, int idUsuario, int idFilial, String cpfCnpj, int pagamento, int parcelas, double total, String data,
            int idMetodo, int idCliente, boolean eccomerce, double frete, String identificador, String status, String metodo, String endentrega, String endfatura, List<ItensVenda> listaItens) {
        this.idVenda = idVenda;
        this.idStatus = idStatus;
        this.idEndereco = idEndereco;
        this.idUsuario = idUsuario;
        this.idFilial = idFilial;
        this.cpfCnpj = cpfCnpj;
        this.pagamento = pagamento;
        this.parcelas = parcelas;
        this.total = total;
        this.data = data;
        this.idMetodo = idMetodo;
        this.idCliente = idCliente;
        this.eccomerce = eccomerce;
        this.frete = frete;
        this.identificador = identificador;
        this.status = status;
        this.metodo = metodo;
        this.endentrega = endentrega;
        this.endfatura = endfatura;
        this.listaItens = listaItens;
    }

    public VendaModel() {

    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int _idCliente) {
        this.idCliente = _idCliente;
    }

    public int getIdMetodo() {
        return idMetodo;
    }

    public void setIdMetodo(int _IdMetodo) {
        this.idMetodo = _IdMetodo;
    }

    public int getIdEndFatura() {
        return idEndFatura;
    }

    public void setIdEndFatura(int _IdEndFatura) {
        this.idEndFatura = _IdEndFatura;
    }

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public int getPagamento() {
        return pagamento;
    }

    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double _frete) {
        this.frete = _frete;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(String dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public String getUserExclusao() {
        return userExclusao;
    }

    public void setUserExclusao(String userExclusao) {
        this.userExclusao = userExclusao;
    }

    public String getCodRastreio() {
        return codRastreio;
    }

    public void setCodRastreio(String codRastreio) {
        this.codRastreio = codRastreio;
    }

    public boolean getEccomerce() {
        return isEccomerce();
    }

    public void setEccomerce(boolean eccomerce) {
        this.eccomerce = eccomerce;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getEndentrega() {
        return endentrega;
    }

    public void setEndentrega(String endentrega) {
        this.endentrega = endentrega;
    }

    public String getEndfatura() {
        return endfatura;
    }

    public void setEndfatura(String endfatura) {
        this.endfatura = endfatura;
    }

    public boolean isEccomerce() {
        return eccomerce;
    }

    public List<ItensVenda> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<ItensVenda> listaItens) {
        this.listaItens = listaItens;
    }

}

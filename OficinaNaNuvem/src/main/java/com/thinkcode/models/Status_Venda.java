/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.models;

/**
 *
 * @author Leronardo Moreno
 */
public class Status_Venda {

    private int id_status;
    private String status;
    private String descricao;
    private String dataInclusao;
    private int usrInclusao;
    private String dataExclusao;
    private int usrExclusao;
    private String usrDsUsuarioInclusao;
    private String usrDsUsuarioExclusao;

    public Status_Venda(int id_status, String status, String descricao, String dataInclusao,
            int usrInclusao, String dataExclusao, int usrExclusao) {
        this.id_status = id_status;
        this.status = status;
        this.descricao = descricao;
        this.dataInclusao = dataInclusao;
        this.usrInclusao = usrInclusao;
        this.dataExclusao = dataExclusao;
        this.usrExclusao = usrExclusao;
    }

    public Status_Venda() {
    }

    public int getId_status() {
        return id_status;
    }

    public void setId_status(int _id_status) {
        this.id_status = _id_status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String _status) {
        this.status = _status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String _descricao) {
        this.descricao = _descricao;
    }

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String _dataInclusao) {
        this.dataInclusao = _dataInclusao;
    }

    public int getUsrInclusao() {
        return usrInclusao;
    }

    public void setUsrInclusao(int _usrInclusao) {
        this.usrInclusao = _usrInclusao;
    }

    public String getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(String _dataExclusao) {
        this.dataExclusao = _dataExclusao;
    }

    public int getUsrExclusao() {
        return usrExclusao;
    }

    public void setUsrExclusao(int _usrExclusao) {
        this.usrExclusao = _usrExclusao;
    }

    public void setUsrDsUsuarioInclusao(String _usr) {
        this.usrDsUsuarioInclusao = _usr;
    }

    public String getUsrDsUsuarioInclusao() {
        return this.usrDsUsuarioInclusao;
    }

    public void setUsrDsUsuarioExclusao(String _usr) {
        this.usrDsUsuarioExclusao = _usr;
    }

    public String getUsrDsUsuarioExclusao() {
        return this.usrDsUsuarioExclusao;
    }

    public static String insertInto() {
        return "insert into tb_status_venda (status, descricao, data_inclusao, usr_inclusao) values (?,?,?,?)";
    }

    public static String update() {
        return "UPDATE tb_status_venda SET status = ?, descricao = ?, usr_inclusao = ?, data_inclusao = ? WHERE id_status = ?";
    }

    public static String delete() {
        return "UPDATE tb_status_venda SET data_exclusao = ?, usr_exclusao = ? WHERE id_status = ?";
    }

    public static String listAllInto() {
        return "SELECT SV.*, US.nome FROM `tb_status_venda` as SV INNER JOIN tb_usuario as US on SV.usr_inclusao = US.id_usuario WHERE SV.data_exclusao is null";
    }
}

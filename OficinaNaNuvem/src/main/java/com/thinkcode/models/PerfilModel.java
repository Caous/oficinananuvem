/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thinkcode.models;

/**
 *
 * @author Devakian
 */
public class PerfilModel {

    private int idPerfil;
    private String tipo;
    private String descricao;
    private String dataInclusao;
    private int usrInclusao;
    private String dataExclusao;
    private int usrExclusao;

    public PerfilModel() {
    }

    public PerfilModel(int idPerfil, String tipo, String descricao) {
        this.idPerfil = idPerfil;
        this.tipo = tipo;
        this.descricao = descricao;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public int getUsrInclusao() {
        return usrInclusao;
    }

    public void setUsrInclusao(int usrInclusao) {
        this.usrInclusao = usrInclusao;
    }

    public String getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(String dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public int getUsrExclusao() {
        return usrExclusao;
    }

    public void setUsrExclusao(int usrExclusao) {
        this.usrExclusao = usrExclusao;
    }
    public static String insertInto() {
        return " insert into tb_perfil (id_perfil, tipo, descricao, data_inclusao,usr_inclusao,data_exclusao,usr_exclusao) values (?,?,?,?,?,?,?)";
    }

    public static String update() {
        return "update tb_perfil set tipo = ?, descricao = ? where id_perfil = ?";
    }

    public static String delete() {
        return "update tb_perfil set data_exclusao = ?, usr_exclusao = ? where id_perfil = ?";
    }

    public static String listAllInto() {
        return "select * from tb_perfil WHERE data_exclusao is null";
    }

}

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
public class PermissaoModel {

    private int idPermissao;
    private int idPerfil;
    private int idMenu;
    private int idUsuarioInclusao;
    private int idUsuarioExclusao;
    private String dtInclusao;
    private String dtExclusao;
    private String perfil;
    private String pagina;
    private String descUsuario;

    public PermissaoModel() {
    }

    public int getIdPermissao() {
        return idPermissao;
    }

    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public int getIdUsuarioInclusao() {
        return idUsuarioInclusao;
    }

    public void setIdUsuarioInclusao(int idUsuarioInclusao) {
        this.idUsuarioInclusao = idUsuarioInclusao;
    }

    public int getIdUsuarioExclusao() {
        return idUsuarioExclusao;
    }

    public void setIdUsuarioExclusao(int idUsuarioExclusao) {
        this.idUsuarioExclusao = idUsuarioExclusao;
    }

    public String getDtInclusao() {
        return dtInclusao;
    }

    public void setDtInclusao(String dtInclusao) {
        this.dtInclusao = dtInclusao;
    }

    public String getDtExclusao() {
        return dtExclusao;
    }

    public void setDtExclusao(String dtExclusao) {
        this.dtExclusao = dtExclusao;
    }

    public String getperfil() {
        return perfil;
    }

    public void setperfil(String _perfil) {
        this.perfil = _perfil;
    }

    public String getpagina() {
        return pagina;
    }

    public void setpagina(String _pagina) {
        this.pagina = _pagina;
    }

    public String getdescUsuario() {
        return descUsuario;
    }

    public void setdescUsuario(String _descUsuario) {
        this.descUsuario = _descUsuario;
    }

    public static String insertInto() {
        return "insert into tb_permissao(id_perfil , id_menu ,data_inclusao , usr_inclusao) values (?,?,?,?)";
    }

    public static String update() {
        return "update tb_perfil set tipo = ?, descricao = ? where id_perfil = ?";
    }

    public static String delete() {
        return "update tb_permissao set data_exclusao = ?, usr_exclusao = ? where id_permissao = ?";
    }

    public static String listAllInto() {
        return "select PE.* ,US.NOME ,MU.PAGINA ,PF.TIPO from tb_permissao as PE inner join tb_perfil as PF on PE.id_perfil = PF.id_perfil inner join tb_menu as MU on PE.id_menu = MU.id_menu inner join tb_usuario as US on PE.USR_INCLUSAO = US.ID_USUARIO	where PE.data_exclusao is null";
    }

    public static String property() {
        return "select * from tb_permissao where data_exclusao is null and id_perfil= ";
    }
}

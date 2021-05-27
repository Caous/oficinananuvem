package com.thinkcode.models;

public class TB_MENU {

    private int idMenu;
    private String pagina;
    private String descricao;
    private int idUsuarioInclusao;
    private int idUsuarioExclusao;
    private String dtInclusao;
    private String dtExclusao;
    private int tipoMenu;
    private String descUsuario;
    private String descTipoMenu;
    private String url;

    public TB_MENU() {

    }

    public void setIdMenu(int _idMenu) {

        this.idMenu = _idMenu;

    }

    public int getIdMenu() {
        return this.idMenu;
    }

    public void setPagina(String _Pagina) {
        this.pagina = _Pagina;
    }

    public String getPagina() {
        return this.pagina;
    }

    public void settipoMenu(int _tipoMenu) {
        this.tipoMenu = _tipoMenu;
    }

    public int gettipoMenu() {
        return this.tipoMenu;
    }

    public void setDescricao(String _Descricao) {
        this.descricao = _Descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setidUsuarioInclusao(int _idUsuarioInclusao) {
        this.idUsuarioInclusao = _idUsuarioInclusao;
    }

    public int getidUsuarioInclusao() {
        return this.idUsuarioInclusao;
    }

    public void setidUsuarioExclusao(int _idUsuarioExclusao) {
        this.idUsuarioExclusao = _idUsuarioExclusao;
    }

    public int getidUsuarioExclusao() {
        return this.idUsuarioExclusao;
    }

    public String getdtInclusao() {
        return this.dtInclusao;
    }

    public void setdtInclusao(String _dtInclusao) {
        this.dtInclusao = _dtInclusao;
    }
    
    public String getdescUsuario() {
        return this.descUsuario;
    }

    public void setdescUsuario(String _descUsuario) {
        this.descUsuario = _descUsuario;
    }
    

    public String getdtExclusao() {
        return this.dtExclusao;
    }
    
    public void setdescTipoMenu(String _descTipoMenu) {
        this.descTipoMenu = _descTipoMenu;
    }
    

    public String getdescTipoMenu() {
        return this.descTipoMenu;
    }

    public void setdtExclusao(String _dtExclusao) {
        this.dtExclusao = _dtExclusao;
    }
    
    public String geturl() {
        return this.url;
    }

    public void seturl(String _url) {
        this.url = _url;
    }
    
     public static String insertInto(){
        return "insert into tb_menu(pagina, descricao,data_inclusao , usr_inclusao,TIPOMENU,URL) values (?,?,?,?,?,?)";
    }
    
    public static String update(){
        return " update tb_menu set pagina = ?, descricao = ?, URL = ?, TIPOMENU = ? where id_menu = ?";
    }
    
    public static String delete(){
        return " update tb_menu set data_exclusao = ?, usr_exclusao = ? where id_menu = ?";
    }
    
    public static String listAllInto(){
        return " select MU.*, US.NOME from tb_menu as MU inner join tb_usuario US on MU.USR_INCLUSAO = US.ID_USUARIO where MU.data_exclusao is null";
    }

}

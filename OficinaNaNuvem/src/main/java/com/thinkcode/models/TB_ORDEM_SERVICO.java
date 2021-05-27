package com.thinkcode.models;

public class TB_ORDEM_SERVICO {

    private int Id_Order_Servico;
    private int Id_Cliente;
    private String Ds_Marca_Carro;
    private String Ds_Modelo_Carro;
    private String Ds_Placa_Carro;
    private double Valor_Ordem;
    private int PAGAMENTO;
    private int Id_Usuario_Inclusao;
    private String DT_INCLUSAO;
    private int ID_USUARIO_EXCLUSAO;
    private String DT_EXCLUSAO;
    
    
    public int getIdOrderServico() {
        return Id_Order_Servico;
    }

    public void setIdOrderServico(int _Id_Order_Servico) {
        this.Id_Order_Servico = _Id_Order_Servico;
    }
        public int getIdCliente() {
        return Id_Cliente;
    }

    public void setIdCliente(int _Id_Cliente) {
        this.Id_Cliente = _Id_Cliente;
    }
    
    public String getDsMarcaCarro() {
        return Ds_Marca_Carro;
    }

    public void setDsMarcaCarro(String _Ds_Marca_Carro) {
        this.Ds_Marca_Carro = _Ds_Marca_Carro;
    }
    
    public String getDsModeloCarro() {
        return Ds_Modelo_Carro;
    }

    public void setDsModeloCarro(String _Ds_Modelo_Carro) {
        this.Ds_Modelo_Carro = _Ds_Modelo_Carro;
    }
    
    public String getDsPlacaCarro() {
        return Ds_Placa_Carro;
    }

    public void setDsPlacaCarro(String _Ds_Placa_Carro) {
        this.Ds_Placa_Carro = _Ds_Placa_Carro;
    }

    public double getValorOrdem(){
        return Valor_Ordem;
    }
    
     public void setValorOrdem(Double _Valor_Ordem) {
        this.Valor_Ordem = _Valor_Ordem;
    }
      public int getPAGAMENTO() {
        return PAGAMENTO;
    }

    public void setPAGAMENTO(int _PAGAMENTO) {
        this.PAGAMENTO = _PAGAMENTO;
    }
    
     public int getIdUsuarioInclusao() {
        return Id_Usuario_Inclusao;
    }

    public void setIdUsuarioInclusao(int _Id_Usuario_Inclusao) {
        this.Id_Usuario_Inclusao = _Id_Usuario_Inclusao;
    }
    
    public String getDtInclusao() {
        return DT_INCLUSAO;
    }

    public void setDtInclusao(String _DT_INCLUSAO) {
        this.DT_INCLUSAO = _DT_INCLUSAO;
    }
    public int getIdUsuarioExclusao() {
        return ID_USUARIO_EXCLUSAO;
    }

    public void setIdUsuarioExclusao(int _ID_USUARIO_EXCLUSAO) {
        this.ID_USUARIO_EXCLUSAO = _ID_USUARIO_EXCLUSAO;
    }
    public String getDtExclusao() {
        return DT_EXCLUSAO;
    }

    public void setDtExclusao(String _DT_EXCLUSAO) {
        this.DT_EXCLUSAO = _DT_EXCLUSAO;
    }
}

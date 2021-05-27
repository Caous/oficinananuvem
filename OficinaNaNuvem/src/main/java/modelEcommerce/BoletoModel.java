/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelEcommerce;

/**
 *
 * @author Leonardo Moreno
 */
public class BoletoModel {
    private int idboleto;
    private int idvenda;
    private int idcliente;
    private String idapi;
    private boolean pago;
    private String pdf;

    public BoletoModel(){}
    
    public BoletoModel(int idboleto,int idvenda,int idcliente,String idapi,boolean pago,String pdf){
    
        this.idboleto = idboleto;
        this.idvenda = idvenda;
        this.idcliente = idcliente;
        this.idapi = idapi;
        this.pago = pago;
        this.pdf = pdf;
    }
    
    
    public int getIdboleto() {
        return idboleto;
    }

    public void setIdboleto(int idboleto) {
        this.idboleto = idboleto;
    }

    public int getIdvenda() {
        return idvenda;
    }

    public void setIdvenda(int idvenda) {
        this.idvenda = idvenda;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getIdapi() {
        return idapi;
    }

    public void setIdapi(String idapi) {
        this.idapi = idapi;
    }

    public boolean getPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
}

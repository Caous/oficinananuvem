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
public class EnderecoClienteModel {
                private int id_endereco;
                private int id_cliente;
                private String nome_endereco;
                private String nome_receptor;
                private String cep;
                private String rua;
                private int numero;
                private String complemento;
                private String bairro;
                private String cidade;
                private String estado;
                private String telefone;
                private String referencia_entrega;
                 private boolean fatura;

    public int getId_endereco() {
        return id_endereco;
    }

    public void setId_endereco(int id_endereco) {
        this.id_endereco = id_endereco;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNome_endereco() {
        return nome_endereco;
    }

    public void setNome_endereco(String nome_endereco) {
        this.nome_endereco = nome_endereco;
    }

    public String getNome_receptor() {
        return nome_receptor;
    }

    public void setNome_receptor(String nome_receptor) {
        this.nome_receptor = nome_receptor;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getReferencia_entrega() {
        return referencia_entrega;
    }

    public void setReferencia_entrega(String referencia_entrega) {
        this.referencia_entrega = referencia_entrega;
    }

    public void getId_endereco(int aInt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getFatura() {
        return fatura;
    }

    public void setFatura(boolean fatura) {
        this.fatura = fatura;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelEcommerce;

import com.thinkcode.models.Criptografia;

/**
 *
 * @author Leonardo Moreno
 */
public class ClienteModel extends Criptografia {

    private int id_cliente;
    private String cpf_cnpj;
    private String rg;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private String sexo;
    private String data_nascimento;
    private int usr_exclusao;
    private String data_exclusao;

    public ClienteModel() {

    }

    public ClienteModel(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }
    public ClienteModel(int id_cliente, String cpf_cnpj, String nome,
            String email, String senha,String rg ,String telefone, String sexo, 
            String data_nascimento, int usr_exclusao ,String data_exclusao) {
        this.id_cliente = id_cliente;
        this.cpf_cnpj = cpf_cnpj;
        this.rg = rg;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
        this.sexo = sexo;
        this.data_nascimento = data_nascimento;
        this.usr_exclusao = usr_exclusao;
        this.data_exclusao = data_exclusao;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        String senhaCrip = "";
        if (senha != null) {
            senhaCrip = CriptografiaMD5(senha);
        } else {
            senhaCrip = senha;
        }

        return senhaCrip;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public int getUsr_exclusao() {
        return usr_exclusao;
    }

    public void setUsr_exclusao(int usr_exclusao) {
        this.usr_exclusao = usr_exclusao;
    }

    public String getData_exclusao() {
        return data_exclusao;
    }

    public void setData_exclusao(String data_exclusao) {
        this.data_exclusao = data_exclusao;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

}

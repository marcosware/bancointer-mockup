package com.example.bancoInter;

public class Cliente {
    private int cliente_id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String senha;

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int id) {
        this.cliente_id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha(){return senha;
    }
    public void setSenha(String senha){this.senha = senha;
    }
}

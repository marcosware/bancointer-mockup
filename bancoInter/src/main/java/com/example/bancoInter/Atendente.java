package com.example.bancoInter;

import java.util.Date;

public class Atendente {
    private int atendente_id;
    private String nome_atendente;
    private String email_atendente;
    private String telefone_atendente;
    private Date data_admissao;

    public int getAtendente_id() {
        return atendente_id;
    }

    public void setAtendente_id(int atendente_id) {
        this.atendente_id = atendente_id;
    }

    public String getNome_atendente() {
        return nome_atendente;
    }

    public void setNome_atendente(String nome_atendente) {
        this.nome_atendente = nome_atendente;
    }

    public String getEmail_atendente() {
        return email_atendente;
    }

    public void setEmail_atendente(String email_atendente) {
        this.email_atendente = email_atendente;
    }

    public String getTelefone_atendente() {
        return telefone_atendente;
    }

    public void setTelefone_atendente(String telefone_atendente) {
        this.telefone_atendente = telefone_atendente;
    }

    public Date getData_admissao() {
        return data_admissao;
    }

    public void setData_admissao(Date data_admissao) {
        this.data_admissao = data_admissao;
    }
}

package com.example.bancoInter;

import java.util.Date;

public class Atendimento {
    private int atendimento_id;
    private int cliente_id;
    private Date data_hora;
    private String descricao_atendimentos;
    private int atendente_id;

    public int getAtendimento_id() {
        return atendimento_id;
    }

    public void setAtendimento_id(int atendimento_id) {
        this.atendimento_id = atendimento_id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    public String getDescricao_atendimentos() {
        return descricao_atendimentos;
    }

    public void setDescricao_atendimentos(String descricao_atendimentos) {
        this.descricao_atendimentos = descricao_atendimentos;
    }

    public int getAtendente_id() {
        return atendente_id;
    }

    public void setAtendente_id(int atendente_id) {
        this.atendente_id = atendente_id;
    }
}

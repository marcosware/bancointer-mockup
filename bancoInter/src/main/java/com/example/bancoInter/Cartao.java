package com.example.bancoInter;

import java.util.Date;

public class Cartao {
    private int cartao_id;
    private int cliente_id;
    private String tipo;
    private double limite;
    private String numero_cartao;
    private Date data_vencimento;

    public int getCartao_id() {
        return cartao_id;
    }

    public void setCartao_id(int id) {
        this.cartao_id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public String getNumero_cartao() {
        return numero_cartao;
    }

    public void setNumero_cartao(String numero_cartao) {
        this.numero_cartao = numero_cartao;
    }

    public Date getData_vencimento() {
        return data_vencimento;
    }

    public void setData_vencimento(Date data_vencimento) {
        this.data_vencimento = data_vencimento;
    }
}

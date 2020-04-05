package com.solid.calculadoraDePreco;

public class Compra {
    private double valor;
    private String cidade;

    public Compra() {}

    public Compra(double valor, String cidade) {
        this.valor = valor;
        this.cidade = cidade;
    }

    public double getValor() {
        return valor;
    }

    public Compra setValor(double valor) {
        this.valor = valor;
        return this;
    }

    public String getCidade() {
        return cidade;
    }

    public Compra setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }
}

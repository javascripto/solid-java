package com.solid.processadorDeBoletos;

public class Boleto {
    private double valor;

    public Boleto() {
        this.valor = 0;
    }

    public Boleto(double _valor) {
        valor = _valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}

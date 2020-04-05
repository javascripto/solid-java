package com.solid.ProcessadorDeInvestimentos;

public class ManipuladorDeSaldo {

    private double saldo;

    public void depositar(double valor) {
        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (valor > saldo)
            throw new IllegalArgumentException();
        saldo -= valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void render(double taxa) {
        this.saldo *= taxa;
    }
}

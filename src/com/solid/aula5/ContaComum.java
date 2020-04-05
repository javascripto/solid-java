package com.solid.aula5;

public class ContaComum {
    protected double saldo;

    public ContaComum() {
        this.saldo = 0;
    }

    public ContaComum(double saldoAbertura) {
        saldo = saldoAbertura;
    }

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

    public void render() {
        this.saldo *= 1.1;
    }
}

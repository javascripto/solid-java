package com.solid.ProcessadorDeInvestimentos;

public class ContaDeEstudante {

    private int milhas;
    private ManipuladorDeSaldo manipulador;

    public ContaDeEstudante() {
        manipulador = new ManipuladorDeSaldo();
    }

    public ContaDeEstudante(double saldoInicial) {
        manipulador = new ManipuladorDeSaldo();
        manipulador.depositar(saldoInicial);
    }

    public void depositar(double valor) {
        manipulador.depositar(valor);
        milhas += (int) valor;
    }

    public int getMilhas() {
        return milhas;
    }

    public double getSaldo() {
        return manipulador.getSaldo();
    }
}

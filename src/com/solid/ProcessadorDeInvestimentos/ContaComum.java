package com.solid.ProcessadorDeInvestimentos;

public class ContaComum {

    private ManipuladorDeSaldo manipulador;

    public ContaComum() {
        this.manipulador = new ManipuladorDeSaldo();
    }

    public ContaComum(double saldoAbertura) {
        manipulador = new ManipuladorDeSaldo();
        manipulador.depositar(saldoAbertura);
    }

    public void render() {
        manipulador.render(1.1);
    }

    public void sacar(double valor) {
        manipulador.sacar(valor);
    }

    public void depositar(double valor) {
        manipulador.depositar(valor);
    }

    public double getSaldo() {
        return manipulador.getSaldo();
    }
}

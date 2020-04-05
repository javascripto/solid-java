package com.solid.aula5;

public class ContaEstudante extends ContaComum {
    private int milhas;

    public void depositar(double valor) {
        super.depositar(valor);
        milhas += (int) valor;
    }

    public void render()  {
        throw new RuntimeException("Não pode Render");
    }

    public int getMilhas() {
        return milhas;
    }
}

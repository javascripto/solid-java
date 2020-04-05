package com.solid.calculadoraDePreco;

public class CalculadoraDePreco {

    private final TabelaDePreco tabela;
    private final ServiceDeEntrega entrega;

    public CalculadoraDePreco(TabelaDePreco tabela, ServiceDeEntrega entrega) {
        this.tabela = tabela;
        this.entrega = entrega;
    }

    public double calcular(Compra produto) {
        double desconto = tabela.descontPara(produto.getValor());
        double frete = entrega.para(produto.getCidade());

        return produto.getValor() * (1 - desconto) + frete;
    }
}

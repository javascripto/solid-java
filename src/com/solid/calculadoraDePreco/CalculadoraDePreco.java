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

    public static void main(String[] args) {
        // TabelaDePreco tabela = new TabelaDePrecoPadrao();
        TabelaDePreco tabela = new TabelaDePrecoSemDesconto();
        ServiceDeEntrega entrega = new Frete();

        Compra produto = new Compra(1500, "SAO PAULO");

        CalculadoraDePreco calculadora = new CalculadoraDePreco(tabela, entrega);
        System.out.println(calculadora.calcular(produto));
    }
}

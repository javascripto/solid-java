package com.solid.calculadoraDePreco;

public class CalculadoraDePreco {
    public double calcular(Compra produto) {
        TabelaDePrecoPadrao tabela = new TabelaDePrecoPadrao();
        Frete correios = new Frete();

        double desconto = tabela.descontPara(produto.getValor());
        double frete = correios.para(produto.getCidade());

        return produto.getValor() * (1 - desconto) + frete;
    }
}

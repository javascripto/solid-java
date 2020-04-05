package com.solid;

import com.solid.calculadoraDePreco.CalculadoraDePreco;
import com.solid.calculadoraDePreco.Compra;
import com.solid.calculadoraDeSalario.Cargo;
import com.solid.calculadoraDeSalario.Funcionario;
import com.solid.calculadoraDeSalario.CalculadoraDeSalario;
import com.solid.geradorDeNotaFiscal.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Main.calculadoraDeSalario();
        // Main.geradorDeNotaFiscal();
         Main.calculadoraDePreco();
    }

    private static void calculadoraDeSalario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setCargo(Cargo.DESENVOLVEDOR);
        funcionario.setSalarioBase(7200);

        CalculadoraDeSalario calculadoraDeSalario = new CalculadoraDeSalario();

        System.out.println(calculadoraDeSalario.calcula(funcionario));
    }

    private static void geradorDeNotaFiscal() {
        List<AcaoAposGerarNota> acoesAposGerarNota = new ArrayList<>();
        acoesAposGerarNota.add(new NotaFiscalDAO());
        acoesAposGerarNota.add(new EnviadorDeEmail());

        Fatura fatura = new Fatura(123.0, "Fulano");
        GeradorDeNotaFiscal geradorNF = new GeradorDeNotaFiscal(acoesAposGerarNota);

        geradorNF.gerar(fatura);
    }

    private static void calculadoraDePreco() {
        CalculadoraDePreco calculadora = new CalculadoraDePreco();
        Compra produto = new Compra();
        produto.setCidade("SAO PAULO");
        System.out.println(calculadora.calcular(produto));
    }
}

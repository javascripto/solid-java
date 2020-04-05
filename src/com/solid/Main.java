package com.solid;

import com.solid.calculadoraDeSalario.Cargo;
import com.solid.calculadoraDeSalario.Funcionario;
import com.solid.calculadoraDeSalario.CalculadoraDeSalario;
import com.solid.geradorDeNotaFiscal.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Main.calculadoraDeSalario();
        Main.geradorDeNotaFiscal();
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
}

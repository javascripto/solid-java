package com.solid;

import com.solid.calculadoraDeSalario.Cargo;
import com.solid.calculadoraDeSalario.Funcionario;
import com.solid.calculadoraDeSalario.CalculadoraDeSalario;

public class Main {

    public static void main(String[] args) {
        // Main.calculadoraDeSalario();
    }

    private static void calculadoraDeSalario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setCargo(Cargo.DESENVOLVEDOR);
        funcionario.setSalarioBase(7200);

        CalculadoraDeSalario calculadoraDeSalario = new CalculadoraDeSalario();

        System.out.println(calculadoraDeSalario.calcula(funcionario));
    }

    private static void geradorDeNotaFiscal() {

    }
}

package com.solid;

public class Main {

    public static void main(String[] args) {
        Funcionario funcionario = new Funcionario();
        funcionario.setCargo(Cargo.DESENVOLVEDOR);
        funcionario.setSalarioBase(7200);

        CalculadoraDeSalario calculadoraDeSalario = new CalculadoraDeSalario();

        System.out.println(calculadoraDeSalario.calcula(funcionario));
    }
}

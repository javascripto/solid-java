package com.solid;

public enum Cargo {
    DBA(new QuinzeOuVinteECincoPorCento()),
    DESENVOLVEDOR(new DezOuQuinzePorCento()),
    TESTER(new QuinzeOuVinteECincoPorCento());

    private RegraDeCalculo regra;

    Cargo(RegraDeCalculo regra) {
        this.regra = regra;
    }

    RegraDeCalculo getRegraDeCalculo() {
        return regra;
    }
}

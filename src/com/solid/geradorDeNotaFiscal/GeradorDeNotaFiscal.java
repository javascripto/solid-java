package com.solid.geradorDeNotaFiscal;

import java.util.List;
import java.util.ArrayList;

public class GeradorDeNotaFiscal {
    private final List<AcaoAposGerarNota> acoesAposGerarNota;

    public GeradorDeNotaFiscal(List<AcaoAposGerarNota> acoes) {
        this.acoesAposGerarNota = acoes;
    }

    public NotaFiscal gerar(Fatura fatura) {
        double valor = fatura.getValorMensal();
        NotaFiscal nf = new NotaFiscal(valor, impostoSimplesSobreO(valor));

        for (AcaoAposGerarNota acao: acoesAposGerarNota) {
            acao.executar(nf);
        }

        return nf;
    }

    private double impostoSimplesSobreO(double valor) {
        return valor * 0.06;
    }

    public static void main(String[] args) {
        List<AcaoAposGerarNota> acoesAposGerarNota = new ArrayList<>();
        acoesAposGerarNota.add(new NotaFiscalDAO());
        acoesAposGerarNota.add(new EnviadorDeEmail());

        Fatura fatura = new Fatura(123.0, "Fulano");
        GeradorDeNotaFiscal geradorNF = new GeradorDeNotaFiscal(acoesAposGerarNota);

        geradorNF.gerar(fatura);
    }
}

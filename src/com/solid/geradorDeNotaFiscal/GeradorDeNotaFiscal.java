package com.solid.geradorDeNotaFiscal;

public class GeradorDeNotaFiscal {
    private final EnviadorDeEmail email;
    private final NotaFiscalDAO dao;

    public GeradorDeNotaFiscal(EnviadorDeEmail email, NotaFiscalDAO dao) {
        this.email = email;
        this.dao = dao;
    }

    public NotaFiscal gerar(Fatura fatura) {
        double valor = fatura.getValorMensal();
        NotaFiscal nf = new NotaFiscal(valor, impostoSimplesSobreO(valor));
        email.enviarEmail(nf);
        dao.persistir(nf);
        return nf;
    }

    private double impostoSimplesSobreO(double valor) {
        return valor * 0.06;
    }
}

package br.uel.principal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ContaInvestimento extends ContaBancaria {
	
	@Override
	public void sacar(BigDecimal saque) {
		if(this.saldo.compareTo(saque) >= 0) {
			this.saldo = this.saldo.subtract(saque);
			System.out.printf("\nSaque de %s realizado, restam %s reais na conta\n", NumberFormat.getInstance(Locale.US).format(saque), NumberFormat.getInstance(Locale.US).format(this.saldo));
		}else {
			System.out.println("\nSaldo insuficiente");
		}
	}
	
	public BigDecimal calcularTributo(BigDecimal taxaRendimento) {
		BigDecimal rendimento = this.saldo.multiply(taxaRendimento);
		BigDecimal lucro = rendimento.multiply(BigDecimal.valueOf(0.005));
		return lucro;
		
	}
	
	protected BigDecimal calcularTaxaAdministracao(BigDecimal taxaRendimento) {
		BigDecimal rendimento = this.saldo.multiply(taxaRendimento);
		BigDecimal lucro = rendimento.multiply(BigDecimal.valueOf(0.01));
		return lucro;
	}

	protected void calcularNovoSaldo(BigDecimal taxaRendimento) {
		BigDecimal rendimento = this.saldo.multiply(taxaRendimento);
		this.saldo = this.saldo.add(rendimento);
	}
}

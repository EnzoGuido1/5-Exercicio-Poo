package br.uel.principal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ContaPoupanca extends ContaBancaria {
	protected Integer diaRendimento;
	
	protected Integer getDiaRendimento() {
		return diaRendimento;
	}
	
	protected void setDiaRendimento(Integer diaRendimento) {
		this.diaRendimento = diaRendimento;
	}
	
	@Override
	public void sacar(BigDecimal saque) {
		if(this.saldo.compareTo(saque) >= 0) {
			this.saldo = this.saldo.subtract(saque);
			System.out.printf("\nSaque de %s realizado, restam %s reais na conta\n", NumberFormat.getInstance(Locale.US).format(saque), NumberFormat.getInstance(Locale.US).format(this.saldo));
		}else {
			System.out.println("\nSaldo insuficiente");
		}
	}
	
	protected void calcularNovoSaldo(BigDecimal taxaDeRendimento) {
		BigDecimal porcentagem = this.saldo.multiply(taxaDeRendimento);
		this.saldo = this.saldo.add(porcentagem);
		System.out.printf("\nSaldo alterado, foi acrescentado %s percentual em relacao ao seu saldo anterior, totalizando %s reais no saldo atual\n", taxaDeRendimento, NumberFormat.getInstance(Locale.US).format(this.saldo));
	}
}

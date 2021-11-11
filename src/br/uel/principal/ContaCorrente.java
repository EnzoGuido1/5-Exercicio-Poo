package br.uel.principal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class ContaCorrente extends ContaBancaria {
	protected BigDecimal limite;
	
	protected BigDecimal getLimite() {
		return limite;
	}
	
	protected void setLimite(BigDecimal limite) {
		this.limite = limite;
	}
	
	@Override
	public void sacar(BigDecimal saque) {
		BigDecimal menosUm = new BigDecimal(-1);
		BigDecimal limitante = this.limite.multiply(menosUm);
		BigDecimal soma = new BigDecimal(0);
		soma = this.saldo.subtract(saque);
		if(soma.compareTo(limitante) >= 0) {
			this.saldo = this.saldo.subtract(saque);
			System.out.printf("\nSaque de %s realizado, restam %s reais na conta\n", NumberFormat.getInstance(Locale.US).format(saque), NumberFormat.getInstance(Locale.US).format(this.saldo));
		}else {
			System.out.println("\nSaldo e limite insuficientes");
		}
	}
}

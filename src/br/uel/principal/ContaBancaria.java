package br.uel.principal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public abstract class ContaBancaria {
	protected String cliente;
	protected String numeroConta;
	protected BigDecimal saldo;
	
	protected String getCliente() {
		return cliente;
	}
	
	protected void setCliente(String cliente) {
		this.cliente = cliente;
	}
	
	protected String getNumeroConta() {
		return numeroConta;
	}
	
	protected void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}
	
	protected BigDecimal getSaldo() {
		return saldo;
	}
	
	protected void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public abstract void sacar(BigDecimal saque);
	
	public void depositar(BigDecimal deposito) {
		this.saldo = this.saldo.add(deposito);
		System.out.printf("\nDeposito de %s realizado, totalizando %s reais na conta\n", NumberFormat.getInstance(Locale.US).format(deposito), NumberFormat.getInstance(Locale.US).format(this.saldo));
	}
}

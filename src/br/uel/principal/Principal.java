package br.uel.principal;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Principal {
	public ArrayList<ContaBancaria> cb = new ArrayList<ContaBancaria>();
	
	int tipoConta;
	
	public ArrayList<ContaBancaria> getContaBancaria() {
		return cb;
	}
	
	public void setContaBancaria(ArrayList<ContaBancaria> contaBancaria) {
		this.cb = contaBancaria;
	}
	
	private Object percorreContas(String numeroConta) {
		
		for(ContaBancaria contaBancaria : this.cb) {					
			if(contaBancaria.getNumeroConta().equals(numeroConta)) {
				return contaBancaria;
			}
		}
		
		return null;
	}
	
	public void cadastrarConta(Integer tipo, String cliente, String numeroConta, BigDecimal saldo) {
		Scanner input = new Scanner(System.in);
		input.useLocale(Locale.US);
		
		tipoConta = tipo;
		
		Object conta = percorreContas(numeroConta);
		
		if(conta != null) {
			System.out.println("\nJa existe uma conta com este numero");
			return;
		}
		
		if(tipo == 1) { //cc
			ContaCorrente checkingAccount = new ContaCorrente();
			checkingAccount.setCliente(cliente);
			checkingAccount.setNumeroConta(numeroConta);
			checkingAccount.setSaldo(saldo);
			System.out.printf("\nInsira o limite da conta corrente\n");
			BigDecimal limit = input.nextBigDecimal();
			checkingAccount.setLimite(limit);
			this.cb.add(checkingAccount);
			
		}else if(tipo == 2) { //cp
			ContaPoupanca savingsAccount = new ContaPoupanca();
			savingsAccount.setCliente(cliente);
			savingsAccount.setNumeroConta(numeroConta);
			savingsAccount.setSaldo(saldo);
			System.out.printf("\nInsira o rendimento diario da conta poupanca\n");
			Integer dailyIncome = input.nextInt();
			savingsAccount.setDiaRendimento(dailyIncome);
			this.cb.add(savingsAccount);
			
		}else if(tipo == 3) { //ci
			ContaInvestimento investmentAccount = new ContaInvestimento();
			investmentAccount.setCliente(cliente);
			investmentAccount.setNumeroConta(numeroConta);
			investmentAccount.setSaldo(saldo);
			this.cb.add(investmentAccount);
			
		}
		
	}
	
	public void sacarValorConta(String numeroConta, BigDecimal saque) {
		Object conta = percorreContas(numeroConta);
		
		if(conta == null) {
			System.out.println("\nNao existe conta com este numero");
			return;
		}
		
		if (conta instanceof ContaCorrente) 
			((ContaCorrente) conta).sacar(saque);
		
		
		if (conta instanceof ContaPoupanca) 
			((ContaPoupanca) conta).sacar(saque);
		
		
		if (conta instanceof ContaInvestimento) 
			((ContaInvestimento) conta).sacar(saque);
		
	}
	
	public void atualizarContaPoupanca(String numeroConta, BigDecimal taxaRendimento) {
		Object conta = percorreContas(numeroConta);
		
		
		if (conta instanceof ContaPoupanca) {
			((ContaPoupanca) conta).calcularNovoSaldo(taxaRendimento);
			return;
		}

		System.out.println("\nNao existe conta poupanca com este numero de conta");
		
	}
	
	public void depositarValorConta(String numeroConta, BigDecimal deposito) {
		Object conta = percorreContas(numeroConta);
		
		if(conta == null){
			System.out.println("\nNao existe conta com este numero");
			return;
		}
		
		if (conta instanceof ContaCorrente) 
			((ContaCorrente) conta).depositar(deposito);
		
		
		if (conta instanceof ContaPoupanca) 
			((ContaPoupanca) conta).depositar(deposito);
		
		
		if (conta instanceof ContaInvestimento) 
			((ContaInvestimento) conta).depositar(deposito);
	}
	
	public void mostrarSaldoConta(String numeroConta) {
		Object conta = percorreContas(numeroConta);
		
		if(conta == null) {
			System.out.println("\nNao existe conta com este numero");
			return;
		}
		
		if (conta instanceof ContaCorrente) {
			BigDecimal saldo = ((ContaCorrente) conta).getSaldo();
			System.out.printf("\nSaldo atual da conta corrente: %s\n", NumberFormat.getInstance(Locale.US).format(saldo));
		}
		
		
		if (conta instanceof ContaPoupanca) {
			BigDecimal saldo = ((ContaPoupanca) conta).getSaldo();
			System.out.printf("\nSaldo atual da conta poupanca: %s\n", NumberFormat.getInstance(Locale.US).format(saldo));
		}
		
		if (conta instanceof ContaInvestimento) {
			BigDecimal saldo = ((ContaInvestimento) conta).getSaldo();
			System.out.printf("\nSaldo atual da conta investimento: %s\n", NumberFormat.getInstance(Locale.US).format(saldo));
		}
		
	}
	
	public void calcularTributo(String numeroConta, BigDecimal taxaRendimento) {
		Object conta = percorreContas(numeroConta);
		
		if (conta instanceof ContaInvestimento) {
			BigDecimal lucro = ((ContaInvestimento) conta).calcularTributo(taxaRendimento);
			System.out.printf("\nTributo da conta investimento: %s\n", NumberFormat.getInstance(Locale.US).format(lucro));
			return;
		}
		
		System.out.println("\nNao existe conta investimento com este numero de conta");
	}
	
	public void calcularTaxaAdministracao(String numeroConta, BigDecimal taxaRendimento) {
		Object conta = percorreContas(numeroConta);
		
		if (conta instanceof ContaInvestimento) {
			BigDecimal lucro = ((ContaInvestimento) conta).calcularTaxaAdministracao(taxaRendimento);
			System.out.printf("\nTaxa de administracao da conta investimento: %s\n", NumberFormat.getInstance(Locale.US).format(lucro));
			return;
		}
		
		System.out.println("\nNao existe conta investimento com este numero de conta");
	}	
	
}

package br.uel.principal;

import java.math.BigDecimal;

public abstract class Tributavel extends ContaBancaria {
	
	abstract BigDecimal calcularTributo(BigDecimal taxaRendimento);
	
}

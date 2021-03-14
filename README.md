# Herança e Polimorfismo (exemplo Java)

## Sumário
- [O que você vai aprender](#O-que-você-vai-aprender)
- [Pré-requisitos](#pré-requisitos)
- [Enunciado do exercício](#Enunciado-do-exercício)

## O que você vai aprender
- Herança
- Métodos e classes abstratas
- Polimorfismo

## Pré-requisitos

- Lógica de programação
  - Variáveis, entrada, processamento, saída
  - Estrutura condicional
  - Estruturas repetitivas
- OOP básica
  - Classes, atributos, métodos, objetos
  - Construtores, encapsulamento
  - List

## Enunciado do exercício

Fazer um programa para ler os dados de N contribuintes (N fornecido pelo usuário), os quais podem ser pessoa física ou pessoa jurídica, e depois mostrar o valor do imposto pago por cada um, bem como o total de imposto arrecadado. 

Os dados de pessoa física são: nome, renda anual e gastos com saúde. Os dados de pessoa jurídica são nome, renda anual e número de funcionários. As regras para cálculo de imposto são as seguintes:

Pessoa física: pessoas cuja renda foi abaixo de 20000.00 pagam 15% de imposto. Pessoas com renda de 20000.00 em diante pagam 25% de imposto. Se a pessoa teve gastos com saúde, 50% destes gastos são abatidos no imposto. 
Exemplo: uma pessoa cuja renda foi 50000.00 e teve 2000.00 em gastos com saúde, o imposto fica: (50000 * 25%) - (2000 * 50%) = 11500.00

Pessoa jurídica: pessoas jurídicas pagam 16% de imposto. Porém, se a empresa possuir mais de 10 funcionários, ela paga 14% de imposto. 
Exemplo: uma  empresa  cuja  renda foi 400000.00 e possui 25 funcionários, o imposto fica: 400000 * 14% = 56000.00

### Exemplo

```
Entre com o número de contribuintes: 3
Contribuinte #1:
Pessoa física ou júridica (F/J)? F
Nome: Alex
Renda anual: 50000.00
Despesas com Saúde: 2000.00

Contribuinte #2:
Pessoa física ou júridica (F/J)? J
Nome: SoftTech
Renda anual: 400000.00
Number of employees: 25

Contribuinte #3:
Pessoa física ou júridica (F/J)? F
Nome: Bob
Renda anual: 120000.00
Despesas com Saúde: 1000.00

Impostos pagos:
Alex: $ 11500.00
SoftTech: $ 56000.00
Bob: $ 29500.00

TOTAL DE IMPOSTOS: $ 97000.00
```

### Diagrama

![myImage](https://github.com/Maksuedson/assets/blob/master/OOP/heranca_e_polimorfismo_modelo_de_dominio.png)

## Contribuinte
```
package entidades;

public abstract class Contribuinte {
	private String nome;
	private Double rendaAnual;
	
	public  Contribuinte() {	
	}
	
	public Contribuinte(String nome, Double rendaAnual) {
		this.nome = nome;
		this.rendaAnual = rendaAnual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getRendaAnual() {
		return rendaAnual;
	}

	public void setRendaAnual(Double rendaAnual) {
		this.rendaAnual = rendaAnual;
	}
	
	public abstract double imposto();
}

```



## PessoaFisica
```
package entidades;

public class PessoaFisica extends Contribuinte {

	private Double despesasComSaude;
	
	public PessoaFisica() {		
	}

	public PessoaFisica(String nome, Double rendaAnual, Double despesasComSaude) {
		super(nome, rendaAnual);
		this.despesasComSaude = despesasComSaude;
	}
	
	public Double getDespesasComSaude() {
		return despesasComSaude;
	}

	public void setDespesasComSaude(Double despesasComSaude) {
		this.despesasComSaude = despesasComSaude;
	}

	@Override
	public double imposto() {
		//Ternario
		//double taxaBasica = getRendaAnual() < 20000 ? getRendaAnual() * 015 : getRendaAnual() * 0.25;
		double taxaBasica;
		if( getRendaAnual() < 20000) {
			taxaBasica = getRendaAnual() * 0.15;
		}else {
			taxaBasica = getRendaAnual() * 0.25;
		}		
		
		taxaBasica -= getDespesasComSaude() * 0.5;
		if (taxaBasica < 0.0) {
			taxaBasica = 0.0;
		}
		
		return taxaBasica;
	}

}

```

## PessaJuridica
```
package entidades;

public class PessoaJuridica extends Contribuinte{

	private Integer numeroDeFuncionarios;
	
	public PessoaJuridica() {		
	}
	
	public PessoaJuridica(String nome, Double rendaAnual, Integer numeroDeFuncionarios) {
		super(nome, rendaAnual);
		this.numeroDeFuncionarios = numeroDeFuncionarios;
	}	

	public Integer getNumeroDeFuncionarios() {
		return numeroDeFuncionarios;
	}

	public void setNumeroDeFuncionarios(Integer numeroDeFuncionarios) {
		this.numeroDeFuncionarios = numeroDeFuncionarios;
	}

	@Override
	public double imposto() {
		if(numeroDeFuncionarios > 10 ) {
			return getRendaAnual() * 0.14;
		} else {
			return getRendaAnual() * 0.16;
		}
	}

}

```

## App
```
package aplicacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entidades.Contribuinte;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;

public class App {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Entre como o número de contribuintes :");
		int N = sc.nextInt();
		
		List<Contribuinte> lista = new ArrayList<Contribuinte>();
		
		for (int i = 1; i <= N; i++) {
			System.out.println();
			System.out.println("Contribuinte # "+ i);
			System.out.print("Pessoa física ou Jurídica (F/J)? ");
			char tipo = sc.next().toUpperCase().charAt(0);
			System.out.print("Nome: ");
			String nome = sc.next().toUpperCase();
			System.out.print("Renda anual: ");
			Double rendaAnual = sc.nextDouble();
			
			if (tipo == 'F') {				
				System.out.print("Despesas com saúde: ");
				Double despesasComSaude = sc.nextDouble();				
				lista.add(new PessoaFisica(nome, rendaAnual, despesasComSaude));
			}else {
				System.out.print("Número de funcionários: ");
				Integer numeroDeFuncionarios = sc.nextInt();
				lista.add(new PessoaJuridica(nome, rendaAnual, numeroDeFuncionarios));
			}			
		}
		
		System.out.println();
		System.out.println("Taxas pagas:");		
		for(Contribuinte c: lista) {				
			System.out.println(c.getNome() + ": $ " + String.format("%.2f", c.imposto()));
		}
		
		System.out.println();
		double soma = 0.0;
		for(Contribuinte c: lista) {
			soma += c.imposto();
		}
		System.out.println("Total de Imposto: " + String.format("%.2f", soma));						
		sc.close();
	}

}

```
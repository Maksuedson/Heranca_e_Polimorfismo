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

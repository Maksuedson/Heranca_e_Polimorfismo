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

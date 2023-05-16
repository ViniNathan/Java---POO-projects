package labs;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente {
	private String cpf;
    private String educacao;
	private Date dataLicenca;
	private String genero;
	private String classeEconomica;
	private Date dataNascimento;


	public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, String cpf, Date dataNascimento, Double valorSeguro) {
		// Chama o construtor da superclasse
		super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos, valorSeguro);
		this.cpf = cpf;
		this.educacao = educacao;
		this.dataLicenca = dataLicenca;
		this.genero = genero;
		this.classeEconomica = classeEconomica;
		this.dataNascimento = dataNascimento;
	}

	// Getters e setters
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
    public Date getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(Date dataLicenca) {
		this.dataLicenca = dataLicenca;
	}
	
	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}

	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	// Metodo para calcular o Score
	public double calculaScore() {
	    double valorBase = CalcSeguro.VALOR_BASE.getValor(); // Obtém o valor base do seguro
	    
	    // Calculo da idade do cliente
        Date dataNascimento = getDataNascimento();
        Calendar calendarNascimento = Calendar.getInstance();
        calendarNascimento.setTime(dataNascimento);
        Calendar calendarAtual = Calendar.getInstance();
        
        int idade = calendarAtual.get(Calendar.YEAR) - calendarNascimento.get(Calendar.YEAR);
        
        if (calendarAtual.get(Calendar.MONTH) < calendarNascimento.get(Calendar.MONTH) ||
                (calendarAtual.get(Calendar.MONTH) == calendarNascimento.get(Calendar.MONTH) &&
                calendarAtual.get(Calendar.DAY_OF_MONTH) < calendarNascimento.get(Calendar.DAY_OF_MONTH))) {
            idade--; // Reduz um ano se ainda não completou o aniversário no ano corrente
        }
        
        // Obtem a quantidade de carros do cliente
	    int quantidadeCarros = super.getListaVeiculos().size();

	    if (idade >= 18 && idade <= 30) {
	    	valorBase *= CalcSeguro.FATOR_18_30.getValor(); // Multiplica o valor base pelo fator de idade para clientes entre 18 e 30 anos
	    } else if (idade > 30 && idade <= 60) {
	    	valorBase *= CalcSeguro.FATOR_30_60.getValor(); // Multiplica o valor base pelo fator de idade para clientes entre 30 e 60 anos
	    } else if (idade > 60 && idade <= 90) {
	    	valorBase *= CalcSeguro.FATOR_60_90.getValor(); // Multiplica o valor base pelo fator de idade para clientes acima de 60 anos
	    }
	    
	    double score = valorBase * quantidadeCarros; // Calculo do valor de score
	    return score; // Retorna o valor final do score
	}
	
	
    @Override
    public String toString() {
    String output = "Cliente PF" + "\n";
    output += super.toString();
    output += "CPF: " + this.getCpf() + "\n";
    output += "Genero: " + this.getGenero() + "\n";
    output += "Data da Licenca: " + this.getDataLicenca().toString() + "\n";
    output += "Educacao: " + this.getEducacao() + "\n";
    output += "Data de Nascimento: " + this.getDataNascimento().toString() + "\n";
    output += "Classe Economica: " + this.getClasseEconomica() + "\n";
    output += "Score: " + this.calculaScore() + "\n";
    return output;
    }
}
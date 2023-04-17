package labs;

import java.util.Date;
import java.util.List;

public class ClientePF extends Cliente {
	private String cpf;
    private String educacao;
	private Date dataLicenca;
	private String genero;
	private String classeEconomica;
	private Date dataNascimento;


	public ClientePF(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, String cpf, Date dataNascimento) {
		// Chama o construtor da superclasse
		super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
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

	// Método para validar o CPF
    public boolean validarCPF() {
        String cpfLimpo = cpf.replaceAll("\\D+", ""); // Removendo caracteres não numéricos do CPF
        if (cpfLimpo.length() != 11) { // Verificando se o CPF tem 11 dígitos
            return false;
        }
        // Verificando se todos os dígitos são iguais
        boolean todosDigitosIguais = true;
        for (int i = 1; i < cpfLimpo.length(); i++) {
            if (cpfLimpo.charAt(i) != cpfLimpo.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
        	System.out.println("O CPF fornecido pelo cliente é inválido");
            return false;
        }
        // Calculando os dígitos verificadores
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += digito * peso;
            peso--;
        }
        int resto = soma % 11;
        int primeiroDigitoVerificador = (resto < 2) ? 0 : (11 - resto);

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpfLimpo.charAt(i));
            soma += digito * peso;
            peso--;
        }
        resto = soma % 11;
        int segundoDigitoVerificador = (resto < 2) ? 0 : (11 - resto);

        // Verificando se os dígitos verificadores calculados são iguais aos dígitos verificadores do CPF
        if (primeiroDigitoVerificador == Character.getNumericValue(cpfLimpo.charAt(9)) &&
                segundoDigitoVerificador == Character.getNumericValue(cpfLimpo.charAt(10))) {
        	System.out.println("O CPF fornecido pelo cliente é válido");
            return true;
        } else {
        	System.out.println("O CPF fornecido pelo cliente é inválido");
            return false;
        }
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
    output += "\n";
    return output;
    }
}
package lab02;

public class Cliente {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private Integer idade;
    private String endereco;

    // Construtor
    public Cliente(String nome, String cpf, String dataNascimento, Integer idade, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }
    
    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // Padrão de "print"
    public String toString() {
        return "Cliente{" +
                "Nome='" + nome + '\'' +
                ", Cpf='" + cpf + '\'' +
                ", Data de Nascimento='" + dataNascimento + '\'' +
                ", Idade=" + idade +
                ", Endereco='" + endereco + '\'' +
                '}';
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
            return true;
        } else {
            return false;
        }
    }



}

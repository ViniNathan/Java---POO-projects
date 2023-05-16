package labs;

public class Validacao {
	public static String cpf;
	public static String cnpj;
	public static String nome;
	
	
		// Método para validar o CPF
	    public static boolean validaCPF(String cpf) {
	        cpf = cpf.replaceAll("\\D+", ""); // Removendo caracteres não numéricos do CPF
	        if (cpf.length() != 11) { // Verificando se o CPF tem 11 dígitos
	            return false;
	        }
	        // Verificando se todos os dígitos são iguais
	        boolean todosDigitosIguais = true;
	        for (int i = 1; i < cpf.length(); i++) {
	            if (cpf.charAt(i) != cpf.charAt(0)) {
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
	            int digito = Character.getNumericValue(cpf.charAt(i));
	            soma += digito * peso;
	            peso--;
	        }
	        int resto = soma % 11;
	        int primeiroDigitoVerificador = (resto < 2) ? 0 : (11 - resto);
	
	        soma = 0;
	        peso = 11;
	        for (int i = 0; i < 10; i++) {
	            int digito = Character.getNumericValue(cpf.charAt(i));
	            soma += digito * peso;
	            peso--;
	        }
	        resto = soma % 11;
	        int segundoDigitoVerificador = (resto < 2) ? 0 : (11 - resto);
	
	        // Verificando se os dígitos verificadores calculados são iguais aos dígitos verificadores do CPF
	        if (primeiroDigitoVerificador == Character.getNumericValue(cpf.charAt(9)) &&
	                segundoDigitoVerificador == Character.getNumericValue(cpf.charAt(10))) {
	        	System.out.println("O CPF fornecido pelo cliente é válido");
	            return true;
	        } else {
	        	System.out.println("O CPF fornecido pelo cliente é inválido");
	            return false;
	        }
	    }
    
	    // Método para validar o nome
        public static boolean validarNome(String nome) {
            if(nome == null || nome.isEmpty()) {
                return false; // Retorna falso se o nome é nulo ou vazio
            }
            
            for(char letra : nome.toCharArray()) {
                if(!Character.isLetter(letra)) {
                    return false; // Retorna falso se a string contém caracteres que não são letras
                }
            }
            
            return true; // Retorna verdadeiro se a string contém apenas letras
        }
        
	    // Método para validar o CNPJ
	    public static boolean validaCNPJ(String cnpj) {
	        // Remove caracteres não numéricos do CNPJ
	        cnpj = cnpj.replaceAll("[^0-9]", "");
	
	        // Verifica se o CNPJ tem 14 caracteres
	        if (cnpj.length() != 14) {
	        	System.out.println("O CNPJ fornecido pelo cliente é inválido");
	            return false;
	        }
	
	        // Calcula o primeiro dígito verificador do CNPJ
	        int soma = 0;
	        int peso = 2;
	        for (int i = 11; i >= 0; i--) {
	            int num = Integer.parseInt(cnpj.charAt(i) + "");
	            soma += num * peso;
	            peso = (peso == 9) ? 2 : peso + 1;
	        }
	        int resto = soma % 11;
	        int dv1 = (resto < 2) ? 0 : 11 - resto;
	
	        // Verifica o primeiro dígito verificador
	        if (Integer.parseInt(cnpj.charAt(12) + "") != dv1) {
	        	System.out.println("O CNPJ fornecido pelo cliente é inválido");
	            return false;
	        }
	
	        // Calcula o segundo dígito verificador do CNPJ
	        soma = 0;
	        peso = 2;
	        for (int i = 12; i >= 0; i--) {
	            int num = Integer.parseInt(cnpj.charAt(i) + "");
	            soma += num * peso;
	            peso = (peso == 9) ? 2 : peso + 1;
	        }
	        resto = soma % 11;
	        int dv2 = (resto < 2) ? 0 : 11 - resto;
	
	        // Verifica o segundo dígito verificador
	        if (Integer.parseInt(cnpj.charAt(13) + "") != dv2) {
	        	System.out.println("O CNPJ fornecido pelo cliente é inválido");
	            return false;
	        }
	
	        // Se chegou até aqui, o CNPJ é válido
	        System.out.println("O CNPJ fornecido pelo cliente é válido");
	        return true;
	    }
    
}

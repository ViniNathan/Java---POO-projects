package labs;

import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente {

    private String cnpj;
    private Date dataFundacao;

    public ClientePJ(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, String cnpj, Date dataFundacao) {
        // Chama o construtor da superclasse
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }

    // Getters e setters
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    // Método para validar o CNPJ
    public boolean validarCNPJ(String cnpj) {
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

    @Override
    public String toString() {
        return super.toString() + "CNPJ: " + this.getCnpj() + "\n" + "Data de Fundacao: " + this.getDataFundacao().toString() + "\n" + "\n";
    }
}
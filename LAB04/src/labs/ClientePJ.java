package labs;

import java.util.Date;
import java.util.List;

public class ClientePJ extends Cliente {

    private String cnpj;
    private Date dataFundacao;
    private Integer qtdeFuncionarios;

    public ClientePJ(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, String cnpj, Date dataFundacao, Double valorSeguro, Integer qtdeFuncionarios) {
        // Chama o construtor da superclasse
        super(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos, valorSeguro);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.qtdeFuncionarios = qtdeFuncionarios;
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
    
    public Integer getQtdeFuncionarios() {
        return qtdeFuncionarios;
    }

    public void setQtdeFuncionarios(Integer qtdeFuncionarios) {
    	this.qtdeFuncionarios = qtdeFuncionarios;
    }

    // Metodo para calcular o Score
    public double calculaScore() {
        double valorBase = CalcSeguro.VALOR_BASE.getValor(); // Obtém o valor base do seguro da classe CalcSeguro
        double quantidadeFunc = this.getQtdeFuncionarios(); // Obtém a quantidade de funcionários

        // Obtem a quantidade de carros do cliente
	    int quantidadeCarros = super.getListaVeiculos().size();
	    
        double score = valorBase * (1 + (quantidadeFunc / 100)) * quantidadeCarros; // Calcula o valor final do score
        return score; // Retorna o valor final do score
    }

    
    
    @Override
    public String toString() {
    	String output = "Cliente PJ" + "\n";
        output += super.toString();
        output += "CNPJ: " + this.getCnpj() + "\n";
        output += "Data de Fundacao: " + this.getDataFundacao().toString() + "\n";
        output += "Quantidade de Funcionarios: " + this.getQtdeFuncionarios() + "\n";
        output += "Score: " + this.calculaScore() + "\n";
        return output;
    }
}
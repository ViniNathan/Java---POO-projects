package labs;

import java.util.Date;
import java.util.List;

public class Cliente {
	private String nome;
    private String endereco;
	private List<Veiculo> listaVeiculos;
	private Double valorSeguro;

    // Construtor
    public Cliente(String nome, String endereco, Date dataLicenca, String educacao, String genero, String classeEconomica, List<Veiculo> listaVeiculos, Double valorSeguro) {
    	this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
        this.valorSeguro = valorSeguro;
    }
    
    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public List<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(List<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    
    public Double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(Double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    // Remover veiculo
    public boolean removerVeiculo(String placa) {  // Parâmetro nome representa a placa que se deseja remover
    	for (Veiculo veiculo : listaVeiculos) {
    		if (veiculo.getPlaca().equals(placa)) { // Checado se a placa do veiculo é igual ao valor passado como parâmetro
    			listaVeiculos.remove(veiculo); // Veiculo é removido da lista e o método retorna true
    			return true;
    		}
    	}
    	return false;
    }
    
    
    @Override
    public String toString() {
    String output = "Nome: " + nome + "\n";
    output += "Endereco: " + endereco + "\n";
    output += "Lista de Veiculos: " + listaVeiculos + "\n";
    return output;
    }
}

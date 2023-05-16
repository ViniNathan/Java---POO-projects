package labs;
import java.util.ArrayList;
import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;
    
 // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
    }

    // Getters e setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(List<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    // Cadastro de cliente
    public boolean cadastrarCliente(Cliente cliente) {
        if (listaClientes.contains(cliente)) {
            return false; // Se o cliente já estiver presente na lista, o método retorna false
        }
        else {
	        listaClientes.add(cliente);
	        return true; // Se o cliente for novo, o método retorna true
        }
    }

    // Remover cliente
    public boolean removerCliente(String nome) {  // Parâmetro nome representa o nomedo cliente que se deseja remover
    	for (Cliente cliente : listaClientes) {
    		if (cliente.getNome().equals(nome)) { // Checado se o CPF do cliente é igual ao valor passado como parâmetro
    			listaClientes.remove(cliente); // Cliente é removido da lista e o método retorna true
    			return true;
    		}
    	}
    	return false;
    }
    
    
    

    // Listar clientes
    public void listarClientes(String tipoCliente) { //Recebe uma string tipoCliente como parâmetro
        for (Cliente cliente : listaClientes) { // Percorre a lista de clientes listaClientes verificando se o tipo de cliente é igual ao tipoCliente
            if (tipoCliente.equalsIgnoreCase(cliente.getClass().getSimpleName())) {  // A comparação é feita ignorando o case (maiuscula ou minuscula)
                System.out.println(cliente); // Se for igual, o método imprime as informações do cliente
            }
        }
    }
    
    // Gerar sinistro
    public boolean gerarSinistro(Sinistro sinistro) {
        if (listaSinistros.contains(sinistro)) {
            return false; // Se o sinistro já estiver presente na lista, o método retorna false
        }
        else {
	        listaSinistros.add(sinistro);
	        return true; // Se o sinistro for novo, o método retorna true
        }
    }      
        
    
    // Visualizar sinistro
    public boolean visualizarSinistro(String cliente) {
    	boolean sinistroEncontrado = false;
    	for (Sinistro sinistro : listaSinistros) {
	    	if (sinistro.getCliente().getNome().equalsIgnoreCase(cliente)) { // Verifica se o nome do cliente é igual ao valor passado como parâmetro, ignorando o case
	    	System.out.println(sinistro); // Imprime as informações do sinistro
	    	sinistroEncontrado = true;
	    	}
    	}
    	return sinistroEncontrado; // Retorna true se pelo menos um sinistro foi encontrado para o cliente, e false caso contrário
    }
    
    // Listar sinistros
    public List<Sinistro> listarSinistros() {
    	return listaSinistros;
    }
    
    // Remover sinistro
    public boolean removerSinistro(Integer ID) {  // Parâmetro ID representa o ID do sinistro que se deseja remover
    	for (Sinistro sinistro : listaSinistros) {
    		if (sinistro.getId().equals(ID)) { // Checado se o ID do sinistro é igual ao valor passado como parâmetro
    			listaSinistros.remove(sinistro); // Sinistro é removido da lista e o método retorna true
    			return true;
    		}
    	}
    	return false;
    }
    
    public double calcularPrecoSeguroCliente(Cliente cliente) {
        double score = 0;
        int quantidadeDeSinistros = 0;
        String nomeCliente = ""; // Nome do cliente ou da empresa

        if (cliente instanceof ClientePF) { // Verifica se o cliente é do tipo Pessoa Física (ClientePF)
            ClientePF clientePF = (ClientePF) cliente; // Faz a conversão do tipo Cliente para ClientePF
            score = clientePF.calculaScore(); // Obtém o score do cliente PF através do seu método calculaScore()
            nomeCliente = clientePF.getNome(); // Obtém o nome do ClientePF
            for (Sinistro sinistro : listaSinistros) {
                if (sinistro.getCliente().getNome().equals(nomeCliente)) {
                    quantidadeDeSinistros++;
                }
            }
        } else if (cliente instanceof ClientePJ) { // Verifica se o cliente é do tipo Pessoa Jurídica (ClientePJ)
            ClientePJ clientePJ = (ClientePJ) cliente; // Faz a conversão do tipo Cliente para ClientePJ
            score = clientePJ.calculaScore(); // Obtém o score do cliente PJ através do seu método calculaScore()
            nomeCliente = clientePJ.getNome(); // Obtém o none do ClientePF
            for (Sinistro sinistro : listaSinistros) {
                if (sinistro.getCliente().getNome().equals(nomeCliente)) {
                    quantidadeDeSinistros++;
                }
            }
        }
        
        double valorSeguro = score * (1 + quantidadeDeSinistros);
        return valorSeguro;
    }


    // Calculo da receita da seguradora
    public double calcularReceita() {
        double receitaTotal = 0.0; // Variável para armazenar a receita total da seguradora

        for (Cliente cliente : listaClientes) { // Percorre a lista de clientes
        	double precoSeguro = cliente.getValorSeguro();
            receitaTotal += precoSeguro; // Soma o preço do seguro de cada cliente à receita total
        }
        return receitaTotal;
    }
    
    @Override
    public String toString() {
        String output = "Nome: " + this.getNome() + "\n";
        output += "Telefone: " + this.getTelefone() + "\n";
        output += "Email: " + this.getEmail() + "\n";
        output += "Endereco: " + this.getEndereco() + "\n";
        output += "Lista de Sinistros: " + listaSinistros + "\n";
        output += "Receita da seguradora: " + this.calcularReceita() + "\n";
        return output;
    }
    
}

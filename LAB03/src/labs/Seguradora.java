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

    public boolean removerCliente(String CpfCnpj) {  // Parâmetro CpfCnpj representa o número de CPF ou CNPJ do cliente que se deseja remover
    	for (Cliente cliente : listaClientes) {
    		if (cliente instanceof ClientePF) { // Se o cliente for do tipo ClientePF, é feita uma conversão para ClientePF
    			ClientePF clientePF = (ClientePF) cliente;
    			if (clientePF.getCpf().equals(CpfCnpj)) { // Checado se o CPF do cliente é igual ao valor passado como parâmetro
    				listaClientes.remove(clientePF); // Cliente é removido da lista e o método retorna true
    				return true;
    			}
    		} 
    		else if (cliente instanceof ClientePJ) { // Se o cliente for do tipo ClientePj, é feita uma conversão para ClientePJ
    			ClientePJ clientePJ = (ClientePJ) cliente;
    			if (clientePJ.getCnpj().equals(CpfCnpj)) { // Checado se o CNPJ do cliente é igual ao valor passado como parâmetro
    				listaClientes.remove(clientePJ); // Cliente é removido da lista e o método retorna true
    				return true;
    			}
    		}
    	}
    	return false;
    }

    public void listarClientes(String tipoCliente) { //Recebe uma string tipoCliente como parâmetro
        for (Cliente cliente : listaClientes) { // Percorre a lista de clientes listaClientes verificando se o tipo de cliente é igual ao tipoCliente
            if (tipoCliente.equalsIgnoreCase(cliente.getClass().getSimpleName())) {  // A comparação é feita ignorando o case (maiuscula ou minuscula)
                System.out.println(cliente); // Se for igual, o método imprime as informações do cliente
            }
        }
    }
    
    public boolean gerarSinistro(Sinistro sinistro) {
        if (listaSinistros.contains(sinistro)) {
            return false; // Se o cliente já estiver presente na lista, o método retorna false
        }
        else {
	        listaSinistros.add(sinistro);
	        return true; // Se o sinistro for novo, o método retorna true
        }
    }      
    
    
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
    
    public List<Sinistro> listarSinistros() {
    	return listaSinistros;
    }
    
    @Override
    public String toString() {
        String output = "Nome: " + this.getNome() + "\n";
        output += "Telefone: " + this.getTelefone() + "\n";
        output += "Email: " + this.getEmail() + "\n";
        output += "Endereco: " + this.getEndereco() + "\n";
        output += "Lista de Sinistros: " + listaSinistros + "\n";
        return output;
    }
    
}

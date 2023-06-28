
import java.util.ArrayList;
import java.util.Date;


public class Seguradora {
    private final String cnpj;
    private String nome;
    private String telefone;
    private String endereco;
    private String email;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;
    private ArrayList<Sinistro> listaSinistros;

    
    // Construtor
    public Seguradora(String cnpj, String nome, String telefone, String endereco, String email) {
        this.cnpj = cnpj;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.listaClientes = new ArrayList<>();
        this.listaSeguros = new ArrayList<>();
        this.listaSinistros = new ArrayList<>();
    }

    // Getters e setters
    public String getCnpj() {
        return cnpj;
    }
    
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

    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public ArrayList<Seguro> getListaSeguros() {
        return listaSeguros;
    }

    public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
        this.listaSeguros = listaSeguros;
    }
    
    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    
    // Listar clientes
    public void listarClientes(String tipoCliente) { // Recebe uma string tipoCliente como parâmetro
        for (Cliente cliente : listaClientes) { // Percorre a lista de clientes listaClientes verificando se o tipo de cliente é igual ao tipoCliente
            if (tipoCliente.equalsIgnoreCase(cliente.getClass().getSimpleName())) {  // A comparação é feita ignorando o case (maiuscula ou minuscula)
                System.out.println(cliente); // Se for igual, o método imprime as informações do cliente
            }
        }
    }
    
    
    // Método para gerar seguro
    public Seguro gerarSeguro(Date dataInicio, Date dataFim, Cliente cliente, Frota frota, int valorMensal, String tipoSeguro) {
        if (tipoSeguro.equalsIgnoreCase("PF")) {
            ClientePF clientePF = (ClientePF) cliente;
            for (Veiculo veiculo : frota.getListaVeiculos()) {
                Seguro seguro = new SeguroPF(veiculo, clientePF, dataInicio, dataFim, this, valorMensal);
                listaSeguros.add(seguro); // Adiciona o seguro à lista de seguros da seguradora
                return seguro;
            }
        } else if (tipoSeguro.equalsIgnoreCase("PJ")) {
            ClientePJ clientePJ = (ClientePJ) cliente;
            Seguro seguro = new SeguroPJ(frota, clientePJ, dataInicio, dataFim, this, valorMensal);
            listaSeguros.add(seguro); // Adiciona o seguro à lista de seguros da seguradora
            return seguro;
        } else {
            throw new IllegalArgumentException("Tipo de seguro inválido. Escolha entre PF ou PJ.");
        }
     
        throw new IllegalArgumentException("Não foi possível gerar o seguro.");
    }



    
    // Método para cancelar seguro
    public boolean cancelarSeguro(int id) {
        for (Seguro seguro : listaSeguros) {
            if (seguro.getId() == id) {
                listaSeguros.remove(seguro);
                return true; // Seguro cancelado 
            }
        }
        return false; // Seguro não está na lista
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
        

    
    // Método para obter seguros por cliente
    public ArrayList<Seguro> getSegurosPorCliente(String nomeCliente) { // Procura pelo nome do cliente
        ArrayList<Seguro> segurosCliente = new ArrayList<>(); // Lista para armazenar os seguros do cliente
        // Percorre a lista de seguros
        for (Seguro seguro : listaSeguros) {
            if (seguro instanceof SeguroPF) {
                SeguroPF seguroPF = (SeguroPF) seguro;
                if (seguroPF.getClientePF().getNome().equals(nomeCliente)) { // Verifica se o nome do cliente passado existe na lista de clientes
                    segurosCliente.add(seguro); // Seguro é adicionado na lista de seguros
                }
            } else if (seguro instanceof SeguroPJ) {
                SeguroPJ seguroPJ = (SeguroPJ) seguro;
                if (seguroPJ.getClientePJF().getNome().equals(nomeCliente)) { // Verifica se o nome do cliente passado existe na lista de clientes
                    segurosCliente.add(seguro); // Seguro é adicionado na lista de seguros
                }
            }
        }
        return segurosCliente;
    }
 
    
    // Método para obter sinistros por cliente
    public ArrayList<Sinistro> getSinistrosPorCliente(String nomeCliente) {
        ArrayList<Sinistro> sinistrosCliente = new ArrayList<>(); // Lista para armazenar os sinistros do cliente
        
        // Percorre todos os seguros da seguradora
        for (Seguro seguro : listaSeguros) {
            ArrayList<Sinistro> sinistrosSeguro = seguro.getListaSinistros(); // Obtém a lista de sinistros do seguro atual
            
            for (Sinistro sinistro : sinistrosSeguro) {// Percorre os sinistros do seguro atual
                if (sinistro.getCondutor().getNome().equalsIgnoreCase(nomeCliente)) { 
                    sinistrosCliente.add(sinistro); // Sinistro é adicionado na lista de sinistros
                }
            }
        }
        
        return sinistrosCliente;
    }
    
    // Método para encontrar um cliente na lista pelo nome
    public Cliente encontrarClientePorNome(String nome) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getNome().equalsIgnoreCase(nome)) {
                return cliente; // Retorna o cliente encontrado
            }
        }
        return null; // Retorna null caso o cliente não seja encontrado
    }


    // Método para calcular receita
    public double calcularReceita() {
        double receitaTotal = 0.0; // Variável para armazenar a receita total da seguradora
        for (Seguro seguro : listaSeguros) { // Percorre a lista de seguros
        	double precoSeguro = seguro.valorSeguro();
        	receitaTotal += precoSeguro; // Soma o preço do seguro de cada cliente à receita 
        }
        return receitaTotal;
    }
    
    
    @Override
    public String toString() {
        String output = "Dados da seguradora: " + "\n";
        output +=	"Nome: " + this.getNome() + "\n";
        output += "Telefone: " + this.getTelefone() + "\n";
        output += "Email: " + this.getEmail() + "\n";
        output += "Endereco: " + this.getEndereco() + "\n";
        output += "Lista de Clientes: " + this.getListaClientes() + "\n";
        output += "Lista de Seguros: " + this.getListaSeguros() + "\n";
        output += "Lista de Sinistros: " + this.getListaSinistros() + "\n";
        return output;
    }
    
}

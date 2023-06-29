import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
	private final String cpf;
	private String genero;
    private String educacao;
    private Date dataNascimento;
	private ArrayList <Veiculo> listaVeiculos;
	

	public ClientePF(String nome, String telefone, String endereco, String email, String cpf, String genero, String educacao, Date dataNascimento,ArrayList<Veiculo> listaVeiculos) {
		// Chama o construtor da superclasse
		super(nome, telefone, endereco, email);
		this.cpf = cpf;
		this.genero = genero;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.listaVeiculos = listaVeiculos;
	}

	// Getters e setters
	public String getCpf() {
		return cpf;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
    
    
    // Cadastro de veiculo
    public boolean cadastrarVeiculo(Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo)) {
            return false; // Se o veiculo já estiver presente na lista, o método retorna false
        }
        else {
	        listaVeiculos.add(veiculo); // Adiciona o veiculo na lista
	        return true; // Se o veiculo for novo, o método retorna true
        }
    }
    
    // Remover veiculo
    public boolean removerVeiculo(String placa) {  // Parâmetro que representa a placa do veiculo que se deseja remover
    	for (Veiculo veiculo : listaVeiculos) {
    		if (veiculo.getPlaca().equals(placa)) { // Checado se a placa do veiculo é igual ao valor passado como parâmetro
    			listaVeiculos.remove(veiculo); // Veiculo é removido da lista e o método retorna true
    			return true;
    		}
    	}
    	return false;
    }
    
    public String toCsvString() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        // Adiciona os atributos do cliente PF separados por vírgula
        sb.append(getCpf()).append(",");
        sb.append(getNome()).append(",");
        sb.append(getTelefone()).append(",");
        sb.append(getEndereco()).append(",");
        sb.append(getEmail()).append(",");
        sb.append(getGenero()).append(",");
        sb.append(getEducacao()).append(",");
        sb.append(dateFormat.format(getDataNascimento())).append(",");
        for(Veiculo veiculo : listaVeiculos) {
        	sb.append(veiculo.getPlaca());
        }
        
        // Retorna a representação CSV do cliente PF
        return sb.toString();
    }

    

    public String toString() {
    String output = "Cliente PF" + "\n";
    output += super.toString();
    output += "CPF: " + this.getCpf() + "\n";
    output += "Genero: " + this.getGenero() + "\n";
    output += "Educacao: " + this.getEducacao() + "\n";
    output += "Data de Nascimento: " + this.getDataNascimento().toString() + "\n";
    return output;
    }
}
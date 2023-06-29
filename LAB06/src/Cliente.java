
public abstract class Cliente {
	private String nome;
	private String telefone;
    private String endereco;
    private String email;

    // Construtor
    public Cliente(String nome, String telefone,String endereco, String email) {
    	this.nome = nome;
    	this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;

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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String toCsvString() {
        StringBuilder csvString = new StringBuilder();

        csvString.append(nome).append(",");
        csvString.append(telefone).append(",");
        csvString.append(endereco).append(",");
        csvString.append(email);

        return csvString.toString();
    }
    
    @Override
    public String toString() {
    String output = "Dados do cliente: " + "\n";
    output += "Nome: " + nome + "\n";
    output += "Telefone: " + telefone + "\n";
    output += "Endereco: " + endereco + "\n";
    output += "Email: " + email + "\n";
    return output;
    }
}

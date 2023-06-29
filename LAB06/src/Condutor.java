import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Condutor {
	private final String cpf;
	private String nome;
	private String telefone;
    private String endereco;
    private String email;
    private Date dataNascimento;
    private ArrayList <Sinistro> listaSinistros;
    
    // Construtor
    public Condutor(String cpf, String nome, String telefone, String endereco, String email, Date dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }
    
    // Getters e Setters
    public String getCpf() {
        return cpf;
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

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }
    
    // Método para adicionar sinistro
    public void adicionarSinistro(Sinistro sinistro, Seguro seguro) {
        if (!listaSinistros.contains(sinistro)) { // Verifica se o sinistro ainda não estiver na lista de sinistros do condutor
            listaSinistros.add(sinistro); // Adiciona o sinistro na lista de sinistros do condutor
        }

        if (!seguro.getListaSinistros().contains(sinistro)) { // Verifica se o sinistro ainda não estiver na lista de sinistros do seguro
            seguro.getListaSinistros().add(sinistro); // Adiciona o sinistro na lista de sinistros do seguro
        }
    }
    
    
    public String toCsvString() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder csvString = new StringBuilder();

        csvString.append(cpf).append(",");
        csvString.append(nome).append(",");
        csvString.append(telefone).append(",");
        csvString.append(endereco).append(",");
        csvString.append(email).append(",");
        csvString.append(dateFormat.format(dataNascimento)).append(",");
        return csvString.toString();
    }
    
    public String toString() {
        String output = "Dados do condutor: " + "\n";
        output += "CPF: " + cpf + "\n";
        output += "Nome: " + nome + "\n";
        output += "Telefone: " + telefone + "\n";
        output += "Endereço: " + endereco + "\n";
        output += "Email: " + email + "\n";
        output += "Data de Nascimento: " + dataNascimento + "\n";
        output += "Lista de Sinistros: " + listaSinistros + "\n";
        return output;
    }
}

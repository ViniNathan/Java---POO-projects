
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ClientePJ extends Cliente {
    private final String cnpj;
    private Date dataFundacao;
    private ArrayList <Frota> listaFrota;

    public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, Date dataFundacao, ArrayList<Frota> listaFrota) {
        // Chama o construtor da superclasse
        super(nome, telefone, endereco, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.listaFrota = listaFrota;
    }

    // Getters e setters
    public String getCnpj() {
        return cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }
    
    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    public void setListaFrota(ArrayList<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }

    
    // Método para cadastrar frota
    public boolean cadastrarFrota(Frota frota) {
        if (!listaFrota.contains(frota)) { // Verifica se a frota já está cadastrada na lista de frota
            listaFrota.add(frota); // Adiciona a frota à lista de frota se a frota ainda não está cadastrada
            return true; // Frota cadastrada com sucesso
        }
        return false; // Frota já cadastrada
    }
    
    
    public boolean atualizarFrota(Frota frotaAntiga, Frota frotaNova) {
    	// Se a frotaNova passada como parametro já existir na lista, a frota é totalmente removida
    	if (listaFrota.contains(frotaAntiga)) { // Verifica se a frota ja está cadastrada na lista de frota
    		int index = listaFrota.indexOf(frotaAntiga); // Obtém o índice da frota  na lista
    		if (frotaAntiga == frotaNova) {
    		listaFrota.remove(index); // Remove a frota
    		return true;
    		}
    	}
    	// Se a frota passada como parametro não for identica a alguma existente na lista, a frota é atualizada (add ou remove veiculo)
        if (listaFrota.contains(frotaAntiga)) { // Verifica se a frota antiga está cadastrada na lista de frota
            if (!listaFrota.contains(frotaNova)) { // Verifica se a frota nova ainda não está cadastrada na lista de frota
                int index = listaFrota.indexOf(frotaAntiga); // Obtém o índice da frota antiga na lista
                listaFrota.set(index, frotaNova); // Substitui a frota antiga pela nova na lista de frota (atualiza a frota)
                return true; 
            }
        }
		return false;
    }
    
    // Método  para obter veículos por frota
    public boolean getVeiculosPorFrota(String code) {
        for (Frota frota : listaFrota) {
            if (frota.getCode().equals(code)) { // Verifica se o código da frota corresponde ao código fornecido
				ArrayList<Veiculo> veiculos = frota.getListaVeiculos(); // Obtém a lista de veículos da frota correspondente
            	System.out.println("Veículos da frota: " + veiculos);
                return true;
            }
        }
        return false; // Frota não encontrada
    }
    
    public String toCsvString() {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();

        // Adiciona os atributos do cliente PF separados por vírgula
        sb.append(getCnpj()).append(",");
        sb.append(getNome()).append(",");
        sb.append(getTelefone()).append(",");
        sb.append(getEndereco()).append(",");
        sb.append(getEmail()).append(",");
        sb.append(dateFormat.format(getDataFundacao())).append(",");
        for(Frota frota : listaFrota) {
        	sb.append(frota.getCode());
        }
        
        // Retorna a representação CSV do cliente PF
        return sb.toString();
    }
   
    
    @Override
    public String toString() {
    	String output = "Cliente PJ" + "\n";
        output += super.toString();
        output += "CNPJ: " + this.getCnpj() + "\n";
        output += "Data de Fundacao: " + this.getDataFundacao().toString() + "\n";
        return output;
    }
    

    
}
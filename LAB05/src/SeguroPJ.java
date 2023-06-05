import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class SeguroPJ extends Seguro{
	private Veiculo veiculo;
	private ClientePJ clientePJ;
	private ArrayList<Condutor> listaCondutores;
	
	// Construtor
	public SeguroPJ(Veiculo veiculo, ClientePJ clientePJ, Date dataInicio, Date dataFim, Seguradora seguradora, int valorMensal) {
		super(dataInicio, dataFim, seguradora, valorMensal);
		this.veiculo = veiculo;
		this.clientePJ = clientePJ;
	}
	
	// Getters e Setters
	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public ClientePJ getClientePJF() {
		return clientePJ;
	}

	public void setClientePJ(ClientePJ clientePJ) {
		this.clientePJ = clientePJ;
	}
	
	public ArrayList<Condutor> getListaCondutores() {
        return listaCondutores;
    }

    public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
        this.listaCondutores = listaCondutores;
    }
	
    // Método para autorizar um condutor
    public boolean autorizarCondutor(Condutor condutor) {
        if (!listaCondutores.contains(condutor)) { // Verifica se o condutor não está cadastrado na lista de condutores do seguro
            listaCondutores.add(condutor);
            return true; // Condutor autorizado com sucesso
        }
        return false; // Condutor não encontrado
    }

    // Método para desautorizar um condutor
    public boolean DesautorizarCondutor(Condutor condutor) {
        if (listaCondutores.contains(condutor)) { // Verifica se o condutor não está cadastrado na lista de condutores do seguro
            listaCondutores.remove(condutor);
            return true; // Condutor desautorizado
        }
        return false; // Condutor não encontrado
    }
    
    public double calcularValor(Seguradora seguradora) {
		double valorBase = ValorSeguro.VALOR_BASE.getValor();
		double valorSeguro;

		// Calculo da idade da empresa
        Date dataFundacao = clientePJ.getDataFundacao();
        Calendar calendarFundacao = Calendar.getInstance();
        calendarFundacao.setTime(dataFundacao);
        Calendar calendarAtual = Calendar.getInstance();
        
        int AnosPosFundacao = calendarAtual.get(Calendar.YEAR) - calendarFundacao.get(Calendar.YEAR);
        
        if (calendarAtual.get(Calendar.MONTH) < calendarFundacao.get(Calendar.MONTH) ||
                (calendarAtual.get(Calendar.MONTH) == calendarFundacao.get(Calendar.MONTH) &&
                calendarAtual.get(Calendar.DAY_OF_MONTH) < calendarFundacao.get(Calendar.DAY_OF_MONTH))) {
        	AnosPosFundacao--; // Reduz um ano se ainda não completou o aniversário no ano corrente
        }

		// Obtem a quantidade de funcionarios do cliente
		int quantidadeFunc = this.getListaCondutores().size();
		valorSeguro = valorBase * (10 + ( quantidadeFunc ) /10);
		
        // Obtem a quantidade de veiculos do cliente
	    int quantidadeCarros = clientePJ.getListaFrota().size();
	    valorSeguro *= (1 + 1/(quantidadeCarros + 2));
	    
	    // Multiplica pelo fator de idade da empresa
	    valorSeguro *= (1 + 1/(AnosPosFundacao +2));
	    
	    // Obtem a quantidade de sinistros do cliente
	    int quantidadeSinistrosCliente = seguradora.getSinistrosPorCliente(clientePJ.getNome()).size();
	    valorSeguro *= (2 + quantidadeSinistrosCliente /10);
	    
	    // Obtem a quantidade de sinistros do condutor;
	    int quantidadeSinistrosCondutor = getListaCondutores().size();
	    valorSeguro *= (5 + quantidadeSinistrosCondutor /10);
	    
	    valorSeguro = valorBase * quantidadeCarros; // Calculo do valor de score
	    return valorSeguro; // Retorna o valor final do score
	}
	
	
    public String toString() {
        String output = super.toString(); 
        output += "Veiculo: " + veiculo + "\n";
        output += "Cliente PJ: " + clientePJ + "\n";
        return output;
    }
}

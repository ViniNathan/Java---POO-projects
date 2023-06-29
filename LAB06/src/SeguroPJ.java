import java.util.Calendar;
import java.util.Date;

public class SeguroPJ extends Seguro{
	private Frota frota;
	private ClientePJ clientePJ;
	
	// Construtor
	public SeguroPJ(Date dataInicio, Date dataFim, Seguradora seguradora, int valorMensal, Frota frota, ClientePJ clientePJ) {
		super(dataInicio, dataFim, seguradora, valorMensal);
		this.frota = frota;
		this.clientePJ = clientePJ;
	}
	
	// Getters e Setters
	public Frota getFrota() {
		return frota;
	}

	public void setFrota(Frota frota) {
		this.frota = frota;
	}
	
	public ClientePJ getClientePJF() {
		return clientePJ;
	}

	public void setClientePJ(ClientePJ clientePJ) {
		this.clientePJ = clientePJ;
	}
	
	
    // Método para autorizar um condutor
    public boolean autorizarCondutor(Condutor condutor) {
        if (!super.getListaCondutores().contains(condutor)) { // Verifica se o condutor não está cadastrado na lista de condutores do seguro
            super.getListaCondutores().add(condutor);
            return true; // Condutor autorizado com sucesso
        }
        return false; // Condutor não encontrado
    }

    // Método para desautorizar um condutor
    public boolean DesautorizarCondutor(Condutor condutor) {
        if (super.getListaCondutores().contains(condutor)) { // Verifica se o condutor não está cadastrado na lista de condutores do seguro
            super.getListaCondutores().remove(condutor);
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
		
        // Obtem a quantidade de frotas do cliente
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
	
	
    public String toCsvString() {
        StringBuilder csvString = new StringBuilder();
        csvString.append(getDataInicio().toString()).append(",");
        csvString.append(getDataFim().toString()).append(",");
        csvString.append(getSeguradora()).append(",");
        csvString.append(getValorMensal()).append(",");
        csvString.append("SeguroPJ");

        return csvString.toString();
    }
    
    public String toString() {
        String output = super.toString(); 
        output += "Frota: " + frota + "\n";
        output += "Cliente PJ: " + clientePJ + "\n";
        return output;
    }
}

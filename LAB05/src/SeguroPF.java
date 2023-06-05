import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SeguroPF extends Seguro{
	private Frota frota;
	private ClientePF clientePF;
	private ArrayList<Condutor> listaCondutores;
	
	// Construtor
	public SeguroPF(Frota frota, ClientePF clientePF, Date dataInicio, Date dataFim, Seguradora seguradora, int valorMensal) {
		super(dataInicio, dataFim, seguradora, valorMensal);
		this.frota = frota;
		this.clientePF = clientePF;
	}
	
	// Getters e Setters
	public Frota getFrota() {
		return frota;
	}

	public void setFrota(Frota frota) {
		this.frota = frota;
	}
	
	public ClientePF getClientePF() {
		return clientePF;
	}

	public void setEducacao(ClientePF clientePF) {
		this.clientePF = clientePF;
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

		// Calculo da idade do cliente
        Date dataNascimento = clientePF.getDataNascimento();
        Calendar calendarNascimento = Calendar.getInstance();
        calendarNascimento.setTime(dataNascimento);
        Calendar calendarAtual = Calendar.getInstance();
        
        int idade = calendarAtual.get(Calendar.YEAR) - calendarNascimento.get(Calendar.YEAR);
        
        if (calendarAtual.get(Calendar.MONTH) < calendarNascimento.get(Calendar.MONTH) ||
                (calendarAtual.get(Calendar.MONTH) == calendarNascimento.get(Calendar.MONTH) &&
                calendarAtual.get(Calendar.DAY_OF_MONTH) < calendarNascimento.get(Calendar.DAY_OF_MONTH))) {
            idade--; // Reduz um ano se ainda não completou o aniversário no ano corrente
        }

        // Considera o fator idade no calculo do valor
	    if (idade >= 18 && idade < 30) {
	    	valorBase *= ValorSeguro.FATOR_18_30.getValor(); // Multiplica o valor base pelo fator de idade para clientes entre 18 e 30 anos
	    } else if (idade >= 30 && idade <= 60) {
	    	valorBase *= ValorSeguro.FATOR_30_60.getValor(); // Multiplica o valor base pelo fator de idade para clientes entre 30 e 60 anos
	    } else if (idade > 60) {
	    	valorBase *= ValorSeguro.FATOR_60.getValor(); // Multiplica o valor base pelo fator de idade para clientes acima de 60 anos
	    }
	    
        // Obtem a quantidade de carros do cliente
	    int quantidadeCarros = clientePF.getListaVeiculos().size();
	    valorSeguro = valorBase * (1 + 1/(quantidadeCarros + 2));
	    
	    // Obtem a quantidade de sinistros do cliente
	    int quantidadeSinistrosCliente = seguradora.getSinistrosPorCliente(clientePF.getNome()).size();
	    valorSeguro *= (2 + quantidadeSinistrosCliente /10);
	    
	    // Obtem a quantidade de sinistros do condutor;
	    int quantidadeSinistrosCondutor = getListaCondutores().size();
	    valorSeguro *= (5 + quantidadeSinistrosCondutor /10);
	    
	    valorSeguro = valorBase * quantidadeCarros; // Calculo do valor do seguro
	    return valorSeguro; // Retorna o valor final do seguro
	}
	
	
	
    public String toString() {
        String output = super.toString(); 
        output += "Frota: " + frota + "\n";
        output += "Cliente PF: " + clientePF + "\n";
        return output;
    }
	
}

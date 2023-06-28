import java.util.ArrayList;
import java.util.Date;

public abstract class Seguro {
	private static int contador = 0;
	private final int id;
	private Date dataInicio;
	private Date dataFim;
	private Seguradora seguradora;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Condutor> listaCondutores;
	private double valorMensal;
	
	// Construtor
    public Seguro(Date dataInicio, Date dataFim, Seguradora seguradora, double valorMensal) {
    	this.id = contador++;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.seguradora = seguradora;
        this.valorMensal = valorMensal;
        this.listaSinistros = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public ArrayList<Sinistro> getListaSinistros() {
        return listaSinistros;
    }

    public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
        this.listaSinistros = listaSinistros;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(Double valorMensal) {
        this.valorMensal = valorMensal;
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
    
    // Método para gerar sinistro
    public boolean gerarSinistro(Date data, String endereco, Condutor condutor) {
        Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
        if (!listaSinistros.contains(sinistro)) {
        	listaSinistros.add(sinistro);
        	System.out.println(listaSinistros);
        	return true; // Sinistro gerado
        }
        	return false;
    }
    
    // Método para calcular o valor do seguro
    public double valorSeguro() {
    	double valorSeguro = 0;
    	for (Seguro seguro : seguradora.getListaSeguros()) {
        	if (seguro instanceof SeguroPF) {
        		valorSeguro = ((SeguroPF) seguro).calcularValor(seguradora);
        		this.valorMensal = valorSeguro;
        		return valorSeguro;
        	}
        	if (seguro instanceof SeguroPJ) {
        		valorSeguro = ((SeguroPJ) seguro).calcularValor(seguradora);
        		this.valorMensal = valorSeguro;
        		return valorSeguro;
        	}
    	}
    	return valorSeguro;
    }
    
    public String toString() {
        String output = "ID: " + id + "\n";
        output += "Data de Início: " + dataInicio + "\n";
        output += "Data de Fim: " + dataFim + "\n";
        output += "Seguradora: " + seguradora + "\n";
        output += "Lista de Sinistros: " + listaSinistros + "\n";
        output += "Valor Mensal: " + valorMensal + "\n";
        return output;
    }
}

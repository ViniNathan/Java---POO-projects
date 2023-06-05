
import java.util.ArrayList;

public class Frota {
	private String code;
	private ArrayList <Veiculo> listaVeiculos;
	
	// Construtor
	public Frota(String code, ArrayList <Veiculo> listaVeiculos) {
		this.code = code;
		this.listaVeiculos = listaVeiculos;
	}
	
	// Getters e Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList <Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }
	
	// Método para adicionar veiculo
    public boolean addVeiculo(Veiculo veiculo) {
        if (listaVeiculos.contains(veiculo)) {
            return false; // Se o veiculo já estiver presente na lista, o método retorna false
        }
        else {
	        listaVeiculos.add(veiculo);
	        return true; // Se o veiculo for novo, o método retorna true
        }
    }
    
    // Método para remover veiculo
    public boolean removeVeiculo(String placa) {  // Parâmetro representa a placa do veiculo que se deseja remover
    	for (Veiculo veiculo : listaVeiculos) {
    		if (veiculo.getPlaca().equals(placa)) { // Checado se a placa do veiculo é igual ao valor passado como parâmetro
    			listaVeiculos.remove(veiculo); // Veiculo é removido da lista e o método retorna true
    			return true;
    		}
    	}
    	return false;
    }
    
    public String toString() {
        String output = "---- Dados da frota ----" + "\n";
        output +=	"Código da Frota: " + code + "\n";
        output += "Lista de Veículos: " + listaVeiculos + "\n";
        return output;
    }
}

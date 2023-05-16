package labs;

public class Sinistro {
    private static Integer contador = 0; // Utilizado para que os IDS sejam unicos
    private final int ID;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    
    // Construtor
    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        this.ID = contador++;
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }

    // Getters e setters
    public Integer getId() {
        return ID;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }
    
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        String output = "ID do Sinistro: " + ID + "\n";
        output += "Data: " + data + "\n";
        output += "Endereço: " + endereco + "\n";
        if (seguradora != null) {
            output += "Seguradora: " + seguradora.getNome() + "\n";
        }
        if (veiculo != null) {
            output += "Veículo: " + veiculo.getModelo() + "\n";
        }
        if (cliente != null) {
            output += "Cliente: " + cliente.getNome() + "\n";
        }
        return output;
    }
}

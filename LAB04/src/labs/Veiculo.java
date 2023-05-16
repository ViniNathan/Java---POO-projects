package labs;

public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;

    // Construtor
    public Veiculo(String placa, String marca, String modelo, Integer anoFabricacao) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    // Getters e setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }
    
    @Override
    public String toString() {
        String output = "Veiculo:" + "\n";
        output += "Placa: " + this.getPlaca() + "\n";
        output += "Marca: " + this.getMarca() + "\n";
        output += "Ano: " + this.getAnoFabricacao() + "\n";
        output += "Modelo: " + this.getModelo() + "\n";
        return output;
    }
}

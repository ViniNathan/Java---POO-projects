import java.util.Date;

public class Sinistro {
    private static Integer contador = 0; // Utilizado para que os IDS sejam unicos
    private final int ID;
    private Date data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;
    
    // Construtor
    public Sinistro(Date data, String endereco, Condutor condutor, Seguro seguro) {
        this.ID = contador++;
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

    // Getters e setters
    public Integer getId() {
        return ID;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }
    
    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String toCsvString() {
        StringBuilder csvString = new StringBuilder();

        csvString.append(ID).append(",");
        csvString.append(data.toString()).append(",");
        csvString.append(endereco).append(",");
        if (condutor != null) {
            csvString.append(condutor.getCpf()).append(",");
        } else {
            csvString.append(",");
        }
        if (seguro != null) {
            csvString.append(seguro.getId()).append(",");
        } else {
            csvString.append(",");
        }

        // Remove a última vírgula
        csvString.deleteCharAt(csvString.length() - 1);

        return csvString.toString();
    }
    
    @Override
    public String toString() {
        String output = "ID do Sinistro: " + ID + "\n";
        output += "Data: " + data + "\n";
        output += "Endereço: " + endereco + "\n";
        if (condutor != null) {
            output += "Condutor: " + condutor.getNome() + "\n";
        }
        if (seguro != null) {
            output += "Seguro: " + seguro + "\n";
        }
        return output;
    }
}

package lab02;

public class Sinistro {
    private Integer id;
    private String data;
    private String endereco;

    public Sinistro(Integer id, String data, String endereco) {
        this.id = id;
        this.data = data;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    // Padrão de "print"
    public String toString() {
        return "Sinistro{" +
                "Id='" + id + '\'' +
                ", Data='" + data + '\'' +
                ", Endereco='" + endereco + '\'' +
                '}';
    }
}

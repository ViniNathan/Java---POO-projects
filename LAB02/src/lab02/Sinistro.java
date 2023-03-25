package lab02;

public class Sinistro {
    private Integer id;
    private String data;
    private String endereco;
    private static Integer contador = 0; // Utilizado para que os IDS sejam unicos
    
    // Construtor
    public Sinistro(String data, String endereco) {
        this.id = contador++;
        this.data = data;
        this.endereco = endereco;
    }

    // Getters e setters
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

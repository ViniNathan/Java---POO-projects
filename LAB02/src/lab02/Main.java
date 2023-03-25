package lab02;

public class Main {
    public static void main(String[] args) {
        // Instanciando um objeto da classe Seguradora
        Seguradora seguradora = new Seguradora("Seguradora X", "(11) 1234-5678", "contato@seguradorax.com", "Rua A, 123");

        // Instanciando um objeto da classe Veiculo
        Veiculo veiculo = new Veiculo("ABC1234", "Fiat", "Uno");

        // Instanciando um objeto da classe Sinistro
        Sinistro sinistro = new Sinistro("01/01/2023", "Rua B, 456");

        // Instanciando um objeto da classe Cliente
        Cliente cliente = new Cliente("Fulano da Silva", "123.456.789-00", "01/01/1980", 43, "Rua C, 789");

        // Imprimindo informações
        System.out.println(seguradora);
        System.out.println(veiculo);
        System.out.println(sinistro);
        System.out.println(cliente);

        // Validando o CPF do cliente
        if (cliente.validarCPF()) {
            System.out.println("O CPF fornecido pelo cliente é válido");
        } else {
            System.out.println("O CPF fornecido pelo cliente é inválido");
        }
    }
}

package labs;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		// Instanciando um objeto Seguradora
		Seguradora seguradora = new Seguradora("Seguradora X", "(11) 1234-5678", "contato@seguradorax.com", "Rua X, 123");
		// Instanciando um objeto ClientePF
		ClientePF cliente1 = new ClientePF("Fulano de Tal", "Rua A, 123", new Date(), "Superior completo", "Masculino", "Classe média", new ArrayList<Veiculo>(), "347.146.816-13", new Date());
		// Criando um objeto ClientePJ
		ClientePJ cliente2 = new ClientePJ("Empresa A", "Rua B, 456", new Date(), "Superior completo", "Feminino", "Classe alta", new ArrayList<Veiculo>(), "26.372.837/0001-55", new Date());
		// Adicionando os clientes à lista
		seguradora.cadastrarCliente(cliente1);
		seguradora.cadastrarCliente(cliente2);
		// Removendo um cliente da lista
		seguradora.removerCliente(cliente1.getCpf());
		seguradora.removerCliente(cliente2.getCnpj());
		
        
        // Instanciando objetos Veiculo
        Veiculo veiculo1 = new Veiculo("ABC1234", "Fiat", "Uno", 2012);
        Veiculo veiculo2 = new Veiculo("XYZ6789", "Chevrolet", "Onix", 2018);
        // Adicionando os veiculos à lista de veiculos dos clientes 1 e 2
        cliente1.getListaVeiculos().add(veiculo1);
        cliente2.getListaVeiculos().add(veiculo2);
        
        // Cadastrando 2 clientes na seguradora, sendo um PF e outro PJ
    	seguradora.cadastrarCliente(cliente1);
		seguradora.cadastrarCliente(cliente2);
		
		// Gerando 2 sinistros
		Sinistro sinistro1 = new Sinistro("10/10/2010", cliente1.getEndereco(), seguradora, veiculo1, cliente1);
		Sinistro sinistro2 = new Sinistro("10/10/2010", cliente2.getEndereco(), seguradora, veiculo2, cliente2);
		seguradora.gerarSinistro(sinistro1);
		seguradora.gerarSinistro(sinistro2);
		
		
		
		while (true) {
			// Exibe o menu textual
		    System.out.println("Escolha uma opção:");
		    System.out.println("1 - Ver dados da seguradora");
		    System.out.println("2 - Ver dados do veiculo1");
		    System.out.println("3 - Ver dados do veiculo2");
		    System.out.println("4 - Ver dados do clientePF");
		    System.out.println("5 - Ver dados do clientePJ");
		    System.out.println("6 - Ver dados do sinistro1");
		    System.out.println("7 - Validador CPF");
		    System.out.println("8 - Validador CNPJ");
		    // Lê a opção escolhida pelo usuário
		    int opcao = scanner.nextInt();
		    // Exibe o conteúdo de acordo com a opção escolhida
		    switch (opcao) {
		      case 1:
		    	  // Exibe dados da seguradora
		    	  System.out.println(seguradora);
		    	  break;
		      case 2:
		    	  // Exibe dados do veiculo
		    	  System.out.println(veiculo1);
		    	  break;
		      case 3:
		    	  // Exibe dados do veiculo
		    	  System.out.println(veiculo2);
		    	  break;
		      case 4:
		    	  System.out.println(cliente1);
				  break;
		      case 5:
		    	  System.out.println(cliente2);
				  break;
		      case 6:
		    	  System.out.println(sinistro1);
				  break;
		      case 7:
		   	      // Código para validar CPF
		    	  cliente1.validarCPF();
		      case 8:
		    	  // Código para validar CNPJ
		    	  cliente2.validarCNPJ(cliente2.getCnpj());
		    	  break;
		      default:
		        System.out.println("Opção inválida");
		    }
		}
	}
}
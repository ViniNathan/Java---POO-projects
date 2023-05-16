package labs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha;
        Date dataNascimento = new Date();
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        // Instanciando um objeto Seguradora
        Seguradora seguradora = new Seguradora("Seguradora X", "(11) 1234-5678", "contato@seguradorax.com", "Rua X, 123");
        List<Seguradora> listaSeguradoras = new ArrayList<>(); // Lista de seguradoras
        listaSeguradoras.add(seguradora); // Adiciona a seguradora a lista de seguradoras
        // Instanciando um objeto ClientePF
        double valorSeguroPF = 0.0;
        ClientePF cliente1 = new ClientePF("Fulano de Tal", "Rua A, 123", new Date(), "Superior completo", "Masculino", "Classe média", new ArrayList<Veiculo>(), "347.146.816-13", dataNascimento, valorSeguroPF);
        try {
            Date data = formatoData.parse("01/01/2000");
            cliente1.setDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Criando um objeto ClientePJ
        double valorSeguroPJ = 0.0;
        ClientePJ cliente2 = new ClientePJ("Empresa A", "Rua B, 456", new Date(), "Superior completo", "Feminino", "Classe alta", new ArrayList<Veiculo>(), "26.372.837/0001-55", new Date(), valorSeguroPJ, 60);
        
        // Instanciando objetos Veiculo
        Veiculo veiculo1 = new Veiculo("ABC1234", "Fiat", "Uno", 2012);
        Veiculo veiculo2 = new Veiculo("XYZ6789", "Chevrolet", "Onix", 2018);
        // Adicionando os veiculos à lista de veiculos dos clientes 1 e 2
        cliente1.getListaVeiculos().add(veiculo1);
        cliente2.getListaVeiculos().add(veiculo2);
 
		// Adicionando os clientes à seguradora
		seguradora.cadastrarCliente(cliente1);
		seguradora.cadastrarCliente(cliente2);
        
		// Gerando sinistros
		Sinistro sinistro1 = new Sinistro("10/10/2010", cliente1.getEndereco(), seguradora, veiculo1, cliente1);
		Sinistro sinistro2 = new Sinistro("10/10/2010", cliente2.getEndereco(), seguradora, veiculo2, cliente2);
		seguradora.gerarSinistro(sinistro1);
		seguradora.gerarSinistro(sinistro2);
		
		// Atualizando o valor do seguro dos clientes
        valorSeguroPF = seguradora.calcularPrecoSeguroCliente(cliente1);
        cliente1.setValorSeguro(valorSeguroPF);
        valorSeguroPJ = seguradora.calcularPrecoSeguroCliente(cliente2);
        cliente2.setValorSeguro(valorSeguroPJ);

        
        do {
            exibirMenuPrincipal();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Executar a operação escolhida
            switch (escolha) {
                case 1:
                    menuCadastro(scanner, seguradora, cliente1, listaSeguradoras);
                    break;
                case 2:
                    menuListar(scanner, seguradora, cliente1, listaSeguradoras);
                    break;
                case 3:
                    menuExcluir(scanner, cliente1, seguradora);
                    break;
                case 4:
                    gerarSinistro(seguradora, veiculo1, cliente1);
                    break;
                case 5:
                    transferirSeguro(cliente1, seguradora, veiculo1);
                    break;
                case 6:
                    calcularReceitaSeguradora(seguradora);                
                    break;
                case 0:
                    sair();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 0);
        
        scanner.close();
    }
    
    private static void exibirMenuPrincipal() {
        System.out.println("----- Menu de Operações -----");
        for (MenuOperacoes operacao : MenuOperacoes.values()) {
            System.out.println(operacao.getOperacao() + " - " + operacao.name());
        }
        System.out.println("-----------------------------");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void gerarSinistro(Seguradora seguradora, Veiculo veiculo, Cliente cliente) {
        System.out.println("Opção 4 - Gerar Sinistro");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Data: ");
        String data = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        Sinistro sinistro = new Sinistro (data, endereco, seguradora, veiculo, cliente);
        
        seguradora.gerarSinistro(sinistro);
        System.out.println("Sinistro gerado com sucesso");
    }

    private static void transferirSeguro(Cliente cliente, Seguradora seguradora, Veiculo veiculo) {
        System.out.println("Opção 5 - Transferir Seguro");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nome do cliente que irá transferir o seguro: ");
        String nome1 = scanner.nextLine();
        System.out.print("Nome do cliente que irá receber o seguro: ");
        String nome2 = scanner.nextLine();
        
        for (Cliente cliente1 : seguradora.getListaClientes()) { // Percorre a lista de clientes da seguradora para encontrar o cliente de origem do seguro
        	for (Cliente cliente2 : seguradora.getListaClientes()) { // Percorre a lista de clientes da seguradora para encontrar o cliente de destino do seguro
        		if (cliente1.getNome().equals(nome1) && cliente2.getNome().equals(nome2)) {
        			for (Veiculo veiculo1 : cliente1.getListaVeiculos()) {
        				cliente2.getListaVeiculos().add(veiculo1); // Copia a lista de veiculos para o cliente destino
        				cliente2.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cliente2)); // Atualiza o valor do seguro do cliente destino

        			}
        			cliente1.removerVeiculo(veiculo.getPlaca()); // Remove a lista de veiculos do cliente origem
        			cliente1.setValorSeguro(0.0); // Atualiza o valor do seguro do cliente origem
        		}
        	}				 
        }
        
       
    }

    private static void calcularReceitaSeguradora(Seguradora seguradora) {
        System.out.println("Opção 6 - Calcular Receita da Seguradora");
        Double receita = seguradora.calcularReceita();
        System.out.println(receita);
    }

    private static void sair() {
        System.out.println("Opção 0 - Sair");
        System.out.println("PROGRAMA ENCERRADO");
    }
    
    private static void menuCadastro(Scanner scanner, Seguradora seguradora, Cliente cliente, List<Seguradora> listaSeguradoras) {
        int escolha;

        do {
            exibirMenuCadastro();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Executar a operação de cadastro escolhida
            switch (escolha) {
                case 1:
                    MenuCadastrarCliente(scanner, seguradora);
                    break;
                case 2:	
					cadastrarVeiculo(cliente);
                    break;
                case 3:
                    cadastrarSeguradora(listaSeguradoras);
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 4);
    }
    
    private static void exibirMenuCadastro() {
        System.out.println("----- Menu de Cadastro -----");
        System.out.println("1 - Cadastrar Cliente PF/PJ");
        System.out.println("2 - Cadastrar Veiculo");
        System.out.println("3 - Cadastrar Seguradora");
        System.out.println("4 - Voltar");
        System.out.print("Escolha uma opção: ");
    }

    private static void MenuCadastrarCliente(Scanner scanner, Seguradora seguradora) {
    	int escolha;
    	
    	do {
    		System.out.println("----- Cadastrar Cliente PF/PJ -----");
            System.out.println("1 - Cadastrar Cliente PF");
            System.out.println("2 - Cadastrar Cliente PJ");
            System.out.println("3 - Voltar");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            switch (escolha) {
            case 1:
                cadastrarClientePF(seguradora);
                break;
            case 2:
                cadastrarClientePJ(seguradora);
                break;
            
            case 3:
                System.out.println("Voltando ao menu anterior");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
            }
    	} while (escolha != 3);
    }

    private static void cadastrarClientePF(Seguradora seguradora) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Cadastro de Cliente PF -----");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Data de Licença (dd/mm/aaaa): ");
        String dataLicencaStr = scanner.nextLine();
        Date dataLicenca = parseData(dataLicencaStr);

        System.out.print("Educação: ");
        String educacao = scanner.nextLine();

        System.out.print("Gênero: ");
        String genero = scanner.nextLine();

        System.out.print("Classe Econômica: ");
        String classeEconomica = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Data de Nascimento (dd/mm/aaaa): ");
        String dataNascimentoStr = scanner.nextLine();
        Date dataNascimento = parseData(dataNascimentoStr);

        // Cria a lista de veículos vazia para o cliente PF
        List<Veiculo> listaVeiculos = new ArrayList<>();

        // Cria o objeto ClientePF com os dados fornecidos
        Double valorSeguro = 0.0;
        ClientePF clientePF = new ClientePF(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos, cpf, dataNascimento, valorSeguro);
        valorSeguro = seguradora.calcularPrecoSeguroCliente(clientePF);

		// Adiciona o cliente PF à lista de clientes da seguradora
        seguradora.cadastrarCliente(clientePF);

        System.out.println("Cliente PF cadastrado com sucesso!");

    }
		

	private static void cadastrarClientePJ(Seguradora seguradora) {
	    Scanner scanner = new Scanner(System.in);
	    
	
	    System.out.println("----- Cadastro de Cliente PJ -----");
	    System.out.print("Nome da empresa: ");
	    String nome = scanner.nextLine();
	
	    System.out.print("Endereço: ");
	    String endereco = scanner.nextLine();
	
	    System.out.print("Data de Licença (dd/mm/aaaa): ");
	    String dataLicencaStr = scanner.nextLine();
	    Date dataLicenca = parseData(dataLicencaStr);
	
	    System.out.print("Educação: ");
	    String educacao = scanner.nextLine();
	
	    System.out.print("Gênero: ");
	    String genero = scanner.nextLine();
	
	    System.out.print("Classe Econômica: ");
	    String classeEconomica = scanner.nextLine();
	
	    System.out.print("CNPJ: ");
	    String cnpj = scanner.nextLine();
	
	    System.out.print("Data de Fundação (dd/mm/aaaa): ");
	    String dataFundacaoStr = scanner.nextLine();
	    Date dataFundacao = parseData(dataFundacaoStr);
	
	    System.out.print("Quantidade de Funcionários: ");
	    int qtdeFuncionarios = scanner.nextInt();
	
	    // Cria a lista de veículos vazia para o cliente PJ
	    List<Veiculo> listaVeiculos = new ArrayList<>();
	
	    // Cria o objeto ClientePJ com os dados fornecidos
	    Double valorSeguro = 0.0;
	    ClientePJ clientePJ = new ClientePJ(nome, endereco, dataLicenca, educacao, genero, classeEconomica, listaVeiculos, cnpj, dataFundacao, valorSeguro, qtdeFuncionarios);
	    valorSeguro = seguradora.calcularPrecoSeguroCliente(clientePJ);
	    
	    // Adiciona o cliente PJ à lista de clientes da seguradora
	    seguradora.cadastrarCliente(clientePJ);

	    System.out.println("Cliente PJ cadastrado com sucesso!");

	}

	private static Date parseData(String dataStr) {
	    DateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	    try {
	        return formatoData.parse(dataStr);
	    } catch (ParseException e) {
	        System.out.println("Formato de data inválido. Utilize o formato dd/mm/aaaa.");
	        return null;
	    }
	}


	private static void cadastrarVeiculo(Cliente cliente) {
        Scanner scanner = new Scanner(System.in);
    	
        System.out.println("Opção 2 - Cadastrar Veiculo");
	    System.out.print("Placa: ");
	    String placa = scanner.nextLine();
	
	    System.out.print("Marca: ");
	    String marca = scanner.nextLine();
	
	    System.out.print("Modelo: ");
	    String modelo = scanner.nextLine();
	
	    System.out.print("Ano de fabricação: ");
	    int anoFabricacao = scanner.nextInt();

	    // Crie o objeto veiculo com os dados fornecidos
	    Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
	    // Adicione o veiculo à lista de veiculos do cliente
	    cliente.getListaVeiculos().add(veiculo);

    }

    private static void cadastrarSeguradora(List<Seguradora> listaSeguradoras) {
        System.out.println("Opção 3 - Cadastrar Seguradora");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nome: ");
	    String nome = scanner.nextLine();
	
	    System.out.print("Telefone: ");
	    String telefone = scanner.nextLine();
	
	    System.out.print("Email: ");
	    String email = scanner.nextLine();
	
	    System.out.print("Endereço: ");
	    String endereco = scanner.nextLine();
        
	    Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
	    listaSeguradoras.add(seguradora);

    }

    private static void menuListar(Scanner scanner, Seguradora seguradora, Cliente cliente, List <Seguradora> listaSeguradoras) {
        int escolha;

        do {
            exibirMenuListar();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Executar a operação de cadastro escolhida
            switch (escolha) {
                case 1:
                    listarClientesSeguradora(seguradora);
                    break;
                case 2:
                    listarSinistrosSeguradora(seguradora, listaSeguradoras);
                    break;
                case 3:
                    listarSinistrosCliente(seguradora, cliente);
                    break;
                case 4:
                    listarVeiculosCliente(seguradora, cliente);
                    break;
                case 5:
                    listarVeiculosSeguradora(seguradora, listaSeguradoras, cliente);
                    break;
                case 6:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 6);
    }
    
    private static void exibirMenuListar() {
        System.out.println("----- Menu de Listagens -----");
        System.out.println("1 - Listar Cliente PF/PJ por Seguradora");
        System.out.println("2 - Listar Sinistros por Seguradora");
        System.out.println("3 - Listar Sinistros por Cliente");
        System.out.println("4 - Listar Veiculos por Cliente");
        System.out.println("5 - Listar Veiculos por Seguradora");
        System.out.println("6 - Voltar");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void listarClientesSeguradora(Seguradora seguradora) {
        System.out.println("Opção 1 - Listar Cliente PF/PJ por Seguradora");
        seguradora.listarClientes("ClientePF");
        seguradora.listarClientes("ClientePJ");
    }

    private static void listarSinistrosSeguradora(Seguradora seguradora, List <Seguradora> listaSeguradoras) {
        System.out.println("Opção 2 - Listar Sinistros por Seguradora");
        for(Seguradora seguradora1 : listaSeguradoras) {
        System.out.println(seguradora1.listarSinistros());
        }
    }

    private static void listarSinistrosCliente(Seguradora seguradora, Cliente cliente) {
        System.out.println("Opção 3 - Listar Sinistros por Cliente");
        for(Cliente cliente1 : seguradora.getListaClientes()) {
        	seguradora.visualizarSinistro(cliente1.getNome());
        }
    }
    
    private static void listarVeiculosCliente(Seguradora seguradora, Cliente cliente) {
        System.out.println("Opção 4 - Listar Veiculos por Cliente");
        for(Cliente cliente1 : seguradora.getListaClientes()) {
        	System.out.println("Cliente: " + cliente1.getNome());
        	System.out.println(cliente1.getListaVeiculos());
        }
    }
    
    private static void listarVeiculosSeguradora(Seguradora seguradora, List <Seguradora> listaSeguradoras, Cliente cliente) {
        System.out.println("Opção 5 - Listar Veiculos por Seguradora");
        for(Seguradora seguradora1 : listaSeguradoras) {
        	System.out.println(seguradora1.getNome());
	        for(Cliente cliente1 : seguradora.getListaClientes()) {
	        	System.out.println(cliente1.getListaVeiculos());
	        }
        }
    }
    
    private static void menuExcluir(Scanner scanner, Cliente cliente, Seguradora seguradora) {
        int escolha;

        do {
            exibirMenuExcluir();
            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner

            // Executar a operação de cadastro escolhida
            switch (escolha) {
                case 1:
                    excluirCliente(seguradora);
                    break;
                case 2:
                    excluirVeiculo(cliente);
                    break;
                case 3:
                    excluirSinistro(seguradora);
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (escolha != 4);
    }
    
    private static void exibirMenuExcluir() {
        System.out.println("----- Menu para Excluir -----");
        System.out.println("1 - Excluir Cliente");
        System.out.println("2 - Excluir Veiculo");
        System.out.println("3 - Excluir Sinistro");
        System.out.println("4 - Voltar");
        System.out.print("Escolha uma opção: ");
    }

    private static void excluirCliente(Seguradora seguradora) {
        System.out.println("Opção 1 - Excluir Cliente");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Nome do cliente a ser removido: ");
	    String nome = scanner.nextLine();
	    
        seguradora.removerCliente(nome);
        System.out.println("Cliente " + nome + " removido com sucesso");
    }

    private static void excluirVeiculo(Cliente cliente) {
        System.out.println("Opção 2 - Excluir Veiculo");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Placa do veiculo a ser removido: ");
        String placa = scanner.nextLine();
        
        cliente.removerVeiculo(placa);
        System.out.println("Veiculo " + placa + " removido com sucesso");
    }

    private static void excluirSinistro(Seguradora seguradora) {
        System.out.println("Opção 3 - Excluir Sinistro");
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("ID do sinistro a ser removido: ");
	    int ID = scanner.nextInt();
	    
        seguradora.removerSinistro(ID);
        System.out.println("Sinistro " + ID + " removido com sucesso");
    }
}
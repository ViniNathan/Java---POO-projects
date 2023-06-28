import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AppMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int escolha;
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        
        // Instanciando um objeto Seguradora
        Seguradora seguradora = new Seguradora("26.372.837/0001-55", "Seguradora XYZ", "(87) 98137-6381", "Rua ABC, 123", "seguradora@xyz.com");
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<>(); // Lista de seguradoras
        listaSeguradoras.add(seguradora); // Adiciona a seguradora a lista de seguradoras
        
        // Instanciando um objeto ClientePF
        Date dataNascimento = new Date();
        ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
        ClientePF cliente1 = new ClientePF("Fulano de Tal", "(81) 98774-1420", "Rua A, 123", "clientepf@gmail.com", "347.146.816-13", "Masculino", "Superior completo", dataNascimento, listaVeiculos);
        try {
            Date data = formatoData.parse("01/01/2000");
            cliente1.setDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // Instanciando um objeto ClientePJ
        ArrayList<Frota> listaFrota = new ArrayList<>();
        Date dataFundacao = new Date();
        ClientePJ cliente2 = new ClientePJ("Empresa A", "(63) 98521-5457", "Rua B, 456", "clientepj@empresa.com", "26.372.837/0001-55", dataFundacao, listaFrota);
        try {
            Date dataFundacao1 = formatoData.parse("03/05/1988");
            cliente2.setDataFundacao(dataFundacao1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // Instanciando objetos Veiculo
        Veiculo veiculo1 = new Veiculo("ABC1234", "Fiat", "Uno", 2012);
        Veiculo veiculo2 = new Veiculo("XYZ6789", "Chevrolet", "Onix", 2018);
        Veiculo veiculo3 = new Veiculo("OIZ1789", "Ford", "Fusion", 2022);
        Veiculo veiculo4 = new Veiculo("AUI2819", "Ford", "KA", 2015);
        
        
        // Instanciando objetos Condutor
        Date dataNascimento1 = new Date();
        ArrayList<Sinistro> listaSinistros1 = new ArrayList<>();
        Condutor condutor1 = new Condutor("347.146.816-13", "João da Silva", "(69) 97114-1238", "Rua C, 123", "condutor1@gmail.com", dataNascimento1, listaSinistros1);
        try {
            Date data = formatoData.parse("03/05/1990");
            condutor1.setDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        Date dataNascimento2 = new Date();
        ArrayList<Sinistro> listaSinistros2 = new ArrayList<>();
        Condutor condutor2 = new Condutor("924.388.454-90", "Pedro Oliveira", "(48) 99372-5617", "Rua D, 123", "condutor2@gmail.com", dataNascimento2, listaSinistros2);
        try {
            Date data = formatoData.parse("01/09/1978");
            condutor2.setDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        Date dataNascimento3 = new Date();
        ArrayList<Sinistro> listaSinistros3 = new ArrayList<>();
        Condutor condutor3 = new Condutor("275.757.544-90", "Renata Maria", "(93) 98152-0816", "Rua E, 123", "condutor3@gmail.com", dataNascimento3, listaSinistros3);
        try {
            Date data = formatoData.parse("15/09/1995");
            condutor3.setDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        
        Date dataNascimento4 = new Date();
        ArrayList<Sinistro> listaSinistros4 = new ArrayList<>();
        Condutor condutor4 = new Condutor("676.842.704-00", "Sophia Elias", "(86) 98839-2446", "Rua F, 123", "condutor4@gmail.com", dataNascimento4, listaSinistros4);
        try {
            Date data = formatoData.parse("22/11/2000");
            condutor4.setDataNascimento(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        

        // Instanciando objetos Frota
        ArrayList<Veiculo> frotaPF = new ArrayList<>();
        frotaPF.add(veiculo1);
        
        ArrayList<Veiculo> frotaVeiculos = new ArrayList<>();
        frotaVeiculos.add(veiculo2);
        frotaVeiculos.add(veiculo3);
        frotaVeiculos.add(veiculo4);
        Frota frota = new Frota("Frota de viagens", frotaVeiculos);
        Frota frota1 = new Frota("Frota PF", frotaPF);
        
        // Adicionando os veiculos à lista de veiculos dos clientes 1 e 2
        cliente1.getListaVeiculos().add(veiculo1);
        cliente2.cadastrarFrota(frota);

		// Adicionando os clientes à seguradora
		seguradora.cadastrarCliente(cliente1);
		seguradora.cadastrarCliente(cliente2);
        
		// Gerando seguro e sinistros
		int valorMensal = 0; // TEM QUE MEXER NESSA PORRA AQUI !!!!!!!!!!!!!!!
		Date dataInicio = new Date();
		Date dataFim = new Date();
		try {
			dataInicio = formatoData.parse("22/04/2023");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		try {
			dataFim = formatoData.parse("22/04/2028");
		} catch (ParseException e) {
			e.printStackTrace();
		}

        ArrayList<Condutor> listaCondutores = new ArrayList<>(); // Adicionando os condutores na lista de condutores
        listaCondutores.add(condutor2);
        listaCondutores.add(condutor3);
        listaCondutores.add(condutor4);
        
		Seguro seguroPF = seguradora.gerarSeguro(dataInicio, dataFim, cliente1, frota1, valorMensal, "PF"); // Gerando um seguro PF
		seguroPF.gerarSinistro(dataInicio, "Rua Teste, 1209", condutor1);
		
		Seguro seguroPJ = seguradora.gerarSeguro(dataInicio, dataFim, cliente2, frota, valorMensal, "PJ"); // Gerando um seguro PF
		seguroPJ.gerarSinistro(dataInicio, "Rua Teste, 1209", condutor2);
		seguroPJ.gerarSinistro(dataInicio, "Rua Oliveira da Silva, 439", condutor3);
		seguroPJ.gerarSinistro(dataInicio, "Rua São Bartolomeu, 23", condutor4);
		
		
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
                    gerarSinistro(seguradora, condutor1, seguroPF);
                    break;
                case 5:
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
    
    private static void gerarSinistro(Seguradora seguradora, Condutor condutor1, Seguro seguro1) {
        System.out.println("Opção 4 - Gerar Sinistro");
        try (Scanner scanner = new Scanner(System.in)) {
			System.out.print("Data: ");
			String dataStr = scanner.nextLine();
			Date data = parseData(dataStr);
			
			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();
  
			seguro1.gerarSinistro(data, endereco, condutor1);
		}
      
        System.out.println("Sinistro gerado com sucesso");
    }

    private static void calcularReceitaSeguradora(Seguradora seguradora) {
        System.out.println("Opção 5 - Calcular Receita da Seguradora");
        Double receita = seguradora.calcularReceita();
        System.out.println(receita);
    }

    private static void sair() {
        System.out.println("Opção 0 - Sair");
        System.out.println("PROGRAMA ENCERRADO");
    }
    
    private static void menuCadastro(Scanner scanner, Seguradora seguradora, Cliente cliente, ArrayList<Seguradora> listaSeguradoras) {
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
					cadastrarVeiculo(scanner, cliente);
                    break;
                case 3:
                    cadastrarSeguradora(scanner, listaSeguradoras);
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
                cadastrarClientePF(scanner, seguradora);
                break;
            case 2:
                cadastrarClientePJ(scanner, seguradora);
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

    private static void cadastrarClientePF(Scanner scanner, Seguradora seguradora) {
			System.out.println("----- Cadastro de Cliente PF -----");
			System.out.print("Nome: ");
			String nome = scanner.nextLine();

			System.out.print("Telefone: ");
			String telefone = scanner.nextLine();
			
			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();
			
			System.out.print("Email: ");
			String email = scanner.nextLine();
			
			System.out.print("CPF: ");
			String cpf = scanner.nextLine();
			
			System.out.print("Gênero: ");
			String genero = scanner.nextLine();
			
			System.out.print("Educação: ");
			String educacao = scanner.nextLine();
			
			System.out.print("Data de Nascimento (dd/mm/aaaa): ");
			String dataNascimentoStr = scanner.nextLine();
			Date dataNascimento = parseData(dataNascimentoStr);

			// Cria a lista de veículos vazia para o cliente PF
			ArrayList<Veiculo> listaVeiculos = new ArrayList<>();

			// Cria o objeto ClientePF com os dados fornecidos
			ClientePF clientePF = new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNascimento, listaVeiculos);

			// Adiciona o cliente PF à lista de clientes da seguradora
			seguradora.cadastrarCliente(clientePF);

        System.out.println("Cliente PF cadastrado com sucesso!");

    }
		

	private static void cadastrarClientePJ(Scanner scanner, Seguradora seguradora) {
			System.out.println("----- Cadastro de Cliente PJ -----");
			System.out.print("Nome da empresa: ");
			String nome = scanner.nextLine();
			
			System.out.print("Telefone: ");
			String telefone = scanner.nextLine();

			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();

			System.out.print("Email: ");
			String email = scanner.nextLine();
			
			System.out.print("CNPJ: ");
			String cnpj = scanner.nextLine();

			System.out.print("Data de Fundação (dd/mm/aaaa): ");
			String dataFundacaoStr = scanner.nextLine();
			Date dataFundacao = parseData(dataFundacaoStr);
			
			// Cria a lista de veículos vazia para o cliente PJ
			ArrayList<Frota> listaFrota = new ArrayList<>();

			// Cria o objeto ClientePJ com os dados fornecidos
			ClientePJ clientePJ = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, listaFrota);	    
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

	private static void cadastrarVeiculo(Scanner scanner, Cliente cliente) {
			System.out.println("Opção 2 - Cadastrar Veiculo");
			System.out.print("Placa: ");
			String placa = scanner.nextLine();

			System.out.print("Marca: ");
			String marca = scanner.nextLine();

			System.out.print("Modelo: ");
			String modelo = scanner.nextLine();

			System.out.print("Ano de fabricação: ");
			int anoFabricacao = scanner.nextInt();

			// Cria o objeto veiculo com os dados fornecidos
			Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
			// Adiciona o veiculo à lista de veiculos do cliente
			if (cliente instanceof ClientePF) {
				ClientePF clientePF = (ClientePF) cliente; // Faz a conversão do tipo Cliente para ClientePF
				clientePF.getListaVeiculos().add(veiculo);
			}
			if (cliente instanceof ClientePJ) {
				ClientePJ clientePJ = (ClientePJ) cliente; // Faz a conversão do tipo Cliente para ClientePJ
			    System.out.print("Código da frota: ");
			    String code = scanner.nextLine();
			    ArrayList<Veiculo> listaVeiculos = new ArrayList<>();
			    listaVeiculos.add(veiculo);
			    Frota frota = new Frota(code, listaVeiculos);
				clientePJ.getListaFrota().add(frota);
			}

    }

    private static void cadastrarSeguradora(Scanner scanner, ArrayList<Seguradora> listaSeguradoras) {
        System.out.println("Opção 3 - Cadastrar Seguradora");
			System.out.print("Cnpj: ");
			String cnpj = scanner.nextLine();
			
			System.out.print("Nome: ");
			String nome = scanner.nextLine();

			System.out.print("Telefone: ");
			String telefone = scanner.nextLine();

			System.out.print("Endereço: ");
			String endereco = scanner.nextLine();
			
			System.out.print("Email: ");
			String email = scanner.nextLine();
			
			Seguradora seguradora = new Seguradora(cnpj, nome, telefone, email, endereco);
			listaSeguradoras.add(seguradora);

    }

    private static void menuListar(Scanner scanner, Seguradora seguradora, Cliente cliente, ArrayList <Seguradora> listaSeguradoras) {
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
                    listarSinistrosCliente(seguradora);
                    break;
                case 4:
                    listarVeiculosCliente(seguradora);
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

    private static void listarSinistrosSeguradora(Seguradora seguradora, ArrayList <Seguradora> listaSeguradoras) {
        System.out.println("Opção 2 - Listar Sinistros por Seguradora");
        for(Seguradora seguradora1 : listaSeguradoras) {
        	System.out.println(seguradora1.getListaSinistros());
        }
    }

    private static void listarSinistrosCliente(Seguradora seguradora) {
        System.out.println("Opção 3 - Listar Sinistros por Cliente");
        for(Cliente cliente : seguradora.getListaClientes()) {
        	System.out.println(seguradora.getSinistrosPorCliente(cliente.getNome()));
        }
    }
    
    
    private static void listarVeiculosCliente(Seguradora seguradora) {
        System.out.println("Opção 4 - Listar Veiculos por Cliente");
        for(Cliente cliente : seguradora.getListaClientes()) {
        	System.out.println("Cliente: " + cliente.getNome());
        	if (cliente instanceof ClientePF) {
    	    	ClientePF clientePF = (ClientePF) cliente; // Faz a conversão do tipo Cliente para ClientePF
    	    	System.out.println(clientePF.getListaVeiculos());
    	    }
        	if (cliente instanceof ClientePJ) {
    	    	ClientePJ clientePJ = (ClientePJ) cliente; // Faz a conversão do tipo Cliente para ClientePJ
    	    	ArrayList <Frota> ListaFrota = clientePJ.getListaFrota();
    	    	for(Frota frota : ListaFrota) {
    	    		System.out.println(frota.getListaVeiculos());
    	    	}
        	}
        }
    }
    
    private static void listarVeiculosSeguradora(Seguradora seguradora, ArrayList <Seguradora> listaSeguradoras, Cliente cliente) {
        System.out.println("Opção 5 - Listar Veiculos por Seguradora");
        for(Seguradora seguradora1 : listaSeguradoras) {
        	System.out.println(seguradora1.getNome());
	        for(Cliente cliente1 : seguradora.getListaClientes()) {
	        	if (cliente1 instanceof ClientePF) {
	    	    	ClientePF clientePF = (ClientePF) cliente1; // Faz a conversão do tipo Cliente para ClientePF
	    	    	System.out.println(clientePF.getListaVeiculos());
	    	    }
	        	else if (cliente1 instanceof ClientePJ) {
	    	    	ClientePJ clientePJ = (ClientePJ) cliente1; // Faz a conversão do tipo Cliente para ClientePJ
	    	    	ArrayList <Frota> ListaFrota = clientePJ.getListaFrota();
	    	    	for(Frota frota : ListaFrota) {
	    	    		System.out.println(frota.getListaVeiculos());
	    	    	}
	        	}
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
                    excluirCliente(scanner, seguradora);
                    break;
                case 2:
                    excluirVeiculo(scanner, cliente);
                    break;
                case 3:
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

    private static void excluirCliente(Scanner scanner, Seguradora seguradora) {
        System.out.println("Opção 1 - Excluir Cliente");
			System.out.println("Nome do cliente a ser removido: ");
			String nome = scanner.nextLine();
			Cliente cliente = seguradora.encontrarClientePorNome(nome);
			if(seguradora.getListaClientes().contains(cliente)) {
				seguradora.removerCliente(nome);
				System.out.println("Cliente " + nome + " removido com sucesso");
			} else {
				System.out.println("Cliente não encontrado.");
			}
			
    }

    private static void excluirVeiculo(Scanner scanner, Cliente cliente) {
        System.out.println("Opção 2 - Excluir Veiculo");
			System.out.println("Placa do veiculo a ser removido: ");
			String placa = scanner.nextLine();
			if (cliente instanceof ClientePF) {
				ClientePF clientePF = (ClientePF) cliente; // Faz a conversão do tipo Cliente para ClientePF
				clientePF.removerVeiculo(placa);
				System.out.println("Veiculo " + placa + " removido com sucesso");
			}
			else {
				System.out.println("Veiculo não encontrado");
			}
    }

}
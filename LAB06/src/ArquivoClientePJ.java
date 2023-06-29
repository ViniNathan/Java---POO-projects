import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArquivoClientePJ {
    // Caminho do arquivo onde os clientes PJ serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/clientesPJ.csv";
    private static final String CAMINHO_ARQUIVO_BACKUP = "Arquivos/backup/clientesPJ.csv";

    
    // Método para gravar os clientesPJ no arquivo
    public static void gravarClientesPJ(Seguradora seguradora) {
        List<Cliente> clientes = new ArrayList<>();
        clientes = seguradora.getListaClientes();

        // Cria uma cópia do arquivo atual como backup
        File arquivoBackup = new File(CAMINHO_ARQUIVO_BACKUP + ".backup");
        File arquivoAtual = new File(CAMINHO_ARQUIVO);
        
        // Verifica se o arquivo atual existe
        if (arquivoAtual.exists()) {
            try {
                Files.copy(arquivoAtual.toPath(), arquivoBackup.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Backup do arquivo anterior criado com sucesso.");
            } catch (IOException e) {
            	System.out.println("Erro ao criar o backup do arquivo anterior: " + e.getMessage());
            }
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CAMINHO_ARQUIVO))) {
        	// Escreve os nomes das colunas no início do arquivo
            writer.write("CNPJ, Nome da Empresa, Telefone, Endereço, Email, Data de Fundação, Código da frota");
            writer.newLine();

            // Itera sobre os clientesPF e os escreve no arquivo
            for (Cliente cliente : clientes) {
                if (cliente instanceof ClientePJ) {
                	ClientePJ clientePJ = (ClientePJ) cliente;
                    writer.write(clientePJ.toCsvString());
                    writer.newLine();
                }
            }
            System.out.println("ClientesPJ gravados com sucesso.");
        } catch (IOException e) {
        	System.out.println("Erro ao gravar ClientesPJ: " + e.getMessage());
        }
    }
    
    
    
    // Método para ler o arquivo CSV
    public String lerArquivoCSV(File arquivo, Seguradora seguradora) {
        StringBuilder conteudo = new StringBuilder();

        // Verifica se o arquivo existe
        if (arquivo.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
                String linha;

                // Lê a primeira linha do arquivo (cabeçalho) e descarta
                reader.readLine();

                // Lê cada linha do arquivo a partir da segunda linha
                while ((linha = reader.readLine()) != null) {
                    // Quebra a linha em campos usando a vírgula como separador
                    String[] campos = linha.split(",");

                    // Verifica se há campos suficientes
                    if (campos.length >= 7) {
                        String cnpj = campos[0].trim();
                        String nome = campos[1].trim();
                        String telefone = campos[2].trim();
                        String endereco = campos[3].trim();
                        String email = campos[4].trim();
                        Date dataFundacao = parseData(campos[5].trim());
                        String codigo = campos[6].trim();                   

                        // Cria uma instância de ClientePJ com os dados da linha
                        ArrayList <Frota> listaFrota = new ArrayList<Frota>();
                        Frota frota = seguradora.buscarFrotaPorCodigo(codigo);
                        listaFrota.add(frota);
                        
                        ClientePJ clientePJ = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, listaFrota);

                        // Verifica se a data é nula antes de adicionar o cliente à seguradora
                        if (clientePJ.getDataFundacao() != null) {
                            // Adiciona o clientePJ à seguradora
                            seguradora.getListaClientes().add(clientePJ);
                        } else {
                        	System.out.println("Data de fundação inválida para o clientePJ: " + nome);
                        }
                    } else {
                    	System.out.println("Formato inválido da linha no arquivo CSV: " + linha);
                    }
                }
            } catch (IOException e) {
            	System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
        } else {
        	System.out.println("Arquivo não encontrado.");
        }

        // Retorna o conteúdo do arquivo como uma string
        System.out.println("A lista de clientesPJ foi lida com sucesso!");
        return conteudo.toString();
    }

    

    // Método auxiliar para converter uma string em uma data
    private static Date parseData(String dataString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.parse(dataString);
        } catch (ParseException e) {
            System.out.println("Erro ao converter a data.");
            return null;
        }
    }
}

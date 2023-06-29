import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class ArquivoFrota {
    // Caminho do arquivo onde as frotas serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/frotas.csv";
    private static final String CAMINHO_ARQUIVO_BACKUP = "Arquivos/backup/frotas.csv";

    // Método para gravar os frotas no arquivo
    public static void gravarFrotas(Seguradora seguradora) {
        List<Frota> frotas = new ArrayList<>();
        frotas = seguradora.getListaFrotas();

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
            writer.write("ID da frota, Placa do Veiculo 1, Placa do Veiculo 2, Placa do Veiculo 3");
            writer.newLine();

            // Itera sobre os frotas e os escreve no arquivo
            for (Frota frota : frotas) {
                writer.write(frota.toCsvString());
                writer.newLine();
            }
            System.out.println("Frotas gravados com sucesso.");
        } catch (IOException e) {
        	System.out.println("Erro ao gravar Veículos: " + e.getMessage());
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
                    if (campos.length >= 4) {
                        String id = campos[0].trim();
                        String placa1 = campos[1].trim();
                        String placa2 = campos[2].trim();
                        String placa3 = campos[3].trim();                 

                        // Cria uma instância de Veículos com os dados da linha
                        ArrayList <Veiculo> listaVeiculos = new ArrayList<Veiculo>();
                        Veiculo veiculo1 = seguradora.buscarVeiculoPorPlaca(placa1);
                        Veiculo veiculo2 = seguradora.buscarVeiculoPorPlaca(placa2);
                        Veiculo veiculo3 = seguradora.buscarVeiculoPorPlaca(placa3);
                        listaVeiculos.add(veiculo1);
                        listaVeiculos.add(veiculo2);
                        listaVeiculos.add(veiculo3);
                     
                        Frota frota = new Frota(id, listaVeiculos);                        
                        seguradora.getListaFrotas().add(frota);

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
        System.out.println("A lista de frotas foi lida com sucesso!");
        return conteudo.toString();
    }
}

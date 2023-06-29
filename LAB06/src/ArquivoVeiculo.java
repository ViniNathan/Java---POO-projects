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

public class ArquivoVeiculo {
    // Caminho do arquivo onde os veículos serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/veiculos.csv";
    private static final String CAMINHO_ARQUIVO_BACKUP = "Arquivos/backup/veiculos.csv";

    // Método para gravar os veículos no arquivo
    public static void gravarVeiculos(Seguradora seguradora) {
        List<Veiculo> veiculos = new ArrayList<>();
        veiculos = seguradora.getListaVeiculos();

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
            writer.write("Placa, Marca, Modelo, Ano de Fabricação");
            writer.newLine();

            // Itera sobre os veiculos e os escreve no arquivo
            for (Veiculo veiculo : veiculos) {
                writer.write(veiculo.toCsvString());
                writer.newLine();
            }
            System.out.println("Veiculos gravados com sucesso.");
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
                        String placa = campos[0].trim();
                        String marca = campos[1].trim();
                        String modelo = campos[2].trim();
                        String ano = campos[3].trim();                 

                        // Cria uma instância de Veículos com os dados da linha
                        Veiculo veiculo = new Veiculo(placa, marca, modelo, Integer.parseInt(ano));                        
                        seguradora.getListaVeiculos().add(veiculo);

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
        System.out.println("A lista de veículos foi lida com sucesso!");
        return conteudo.toString();
    }
}

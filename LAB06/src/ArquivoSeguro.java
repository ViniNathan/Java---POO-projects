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

public class ArquivoSeguro {
    // Caminho do arquivo onde as seguros serão gravados
    private static final String CAMINHO_ARQUIVO = "Arquivos/seguros.csv";
    private static final String CAMINHO_ARQUIVO_BACKUP = "Arquivos/backup/seguros.csv";

    // Método para gravar os seguros no arquivo
    public static void gravarSeguro(Seguradora seguradora) {
        List<Seguro> seguros = new ArrayList<>();
        seguros = seguradora.getListaSeguros();

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
            writer.write("Data de Inicio, Data de Fim, Seguradora, Valor Mensal, Tipo");
            writer.newLine();

            // Itera sobre os seguros e os escreve no arquivo
            for (Seguro seguro : seguros) {
            	if (seguro instanceof SeguroPF) {
            		SeguroPF seguroPF = (SeguroPF) seguro;
                    writer.write(seguroPF.toCsvString());
                    writer.newLine();
            	}
            	else if (seguro instanceof SeguroPJ) {
            		SeguroPJ seguroPJ = (SeguroPJ) seguro;
                    writer.write(seguroPJ.toCsvString());
                    writer.newLine();
            	}
            }
            System.out.println("Seguros gravados com sucesso.");
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
                    if (campos.length >= 5) {
                        Date dataInicio = parseData(campos[0].trim());
                        Date dataFim = parseData(campos[1].trim());
                        String NomeSeguradora = campos[2].trim();
                        String valorMensal = campos[3].trim();   
                        String tipo = campos[4].trim();   

                        // Cria uma instância de seguros com os dados da linha                      
                        if (tipo == "SeguroPF") {
                        	Veiculo veiculo = null;
                        	ClientePF clientePF = null;
                        	SeguroPF seguroPF = new SeguroPF(dataInicio, dataFim, seguradora, Integer.parseInt(valorMensal), veiculo, clientePF);
                        	seguradora.getListaSeguros().add(seguroPF);
                        }
                        else if (tipo == "SeguroPJ") {
                        	Frota frota = null;
                        	ClientePJ clientePJ = null;
                        	SeguroPJ seguroPJ = new SeguroPJ(dataInicio, dataFim, seguradora, Integer.parseInt(valorMensal), frota, clientePJ);
                        	seguradora.getListaSeguros().add(seguroPJ);
                        }             

                    } else {
                    	System.out.println("Formato inválido da linha no arquivo CSV: " + linha);
                    }
                }
            } catch (IOException e) {
            	System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            }
            System.out.println("A lista de seguros foi lida com sucesso!");
        } else {
        	System.out.println("Arquivo não encontrado.");
        }

        // Retorna o conteúdo do arquivo como uma string
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

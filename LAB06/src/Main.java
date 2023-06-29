import java.io.File;
import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args) {
		// Instanciando um objeto Seguradora
        Seguradora seguradora = new Seguradora("26.372.837/0001-55", "Seguradora XYZ", "(87) 98137-6381", "Rua ABC, 123", "seguradora@xyz.com");
        ArrayList<Seguradora> listaSeguradoras = new ArrayList<>(); // Lista de seguradoras
        listaSeguradoras.add(seguradora); // Adiciona a seguradora a lista de seguradoras
             
    	
    	// Caminhos dos arquivos a serem lidos automaticamente
        String caminhoClientePF = "Arquivos/clientesPF.csv";
        String caminhoClientePJ = "Arquivos/clientesPJ.csv";
        String caminhoCondutor = "Arquivos/condutores.csv";
        String caminhoFrota = "Arquivos/frotas.csv";
        String caminhoSeguro = "Arquivos/seguros.csv";
        String caminhoVeiculo = "Arquivos/veiculos.csv";

        // Leitura automática dos arquivos
        ArquivoVeiculo arquivoVeiculo = new ArquivoVeiculo();
        arquivoVeiculo.lerArquivoCSV(new File(caminhoVeiculo), seguradora);
        
        ArquivoFrota arquivoFrota = new ArquivoFrota();
        arquivoFrota.lerArquivoCSV(new File(caminhoFrota), seguradora);
     
        ArquivoClientePF arquivoClientePF = new ArquivoClientePF();
        arquivoClientePF.lerArquivoCSV(new File(caminhoClientePF), seguradora);

        ArquivoClientePJ arquivoClientePJ = new ArquivoClientePJ();
        arquivoClientePJ.lerArquivoCSV(new File(caminhoClientePJ), seguradora);

        ArquivoCondutor arquivoCondutor = new ArquivoCondutor();
        arquivoCondutor.lerArquivoCSV(new File(caminhoCondutor), seguradora);

        ArquivoSeguro arquivoSeguro = new ArquivoSeguro();
        arquivoSeguro.lerArquivoCSV(new File(caminhoSeguro), seguradora);
             
        
        // Implementa a interface Grafica
		InterfaceGrafica interfaceGrafica = new InterfaceGrafica(seguradora);
        interfaceGrafica.setVisible(true);
        
    }
	


}
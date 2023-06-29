
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class InterfaceGrafica extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea textArea;

    public InterfaceGrafica(Seguradora seguradora) {

        // Configurações da janela
        setTitle("Gestão da Seguradora");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Cria a barra de menu
        JMenuBar menuBar = new JMenuBar();
        
        // Cria o menu "Arquivo"
        JMenu menuArquivo = new JMenu("Arquivo");
        // Cria o submenu "Abrir"
        JMenu menuAbrir = new JMenu("Abrir");
        
        // Cria os itens de menu para o menu "Arquivo"
        JMenuItem itemSalvar = new JMenuItem("Salvar");
        JMenuItem itemSair = new JMenuItem("Sair");
        
        // Adiciona os itens de menu ao menu "Arquivo"
        menuArquivo.add(menuAbrir);
        menuArquivo.add(itemSalvar);
        menuArquivo.addSeparator(); // Adiciona uma linha separadora
        menuArquivo.add(itemSair);
        
        // Adiciona os itens de menu ao submenu "Abrir"
        JMenuItem itemAbrirVeiculos = new JMenuItem("Veiculos");
        JMenuItem itemAbrirFrota = new JMenuItem("Frotas");
        JMenuItem itemAbrirClientesPF = new JMenuItem("ClientesPF");
        JMenuItem itemAbrirClientesPJ = new JMenuItem("ClientesPJ");
        JMenuItem itemAbrirCondutores = new JMenuItem("Condutores");
        JMenuItem itemAbrirSeguros = new JMenuItem("Seguros");
        
        menuAbrir.add(itemAbrirVeiculos);
        menuAbrir.add(itemAbrirFrota);
        menuAbrir.add(itemAbrirClientesPF);
        menuAbrir.add(itemAbrirClientesPJ);
        menuAbrir.add(itemAbrirCondutores);
        menuAbrir.add(itemAbrirSeguros);

        // Adiciona os menus à barra de menu
        menuBar.add(menuArquivo);
        
        // Define a barra de menu na janela
        setJMenuBar(menuBar);
        getContentPane().setLayout(null);
        
        // Cria o JTextArea
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 460, 400);
        getContentPane().add(scrollPane);
        
        itemAbrirVeiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoVeiculo arquivoV = new ArquivoVeiculo();
                    arquivoV.lerArquivoCSV(file, seguradora);
                }
            }
        });
        
        itemAbrirFrota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoFrota arquivoF = new ArquivoFrota();
                    arquivoF.lerArquivoCSV(file, seguradora);
                }
            }
        });
        
        itemAbrirClientesPF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoFrota arquivoCpf = new ArquivoFrota();
                    arquivoCpf.lerArquivoCSV(file, seguradora);
                }
            }
        });
        
        itemAbrirClientesPJ.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoFrota arquivoCpj = new ArquivoFrota();
                    arquivoCpj.lerArquivoCSV(file, seguradora);
                }
            }
        });
        
        itemAbrirCondutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoFrota arquivoC = new ArquivoFrota();
                    arquivoC.lerArquivoCSV(file, seguradora);
                }
            }
        });
        
        itemAbrirSeguros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cria uma instância do JFileChooser
                JFileChooser fileChooser = new JFileChooser();
                
                // Abre a janela de seleção de arquivo
                int result = fileChooser.showOpenDialog(InterfaceGrafica.this);
                
                // Verifica se o usuário selecionou um arquivo
                if (result == JFileChooser.APPROVE_OPTION) {
                    // Obtém o arquivo selecionado
                    File file = fileChooser.getSelectedFile();
                    
                    ArquivoFrota arquivoSe = new ArquivoFrota();
                    arquivoSe.lerArquivoCSV(file, seguradora);
                }
            }
        });
        
        itemSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArquivoClientePF.gravarClientesPF(seguradora);
                ArquivoClientePJ.gravarClientesPJ(seguradora);
                ArquivoCondutor.gravarCondutores(seguradora);
                ArquivoFrota.gravarFrotas(seguradora);
//                ArquivoSeguro.gravarSeguro(seguradora);
                ArquivoVeiculo.gravarVeiculos(seguradora);
            }
        });
        
        itemSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela
            }
        });
        
    }

    // Método para exibir mensagens do sistema no JTextArea
    public void exibirMensagem(String mensagem) {
        textArea.append(mensagem + "\n");
    }
    // Método para exibir mensagens do sistema no JTextArea
    public void exibirMensagemSb(StringBuilder mensagem) {
        textArea.append(mensagem + "\n");
    }
}
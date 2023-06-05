
public enum MenuOperacoes {
    CADASTRAR(1),
    LISTAR(2),
    EXCLUIR(3),
    GERAR_SINISTRO(4),
    CALCULAR_RECEITA_SEGURADORA(5),
    SAIR(0);

    public int operacao;

    MenuOperacoes(int operacao) {
        this.operacao = operacao;
    }
    
    public int getOperacao() {
        return this.operacao;
    }
}

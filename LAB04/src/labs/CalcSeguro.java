package labs;

public enum CalcSeguro {
    VALOR_BASE(100.0),       // Valor base do seguro
    FATOR_18_30(1.2),        // Fator de multiplicação para clientes entre 18 e 30 anos
    FATOR_30_60(1.0),        // Fator de multiplicação para clientes entre 30 e 60 anos
    FATOR_60_90(1.5);        // Fator de multiplicação para clientes entre 60 e 90 anos

    private double valor;    // Valor do parâmetro

    private CalcSeguro(double valor) {
        this.valor = valor;
    }

    public double getValor() { // Para obter os valores dos parametros
        return valor;
    }
}


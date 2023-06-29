
public enum ValorSeguro {
    VALOR_BASE(10.0),       // Valor base do seguro
    FATOR_18_30(1.25),        // Fator de multiplicação para clientes entre 18 e 30 anos
    FATOR_30_60(1.0),        // Fator de multiplicação para clientes entre 30 e 60 anos
    FATOR_60(1.5);        // Fator de multiplicação para clientes coma mais de 60 anos

    private double valor;    // Valor do parâmetro

    private ValorSeguro(double valor) {
        this.valor = valor;
    }

    public double getValor() { // Para obter os valores dos parametros
        return valor;
    }
}


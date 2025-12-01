public class CalculadoraInfracaoImpl implements CalculadoraInfracao {
    @Override
    public int calcularPontos(TipoInfracao tipo) {
        return tipo.getPontos();
    }
    
    @Override
    public double calcularValor(TipoInfracao tipo) {
        return tipo.getValor();
    }
}

public class Infracao {
    private TipoInfracao tipo;
    private Veiculo veiculo;
    private double valorMulta;
    private int pontos;
    
    public Infracao(TipoInfracao tipo, Veiculo veiculo) {
        this.tipo = tipo;
        this.veiculo = veiculo;
        this.valorMulta = tipo.getValor();
        this.pontos = tipo.getPontos();
    }
    
    public TipoInfracao getTipo() { return tipo; }
    public Veiculo getVeiculo() { return veiculo; }
    public double getValorMulta() { return valorMulta; }
    public int getPontos() { return pontos; }
    
    @Override
    public String toString() {
        return String.format("Infração %s - Pontos: %d - Valor: R$ %.2f - Veículo: %s",
            tipo, pontos, valorMulta, veiculo.getPlaca());
    }
}

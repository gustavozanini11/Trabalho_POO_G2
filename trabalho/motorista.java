public class Motorista {
    private String nome;
    private String numeroCNH;
    private int pontosAcumulados;
    
    public Motorista(String nome, String numeroCNH) {
        this.nome = nome;
        this.numeroCNH = numeroCNH;
        this.pontosAcumulados = 0;
    }
    
    public String getNome() { return nome; }
    public String getNumeroCNH() { return numeroCNH; }
    public int getPontosAcumulados() { return pontosAcumulados; }
    
    public void adicionarPontos(int pontos) {
        this.pontosAcumulados += pontos;
    }
    
    @Override
    public String toString() {
        return String.format("%s (CNH: %s) - %d pontos", nome, numeroCNH, pontosAcumulados);
    }
}

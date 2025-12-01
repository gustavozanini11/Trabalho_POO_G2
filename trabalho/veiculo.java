public class Veiculo {
    private String placa;
    private String modelo;
    private Motorista motoristaResponsavel;
    
    public Veiculo(String placa, String modelo, Motorista motorista) {
        this.placa = placa;
        this.modelo = modelo;
        this.motoristaResponsavel = motorista;
    }
    
    public String getPlaca() { return placa; }
    public String getModelo() { return modelo; }
    public Motorista getMotoristaResponsavel() { return motoristaResponsavel; }
    
    @Override
    public String toString() {
        return String.format("%s - %s (Motorista: %s)", 
            placa, modelo, motoristaResponsavel.getNome());
    }
}

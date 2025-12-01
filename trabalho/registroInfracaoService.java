public class RegistroInfracaoService {
    private CalculadoraInfracao calculadora;
    private AvaliadorSituacaoCNH avaliador;
    
    public RegistroInfracaoService(CalculadoraInfracao calculadora,
                                   AvaliadorSituacaoCNH avaliador) {
        this.calculadora = calculadora;
        this.avaliador = avaliador;
    }
    
    public Infracao registrarInfracao(TipoInfracao tipo, Veiculo veiculo) {
        
        Infracao infracao = new Infracao(tipo, veiculo);
        
        Motorista motorista = veiculo.getMotoristaResponsavel();
        motorista.adicionarPontos(infracao.getPontos());
        
        SituacaoCNH situacao = avaliador.avaliarSituacao(motorista.getPontosAcumulados());
        
        System.out.println("\n--- INFRAÇÃO REGISTRADA ---");
        System.out.println(infracao);
        System.out.println("Motorista: " + motorista);
        System.out.println("Situação CNH: " + situacao.getDescricao());
        System.out.println("---------------------------\n");
        
        return infracao;
    }
    
    public void exibirSituacaoMotorista(Motorista motorista) {
        SituacaoCNH situacao = avaliador.avaliarSituacao(motorista.getPontosAcumulados());
        System.out.println("\n=== SITUAÇÃO DO MOTORISTA ===");
        System.out.println("Nome: " + motorista.getNome());
        System.out.println("CNH: " + motorista.getNumeroCNH());
        System.out.println("Pontos: " + motorista.getPontosAcumulados());
        System.out.println("Situação: " + situacao.getDescricao());
        System.out.println("=============================\n");
    }
}

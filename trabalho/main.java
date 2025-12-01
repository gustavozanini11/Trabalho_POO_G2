public class Main {
    public static void main(String[] args) {
        // Instanciação das dependências (Implementações)
        CalculadoraInfracao calculadora = new CalculadoraInfracaoImpl();
        AvaliadorSituacaoCNH avaliador = new AvaliadorSituacaoCNHImpl();
        
        // Injeção de dependência no serviço
        RegistroInfracaoService servico = new RegistroInfracaoService(calculadora, avaliador);
        
        // Criação dos motoristas
        Motorista motorista1 = new Motorista("Gustavo Zanini", "12345678900");
        Motorista motorista2 = new Motorista("Leonam Hemann", "98765432100");
        
        // Criação dos veículos
        Veiculo veiculo1 = new Veiculo("ABC-1234", "Fiat Uno", motorista1);
        Veiculo veiculo2 = new Veiculo("XYZ-5678", "Honda Civic", motorista2);
        
        System.out.println("========== SISTEMA DE CONTROLE DE MULTAS ==========");
        
        servico.registrarInfracao(TipoInfracao.LEVE, veiculo1);
        servico.registrarInfracao(TipoInfracao.MEDIA, veiculo1);
        servico.registrarInfracao(TipoInfracao.GRAVE, veiculo1);
        
        servico.exibirSituacaoMotorista(motorista1);
        
        
        servico.registrarInfracao(TipoInfracao.GRAVISSIMA, veiculo1);
        servico.registrarInfracao(TipoInfracao.GRAVISSIMA, veiculo1);
        
        servico.exibirSituacaoMotorista(motorista1);
        
        
        servico.registrarInfracao(TipoInfracao.LEVE, veiculo2);
        servico.exibirSituacaoMotorista(motorista2);
    }
}

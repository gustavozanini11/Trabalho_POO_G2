class SistemaMultasTransito {
    public static void main(String[] args) {
        CalculadoraInfracao calculadora = new CalculadoraInfracaoImpl();
        AvaliadorSituacaoCNH avaliador = new AvaliadorSituacaoCNHImpl();
        RegistroInfracaoService servico = new RegistroInfracaoService(calculadora, avaliador);
        
        
        Motorista motorista1 = new Motorista("Gustavo Zanini", "12345678900");
        Motorista motorista2 = new Motorista("Leonam Hemann", "98765432100");
        
        
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


enum TipoInfracao {
    LEVE(3, 88.30),
    MEDIA(4, 130.16),
    GRAVE(5, 195.23),
    GRAVISSIMA(7, 293.47);
    
    private final int pontos;
    private final double valor;
    
    TipoInfracao(int pontos, double valor) {
        this.pontos = pontos;
        this.valor = valor;
    }
    
    public int getPontos() { return pontos; }
    public double getValor() { return valor; }
}


interface CalculadoraInfracao {
    int calcularPontos(TipoInfracao tipo);
    double calcularValor(TipoInfracao tipo);
}


class Infracao {
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


class CalculadoraInfracaoImpl implements CalculadoraInfracao {
    @Override
    public int calcularPontos(TipoInfracao tipo) {
        return tipo.getPontos();
    }
    
    @Override
    public double calcularValor(TipoInfracao tipo) {
        return tipo.getValor();
    }
}


enum SituacaoCNH {
    REGULAR("Regular"),
    EM_RISCO("Em risco de suspensão"),
    SUSPENSA("CNH Suspensa");
    
    private final String descricao;
    
    SituacaoCNH(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() { return descricao; }
}


class Motorista {
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


class Veiculo {
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


interface AvaliadorSituacaoCNH {
    SituacaoCNH avaliarSituacao(int pontos);
}


class AvaliadorSituacaoCNHImpl implements AvaliadorSituacaoCNH {
    @Override
    public SituacaoCNH avaliarSituacao(int pontos) {
        if (pontos < 10) {
            return SituacaoCNH.REGULAR;
        } else if (pontos < 20) {
            return SituacaoCNH.EM_RISCO;
        } else {
            return SituacaoCNH.SUSPENSA;
        }
    }
}


class RegistroInfracaoService {
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

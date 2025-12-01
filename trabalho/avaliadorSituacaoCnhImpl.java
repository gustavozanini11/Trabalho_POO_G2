public class AvaliadorSituacaoCNHImpl implements AvaliadorSituacaoCNH {
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

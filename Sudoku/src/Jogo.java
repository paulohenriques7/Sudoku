public class Jogo {

    private final Tabuleiro tabuleiro;
    private StatusJogo status = StatusJogo.NAO_INICIADO;

    public Jogo(String[] args) {
        this.tabuleiro = new Tabuleiro(args);
        atualizaStatus();
    }

    public Tabuleiro getTabuleiro() { return tabuleiro; }

    public StatusJogo getStatus() { return status; }

    public void atualizaStatus() {
        if (tabuleiro.completo())
            status = StatusJogo.COMPLETO;
        else if (tabuleiro.contemErros())
            status = StatusJogo.INCOMPLETO;  // mas com erro
        else if (estaVazio())
            status = StatusJogo.NAO_INICIADO;
        else
            status = StatusJogo.INCOMPLETO;
    }

    private boolean estaVazio() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (tabuleiro.getValor(i, j) != 0) return false;
        return true;
    }
}


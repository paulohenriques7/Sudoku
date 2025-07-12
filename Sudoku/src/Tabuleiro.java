import java.util.*;

public class Tabuleiro {

    private final int[][] valores = new int[9][9];   // 0 = vazio
    private final boolean[][] fixos = new boolean[9][9];

    /** Constrói o tabuleiro inicial a partir dos argumentos "linha,coluna,valor". */
    public Tabuleiro(String[] args) {
        carregarFixos(args);
    }

    /** Recarrega o tabuleiro mantendo apenas os números fixos. */
    public void limparJogadas() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (!fixos[i][j]) valores[i][j] = 0;
    }

    /** Tenta inserir um valor. Retorna true se conseguiu. */
    public boolean inserir(int lin, int col, int val) {
        if (!validoIndice(lin, col) || !validoValor(val) || fixos[lin][col] || valores[lin][col] != 0)
            return false;
        valores[lin][col] = val;
        return true;
    }

    /** Tenta remover um valor. Retorna true se conseguiu. */
    public boolean remover(int lin, int col) {
        if (!validoIndice(lin, col) || fixos[lin][col] || valores[lin][col] == 0)
            return false;
        valores[lin][col] = 0;
        return true;
    }

    public boolean completo() {
        for (int[] linha : valores)
            for (int v : linha)
                if (v == 0) return false;
        return true && !contemErros();
    }

    /** Verifica linhas, colunas e sub‑grades 3×3. */
    public boolean contemErros() {
        // linhas/colunas
        for (int i = 0; i < 9; i++) {
            if (temDuplicados(valores[i])) return true;                 // linha
            int[] col = new int[9];
            for (int j = 0; j < 9; j++) col[j] = valores[j][i];
            if (temDuplicados(col)) return true;                        // coluna
        }
        // blocos 3×3
        for (int bi = 0; bi < 3; bi++)
            for (int bj = 0; bj < 3; bj++)
                if (temDuplicados(subBloco(bi * 3, bj * 3))) return true;
        return false;
    }

    public void exibir() {
        final String H = "+-------+-------+-------+";
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) System.out.println(H);
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0) System.out.print("| ");
                System.out.print(valores[i][j] == 0 ? ". " : valores[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println(H);
    }

    /* -------------------------- privados --------------------------- */

private void carregarFixos(String[] args) {
    for (String arg : args) {
        try {
            // Ex: "0,0;4,false"
            String[] partes = arg.split(";");
            if (partes.length != 2) continue;

            String[] indices = partes[0].split(",");
            if (indices.length != 2) continue;

            int lin = Integer.parseInt(indices[0].trim());
            int col = Integer.parseInt(indices[1].trim());

            String[] valorFixo = partes[1].split(",");
            int val = Integer.parseInt(valorFixo[0].trim());
            boolean isFixo = Boolean.parseBoolean(valorFixo[1].trim());

            if (validoIndice(lin, col) && validoValor(val)) {
                valores[lin][col] = val;
                fixos[lin][col]  = isFixo;
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar entrada: " + arg);
        }
    }
}


    private boolean validoIndice(int l, int c) { return l >= 0 && l < 9 && c >= 0 && c < 9; }
    private boolean validoValor(int v) { return v >= 1 && v <= 9; }

    private boolean temDuplicados(int[] vetor) {
        boolean[] seen = new boolean[10];
        for (int v : vetor)
            if (v != 0) {
                if (seen[v]) return true;
                seen[v] = true;
            }
        return false;
    }

    private int[] subBloco(int startLin, int startCol) {
        int[] vals = new int[9];
        int k = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                vals[k++] = valores[startLin + i][startCol + j];
        return vals;
    }
    public int getValor(int lin, int col) { return valores[lin][col]; }
    public boolean isFixo(int lin, int col) { return fixos[lin][col]; }

}


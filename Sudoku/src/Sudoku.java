import java.util.Scanner;

public class Sudoku {

    public static void main(String[] args) {
        Jogo jogo = new Jogo(args);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n======= MENU =======");
            System.out.println("1. Iniciar/Reiniciar novo jogo");
            System.out.println("2. Colocar um novo número");
            System.out.println("3. Remover um número");
            System.out.println("4. Verificar jogo (exibir)");
            System.out.println("5. Verificar status");
            System.out.println("6. Limpar jogadas");
            System.out.println("7. Finalizar jogo");
            System.out.print("Escolha: ");
            int op = sc.nextInt();

            switch (op) {
                case 1 -> jogo = new Jogo(args);
                case 2 -> inserirNumero(sc, jogo);
                case 3 -> removerNumero(sc, jogo);
                case 4 -> jogo.getTabuleiro().exibir();
                case 5 -> exibirStatus(jogo);
                case 6 -> { jogo.getTabuleiro().limparJogadas(); jogo.atualizaStatus(); }
                case 7 -> {
                    if (jogo.getTabuleiro().completo()) {
                        System.out.println("Parabéns! Jogo concluído.");
                        System.exit(0);
                    } else {
                        System.out.println("Ainda há espaços vazios ou erros. Continue jogando!");
                    }
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void inserirNumero(Scanner sc, Jogo jogo) {
        System.out.print("Linha [0‑8]: "); int l = sc.nextInt();
        System.out.print("Coluna [0‑8]: "); int c = sc.nextInt();
        System.out.print("Valor  [1‑9]: "); int v = sc.nextInt();
        if (jogo.getTabuleiro().inserir(l, c, v))
            System.out.println("Número inserido!");
        else
            System.out.println("Não foi possível inserir. Verifique índices, valor ou posição fixa.");
        jogo.atualizaStatus();
    }

    private static void removerNumero(Scanner sc, Jogo jogo) {
        System.out.print("Linha [0‑8]: "); int l = sc.nextInt();
        System.out.print("Coluna [0‑8]: "); int c = sc.nextInt();
        if (jogo.getTabuleiro().remover(l, c))
            System.out.println("Número removido!");
        else
            System.out.println("Não foi possível remover (posição vazia ou número fixo).");
        jogo.atualizaStatus();
    }

    private static void exibirStatus(Jogo jogo) {
        StatusJogo st = jogo.getStatus();
        boolean erros = jogo.getTabuleiro().contemErros();
        System.out.printf("Status: %s | Erros: %s%n",
                st, (erros ? "SIM" : "NÃO"));
    }
}


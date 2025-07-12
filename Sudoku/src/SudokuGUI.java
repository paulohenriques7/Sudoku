import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.BadLocationException;

public class SudokuGUI extends JFrame {

    private final Jogo jogo;
    private final JTextField[][] campos = new JTextField[9][9];

    public SudokuGUI(String[] args) {
        super("Sudoku – Swing");
        this.jogo = new Jogo(args);
        criarInterface();
        atualizarCampos();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // centraliza
        setVisible(true);
    }

    /* ----------------- Interface ----------------- */

    private void criarInterface() {
        setLayout(new BorderLayout());
        add(criarGrade(), BorderLayout.CENTER);
        add(criarBarraBotoes(), BorderLayout.SOUTH);
    }

    private JPanel criarGrade() {
        JPanel grade = new JPanel(new GridLayout(9, 9));
        grade.setBorder(new LineBorder(Color.BLACK, 2));

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField tf = new JTextField();
                tf.setHorizontalAlignment(JTextField.CENTER);
                tf.setFont(new Font("SansSerif", Font.BOLD, 18));
                tf.setDocument(new LimitDocument(1));           // 1 caractere
                campos[i][j] = tf;
                // Destaque de sub‑grade 3×3
                int top = (i % 3 == 0) ? 2 : 1;
                int left = (j % 3 == 0) ? 2 : 1;
                tf.setBorder(new LineBorder(Color.GRAY, 1));
                ((JComponent) tf).setBorder(BorderFactory.createMatteBorder(
                        top, left, 1, 1, Color.BLACK));
                int lin = i, col = j;
                tf.addKeyListener(new KeyAdapter() {
                    @Override public void keyReleased(KeyEvent e) {
                        String t = tf.getText().trim();
                        if (t.isEmpty()) {
                            jogo.getTabuleiro().remover(lin, col);
                        } else if (t.matches("[1-9]")) {
                            int v = Integer.parseInt(t);
                            if (!jogo.getTabuleiro().inserir(lin, col, v))
                                tf.setText(""); // inválido
                        } else {
                            tf.setText("");
                        }
                        jogo.atualizaStatus();
                    }
                });
                grade.add(tf);
            }
        }
        return grade;
    }

    private JPanel criarBarraBotoes() {
        JButton btnVerificar = new JButton("Verificar");
        JButton btnLimpar    = new JButton("Limpar");
        JButton btnFinalizar = new JButton("Finalizar");

        btnVerificar.addActionListener(e -> {
            boolean erro = jogo.getTabuleiro().contemErros();
            JOptionPane.showMessageDialog(this,
                    erro ? "Há conflitos no tabuleiro." : "Nenhum erro encontrado.",
                    "Resultado", erro ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        });

        btnLimpar.addActionListener(e -> {
            jogo.getTabuleiro().limparJogadas();
            atualizarCampos();
        });

        btnFinalizar.addActionListener(e -> {
            if (jogo.getTabuleiro().completo()) {
                JOptionPane.showMessageDialog(this, "Parabéns! Sudoku completo.");
                System.exit(0);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Ainda restam espaços vazios ou erros.",
                        "Incompleto", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel barra = new JPanel();
        barra.add(btnVerificar);
        barra.add(btnLimpar);
        barra.add(btnFinalizar);
        return barra;
    }

    private void atualizarCampos() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                int val = jogo.getTabuleiro().getValor(i, j);
                JTextField tf = campos[i][j];
                tf.setText(val == 0 ? "" : String.valueOf(val));
                boolean fixo = jogo.getTabuleiro().isFixo(i, j);
                tf.setEditable(!fixo);
                tf.setBackground(fixo ? new Color(230, 230, 230) : Color.WHITE);
            }
    }

    /* ---------- Pequena classe util para limitar caracteres ---------- */
    static class LimitDocument extends javax.swing.text.PlainDocument {
        private final int limit;
        LimitDocument(int l) { this.limit = l; }
        @Override public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                throws BadLocationException {
            if (str == null) return;
            if ((getLength() + str.length()) <= limit)
                super.insertString(offs, str, a);
        }
    }

    /* --------------------------- main --------------------------- */

public static void main(String[] args) {
    // Exemplo rápido se nenhum argumento for passado:
    if (args.length == 0) {
        String[] defaultArgs = { "0,0;5,true", "1,1;7,true", "2,2;3,true" };
        SwingUtilities.invokeLater(() -> new SudokuGUI(defaultArgs));
    } else {
        SwingUtilities.invokeLater(() -> new SudokuGUI(args));
    }
}

}


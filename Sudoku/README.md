# 🧩 Sudoku - Java Swing

Este projeto é uma implementação gráfica do jogo Sudoku, desenvolvida em Java utilizando a biblioteca Swing. A aplicação simula um jogo interativo, com recursos de verificação automática de erros, preenchimento de números, status do jogo e validação da vitória.

---

## 🎮 Funcionalidades

O sistema oferece um menu interativo com as seguintes opções:

1. **Iniciar novo jogo**  
   O tabuleiro é montado com os valores iniciais definidos por argumentos no método `main`. Cada valor segue o formato:  
Exemplo: `"0,0;5,true"` representa o número 5 fixado na posição (0,0).

2. **Colocar um novo número**  
O jogador pode preencher posições vazias com novos números, desde que a célula não esteja previamente preenchida (por número fixo ou jogada anterior).

3. **Remover um número**  
Permite remover números inseridos pelo jogador. Números fixos (definidos no início do jogo) **não podem ser removidos**.

4. **Verificar jogo**  
Analisa a grade atual e exibe se há conflitos (números repetidos em linhas, colunas ou regiões 3x3).

5. **Verificar status do jogo**  
Informa se o jogo está:
- `Não iniciado`: Nenhuma jogada feita.
- `Incompleto`: Há espaços vazios.
- `Completo`: Todas as posições preenchidas.

Também informa se há erros no preenchimento atual.

6. **Limpar jogo**  
Remove todos os números inseridos pelo jogador, mantendo apenas os números fixos do jogo inicial.

7. **Finalizar o jogo**  
Verifica se todas as posições estão corretamente preenchidas. Caso positivo, finaliza o jogo com mensagem de vitória. Se não, informa ao usuário o que falta.

---

## 🖼️ Interface Gráfica

A interface gráfica foi desenvolvida com **Java Swing** e apresenta:

- Um **tabuleiro 9x9** com campos interativos.
- **Destaque visual nas regiões 3x3** com bordas realçadas.
- Três botões principais:
- 🔍 **Verificar**: analisa o tabuleiro.
- 🧹 **Limpar**: remove jogadas do usuário.
- ✅ **Finalizar**: tenta encerrar o jogo, se válido.

Cada célula permite apenas **1 caractere numérico** e impede entradas inválidas. Células fixas são exibidas em cinza e não são editáveis.

---

## 🛠️ Execução do Projeto

### ✅ Requisitos:
- Java 8 ou superior
- IDE como VS Code, Eclipse ou IntelliJ



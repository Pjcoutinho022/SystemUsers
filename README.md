# ☕ Sistema de Gestão de Usuários (UsuarioSistema)

Projeto prático desenvolvido como parte dos estudos de **Java e Inteligência Artificial** no **Bootcamp Itaú Unibanco**. O objetivo principal é a aplicação aprofundada de **Programação Orientada a Objetos (POO)** e boas práticas de arquitetura de software, especificamente o padrão **MVC (Model - View - Controller)**.

---

## 🎯 Sobre o Desafio

O exercício propõe a modelagem de uma hierarquia de usuários em um ambiente corporativo, contemplando diferentes níveis de acesso e permissões:

1. **Gerente (`Manager`)**:
   - Atributos: Nome, E-mail, Senha e Administrador (sempre `true`).
   - Métodos: Gerar relatório financeiro com a soma dos pagamentos dos atendentes, consultar vendas gerais de todos os vendedores, login, logoff, alterar dados e alterar senha.
2. **Vendedor (`Salesman`)**:
   - Atributos: Nome, E-mail, Senha, Quantidade de Vendas e Administrador (sempre `false`).
   - Métodos: Realizar venda (incrementar o contador de vendas), consultar vendas pessoais, login, logoff, alterar dados e alterar senha.
3. **Atendente (`Attendant`)**:
   - Atributos: Nome, E-mail, Senha, Valor em Caixa e Administrador (sempre `false`).
   - Métodos: Receber pagamentos (incrementar saldo em caixa e manter histórico), fechar o caixa (zerar saldo), login, logoff, alterar dados e alterar senha.

---

## 🏛️ Arquitetura e Decisões de Design (MVC)

O projeto foi refatorado e estruturado respeitando o padrão **MVC**, garantindo alta coesão e baixo acoplamento:

```
[ VIEW ] (ConsoleView / UserMenu)
   │  ▲
   │  │  (Chama métodos do Controller e exibe dados formatados)
   ▼  │
[ CONTROLLER ] (UsuarioController)
   │  ▲
   │  │  (Gerencia coleções, autenticação e fluxo)
   ▼  │
[ MODEL ] (SystemUsers, Manager, Salesman, Attendant)
```

- **`Models`**: Contém a classe base abstrata `SystemUsers` e suas especializações (`Manager`, `Salesman`, `Attendant`), além das interfaces que definem contratos de negócio (`ISalesman`, `IAttendant`, `IManager`, `IConsultSales`, `IUserOperations`).
- **`Controller`**: `UsuarioController` orquestra a lista unificada de usuários em memória (`listAllUsers`), valida unicidade de e-mails, controla a sessão do usuário logado e delega o processamento de regras para os modelos corretos.
- **`View`**: `ConsoleView` conduz os loops de interação com o usuário através de `Scanner`, enquanto `UserMenu` desacopla a apresentação visual dos menus utilizando *Text Blocks*.

---

## 📈 Aprendizados e Evolução Durante o Projeto

Durante o desenvolvimento deste exercício, identifiquei e superei vários desafios técnicos e arquiteturais importantes:

### 1. Quebra de Acoplamento (Model não deve conhecer Controller)
- **O Desafio:** Inicialmente, a classe `Manager` importava e acessava diretamente o `UsuarioController` para buscar listas de funcionários e calcular os relatórios.
- **A Solução:** Aplicação do princípio de **Inversão de Dependências**. A interface `IManager` e os métodos do `Manager` foram refatorados para receber a lista de usuários por parâmetro (`todosUsuarios`). O `Manager` processa as regras de negócio de forma pura, sem depender da camada do Controller.

### 2. Responsabilidade de Entrada/Saída (I/O)
- **O Desafio:** Modelos e controladores continham chamadas diretas a `System.out.println()`.
- **A Solução:** Purificação das camadas. `Models` e `Controllers` agora devolvem dados brutos (`boolean`, `double`, `List<String>`), deixando 100% da responsabilidade de exibir texto na tela e interagir com o usuário para a camada `View`.

### 3. Encapsulamento Real vs. Setters Desnecessários
- **O Desafio:** Manter um método `setSalesQuantity()` que permitia alterar externamente o número de vendas.
- **A Solução:** Remoção do setter. A contagem de vendas deve ser protegida e só pode sofrer incremento através do método de negócio `makeASale()`.

### 4. Apresentação Limpa no Terminal
- **O Desafio:** Imprimir listas diretamente gerava saídas cruas com colchetes e vírgulas (`[Item 1, Item 2]`).
- **A Solução:** Iteração com loops na camada de visualização para formatar relatórios linha por linha, garantindo legibilidade profissional.

---

## 🛠️ Tecnologias Utilizadas

- **Java 17+** (Text Blocks, formatação moderna de strings com `.formatted()`, validações com streams/loops)
- **IntelliJ IDEA**
- **Git & GitHub**

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java JDK 17** ou superior instalado na máquina.
- Git instalado (opcional, para clonar).

### Execução via Linha de Comando (Terminal)

1. Clone o repositório:
```bash
git clone https://github.com/SEU_USUARIO/UsuarioSistema.git
```

2. Acesse a pasta do projeto:
```bash
cd UsuarioSistema
```

3. Compile os arquivos Java:
```bash
javac -d bin src/Models/Interfaces/*.java src/Models/*.java src/Controller/*.java src/View/*.java
```

4. Execute a aplicação:
```bash
java -cp bin View.Main
```

### Execução via IntelliJ IDEA
1. Abra a pasta `UsuarioSistema` no IntelliJ IDEA.
2. Aguarde o IntelliJ indexar as classes.
3. Navegue até `src/View/Main.java`.
4. Clique no ícone verde de **Play** (`Run 'Main'`) ou use o atalho `Shift + F10`.

---

## 🧑‍💻 Autor

Desenvolvido por **Pedro Coutinho** no Bootcamp Itaú Unibanco - Trilha Java com Inteligência Artificial.

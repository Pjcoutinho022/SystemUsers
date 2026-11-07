# 🏛️ Guia Definitivo: Arquitetura MVC e Baseada em Componentes

> **Objetivo Central:** Separação de Responsabilidades (*Separation of Concerns*). Dividir a lógica de negócio da interface com o usuário para tornar o software modular, testável e manutenível.

---

## 🧭 O Fluxo Universal de Execução

```text
       [ VIEW ] ────────── Dispara Ação / Evento ──────────► [ CONTROLLER ]
    (Telas, Console)                                       (Regras e Fluxo)
           ▲                                                       │
           │                                                       ▼
           └──────────── Recebe Dados do Modelo ─────────────── [ MODEL ]
                                                              (Dados/Regras)
```

---

## 🧱 Os Três Pilares do MVC

### 1. MODEL (Os Dados e o Cérebro)
*Representa as entidades do mundo real dentro do sistema.*
* **O que faz:**
  - Armazena atributos e estados dos objetos (ex: `name`, `email`, `salesQuantity`, `cashOnHand`).
  - Aplica regras de integridade dos dados (ex: e-mail deve conter `@`, valores não podem ser negativos).
  - Define comportamentos internos puros (ex: incrementar vendas, calcular totais).
* **Regra de Ouro:** O Model é **100% agnóstico**. Ele não sabe se está rodando no console, na web ou num aplicativo mobile.
* **O que NUNCA deve ter:**
  - Nenhuma chamada de interação com usuário (`System.out.println`, `Scanner`, formulários).
  - Nenhuma importação ou dependência de `Controller`.

---

### 2. VIEW (Os Olhos e a Apresentação)
*A interface de contato com o usuário externo.*
* **O que faz:**
  - Apresenta menus, relatórios, telas ou formulários para o usuário.
  - Captura as entradas do teclado/cliques e repassa as intenções para o Controller.
  - Exibe mensagens amigáveis baseando-se no que o Controller responder.
* **Regra de Ouro:** A View é **passiva e sem inteligência**.
* **O que NUNCA deve ter:**
  - Não faz validações profundas de negócio.
  - Não armazena listas de usuários nem se conecta a bancos de dados.

---

### 3. CONTROLLER (O Maestro e o Intermediário)
*Coordena o fluxo entre a intenção do usuário e as ações do sistema.*
* **O que faz:**
  - Recebe os dados brutos enviados pela View.
  - Valida regras de fluxo (ex: verificar se o e-mail já existe no sistema antes de cadastrar).
  - Instancia os Models correspondentes e os armazena nas coleções/repositórios.
  - Devolve respostas lógicas (`true`/`false`, listas de objetos, códigos de status) para a View.
* **Regra de Ouro:** O Controller **não renderiza tela e não imprime mensagens**.
* **O que NUNCA deve ter:**
  - Interações diretas de entrada/saída (`System.out.println` ou `Scanner`).

---

## ⚖️ Matriz de Acoplamento (Quem conhece quem?)

| Camada | Pode Conhecer? | NÃO pode Conhecer! |
| :--- | :--- | :--- |
| **Model** | Apenas ele mesmo e utilitários da linguagem | ❌ **NUNCA** o Controller e **NUNCA** a View |
| **Controller** | ✅ Conhece os Models | ❌ **NUNCA** a View diretamente |
| **View** | ✅ Conhece o Controller (para disparar ações) e os Models (para exibir dados) | — |

---

## 🧩 Arquitetura Baseada em Componentes

Complementa o MVC com foco em criar módulos plugáveis e independentes:

1. **Segregação de Interfaces (ISP do SOLID):**
   - Classes devem implementar apenas contratos específicos para o que realmente fazem (ex: `ISalesman`, `IAttendant`, `IManager`, `IConsultSales`).
2. **Baixo Acoplamento e Alta Coesão:**
   - Cada classe ou componente deve ter uma única responsabilidade bem delimitada.
3. **Inversão e Injeção de Dependências:**
   - Em vez de um componente buscar dados por conta própria (ex: `Manager` acessar o `Controller` diretamente), ele deve receber os dados prontos por parâmetro.

---

## 🚀 O Maior Benefício Prático

Se no futuro você quiser transformar seu programa de terminal em um aplicativo web com **Spring Boot** ou uma tela gráfica:
* **Sua View muda 100%.**
* **Seus Models e Controllers continuam praticamente idênticos!**

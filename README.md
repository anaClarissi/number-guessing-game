# 🎯 Number Guessing Game

Um jogo de adivinhação de números desenvolvido em **Java**, no qual o jogador precisa descobrir um número secreto entre **1 e 100**, escolhido aleatoriamente pelo sistema.

Este projeto foi desenvolvido como uma implementação do desafio **[Number Guessing Game](https://roadmap.sh/projects/number-guessing-game)** proposto pelo **roadmap.sh**, com o objetivo de praticar conceitos de Java, orientação a objetos, organização de código, testes automatizados e princípios de arquitetura de software.

> **Projeto baseado no desafio do roadmap.sh:**
> https://roadmap.sh/projects/number-guessing-game

---

## 📌 Sobre o projeto

O **Number Guessing Game** é um jogo executado no terminal em que o sistema gera um número aleatório entre 1 e 100 e o jogador tenta descobrir qual é esse número.

A cada tentativa, o jogo informa se o número escolhido pelo jogador é:

* 📉 Menor que o número secreto;
* 📈 Maior que o número secreto;
* 🎯 Igual ao número secreto.

O jogador também pode receber **dicas** durante a partida e possui uma quantidade limitada de tentativas, dependendo do nível de dificuldade escolhido.

O projeto foi estruturado buscando separar as responsabilidades entre diferentes camadas, utilizando conceitos como **Domain Model, Services, Ports, Infrastructure e Presentation**.

---

## 🎮 Funcionalidades

### Níveis de dificuldade

O jogador pode escolher entre três níveis:

| Dificuldade | Tentativas |
| ----------- | ---------: |
| 🟢 Easy     |         10 |
| 🟡 Medium   |          5 |
| 🔴 Hard     |          3 |

A configuração dos níveis está centralizada no enum `Difficulty`.

---

### 🔢 Geração do número secreto

A cada nova rodada, o sistema gera aleatoriamente um número entre:

```text
1 e 100
```

A geração é realizada através da interface `NumberGeneratorPort` e implementada pela classe `RandomNumberGenerator`.

Essa separação permite substituir facilmente a implementação do gerador no futuro.

---

### 📊 Avaliação das tentativas

Cada tentativa pode produzir um dos seguintes resultados:

```text
TOO_LOW
TOO_HIGH
CORRECT
```

A classe `GuessEvaluator` é responsável por comparar o palpite do jogador com o número secreto.

---

### 💡 Sistema de dicas

A partir da segunda tentativa, o jogador pode solicitar uma dica.

O sistema pode informar:

* `You are very close!`
* `You are very far!`
* `The number is even.`
* `The number is odd.`

As dicas são determinadas com base na distância entre o palpite e o número secreto e, em determinados casos, pela paridade do número.

---

### ⏱️ Cronômetro

O jogo registra o tempo utilizado pelo jogador durante cada rodada.

A implementação utiliza:

```java
Instant
Duration
```

A abstração é feita através da interface `GameClockPort`, enquanto `SystemGameClock` fornece a implementação utilizando o relógio do sistema.

---

### 🏆 Sistema de recordes

O jogo mantém o melhor resultado de cada nível de dificuldade.

O recorde é baseado na quantidade de tentativas utilizadas.

Por exemplo:

```text
Easy: 5 attempts
Medium: 3 attempts
Hard: 1 attempt
```

Se o jogador conseguir um resultado melhor, o recorde anterior é substituído.

Atualmente, os resultados são armazenados em memória através da implementação:

```text
InMemoryScoreRepository
```

---

### 🔄 Jogar novamente

Ao terminar uma rodada, o jogador pode escolher se deseja iniciar uma nova partida.

```text
Do you want to play again? (y/n)
```

---

## 🏗️ Arquitetura do projeto

O projeto foi organizado em diferentes pacotes para separar responsabilidades:

```text
src/
├── main/
│   └── java/
│       └── com/
│           └── anaclarissi/
│               └── numberguessinggame/
│                   │
│                   ├── application/
│                   │   ├── GameService.java
│                   │   └── ScoreService.java
│                   │
│                   ├── domain/
│                   │   ├── exception/
│                   │   │   ├── InvalidGuessException.java
│                   │   │   └── OutOfAttemptsException.java
│                   │   │
│                   │   ├── model/
│                   │   │   ├── Difficulty.java
│                   │   │   ├── GameRound.java
│                   │   │   ├── Guess.java
│                   │   │   ├── GuessResult.java
│                   │   │   ├── ScoreRecord.java
│                   │   │   └── SecretNumber.java
│                   │   │
│                   │   └── service/
│                   │       ├── GameClockPort.java
│                   │       ├── GuessEvaluator.java
│                   │       ├── HintService.java
│                   │       ├── NumberGeneratorPort.java
│                   │       └── ScoreRepositoryPort.java
│                   │
│                   ├── infrastructure/
│                   │   ├── clock/
│                   │   │   └── SystemGameClock.java
│                   │   │
│                   │   ├── persistence/
│                   │   │   └── InMemoryScoreRepository.java
│                   │   │
│                   │   └── random/
│                   │       └── RandomNumberGenerator.java
│                   │
│                   ├── presentation/
│                   │   ├── ConsoleInputReader.java
│                   │   ├── ConsoleView.java
│                   │   └── GameRunner.java
│                   │
│                   └── Main.java
│
└── test/
    └── java/
        └── com/
            └── anaclarissi/
                └── numberguessinggame/
                    ├── application/
                    ├── domain/
                    └── infrastructure/
```

---

## 🧩 Organização das camadas

### Application

Contém os serviços responsáveis por coordenar os casos de uso da aplicação.

#### `GameService`

Responsável por:

* iniciar novas partidas;
* gerar o número secreto;
* processar os palpites do jogador.

#### `ScoreService`

Responsável por:

* registrar resultados;
* consultar o melhor resultado de uma determinada dificuldade.

---

### Domain

É o núcleo das regras do jogo.

Contém os modelos, exceções e serviços relacionados diretamente às regras de negócio.

#### Model

Principais classes:

* `GameRound`
* `Guess`
* `SecretNumber`
* `ScoreRecord`
* `Difficulty`
* `GuessResult`

A classe `GameRound`, por exemplo, controla o estado de uma rodada, incluindo tentativas utilizadas, vitória, finalização e tempo decorrido.

---

### Domain Services

Contém regras que não pertencem diretamente a uma única entidade.

#### `GuessEvaluator`

Determina se um palpite está:

```text
TOO_LOW
TOO_HIGH
CORRECT
```

#### `HintService`

Responsável pela geração das dicas.

---

### Ports

O projeto utiliza interfaces para definir contratos entre o domínio e as implementações externas.

Exemplos:

```java
GameClockPort
NumberGeneratorPort
ScoreRepositoryPort
```

Isso reduz o acoplamento entre as regras do jogo e detalhes de infraestrutura.

---

### Infrastructure

Contém implementações concretas de recursos externos ao domínio.

#### `RandomNumberGenerator`

Implementa:

```java
NumberGeneratorPort
```

e utiliza `java.util.Random` para gerar os números.

#### `SystemGameClock`

Implementa:

```java
GameClockPort
```

e utiliza `Instant` e `Duration` para controlar o tempo.

#### `InMemoryScoreRepository`

Implementa:

```java
ScoreRepositoryPort
```

e utiliza um `HashMap` para armazenar os melhores resultados durante a execução da aplicação.

---

### Presentation

Responsável pela interação com o usuário através do terminal.

Principais classes:

* `ConsoleInputReader`
* `ConsoleView`
* `GameRunner`

Essa separação evita colocar regras de negócio diretamente na entrada e saída do programa.

---

## 🧪 Testes automatizados

O projeto possui testes automatizados utilizando **JUnit 5**.

Também foi utilizado **Mockito** para criar mocks em testes que dependem de determinadas interfaces.

Os testes cobrem diferentes partes da aplicação.

### Testes de Application

`GameServiceTest`

Testa:

* criação de uma nova rodada;
* geração do número secreto;
* resultado de um palpite menor;
* resultado de um palpite maior;
* resultado de um palpite correto.

`ScoreServiceTest`

Testa:

* registro de resultados;
* recuperação de recordes;
* ausência de recordes;
* manutenção do melhor resultado por dificuldade.

---

### Testes de Domain

Foram criados testes para:

* `Difficulty`
* `Guess`
* `ScoreRecord`
* `SecretNumber`
* `GuessEvaluator`
* `HintService`

Os testes verificam tanto comportamentos válidos quanto situações que devem gerar exceções.

Por exemplo, `GuessTest` verifica que valores fora do intervalo de 1 a 100 são rejeitados.

---

### Testes de Infrastructure

Também existem testes para:

* `SystemGameClock`
* `InMemoryScoreRepository`
* `RandomNumberGenerator`

O gerador aleatório, por exemplo, é testado várias vezes para verificar se os números permanecem dentro do intervalo esperado.

---

## 🛠️ Tecnologias utilizadas

* **Java**
* **JUnit 5**
* **Mockito**
* **Maven**
* **Git**
* **GitHub**

### Conceitos praticados

* Programação Orientada a Objetos
* Encapsulamento
* Enum
* Interfaces
* Exceções personalizadas
* Injeção de dependência
* Separação de responsabilidades
* Testes automatizados
* Mocks
* Repository Pattern
* Ports and Adapters
* Organização em camadas
* Domain Model

---

## ▶️ Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/anaClarissi/number-guessing-game.git
```

### 2. Acesse a pasta do projeto

```bash
cd number-guessing-game
```

### 3. Execute os testes

Caso esteja utilizando Maven:

```bash
mvn test
```

### 4. Execute a aplicação

O ponto de entrada da aplicação é:

```text
Main.java
```

Execute a classe `Main` pela sua IDE ou utilizando o Maven configurado para o projeto.

---

## 🎮 Exemplo de execução

Uma partida pode seguir este fluxo:

```text
Welcome to the Number Guessing Game!
I'm thinking of a number between 1 and 100.

Please select the difficulty level:
1. Easy (10 chances)
2. Medium (5 chances)
3. Hard (3 chances)

Your choice: 2

Enter a value: 30

Incorrect! The number is greater than 30.

Enter a value: 70

Incorrect! The number is less than 70.

Enter a value (enter 'hint' to get a hint): hint

You are very close!

Enter a value: 64

Congratulations! You guessed the correct number in 3 attempt(s)
```

---

## 🔎 Decisões de projeto

Uma das principais decisões foi evitar que a lógica principal do jogo ficasse concentrada na classe `Main`.

A aplicação utiliza diferentes componentes para cada responsabilidade.

Por exemplo:

```text
Main
 ↓
GameRunner
 ↓
GameService
 ↓
GameRound
 ↓
GuessEvaluator
```

Enquanto recursos como geração aleatória, persistência e controle de tempo são acessados através de interfaces.

Isso facilita a manutenção e permite substituir implementações sem precisar alterar as regras principais do jogo.

---

## 🔌 Ports e desacoplamento

Um dos pontos importantes do projeto é o uso de interfaces para abstrair algumas dependências.

Por exemplo:

```java
public interface NumberGeneratorPort {

    int generate(int min, int max);

}
```

A aplicação não precisa conhecer diretamente `Random`.

Ela depende apenas do contrato definido por `NumberGeneratorPort`.

A implementação concreta fica em:

```text
infrastructure/random/
└── RandomNumberGenerator.java
```

O mesmo conceito é aplicado ao relógio e ao armazenamento dos recordes.

---

## 🚧 Possíveis melhorias futuras

Embora o projeto cumpra a proposta do desafio, existem várias possibilidades de evolução.

### 💾 Persistência real

Atualmente, os recordes são armazenados em memória.

Uma evolução seria utilizar:

* MySQL;
* PostgreSQL;
* SQLite;
* MongoDB.

Isso permitiria manter os recordes mesmo depois de fechar a aplicação.

---

### 🖥️ Interface gráfica

O jogo atualmente funciona através do terminal.

Uma próxima versão poderia utilizar:

* JavaFX;
* Swing;
* uma aplicação web.

Isso permitiria criar uma experiência mais visual e interativa.

---

### 👤 Sistema de jogadores

Adicionar usuários permitiria armazenar:

```text
Nome
Pontuação
Dificuldade
Tentativas
Tempo
Data da partida
```

Também seria possível criar um ranking.

---

### 🏆 Ranking global

Além do melhor resultado individual por dificuldade, poderia ser implementado um ranking com os melhores jogadores.

Exemplo:

```text
🏆 Ranking

1. Ana       - 1 tentativa
2. Carlos    - 2 tentativas
3. Maria     - 2 tentativas
4. João      - 3 tentativas
```

---

### 📈 Estatísticas

O sistema poderia apresentar informações como:

* quantidade de partidas;
* taxa de vitória;
* média de tentativas;
* melhor tempo;
* dificuldade mais jogada;
* maior sequência de vitórias.

---

### 🌐 Transformar em aplicação web

Outra possibilidade seria transformar o projeto em uma aplicação web, utilizando Java no backend e uma interface frontend.

Por exemplo:

```text
Frontend
HTML + CSS + JavaScript

        ↓

Backend
Java + Spring Boot

        ↓

Database
MySQL
```

---

### 🧪 Aumentar a cobertura de testes

Também seria possível adicionar testes para cenários adicionais, como:

* tentativa após o fim da rodada;
* tentativa exatamente no limite de tentativas;
* vitória na última tentativa disponível;
* solicitação de dica em diferentes situações;
* comportamento do cronômetro;
* entradas inválidas na interface.

---

## 📚 Origem do projeto

Este projeto foi desenvolvido **a partir do desafio Number Guessing Game do roadmap.sh**.

O desafio propõe a criação de um jogo de adivinhação de números para praticar conceitos fundamentais de programação e desenvolvimento de aplicações.

🔗 **Desafio original:**
https://roadmap.sh/projects/number-guessing-game

A implementação deste repositório adiciona uma estrutura organizada em camadas, testes automatizados, sistema de dificuldades, dicas, cronômetro e gerenciamento de recordes.

---

## 👩‍💻 Sobre mim

Olá! Eu sou **Ana Clarissi**, estudante e desenvolvedora em formação, interessada principalmente em desenvolvimento de software e programação.

Este projeto faz parte da minha jornada de aprendizado e prática com **Java**, especialmente na construção de aplicações organizadas, testáveis e com responsabilidades bem definidas.

### 🔗 Me encontre

**LinkedIn:**
https://www.linkedin.com/in/anaclarissi/

**roadmap.sh:**
https://roadmap.sh/account/update-profile

**Desafio utilizado neste projeto:**
https://roadmap.sh/projects/number-guessing-game

---

## ⭐ Objetivo do projeto

Mais do que apenas criar um jogo simples, este projeto teve como objetivo praticar a construção de uma aplicação Java organizada, explorando conceitos importantes como:

```text
Java
  ↓
OOP
  ↓
Domain Model
  ↓
Services
  ↓
Interfaces / Ports
  ↓
Infrastructure
  ↓
Testing
```

O projeto representa uma etapa de aprendizado e pode servir como base para futuras versões mais completas da aplicação.

---

## 📄 Licença

Este projeto foi desenvolvido para fins de estudo e prática de programação.

Sinta-se à vontade para explorar o código e utilizar o projeto como referência para seus próprios estudos.

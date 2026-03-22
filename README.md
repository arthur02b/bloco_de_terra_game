# bloco_de_terra_game

RPG de turnos em terminal com temática de Minecraft, desenvolvido em Java como trabalho da disciplina de Programação Orientada a Objetos.

## Sobre o jogo

Steve acorda na beira de uma caverna ao anoitecer. Um baú abandonado com alguns itens básicos é tudo que ele tem. A partir daí, ele desce para as profundezas, enfrenta monstros, encontra baús com equipamentos melhores e busca chegar ao nível 10 para enfrentar o boss final: o temido **Zumbi Bebê**.

## Como jogar

**Pré-requisitos**
- JDK 11 ou superior instalado

**Compilar**
```bash
javac *.java
```

**Executar**
```bash
java Main
```

## Mecânicas

**Progressão**
- Steve começa no nível 1 com 10 corações e uma espada de madeira
- Cada monstro derrotado concede XP
- Ao acumular XP suficiente, Steve sobe de nível
- No nível 10, o boss final é invocado automaticamente

**Tabela de XP por nível**

| Nível | XP necessário |
|:-----:|:-------------:|
| 1 | 15 |
| 2 | 18 |
| 3 | 22 |
| 4 | 25 |
| 5 | 28 |
| 6 | 30 |
| 7 | 33 |
| 8 | 36 |
| 9 | 40 |
| 10 | Boss |

**Combate**
- Steve e o inimigo se alternam nos ataques
- A cada turno o jogador pode atacar, usar um item ou fugir
- O dano é fixo e determinado pela espada equipada

**Espadas**

| Espada   | Raridade | Dano |
|----------|----------|------|
| Madeira  | Comum    | 2    |
| Pedra    | Incomum  | 3    |
| Ferro    | Raro     | 5    |
| Diamante | Épico    | 7    |

A espada de madeira está garantida no baú inicial. As demais aparecem em baús dentro da caverna — diamante com baixa probabilidade.

**Monstros**

| Monstro           | Corações | Dano | XP |
|-------------------|----------|------|----|
| Zumbi             | 6        | 2    | 15 |
| Spider            | 7        | 2    | 18 |
| Esqueleto         | 8        | 3    | 20 |
| Creeper           | 5        | 4    | 22 |
| Zumbi Bebê (boss) | 20       | 5    | —  |

**Eventos na caverna**
- 60% — encontro com monstro
- 30% — baú com itens
- 10% — mina abandonada (recupera 1 coração)

**Comida**

| Item         | Cura |
|--------------|------|
| Maçã         | 2    |
| Pão          | 3    |
| Carne        | 4    |
| Golden Apple | 6    |

## Estrutura do projeto

```
├── Entidade.java       # Classe abstrata base
├── Steve.java          # Personagem do jogador
├── Inimigo.java        # Classe abstrata base dos inimigos
├── Zumbi.java
├── Spider.java
├── Esqueleto.java
├── Creeper.java
├── ZumbiBebe.java      # Boss final
├── Item.java           # Classe abstrata base dos itens
├── Comida.java
├── Espada.java
├── Bau.java            # Gerador de itens aleatórios
├── Combate.java        # Sistema de turnos
├── Caverna.java        # Controle de eventos e exploração
└── Main.java           # Ponto de entrada e história
```

## Disciplina

Programação Orientada a Objetos — Universidade Católica de Brasília
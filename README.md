# Controle de Multas de Trânsito

## Integrantes
- Alice Vitória Boschetti
- Felipe Cordeiro Carvalho
- Eduardo Marques dos Santos

---

## Estrutura Geral do Sistema

O sistema é dividido em três pacotes principais:

- **modelo**: Contém as classes de dados e lógica do domínio.
- **servico**: Cuida da lógica de negócios, simula o banco de dados e aplica filtros.
- **ui**: Implementa as interfaces de interação com o usuário (terminal e gráfica).

### Funcionamento
1. Registrar e armazenar ocorrências de trânsito.
2. Processar as regras para calcular multas com base nas ocorrências registradas.

---

## Detalhamento das Classes

### Pacote `modelo` (Lógica do Domínio)
#### **Ocorrencia**
- Representa uma ocorrência de trânsito.
- Armazena informações como: placa, logradouro, data e velocidade.
- Exemplo: "Placa ABC1234, Avenida Paulista, 2024-12-08, 80 km/h".

#### **RegraMulta**
- Classe abstrata que define o cálculo e a gravidade de multas.
- Métodos principais:
  - `calcularMulta(Ocorrencia ocorrencia)`: Calcula o valor da multa.
  - `verificaNivelMulta(Ocorrencia ocorrencia)`: Determina a gravidade da infração.

#### **RegraVelocidade**
- Extensão de `RegraMulta` para infrações de velocidade.
- Multas baseadas no excesso de velocidade:
  - Até 20 km/h: leve.
  - 20-50 km/h: média.
  - Acima de 50 km/h: grave.

#### **RegraRodizio**
- Extensão de `RegraMulta` para infrações de rodízio.
- Avalia o último dígito da placa e o dia da semana.
- Exemplo: Placa "ABC1234" não pode circular às segundas-feiras.

---

### Pacote `servico` (Lógica de Negócios)
#### **BaseDeDados**
- Simula um banco de dados em memória.
- Métodos para adicionar e buscar ocorrências.
- Exemplo: Armazena "Placa ABC1234, Avenida Paulista, 2024-12-08, 80 km/h".

#### **FiltroOcorrencias**
- Aplica filtros às ocorrências armazenadas, como data ou velocidade.

---

### Pacote `ui` (Interface com o Usuário)
#### **AppTerminal**
- Menu de interação no terminal.
- Funcionalidades:
  - Registrar ocorrências.
  - Buscar multas por placa.
- Exemplo: O usuário insere uma ocorrência e recebe o valor da multa.

#### **TelaPrincipal**
- Interface gráfica utilizando Swing.
- Funcionalidades:
  - Adicionar ocorrências.
  - Buscar ocorrências por placa.
- Os resultados são exibidos em janelas de diálogo ou painéis.

---

## Funcionamento do Sistema

### 1. Registrar Ocorrências
- O usuário insere os dados de uma ocorrência no terminal ou interface gráfica.
- A ocorrência é armazenada na `BaseDeDados`.
- Exemplo:
  - Placa: "ABC1234"
  - Logradouro: "Avenida Paulista"
  - Data: "2024-12-08"
  - Velocidade: 80 km/h

### 2. Aplicar Regras
- As regras (como `RegraVelocidade` ou `RegraRodizio`) são aplicadas às ocorrências.
- Exemplo:
  - Limite: 60 km/h
  - Velocidade registrada: 80 km/h
  - Resultado: Multa leve de R$ 130,16.

### 3. Exibir Resultados
- O sistema exibe os resultados no terminal ou na interface gráfica.






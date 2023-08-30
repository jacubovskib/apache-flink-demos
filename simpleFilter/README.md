# Aplicação Apache Flink para Processamento de Dados em Tempo Real com Kafka

Este repositório contém uma aplicação de exemplo que utiliza o Apache Flink para consumir dados de um tópico Kafka, aplicar um filtro sobre esses dados e, em seguida, enviar os resultados processados para outro tópico Kafka. Essa aplicação demonstra como realizar processamento de dados em tempo real de maneira eficiente e escalável, aproveitando a capacidade de streaming do Apache Flink em conjunto com a plataforma de mensagens Kafka.

## Pré-requisitos

Certifique-se de ter os seguintes componentes instalados antes de executar a aplicação:

- Java 20
- Apache Flink 1.17.1
- Apache Kafka 3.2.2
- Dependências do projeto (consulte o arquivo `pom.xml` para detalhes)

## Configuração

1. **Configuração do Kafka**: Certifique-se de ter os tópicos Kafka criados e configurados corretamente para entrada e saída de dados. Você precisará ter acesso aos detalhes de conexão, como endereço do broker Kafka, tópicos de entrada e saída, etc. (docker-compose)

2. **Configuração do Flink**: A aplicação Apache Flink requer configurações específicas para conexão com o Kafka. Isso geralmente envolve definir as propriedades corretas relacionadas aos brokers Kafka, grupos de consumo, etc. Essas configurações podem ser passadas via _Cli Arguments_ ou passadas diretamente ao código da aplicação.

3. **Código da Aplicação**: O código-fonte da aplicação está localizado no diretório `src`. Você precisará configurar o filtro ou transformações que deseja aplicar aos dados recebidos do tópico Kafka de entrada. Consulte o arquivo `Main.java` para mais detalhes.

## Execução
Obs.: É possível utilizar o Intellij para rodar a aplicação.

1. Dockerfile configurado para buildar e executar a aplicação.

2. A aplicação começará a consumir dados do tópico Kafka de entrada, aplicar o filtro ou transformações especificadas e enviar os resultados processados para o tópico Kafka de saída.

## Contribuição

Se você quiser contribuir para este projeto, sinta-se à vontade para criar pull requests ou reportar problemas na seção de problemas. Ficaremos felizes em revisar e incorporar suas contribuições.

## Aviso

Esta é uma aplicação de exemplo destinada a fins educacionais. Certifique-se de adaptar e ajustar a aplicação de acordo com os requisitos e padrões de sua própria infraestrutura e aplicação.

**Autor:** Breno Jacubovski
**Contato:** jacubovski.breno@outlook.com
**Linkedin:** [https://www.linkedin.com/in/breno-jacubovski/](https://www.linkedin.com/in/breno-jacubovski/)
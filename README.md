# 📚 Biblioteca Pessoal - API REST com Spring Boot

**Descrição**  
Este projeto é uma API REST desenvolvida com **Java** e **Spring Boot** para gerenciar uma biblioteca pessoal. A aplicação permite organizar livros que você já leu, está lendo ou deseja ler, além de criar listas personalizadas para categorizá-los de forma prática e eficiente.

## **Funcionalidades Principais**
- **Gerenciamento de Livros**:
  - Adicionar livros com informações detalhadas, incluindo título, autor, ISBN, gênero, tipo (físico ou digital) e status de leitura.
  - Atualizar informações e avaliar os livros com notas de 1 a 5 estrelas.
  - Filtrar e buscar livros por diferentes critérios, como autor, gênero ou tipo.
- **Listas Personalizadas**:
  - Criar listas temáticas (ex.: "Favoritos", "Clássicos da Literatura").
  - Adicionar ou remover livros das listas.
- **Estatísticas**:
  - Visualizar estatísticas como a quantidade de livros lidos, em leitura ou desejados.
  - Contagem de livros físicos e digitais.
- **Histórico de Atualizações**:
  - Rastrear mudanças no status de leitura.

## **Estrutura do Projeto**
- **Backend**: Java com Spring Boot
- **Banco de Dados**: Postgres
- **Gerenciamento de Dependências**: Maven
- **Documentação da API**: Swagger (OpenAPI)
- **Testes**: JUnit e Mockito

## **Rotas Principais**
- **/livros**: Gerenciamento dos livros na biblioteca.
- **/listas**: Criação e gerenciamento de listas personalizadas.

## **Tecnologias Utilizadas**
- **Linguagem**: Java 17+
- **Frameworks**: Spring Boot, Spring Data JPA, Spring Web
- **Banco de Dados**: Postgres, MySQL
- **Testes**: JUnit 5, Mockito
- **Documentação**: Swagger/OpenAPI

# 🛍️ Shop API - Desafio Técnico de Engenharia de Software

Este projeto é a implementação da API backend para o desafio técnico de construção de uma página de detalhes de produto, inspirado no Mercado Livre.

A aplicação foi desenvolvida como um **monólito modular** utilizando **Java 22** e **Spring Boot**, com uma arquitetura fortemente baseada nos princípios de **Domain-Driven Design (DDD)** para garantir organização, manutenibilidade e escalabilidade.

## ✨ Principais Funcionalidades

- **API RESTful Completa:** Fornece todos os dados necessários para a renderização da página de detalhes do produto.
- **Agregação de Dados:** O endpoint principal orquestra e retorna informações consolidadas de diferentes domínios (produto, vendedor, estoque e avaliações).
- **Persistência Local:** Utiliza arquivos JSON para persistência de dados, eliminando a necessidade de um banco de dados externo e facilitando a execução do projeto.
- **Tratamento de Erros:** Respostas de erro padronizadas para uma experiência de desenvolvimento consistente.
- **Alta Cobertura de Testes:** Cobertura de testes unitários superior a 80%, garantindo a confiabilidade do código.

---

## 🏛️ Arquitetura e Decisões de Design

A decisão mais importante foi estruturar a aplicação como um **Monólito Modular** guiado pelo DDD. Essa abordagem foi escolhida por ser ideal para um projeto de escopo definido, ao mesmo tempo que estabelece uma base sólida para futuras expansões, como a extração de microsserviços.

### Módulos como Bounded Contexts

A aplicação é dividida em módulos que representam os **Contextos Delimitados (Bounded Contexts)** do negócio. Isso aumenta a **coesão** e diminui o **acoplamento**, tornando o sistema mais fácil de entender e manter.

- `product`: Responsável pelo catálogo: título, descrição, preço e imagens.
- `seller`: Contém as informações do vendedor, como nome e reputação.
- `inventory`: Gerencia o controle de estoque dos produtos.
- `review`: Lida com as avaliações, incluindo nota e quantidade.
- `bff (Backend for Frontend)`: Orquestra os outros módulos e expõe a API pública, simplificando o consumo pelo cliente.

### Arquitetura em Camadas (Onion Architecture)

Cada módulo internamente segue uma arquitetura em camadas para separar as responsabilidades:

- **Domain**: O coração do módulo. Contém as entidades, objetos de valor e regras de negócio puras, sem dependências de frameworks.
- **Application**: Orquestra os casos de uso, utilizando os objetos de domínio para executar as ações.
- **Infrastructure**: Implementa detalhes técnicos, como a persistência de dados (leitura/escrita de arquivos JSON) e a integração com outras ferramentas.
- **Presentation**: A camada de entrada. Expõe os endpoints REST e lida com as requisições e respostas HTTP.

Essa estrutura torna o código resiliente a mudanças em tecnologias externas e **altamente testável**, além de facilitar a adição de novas funcionalidades, como a implementação de um sistema de descontos, de forma isolada e segura.

---

## 🔧 Tecnologias Utilizadas

- **Java 22** e **Spring Boot 3.5**: Plataforma robusta para criação de APIs.
- **Maven**: Gerenciamento de dependências.
- **JUnit 5 & Mockito**: Para testes unitários e de integração.
- **Lombok**: Para reduzir código boilerplate.
- **Jackson**: Para manipulação de JSON.

---

## 🚀 Como Executar

### Pré-requisitos

- Java 22 ou superior
- Maven 3.8+

### 1. Clone o repositório

```bash
git clone git@github.com:guibernardino/meli-shop.git
cd meli-shop
```

### 2. Execute a aplicação

```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### Endpoints da API

O principal endpoint para buscar os detalhes de um produto é:

- `GET /api/v1/product-details/{productId}`

Você pode explorar todos os endpoints disponíveis através da interface do Swagger UI:

- **Swagger UI**: `http://localhost:8080/swagger-ui/index.html`

---

## 🧪 Testes

Para rodar a suíte completa de testes e verificar a cobertura de código:

```bash
./mvnw test
```
# Backend

Um serviço backend baseado em Spring Boot implementando arquitetura hexagonal, projetado para servir como arquétipo para portais de produtos digitais. Este serviço fornece recursos de gerenciamento de clientes com integração DynamoDB e recursos abrangentes de logging/monitoramento.

## Features


- Arquitetura Hexagonal (Padrão de Portas e Adaptadores)
- Integração com DynamoDB
- Especificação OpenAPI
- Logging Abrangente com Mascaramento
- Verificações de Saúde
- Configuração Docker Compose
- MapStruct para Mapeamento de Objetos
- Testes Unitários e de Integração

## Prerequisites

- Java 21
- Maven
- Docker & Docker Compose (pode ser utilizado o Podman)
- AWS CLI (configurado com perfil local)

## Quick Start

1. Clone o repositório
```bash
git clone <repository-url>
```

2. Inicie o DynamoDB Local com Docker Compose (ou pelo Podman Desktop)
```bash
docker-compose -f docker/compose.yml up -d
```

3. Exdecute o build do projeto
```bash
mvn clean install
```

4. Execute o projeto com o perfil local
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

## Estrutura do Projeto

```
├── application/                 # Application layer
│   ├── adapter/                 # Controllers and DTOs
│   └── mappers/                 # DTO to Domain mappers
├── domain/                      # Domain layer
│   ├── model/                   # Domain entities
│   ├── ports/                   # Port interfaces
│   └── service/                 # Domain services
└── infrastructure/              # Infrastructure layer
    ├── database/                # DynamoDB configuration
    ├── health/                  # Health checks
    └── log/                     # Logging configuration
```
+## Desenvolvimento da API

### Estendendo a API com OpenAPI Generator

1. Localize o arquivo de especificação OpenAPI:
   ```
   /src/main/resources/serverapi/api.yaml
   ```

2. Adicione seu novo endpoint no arquivo api.yaml:
   ```yaml
   /novo-endpoint:
     get:
       summary: Descrição do novo endpoint
       operationId: novoEndpoint
       responses:
         '200':
           description: Sucesso
   ```

3. Execute a geração de código:
   ```bash
   mvn clean generate-sources
   ```

4. Implemente a interface gerada:
   ```java
   @Override
   public ResponseEntity<Void> novoEndpoint() {
       // Sua implementação aqui
   }
   ```

### Boas Práticas

1. Mantenha o contrato primeiro (contract-first):
   - Sempre comece definindo a API no arquivo api.yaml
   - Use descrições claras e documentação adequada

2. Nomeação de Operações:
   - Use verbos para operationId: getCustomer, createCustomer
   - Mantenha consistência nos nomes dos endpoints

3. Versionamento:
   - Considere incluir versão na URL: /v1/customers
   - Documente breaking changes

## Visão geral da Arquitetura

O projeto segue os princípios da arquitetura hexagonal:  - Camada de Domínio: Contém a lógica de negócio e os modelos de domínio - Camada de Aplicação: Gerencia requisições e respostas HTTP - Camada de Infraestrutura: Implementa dependências externas (DynamoDB, logging)
Diretrizes de Desenvolvimento
Padrões de Código
- Seguir convenções padrão de nomenclatura Java - Utilizar Lombok para redução de código boilerplate - Implementar interfaces para baixo acoplamento - Escrever testes unitários para todas as novas funcionalidades
  Fluxo de Trabalho Git 
- Criar branch de feature a partir do  develop  2. Seguir commits convencionais 3. Submeter PR para revisão 4. Realizar merge com squash para  develop  
Testes
- Testes unitários com JUnit 5 - Testes de integração com TestContainers - Testes de API com REST Assured

## API Documentation

OpenAPI specification is available at:
- Local: http://localhost:9000/swagger-ui.html

## TODO List

### High Priority
- [x] Implement Caffeine caching layer
- [x] Add comprehensive monitoring (metrics, tracing)
- [ ] Enhance error handling
- [x] Add request validation

### Medium Priority
- [ ] Add API versioning
- [ ] Enhance test coverage

### Low Priority
- [ ] Add developer tools
- [ ] Enhance documentation


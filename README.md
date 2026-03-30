# 🚀 Projeto de Microserviços com Spring Boot + Feign

## 🧠 Visão Geral

Este projeto demonstra uma arquitetura de microserviços utilizando Spring Boot, OpenFeign e comunicação REST.

O sistema é composto por três serviços principais:

* **user-service** → Gerencia usuários e persiste dados
* **geo-service** → Integra com API externa (ViaCEP)
* **score-service** → Calcula o score do usuário com base em regras de negócio

---

## 🏗️ Arquitetura

```
[ Cliente ]

    ↓

user-service
    ↓
geo-service → ViaCEP (API externa)

score-service → user-service
```

---

## 🧩 Responsabilidades

### 👤 user-service

* Criar usuários
* Armazenar endereço usando `@Embeddable`
* Buscar dados do usuário

---

### 🌍 geo-service

* Buscar dados de endereço via API externa
* Validar CEP
* Retornar endereço estruturado

---

### 🧠 score-service

* Calcular score do usuário
* Aplicar regras de negócio
* Retornar classificação

---

## 🔄 Fluxos Principais

### 🚀 Criar Usuário

```
POST /users
```

1. user-service recebe nome + CEP
2. Chama geo-service
3. geo-service chama ViaCEP
4. Retorna endereço
5. user-service salva tudo

---

### 🔍 Buscar Usuário

```
GET /users/{id}
```

* Busca direto no banco
* NÃO chama API externa

---

### 🧠 Calcular Score

```
GET /score/{userId}
```

1. score-service chama user-service
2. Aplica regras
3. Retorna score

---

## ✅ Checklist de Implementação

---

### 🚀 Fase 1 — geo-service

#### Setup

* [x] Criar projeto Spring Boot
* [x] Adicionar Spring Web

#### Integração

* [x] Criar ViaCepClient (Feign ou RestTemplate)
* [x] Criar DTO: ViaCepResponse

#### Regra de Negócio

* [x] Criar GeoService
* [x] Tratar CEP inválido (`erro = true`)

#### Endpoint

* [x] GET /geo/{cep}

#### Teste

* [x] Testar no Postman

---

### 🚀 Fase 2 — user-service

#### Setup

* [ ] Criar projeto Spring Boot
* [ ] Adicionar:

  * Spring Web
  * Spring Data JPA
  * Banco (H2 ou PostgreSQL)
  * OpenFeign

#### Configuração

* [ ] Habilitar Feign (`@EnableFeignClients`)

#### Modelagem

* [ ] Criar entidade Usuario
* [ ] Criar Address com `@Embeddable`

#### Integração

* [ ] Criar GeoClient (Feign → geo-service)

#### Regra de Negócio

* [ ] Criar UsuarioService
* [ ] No CREATE:

  * Chamar geo-service
  * Montar Address
  * Salvar usuário

#### Endpoints

* [ ] POST /users
* [ ] GET /users/{id}

#### Testes

* [ ] Criar usuário com CEP
* [ ] Validar endereço salvo
* [ ] Garantir que GET não chama geo-service

---

### 🚀 Fase 3 — score-service

#### Setup

* [ ] Criar projeto Spring Boot
* [ ] Adicionar:

  * Spring Web
  * OpenFeign

#### Integração

* [ ] Criar UserClient (Feign → user-service)

#### Regra de Negócio

* [ ] Criar ScoreService
* [ ] Implementar regras:

  * Renda
  * Estado (UF)
  * Idade (opcional)

#### Endpoint

* [ ] GET /score/{userId}

#### Testes

* [ ] Criar usuário
* [ ] Chamar endpoint de score
* [ ] Validar cálculo

---

### 🔥 Fase 4 — Melhorias

#### Resiliência

* [ ] Adicionar fallback (Feign + Resilience4j)
* [ ] Tratar falha entre serviços

#### Performance

* [ ] Cachear consultas de CEP no geo-service

#### Qualidade

* [ ] Usar DTOs (não expor entidades)
* [ ] Criar handler global de exceções

---

### 🧠 Fase 5 — Avançado (Opcional)

#### Observabilidade

* [ ] Logs estruturados

#### Arquitetura

* [ ] API Gateway
* [ ] Service Discovery

#### Assíncrono

* [ ] Evento: UserCreated
* [ ] Recalcular score automaticamente

---

## 🎯 Ordem de Implementação

```
1. geo-service
2. user-service
3. score-service
4. melhorias
```

---

## 💥 Resultado Final

Ao concluir este projeto, você terá:

* Arquitetura de microserviços funcional
* Integração com API externa
* Comunicação entre serviços com Feign
* Regras de negócio distribuídas

---

## 📌 Tecnologias Utilizadas

* Java + Spring Boot
* Spring Web
* Spring Data JPA
* OpenFeign
* H2 / PostgreSQL

---

## 🚀 Próximos Passos

* Implementar autenticação (JWT)
* Dockerizar os serviços
* Adicionar monitoramento (Prometheus, Grafana)

---

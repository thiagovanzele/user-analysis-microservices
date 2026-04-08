# 🚀 TECH CHALLENGE - DETALHAMENTO DE TASKS

---

# 👤 PESSOA A — USER (CORE)

## TASK A1 — Entidade User
* [ ] Criar classe User com JPA
* [ ] Criar enum UserType
* [ ] Mapear campos corretamente

✔ Pronto quando:
* [ ] Tabela criada automaticamente no banco

---

## TASK A2 — Repository
* [ ] Criar UserRepository
* [ ] Métodos:
* [ ] findByEmail
* [ ] findByNomeContaining

✔ Pronto quando:
* [ ] Consultas funcionando

---

## TASK A3 — Service
* [ ] Criar UserService
* [ ] Implementar:
* [ ] createUser
* [ ] listUsers
* [ ] deleteUser
* [ ] Validar email único
* [ ] Atualizar data automaticamente

✔ Pronto quando:
* [ ] Regras funcionando corretamente

---

## TASK A4 — Controller
* [ ] Criar endpoints:
* [ ] POST /users
* [ ] GET /users
* [ ] GET /users?name=
* [ ] DELETE /users/{id}

✔ Pronto quando:
* [ ] Endpoints respondem corretamente

---

# 📍 PESSOA B — ADDRESS

## TASK B1 — Entidade Address
* [ ] Criar classe Address
* [ ] Relacionar com User (1:1)

✔ Pronto quando:
* [ ] FK funcionando corretamente

---

## TASK B2 — Repository
* [ ] Criar AddressRepository

✔ Pronto quando:
* [ ] CRUD funcionando

---

## TASK B3 — Service
* [ ] Criar AddressService
* [ ] Salvar endereço junto com usuário
* [ ] Atualizar endereço

✔ Pronto quando:
* [ ] Endereço vinculado corretamente ao usuário

---

# 🔐 PESSOA C — AUTH

## TASK C1 — Login
* [ ] Criar endpoint POST /auth/login
* [ ] Validar login e senha

✔ Pronto quando:
* [ ] Retorna sucesso ou erro corretamente

---

## TASK C2 — Troca de senha
* [ ] Criar endpoint PUT /users/{id}/password
* [ ] Validar senha atual

✔ Pronto quando:
* [ ] Senha alterada corretamente

---

# 🍽️ PESSOA D — RESTAURANT

## TASK D1 — Entidade
* [ ] Criar Restaurant
* [ ] Relacionar com User

✔ Pronto quando:
* [ ] FK funcionando

---

## TASK D2 — Service
* [ ] Criar RestaurantService
* [ ] Validar tipo DONO

✔ Pronto quando:
* [ ] Apenas donos criam restaurante

---

## TASK D3 — Repository
* [ ] Criar RestaurantRepository

✔ Pronto quando:
* [ ] CRUD funcionando

---

# ⚙️ PESSOA E — INFRA / QUALIDADE

## TASK E1 — Docker
* [ ] Criar Dockerfile
* [ ] Criar docker-compose

✔ Pronto quando:
* [ ] Projeto sobe com docker-compose

---

## TASK E2 — Exceptions
* [ ] Criar GlobalExceptionHandler
* [ ] Implementar ProblemDetail

✔ Pronto quando:
* [ ] Erros padronizados

---

## TASK E3 — Swagger
* [ ] Documentar endpoints

✔ Pronto quando:
* [ ] Swagger acessível

---

## TASK E4 — Postman
* [ ] Criar collection:
* [ ] Cadastro
* [ ] Login
* [ ] Erros

✔ Pronto quando:
* [ ] Testes cobrindo principais cenários

---

# ✅ CHECKLIST FINAL

* [ ] CRUD funcionando
* [ ] Login funcionando
* [ ] Email único
* [ ] Troca de senha separada
* [ ] Swagger OK
* [ ] Postman OK
* [ ] Docker rodando
* [ ] Tratamento de erros OK

---

# 🚀 FIM

# 🛍️ DevStore

**DevStore** é um projeto de e-commerce criado para praticar e demonstrar boas práticas de desenvolvimento backend com Java.  
A aplicação está organizada em um repositório único (monorepo) e será expandida no futuro com uma interface frontend.

---

## 📚 Visão Geral

Este repositório agrupa os componentes da aplicação **DevStore**, começando com o backend e futuramente incluindo o frontend.  
A proposta é construir uma base sólida e escalável para um sistema de e-commerce completo.

### 🔧 Estrutura Atual

- [`/devstore_backend`](./devstore_backend): API RESTful desenvolvida em Java com Spring Boot.  
  👉 O README completo dessa aplicação está localizado dentro da pasta [`devstore_backend`](./devstore_backend).
- (em breve) `/devstore_frontend`: Interface web moderna para interação com a API.

---

## 🛠️ DevStore Backend

A **DevStore Backend** é uma API REST que permite o gerenciamento de produtos, categorias e usuários.  
Também inclui autenticação segura com JWT, importação de produtos externos via FakeAPI e documentação interativa via Swagger.

**Principais funcionalidades:**
- 🔐 Autenticação e registro de usuários com JWT  
- 📦 CRUD de produtos com associação a categorias  
- 🔗 Respostas com links HATEOAS  
- 🌐 Integração com FakeAPI  
- 📄 Documentação da API com Swagger

**Tecnologias utilizadas:**
- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA  
- Spring Security  
- Spring HATEOAS  
- MySQL  
- Lombok  
- RestTemplate  
- Hibernate Validator  
- JWT (com a biblioteca java-jwt)  
- SpringDoc OpenAPI (Swagger)

📁 Localização do projeto: [`/devstore_backend`](./devstore_backend)

---

## 🚧 Em breve

O projeto será complementado com o **DevStore Frontend**, que fornecerá uma interface web moderna para interação com a API.

---

## ✍️ Criado por

**Diego Cutrim**

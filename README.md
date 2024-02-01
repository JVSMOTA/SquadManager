# SquadManager

<div align="right">
  
[![HitCount](https://hits.dwyl.com/JVSMOTA/SquadManager.svg?style=flat-square)](http://hits.dwyl.com/JVSMOTA/SquadManager)

</div>

## 📝 Descrição

O SquadManager é uma aplicação web desenvolvida em Java com Spring Boot, projetada para simplificar o gerenciamento de squads em uma empresa.

## 🎯 Desafio

Construa uma aplicação web (preferencialmente NodeJS ou Java) no qual seja possível consultar, listar e persistir as squads de uma empresa e seus colaboradores. A persistência dos dados pode ser realizada de forma lógica. Persistir no banco de dados é um diferencial.

## 🏹 User Stories

**Como usuário do SquadManager**, eu quero ser capaz de:

1. **Consultar Squads:**
   - Para visualizar informações detalhadas sobre as squads existentes na empresa.
   - Para garantir uma rápida referência às equipes disponíveis.

2. **Listar Colaboradores de uma Squad:**
   - Para obter uma lista completa de membros pertencentes a uma squad específica.
   - Para facilitar a gestão de recursos humanos e atribuições de equipe.

3. **Persistir Dados de Squads e Colaboradores:**
   - Para garantir que as informações sobre squads e seus colaboradores sejam mantidas de forma lógica.
   - Persistir dados no banco de dados, se possível, para uma solução robusta.

## ✨ Principais Recursos

### 📋 Gestão de Squads
- Crie, liste, atualize e exclua squads de forma intuitiva.
- Associe colaboradores a squads para melhor organização e comunicação.

### ⚙️ Gerenciamento de Colaboradores
- Adicione, consulte, atualize e remova colaboradores com facilidade.
- Estabeleça associações claras entre colaboradores e suas respectivas squads.

### 🎉 Desafio Técnico Completo
- A aplicação atendeu ao desafio proposto, oferecendo uma solução em Java com Spring Boot.
- Persistência dos dados está sendo feita em PostegreSQL, com a opção de persistência no banco de dados para escalabilidade futura.

## Como Usar:

### Pré-requisitos:
- Certifique-se de ter o Java e o Spring Boot instalados em sua máquina.
- Clone este repositório para o seu ambiente local.

### Instalação e Configuração:
1. Execute a aplicação Spring Boot.
2. Acesse a API através do endpoint principal.

### Exemplos de Uso:

#### Criar um Colaborador:
```http
POST /colaboradores
{
  "nome": "Colaborador1",
  "cargo": "PO", // PO, SCRUM, DESIGNERUX, QA, DEVFRONT, DEVBACK
  "email": "colaborador1@gmail.com"
}
```

#### Criar um Squad:
```http
POST /squads
{
  "nome": "SquadA"
}
```

#### Adicionar um colaborador a um squad:
```http
POST /squads/{idSquad}/adicionar-colaborador
1 // Id do colaborador existente
```

#### Listar Todos os Squads:
```http
GET /squads
```
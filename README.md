# Recomeçar+

Reconstruindo vidas, conectando solidariedade.

---

## Descrição do Sistema

O **Recomeçar+** é uma plataforma digital que conecta vítimas de desastres naturais a voluntários e doadores, proporcionando auxílio ágil, gratuito e seguro.  
A plataforma simplifica o cadastro de pedidos de ajuda e ofertas de doação, prioriza atendimentos urgentes e promove uma rede eficiente de solidariedade.

---

## Funcionalidades

- Cadastro ágil de vítimas e necessidades (alimentos, abrigo, higiene, medicamentos, roupas, etc)
- Algoritmo de prioridade para pedidos urgentes
- Acompanhamento em tempo real e notificações de status
- Voluntários oferecem ajuda (doações, hospedagem, transporte, serviços de saúde)
- Correspondência inteligente entre ofertas e pedidos
- Histórico de contribuições, painel de voluntário e certificados digitais
- (Futuro) Alertas SMS e monitoramento por drones de áreas críticas

---

## Arquitetura da Solução

- **Model** — Entidades Java: Usuário, Categoria, PedidoAjuda, OfertaAjuda, Match, Acompanhamento, StatusPedido, StatusMatch, StatusAcompanhamento
- **DAO** — Acesso a dados (CRUD para cada entidade)
- **Service** — Regras de negócio, validação e exceções customizadas
- **BO** — Regras específicas (prioridade, filtragem, tipo de usuário)
- **Resource** — API RESTful (Jakarta REST/JAX-RS)
- **Banco de Dados** — Oracle, tabelas normalizadas, FKs ON DELETE CASCADE

---

## Boas Práticas

- Organização em camadas (Model, DAO, Service, BO, Resource)
- Integridade referencial via ON DELETE CASCADE
- Exceções customizadas para validação e registro não encontrado
- Código limpo, comentado e orientado a objetos

---

## Principais Endpoints REST

_(Detalhamento completo na próxima seção — cada endpoint listado com parâmetros de entrada e saída)_

- `/usuarios`
- `/categorias`
- `/status-pedido`
- `/pedidos-ajuda`
- `/status-acompanhamento`
- `/ofertas-ajuda`
- `/status-match`
- `/matches`
- `/acompanhamentos`

---

## Como rodar o projeto

1. **Clone o repositório**
2. **Configure o banco de dados Oracle** com as tabelas do projeto (DDL disponível em `/database/ddl.sql`)
3. **Compile e execute o projeto** usando Maven ou sua IDE Java favorita
4. **Acesse os endpoints REST** conforme documentação abaixo

---

## Equipe

- **Bruno Tizer** (RM: 559999)
- **Gabriel Dos Santos Souza** (RM: 560812)
- **Thomas Henrique Baute** (RM: 560649)

---

## Sobre

> "Quando um desastre natural atinge uma comunidade, cada minuto conta. O Recomeçar+ é a ponte digital entre quem precisa de ajuda urgente e quem quer estender a mão — tudo em uma plataforma simples, rápida e gratuita."

---

# Endpoints de Acompanhamentos

## Criar um novo acompanhamento

**POST /acompanhamentos**

Cria um novo acompanhamento.

**Body (JSON):**

```json
{
  "pedidoAjudaId": 1,
  "descricao": "Pedido recebeu novo status",
  "dataHora": "2024-06-08T14:00:00",
  "statusAcompanhamentoId": 2,
  "observacao": "Acompanhamento inicial"
}
```

_Obs: Os campos exatos podem variar conforme sua classe `Acompanhamento`. Ajuste conforme necessário!_

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todos os acompanhamentos

**GET /acompanhamentos**

Retorna uma lista de todos os acompanhamentos.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "id": 1,
    "pedidoAjudaId": 1,
    "descricao": "Pedido recebeu novo status",
    "dataHora": "2024-06-08T14:00:00",
    "statusAcompanhamentoId": 2,
    "observacao": "Acompanhamento inicial"
  }
  // ...
]
```

---

## Buscar acompanhamento por ID

**GET /acompanhamentos/{id}**

Retorna um acompanhamento específico pelo ID.

**Path Param:**

- `id` (integer): ID do acompanhamento

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto acompanhamento (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Acompanhamento não encontrado."`

---

## Atualizar acompanhamento existente

**PUT /acompanhamentos/{id}**

Atualiza um acompanhamento já existente.

**Path Param:**

- `id` (integer): ID do acompanhamento

**Body (JSON):**

```json
{
  "pedidoAjudaId": 1,
  "descricao": "Status atualizado",
  "dataHora": "2024-06-08T15:00:00",
  "statusAcompanhamentoId": 3,
  "observacao": "Observação editada"
}
```

_Obs: O campo `id` geralmente vem apenas na URL, não precisa enviar no corpo._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar acompanhamento por ID

**DELETE /acompanhamentos/{id}**

Remove um acompanhamento pelo ID.

**Path Param:**

- `id` (integer): ID do acompanhamento

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

# Endpoints de Categorias

## Criar uma nova categoria

**POST /categorias**

Cria uma nova categoria.

**Body (JSON):**

```json
{
  "nome": "Categoria Exemplo",
  "descricao": "Descrição da categoria"
}
```

_Obs: Os campos podem variar conforme sua classe `Categoria`._

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todas as categorias

**GET /categorias**

Retorna uma lista de todas as categorias.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "idCategoria": 1,
    "nome": "Categoria Exemplo",
    "descricao": "Descrição da categoria"
  }
  // ...
]
```

---

## Buscar categoria por ID

**GET /categorias/{id}**

Retorna uma categoria específica pelo ID.

**Path Param:**

- `id` (integer): ID da categoria

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto categoria (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Categoria não encontrada."`

---

## Atualizar categoria existente

**PUT /categorias/{id}**

Atualiza uma categoria já existente.

**Path Param:**

- `id` (integer): ID da categoria

**Body (JSON):**

```json
{
  "nome": "Novo nome",
  "descricao": "Nova descrição"
}
```

_Obs: O campo `idCategoria` pode ser omitido do corpo, pois o backend já define pelo parâmetro da URL._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar categoria por ID

**DELETE /categorias/{id}**

Remove uma categoria pelo ID.

**Path Param:**

- `id` (integer): ID da categoria

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

# Endpoints de Matches

## Criar um novo match

**POST /matches**

Cria um novo match.

**Body (JSON):**

```json
{
  "usuarioAId": 1,
  "usuarioBId": 2,
  "status": "pendente"
}
```

_Obs: Ajuste os campos conforme o seu model `Match`._

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todos os matches

**GET /matches**

Retorna uma lista de todos os matches.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "id": 1,
    "usuarioAId": 1,
    "usuarioBId": 2,
    "status": "pendente"
  }
  // ...
]
```

---

## Buscar match por ID

**GET /matches/{id}**

Retorna um match específico pelo ID.

**Path Param:**

- `id` (integer): ID do match

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto match (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Match não encontrado."`

---

## Atualizar match existente

**PUT /matches/{id}**

Atualiza um match já existente.

**Path Param:**

- `id` (integer): ID do match

**Body (JSON):**

```json
{
  "usuarioAId": 1,
  "usuarioBId": 2,
  "status": "confirmado"
}
```

_Obs: O campo `id` pode ser omitido do corpo, pois o backend já define pelo parâmetro da URL._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar match por ID

**DELETE /matches/{id}**

Remove um match pelo ID.

**Path Param:**

- `id` (integer): ID do match

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

# Endpoints de Ofertas de Ajuda

## Criar uma nova oferta de ajuda

**POST /ofertas-ajuda**

Cria uma nova oferta de ajuda.

**Body (JSON):**

```json
{
  "usuarioId": 1,
  "titulo": "Ajuda com transporte",
  "descricao": "Posso ajudar com carona para o local.",
  "status": "ativa"
}
```

_Obs: Ajuste os campos conforme o seu model `OfertaAjuda`._

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todas as ofertas de ajuda

**GET /ofertas-ajuda**

Retorna uma lista de todas as ofertas de ajuda.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "id": 1,
    "usuarioId": 1,
    "titulo": "Ajuda com transporte",
    "descricao": "Posso ajudar com carona para o local.",
    "status": "ativa"
  }
  // ...
]
```

---

## Buscar oferta de ajuda por ID

**GET /ofertas-ajuda/{id}**

Retorna uma oferta de ajuda específica pelo ID.

**Path Param:**

- `id` (integer): ID da oferta de ajuda

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto oferta de ajuda (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Oferta de ajuda não encontrada."`

---

## Atualizar oferta de ajuda existente

**PUT /ofertas-ajuda/{id}**

Atualiza uma oferta de ajuda já existente.

**Path Param:**

- `id` (integer): ID da oferta de ajuda

**Body (JSON):**

```json
{
  "usuarioId": 1,
  "titulo": "Ajuda com alimentos",
  "descricao": "Tenho cestas básicas disponíveis.",
  "status": "inativa"
}
```

_Obs: O campo `id` pode ser omitido do corpo, pois o backend já define pelo parâmetro da URL._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar oferta de ajuda por ID

**DELETE /ofertas-ajuda/{id}**

Remove uma oferta de ajuda pelo ID.

**Path Param:**

- `id` (integer): ID da oferta de ajuda

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

# Endpoints de Pedidos de Ajuda

## Criar um novo pedido de ajuda

**POST /pedidos-ajuda**

Cria um novo pedido de ajuda.

**Body (JSON):**

```json
{
  "usuarioId": 1,
  "titulo": "Preciso de carona",
  "descricao": "Preciso de carona para o hospital amanhã.",
  "status": "aberto"
}
```

_Obs: Ajuste os campos conforme o seu model `PedidoAjuda`._

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todos os pedidos de ajuda

**GET /pedidos-ajuda**

Retorna uma lista de todos os pedidos de ajuda.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "id": 1,
    "usuarioId": 1,
    "titulo": "Preciso de carona",
    "descricao": "Preciso de carona para o hospital amanhã.",
    "status": "aberto"
  }
  // ...
]
```

---

## Buscar pedido de ajuda por ID

**GET /pedidos-ajuda/{id}**

Retorna um pedido de ajuda específico pelo ID.

**Path Param:**

- `id` (integer): ID do pedido de ajuda

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto pedido de ajuda (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Pedido de ajuda não encontrado."`

---

## Atualizar pedido de ajuda existente

**PUT /pedidos-ajuda/{id}**

Atualiza um pedido de ajuda já existente.

**Path Param:**

- `id` (integer): ID do pedido de ajuda

**Body (JSON):**

```json
{
  "usuarioId": 1,
  "titulo": "Atualização do pedido",
  "descricao": "Novo detalhe sobre o pedido.",
  "status": "em andamento"
}
```

_Obs: O campo `id` pode ser omitido do corpo, pois o backend já define pelo parâmetro da URL._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar pedido de ajuda por ID

**DELETE /pedidos-ajuda/{id}**

Remove um pedido de ajuda pelo ID.

**Path Param:**

- `id` (integer): ID do pedido de ajuda

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

  # Endpoints de Status de Acompanhamento

## Criar um novo status de acompanhamento

**POST /status-acompanhamento**

Cria um novo status de acompanhamento.

**Body (JSON):**

```json
{
  "nome": "Em andamento",
  "descricao": "Acompanhamento em processo"
}
```

_Obs: Ajuste os campos conforme o seu model `StatusAcompanhamento`._

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todos os status de acompanhamento

**GET /status-acompanhamento**

Retorna uma lista de todos os status de acompanhamento.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "idStatusAcompanhamento": 1,
    "nome": "Em andamento",
    "descricao": "Acompanhamento em processo"
  }
  // ...
]
```

---

## Buscar status de acompanhamento por ID

**GET /status-acompanhamento/{id}**

Retorna um status de acompanhamento específico pelo ID.

**Path Param:**

- `id` (integer): ID do status de acompanhamento

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto status de acompanhamento (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Status de acompanhamento não encontrado."`

---

## Atualizar status de acompanhamento existente

**PUT /status-acompanhamento/{id}**

Atualiza um status de acompanhamento já existente.

**Path Param:**

- `id` (integer): ID do status de acompanhamento

**Body (JSON):**

```json
{
  "nome": "Finalizado",
  "descricao": "Acompanhamento concluído"
}
```

_Obs: O campo `idStatusAcompanhamento` pode ser omitido do corpo, pois o backend já define pelo parâmetro da URL._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar status de acompanhamento por ID

**DELETE /status-acompanhamento/{id}**

Remove um status de acompanhamento pelo ID.

**Path Param:**

- `id` (integer): ID do status de acompanhamento

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

# Endpoints de Status do Match

## Criar um novo status do match

**POST /status-match**

Cria um novo status do match.

**Body (JSON):**

```json
{
  "nome": "Pendente",
  "descricao": "Aguardando confirmação do outro usuário."
}
```

_Obs: Ajuste os campos conforme o seu model `StatusMatch`._

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todos os status do match

**GET /status-match**

Retorna uma lista de todos os status do match.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "idStatusMatch": 1,
    "nome": "Pendente",
    "descricao": "Aguardando confirmação do outro usuário."
  }
  // ...
]
```

---

## Buscar status do match por ID

**GET /status-match/{id}**

Retorna um status do match específico pelo ID.

**Path Param:**

- `id` (integer): ID do status do match

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto status do match (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Status do match não encontrado."`

---

## Atualizar status do match existente

**PUT /status-match/{id}**

Atualiza um status do match já existente.

**Path Param:**

- `id` (integer): ID do status do match

**Body (JSON):**

```json
{
  "nome": "Confirmado",
  "descricao": "Match confirmado por ambos os usuários."
}
```

_Obs: O campo `idStatusMatch` pode ser omitido do corpo, pois o backend já define pelo parâmetro da URL._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar status do match por ID

**DELETE /status-match/{id}**

Remove um status do match pelo ID.

**Path Param:**

- `id` (integer): ID do status do match

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

  # Endpoints de Status do Pedido

## Criar um novo status do pedido

**POST /status-pedido**

Cria um novo status do pedido.

**Body (JSON):**

```json
{
  "nome": "Aguardando aprovação",
  "descricao": "Pedido está aguardando análise."
}
```

_Obs: Ajuste os campos conforme o seu model `StatusPedido`._

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todos os status do pedido

**GET /status-pedido**

Retorna uma lista de todos os status do pedido.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "idStatus": 1,
    "nome": "Aguardando aprovação",
    "descricao": "Pedido está aguardando análise."
  }
  // ...
]
```

---

## Buscar status do pedido por ID

**GET /status-pedido/{id}**

Retorna um status do pedido específico pelo ID.

**Path Param:**

- `id` (integer): ID do status do pedido

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto status do pedido (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Status do pedido não encontrado."`

---

## Atualizar status do pedido existente

**PUT /status-pedido/{id}**

Atualiza um status do pedido já existente.

**Path Param:**

- `id` (integer): ID do status do pedido

**Body (JSON):**

```json
{
  "nome": "Aprovado",
  "descricao": "Pedido aprovado para andamento."
}
```

_Obs: O campo `idStatus` pode ser omitido do corpo, pois o backend já define pelo parâmetro da URL._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar status do pedido por ID

**DELETE /status-pedido/{id}**

Remove um status do pedido pelo ID.

**Path Param:**

- `id` (integer): ID do status do pedido

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

  # Endpoints de Usuários

## Criar um novo usuário

**POST /usuarios**

Cria um novo usuário.

**Body (JSON):**

```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "senha": "123456",
  "telefone": "11999999999"
}
```

_Obs: Ajuste os campos conforme o seu model `Usuario`._

**Resposta de sucesso:**

- **HTTP 201 Created**

  - Body: mensagem de confirmação (string)

---

## Listar todos os usuários

**GET /usuarios**

Retorna uma lista de todos os usuários.

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body:

```json
[
  {
    "idUsuario": 1,
    "nome": "João Silva",
    "email": "joao@email.com",
    "telefone": "11999999999"
  }
  // ...
]
```

---

## Buscar usuário por ID

**GET /usuarios/{id}**

Retorna um usuário específico pelo ID.

**Path Param:**

- `id` (integer): ID do usuário

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto usuário (JSON)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: `"Usuário não encontrado."`

---

## Login de usuário

**POST /usuarios/login**

Autentica um usuário usando e-mail e senha.

**Body (JSON):**

```json
{
  "email": "joao@email.com",
  "senha": "123456"
}
```

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: objeto usuário (JSON)

**Resposta de erro:**

- **HTTP 401 Unauthorized**

  - Body: mensagem de erro (string)

---

## Atualizar usuário existente

**PUT /usuarios/{id}**

Atualiza um usuário já existente.

**Path Param:**

- `id` (integer): ID do usuário

**Body (JSON):**

```json
{
  "nome": "João Atualizado",
  "email": "joao@email.com",
  "senha": "novaSenha123",
  "telefone": "11988888888"
}
```

_Obs: O campo `idUsuario` pode ser omitido do corpo, pois o backend já define pelo parâmetro da URL._

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

---

## Deletar usuário por ID

**DELETE /usuarios/{id}**

Remove um usuário pelo ID.

**Path Param:**

- `id` (integer): ID do usuário

**Resposta de sucesso:**

- **HTTP 200 OK**

  - Body: mensagem de confirmação (string)

**Resposta de erro:**

- **HTTP 404 Not Found**

  - Body: mensagem de erro (string)

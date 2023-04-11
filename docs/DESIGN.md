# Design

## Minicenário

O projeto irá gerenciar uma rede fictícia de farmácias de uma determinada cidade, bem como criar suas filiais e manipular os produtos por elas ofertados. Além de permitir o cadastro de clientes, e o gerenciamento de suas contas e dos produtos e por eles comprados.

## Classes

- Store
- Branch (Store)
- Client
- Product

## Atributos

**Store:**

- Clients
- Branches

**Branch (Store):**

- Address
- Products

**Client:**

- Name
- Age
- Id
- Purchased Products
- Shopping Cart

**Product:**

- Name
- Price
- Type
- Validity

## Métodos

**Store:**

- Register/Delete Client
- Register/Delete Branch

**Branch (Store):**

- Constructor
- Add/Delete Product
- Show All Products
- Search for Product

**Client:**

- Constructor
- Add Product to Cart
- Buy Product from Cart
  - Update Purchased Products (Private)

**Product:**

- Constructor

<!--
CRUD de produto. CRUD de filiais. CRUD de cliente. Compra de produto. Busca
por um produto dado seu nome. Listagem de todas as filiais de uma determinada cidade.
Listagem de todos os produtos cadastrados. Listagem de todos os produtos comprados por
um determinado cliente.

Obs: Questionar sobre oq seria uma cidade. Rede de farmácias ou local único?
-->

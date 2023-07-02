# Design

## Minicenário

A rede de farmácia "Drogas Lícitas" precisa de um aplicativo para gerenciar suas filiais, produtos e clientes. Sendo assim, será necessário: Cadastrar filiais, neste processo deve informar sua região, cidade e senha, e será gerado pelo sistema uma UUID, a senha e o uuid serão necessárias para que a filial faça seu login no sistema, a região e a cidade da filial podem ser alterados. Cadastrada a filial, mais filiais podem ser cadrastadas, as filiais cadastradas podem ser apresentadas e filtradas por sua cidade, ou pode ser efetuado o login por uma filial cadastrada. Após o login de uma determinada filial esta terá as opções de cadastrar os produtos que por ela serão ofertados, criar clientes e fazer receber compras efetuadas por cliente, além de ter a possibilidade de atualizar, ler e deletar cada um de seus componentes. As opções de vizualização da quantidade de clientes (mostrará o nome de todos os clientes, suas idades e identidades), dos produtos ofertados por ordem alfabética (mostrará os nomes dos produtos, preços e tipos), e das compras efetuadas por cada cliente (entrando na conta de um cliente específico já cadastrado e daí mostrará os produtos por ele comprado, seu nome, idade, e id). Na criação dos clientes será necessário, pela filial, cadastrar o nome, idade e identidade dos sujeitos. Na criação dos produtos serão informados o nome do produto, quantidade, preço e tipos. Por fim na efetuação da compra, a filial deve informar o id do comprador, e portanto logar em sua conta, e o nome de cada produto, este nome que pode ser localizado por busca diretamente nos produtos ofertados, e após a compra todos serão deletados da filial e listados em produtos comprados.

## Project Structure

*(abstract)*
**Store:**

- static final String: name
- static final String: password
- static ArrayList&#60;Branch&#62;: branches
- static ArrayList&#60;Client&#62;: clients
- Methods:
  - static registerBranch(Branch branch): void
  - static removeBranch(Branch branch, String pass): void
  - static searchBranchesFromCity(String city): ArrayList&#60;Branch&#62;
  - static registerClient(Client client): void
  - static removeClient(Client client): void
  - static getters() and setters()

**Address:**

- String: city
- String: region
- Methods:
  - toString(): String
  - getters() and setters()

**Branch:**

- Integer: id
- String: password
- Address: address
- Boolean: isAuthenticated
- HashMap<Product, Integer>: products
- Methods:
  - searchProductsByWord(String word): ArrayList&#60;Product&#62;
  - searchProductByName(String name): Product
  - addProduct(Product product): void
  - removeProduct(Product product, Integer quantity): boolean
  - removeProduct(Product product): boolean
  - buyProduct(Client client, Product product): void
  - login(String password): Boolean
  - logout(): void
  - toString(): String
  - getters() and setters()

**Client:**

- String: name
- Integer: age
- String: cpf
- HashMap<Product, Integer>: purchasedProducts
- Methods:
  - addProductToPurchasedProducts(Product product): void
  - toString(): String
  - getters() and setters()

*(abstract)*
**Product:**

- String: name
- Integer: price
- Methods:
  - toString(): String
  - getters() and setters()

**Cosmetic(Product):**

- String: brand
- String: type
- Boolean: containsSunProtectionFactor
- Methods:
  - getters() and setters()

**Medicament(Product):**

- String: boxColor
- Integer: dosageMg
- Integer: minimumAge
- Methods:
  - getters() and setters()

# Design

## Minicenário

A rede de farmácia "Drogas Lícitas" precisa de um aplicativo para gerenciar suas filiais, produtos e clientes. Sendo assim, será necessário: Cadastrar filiais, neste processo deve informar seu nome, endereço e senha, e será gerado pelo sistema uma UUID, a senha e o uuid serão necessárias para que a filial faça seu login no sistema, o endereço e nome da filial podem ser alterados. Cadastrada a filial, mais filiais podem ser cadrastadas, as filiais cadastradas podem ser apresentadas, ou pode ser efetuado o login por uma filial cadastrada. Após o login de uma determinada filial esta terá as opções de cadastrar os produtos que por ela serão ofertados, criar clientes e fazer receber compras efetuadas por cliente, além de ter a possibilidade de atualizar, ler e deletar cada um de seus componentes. As opções de vizualização da quantidade de clientes por ordem alfabética (mostrará o nome de todos os clientes, suas idades e identidades), dos produtos ofertados por ordem alfabética (mostrará os nomes dos produtos, preços e tipos), e das compras efetuadas por cada cliente (pesquisará o id de um cliente em específico e daí mostrará os produtos por ele comprado, seu nome, idade, e id), também poderão ser apresentadas após o login da filial. Na criação dos clientes será necessário, pela filial, cadastrar o nome, idade e identidade dos sujeitos. Na criação dos produtos serão informados o nome do produto, preço e tipos. Por fim na efetuação da compra, a filial deve informar o id do comprador, e o nome de cada produto, este nome que pode ser localizado por busca diretamente nos produtos ofertados, e após a compra todos serão deletados da filial.

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
  - static listAllBranches(): void
  - static searchBranchesFromCity(String city): ArrayList&#60;Branch&#62;
  - static listBranchesFromCity(String city): void
  - static registerClient(Client client): void
  - static removeClient(Client client): void
  - static listAllClients(): void
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
  - listAllProducts(): void
  - searchProductsByWord(String word): ArrayList&#60;Product&#62;
  - searchProductByName(String name): Product
  - addProduct(Product product): void
  - removeProduct(Product product, Integer quantity): void
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
  - listPurchasedProducts(): void
  - addProductToPurchasedProducts(Product product): void
  - toString(): String
  - getters() and setters()

*(abstract)*
**Product:**

- String: name
- float: price
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
- Integer: dosageMl
- Integer: minimumAge
- Methods:
  - getters() and setters()

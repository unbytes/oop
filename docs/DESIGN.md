# Design

## Minicenário

A rede de farmácia "Drogas Lícitas" precisa de um aplicativo para gerenciar suas filiais, produtos e clientes. Sendo assim, será necessário: Cadastrar filiais, neste processo deve informar seu nome, endereço e senha, e será gerado pelo sistema uma UUID, a senha e o uuid serão necessárias para que a filial faça seu login no sistema, o endereço e nome da filial podem ser alterados. Cadastrada a filial, mais filiais podem ser cadrastadas, as filiais cadastradas podem ser apresentadas, ou pode ser efetuado o login por uma filial cadastrada. Após o login de uma determinada filial esta terá as opções de cadastrar os produtos que por ela serão ofertados, criar clientes e fazer receber compras efetuadas por cliente, além de ter a possibilidade de atualizar, ler e deletar cada um de seus componentes. As opções de vizualização da quantidade de clientes por ordem alfabética (mostrará o nome de todos os clientes, suas idades e identidades), dos produtos ofertados por ordem alfabética (mostrará os nomes dos produtos, preços e tipos), e das compras efetuadas por cada cliente (pesquisará o id de um cliente em específico e daí mostrará os produtos por ele comprado, seu nome, idade, e id), também poderão ser apresentadas após o login da filial. Na criação dos clientes será necessário, pela filial, cadastrar o nome, idade e identidade dos sujeitos. Na criação dos produtos serão informados o nome do produto, preço e tipos. Por fim na efetuação da compra, a filial deve informar o id do comprador, e o nome de cada produto, este nome que pode ser localizado por busca diretamente nos produtos ofertados, e após a compra todos serão deletados da filial.

## Project Structure

**Store:**

- String: name
- Client[]: clients
- Branch[]: branches
- Methods:
  - Store(String name)
  - showAllBranches()
  - showAllCityBranches(String city)
  - deleteBranch(String id, String password)
  - registerClient(Client client)
  - searchClientById(String id)
  - deleteClient(Client client)
  - showAllClientsSortedByName()

**Address:**

- String: city
- String: region
- Methods:
  - Address(String city, String region)
  - getters() and setters()

**Branch:**

- String name
- Integer: id
- String: password
- Address: address
- Product[]: products
- final String[]: PRODUCT_TYPES
- Methods:
  - Branch(String password, String city, String region)
  - registerProduct(Product product)
  - searchProductByName(String name)
  - listAllProductsSortedByName()
  - deleteProduct(Product product)
  - setName(String name)
  - setAddress(Address address)

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

*Product:*

- String: name
- float: price
- Methods:
  - Product(String name, float price)
  - getters() and setters()

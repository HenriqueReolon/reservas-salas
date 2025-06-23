# Guia de Execução da Aplicação

## Requisitos
1. **Docker e Docker Compose**: Certifique-se de ter o Docker e o Docker Compose instalados em seu sistema.
2. **Execução Local**: Se preferir rodar a aplicação localmente, é recomendável executar pelo menos o banco de dados em um container Docker.
3. **Fork do Repositório**: Realize um fork do repositório no Git.

---

## Instruções de Execução

### Construção e Execução com Docker
O Docker está configurado para realizar o build automaticamente. Siga os passos abaixo para executar a aplicação:

1. **Navegue até o diretório** onde o arquivo `docker-compose.yml` está localizado.
2. **Execute o seguinte comando** para iniciar os containers em modo detached e construir as imagens:
   ```bash
   docker-compose up -d --build
   ```

### Parar e Remover Containers

- **Para parar os containers e remover todas as imagens criadas**, execute:
  ```bash
  docker-compose down --rmi all
  ```

- **Para parar os containers e remover uma imagem específica**, execute:
  ```bash
  docker-compose down && docker rmi <ID da imagem>
  ```

### Acesso à Aplicação

Após iniciar os containers, a interface da aplicação pode ser acessada através dos seguintes caminhos:

- [http://localhost:8082/usuarios](http://localhost:8082/usuarios)
- [http://localhost:8082/salas](http://localhost:8082/salas)
- [http://localhost:8082/reservas](http://localhost:8082/reservas)

---

## Cobertura de Testes

O projeto possui uma cobertura de testes que abrange as principais funcionalidades da aplicação, incluindo:

- **Testes de unidade para os serviços**: `ReservaService`, `SalaService` e `UsuarioService`, garantindo que a lógica de negócio para criação, listagem e atualização de dados está funcionando corretamente.
- **Testes de interface de usuário (UI)**: Para as telas de `Salas` e `Usuários`, que simulam a interação do usuário com o sistema, como o preenchimento e envio de formulários.

### Como executar os testes

Para executar a suíte de testes completa, utilize o seguinte comando do Maven na raiz do projeto:

```bash
mvn test
```

Este comando irá compilar o código, baixar as dependências necessárias e executar todos os testes de unidade e de interface. Os testes de UI utilizam o Testcontainers para criar um ambiente de banco de dados isolado, garantindo que os testes não afetem o banco de dados de desenvolvimento.
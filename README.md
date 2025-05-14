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

### Passos Adicionais

1. **Acesse o diretório da aplicação** para poder executar o Docker.
2. **Se estiver usando Windows**, certifique-se de iniciar o Docker Desktop antes de executar os comandos.
3. **Execute o comando** para iniciar os containers:
   ```bash
   docker-compose up -d --build
   ```

### Acesso à Aplicação

Após iniciar os containers, a interface da aplicação pode ser acessada através dos seguintes caminhos:

- [http://localhost:8082/usuarios](http://localhost:8082/usuarios)
- [http://localhost:8082/salas](http://localhost:8082/salas)
- [http://localhost:8082/reservas](http://localhost:8082/reservas)
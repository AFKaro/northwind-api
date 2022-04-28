
# Northwind API
## _API baseada no banco Northwind_

## Tecnologias utilizadas

- Spring Boot
- SQL Server
- ReactJS
- Docker

## Como rodar

### Subindo o banco de dados
OBS: necessário ter o SQL Server e docker instalados.
OBS2: Recomenda-se a utilização do DBeaver para gerenciar o banco.

1. Efetuar pull da imagem do SQL Server contêiner 2019
```sh
sudo docker pull mcr.microsoft.com/mssql/server:2019-latest
```

2. Executar imagem
```sh
sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=NorthwindDB123#" \
   -p 1433:1433 --name northwind --hostname localhost \
   -d mcr.microsoft.com/mssql/server:2019-latest
```

3. Criar conexão com banco
4. Rodar script (/databaset/northwind_database.sql) para criar Nothwind e popular
OBS: Atalho para executar scripts sql no Dbeaver: Alt+X

## Subindo aplicação
1. Clonar repositório
```sh
https://github.com/AFKaro/northwind-api.git
```

2. Abrir em IDE de preferência
3. Instale as dependências do projeto com maven
4. Rode a aplicação

## Subindo front-end
1. Buildar front-end
```sh
kah@ubuntu:~/Documents/bd/northwind-api/view$ sudo docker build -t view_bd2 .
```
2. Docker run
```sh
kah@ubuntu:~/Documents/bd/northwind-api/view$ sudo docker run -t -p 3000:3000 view_bd2
```


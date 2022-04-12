
## Efetuar pull da imagem de contêiner 2019

sudo docker pull mcr.microsoft.com/mssql/server:2019-latest

## Executar imagem

sudo docker run -e "ACCEPT_EULA=Y" -e "SA_PASSWORD=NorthwindDB123#" \
   -p 1433:1433 --name northwind --hostname localhost \
   -d mcr.microsoft.com/mssql/server:2019-latest
   
## Rodar script para criar Nothwind e popular

Arquivo: /databaset/northwind_database.sql

Atalho para executar no Dbeaver: Alt+X


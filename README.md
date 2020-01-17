# Golden Raspberry Awards
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/3e7bc24d32d94097a20ae267c5b4310b)](https://app.codacy.com/manual/andreassisbaroni/raspberryawards?utm_source=github.com&utm_medium=referral&utm_content=andreassisbaroni/raspberryawards&utm_campaign=Badge_Grade_Dashboard)
[![BCH compliance](https://bettercodehub.com/edge/badge/andreassisbaroni/raspberryawards?branch=master)](https://bettercodehub.com/)

### Requisitos
 - Java 8
 - Docker (Opcional)

### Executar testes
 - Navegar até a pasta raiz do projeto e executar o comando `./mvnw test`.
 - Após a conclusão dos testes, será gerado um relatório de cobertura de testes na pasta target, para visualizá-lo é possível
 abrir o arquivo `/target/site/jacoco/index.html`.

### Passos para executar 
 O projeto permite a execução pelo computador local ou executar por um container docker.

#### Execuão local
 - Navegar até a pasta raiz do projeto pelo terminal, executar o comando `./mvnw clean install -DskipTests`.
 - Após a conclusão do build, será necessário executá-lo, isso pode ser feito com o comando `java -jar ./target/raspberryawards.jar`. 
 
#### Execução docker
 - Navegar até a pasta raiz do projeto pelo terminal, executar o comando `./mvnw clean install -DskipTests dockerfile:build`.
 - O comando executado irá além de gerar o arquivo `jar`, também irá gerar uma imagem docker, como está configurado no `pom.xml`,
 a imagem gerada será do repositório `andrebaroni/raspberryawards` e a tag gerada será a versão do projeto, no caso `1.0.0`.
 - Após gerar a imagem, é possível executá-la pelo com o comando `docker run -d -p 8080:8080 andrebaroni/raspberryawards:1.0.0`.
 
### Documentação da API
 O projeto conta com o `swagger` para documentação da API, o mesmo pode ser acessado pelo próprio sistema (que deve estar sendo executado).
 - Com o sistema em execução, é necessário acessar a url `http://{api-address}:8080/swagger-ui.html`, assim será carregado uma página que permite a visualização da documentação.
 
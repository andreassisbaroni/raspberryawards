# Golden Raspberry Awards
[![BCH compliance](https://bettercodehub.com/edge/badge/andreassisbaroni/raspberryawards?branch=master)](https://bettercodehub.com/)

### Requisitos
 - Java 8

### Passos para executar 
 - Inicialmente é necessário buildar o projeto, para isso é necessário abrir o terminal e navegar até a pasta raiz do projeto e executar o comando `./mvnw clean install`.
 - Após a conclusão do build, será necessário executá-lo, isso pode ser feito com o comando `java -jar ./target/raspberryawards.jar`.
 
### Documentação da API
 O projeto conta com o `swagger` para documentação da API, o mesmo pode ser acessado pelo próprio sistema (que deve estar sendo executado).
 - Com o sistema em execução, é necessário acessar a url `http://{api-address}:8080/swagger-ui.html`, assim será carregado uma página que permite a visualização da documentação.
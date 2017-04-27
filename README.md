# zup-developer-teste

O projeto foi desenvolvido usando Rest, com Spring-boot e maven, e uma base de dados mySQL.

Para executar o projeto crie um schema com o nome zuptest no mySQL, a tabela POI é criada quando executa o projeto.

Existe uma pasta teste com um arquivo que valida por meio do spring-boot-test os recursos:
  *Post (para cadastrar novos pontos);
  *Get (listar os pontos cadastrados);
  *Get (listar os pontos a partir de uma distancia maxima).
  
 A validação por meio de um client usa as urls:
  *localhost:8080/pois (listar todos os pois cadastrados);
  *localhost:8080/poi (enviando um json {
                                             "nome": "local_name",
                                             "x_coordanate": "ponto_x",
                                             "y_coordanate": "ponto_y"
                                         }
   para salvar);
  *localhost:8080/poi/x_coordanate/y_coordanate/max_distance (faz o calculo dos locais salvos utilizando 
   max_distance como raio).

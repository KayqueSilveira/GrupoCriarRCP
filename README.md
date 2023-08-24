# API de Gerenciamento de Pilotos
Esta API oferece endpoints para lidar com informações relacionadas a pilotos de corrida. Você pode usar esses endpoints para obter detalhes sobre os pilotos, suas posições e outras informações relevantes.

Endpoints Disponíveis: pilot
1. Com base em um arquivo txt, localizado na pasta Utils : race.txt. É convertido em objeto e persistido no banco de dados 
Endpoint: GET /load/archive/race

Este endpoint permite obter uma lista de pilotos que estão prontos para serem persistidos no banco. Assim que inserir corretamente todos os dados, retorna uma lista de objetos de piloto e suas devidas corridas.

2. Obter piloto por nome
Endpoint: GET /{name}

Este endpoint permite obter informações detalhadas sobre um piloto específico, com base no nome fornecido. Retorna um objeto de transferência de dados (DTO) contendo detalhes sobre o piloto.

3. Obter todos os pilotos
Endpoint: GET /all

Este endpoint recupera uma lista de todos os pilotos registrados. Retorna uma lista de objetos DTO que contêm informações sobre todos os pilotos.

4. Obter posições dos pilotos
Endpoint: GET /position

Este endpoint retorna as posições atuais de todos os pilotos e também a verificação de melhor volta de cada piloto, melhor volta de toda a corrida, media de velocidade e etc...

Uso dos Endpoints
Você pode usar as solicitações HTTP GET para acessar os diferentes endpoints e obter as informações desejadas. Certifique-se de fornecer os parâmetros necessários, como o nome do piloto, quando aplicável.

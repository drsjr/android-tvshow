# Android TvMaze Project

### Resumo

Este é um simples projeto Andrid utilizando a Arquitetura MVVM Clean Arch.
 - Koin para injeção de dependência.
 - Coroutines + Flow para controle assíncrono do aplicativo.
 - Multi-módulos para melhor separação da responsabilidades.
 - O arquivo TOML para gerenciar as bibliotecas.

### Estruturação do App

 <img src="gradle/dependency-graph/project-graph.png" width="500" height="300"> 

Eu dividi em três módulos sem contar o módulo **:app**, segundos os padrões do Clean Code com as camadas:
 - Presentation: **:feature**:catalog
 - Domain: **:domain**:tvmaze
 - Data: **:data**:tvmaze

Algumas importante em detrimento de outra. O fato de eu ter feito multi-módulos deixou bem mais visível a responsabilidade de cada módulo, assim melhorando a leitura e entendimento do código.

Outro ponto foi eu ter utilizado o Navigation e o [ArgSage]() para passar os dados eu um Fragment para outro deixando a navegação bem mais simple.

Criar um módulo features (Presentation Layer) vai nos permitir builds mais rápidas, poís caso isso seja implementado somente no módulo **:app**, com o tempo e o a adição de nova features acarretará na demora de compilação por estar tudo em um lugar só.

A imagem foi gerada pelo **task** `projectDependencyGraph`, use o site [Graphviz](https://renenyffenegger.ch/notes/tools/Graphviz/examples/index), para entender como criar o grafo.


### Funcionalidade

O Aplicativo é bem simple, ele basicamente lista as Séries que a api [TvMaze](https://www.tvmaze.com/api) fornece através da [API](https://github.com/drsjr/android-tvshow/blob/master/data/tvmave/src/main/java/tour/donnees/data/tvmaze/datasource/remote/api/TvMazeApi.kt).

O seu uso é bem simples:


 <img src="https://github.com/drsjr/android-tvshow/blob/master/source/tvmaze.gif" width="200" height="400"> 


### Bugs

Problemas existem e muitos deles pretendo resolver:
 - SearchView continua aberto após ir para a tela de detalhes do Show.
 - Manter o estado da tela após o retorno da tela de detalhe.
 - Remover o icone de busca na tela de detalhe.
  

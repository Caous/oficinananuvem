<!doctype html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

        <link rel="icon" href="../../../../favicon.ico">

        <title>Gerenciar Perfil</title>

        <!-- Principal CSS do Bootstrap -->
        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Estilos customizados para esse template -->
        <link href="pricing.css" rel="stylesheet">
    </head>

    <body>
        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm" style="background-color: #262626;">
            <h5 class="my-0 mr-md-auto font-weight-normal">Oficina na Nuvem</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="#"></a>
                <a class="p-2 text-dark" href="#"></a>
                <a class="p-2 text-dark" href="#"></a>
                <a class="p-2 text-dark" href="#"></a>
            </nav>
            <a class="btn btn-info btn-sm" href="ProdutosWebServlet" style="margin-right:5px;">Continuar comprando</a>
            <br>
            <form method="Post" action="LoginLogoutServlet">
                <button class="btn btn-outline-danger btn-sm" type="submit" name="tarefa" value="sair">Sair</button>
            </form>
        </div>


    <div class="container">

        <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
            <h1 class="display-4">Bem Vindo - ${NomeCliente}</h1>
            <p class="lead">Esta area é reservada para o cliente, onde pode fazer alterações dos dados, acompanhamento de pedidos e consulta de produtos salvos no carrinho</p>
        </div>

        <div class="container">
            <div class="card-deck mb-3 text-center">
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Meus endereços</h4>
                    </div>
                    <div class="card-body">
                        <h3>Visualize e adicione endereços</h1>
                            <ul class="list-unstyled mt-3 mb-4">
                                <img src="assets/images/gallery/map.png">
                            </ul>
                            <form method="Post" action="EnderecoClienteServlet">
                                <button type="submit" name="tarefa" class="btn btn-lg btn-block btn-info">Endereços</button>
                            </form>
                    </div>
                </div>
                <div class="card mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Meus Dados</h4>
                    </div>
                    <div class="card-body">
                        <h3></h>Visualize e altere seus dados</h3>
                        <ul class="list-unstyled mt-3 mb-4">
                            <img src="assets/images/gallery/client.png" >
                        </ul>
                        <form method="Post" action="ClienteServlet">
                        <button type="submit" name="tarefa" class="btn btn-lg btn-block btn-info">Dados</button>
                        </form>
                    </div>
                </div>
        <div class="card mb-4 shadow-sm">
          <div class="card-header">
            <h4 class="my-0 font-weight-normal"> Pedido</h4>
          </div>
          <div class="card-body">
               <h3>Visualize seus pedidos</h3>
            <h1 class="card-title pricing-card-title"></h1>
            <ul class="list-unstyled mt-3 mb-4">
             <img src="assets/images/gallery/produto_caminho.gif" style="width: 60%;" alt="" srcset="">
            </ul>
             <form method="Post" action="PedidoClienteServlet">
                        <button type="submit" name="tarefa" class="btn btn-lg btn-block btn-info">Pedidos</button>
             </form>
          </div>
        </div>
            </div>

            <footer class="pt-4 my-md-5 pt-md-5 border-top">
                <div class="row">
                    <div class="col-12 col-md">
                        <img class="mb-2" src="../../assets/brand/bootstrap-solid.svg" alt="" width="24" height="24">
                        <small class="d-block mb-3 text-muted">&copy; 2017-2018</small>
                    </div>
                    <div class="col-6 col-md">
                        <h5>Features</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Algo legal</a></li>
                            <li><a class="text-muted" href="#">Feature aleatória</a></li>
                            <li><a class="text-muted" href="#">Recursos para times</a></li>
                            <li><a class="text-muted" href="#">Coisas para desenvolvedores</a></li>
                            <li><a class="text-muted" href="#">Outra coisa legal</a></li>
                            <li><a class="text-muted" href="#">Último item</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5>Fontes</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Fonte</a></li>
                            <li><a class="text-muted" href="#">Nome da fonte</a></li>
                            <li><a class="text-muted" href="#">Outra fonte</a></li>
                            <li><a class="text-muted" href="#">Fonte final</a></li>
                        </ul>
                    </div>
                    <div class="col-6 col-md">
                        <h5>Sobre</h5>
                        <ul class="list-unstyled text-small">
                            <li><a class="text-muted" href="#">Equipe</a></li>
                            <li><a class="text-muted" href="#">Locais</a></li>
                            <li><a class="text-muted" href="#">Privacidade</a></li>
                            <li><a class="text-muted" href="#">Termos</a></li>
                        </ul>
                    </div>
                </div>
            </footer>
        </div>


        <!-- Principal JavaScript do Bootstrap
        ================================================== -->
        <!-- Foi colocado no final para a página carregar mais rápido -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
        <script src="../../assets/js/vendor/popper.min.js"></script>
        <script src="../../dist/js/bootstrap.min.js"></script>
        <script src="../../assets/js/vendor/holder.min.js"></script>
        <script>
            Holder.addTheme('thumb', {
                bg: '#55595c',
                fg: '#eceeef',
                text: 'Thumbnail'
            });
        </script>
    </body>
</html>

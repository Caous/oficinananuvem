<!doctype html>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="pt-br">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
                integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
                integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>

        <link rel="icon" href="../../../../favicon.ico">

        <title>Meus Dados</title>

        <!-- Principal CSS do Bootstrap -->
        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Estilos customizados para esse template -->
        <link href="form-validation.css" rel="stylesheet">
    </head>

    <body>

        <div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm"
             style="background-color: #262626;">
            <h5 class="my-0 mr-md-auto font-weight-normal">Oficina na Nuvem</h5>
            <nav class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="#"></a>
                <a class="p-2 text-dark" href="#"></a>
                <a class="p-2 text-dark" href="#"></a>
                <a class="p-2 text-dark" href="#"></a>
            </nav>
            <a class="btn btn-info btn-sm" href="homeCliente.jsp" style="margin-right:5px;">Gerenciar Perfil</a>
            <a class="btn btn-info btn-sm" href="ProdutosWebServlet" style="margin-right:5px;">Continuar comprando</a> 
            <br>
            <a class="btn btn-outline-danger btn-sm" href="">Sair</a>
        </div>

        <div class="container" style="text-align: center;">
            <div class="py-5 text-center">
                <img class="d-block mx-auto mb-4" src="assets/images/gallery/client.png" alt="" width="72" height="72">
            </div>

            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8 order-md-1">
                    <h4 class="mb-3">Dados de Usuário</h4>
                    <form class="needs-validation" novalidate>
                        <div class="row">
                            <div class="col-md-6">
                                <label for="email">Email</span></label>
                                <h6>${cliente.getEmail()}</h6>
                            </div>
                            <div class="col-md-6">
                                <label for="sobrenome">CPF</label>
                                <h6>${cliente.getCpf_cnpj()}</h6>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="primeiroNome">Nome</label>
                                <input type="text" class="form-control" id="nomeCliente" name="nomeCliente" placeholder="" value="${cliente.getNome()}" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="primeiroNome">Rg</label>
                                <input type="text" class="form-control" id="rgCliente" name="rgCliente" placeholder="" value="${cliente.getRg()}" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="email">Senha</span></label>
                                <input type="password" class="form-control" readonly id="senhaCliente" name="senhaCliente" value="${cliente.getSenha()}" placeholder="">
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="mr-sm-2 sr-only" for="inlineFormCustomSelect">Sexo</label>
                                <select class="custom-select mr-sm-2" id="sexoCliente" name="sexoCliente" style="margin-top: 8%;">
                                    <option selected value="${cliente.getSexo()}">${cliente.getSexo()}</option>
                                    <option value="Masculino">Masculino</option>
                                    <option value="Feminino">Feminino</option>
                                </select>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="endereco">Contato</label>
                                <input type="text" class="form-control" value="${cliente.getTelefone()}" id="telCliente" name="telCliente" placeholder="(xx) xxxx-xxxx" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label for="endereco">Data Nascimento</label>
                                <input type="date" class="form-control" value="${cliente.getData_nascimento()}" id="dataCliente" name="dataCliente" placeholder="(xx) xxxx-xxxx" required>
                            </div>
                        </div>

                        <hr class="mb-4">
                        <hr class="mb-4">
                        <button class="btn btn-success" id="btnSalvar" name="tarefa" value="Editando" btn-lg btn-block type="submit">Salvar</button>
                    </form>
                </div>
            </div>
        </div>

        <!-- Principal JavaScript do Bootstrap
          ================================================== -->
        <!-- Foi colocado no final para a página carregar mais rápido -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
        <script src="../../assets/js/vendor/popper.min.js"></script>
        <script src="../../dist/js/bootstrap.min.js"></script>
        <script src="../../assets/js/vendor/holder.min.js"></script>
        <script>
            // Exemplo de JavaScript para desativar o envio do formulário, se tiver algum campo inválido.
            (function () {
                'use strict';

                window.addEventListener('load', function () {
                    // Selecione todos os campos que nós queremos aplicar estilos Bootstrap de validação customizados.
                    var forms = document.getElementsByClassName('needs-validation');

                    // Faz um loop neles e previne envio
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>
        <script type="text/javascript">
            jQuery(function ($) {

                $('#btnSalvar').click(function () {

                    alert('Alterado com sucesso!')
                })
            })
        </script>
    </body>

</html>
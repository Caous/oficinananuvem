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

        <div class="container">
            <div class="py-5 text-center">
                <img class="d-block mx-auto mb-4" src="assets/images/gallery/map.png" alt="" width="72" height="72">
                <h2>Endereços Cadastrados</h2>
                <p class="lead">o primeiro endereço cadastrado será o de fatura ou se ainda não tiver um.</p>
            </div>

            <div class="row">
                <c:forEach var="enderecos" items="${enderecos}" > 
                    <div class="col-md-4 order-md-2 mb-4">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            <span class="text-muted">Endereço Salvo</span>
                        </h4>
                        <ul class="list-group mb-3">
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Nome do endereço</h6>
                                    <small class="text-muted">${enderecos.getNome_endereco()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Estado</h6>
                                    <small class="text-muted">${enderecos.getEstado()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Cidade</h6>
                                    <small class="text-muted">${enderecos.getCidade()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">CEP</h6>
                                    <small class="text-muted">${enderecos.getCep()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Endereço de fatura</h6>
                                    <small class="text-muted">
                                        <c:if test="${enderecos.getFatura() == true}"> SIM </c:if>
                                        <c:if test="${enderecos.getFatura() == false}" >NÃO</c:if>
                                        </small>
                                    </div>
                                </li>
                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 class="my-0">Bairro</h6>
                                        <small class="text-muted">${enderecos.getBairro()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Rua</h6>
                                    <small class="text-muted"> ${enderecos.getRua()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Numero</h6>
                                    <small class="text-muted"> ${enderecos.getNumero()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Complemento</h6>
                                    <small class="text-muted"> ${enderecos.getComplemento()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Telefone</h6>
                                    <small class="text-muted"> ${enderecos.getTelefone()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Nome Receptor</h6>
                                    <small class="text-muted">${enderecos.getNome_receptor()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>Status</span>
                                <strong> Salvo</strong>
                                <form action="EnderecoClienteServlet" method="POST" class="needs-validation">
                                    <button type="submit" class="btn btn-danger" id="tarefa" name="tarefa" value="Excluindo" >Excluir</button>
                                    <input type="hidden" id="id" name="id" value="${enderecos.getId_endereco()}">
                                </form>
                            </li>
                        </ul>
                    </div>
                </c:forEach>
                <div class="col-md-8 order-md-1">
                    <h4 class="mb-3">Adicione um Endereço</h4>
                    <form action="EnderecoClienteServlet" method="POST" class="needs-validation">
                        <div class="mb-3">
                            <div class="row" >
                                <div class="col-md-6">
                                    <label for="text">CEP</span></label>
                                    <input type="text" class="form-control input-mask-cep"  name="cep" id="cep" value="${cadastrando.getCep()}" placeholder="">
                                </div>
                                <div class="col-md-3" style="margin-top: 4%;">
                                    <button type="submit" name="tarefa" value="BuscaCep" class="btn btn-lg btn-block btn-info">Pesquisar</button>
                                </div>
                            </div>       
                            <div class="invalid-feedback">
                                Insira uma CEP valido.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="endereco2">Nome Endereço </label>
                            <input type="text" class="form-control" id="nomeendereco" name="nomeendereco"  placeholder="">
                        </div>
                        <div class="mb-3">
                            <label for="endereco2">Nome Receptor </label>
                            <input type="text" class="form-control" id="nomereceptor" name="nomereceptor" placeholder="">
                        </div>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label for="endereco2">Numero </label>
                                <input type="number" class="form-control" id="numero" name="numero" placeholder="">
                            </div>
                            <div class="col-md-4 mb-3">
                                <label for="estado">Estado</label>
                                <input type="text" class="form-control" value="${cadastrando.getEstado()}" id="estado" name="estado" placeholder="">
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="email">Cidade</span></label>
                            <input type="text" class="form-control" value="${cadastrando.getCidade()}" id="cidade" name="cidade" placeholder="">
                            <div class="invalid-feedback">
                                Por favor, insira um endereço válido.
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="email">Rua</span></label>
                            <input type="text" class="form-control" value="${cadastrando.getRua()}" id="rua" name="rua" placeholder="">
                            <div class="invalid-feedback">
                                Insira uma Rua valida.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="endereco">Bairro<span class="text-muted"></span></label>
                            <input type="text" class="form-control" value="${cadastrando.getBairro()}" id="bairro" name="bairro"  placeholder="">
                            <div class="invalid-feedback">
                                Por favor, insira seu endereço de entrega.
                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="endereco2">Complemento </label>
                            <input type="text" class="form-control" value="${cadastrando.getComplemento()}" name="complemento" id="complemento" placeholder="">
                        </div>

                        <div class="row">
                            <div class="col-md-5 mb-3">
                                <label for="pais">Referencia de entrega</label>
                                <input type="text" class="form-control" id="refentrega" name="refentrega" placeholder="">
                                <div class="invalid-feedback">
                                    Por favor, escolha um complemento válido.
                                </div>
                            </div>
                            <div class="col-md-5 mb-3">
                                <label for="pais">Telefone Contato</label>
                                <input type="text" class="form-control phone-mask" id="telcontato" name="telcontato" placeholder="">
                                <div class="invalid-feedback">
                                    Por favor, escolha um telefone válido.
                                </div>
                            </div>
                        </div>
                        <hr class="mb-4">
                        <hr class="mb-4">
                        <div style="text-align: center;">
                            <button class="btn btn-success"  name="tarefa" value="Cadastrando" btn-lg btn-block type="submit">Salvar</button>
                        </div>
                </div>

                <!-- Principal JavaScript do Bootstrap
              ================================================== -->
                <!-- Foi colocado no final para a página carregar mais rápido -->
            <script src="themes/js/jquery.js" type="text/javascript"></script>
                      
                <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
                <script src="../../assets/js/vendor/popper.min.js"></script>
                <script src="../../dist/js/bootstrap.min.js"></script>
                <script src="../../assets/js/vendor/holder.min.js"></script>
                
            <script src="assets/js/jquery.maskedinput.min.js"></script>
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
                        $.mask.definitions['~'] = '[+-]';
                        $('.input-mask-cep').mask('99999-999')
                        $('.phone-mask').mask('(99)9999-9999')
                    })

                </script>
                </body>

                </html>
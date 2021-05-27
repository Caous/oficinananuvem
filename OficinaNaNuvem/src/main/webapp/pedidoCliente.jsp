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
        <style>
            .accordion {
                background-color: #eee;
                color: #444;
                cursor: pointer;
                padding: 18px;
                width: 100%;
                border: none;
                text-align: left;
                outline: none;
                font-size: 15px;
                transition: 0.4s;
            }

            .active, .accordion:hover {
                background-color: #ccc;
            }

            .panel {
                padding: 0 18px;
                background-color: white;
                max-height: 0;
                overflow: hidden;
                transition: max-height 0.2s ease-out;
            }
        </style>
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
            <div class="row">
                <c:forEach var="pedidos" items="${pedidos}" >
                    <div class="col-md-4 order-md-2 mb-4">
                        <h4 class="d-flex justify-content-between align-items-center mb-3">
                            <span class="text-muted">Seu pedido</span>
                        </h4>

                        <ul class="list-group mb-3">
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Venda</h6>
                                    <small class="text-muted">${pedidos.getIdVenda()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Status da venda</h6>
                                    <small class="text-muted">${pedidos.getStatus()}</small>
                                </div>
                            </li>
                            <li class="list-group-item d-flex justify-content-between lh-condensed">
                                <div>
                                    <h6 class="my-0">Data</h6>
                                    <small class="text-muted">${pedidos.getData()}</small>
                                </div>
                            </li>
                            <button class="accordion">Detalhes</button>
                            <div class="panel">
                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 class="my-0">Metodo de pagamento</h6>
                                        <c:if test="${pedidos.getIdMetodo() == 1}"> 
                                            <form action="PedidoClienteServlet" method="POST" class="needs-validation">
                                                
                                                <button type="submit" class="btn btn-link" name="tarefa" id="tarefa" value="boleto">  <small class="text-muted">${pedidos.getMetodo()}</small> </button>
                                                <input type="hidden" name="id" id="id" value="${pedidos.getIdVenda()}">
                                            </form>
                                        </c:if>
                                        <c:if test="${pedidos.getIdMetodo() == 2}"> 
                                            <small class="text-muted">${pedidos.getMetodo()}</small>
                                        </c:if>
                                    </div>
                                </li>
                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 class="my-0">Endereço entrega</h6>
                                        <small class="text-muted">${pedidos.getEndentrega()}</small>
                                    </div>
                                </li>
                                <li class="list-group-item d-flex justify-content-between lh-condensed">
                                    <div>
                                        <h6 class="my-0">Frete</h6>
                                    </div>
                                    <span class="text-muted row_currency"> ${pedidos.getFrete()}</span>
                                </li>
                                <c:forEach var="itens" items="${pedidos.getListaItens()}" >

                                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                                        <div>
                                            <h6 class="my-0">${itens.getQntd()} x ${itens.getNomeProduto()} </h6>
                                        </div>
                                        <span class="text-muted row_currency">  ${itens.getValor()}</span>
                                    </li>

                                </c:forEach>
                            </div>
                            <li class="list-group-item d-flex justify-content-between">
                                <span>Total</span>
                                <strong class="row_currency">  ${pedidos.getTotal()}</strong>
                            </li>
                        </ul>
                    </div>
                </c:forEach>

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
            var acc = document.getElementsByClassName("accordion");
            var i;

            for (i = 0; i < acc.length; i++) {
                acc[i].addEventListener("click", function () {
                    this.classList.toggle("active");
                    var panel = this.nextElementSibling;
                    if (panel.style.maxHeight) {
                        panel.style.maxHeight = null;
                    } else {
                        panel.style.maxHeight = panel.scrollHeight + "px";
                    }
                });
            }
        </script>
        <script type="text/javascript">
            let
            cells = Array.prototype.slice.call(document.querySelectorAll(".row_currency"));
// Loop over the array
            cells.forEach(function (cell) {
                // Convert cell data to a number, call .toLocaleString()
                // on that number and put result back into the cell
                cell.textContent = (+cell.textContent).toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
            });
            if ('ontouchstart' in document.documentElement)
                document.write(
                        "<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");</script>
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
    </body>

</html>
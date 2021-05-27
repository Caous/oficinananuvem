<!doctype html>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Oficina na Nuvem</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!--Less styles -->
        <!-- Other Less css file //different less files has different color scheam
             <link rel="stylesheet/less" type="text/css" href="themes/less/simplex.less">
             <link rel="stylesheet/less" type="text/css" href="themes/less/classified.less">
             <link rel="stylesheet/less" type="text/css" href="themes/less/amelia.less">  MOVE DOWN TO activate
        -->
        <!--<link rel="stylesheet/less" type="text/css" href="themes/less/bootshop.less">
        <script src="themes/js/less.js" type="text/javascript"></script> -->

        <!-- Bootstrap style --> 
        <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen"/>
        <link href="themes/css/base.css" rel="stylesheet" media="screen"/>
        <!-- Bootstrap style responsive -->	
        <link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet"/>
        <link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
        <!-- Google-code-prettify -->	
        <link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet"/>
        <!-- fav and touch icons -->
        <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144" href="themes/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114" href="themes/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
        <style type="text/css" id="enject"></style>
    </head>
    <body>
        <div id="header">
            <div>

                <!-- Navbar ================================================== -->
                <div id="logoArea" class="navbar">
                    <a id="smallScreen" data-target="#topMenu" data-toggle="collapse" class="btn btn-navbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </a>
                    <div class="navbar-inner">
                        <a class="brand" href="#" style="color: white;">Oficina na Nuvem</a>
                        <form class="form-inline navbar-search" method="post" action="products.html" >
                            <input class="srchTxt" type="text" placeholder="Digite um produto..."/>
                            <button type="submit" id="submitButton" class="btn btn-primary">Buscar</button>
                        </form>
                        <ul id="topMenu" class="nav pull-right">
                            <li class="" id="btnLogar">
                                <a href="#login" role="button" data-toggle="modal" style="padding-right:0"><span class="btn btn-sm btn-success">Login/Cadastro</span></a>
                                <div id="login" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false" >
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">X</button>
                                        <h3>Login</h3>
                                    </div>
                                    <form method="POST" action="LoginLogoutServlet">
                                        <div class="modal-body">

                                            <div class="control-group">								
                                                <input type="text" id="inputEmail" name="email" placeholder="Email">
                                            </div>
                                            <div class="control-group">
                                                <input type="password" id="inputPassword" name="senha" placeholder="Senha">
                                            </div>
                                            <div class="control-group">
                                                <label class="checkbox">
                                                    <input type="checkbox"> Lembrar
                                                </label>
                                            </div>

                                            <button type="submit" class="btn btn-success">Entrar</button>
                                            <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
                                            <div>
                                                <p>Não tenho cadastro clique <a href="cadastro.jsp">aqui</a></p>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <li class="" id="btnGerenciaPerfil" style="display: none">
                                <a href="homeCliente.jsp" style="padding-right:0"><span class="btn btn-sm btn-info">Gerenciar Perfil</span></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- Header End====================================================================== -->
        <div id="mainBody">
            <div class="container">
                <div class="row">
                    <!-- Sidebar ================================================== -->
                    <div id="sidebar" class="span3">
                        <div class="well well-small"><a id="myCart" href="CarrinhoServlet"><img src="themes/images/ico-cart.png" alt="cart">${countCarrinho} Itens no carrinho</a></div>
                        <ul id="sideManu" class="nav nav-tabs nav-stacked">
                            <li class="subMenu open"><a> CATEGORIAS</a>
                                <ul>
                                    <c:forEach var="categoria" items="${categorias}" >                                      
                                        <li><a class="active" href="#"><i class="icon-chevron-right"></i>${categoria.getNome()}</a></li>
                                            </c:forEach>
                                </ul>
                            </li>

                        </ul>
                        <br/>
                        <div class="thumbnail">
                            <img src="themes/images/payment_methods.png" title="Bootshop Payment Methods" alt="Payments Methods">
                            <div class="caption">
                                <h5>Métodos de Pagamento</h5>
                            </div>
                        </div>
                    </div>
                    <div class="span9">
                        <form action="PagamentoServlet" method="POST">
                            <div class="" style="background-color:#f5f5f5;">
                                <h4 style="margin-left:3%;">Revise o endeço</h4>
                                <div class="container">
                                    <input type="text" style="display: none;" name="idEndereco" value="${enderco.getId_endereco()}"/>
                                    <div class="col-lg-3">
                                        <label for="number-button" class="block">Receptor</label>
                                        <input type="text" id="form-field-1-1" placeholder="Nome" class="form-control" name="nome" required value="${enderco.getNome_endereco()}"/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label for="number-button" class="block">Endereço</label>
                                        <input type="text" id="form-field-1-1" placeholder="Nome" class="form-control" name="nome" required value="${enderco.getRua()}"/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label for="number-button" class="block">Número</label>
                                        <input type="text" id="form-field-1-1" placeholder="Nome" class="form-control" name="nome" required value="${enderco.getNumero()}"/>
                                    </div>
                                    <div class="col-lg-3">
                                        <label for="number-button" class="block">Bairro</label>
                                        <input type="text" id="form-field-1-1" placeholder="Nome" class="form-control" name="nome" required value="${enderco.getBairro()}"/>
                                    </div>
                                </div>

                                <hr class="soft"/>
                                <c:choose>
                                    <c:when test= "${empty produtos}">
                                        <h4>Carrinho vazio no momento por favor clique <a href="ProdutosWebServlet" style="color: #002A80;"> aqui </a> para iniciar uma compra!</h4>
                                    </c:when>
                                    <c:otherwise>
                                        <form method="POST" action="CarrinhoServlet">
                                            <table class="table table-bordered">
                                                <thead>
                                                    <tr>
                                                        <th>Produto</th>
                                                        <th>Descrição</th>
                                                        <th>Quantidade</th>
                                                        <th>Preço(Un.)</th>
                                                        <th>Desconto</th>
                                                        <th>Frete</th>
                                                        <th>Total</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="produto" items="${produtos}" >
                                                        <tr>
                                                            <td> <img width="60" src="data:image/png;base64, ${produto.getImgCapa()}" alt=""/></td>
                                                            <td>${produto.getNome()}</td>
                                                            <td>
                                                                <div class="input-append">
                                                                    <input class="span1" style="max-width:34px"  value="${produto.getQuantidadeCarrinho()}" max="${produto.getQuantidade()}" id="qtdProduto${produto.getIdProduto()}"  type="number" name="qtdProduto" disabled>

                                                                    <!--<input type="text" style="display:none" name="idProduto" value="{produto.getIdProduto()}"/>-->
                                                                    <input type="text" style="display:none" name="idProduto" id="idProduto"/>
                                                                </div>
                                                            </td>
                                                            <td class="row_currency">${produto.getValor()}</td>
                                                            <td>R$00,00</td>
                                                            <td>R$00,00</td>
                                                            <td class="row_currency" id="totalLinha${produto.getIdProduto()}">${produto.getTotal()}</td>
                                                        </tr>
                                                    </c:forEach>                                   
                                                    <tr>
                                                        <td colspan="6" style="text-align:right">Desconto:	</td>
                                                        <td> R$00,00</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" style="text-align:right">Total de Frete:	</td>
                                                        <td> R$00,00</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="6" style="text-align:right"><!--<strong>TOTAL (R$228 - R$50 + R$31) =</strong>--></td>
                                                        <td class="label label-important row_currency  compraFinal" style="display:block" id="row_currency2"> <strong class=""> ${compraFinal} </strong></td>
                                                    </tr>
                                                </tbody>
                                            </table>

                                        </form> 
                                    </c:otherwise>
                                </c:choose>
                                <hr style="height:1px;border-width:0;color:gray;background-color:gray">



                                <br>
                                <br>
                                <div style="margin-left:5%; ">
                                    <div style="text-align:left;">
                                        <h4>Forma de Pagamento</h4>
                                    </div>
                                    <div class="d-block my-3">
                                        <div class="custom-control custom-radio">
                                            <label class="custom-control-label" for="debito" onclick="boleto()"><input id="debito" name="metodoPagamento" type="radio" class="custom-control-input" checked required value="1"> Boleto</label>
                                        </div>
                                        <div class="custom-control custom-radio">
                                            <label class="custom-control-label" for="credito" onclick="cartao()"><input id="credito" name="metodoPagamento" type="radio" class="custom-control-input" required value="2">  Cartão de crédito</label>
                                        </div>
                                    </div>
                                    <div class="row" id="divCartao"  style="margin-left:5%;">

                                    </div>
                                </div>
                                <hr class="mb-4">
                                <div class="col-md-4" style="max-width: 70px;margin-left:80%;">
                                    <button class="btn btn-success btn-lg btn-block" type="submit" name="tarefa" value="FinalizarCompra">Salvar</button>
                                </div>
                        </form>
                    </div>


                </div>
            </div>
        </div>
        <!-- MainBody End ============================= -->
        <!-- Footer ================================================================== -->
        <div  id="footerSection">
            <div class="container">
                <div class="row">
                    <div class="span3">
                        <h5>CONTA</h5>
                        <a href="#">MINHA CONTA</a>
                        <a href="#">INFORMAÇÕES PESSOAIS</a> 
                        <a href="#">ENDEREÇOS</a>  
                        <a href="#">HISTÓRICO DE PEDIDOS</a>
                    </div>
                    <div class="span3">
                        <h5>INFORMAÇÕES</h5>
                        <a href="#">CONTATO</a>  
                        <a href="#">CADASTRE-SE</a>   
                        <a href="#
                           ">TERMOS E CONDIÇÕES</a> 
                        <a href="#">FAQ</a>
                    </div>
                    <div class="span3">
                        <h5>NOSSAS OFERTAS</h5>
                        <a href="#">NOVOS PRODUTOS</a> 
                        <a href="#">MAIS VENDIDOS</a>  
                        <a href="#">OFERTAS ESPECIAIS</a>  
                        <a href="#">FORNECEDORES</a> 
                    </div>
                    <div id="socialMedia" class="span3 pull-right">
                        <h5>MIDIAS SOCIAIS</h5>
                        <a href="#"><img width="60" height="60" src="themes/images/facebook.png" title="facebook" alt="facebook"/></a>
                        <a href="#"><img width="60" height="60" src="themes/images/twitter.png" title="twitter" alt="twitter"/></a>
                        <a href="#"><img width="60" height="60" src="themes/images/youtube.png" title="youtube" alt="youtube"/></a>
                    </div> 
                </div>
            </div><!-- Container End -->
        </div>
        <!-- Placed at the end of the document so the pages load faster ============================================= -->
        <script src="themes/js/jquery.js" type="text/javascript"></script>
        <script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="themes/js/google-code-prettify/prettify.js"></script>

        <script src="themes/js/bootshop.js"></script>
        <script src="themes/js/jquery.lightbox-0.5.js"></script>

        <script src="assets/js/jquery-2.1.4.min.js"></script>

        <script src="assets/js/jquery.maskedinput.min.js"></script>

        <!-- <![endif]-->

        <!--[if IE]>
    <script src="assets/js/jquery-1.11.3.min.js"></script>
    <![endif]-->
        <script type="text/javascript">
                                                let cells = Array.prototype.slice.call(document.querySelectorAll(".row_currency"));
                                                // Loop over the array
                                                cells.forEach(function (cell) {
                                                    // Convert cell data to a number, call .toLocaleString()
                                                    // on that number and put result back into the cell
                                                    cell.textContent = (+cell.textContent).toLocaleString("pt-BR", {style: "currency", currency: "BRL"});

                                                });
                                                if ('ontouchstart' in document.documentElement)
                                                    document.write(
                                                            "<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");</script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script>
                                                function boleto() {
                                                    document.getElementById("divCartao").innerHTML = "";
                                                }
                                                function cartao() {
                                                    var element = document.getElementById('divCartao');
                                                    element.innerHTML = '<div class="row"><label for="inputEmail4">Nome : <input type="text" class="form-control" id="" name="nomeCartaoImpresso">  Data Expiração : <input type="text" style="width:10%" class="form-control" id="" name="dtValidade"></label></div><div class="row"><label for="inputEmail4">Numero : <input type="text" class="form-control" id="" name="nrCartao">  cvv : <input type="text" style="width:7%" class="form-control" id="" name="cvv"></label></div><div class="row"><label for="inputEmail4">Parcelas : <input type="number" class="form-control" min="1" max="12" name="parcelas" id=""> </div>';
                                                }

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

                $('.input-mask-phone').mask('(99)9999-9999')

            <%
                Cookie[] cookies = request.getCookies();
                for (Cookie atual : cookies) {
                    if (atual.getName().equals("ID_CLIENTE")) {
                        if (!atual.equals("") && atual != null) {
            %>
                $('#btnGerenciaPerfil').show()
                $('#btnLogar').hide()
            <%
            } else {
            %>
                $('#btnGerenciaPerfil').hide()
                $('#btnLogar').show()
            <%
                        }
                    }

                }
            %>
            })
        </script>
    </body>
</html>
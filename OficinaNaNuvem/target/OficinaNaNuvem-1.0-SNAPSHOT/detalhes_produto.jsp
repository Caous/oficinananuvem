<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">
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
                    <!-- Sidebar end=============================================== -->
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="inicial.html">Home</a> <span class="divider">/</span></li>
                            <li><a href="products.html">Produtos</a> <span class="divider">/</span></li>
                            <li class="active">Detalhes do Produto</li>
                        </ul>	
                        <div class="row">	  
                            <div id="gallery" class="span3">

                                <img src="data:image/png;base64, ${imgPrincipal.getNome()}" alt="Red dot" />



                                <div id="differentview" class="moreOptopm carousel slide">
                                    <div class="carousel-inner">
                                        <div class="item active">
                                            <c:forEach var="img" items="${imgProduto}" >
                                                <a href="#"> <img style="width:29%" src="data:image/png;base64, ${img.getNome()}" alt=""/></a>
                                                </c:forEach>       
                                        </div>
                                        <div class="item">
                                            <c:forEach var="img" items="${imgProduto}" >
                                                <a href="#"> <img style="width:29%" src="data:image/png;base64, ${img.getNome()}" alt=""/></a>
                                                </c:forEach> 
                                        </div>
                                    </div>
                                </div>


                            </div>
                            <div class="span6">
                                <h3>${produto.getNome()}</h3>

                                <hr class="soft"/>
                                <form method="POST" action="CarrinhoServlet">
                                    <div class="control-group">
                                        <label class="control-label row_currency"> <b>${produto.getValor()}</b></label>
                                        <input type="text" style="display:none" name="idProduto" value="${produto.getIdProduto()}" required/>
                                        <div class="controls">
                                            <input type="number" class="span1" min="1" max="${produto.getQuantidade()}" placeholder="Qntd." name="qtdProduto"/>
                                            <button type="submit" class="btn btn-large btn-primary pull-right" name="tarefa" value="adicionarCarrinho" > Adicionar ao Carrinho <i class=" icon-shopping-cart"></i></button>
                                        </div>
                                    </div>
                                </form>
                                <hr class="soft"/>
                                <h4>${produto.getQuantidade()} unidade(s) no estoque</h4>

                                <hr class="soft clr"/>
                                <p>
                                    ${produto.getDescricao()}
                                </p>
                                <a class="btn btn-small pull-right" href="#detail">Mais Detalhes</a>
                                <br class="clr"/>
                                <a href="#" name="detail"></a>
                                <hr class="soft"/>
                            </div>

                            <div class="span9">
                                <ul id="productDetail" class="nav nav-tabs">
                                    <li class="active"><a href="#home" data-toggle="tab">Detahes do Produto</a></li>
                                </ul>
                                <div id="myTabContent" class="tab-content">
                                    <div class="tab-pane fade active in" id="home">
                                        <h4>Informações do Produto</h4>
                                        <table class="table table-bordered">
                                            <tbody>
                                                <tr class="techSpecRow"><th colspan="2">Detalhes Produtos</th></tr>
                                                        <c:forEach var="espProd" items="${espProduto}" >
                                                    <tr class="techSpecRow"><td class="techSpecTD1">${espProd.getEspecificacao()}</td><td class="techSpecTD2">${espProd.getResposta()}</td></tr>
                                                        </c:forEach>
                                            </tbody>
                                        </table>

                                        <h4>Perguntas e Respostas</h4>
                                        <c:forEach var="pergProd" items="${pergProduto}" >
                                            <h5>${pergProd.getPergunta()}</h5>
                                            <p>${pergProd.getResposta()}</p>
                                        </c:forEach>

                                    </div>

                                    <br class="clr">
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div> </div>
    </div>
    <!-- MainBody End ============================= -->
    <!-- Footer ================================================================== -->
    <div  id="footerSection">
        <div class="container">
            <div class="row">
                <div class="span3">
                    <h5>ACCOUNT</h5>
                    <a href="login.html">SUA CONTA</a>	
                    <a href="login.html">INFORMAÇÕES PESSOAIS</a> 
                    <a href="login.html">ENDEREÇO</a> 
                    <a href="login.html">DESCONTO</a>  
                    <a href="login.html"> HISTORIA</a>
                </div>
                <div class="span3">
                    <h5>INFORMATION</h5>
                    <a href="contact.html">CONTATO</a>  
                    <a href="register.html">REGISTRE-SE</a>  
                    <a href="legal_notice.html">NOTICIA LEGAL</a>  
                    <a href="tac.html">TERMOS E CONDIÇÕES</a> 
                    <a href="faq.html">FAQ</a>
                </div>
                <div class="span3">
                    <h5>PRODUTOS</h5>
                    <a href="#">PRODUTOS NOVOS</a> 
                    <a href="#">DESTAQUE</a>  
                    <a href="special_offer.html">OFERTAS ESPECAIS</a>  
                    <a href="#">MANUFACTURERS</a> 
                    <a href="#">SUPPLIERS</a> 
                </div>
                <div id="socialMedia" class="span3 pull-right">
                    <h5>MIDIAS SOCIAIS </h5>
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

    <!-- <![endif]-->

    <!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
    <script type="text/javascript">
        jQuery(function ($) {




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


</body>
</html>
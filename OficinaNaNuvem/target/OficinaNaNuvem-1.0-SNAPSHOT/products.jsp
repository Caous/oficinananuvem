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
                        <ul id="topMenu" class="nav pull-right">
                            <li class=""><a href="special_offer.html">Ofertas Especiais</a></li>
                            <li class="">
                                <a href="#login" role="button" data-toggle="modal" style="padding-right:0"><span class="btn btn-large btn-success">Login</span></a>
                                <div id="login" class="modal hide fade in" tabindex="-1" role="dialog" aria-labelledby="login" aria-hidden="false" >
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">Ã</button>
                                        <h3>Login</h3>
                                    </div>
                                    <div class="modal-body">
                                        <form method="POST" action="LoginLogoutServlet">
                                            <div class="control-group">								
                                                <input type="text" id="inputEmail" name="email" placeholder="Email">
                                            </div>
                                            <div class="control-group">
                                                <input type="password" id="inputPassword" name="senha" placeholder="Password">
                                            </div>
                                            <div class="control-group">
                                                <label class="checkbox">
                                                    <input type="checkbox"> Lembrar-me
                                                </label>
                                            </div>

                                            <button type="submit" class="btn btn-success">Entrar</button>
                                            <button class="btn btn-warning" data-dismiss="modal" aria-hidden="true">Cadastre-se</button>
                                        </form>	
                                    </div>
                                </div>
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
                        <div class="well well-small"><a id="myCart" href="#"><img src="themes/images/ico-cart.png" alt="cart">Itens no Carrinho  </a></div>
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

                    </div>
                    <!-- Sidebar end=============================================== -->
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="inicial.html">Home</a> <span class="divider">/</span></li>
                            <li class="active">Produtos</li>
                        </ul>
                        <h3>  Peças </h3>	
                        <hr class="soft"/>
                        <hr class="soft"/>
                        <form class="form-horizontal span6">
                            <div class="control-group">
                                <label class="control-label alignL">Filtrar </label>
                                <select>
                                    <option>Menor Preço</option>
                                    <option>Maior Preço</option>
                                    <option>Mais Vendidos</option>
                                </select>
                            </div>
                        </form>

                        <div id="myTab" class="pull-right">
                            <a href="#blockView" data-toggle="tab"><span class="btn btn-large btn-primary"><i class="icon-th-large"></i></span></a>
                        </div>
                        <br class="clr"/>
                        <div class="tab-content">
                            <div class="tab-pane  active" id="blockView">
                                <ul class="thumbnails">

                                    <c:forEach var="produto" items="${produtos}" >
                                        <div class="col-lg-3">
                                            <li class="span3">
                                                <div class="thumbnail ">

                                                    <a href="product_details.jsp"><img src="data:image/png;base64,${produto.getImgCapa()}" alt="produtos" max-width="30px"/> </a>


                                                    <div class="caption">

                                                        <h5>${produto.getNome()}</h5>
                                                        <p> 
                                                            ${produto.getDescricao()}
                                                        </p>
                                                        <h4 style="text-align:center"> <a class="btn" href="detalhes-produto?idProduto=${produto.getIdProduto()}">Ver Detalhes</a> <a class="btn btn-primary row_currency" href="#">${produto.getValor()}</a></h4>
                                                    </div>
                                                </div>
                                            </li>
                                        </div>
                                    </c:forEach>
                                </ul>
                                <hr class="soft"/>
                            </div>
                        </div>
                        <div class="pagination">
                            <ul>
                                <li><a href="#">&lsaquo;</a></li>
                                <li><a href="#">1</a></li> 	
                            </ul>
                        </div>
                        <br class="clr"/>
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
                        <a href="login.html">SUA CONTA</a>	
                        <a href="login.html">INFORMAÇÕES PESSOAIS	</a> 
                        <a href="login.html">ENDEREÇO</a> 
                        <a href="login.html">DESCONTO</a>  
                        <a href="login.html">HISTORIA</a>
                    </div>
                    <div class="span3">
                        <h5>INFORMAÃÃES</h5>
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
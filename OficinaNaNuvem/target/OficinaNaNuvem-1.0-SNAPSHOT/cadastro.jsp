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
        <link id="callCss" rel="stylesheet" href="themes/bootshop/bootstrap.min.css" media="screen" />
        <link href="themes/css/base.css" rel="stylesheet" media="screen" />
        <!-- Bootstrap style responsive -->
        <link href="themes/css/bootstrap-responsive.min.css" rel="stylesheet" />
        <link href="themes/css/font-awesome.css" rel="stylesheet" type="text/css">
        <!-- Google-code-prettify -->
        <link href="themes/js/google-code-prettify/prettify.css" rel="stylesheet" />
        <!-- fav and touch icons -->
        <link rel="shortcut icon" href="themes/images/ico/favicon.ico">
        <link rel="apple-touch-icon-precomposed" sizes="144x144"
              href="themes/images/ico/apple-touch-icon-144-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="114x114"
              href="themes/images/ico/apple-touch-icon-114-precomposed.png">
        <link rel="apple-touch-icon-precomposed" sizes="72x72" href="themes/images/ico/apple-touch-icon-72-precomposed.png">
        <link rel="apple-touch-icon-precomposed" href="themes/images/ico/apple-touch-icon-57-precomposed.png">
        <style type="text/css" id="enject"></style>
    </head>

    <body>
        <div id="header">
            <div class="container">
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
                            <button type="submit" id="submitButton" name="tarefa" value="BuscaCep" class="btn btn-primary">Buscar</button>
                        </form>
                        <ul id="topMenu" class="nav pull-right">
                            <li class=""><a href="cadastro.jsp">Cadastre-se</a></li>
                            <li class=""><a href="#">Contato</a></li>
                            <li class="">
                                <a href="#login" role="button" data-toggle="modal" style="padding-right:0"><span
                                        class="btn btn-large btn-success">Login</span></a>
                                <div id="login" class="modal hide fade in" tabindex="-1" role="dialog"
                                     aria-labelledby="login" aria-hidden="false">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">X</button>
                                        <h3>Login</h3>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal loginFrm">
                                            <div class="control-group">
                                                <input type="text" id="inputEmail" placeholder="Email">
                                            </div>
                                            <div class="control-group">
                                                <input type="password" id="inputPassword" placeholder="Senha">
                                            </div>
                                            <div class="control-group">
                                                <label class="checkbox">
                                                    <input type="checkbox"> Lembrar
                                                </label>
                                            </div>
                                        </form>
                                        <button type="submit" class="btn btn-success">Entrar</button>
                                        <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
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
                        <div class="well well-small"><a id="myCart" href="product_summary.html"><img
                                    src="themes/images/ico-cart.png" alt="cart">3 Itens no carrinho</a></div>
                        <ul id="sideManu" class="nav nav-tabs nav-stacked">
                            <li class="subMenu open"><a>CATEGORIAS</a>
                                <ul>
                                    <li><a class="active" href="products.html"><i
                                                class="icon-chevron-right"></i>Amortecedor</a></li>
                                    <li><a href="products.html"><i class="icon-chevron-right"></i>Acessório Universal</a>
                                    </li>
                                    <li><a href="products.html"><i class="icon-chevron-right"></i>Peças Importadas</a></li>
                                    <li><a href="products.html"><i class="icon-chevron-right"></i>Peças Nacionais</a></li>
                                </ul>
                            </li>
                        </ul>
                        <br />
                        <div class="thumbnail">
                            <img src="themes/images/payment_methods.png" title="Bootshop Payment Methods"
                                 alt="Payments Methods">
                            <div class="caption">
                                <h5>Métodos de Pagamento</h5>
                            </div>
                        </div>
                    </div>
                    <!-- Sidebar end=============================================== -->
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                            <li class="active">Cadastre-se</li>
                        </ul>
                        <h3>Cadastre-se</h3>
                        <div class="well">

                            <form action="ClienteServlet" class="form-horizontal" method="POST">
                                <h4>Informações Pessoais</h4>
                                <div class="control-group">
                                    <label class="control-label" for="nomeCompleto">Nome Completo <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text" value="${cliente2.getNome()}"  id="nomeCompleto" name="nomeCompleto" placeholder="Seu nome completo" onblur="validaNome()" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="cpf">CPF <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text" value="${cliente2.getCpf_cnpj()}" name="cpf" id="cpf"  placeholder="00000000000"  onblur="validaCpf()" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="rg">RG <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text" value="${cliente2.getRg()}" name="rg" placeholder="000000000" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="email">Email <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text" name="email" value="${cliente2.getEmail()}" placeholder="Email" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="senha">Senha<sup>*</sup></label>
                                    <div class="controls">
                                        <input type="password" value="" name="senha" placeholder="Senha">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="telefone">Telefone<sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text"value="${cliente2.getTelefone()}" name="telefone" placeholder="(11) 1234-5678" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="sexo">Sexo<sup>*</sup></label>
                                    <div class="controls">
                                        <select name="sexo">
                                            <option value="${cliente2.getSexo()}">${cliente2.getSexo()}</option>
                                            <option value="Masculino">Masculino</option>
                                            <option value="Feminino">Feminino</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">Data de Nascimento <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="date"  value="${cliente2.getData_nascimento()}" name="dataNasc" id="dataNasc" onblur="validaData()">
                                    </div>
                                </div>
                                <br>

                                <p><sup>*</sup>Campos obrigatório </p>
                                <p style="color:red">${aviso}</p>
                                <div class="control-group">
                                    <div class="controls">
                                        <input class="btn btn-large btn-success" type="submit" value="Cadastrar" />
                                    </div>
                                </div>
                                <input type="hidden" id="tarefa" name="tarefa" value="Cadastrando">
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- MainBody End ============================= -->
        <!-- Footer ================================================================== -->
        <div id="footerSection">
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
                        <a href="#"><img width="60" height="60" src="themes/images/facebook.png" title="facebook"
                                         alt="facebook" /></a>
                        <a href="#"><img width="60" height="60" src="themes/images/twitter.png" title="twitter"
                                         alt="twitter" /></a>
                        <a href="#"><img width="60" height="60" src="themes/images/youtube.png" title="youtube"
                                         alt="youtube" /></a>
                    </div>
                </div>
            </div><!-- Container End -->
        </div>
        <script type="text/javascript">
            function validaCpf() {
                var x = new Boolean(TestaCPF());
                if (x == false) {
                    alert("CPF inválido");
                }
            }
            function validaNome() {
                var x = new Boolean(TestaNome());
                if (x == false) {
                    alert("Nome inválido, deve preencher com o nome completo");
                }
            }
            function validaData() {
                var x = new Boolean(TestaData());
                if (x == false) {
                    alert("Data Nascimento inválida, deve ter 18 anos completo");
                }
            }
            function TestaData() {
                var data = document.getElementById("dataNasc").value; // pega o valor do input
                data = data.replace(/\//g, "-"); // substitui eventuais barras (ex. IE) "/" por hífen "-"
                var data_array = data.split("-"); // quebra a data em array

                // para o IE onde será inserido no formato dd/MM/yyyy
                if (data_array[0].length != 4) {
                    data = data_array[2] + "-" + data_array[1] + "-" + data_array[0]; // remonto a data no formato yyyy/MM/dd
                }

                // comparo as datas e calculo a idade
                var hoje = new Date();
                var nasc = new Date(data);
                var idade = hoje.getFullYear() - nasc.getFullYear();
                var m = hoje.getMonth() - nasc.getMonth();
                if (m < 0 || (m === 0 && hoje.getDate() < nasc.getDate()))
                    idade--;
                if (idade >= 18) {

                    return true;
                }

                return false;
            }
            function TestaNome() {
                var strNome = document.getElementById('nomeCompleto').value;
                var resultado = strNome.split(" ");
                console.log(resultado.length);
                if (resultado.length >= 2) {
                    if (resultado[0].length > 3 && resultado[1].length > 3) {
                        return true;
                    } else {
                        return false;
                    }

                } else {
                    return false;
                }
            }
            function TestaCPF() {
                var strCPF = document.getElementById('cpf').value;
                var Soma;
                var Resto;
                Soma = 0;
                if (strCPF == "00000000000")
                    return false;
                for (i = 1; i <= 9; i++)
                    Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (11 - i);
                Resto = (Soma * 10) % 11;
                if ((Resto == 10) || (Resto == 11))
                    Resto = 0;
                if (Resto != parseInt(strCPF.substring(9, 10)))
                    return false;
                Soma = 0;
                for (i = 1; i <= 10; i++)
                    Soma = Soma + parseInt(strCPF.substring(i - 1, i)) * (12 - i);
                Resto = (Soma * 10) % 11;
                if ((Resto == 10) || (Resto == 11))
                    Resto = 0;
                if (Resto != parseInt(strCPF.substring(10, 11)))
                    return false;
                return true;
            }

        </script>
        <!-- Placed at the end of the document so the pages load faster ============================================= -->
        <script src="themes/js/jquery.js" type="text/javascript"></script>
        <script src="themes/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="themes/js/google-code-prettify/prettify.js"></script>

        <script src="themes/js/bootshop.js"></script>
        <script src="themes/js/jquery.lightbox-0.5.js"></script>
        =======
    <body>
        <div id="header">
            <div class="container">
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
                            <button type="submit" id="submitButton" name="tarefa" value="BuscaCep" class="btn btn-primary">Buscar</button>
                        </form>
                        <ul id="topMenu" class="nav pull-right">
                            <li class=""><a href="cadastro.jsp">Cadastre-se</a></li>
                            <li class=""><a href="#">Contato</a></li>
                            <li class="">
                                <a href="#login" role="button" data-toggle="modal" style="padding-right:0"><span
                                        class="btn btn-large btn-success">Login</span></a>
                                <div id="login" class="modal hide fade in" tabindex="-1" role="dialog"
                                     aria-labelledby="login" aria-hidden="false">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal"
                                                aria-hidden="true">X</button>
                                        <h3>Login</h3>
                                    </div>
                                    <div class="modal-body">
                                        <form class="form-horizontal loginFrm">
                                            <div class="control-group">
                                                <input type="text" id="inputEmail" placeholder="Email">
                                            </div>
                                            <div class="control-group">
                                                <input type="password" id="inputPassword" placeholder="Senha">
                                            </div>
                                            <div class="control-group">
                                                <label class="checkbox">
                                                    <input type="checkbox"> Lembrar
                                                </label>
                                            </div>
                                        </form>
                                        <button type="submit" class="btn btn-success">Entrar</button>
                                        <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
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
                        <div class="well well-small"><a id="myCart" href="product_summary.html"><img
                                    src="themes/images/ico-cart.png" alt="cart">3 Itens no carrinho</a></div>
                        <ul id="sideManu" class="nav nav-tabs nav-stacked">
                            <li class="subMenu open"><a>CATEGORIAS</a>
                                <ul>
                                    <li><a class="active" href="products.html"><i
                                                class="icon-chevron-right"></i>Amortecedor</a></li>
                                    <li><a href="products.html"><i class="icon-chevron-right"></i>Acessório Universal</a>
                                    </li>
                                    <li><a href="products.html"><i class="icon-chevron-right"></i>Peças Importadas</a></li>
                                    <li><a href="products.html"><i class="icon-chevron-right"></i>Peças Nacionais</a></li>
                                </ul>
                            </li>
                        </ul>
                        <br />
                        <div class="thumbnail">
                            <img src="themes/images/payment_methods.png" title="Bootshop Payment Methods"
                                 alt="Payments Methods">
                            <div class="caption">
                                <h5>Métodos de Pagamento</h5>
                            </div>
                        </div>
                    </div>
                    <!-- Sidebar end=============================================== -->
                    <div class="span9">
                        <ul class="breadcrumb">
                            <li><a href="index.html">Home</a> <span class="divider">/</span></li>
                            <li class="active">Cadastre-se</li>
                        </ul>
                        <h3>Cadastre-se</h3>
                        <div class="well">
                            <!--
<div class="alert alert-info fade in">
<button type="button" class="close" data-dismiss="alert">×</button>
<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
</div>
<div class="alert fade in">
<button type="button" class="close" data-dismiss="alert">×</button>
<strong>Lorem Ipsum is simply dummy</strong> text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
</div>
<div class="alert alert-block alert-error fade in">
<button type="button" class="close" data-dismiss="alert">×</button>
<strong>Lorem Ipsum is simply</strong> dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s
</div> -->
                            <form action="ClienteServlet" class="form-horizontal" method="POST">
                                <h4>Informações Pessoais</h4>
                                <div class="control-group">
                                    <label class="control-label" for="nomeCompleto">Nome Completo <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text" value="${cliente.getNome()}"  name="nomeCompleto" placeholder="Seu nome completo" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="cpf">CPF <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text" value="${cliente.getCpf_cnpj()}" name="cpf" placeholder="000.000.000-00" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="rg">RG <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text" value="${cliente.getRg()}" name="rg" placeholder="00.000.000-0" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="email">Email <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text" name="email" value="${cliente.getEmail()}" placeholder="Email" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="senha">Senha<sup>*</sup></label>
                                    <div class="controls">
                                        <input type="password" value="${cliente.getBanana()}" name="senha" placeholder="Senha">
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="telefone">Telefone<sup>*</sup></label>
                                    <div class="controls">
                                        <input type="text"value="${cliente.getTelefone()}" name="telefone" placeholder="(11) 1234-5678" >
                                    </div>
                                </div>
                                <div class="control-group">
                                    <label class="control-label" for="sexo">Sexo<sup>*</sup></label>
                                    <div class="controls">
                                        <select name="sexo">
                                            <option value="${cliente.getSexo()}">${cliente.getSexo()}</option>
                                            <option value="Masculino">Masculino</option>
                                            <option value="Feminino">Feminino</option>

                                        </select>
                                    </div>
                                </div>

                                <div class="control-group">
                                    <label class="control-label">Data de Nascimento <sup>*</sup></label>
                                    <div class="controls">
                                        <input type="date"  value="${cliente.getData_nascimento()}" name="dataNasc" >
                                    </div>
                                </div>
                                <br>

                                <p><sup>*</sup>Campos obrigatórios </p>
</body>

                                </html>
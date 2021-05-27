<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- Le styles -->
        <link href="assets2/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets2/css/font-awesome.min.css" rel="stylesheet">
        <link href="assets2/css/style.css" rel="stylesheet">
        <link href="assets2/css/animate.css" rel="stylesheet">
        <link href="assets2/css/skin-blue.css" rel="stylesheet">
        <!-- Le fav -->
        <link rel="shortcut icon" href="assets2/img/LogoOficinaNaNuvem.png">
        <title>Oficina Na Nuvem</title>
        <!--[if lt IE 9]>
        <style>
        nav,.container,header#top-section,.carousel,.demobutton {display:none;}
        </style>
        <div id='browserWarning'>
        You are using an outdated version of Internet Explorer. Please, switch to
        <a style="color:red;" href='http://getfirefox.com'>Firefox</a>,
        <a style="color:red;" href='http://www.google.de/chrome'>Google Chrome</a>
        ,
        <a style="color:red;" href='http://www.apple.com/safari/'>Safari</a>
        or the latest version of
        <a style="color:red;" href='http://windows.microsoft.com/en-US/internet-explorer/downloads/ie'>Internet Explorer</a>
        for a
        <span class='bold'>better</span>
        and
        <span class='bold'>safer</span>
        experience.
        </div>
        <![endif]-->
        <style>
            .pricing-table .heading {
                text-align: center;
                padding-bottom: 10px;
                border-bottom: 1px solid rgba(0, 0, 0, 0.1); 
            }

            .pricing-table .item {
                background-color: #ffffff;
                box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.075);
                border-top: 2px solid #5ea4f3;
                padding: 30px;
                overflow: hidden;
                position: relative; 
            }

            .pricing-table .col-md-5:not(:last-child) .item {
                margin-bottom: 30px; 
            }

            .pricing-table .item button {
                font-weight: 600; 
            }

            .pricing-table .ribbon {
                width: 160px;
                height: 32px;
                font-size: 12px;
                text-align: center;
                color: #fff;
                font-weight: bold;
                box-shadow: 0px 2px 3px rgba(136, 136, 136, 0.25);
                background: #4dbe3b;
                transform: rotate(45deg);
                position: absolute;
                right: -42px;
                top: 20px;
                padding-top: 7px; 
            }

            .pricing-table .item p {
                text-align: center;
                margin-top: 20px;
                opacity: 0.7; 
            }

            .pricing-table .features .feature {
                font-weight: 600; }

            .pricing-table .features h4 {
                text-align: center;
                font-size: 18px;
                padding: 5px; 
            }

            .pricing-table .price h4 {
                margin: 15px 0;
                font-size: 45px;
                text-align: center;
                color: #2288f9; 
            }

            .pricing-table .buy-now button {
                text-align: center;
                margin: auto;
                font-weight: 600;
                padding: 9px 0; 
            }
        </style>
    </head>
    <!-- /head-->
    <body data-spy="scroll" data-target=".navbar">
        <nav id="topnav" class="navbar navbar-fixed-top navbar-default" role="navigation">
            <div class="container">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#top-section"><img src="assets2/img/LogoOficinaNaNuvemTamanho.png" style="padding: 2px;"></a>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse navbar-ex1-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="active"><a class="sscroll" href="#top-section">Home</a></li>
                        <li><a class="sscroll" href="#Section-1">Serviços</a></li>
                        <li><a class="sscroll" href="#Section-2">Planos</a></li>
                        <li><a class="sscroll" href="#Section-3">Suporte</a></li>
                        <li><a class="sscroll" href="#Section-4">Sobre</a></li>                        
                        <li><a class="sscroll" href="ProdutosWebServlet">Loja</a></li>
                        <li><a class="sscroll" href="LoginServlet">Sistema</a></li>
                    </ul>
                </div>
                <!-- /.navbar-collapse -->
            </div>
        </nav>
        <!-- HOMEPAGE -->
        <header id="top-section" class="fullbg">
            <div class="jumbotron">
                <div id="carousel_intro" class="carousel slide fadeing">
                    <div class="carousel-inner">
                        <div class="active item" id="slide_1">
                            <div class="carousel-content">					
                                <div class="animated fadeInDownBig">
                                    <h1>Se você procura um sistema para gerenciar sua oficina, temos a solução.</h1>
                                    <h3 style="color: #FFF">Imagine controlar sua empresa de qualquer lugar, contendo um E-Commerce e um sistema ERP dedicado a você</h3>
                                    <div>
                                        <div class="col-lg-4" style="padding: 5%"><img src="assets2/img/ecommerce.png" style="padding: 2%"/><p>Tenha o melhor E-Commerce versátil e preparador para seu cliente e crescer suas vendas.</p></div>
                                        <div class="col-lg-4" style="padding: 5%"><img src="assets2/img/management.png" style="padding: 2%"/><p>Junto tenha o melhor do nosso sistema ERP, para analisar toda sua empresa, desde seu setor de vendas ao estoque da sua loja, com sistema de chamados e muitos mais.</p></div>
                                        <div class="col-lg-4" style="padding: 5%"><img src="assets2/img/seo.png" style="padding: 2%"/><p>Tenha nossos sitema todo estruturado para o SEO do seu site, efetue o marketing do seu site com o trafego pago conosco</p></div>
                                    </div>
                                </div>
                                <br/>
                            </div>
                        </div>
                        <div class="item" id="slide_2">
                            <div class="carousel-content">					
                                <div class="animated fadeInDownBig">
                                    <img src="assets2/img/shopping-card.png" style="padding: 2%"/>
                                    <p>Somos falicitadores da sua empresa, aqui você pode gerenciar todas suas vendas, gerenciar suas vendas em loja presencial ou E-Commerce, seus clientes podem se cadastrar e fazer a compra de todos seus produtos de forma fácil, prática, rápida e segura. Entre em contato com nossos consultores e faça um teste.</p>                                    
                                </div>
                                <br/>
                                <a href="#" class="buttoncolor animated fadeInRightBig"><i class="fa fa-link"></i>&nbsp; Saiba mais </a>

                            </div>
                        </div>
                        <div class="item" id="slide_3">
                            <div class="carousel-content">					
                                <div class="animated fadeInDownBig">
                                    <img src="assets2/img/management.png" style="padding: 2%"/>
                                    <p>Junto tenha o melhor do nosso sistema ERP, para analisar toda sua empresa, desde seu setor de vendas ao estoque da sua loja, com sistema de chamados, gerenciamento de clientes, relatórios analítico e sintéticos, gerenciando também seus funcionários com nível de acesso.</p>					
                                </div>
                                <br/>
                                <a href="#" class="buttoncolor animated fadeInRightBig"><i class="fa fa-link"></i>&nbsp; Saiba mais </a>
                            </div>
                        </div>
                        <div class="item" id="slide_4">
                            <div class="carousel-content">					
                                <div class="animated fadeInDownBig">
                                    <img src="assets2/img/seo.png" style="padding: 2%"/><p>Tenha nossos sitema todo estruturado para o SEO do seu site, efetue o marketing do seu site com o trafego pago conosco</p>					
                                </div>
                                <br/>
                                <a href="#" class="buttoncolor animated fadeInRightBig"><i class="fa fa-link"></i>&nbsp; Saiba mais </a>
                            </div>
                        </div>
                    </div>
                </div>
                <button class="left carousel-control" href="#carousel_intro" data-slide="prev" data-start="opacity: 0.6; left: 0%;" data-250="opacity:0; left: 5%;"><i class="fa fa-chevron-left"></i></button>
                <button class="right carousel-control" href="#carousel_intro" data-slide="next" data-start="opacity: 0.6; right: 0%;" data-250="opacity:0; right: 5%;"><i class="fa fa-chevron-right"></i></button>
            </div>
            <div class="inner-top-bg">
            </div>
        </header>
        <!-- / HOMEPAGE -->

        <!-- SECTION-2(gallery) -->
        <section id="Section-1" class="fullbg color-white">
            <div class="section-divider">
            </div>
            <div class="container demo-3">
                <div class="row">
                    <div class="page-header text-center col-sm-12 col-lg-12 animated fade">
                        <h1>Serviços</h1>
                    </div>
                </div>
                <div class="row animated fadeInUpNow">
                    <div class="col-sm-12 col-lg-12">
                        <ul class="grid cs-style-4">
                            <li>
                                <figure>
                                    <div>
                                        <img src="https://blog.bling.com.br/wp-content/uploads/2018/06/controle-de-estoque_Easy-Resize.com_.jpg" alt="premium-themes-templates">
                                    </div>
                                    <figcaption>
                                        <h3>Gerênciamento de Estoque</h3>
                                    </figcaption>
                                </figure>
                            </li>
                            <li>
                                <figure>
                                    <div>
                                        <img src="assets2/img/OrdemServico.png" alt="premium-themes-templates" width="60%">
                                    </div>
                                    <figcaption>
                                        <h3>Ordem de serviço</h3>
                                    </figcaption>
                                </figure>
                            </li>
                            <li>
                                <figure>
                                    <div>
                                        <img src="assets2/img/Graficos.png" alt="premium-themes-templates">
                                    </div>
                                    <figcaption>
                                        <h3>Gerênciamento de financeiro</h3>
                                    </figcaption>
                                </figure>
                            </li>
                            <li>
                                <figure>
                                    <div>
                                        <img src="https://neogrid-site.s3.amazonaws.com/uploads/blog/2016/04/estoque.jpg" alt="premium-themes-templates">
                                    </div>
                                    <figcaption>
                                        <h3>Gerenciar clientes</h3>
                                    </figcaption>
                                </figure>
                            </li>
                            <li>
                                <figure>
                                    <div>
                                        <img src="assets2/img/relatorio.png" alt="premium-themes-templates">
                                    </div>
                                    <figcaption>
                                        <h3>Financeiro</h3>
                                    </figcaption>
                                </figure>
                            </li>
                            <li>
                                <figure>
                                    <div>
                                        <img src="https://i1.wp.com/g2tecnologia.com.br/wp-content/uploads/2016/06/logc3adstica_vocc3aa_jc3a1_ouviu_falar_em_peps-jpg.jpeg?fit=1000%2C671&ssl=1" alt="premium-themes-templates">
                                    </div>
                                    <figcaption>
                                        <h3>Mercadoria</h3>
                                    </figcaption>
                                </figure>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
        <section id="Section-2" class="fullbg">
            <div class="section-divider">
            </div>
            <div class="container">
                <div class="row">
                    <div class="page-header text-center col-sm-12 col-lg-12 color-white animated fade">
                        <h1>Planos</h1>
                        <p class="lead">
                            Conheça nossos planos ou personalize um para sua empresa
                        </p>
                    </div>
                </div>
                <div class="row ">
                    <div class="col-md-12 animated fadeInUpNow">

                        <section class="pricing-table">
                            <div class="container">				
                                <div class="row justify-content-md-center">
                                    <div class="col-md-5 col-lg-3">
                                        <div class="item">
                                            <div class="heading">
                                                <h3>Básico</h3>
                                            </div>
                                            <p>Invista no controle da sua empresa com nosso ERP, tenha a melhor análise da sua empresa aqui conosco!</p>
                                            <div class="features">
                                                <h5> <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Suporte</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Relatórios</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Chamados</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Estoque</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Gerenciamento de clientes </span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Gerenciamento de filial</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Gerenciamento de funcionários</span>  </h5>
                                            </div>
                                            <div class="price">
                                                <h4>R$ 50</h4>
                                            </div>
                                            <button class="btn btn-block btn-outline-primary" type="submit">Saiba Mais</button>
                                        </div>
                                    </div>
                                    <div class="col-md-5 col-lg-3">
                                        <div class="item">
                                            <div class="ribbon"></div>
                                            <div class="heading">
                                                <h3>Médio</h3>
                                            </div>
                                            <p>Invista no controle da sua empresa com nosso ERP, tenha a melhor análise da sua empresa aqui conosco!</p>
                                            <div class="features">
                                                <h5> <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Suporte</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Relatórios</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Chamados</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Estoque</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Gerenciamento de clientes </span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Gerenciamento de filial</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Gerenciamento de funcionários</span>  </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Emissão de ordem de serviço</span>  </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Relatório de serviços</span>  </h5>
                                            </div>
                                             <div class="price">
                                                <h4>R$ 75</h4>
                                            </div>
                                            <button class="btn btn-block btn-outline-primary" type="submit">Saiba Mais</button>
                                        </div>
                                    </div>
                                    <div class="col-md-5 col-lg-3">
                                        <div class="item">
                                            <div class="heading">
                                                <h3>Lux</h3>
                                            </div>
                                            <p>Invista no controle da sua empresa com nosso ERP, juntamente faça as vendas online da sua loja no nosso ERP! Tenha a melhor análise da sua empresa aqui conosco!</p>
                                            <div class="features">
                                                <h5> <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Suporte</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Relatórios</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Chamados</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Estoque</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Gerenciamento de clientes </span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature"> Gerenciamento de filial</span> </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Gerenciamento de funcionários</span>  </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Emissão de ordem de serviço</span>  </h5>
                                                <h5><svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
                                                    <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
                                                    </svg> <span class="feature">Relatório de serviços</span>  </h5>
                                            </div>
                                             <div class="price">
                                                <h4>R$ 200</h4>
                                            </div>
                                            <button class="btn btn-block btn-outline-primary" type="submit">Saiba Mais</button>
                                        </div>
                                    </div>
                                    <div class="col-md-5 col-lg-3">
                                        <div class="item">
                                            <div class="heading">
                                                <h3>Personalizado</h3>
                                            </div>
                                            <p>Personalize um plano que seja adequado ao seu plano aqui conosco.</p>
                                            <div class="features">
                                                <h4><span class="feature">Full Support</span> : <span class="value">Yes</span></h4>
                                                <h4><span class="feature">Duration</span> : <span class="value">120 Days</span></h4>
                                                <h4><span class="feature">Storage</span> : <span class="value">150GB</span></h4>
                                            </div>
                                            <button class="btn btn-block btn-outline-primary" type="submit">Saiba Mais</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </section>

                    </div>
                </div>

                <!-- end row -->
            </div>
        </section>
        <!--  SECTION-1 -->
        <section id="Section-3" class="fullbg">
            <div class="section-divider">
            </div>
            <div class="container">
                <div class="row">
                    <div class="page-header text-center col-sm-12 col-lg-12 color-white animated fade">
                        <h1>Suporte</h1>
                        <p class="lead">
                            Você irá contar com suporte especializado para seu negócio
                        </p>
                    </div>
                </div>
                <div class="row color-white">
                    <div class="col-md-12 animated fadeInUpNow">
                        <div class="boxservice bottomb">
                            <i class="fa fa-globe"></i>
                            <h3>Conectvidade</h3>
                            <p>
                                Atendemos todos os mercados ferroviários, aereo e portuario em territorio nacional.
                            </p>
                        </div>
                        <div class="boxservice bottomb">
                            <i class="fa fa-user"></i>
                            <h3>Suporte</h3>
                            <p>
                                Contem com suporte especializado 12x6, sempre com atendimento online
                            </p>
                        </div>
                        <div class="boxservice rightb bottomb">
                            <i class="fa fa-dashboard"></i>
                            <h3>Atendimento Full</h3>
                            <p>
                                Velocidade de atendimento e entrega de produtos por todo território nacional. 
                            </p>
                        </div>


                    </div>
                </div>
                <!-- end row -->
            </div>
        </section>
        <section id="Section-4" class="fullbg">
            <div class="section-divider">
            </div>
            <div class="container">
                <div class="row">
                    <div class="page-header text-center col-sm-12 col-lg-12 color-white animated fade">
                        <h1>Quem somos nós?</h1>
                        <p class="lead">
                            Conheça um pouco sobre nós
                        </p>
                    </div>
                </div>
                <div class="row color-white">
                    <div class="col-md-12 animated fadeInUpNow">
                        <div class="boxservice">
                            <i class="fa fa-globe"></i>
                            <h3>Conectvidade</h3>
                            <p>
                                Atendemos todos os mercados ferroviários, aereo e portuario em territorio nacional.
                            </p>
                        </div>
                        <div class="boxservice">
                            <i class="fa fa-user"></i>
                            <h3>Time</h3>
                            <p>
                                Contamos com time de profissionais totalmente qualificados.
                            </p>
                        </div>
                        <div class="boxservice rightb">
                            <i class="fa fa-dashboard"></i>
                            <h3>Atendimento Full</h3>
                            <p>
                                Velocidade de atendimento e entrega de produtos por todo território nacional. 
                            </p>
                        </div>
                        <div class="boxservice bottomb">
                            <i class="fa fa-cloud-download"></i>
                            <h3>Atendimento</h3>
                            <p>
                                Atendimento e Suportamente totalmente online, profissionais qualificados para suporte online.
                            </p>
                        </div>
                        <div class="boxservice bottomb">
                            <i class="fa fa-trophy"></i>
                            <h3>Premios</h3>
                            <p>
                                Nos ultimos anos recebemos premios com tema de entrega com mais rapidez e qualidade de produtos.
                            </p>
                        </div>
                        <div class="boxservice rightb bottomb">
                            <i class="fa fa-microphone"></i>
                            <h3>Relacionamento Cliente</h3>
                            <p>
                                Procuramos manter contato com clientes para Feedbacks positivos e negativos para sempre possamos melhorar nosso atendimento.
                            </p>
                        </div>
                    </div>
                </div>
                <!-- end row -->
            </div>
        </section>

        <!-- FOOTER -->
        <footer id="foot-sec">
            <div class="footerdivide">
            </div>
            <div class="container ">
                <div class="row">
                    <div class="text-center color-white col-sm-4 col-lg-4">
                        <a href="#top-section"><img src="assets2/img/LogoOficinaNaNuvem.png" width="50%" style="padding: 2px;"></a>
                        <h3>Oficina na Nuvem</h3>
                        <p>Sistema para oficinas</p>
                        <p>E-mail: oficinananuvem@gmail.com</p>
                        <p>Tel.: (11) 94261-6650</p>
                    </div>
                    <div class="text-center color-white col-sm-4 col-lg-4" style="border-left: 1px solid #fff; border-right: 1px solid #fff">

                        <ul>Home</ul>
                        <ul>Serviços</ul>
                        <ul>Planos</ul>
                        <ul>Suportes</ul>
                        <ul>Sobre</ul>
                        <ul>Loja</ul>
                        <ul>Sistema</ul>

                    </div>
                    <div class="text-center color-white col-sm-4 col-lg-4">
                        <ul class="social-icons">
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
                            <li><a href="#"><i class="fa fa-pinterest"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="text-center color-white col-sm-12 col-lg-12" style="padding: 20px;">
                    <p>Copyright © 2021 Oficina Na Nuvem | Desenvolvido por ExecuteDevs</p>
                </div>
            </div>
            
        </footer>
        <!-- Le javascript
            ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="assets2/js/jquery.min.js" type="text/javascript"></script>
        <script src="assets2/js/bootstrap.js"></script>
        <script src="assets2/js/jquery.parallax-1.1.3.js" type="text/javascript"></script>
        <script src="assets2/js/jquery.localscroll-1.2.7-min.js" type="text/javascript"></script>
        <script src="assets2/js/jquery.scrollTo-1.4.6-min.js" type="text/javascript"></script>
        <script src="assets2/js/jquery.bxslider.min.js"></script>
        <script src="assets2/js/jquery.placeholder.js"></script>
        <script src="assets2/js/modernizr.custom.js"></script>
        <script src="assets2/js/toucheffects.js"></script>
        <script src="assets2/js/animations.js"></script>
        <script src="assets2/js/init.js"></script>
    </body>
</html>
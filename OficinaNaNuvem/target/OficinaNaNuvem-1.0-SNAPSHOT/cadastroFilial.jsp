<%@page import="Infrastructure.Access"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Infrastructure.DBConnection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page import="java.io.*" %>  
<%@ page import="java.util.*" %>  
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="UTF-8" />
        <title>Dashboard - Ace Admin</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="description" content="overview &amp; stats" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="assets/css/bootstrap.min.css" />
        <link rel="stylesheet" href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

        <!-- page specific plugin styles -->

        <!-- text fonts -->
        <link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

        <!--[if lte IE 9]>
                            <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
                    <![endif]-->
        <link rel="stylesheet" href="assets/css/ace-skins.min.css" />
        <link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

        <!--[if lte IE 9]>
                      <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
                    <![endif]-->

        <!-- inline styles related to this page -->

        <!-- page specific plugin styles -->
        <link rel="stylesheet" href="assets/css/jquery-ui.custom.min.css" />
        <link rel="stylesheet" href="assets/css/chosen.min.css" />
        <link rel="stylesheet" href="assets/css/bootstrap-datepicker3.min.css" />
        <link rel="stylesheet" href="assets/css/bootstrap-timepicker.min.css" />
        <link rel="stylesheet" href="assets/css/daterangepicker.min.css" />
        <link rel="stylesheet" href="assets/css/bootstrap-datetimepicker.min.css" />
        <link rel="stylesheet" href="assets/css/bootstrap-colorpicker.min.css" />

        <!-- ace settings handler -->
        <script src="assets/js/ace-extra.min.js"></script>

        <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

        <!--[if lte IE 8]>
                    <script src="assets/js/html5shiv.min.js"></script>
                    <script src="assets/js/respond.min.js"></script>
                    <![endif]-->
        <script>
            <%
                Connection conn = DBConnection.obterConexao();
                List<Cookie> _cookies = new ArrayList<Cookie>();
                _cookies = Arrays.asList(request.getCookies());
                int id_perfil = Access.Id_Pefil(_cookies);
                String SQLstate = "";
                if (id_perfil == 1) {
                    SQLstate = "SELECT MU.* FROM tb_menu AS MU	WHERE MU.DATA_EXCLUSAO IS NULL  ORDER BY MU.PAGINA;";
                } else {
                    SQLstate = "SELECT MU.* FROM tb_menu AS MU 	INNER JOIN tb_permissao AS PE ON MU.ID_MENU = PE.ID_MENU	WHERE MU.DATA_EXCLUSAO IS NULL AND PE.DATA_EXCLUSAO IS NULL AND PE.ID_PERFIL = " + id_perfil + " ORDER BY MU.PAGINA;";
                }

                PreparedStatement ps = conn.prepareStatement(SQLstate,
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);
                ResultSet rs4 = ps.executeQuery();
                String menuRelatorio = "";
                String menuDashboard = "";
                String menuCadastro = "";
                String menuCompraVenda = "";
                String menuOutros = "";
                while (rs4.next()) {
                    String TipoMenuAux = rs4.getString("MU.TIPOMENU").toString();
                    switch (Integer.parseInt(TipoMenuAux)) {
                        case 1:
                            menuRelatorio += "<li class=''> <a href=" + rs4.getString("MU.URL").toString() + "> <i class='menu-icon fa fa-caret-right'></i>" + rs4.getString("MU.PAGINA").toString() + "</a><b class='arrow'></b></li>";
                            break;
                        case 2:
                            menuCadastro += "<li class=''> <a href=" + rs4.getString("MU.URL").toString() + "> <i class='menu-icon fa fa-caret-right'></i>" + rs4.getString("MU.PAGINA").toString() + "</a><b class='arrow'></b></li>";
                            break;
                        case 3:
                            menuCompraVenda += "<li class=''> <a href=" + rs4.getString("MU.URL").toString() + "> <i class='menu-icon fa fa-caret-right'></i>" + rs4.getString("MU.PAGINA").toString() + "</a><b class='arrow'></b></li>";
                            break;
                        case 4:
                            menuDashboard += "<li class=''> <a href=" + rs4.getString("MU.URL").toString() + "> <i class='menu-icon fa fa-caret-right'></i>" + rs4.getString("MU.PAGINA").toString() + "</a><b class='arrow'></b></li>";
                            break;
                        case 5:
                            menuOutros += "<li class=''> <a href=" + rs4.getString("MU.URL").toString() + "> <i class='menu-icon fa fa-caret-right'></i>" + rs4.getString("MU.PAGINA").toString() + "</a><b class='arrow'></b></li>";
                            break;
                    }
                }

                conn.close();
            %>
        </script>

    </head>

    <body class="no-skin">
        <div id="navbar" class="navbar navbar-default          ace-save-state">
            <div class="navbar-container ace-save-state" id="navbar-container">
                <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
                    <span class="sr-only">Toggle sidebar</span>

                    <span class="icon-bar"></span>

                    <span class="icon-bar"></span>

                    <span class="icon-bar"></span>
                </button>

                <div class="navbar-header pull-left">
                    <a href="IndexServlet" class="navbar-brand">
                        <small>
                            <img src="assets/images/gallery/reparar.png" width="15%">
                            ThinkCode
                        </small>
                    </a>
                </div>

                <div class="navbar-buttons navbar-header pull-right" role="navigation">
                    <ul class="nav ace-nav">

                        <li class="purple dropdown-modal">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                                <span class="badge badge-important">${pedidos.size()}</span>
                            </a>

                            <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                                <li class="dropdown-header">
                                    <i class="ace-icon fa fa-exclamation-triangle"></i> ${pedidos.size()} Notificações
                                </li>

                                <li class="dropdown-content">
                                    <ul class="dropdown-menu dropdown-navbar navbar-pink"> 
                                        <li>
                                            <a href="SolicitacaoServlet">
                                                <div class="clearfix">
                                                    <span class="pull-left">
                                                        <i class="btn btn-xs no-hover btn-success fa fa-product-hunt"></i>
                                                        Solicitações
                                                    </span>
                                                    <span class="pull-right badge badge-success">${pedidos.size()}</span>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </li>



                        <li class="light-blue dropdown-modal">
                            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                <img class="nav-user-photo" src="assets/images/avatars/user.jpg" alt="Jason's Photo" />
                                <span class="user-info">
                                    <small>Bem-vindo,</small>
                                    <label id="lblNome"></label>

                                </span>

                                <i class="ace-icon fa fa-caret-down"></i>
                            </a>

                            <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                                <form action="LoginServlet" method="POST" style="padding: 2%; margin-left: 5%">
                                    <li>
                                        <a href="#" id="idSair" style="color: #000">Sair
                                        </a>
                                    </li>
                                    <button id="btnSair" name="tarefa" type="submit" style="display:none;" value="sair"></button>
                                </form>
                            </ul> 
                        </li>
                    </ul>
                </div>
            </div>
            <!-- /.navbar-container -->
        </div>

        <div class="main-container ace-save-state" id="main-container">
            <script type="text/javascript">
                try {
                    ace.settings.loadState('main-container')
                } catch (e) {
                }
            </script>

            <div id="sidebar" class="sidebar                  responsive                    ace-save-state">
                <script type="text/javascript">

                    try {
                        ace.settings.loadState('sidebar')
                    } catch (e) {
                    }
                </script>


                <!-- /.sidebar-shortcuts -->
                <ul class="nav nav-list">
                    <li class="Active">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-tachometer"></i>
                            <span class="menu-text">
                                Dashboard
                            </span>

                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <%=menuDashboard%>                           
                        </ul>
                    </li>

                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-book"></i>
                            <span class="menu-text">
                                Relatórios
                            </span>

                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <%=menuRelatorio%>                             
                        </ul>
                    </li>

                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-pencil-square-o"></i>
                            <span class="menu-text">
                                Cadastro
                            </span>

                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <%=menuCadastro%>

                        </ul>
                    </li>

                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-desktop"></i>
                            <span class="menu-text">
                                Compra/Venda
                            </span>

                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <%=menuCompraVenda%>
                        </ul>
                    </li>
                    <li class="">
                        <a href="#" class="dropdown-toggle">
                            <i class="menu-icon fa fa-desktop"></i>
                            <span class="menu-text">
                                Outros
                            </span>

                            <b class="arrow fa fa-angle-down"></b>
                        </a>

                        <b class="arrow"></b>

                        <ul class="submenu">
                            <%=menuOutros%>
                        </ul>
                    </li>
                </ul>
                <!-- /.nav-list -->

                <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                    <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>
            </div>

            <div class="main-content">
                <div class="main-content-inner">


                    <div class="page-content">
                        <form action="FilialServlet" method="POST"> 
                            <div class="row">                               
                                <div class="col-lg-12">
                                    <p>
                                    <h4>Cadastro de Filial <i class="fa fa-university" aria-hidden="true"></i> </h1>
                                        <hr>
                                        </p>
                                        </div>
                                        <div class="col-xs-12 col-sm-12">
                                            <div class="row">
                                                <div class="col-sm-12">
                                                    <div class="widget-box">
                                                        <div class="widget-header">
                                                            <h4 class="widget-title center">Informações da Filial</h4>

                                                            <div class="widget-toolbar">
                                                                <a href="#" data-action="collapse">
                                                                    <i class="ace-icon fa fa-chevron-up"></i>
                                                                </a>
                                                            </div>
                                                        </div>

                                                        <div class="widget-body">
                                                            <div class="widget-main">
                                                                <div class="form-group">
                                                                    <div class="col-lg-3">
                                                                        <label for="number-button" class="block">Nome</label>
                                                                        <input type="text" id="form-field-1-1" placeholder="Nome" class="form-control" name="nome" required value="${nome}"/>
                                                                    </div>
                                                                    <div class="col-lg-3">
                                                                        <label for="number-button" class="block">CNPJ</label>
                                                                        <input type="text" id="form-field-1-1" placeholder="00.000.000/0000-00" class="form-control input-mask-cnpj" name="cnpj" required value="${cnpj}"/>
                                                                    </div>
                                                                    <div class="col-lg-3">
                                                                        <label for="number-button" class="block">Descrição</label>
                                                                        <input type="text" id="form-field-1-1" placeholder="Descrição" class="form-control" name="descricao" required value="${descricao}"/>
                                                                    </div>

                                                                    <div class="col-lg-3">
                                                                        <label for="number-button" class="block">Telefone</label>
                                                                        <div class="input-group">
                                                                            <span class="input-group-addon">
                                                                                <i class="ace-icon fa fa-phone"></i>
                                                                            </span>

                                                                            <input class="form-control input-mask-phone" type="text" id="form-field-mask-2" placeholder="(00)00000-0000" name="telefone" required value="${telefone}"/>
                                                                        </div>
                                                                    </div>

                                                                </div>
                                                                <label>
                                                                    &nbsp;<br />

                                                                </label>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="widget-box">
                                            <div class="widget-header">
                                                <h4 class="widget-title center">Endereço</h4>

                                                <div class="widget-toolbar">
                                                    <a href="#" data-action="collapse">
                                                        <i class="ace-icon fa fa-chevron-up"></i>
                                                    </a>
                                                </div>
                                            </div>

                                            <div class="widget-body">
                                                <div class="widget-main">
                                                    <div class="form-group">
                                                        <div class="col-lg-3">
                                                            <label for="number-button" class="block">CEP</label>

                                                            <input type="text" id="form-field-1-1" placeholder="00000-000" class="form-control input-mask-cep" name="cepFilial" value="${cepFilial}"/>

                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label for="number-button" class="block">Rua</label>
                                                            <div class="input-group">
                                                                <span class="input-group-addon">
                                                                    <i class="ace-icon fa fa-map"></i>
                                                                </span>
                                                                <input type="text" id="form-field-1-1" placeholder="Nome da rua" class="form-control" name="ruaFilial" required value="${ruaFilial}"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-3">
                                                            <label for="number-button" class="block">Bairro</label>
                                                            <input type="text" id="form-field-1-1" placeholder="Nome do bairro" class="form-control" name="bairroFilial" required value="${bairroFilial}"/>
                                                        </div>
                                                        <div class="col-lg-3">

                                                            <label for="number-button" class="block">Número </label>
                                                            <input class="form-control" type="text" placeholder="" id="form-field-mask-2" name="numeroFilial" required value="${numeroFilial}"/>
                                                        </div>

                                                        <div class="col-lg-3">
                                                            <label for="number-button" class="block">Complemento</label>

                                                            <input class="form-control" type="text" id="form-field-mask-2" name="complementoFilial" value="${complementoFilial}"/>

                                                        </div>

                                                    </div>


                                                    <label>
                                                        &nbsp;<br />
                                                        &nbsp;<br />
                                                        &nbsp;<br />


                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <input name="ID_FILIAL" style="display: none" value="${ID_FILIAL}"/>
                                </div>

                                <div class="row">
                                    <div class="col-lg-12">
                                        <a href="FilialServlet"  class="btn btn-sm btn-danger right" style="float: right;">
                                            Cancelar &nbsp;<i class="ace-icon fa fa-close"></i>
                                        </a>
                                        <c:choose>
                                            <c:when test= "${empty tarefa}">
                                                <button type="submit" class="btn btn-sm btn-success" style="float: right; margin-right: 2px;" name="tarefa" value="Cadastro">
                                                    Registrar &nbsp; <i class="ace-icon fa fa-save"></i>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" class="btn btn-sm btn-success" style="float: right; margin-right: 2px;" name="tarefa" value="${tarefa}">
                                                        Atualizar &nbsp; <i class="ace-icon fa fa-save"></i>
                                                    </c:otherwise>
                                                </c:choose>


                                            </button>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <!-- /.ace-settings-container -->
                        <div class="footer">
                            <div class="footer-inner">
                                <div class="footer-content">
                                    <span class="bigger-120">
                                        <span class="blue bolder">ThinkCode
                                        </span>

                                        &nbsp; &nbsp;
                                        <span class="action-buttons">
                                            <a href="#">
                                                <i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
                                            </a>

                                            <a href="#">
                                                <i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
                                            </a>

                                            <a href="#">
                                                <i class="ace-icon fa fa-rss-square orange bigger-150"></i>
                                            </a>
                                        </span>
                                </div>
                            </div>
                        </div>

                        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                            <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
                        </a>
                    </div>
                    <!-- /.main-container -->

                    <!-- basic scripts -->

                    <!--[if !IE]> -->
                    <script src="assets/js/jquery-2.1.4.min.js"></script>

                    <!-- <![endif]-->

                    <!--[if IE]>
    <script src="assets/js/jquery-1.11.3.min.js"></script>
    <![endif]-->
                    <script type="text/javascript">
                    if ('ontouchstart' in document.documentElement)
                        document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
                    </script>
                    <script src="assets/js/bootstrap.min.js"></script>

                    <!-- page specific plugin scripts -->

                    <!--[if lte IE 8]>
                      <script src="assets/js/excanvas.min.js"></script>
                    <![endif]-->
                    <script src="assets/js/jquery-ui.custom.min.js"></script>
                    <script src="assets/js/jquery.ui.touch-punch.min.js"></script>
                    <script src="assets/js/jquery.easypiechart.min.js"></script>
                    <script src="assets/js/jquery.sparkline.index.min.js"></script>
                    <script src="assets/js/jquery.flot.min.js"></script>
                    <script src="assets/js/jquery.flot.pie.min.js"></script>
                    <script src="assets/js/jquery.flot.resize.min.js"></script>
                    <script src="assets/js/jquery.maskedinput.min.js"></script>
                    <script src="assets/js/bootstrap-datepicker.min.js"></script>
                    <!-- ace scripts -->
                    <script src="assets/js/ace-elements.min.js"></script>
                    <script src="assets/js/ace.min.js"></script>


                    <!-- inline scripts related to this page -->
                    <script type="text/javascript">
                    jQuery(function ($) {

                        $('#idSair').click(function () {
                            $('#btnSair').click()
                        });
                        $.mask.definitions['~'] = '[+-]';
                        $('.input-mask-phone').mask('(99) 99999-9999');
                        $('.input-mask-cnpj').mask('99.999.999/9999-99');
                        $('.input-mask-rg').mask('99-999-999-9');
                        $('.input-mask-cep').mask('99999-999')
                        $('.date-picker').datepicker({
                            autoclose: true,
                            todayHighlight: true
                        })


                        <%
                            Cookie[] cookies = request.getCookies();
                            for (Cookie atual : cookies) {
                                if (atual.getName().equals("Perfil")) {
                                    int auxilio = Integer.parseInt(atual.getValue());
                                    if (auxilio != 1) {
                        %>
                        $('#liCadastro').hide()
                        <%
                                }
                            }
                            if (atual.getName().equals("Nome")) {
                                String auxiliado = atual.getValue().substring(0, 8);
                        %>
                        $('#lblNome').text('<%= auxiliado%>');
                        <%
                                }

                            }
                        %>
                    })
                    </script>   
                    </body>

                    </html>

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
        <meta charset="utf-8" />
        <title>Dashboard</title>

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
        <style>
        </style>
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
            SQLstate = "SELECT MU.* FROM tb_menu AS MU 	INNER JOIN tb_permissao AS PE ON MU.ID_MENU = PE.ID_MENU	WHERE MU.DATA_EXCLUSAO IS NULL AND PE.DATA_EXCLUSAO IS NULL AND PE.ID_PERFIL = "+ id_perfil +" ORDER BY MU.PAGINA;";
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
                            <img src="assets/images/gallery/reparar.png" width="12%;">
                            <!-- <i class="fa fa-leaf"></i> -->
                            Portal Tades
                        </small>
                    </a>
                </div>

                <div class="navbar-buttons navbar-header pull-right" role="navigation">
                    <ul class="nav ace-nav">
                        <li class="light-blue dropdown-modal">
                            <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                <img class="nav-user-photo" src="assets/images/avatars/user.jpg" alt="Jason's Photo" />
                                <span class="user-info">
                                    <small>Bem-Vindo,</small>
                                    <%
                                        Cookie[] cookies = request.getCookies();

                                        for (Cookie cookie : cookies) {
                                            if (cookie.getName().equals("Nome")) {
                                                out.println(cookie.getValue());
                                            }
                                        }

                                    %>
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
            </div><!-- /.navbar-container -->
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

                <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                    <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
                       data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>
            </div>

            <div class="main-content">
                <div class="col-lg-12">
                    <p>
                    <h4 style="color: rgb(118, 168, 88)"><i class="fa fa-files-o" aria-hidden="true"></i> Registro de Ordens de Serviço</h4>
                    <hr style="border:0.5px solid rgb(194, 194, 194)">
                    </p>
                </div>
                <div class="main-content-inner">

                    <div class="page-content">

                        <div class="widget-box">
                            <div class="widget-header widget-header-blue widget-header-flat">
                                <h4 class="widget-title lighter">Nova Ordem</h4>
                            </div>

                            <div class="widget-body">
                                <div class="widget-main">
                                    <div id="fuelux-wizard-container">
                                        <div>
                                            <ul class="steps">
                                                <li data-step="1" class="active">
                                                    <span class="step">1</span>
                                                    <span class="title">Dados Cliente</span>
                                                </li>

                                                <li data-step="2">
                                                    <span class="step">2</span>
                                                    <span class="title">Dados do produto</span>
                                                </li>

                                                <li data-step="3">
                                                    <span class="step">3</span>
                                                    <span class="title">Informações do Serviço</span>
                                                </li>

                                                <li data-step="4">
                                                    <span class="step">4</span>
                                                    <span class="title">Confirmação</span>
                                                </li>
                                            </ul>
                                        </div>

                                        <hr />

                                        <div class="step-content pos-rel">
                                            <div class="step-pane active" data-step="1">


                                                <form class="form-horizontal" id="formularioCadastro">
                                                    <div class="widget-body">
                                                        <div class="widget-main">
                                                            <div class="form-group">


                                                                <div class="col-lg-12" id="buscaCliente">
                                                                    <div class="row col-lg-3">
                                                                        <br><label for="number-button" class="block"> Buscar CPF </label>
                                                                        <div class="input-group">

                                                                            <input type="text"
                                                                                   class="form-control input-mask-cpf"
                                                                                   name="keywords" id="buscaCPF" placeholder="CPF" />
                                                                            <div class="input-group-btn">
                                                                                <button type="button" id="procurarCliente"
                                                                                        class="btn btn-default no-border btn-sm">
                                                                                    <i
                                                                                        class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                                                                </button>
                                                                            </div>

                                                                        </div>

                                                                    </div><br><br><br><br><br><br>  
                                                                </div>

                                                                <div class="row col-lg-12 infosClientes" 
                                                                     style="display: none;">

                                                                </div>
                                                                <div class="row col-lg-12" style="margin-top: 1%;">
                                                                    <div class="col-lg-4">
                                                                        <label for="form-field-select-3">Marca do carro</label>

                                                                        <br />
                                                                        <select class="chosen-select form-control" id="marcaCarro">

                                                                        </select>
                                                                    </div>
                                                                    <div class="col-lg-4">
                                                                        <label for="form-field-select-3">Modelo do carro</label>

                                                                        <br />
                                                                        <select class="chosen-select form-control" id="modeloCarros">

                                                                        </select>
                                                                    </div>
                                                                    <div class="col-lg-4">
                                                                        <label for="form-field-select-3">Placa do Carro</label>

                                                                        <br />
                                                                        <input type="text" id="placa" placeholder="Placa" placeholder=\"000.00-000\"
                                                                               class="form-control"
                                                                               name="cepCad" />
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <label>
                                                                &nbsp;<br />


                                                            </label>
                                                        </div>
                                                    </div>

                                                </form>

                                            </div>

                                            <div class="step-pane" data-step="2">
                                                <div class="row col-lg-12">
                                                    <div class="row col-lg-3">

                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="keywords"
                                                                   placeholder="Filtro" id="nome_produto" />
                                                            <div class="input-group-btn">
                                                                <button type="button"
                                                                        class="btn btn-default no-border btn-sm" id="filtroProduto">
                                                                    <i
                                                                        class="ace-icon fa fa-search icon-on-right bigger-110"></i>
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </div><br><br><br>
                                                </div>
                                                <div class="row col-lg-12">
                                                    <div>
                                                        <span id="mostrarProdutos"></span>
                                                    </div>
                                                </div>
                                                <div class="row col-lg-12">
                                                    <table id="tabelaProduto" class="table table-hover display  table-striped table-bordered nowrap" style="width: 100%">
                                                        <thead>
                                                            <tr>
                                                                <th id="id"> Nr. Produto</th>
                                                                <th> Nome Produto </th>
                                                                <th> Descrição </th>
                                                                <th> Valor Produto </th>
                                                                <th> Qtd. </th>
                                                                <th> Total </th>
                                                                <th> Gerenciar</th>
                                                            </tr>
                                                        </thead>

                                                        <tbody>
                                                        </tbody>
                                                    </table>
                                                </div>

                                            </div>

                                            <div class="step-pane" data-step="3">
                                                <div class=" col-lg-12">
                                                    <div class="row">
                                                        <label for="form-field-select-3">Descreva o serviço</label>

                                                        <br />
                                                        <div class="col-lg-12">
                                                            <textarea class="form-control" id="descServico"></textarea>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <label for="number-button" class="block">Valor</label>
                                                            <div class="input-group">
                                                                <span class="input-group-addon">
                                                                    <i class="ace-icon fa fa-money"></i>
                                                                </span>

                                                                <input class="form-control money"
                                                                       type="text"
                                                                       placeholder="R$00,00" name="valorProduto" 
                                                                       required id="valServico"/>
                                                            </div>
                                                        </div>
                                                        <div class="col-lg-12">
                                                            <br>
                                                            <button type="submit" class="btn btn-sm btn-success"
                                                                    style="float: right; margin-right: 2px;" id="addRow"/>
                                                            <b>Adicionar &nbsp; <i class="ace-icon fa fa-plus"></i></b>
                                                            </button>
                                                        </div>
                                                    </div>
                                                    <div class="row col-lg-12">
                                                        <table id="tabelaServico" class="table table-hover display  table-striped table-bordered nowrap" style="width: 100%">
                                                            <thead>
                                                                <tr>
                                                                    <th id="id"> Nr. Serviço</th>
                                                                    <th> Descrição </th>
                                                                    <th> Valor </th>
                                                                    <th> Gerenciar</th>
                                                                </tr>
                                                            </thead>

                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>



                                            </div>

                                            <div class="step-pane" data-step="4">
                                                <div class="row col-lg-12 infosClientes" 
                                                     style="display: none;">

                                                </div>
                                                <div class="row col-lg-12 infoCarro">
                                                    <div class="col-lg-3">
                                                        <label for="number-button" class="block">Marca do Carro</label>
                                                        <input type="text" id="MarcaCarro"
                                                               placeholder="Marca"  class="form-control"
                                                               name="MarcaCarro" disabled/>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label for="number-button" class="block">Modelo do Carro</label>
                                                        <input type="text" id="ModeloCarro"
                                                               placeholder="Marca"  class="form-control"
                                                               name="ModeloCarro" disabled/>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label for="number-button" class="block">Placa do carro</label>
                                                        <input type="text" id="PlacaCarro"
                                                               placeholder="Marca"  class="form-control"
                                                               name="PlacaCarro" disabled/>
                                                    </div>
                                                </div>
                                                <div class="row col-lg-12">
                                                    <div class="col-lg-12">
                                                        <label for="form-field-select-2">Produtos selecionados</label>


                                                        <table id="tabelaProdutoFinais" class="table table-hover display  table-striped table-bordered nowrap" style="width: 100%">
                                                            <thead>
                                                                <tr>
                                                                    <th id="id"> Nr. Produto</th>
                                                                    <th> Nome Produto </th>
                                                                    <th> Descrição </th>
                                                                    <th> Valor Produto </th>
                                                                    <th> Qtd. </th>
                                                                    <th> Total </th>
                                                                </tr>
                                                            </thead>

                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-12">
                                                        <label for="form-field-select-2">Serviços efetudos</label>


                                                        <table id="tabelaServicoFinal" class="table table-hover display  table-striped table-bordered nowrap" style="width: 100%">
                                                            <thead>
                                                                <tr>
                                                                    <th id="id"> Nr. Serviço</th>
                                                                    <th> Descrição </th>
                                                                    <th> Valor </th>
                                                                </tr>
                                                            </thead>

                                                            <tbody>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label for="number-button" class="block">Metodo de pagamento</label>
                                                        <select class="chosen-select form-control" name="metodoPagamento" id="metodoPagamento">
                                                            <option value="1">Cartão de Crédito</option>
                                                            <option value="2">Cartão de Débito</option>
                                                            <option value="3">Dinheiro</option>
                                                            <option value="4">Boleto</option>
                                                        </select>

                                                    </div>
                                                    <div class="col-lg-3">
                                                        <label for="number-button" class="block">Valor total</label>
                                                        <input type="text" id="totalFinal" disabled
                                                               class="form-control" name="totalFinal" />

                                                    </div>
                                                </div>



                                            </div>
                                        </div>
                                    </div>

                                    <hr />
                                    <div class="wizard-actions">
                                        <button class="btn btn-white btn-info btn-bold btn-prev" id="btnVoltar">
                                            <i class="ace-icon fa fa-arrow-left"></i>
                                            Voltar
                                        </button>


                                        <button type="submit" class="btn btn-white btn-info btn-bold btn-next" data-last="Finalizar"
                                                id="btnProximo"> Prosseguir <i class="ace-icon fa fa-arrow-right" disabled="false"></i></button>

                                        &nbsp;</br>
                                        &nbsp;</br>
                                        &nbsp;</br>
                                        &nbsp;</br>
                                        &nbsp;</br>
                                    </div>
                                </div><!-- /.widget-main -->
                            </div><!-- /.widget-body -->
                        </div>

                        <div id="modal-wizard" class="modal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div id="modal-wizard-container">
                                        <div class="modal-header">
                                            <ul class="steps">
                                                <li data-step="1" class="active">
                                                    <span class="step">1</span>
                                                    <span class="title">Validation states</span>
                                                </li>

                                                <li data-step="2">
                                                    <span class="step">2</span>
                                                    <span class="title">Alerts</span> 
                                                </li>

                                                <li data-step="3">
                                                    <span class="step">3</span>
                                                    <span class="title">Payment Info</span>
                                                </li>

                                                <li data-step="4">
                                                    <span class="step">4</span>
                                                    <span class="title">Other Info</span>
                                                </li>
                                            </ul>
                                        </div>

                                        <div class="modal-body step-content">
                                            <div class="step-pane active" data-step="1">
                                                <div class="center">
                                                    <h4 class="blue">Step 1</h4>
                                                </div>
                                            </div>

                                            <div class="step-pane" data-step="2">
                                                <div class="center">
                                                    <h4 class="blue">Step 2</h4>
                                                </div>
                                            </div>

                                            <div class="step-pane" data-step="3">
                                                <div class="center">
                                                    <h4 class="blue">Step 3</h4>
                                                </div>
                                            </div>

                                            <div class="step-pane" data-step="4">
                                                <div class="center">
                                                    <h4 class="blue">Step 4</h4>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="modal-footer wizard-actions">
                                        <button class="btn btn-sm btn-prev">
                                            <i class="ace-icon fa fa-arrow-left"></i>
                                            Prev
                                        </button>

                                        <button class="btn btn-success btn-sm btn-next" data-last="Finish">
                                            Next
                                            <i class="ace-icon fa fa-arrow-right icon-on-right"></i>
                                        </button>

                                        <button class="btn btn-danger btn-sm pull-left" data-dismiss="modal">
                                            <i class="ace-icon fa fa-times"></i>
                                            Cancel
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div><!-- PAGE CONTENT ENDS -->
                    </div><!-- /.widget-body -->
                </div>
                <!-- /.ace-settings-container -->
                <div id="my-modal" class="modal fade in" tabindex="-1" style="display: block; display: none;">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                                <h3 class="smaller lighter blue no-margin">Nota Fiscal</h3>
                            </div>

                            <div class="modal-body">
                                <p>Nota Gerada com Sucesso</p>
                                <p>Deseja imprimir?</p>
                                <button type="button" class="btn btn-sm btn-info" id="btnImprimir">Imprimir</button> <button type="button" class="btn btn-sm btn-success" id="VsOrdens">Visualizar Ordens</button>
                            </div>
                            <div class="modal-footer">
                                <button class="btn btn-sm btn-danger pull-right" data-dismiss="modal">
                                    <i class="ace-icon fa fa-times"></i>
                                    Fechar
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div>
                <div class="footer">
                    <div class="footer-inner" >
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

            </div><!-- /.main-container -->


            <!-- basic scripts -->

            <!--[if !IE]> -->
            <script src="assets/js/jquery-2.1.4.min.js"></script>

            <!-- <![endif]-->

            <!--[if IE]>
        <script src="assets/js/jquery-1.11.3.min.js"></script>
        <![endif]-->
            <script type="text/javascript">
                    if ('ontouchstart' in document.documentElement)
                        document.write(
                                "<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");</script>
            <script src="assets/js/bootstrap.min.js"></script>



            <!-- page specific plugin scripts -->
            <script src="assets/js/wizard.min.js"></script>
            <script src="assets/js/jquery.validate.min.js"></script>
            <script src="assets/js/jquery-additional-methods.min.js"></script>
            <script src="assets/js/bootbox.js"></script>
            <script src="assets/js/jquery.maskedinput.min.js"></script>
            <script src="assets/js/select2.min.js"></script>
            <script src="assets/js/jquery.bootstrap-duallistbox.min.js"></script>

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

            <script src="assets/js/jquery.mask.js"></script>
            <!-- ace scripts -->
            <script src="assets/js/ace-elements.min.js"></script>
            <script src="assets/js/ace.min.js"></script>
            <script src="assets/js/spinbox.min.js"></script>


            <script type="text/javascript" src="DataTables/datatables.min.js"></script>
            <script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
            <script type="text/javascript" src="https://cdn.datatables.net/rowreorder/1.2.7/js/dataTables.rowReorder.min.js"></script>
            <script type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.4/js/dataTables.responsive.min.js"></script>


            <!-- inline scripts related to this page -->
            <script type="text/javascript">
                    localStorage.clear();
                    var posicaoCarrinho = 1;
                    var pagAtual = 0;
                    var totalOrdem = parseFloat(0);
                    jQuery(function ($) {
                        $(document).ready(function () {
                            $('.money').mask('000.000.000.000.000,00', {reverse: true});
                        });
                        var counter = 1;
                        $('#addRow').click(function () {
                            var t = $('#tabelaServico').DataTable();
                            var descServico = $('#descServico').val();
                            var valServico = $('#valServico').val();
                            var htmlButton = "<div class='btn-group'> <button class='btn btn-xs btn-danger'  onclick='window.deleteServico(" + counter + ")'> <i class='ace-icon fa fa-trash-o bigger-120'></i> </button> </div>"
                            t.row.add([
                                counter,
                                descServico,
                                valServico,
                                htmlButton
                            ]).draw(false);
                            var t = $('#tabelaServicoFinal').DataTable();
                            var descServico = $('#descServico').val();
                            var valServico = $('#valServico').val();
                            t.row.add([
                                counter,
                                descServico,
                                valServico
                            ]).draw(false);
                            counter++;
                            totalOrdem = totalOrdem + parseFloat(valServico);
                            let
                                    cell3 = totalOrdem;
                            cell3 = cell3.toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
                            $('#totalFinal').val(cell3);
                        });
                        $('#idSair').click(function () {
                            $('#btnSair').click()
                        });
                        $('#VsOrdens').click(function () {
                            window.location.href = "OrdemServicoServlet?tarefa=Relatorio";
                        });
                        $('#btnImprimir').click(function () {
                            window.open("OrdemServicoServlet?tarefa=NF&id=" + $('#btnImprimir').val(), '_blank');
                        });

                        window.deleteProduto = function (user)
                        {
                            var linerow = 0;
                            var acho = false;
                            $('#tabelaProduto > tbody  > tr').each(function (indexlinha, tr) {
                                $(this).find('td').each(function (index) {
                                    if (index == 0) {
                                        if (user == $(this).text()) {
                                            linerow = indexlinha;
                                            acho = true;
                                        }

                                    }
                                    if (index == 5) {
                                        if (acho) {
                                            acho = false;
                                            var subtrai = parseFloat($(this).text().replace("R$", "").replace(",", ".").replace(" ", ""));
                                            totalOrdem = totalOrdem - subtrai;
                                            let cell3 = totalOrdem;
                                            cell3 = cell3.toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
                                            $('#totalFinal').val(cell3);
                                        }
                                    }
                                });
                            });

                            var oTable = $('#tabelaProduto').dataTable();
                            linerow = linerow + 1;
                            var row = oTable.find('tr').eq(linerow);
                            oTable.fnDeleteRow(row[0]);


                            var linerowFinal = 0;
                            $('#tabelaProdutoFinais > tbody  > tr').each(function (indexlinha, tr) {
                                $(this).find('td').each(function (index) {
                                    if (index == 0) {
                                        if (user == $(this).text()) {
                                            linerowFinal = indexlinha;
                                        }
                                    }
                                });
                            });

                            var oTables = $('#tabelaProdutoFinais').dataTable();
                            linerowFinal = linerowFinal + 1;
                            var row2 = oTables.find('tr').eq(linerow);
                            oTables.fnDeleteRow(row2[0]);

                        }

                        window.deleteServico = function (user)
                        {
                            var linerow = 0;
                            var acho = false;
                            $('#tabelaServico > tbody  > tr').each(function (indexlinha, tr) {
                                $(this).find('td').each(function (index) {
                                    if (index == 0) {
                                        if (user == $(this).text()) {
                                            linerow = indexlinha;
                                            acho = true;
                                        }
                                    }
                                    if (index == 2) {
                                        if (acho) {
                                            acho = false;
                                            var subtrai = parseFloat($(this).text().replace("R$", "").replace(",", ".").replace(" ", ""));
                                            totalOrdem = totalOrdem - subtrai;
                                            let cell3 = totalOrdem;
                                            cell3 = cell3.toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
                                            $('#totalFinal').val(cell3);
                                        }
                                    }
                                });
                            });

                            var oTable = $('#tabelaServico').dataTable();
                            linerow = linerow + 1;
                            var row = oTable.find('tr').eq(linerow);
                            oTable.fnDeleteRow(row[0]);



                            var linerowFinal = 0;
                            $('#tabelaServicoFinal > tbody  > tr').each(function (indexlinha, tr) {
                                $(this).find('td').each(function (index) {
                                    if (index == 0) {
                                        if (user == $(this).text()) {
                                            linerowFinal = indexlinha;
                                        }
                                    }
                                });
                            });

                            var oTables = $('#tabelaServicoFinal').dataTable();
                            linerowFinal = linerowFinal + 1;
                            var row2 = oTables.find('tr').eq(linerow);
                            oTables.fnDeleteRow(row2[0]);

                        }

                        var Etapa = 0;
                        $('#btnProximo').click(function () {
                            Etapa++;
                            if (Etapa === 4) {
                                var desc_servico = "";
                                var valor_servico = "";
                                $('#tabelaServico > tbody  > tr').each(function (tr) {
                                    $(this).find('td').each(function (index) {
                                        if (index == 1) {
                                            desc_servico += $(this).text() + ";"
                                        }
                                        if (index == 2) {
                                            valor_servico += $(this).text() + ";"
                                        }
                                    });
                                });
                                var id_produto = "";
                                var valor_produto = "";
                                var qtd_produto = "";
                                var total_produto = "";

                                $('#tabelaProduto > tbody  > tr').each(function (tr) {
                                    $(this).find('td').each(function (index) {
                                        if (index == 0) {
                                            id_produto += $(this).text() + ";"
                                        }
                                        if (index == 3) {
                                            valor_produto += $(this).text() + ";"
                                        }
                                        if (index == 4) {
                                            qtd_produto += $(this).text() + ";"
                                        }
                                        if (index == 5) {
                                            total_produto += $(this).text() + ";"
                                        }
                                    });
                                });


                                Etapa = 0;

                                var idCliente = $('#idCliente').val();
                                var marcaCarro = $('#MarcaCarro').val();
                                var modelCarro = $('#ModeloCarro').val();
                                var placaCarro = $('#PlacaCarro').val();
                                var valorOrdem = $('#totalFinal').val();
                                var pagamento = $('#metodoPagamento').val();



                                $.ajax({
                                    url: 'OrdemServicoServlet',
                                    type: 'POST',
                                    data: {
                                        tarefa: 'Salvar',
                                        idCliente: idCliente,
                                        marcaCarro: marcaCarro,
                                        modelCarro: modelCarro,
                                        placaCarro: placaCarro,
                                        valorOrdem: valorOrdem,
                                        pagamento: pagamento,
                                        desc_servico: desc_servico,
                                        valor_servico: valor_servico,
                                        id_produto: id_produto,
                                        valor_produto: valor_produto,
                                        qtd_produto: qtd_produto,
                                        total_produto: total_produto
                                    },
                                    success: function (result) {
                                        if (result == "Erro ao gerar ordem de serviço") {
                                            alert(result)
                                        } else {
                                            $('#my-modal').modal()
                                            $('#btnImprimir').val(result)
                                        }
                                    }
                                });
                            }

                        })

                        $('#btnVoltar').click(function () {
                            if (Etapa >= 1) {
                                Etapa = Etapa - 1;
                            }
                            //$('#btnProximo').attr("disabled", false);
                        })
                        $.mask.definitions['~'] = '[+-]';
                        $('.input-mask-cpf').mask('999-999-999-99');
                        $('#tabelaServico').DataTable({
                            "language": {
                                "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json"
                            },
                            responsive: true,
                            "pageLength": 25
                        });
                        $('#tabelaProduto').DataTable({
                            "language": {
                                "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json"
                            },
                            responsive: true,
                            "pageLength": 25
                        });
                        $('#tabelaProdutoFinais').DataTable({
                            "language": {
                                "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json"
                            },
                            responsive: true,
                            "pageLength": 25
                        });
                        $('#tabelaServicoFinal').DataTable({
                            "language": {
                                "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json"
                            },
                            responsive: true,
                            "pageLength": 25
                        });
                        $('#procurarCliente').click(function () {
                            var cpf = $('#buscaCPF').val();
                            $.ajax({
                                url: 'OrdemServicoServlet',
                                type: 'POST',
                                data: {
                                    tarefa: 'recuperaCliente',
                                    buscarCPF: cpf

                                },
                                success: function (result) {
                                    if (result == "erroPerfil") {
                                        alert("Perfil de usuário não condiz com a ação solicitada!                              -----[Perfil não é comprador]-----")
                                    } else {
                                        $('.infosClientes').html(result);
                                        $.mask.definitions['~'] = '[+-]';
                                        $('.input-mask-phone').mask('(99) 99999-9999');
                                        $('.input-mask-cpf').mask('999-999-999-99');
                                        $('.input-mask-rg').mask('99-999-999-9');
                                        $('.input-mask-cep').mask('99999-999');
                                        $('.date-picker').datepicker({
                                            autoclose: true,
                                            todayHighlight: true
                                        })
                                    }
                                }
                            });
                            $(".infosClientes").css("display", "block");
                            $.getJSON('http://fipeapi.appspot.com/api/1/carros/marcas.json', function (data) {
                                var option = "<option></option>";
                                $.each(data, function (key, val) {
                                    option += "<option value='" + val.id + "'>" + val.name + "</option>"
                                });
                                $('#marcaCarro').html(option)

                            });
                        })

                        $('#marcaCarro').change(function () {
                            $('#MarcaCarro').val($('#marcaCarro option:selected').text())
                            var stringModelos = "http://fipeapi.appspot.com/api/1/carros/veiculos/" + $('#marcaCarro').val() + ".json";
                            $.getJSON(stringModelos, function (data) {
                                var option = "<option></option>";
                                $.each(data, function (key, val) {
                                    option += "<option value='" + val.id + "'>" + val.name + "</option>"
                                });
                                $('#modeloCarros').html(option)

                            });
                        })

                        $('#modeloCarros').change(function () {
                            $('#ModeloCarro').val($('#modeloCarros option:selected').text())
                        });
                        $('#placa').keyup(function () {
                            $('#PlacaCarro').val(this.value)
                        });
                        $('#filtroProduto').click(function () {
                            var produtoNome = $('#nome_produto').val();
                            $.ajax({
                                type: 'POST',
                                data: {
                                    nomeProduto: produtoNome,
                                    tarefa: 'BuscaProduto'

                                },
                                url: 'OrdemServicoServlet',
                                success: function (result) {
                                    $('#mostrarProdutos').html(result);
                                    let
                                            cells = Array.prototype.slice.call(document.querySelectorAll(".row_currency"));
                                    // Loop over the array
                                    cells.forEach(function (cell) {
                                        // Convert cell data to a number, call .toLocaleString()
                                        // on that number and put result back into the cell
                                        cell.textContent = (+cell.textContent).toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
                                    });
                                }
                            });
                        })



                        $('#selectPagamento').change(function () {
                            var value = $('#selectPagamento').val()
                            if (value === '3') {
                                $('#infoDinheiro').show();
                            } else {
                                $('#infoDinheiro').hide();
                            }
                        })

                        var demo1 = $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox({
                            infoTextFiltered: '<span class="label label-purple label-lg">Filtered</span>'
                        });
                        var container1 = demo1.bootstrapDualListbox('getContainer');
                        $('[data-rel=tooltip]').tooltip();
                        $('.select2').css('width', '200px').select2({
                            allowClear: true
                        })
                                .on('change', function () {
                                    $(this).closest('form').validate().element($(this));
                                });
                        var $validation = false;
                        $('#fuelux-wizard-container')
                                .ace_wizard({
                                    //step: 2 //optional argument. wizard will jump to step "2" at first
                                    //buttons: '.wizard-actions:eq(0)'
                                })
                                .on('actionclicked.fu.wizard', function (e, info) {
                                    if (info.step == 1 && $validation) {
                                        if (!$('#validation-form').valid())
                                            e.preventDefault();
                                    }
                                })
                                //.on('changed.fu.wizard', function() {
                                //})
                                .on('finished.fu.wizard', function (e) {

                                }).on('stepclick.fu.wizard', function (e) {
                        });
                        $('#skip-validation').removeAttr('checked').on('click', function () {
                            $validation = this.checked;
                            if (this.checked) {
                                $('#sample-form').hide();
                                $('#validation-form').removeClass('hide');
                            } else {
                                $('#validation-form').addClass('hide');
                                $('#sample-form').show();
                            }
                        })

                        //documentation : http://docs.jquery.com/Plugins/Validation/validate




                        jQuery.validator.addMethod("phone", function (value, element) {
                            return this.optional(element) || /^\(\d{3}\) \d{3}\-\d{4}( x\d{1,6})?$/.test(value);
                        }, "Enter a valid phone number.");
                        $('#modal-wizard-container').ace_wizard();
                        $('#modal-wizard .wizard-actions .btn[data-dismiss=modal]').removeAttr('disabled');
                        $(document).one('ajaxloadstart.page', function (e) {
                            //in ajax mode, remove remaining elements before leaving page
                            $('[class*=select2]').remove();
                            $('[class*=select2]').remove();
                            $('select[name="duallistbox_demo1[]"]').bootstrapDualListbox('destroy');
                            $('.rating').raty('destroy');
                            $('.multiselect').multiselect('destroy');
                        })


                        $('#id-file-format').removeAttr('checked').on('change', function () {
                            var whitelist_ext, whitelist_mime;
                            var btn_choose
                            var no_icon
                            if (this.checked) {
                                btn_choose = "Drop images here or click to choose";
                                no_icon = "ace-icon fa fa-picture-o";
                                whitelist_ext = ["jpeg", "jpg", "png", "gif", "bmp"];
                                whitelist_mime = ["image/jpg", "image/jpeg", "image/png", "image/gif",
                                    "image/bmp"
                                ];
                            } else {
                                btn_choose = "Drop files here or click to choose";
                                no_icon = "ace-icon fa fa-cloud-upload";
                                whitelist_ext = null; //all extensions are acceptable
                                whitelist_mime = null; //all mimes are acceptable
                            }
                            var file_input = $('#id-input-file-3');
                            file_input
                                    .ace_file_input('update_settings', {
                                        'btn_choose': btn_choose,
                                        'no_icon': no_icon,
                                        'allowExt': whitelist_ext,
                                        'allowMime': whitelist_mime
                                    })
                            file_input.ace_file_input('reset_input');
                            file_input
                                    .off('file.error.ace')
                                    .on('file.error.ace', function (e, info) {

                                    });
                        });
                        $('.spinner1').ace_spinner({
                            value: 0,
                            min: 0,
                            max: 200,
                            step: 1,
                            btn_up_class: 'btn-info',
                            btn_down_class: 'btn-info'
                        })
                                .closest('.ace-spinner')
                                .on('changed.fu.spinbox', function () {
                                });
                        var id_produto_tabela = 0;
                        window.displaymessage = function adicionarCarrinho(nome, id, valor)
                        {
                            var quantProd = document.getElementById("quantia" + id).value;
                            var quantCompr = document.getElementById("quantiaCompra" + id).value;
                            var resultCompr = quantProd - quantCompr;
                            if (quantCompr == 0) {
                                document.getElementById("quantiaCompra" + id).value = "";
                            } else if ((resultCompr < 0) || (quantProd == 0)) {
                                alert("Quantidade em estoque: " + quantProd + ". Favor incluir valor menor ou igual ao dísponivel! ")
                                document.getElementById("quantiaCompra" + id).value = "";
                            } else {
                                totalOrdem = totalOrdem + parseFloat(valor * document.getElementById("quantiaCompra" + id).value);
                                let cell3 = totalOrdem;
                                cell3 = cell3.toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
                                $('#totalFinal').val(cell3);
                                var valor_produto = valor;
                                var convertInt = parseFloat(valor_produto);
                                let
                                        cell2 = convertInt;
                                cell2 = cell2.toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
                                valor = valor * document.getElementById("quantiaCompra" + id).value;
                                var convertInt = parseFloat(valor);
                                let
                                        cell = convertInt;
                                cell = cell.toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
                                var t = $('#tabelaProduto').DataTable();
                                var htmlButton = "<div class='btn-group'> <button class='btn btn-xs btn-danger'  onclick='window.deleteProduto(" + id + ")'> <i class='ace-icon fa fa-trash-o bigger-120'></i> </button> </div>"
                                t.row.add([
                                    id,
                                    nome,
                                    "Teste",
                                    cell2,
                                    document.getElementById("quantiaCompra" + id).value,
                                    cell,
                                    htmlButton
                                ]).draw(false);
                                var t = $('#tabelaProdutoFinais').DataTable();
                                t.row.add([
                                    id,
                                    nome,
                                    "Teste",
                                    cell2,
                                    document.getElementById("quantiaCompra" + id).value,
                                    cell
                                ]).draw(false);
                                /*localStorage.setItem("salvar" + posicaoCarrinho, id);
                                 localStorage.setItem("produto" + posicaoCarrinho, nome);
                                 localStorage.setItem("qtd" + posicaoCarrinho, document.getElementById("quantiaCompra" + id).value);
                                 
                                 localStorage.setItem("valor" + posicaoCarrinho, valor);
                                 // exibe os dados da lista dentro da div itens
                                 document.getElementById("itens").innerHTML += "(x" + localStorage.getItem("qtd" + posicaoCarrinho) + ") ";
                                 document.getElementById("itens").innerHTML += localStorage.getItem("produto" + posicaoCarrinho);
                                 document.getElementById("itens").innerHTML += " <br> TOTAL ";
                                 var convertInt = parseFloat(localStorage.getItem("valor" + posicaoCarrinho));
                                 let cell = convertInt;
                                 cell = cell.toLocaleString("pt-BR", {style: "currency", currency: "BRL"});
                                 document.getElementById("itens").innerHTML += cell + "<hr>";
                                 
                                 document.getElementById("itensFinal").innerHTML += localStorage.getItem("qtd" + posicaoCarrinho) + " x ";
                                 document.getElementById("itensFinal").innerHTML += localStorage.getItem("produto" + posicaoCarrinho);
                                 document.getElementById("itensFinal").innerHTML += " : TOTAL ";
                                 document.getElementById("itensFinal").innerHTML += "R$ " + localStorage.getItem("valor" + posicaoCarrinho) + "<hr>";
                                 // calcula o total dos recheios
                                 valor = parseFloat(localStorage.getItem("valor" + posicaoCarrinho)); // valor convertido com o parseFloat()
                                 //total = (total + valor); // arredonda para 2 casas decimais com o .toFixed(2)
                                 
                                 posicaoCarrinho += 1;
                                 document.getElementById("quantiaCompra" + id).placeholder = "Qtd disponível: " + resultCompr;
                                 var anteriorCarrinho = parseFloat(document.getElementById("totalCarrinho").value);
                                 var valorAtual = parseFloat(valor);
                                 var totalCarrinho = anteriorCarrinho + valorAtual;
                                 
                                 
                                 document.getElementById("totalCarrinho").value = totalCarrinho;
                                 document.getElementById("quantia" + id).value = resultCompr;
                                 document.getElementById("quantiaCompra" + id).value = "";*/


                            }
                        }
                    })




            </script>
    </body>

</html>
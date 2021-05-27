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
                    <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state"
                       data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
                </div>
            </div>

            <div class="main-content">
                <div class="main-content-inner">


                    <div class="page-content">
                        <!-- /.ace-settings-container -->
                        <!-- Filtros -->
                        <form action="ProdutoServlet" method="POST" id="formCadProduto">
                            <div>
                                <div>
                                    <div class="col-lg-12">
                                        <p>
                                        <h4>Cadastro de Produtos</h4>
                                        <hr>
                                        </p>
                                    </div>
                                    <div class="col-xs-12 col-sm-12">
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <div class="widget-box">
                                                    <div class="widget-header">
                                                        <h4 class="widget-title center">Informações Gerais</h4>

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
                                                                    <input type="text" id="nomeProduto"
                                                                           placeholder="Nome" class="form-control"
                                                                           name="nomeProduto" required value="${nomeProduto}" />
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">Tipo</label>
                                                                    <input type="text" id="Tipo"
                                                                           class="form-control" name="tipoProduto" required  value="${tipoProduto}"/>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button"
                                                                           class="block">Quantidade</label>
                                                                    <input type="number" onkeypress="return event.charCode >= 48" min="1" id="quantidadeProduto"
                                                                           class="form-control" name="quantidadeProduto" onkeypress="return onlynumber()"
                                                                           required value="${quantidadeProduto}"/>
                                                                </div>

                                                                <div class="col-lg-3">
                                                                    <label for="number-button"
                                                                           class="block">Descrição</label>
                                                                    <input type="text" id="descricaoProduto"
                                                                           class="form-control" name="descricaoProduto"
                                                                           required value="${descricaoProduto}"/>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">Valor</label>
                                                                    <div class="input-group">
                                                                        <span class="input-group-addon">
                                                                            <i class="ace-icon fa fa-money"></i>
                                                                        </span>

                                                                        <input class="form-control money"
                                                                               type="text" id="valorProduto"
                                                                               placeholder="R$00,00" name="valorProduto" onkeypress="return onlynumber()"
                                                                               required value="${valorProduto}"/>
                                                                    </div>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">Categoria</label>
                                                                    <select class="form-group" style="width: 100%;" name="categoriaProduto">
                                                                        <c:forEach var="categoria" items="${categorias}">
                                                                            <option value="${categoria.idCategoria}">
                                                                                ${categoria.nome}
                                                                            </option>
                                                                        </c:forEach>

                                                                    </select>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">Filial</label>
                                                                    <select class="form-group" style="width: 100%;" name="filialProduto">
                                                                        <c:forEach var="filial" items="${filiais}">
                                                                            <option value="${filial.idFilial}">
                                                                                ${filial.nome}
                                                                            </option>
                                                                        </c:forEach>

                                                                    </select>
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
                                            <div class="col-sm-12">
                                                <div class="widget-box">
                                                    <div class="widget-header">
                                                        <h4 class="widget-title center">Especificação</h4>

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
                                                                    <label for="number-button" class="block">Especificação</label>
                                                                    <input type="text" id="dsEspecificacao"
                                                                           placeholder="Nome" class="form-control"
                                                                           name="dsEspecificacao"  value="" />
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">Respostas</label>
                                                                    <input type="text" id="dsResposta"
                                                                           class="form-control" name="dsResposta"   value=""/>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">  &nbsp;<br /></label>
                                                                    <button type="button" id="btnAddEspecificacao" class="btn btn-success btn-sm">Adicionar</button>
                                                                </div>
                                                                <div class="col-lg-12">
                                                                    <table id="tblEspecificao"name="tblEspecificao"  class="table table-hover display  table-striped table-bordered nowrap" style="width: 100%">
                                                                        <thead>
                                                                            <tr>
                                                                                <th id="id"> ID Especificação </th>
                                                                                <th> Especificação </th>
                                                                                <th> Resposta </th>
                                                                                <th> Gerenciar </th>
                                                                            </tr>
                                                                        </thead>

                                                                        <tbody >
                                                                            <c:forEach var="especificacao" items="${especificacoes}">
                                                                                <tr>
                                                                                    <td>${especificacao.idEspecificacao}</td>
                                                                                    <td>${especificacao.especificacao}</td>
                                                                                    <td>${especificacao.resposta}</td>
                                                                                    <c:choose>
                                                                                        <c:when test= "${empty tarefa}">
                                                                                            <td><div class='btn-group'> <button class='btn btn-xs btn-danger' type='button'  onclick='window.deleteEspecificacao()'> <i class='ace-icon fa fa-trash-o bigger-120'></i> </button> </div></td>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <td><div class='btn-group'> <button class='btn btn-xs btn-danger' type='button'  onclick='window.deleteEspecificacaoTempoExecucaoEspecificao(${especificacao.idEspecificacao})'> <i class='ace-icon fa fa-trash-o bigger-120'></i> </button> </div></td>
                                                                                        </c:otherwise>
                                                                                    </c:choose>

                                                                                </tr>
                                                                            </c:forEach>
                                                                        </tbody>
                                                                    </table>
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
                                            <div class="col-sm-12">
                                                <div class="widget-box">
                                                    <div class="widget-header">
                                                        <h4 class="widget-title center">Perguntas frequentes</h4>

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
                                                                    <label for="number-button" class="block">Perguntas</label>
                                                                    <input type="text" id="dsPerguntasPerguntas"
                                                                           placeholder="Pergunta" class="form-control"
                                                                           name="dsPerguntasPerguntas"  value="" />
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">Respostas</label>
                                                                    <input type="text" id="dsRespostaPerguntas"
                                                                           class="form-control" name="dsRespostaPerguntas"   value=""/>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">Avaliação</label>
                                                                    <input type="number" id="dsAvaliacaoPerguntas" max="5" min="1"
                                                                           class="form-control" name="dsAvaliacaoPerguntas"   value="1"/>
                                                                </div>
                                                                <div class="col-lg-3">
                                                                    <label for="number-button" class="block">  &nbsp;<br /></label>
                                                                    <button type="button" id="btnAddPergunta" class="btn btn-success btn-sm">Adicionar</button>
                                                                </div>

                                                                <div class="col-lg-12">
                                                                    <table id="tblPergunta" class="table table-hover display  table-striped table-bordered nowrap" style="width: 100%">
                                                                        <thead>
                                                                            <tr>
                                                                                <th id="id"> ID Pergunta </th>
                                                                                <th> Pergunta </th>
                                                                                <th> Resposta </th>
                                                                                <th> Pontuação </th>
                                                                                <th>Gerenciar</th>
                                                                            </tr>
                                                                        </thead>

                                                                        <tbody>
                                                                            <c:forEach var="pergunta" items="${perguntas}">
                                                                                <tr>
                                                                                    <td>${pergunta.idPergunta}</td>
                                                                                    <td>${pergunta.pergunta}</td>
                                                                                    <td>${pergunta.resposta}</td>
                                                                                    <td>${pergunta.pontos}</td>
                                                                                    <c:choose>
                                                                                        <c:when test= "${empty tarefa}">
                                                                                            <td><div class='btn-group'> <button class='btn btn-xs btn-danger' type='button'  onclick='window.deletePergunta()'> <i class='ace-icon fa fa-trash-o bigger-120'></i> </button> </div></td>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <td><div class='btn-group'> <button class='btn btn-xs btn-danger' type='button'  onclick='window.deletePerguntaTempoExecucao(${pergunta.idPergunta})'> <i class='ace-icon fa fa-trash-o bigger-120'></i> </button> </div></td>
                                                                                        </c:otherwise>
                                                                                    </c:choose>

                                                                                </tr>
                                                                            </c:forEach>
                                                                        </tbody>
                                                                    </table>
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
                                        </div>
                                        <div class="col-sm-12">
                                            <div class="widget-box">
                                                <div class="widget-header">
                                                    <h4 class="widget-title center">Imagens</h4>

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
                                                                <label for="number-button" class="block">Imagens</label>
                                                                <input type="file" id="dsImage"
                                                                       placeholder="Nome" class="form-control"
                                                                       name="dsImage"  value=""/>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <label for="number-button" class="block">Descrição</label>
                                                                <input type="text" id="dsDescricao"
                                                                       class="form-control" name="dsDescricao"   value=""/>
                                                            </div>
                                                            <div class="col-lg-3">
                                                                <label for="number-button" class="block">  &nbsp;<br /></label>
                                                                <button type="button" id="btnAddImage" class="btn btn-success btn-sm">Adicionar</button>
                                                            </div>
                                                            <div class="col-lg-12">
                                                                <label>
                                                                    &nbsp;<br />
                                                                    &nbsp;<br />
                                                                    &nbsp;<br />


                                                                </label>
                                                                <div class="row" id="AreaDeImgs">

                                                                </div>
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
                                        <input name="ID_PRODUTO" style="display: none" value="${ID_PRODUTO}"/>
                                        <input name="Especificacoes" style="display: none" id="Especificacoes"/>
                                        <input name="Perguntas" style="display: none" id="Perguntas"/>
                                        <input name="EspecificacoesDeletar" style="display: none" id="EspecificacoesDeletar"/>
                                        <input name="PerguntasDeletar" style="display: none" id="PerguntasDeletar"/>
                                        <input name="InputImg1" style="display: none" id="InputImg1"/>
                                        <input name="InputImg2" style="display: none" id="InputImg2"/>
                                        <input name="InputImg3" style="display: none" id="InputImg3"/>
                                        <input name="InputImg4" style="display: none" id="InputImg4"/>
                                        <input name="DescImg1" style="display: none" id="DescImg1"/>
                                        <input name="DescImg2" style="display: none" id="DescImg2"/>
                                        <input name="DescImg3" style="display: none" id="DescImg3"/>
                                        <input name="DescImg4" style="display: none" id="DescImg4"/>
                                    </div>
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <a href="ProdutoServlet"  class="btn btn-sm btn-danger right" style="float: right;">
                                                Cancelar &nbsp;<i class="ace-icon fa fa-close"></i>
                                            </a>
                                            <c:choose>
                                                <c:when test= "${empty tarefa}">
                                                    <button type="submit" class="btn btn-sm btn-success " id="btnSalvar"
                                                            style="float: right; margin-right: 2px;" name="tarefa" value="cadastro" />
                                                    Registrar &nbsp; <i class="ace-icon fa fa-save"></i>
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="submit" class="btn btn-sm btn-success " id="bntEditar" style="float: right; margin-right: 2px;" value="${tarefa}" name="tarefa">
                                                        Alterar &nbsp; <i class="ace-icon fa fa-save"></i>
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>

                                        </div>
                                    </div>
                                </div>
                            </div>
                    </div>
                    </form>
                    <div id="myModal" class="modal fade" role="dialog">
                        <div class="modal-dialog">

                            <!-- Modal content-->
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Arquivo</h4>
                                </div>
                                <div class="modal-body">
                                    <p>Arquivo adicionado com sucesso.</p>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-success" data-dismiss="modal">Fechar</button>
                                </div>
                            </div>

                        </div>
                    </div>
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
                <script src="assets/js/jquery.mask.js"></script>
                <script src="assets/js/jquery.validate.js"></script>

                <!-- ace scripts -->
                <script src="assets/js/ace-elements.min.js"></script>
                <script src="assets/js/ace.min.js"></script>
                <script type="text/javascript" src="DataTables/datatables.min.js"></script>
                <script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
                <script type="text/javascript" src="https://cdn.datatables.net/rowreorder/1.2.7/js/dataTables.rowReorder.min.js"></script>
                <script type="text/javascript" src="https://cdn.datatables.net/responsive/2.2.4/js/dataTables.responsive.min.js"></script>

                <!-- inline scripts related to this page -->
                <script type="text/javascript">
                                                                                                jQuery(function ($) {

                                                                                                    window.displaymessage = function (user)
                                                                                                    {
                                                                                                        $('#valorEditar').val(user);
                                                                                                        $('#enviarEditacao').click();
                                                                                                        /*
                                                                                                         $.ajax({
                                                                                                         url: "UsuarioServlet",
                                                                                                         type: "POST",
                                                                                                         data: {
                                                                                                         tarefa: 'Editar'
                                                                                                         },
                                                                                                         })
                                                                                                         .success(function (e) {
                                                                                                         //do success stuff
                                                                                                         console.log("Sucessor " + e)
                                                                                                         //window.location = "UsuarioServlet?Teste=1";
                                                                                                         $(location).attr('href','cadastroUsuario.jsp');
                                                                                                         })
                                                                                                         .error(function (e) {
                                                                                                         //do error handling stuff
                                                                                                         console.log('Erro' + e.toString())
                                                                                                         })
                                                                                                         */
                                                                                                    }
                                                                                                    $("#btnSalvar").click(function () {
                                                                                                        var especificacao = "";
                                                                                                        var Perguntas = "";
                                                                                                        $('#tblEspecificao> tbody  > tr').each(function (indexlinha, tr) {
                                                                                                            $(this).find('td').each(function (index) {
                                                                                                                if (index == 1) {
                                                                                                                    especificacao += $(this).text() + "&";

                                                                                                                }
                                                                                                                if (index == 2) {
                                                                                                                    especificacao += $(this).text() + ";";
                                                                                                                }
                                                                                                            });
                                                                                                        });
                                                                                                        $('#tblPergunta> tbody  > tr').each(function (indexlinha, tr) {
                                                                                                            $(this).find('td').each(function (index) {
                                                                                                                if (index == 1) {
                                                                                                                    Perguntas += $(this).text() + "&";

                                                                                                                }
                                                                                                                if (index == 2) {
                                                                                                                    Perguntas += $(this).text() + "&";
                                                                                                                }
                                                                                                                if (index == 3) {
                                                                                                                    Perguntas += $(this).text() + ";";
                                                                                                                }
                                                                                                            });
                                                                                                        });
                                                                                                        $('#Especificacoes').val(especificacao)
                                                                                                        $('#Perguntas').val(Perguntas)
                                                                                                    })
                                                                                                    $("#bntEditar").click(function () {
                                                                                                        var especificacao = "";
                                                                                                        var Perguntas = "";
                                                                                                        $('#tblEspecificao> tbody  > tr').each(function (indexlinha, tr) {
                                                                                                            $(this).find('td').each(function (index) {
                                                                                                                if (index == 0) {
                                                                                                                    especificacao += $(this).text() + "&";

                                                                                                                }
                                                                                                                if (index == 1) {
                                                                                                                    especificacao += $(this).text() + "&";

                                                                                                                }
                                                                                                                if (index == 2) {
                                                                                                                    especificacao += $(this).text() + ";";
                                                                                                                }
                                                                                                            });
                                                                                                        });
                                                                                                        $('#tblPergunta> tbody  > tr').each(function (indexlinha, tr) {
                                                                                                            $(this).find('td').each(function (index) {
                                                                                                                if (index == 0) {
                                                                                                                    Perguntas += $(this).text() + "&";

                                                                                                                }
                                                                                                                if (index == 1) {
                                                                                                                    Perguntas += $(this).text() + "&";

                                                                                                                }
                                                                                                                if (index == 2) {
                                                                                                                    Perguntas += $(this).text() + "&";
                                                                                                                }
                                                                                                                if (index == 3) {
                                                                                                                    Perguntas += $(this).text() + ";";
                                                                                                                }
                                                                                                            });
                                                                                                        });
                                                                                                        $('#Especificacoes').val(especificacao)
                                                                                                        $('#Perguntas').val(Perguntas)
                                                                                                    })
                                                                                                    $(document).ready(function () {
                                                                                                        $('#tblEspecificao').DataTable({
                                                                                                            "language": {
                                                                                                                "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json"
                                                                                                            },
                                                                                                            responsive: true,
                                                                                                            "pageLength": 25
                                                                                                        });
                                                                                                        $('#tblPergunta').DataTable({
                                                                                                            "language": {
                                                                                                                "url": "//cdn.datatables.net/plug-ins/1.10.21/i18n/Portuguese-Brasil.json"
                                                                                                            },
                                                                                                            responsive: true,
                                                                                                            "pageLength": 25
                                                                                                        });
                                                                                                        $('.money').mask('000.000.000.000.000,00', {reverse: true});
                                                                                                    });
                                                                                                    counter = 1;
                                                                                                    count2 = 1;
                                                                                                    var valoresEspecificao = "";
                                                                                                    window.deleteEspecificacaoTempoExecucaoEspecificao = function (idLinha) {
                                                                                                        var linerow = 0;
                                                                                                        var line = false;
                                                                                                        var restovalores = false;

                                                                                                        $('#tblEspecificao> tbody  > tr').each(function (indexlinha, tr) {
                                                                                                            $(this).find('td').each(function (index) {
                                                                                                                if (index == 0) {
                                                                                                                    if (line == false) {
                                                                                                                        if (idLinha == $(this).text()) {
                                                                                                                            valoresEspecificao += $(this).text() + "&";
                                                                                                                            linerow = indexlinha;
                                                                                                                            line = true;
                                                                                                                            restovalores = true;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                                if (index == 1) {
                                                                                                                    if (restovalores == true) {
                                                                                                                        valoresEspecificao += $(this).text() + "&";
                                                                                                                    }
                                                                                                                }
                                                                                                                if (index == 2) {
                                                                                                                    if (restovalores == true) {
                                                                                                                        valoresEspecificao += $(this).text() + ";";
                                                                                                                        restovalores = false;
                                                                                                                    }
                                                                                                                }
                                                                                                            });
                                                                                                        });
                                                                                                        $('#EspecificacoesDeletar').val(valoresEspecificao);
                                                                                                        var oTable = $('#tblEspecificao').dataTable();
                                                                                                        linerow = linerow + 1;
                                                                                                        var row = oTable.find('tr').eq(linerow);
                                                                                                        oTable.fnDeleteRow(row[0]);
                                                                                                    }


                                                                                                    window.deleteEspecificacao = function (idLinha)
                                                                                                    {
                                                                                                        var linerow = 0;
                                                                                                        var line = false;
                                                                                                        $('#tblEspecificao> tbody  > tr').each(function (indexlinha, tr) {
                                                                                                            $(this).find('td').each(function (index) {
                                                                                                                if (index == 0) {
                                                                                                                    if (line == false) {
                                                                                                                        if (idLinha == $(this).text()) {
                                                                                                                            linerow = indexlinha;
                                                                                                                            line = true;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            });
                                                                                                        });
                                                                                                        var oTable = $('#tblEspecificao').dataTable();
                                                                                                        linerow = linerow + 1;
                                                                                                        var row = oTable.find('tr').eq(linerow);
                                                                                                        oTable.fnDeleteRow(row[0]);
                                                                                                    }

                                                                                                    window.deleteImage = function (idInput) {
                                                                                                        if (idInput === 1) {
                                                                                                            document.getElementById('InputImg1').value = "";
                                                                                                            document.getElementById('DescImg1').value = "";
                                                                                                            var node = document.getElementById("CardImg1");
                                                                                                            if (node.parentNode) {
                                                                                                                node.parentNode.removeChild(node);
                                                                                                            }

                                                                                                        } else if (idInput === 2) {
                                                                                                            document.getElementById('InputImg2').value = "";
                                                                                                            document.getElementById('DescImg2').value = "";
                                                                                                            var node = document.getElementById("CardImg2");
                                                                                                            if (node.parentNode) {
                                                                                                                node.parentNode.removeChild(node);
                                                                                                            }
                                                                                                        } else if (idInput === 3) {
                                                                                                            document.getElementById('InputImg3').value = "";
                                                                                                            document.getElementById('DescImg3').value = "";
                                                                                                            var node = document.getElementById("CardImg3");
                                                                                                            if (node.parentNode) {
                                                                                                                node.parentNode.removeChild(node);
                                                                                                            }
                                                                                                        } else if (idInput === 4) {
                                                                                                            document.getElementById('InputImg4').value = "";
                                                                                                            document.getElementById('DescImg4').value = "";
                                                                                                            var node = document.getElementById("CardImg4");
                                                                                                            if (node.parentNode) {
                                                                                                                node.parentNode.removeChild(node);
                                                                                                            }
                                                                                                        }
                                                                                                    }


                                                                                                    window.deletePergunta = function (idLinha)
                                                                                                    {
                                                                                                        var linerow = 0;
                                                                                                        var line = false;
                                                                                                        $('#tblPergunta  > tbody  > tr').each(function (indexlinha, tr) {
                                                                                                            $(this).find('td').each(function (index) {
                                                                                                                if (index == 0) {
                                                                                                                    if (line == false) {
                                                                                                                        if (idLinha == $(this).text()) {
                                                                                                                            linerow = indexlinha;
                                                                                                                            line = true;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            });
                                                                                                        });
                                                                                                        var oTable = $('#tblPergunta').dataTable();
                                                                                                        linerow = linerow + 1;
                                                                                                        var row = oTable.find('tr').eq(linerow);
                                                                                                        oTable.fnDeleteRow(row[0]);
                                                                                                    }
                                                                                                    var valoresPerguntas = "";
                                                                                                    window.deletePerguntaTempoExecucao = function (idLinha)
                                                                                                    {
                                                                                                        var linerow = 0;
                                                                                                        var line = false;
                                                                                                        var restovalores = false;

                                                                                                        $('#tblPergunta  > tbody  > tr').each(function (indexlinha, tr) {
                                                                                                            $(this).find('td').each(function (index) {
                                                                                                                if (index == 0) {
                                                                                                                    if (line == false) {
                                                                                                                        if (idLinha == $(this).text()) {
                                                                                                                            linerow = indexlinha;
                                                                                                                            valoresPerguntas += $(this).text() + "&";
                                                                                                                            line = true;
                                                                                                                            restovalores = true;
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                                if (index == 1) {
                                                                                                                    if (restovalores == true) {
                                                                                                                        valoresPerguntas += $(this).text() + "&";
                                                                                                                    }
                                                                                                                }
                                                                                                                if (index == 2) {
                                                                                                                    if (restovalores == true) {
                                                                                                                        valoresPerguntas += $(this).text() + "&";
                                                                                                                    }
                                                                                                                }
                                                                                                                if (index == 3) {
                                                                                                                    if (restovalores == true) {
                                                                                                                        valoresPerguntas += $(this).text() + ";";
                                                                                                                        restovalores = false;
                                                                                                                    }
                                                                                                                }
                                                                                                            });
                                                                                                        });
                                                                                                        $('#PerguntasDeletar').val(valoresPerguntas)
                                                                                                        var oTable = $('#tblPergunta').dataTable();
                                                                                                        linerow = linerow + 1;
                                                                                                        var row = oTable.find('tr').eq(linerow);
                                                                                                        oTable.fnDeleteRow(row[0]);
                                                                                                    }


                                                                                                    $('#btnAddEspecificacao').click(function () {
                                                                                                        var t = $('#tblEspecificao').DataTable();
                                                                                                        var dsEspecificacao = $('#dsEspecificacao').val();
                                                                                                        var dsResposta = $('#dsResposta').val();
                                                                                                        var htmlButton = "<div class='btn-group'> <button class='btn btn-xs btn-danger' type='button'  onclick='window.deleteEspecificacao(" + counter + ")'> <i class='ace-icon fa fa-trash-o bigger-120'></i> </button> </div>"
                                                                                                        t.row.add([
                                                                                                            counter,
                                                                                                            dsEspecificacao,
                                                                                                            dsResposta,
                                                                                                            htmlButton
                                                                                                        ]).draw(false);
                                                                                                        counter++;
                                                                                                    });
                                                                                                    $('#btnAddImage').click(function () {
                                                                                                        var input1 = document.querySelector('#InputImg1').value;
                                                                                                        var input2 = document.querySelector('#InputImg2').value;
                                                                                                        var input3 = document.querySelector('#InputImg3').value;
                                                                                                        var input4 = document.querySelector('#InputImg4').value;
                                                                                                        var input = document.querySelector('#dsImage');
                                                                                                        var descricao = document.querySelector('#dsDescricao');
                                                                                                        if (descricao.value == "") {
                                                                                                            alert("Preencha uma descrição");
                                                                                                            return;
                                                                                                        }
                                                                                                        if (input.value == "") {
                                                                                                            alert("Insira uma imagem");
                                                                                                            return;
                                                                                                        }
                                                                                                        var divCards = document.querySelector('#AreaDeImgs');
                                                                                                        var b64;
                                                                                                        var filename;
                                                                                                        if (input.value) {
                                                                                                            var startIndex = (input.value.indexOf('\\') >= 0 ? input.value.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
                                                                                                            filename = input.value.substring(startIndex);
                                                                                                            if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
                                                                                                                filename = filename.substring(1);
                                                                                                            }

                                                                                                        }
                                                                                                        var file = input.files[0],
                                                                                                                reader = new FileReader();

                                                                                                        reader.onloadend = function () {
                                                                                                            b64 = reader.result.replace(/^data:.+;base64,/, '');
                                                                                                            //console.log(b64); //-> 
                                                                                                            if (input1 == "") {
                                                                                                                document.getElementById('InputImg1').value = b64;
                                                                                                                document.getElementById('DescImg1').value = descricao.value;
                                                                                                                divCards.innerHTML += '<div class="col-lg-3" id="CardImg1"><div class="card text-center" style="width: 18rem;"><img src="data:image/jpeg;base64,' + b64 + '"  style="max-height:150px;max-width:150px;" class="card-img-top" alt="..."><div class="card-body"><h5 class="card-title">' + filename + '</h5><p class="card-text">' + (descricao.value) + '</p><a onclick="window.deleteImage(1)" class="btn btn-danger">Excluir</a> </div></div></div>';
                                                                                                            } else if (input2 == "") {
                                                                                                                document.getElementById('InputImg2').value = b64;
                                                                                                                document.getElementById('DescImg2').value = descricao.value;
                                                                                                                divCards.innerHTML += '<div class="col-lg-3" id="CardImg2"><div class="card text-center" style="width: 18rem;"><img src="data:image/jpeg;base64,' + b64 + '" style="max-height:150px;max-width:150px;" class="card-img-top" alt="..."><div class="card-body"><h5 class="card-title">' + filename + '</h5><p class="card-text">' + (descricao.value) + '</p><a onclick="window.deleteImage(2)" class="btn btn-danger">Excluir</a> </div></div></div>';
                                                                                                            } else if (input3 == "") {
                                                                                                                document.getElementById('InputImg3').value = b64;
                                                                                                                document.getElementById('DescImg3').value = descricao.value;
                                                                                                                divCards.innerHTML += '<div class="col-lg-3" id="CardImg3"><div class="card text-center" style="width: 18rem;"><img src="data:image/jpeg;base64,' + b64 + '" style="max-height:150px;max-width:150px;" class="card-img-top" alt="..."><div class="card-body"><h5 class="card-title">' + filename + '</h5><p class="card-text">' + (descricao.value) + '</p><a onclick="window.deleteImage(3)" class="btn btn-danger">Excluir</a> </div></div></div>';
                                                                                                            } else if (input4 == "") {
                                                                                                                document.getElementById('InputImg4').value = b64;
                                                                                                                document.getElementById('DescImg4').value = descricao.value;
                                                                                                                divCards.innerHTML += '<div class="col-lg-3" id="CardImg4"><div class="card text-center" style="width: 18rem;"><img src="data:image/jpeg;base64,' + b64 + '" class="card-img-top" style="max-height:150px;max-width:150px;" alt="..."><div class="card-body"><h5 class="card-title">' + filename + '</h5><p class="card-text">' + (descricao.value) + '</p><a onclick="window.deleteImage(4)" class="btn btn-danger">Excluir</a> </div></div></div>';
                                                                                                            } else {
                                                                                                                alert("Máximo 4 imagens");
                                                                                                                return;
                                                                                                            }
                                                                                                        };

                                                                                                        reader.readAsDataURL(file);
                                                                                                        $('#myModal').modal()
                                                                                                    });

                                                                                                    $('#btnAddPergunta').click(function () {
                                                                                                        var t = $('#tblPergunta').DataTable();
                                                                                                        var dsPerguntasPerguntas = $('#dsPerguntasPerguntas').val();
                                                                                                        var dsRespostaPerguntas = $('#dsRespostaPerguntas').val();
                                                                                                        var dsAvaliacaoPerguntas = $('#dsAvaliacaoPerguntas').val();
                                                                                                        var htmlButton = "<div class='btn-group'> <button class='btn btn-xs btn-danger' type='button' onclick='window.deletePergunta(" + count2 + ")'> <i class='ace-icon fa fa-trash-o bigger-120'></i> </button> </div>"
                                                                                                        t.row.add([
                                                                                                            count2,
                                                                                                            dsPerguntasPerguntas,
                                                                                                            dsRespostaPerguntas,
                                                                                                            dsAvaliacaoPerguntas,
                                                                                                            htmlButton
                                                                                                        ]).draw(false);
                                                                                                        count2++;
                                                                                                    });



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
                                                                                                });
                </script>
                </body>

                </html>
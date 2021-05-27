<%@page import="java.text.DecimalFormat"%>
<%@page import="com.thinkcode.models.RelatorioModel"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">

    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Ordem de Serviço</title>
        <meta name="description" content="overview &amp; stats" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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

        <!-- ace settings handler -->
        <script src="assets/js/ace-extra.min.js"></script>

        <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

        <!--[if lte IE 8]>
                    <script src="assets/js/html5shiv.min.js"></script>
                    <script src="assets/js/respond.min.js"></script>
                    <![endif]-->
        <style>   
            .myDivToPrint{
                color:rgb(255, 255, 255);
                background-color: rgb(0, 0, 0);
                text-align: center; 
                margin-top: 1%;
            }

            .bordas{

                border: 1px solid rgb(119, 119, 119);
            }

            .margin-top-6{

                margin-top: 6%
            }

            .fundoCinza{
                border: 1px solid rgb(119, 119, 119);
                background-color:rgb(163, 163, 163);
            }

            @media print {
                body * {
                    visibility: hidden  !important;;

                    font-size: 11pt !important;;

                }
                #printable, #printable * {
                    visibility: visible !important;;
                }
                #printable, b {
                    visibility: visible;
                    color: white !important;
                    -webkit-print-color-adjust: exact;
                }
                #printable {

                }
                col-sm-1, .col-sm-2, .col-sm-3, .col-sm-4, .col-sm-5, .col-sm-6, .col-sm-7, .col-sm-8, .col-sm-9, .col-sm-10, .col-sm-11, .col-sm-12 {
                    float: left;
                }
                .col-sm-12 {
                    width: 100%;
                }
                .col-sm-11 {
                    width: 91.66666667%;
                }
                .col-sm-10 {
                    width: 83.33333333%;
                }
                .col-sm-9 {
                    width: 75%;
                }
                .col-sm-8 {
                    width: 66.66666667%;
                }
                .col-sm-7 {
                    width: 58.33333333%;
                }
                .col-sm-6 {
                    width: 50%;
                }
                .col-sm-5 {
                    width: 41.66666667%;
                }
                .col-sm-4 {
                    width: 33.33333333%;
                }
                .col-sm-3 {
                    width: 25%;
                }
                .col-sm-2 {
                    width: 16.66666667%;
                }
                .col-sm-1 {
                    width: 8.33333333%;
                }
                .myDivToPrint {
                    color: white !important;
                    background-color: black !important;
                    text-align: 
                        center; margin-top: 1%;
                    -webkit-print-color-adjust: exact;
                }
                .bordas{

                    border: 1px solid rgb(119, 119, 119);
                    -webkit-print-color-adjust: exact;
                }

                .margin-top-6{

                    margin-top: 6%
                }

                 .fundoCinza{
                    border: 1px solid rgb(119, 119, 119) !important;
                    background-color:rgb(163, 163, 163) !important;
                    -webkit-print-color-adjust: exact;
                }
                img {
                    width: 20%;  
                }
            }
        </style>
    </head>

    <body style="font-family:sans-serif,Helvetica,Arial;page-break-before: always;" >


        <div class="container" id="printable">
            <div class="col-lg-12 col-sm-12 col-xs-12" style="margin-top: 2%;">
                <div class="col-lg-4 col-sm-4 col-xs-4">
                    <img src="assets/images/LogoAvr.jpeg" width="50%">
                </div>
                <div class="col-lg-8 col-sm-8 col-xs-8" style="text-align: right; margin-top: 2%;">
                    <div><strong>Oficina :</strong>RUA CIRO MAIA DE CARVALHO ,284 - JD DAS PALMAS - SP</div>
                    <div><strong>Cep :</strong>05749-270</div>
                    <div><strong>Fone :</strong>(11) 98349-4218 </div>
                    <div><strong>E-mais :</strong> avr_autoservice@outlook.com</div>

                </div>
            </div>

            <div class="col-lg-12 col-sm-12 col-xs-12 myDivToPrint" 
                 >
                <b>AVR GARAGE OFICINA MECANICA</b>
            </div>
            <div class="col-lg-12 col-sm-12 col-xs-12 bordas">
                <div class="col-lg-8 col-sm-8 col-xs-8" style="padding: 1%;"> <strong> Cliente</strong> : ${ordem.nomeCliente}</div>
                <div class="col-lg-4 col-sm-4 col-xs-4" style="padding: 1%;"><strong>Início Do Serviço</strong> : 0</div>
            </div>
            <div class="col-lg-12 col-sm-12 col-xs-12 bordas" >
                <div class="col-lg-8 col-sm-8 col-xs-8" style="padding: 1%;"><strong>Veículo</strong> : ${ordem.dsMarcaCarro}</div>
                <div class="col-lg-4 col-sm-4 col-xs-4" style="padding: 1%;"> <strong>Término De Serviço</strong> :0</div>
            </div>

            <div class="col-lg-12 col-sm-12 col-xs-12 bordas" >
                <div class="col-lg-8 col-sm-8 col-xs-8" style="padding: 1%;"> <strong>Modelo</strong> : ${ordem.dsModeloCarro}</div>
                <div class="col-lg-4 col-sm-4 col-xs-4" style="padding: 1%;"><strong>KM</strong>:0:</div>
            </div>

            <div class="col-lg-12 col-sm-12 col-xs-12 bordas" >
                <div class="col-lg-8 col-sm-8 col-xs-8" style="padding: 1%;"><strong>Placa</strong> : ${ordem.dsModeloPlaca}</div>
                <div class="col-lg-4 col-sm-4 col-xs-4" style="padding: 1%;"><strong>Telefone</strong> : ${ordem.telefone}</div>
            </div>



            <div class="col-lg-12 col-sm-12 col-xs-12 myDivToPrint" >
                <b>Serviços Executados</b>
            </div>
            <div  class="col-lg-12 col-sm-12 col-xs-12 fundoCinza">
                <div class="col-lg-8 col-sm-8 col-xs-8 " style="padding: 1%;"> <strong>Descriminação Do Serviço</strong></div>
                <div class="col-lg-4 col-sm-4 col-xs-4 " style="padding: 1%;"> <strong>Valor R$</strong>: </div>
            </div>



            <div class="col-lg-12 col-sm-12 col-xs-12 bordas" >
                <%
                    DecimalFormat df = new DecimalFormat("###,###.00");
                    RelatorioModel rel = new RelatorioModel();
                    rel = (RelatorioModel) request.getAttribute("ordem");
                    Double valorServico = 0.0;
                    String Servico = "";
                    Double TotalServico = 0.0;
                    for (int i = 0; i < rel.getValorServico().size(); i++) {
                        valorServico = rel.getValorServico().get(i);
                        Servico = rel.getDsServico().get(i);
                        TotalServico += rel.getValorServico().get(i);

                %>
                <div class="col-lg-8 col-sm-8 col-xs-8" style="padding: 1%;"> <%= Servico%> </div>
                <div class="col-lg-4 col-sm-4 col-xs-4" style="padding: 1%;"> <%= df.format(valorServico)%> </div>           
                <%
                    }

                %>

            </div>







            <div class="col-lg-12 col-sm-12 col-xs-12 myDivToPrint" >
                <b>Peças e Acessórios</b>
            </div>
            <div class="col-lg-12 col-sm-12 col-xs-12 bordas fundoCinza" >
                <div class="col-lg-2 col-sm-2 col-xs-2" style="padding: 1%;"><strong>QTD.</strong></div>
                <div class="col-lg-2 col-sm-2 col-xs-2" style="padding: 1%;"><strong>UN.</strong></div>
                <div class="col-lg-4 col-sm-4 col-xs-4" style="padding: 1%;"><strong>Peças</strong></div>
                <div class="col-lg-2 col-sm-2 col-xs-2" style="padding: 1%;"><strong>Unitário R$</strong></div>
                <div class="col-lg-2 col-sm-2 col-xs-2" style="padding: 1%;"><strong>Total R$</strong></div>           
            </div>
            <div class="col-lg-12 col-sm-12 col-xs-12 bordas">
                <%                    RelatorioModel rels = new RelatorioModel();
                    rels = (RelatorioModel) request.getAttribute("ordem");
                    Double dsValorProdutos = 0.0;
                    int QtdProduto = 0;
                    String NomeProduto = "";
                    Double valorProdutoTotal = 0.0;
                    Double valorTotalPecas = 0.0;
                    for (int i = 0; i < rel.getValorServico().size(); i++) {
                        dsValorProdutos = rel.getValorUnitarioProduto().get(i);
                        QtdProduto = rel.getQtdProdutos().get(i);
                        NomeProduto = rel.getNomeProdutos().get(i);
                        valorProdutoTotal = rel.getdsValorProdutos().get(i);
                        valorTotalPecas += rel.getdsValorProdutos().get(i);
                %>
                <div class="col-lg-2 col-sm-2 col-xs-2" style="padding: 1%;"> <%= QtdProduto%> </div>
                <div class="col-lg-2 col-sm-2 col-xs-2" style="padding: 1%;">LT.</div>
                <div class="col-lg-4 col-sm-4 col-xs-4" style="padding: 1%;"> <%= NomeProduto%> </div>
                <div class="col-lg-2 col-sm-2 col-xs-2" style="padding: 1%;"> <%= df.format(dsValorProdutos)%> </div> 
                <div class="col-lg-2 col-sm-2 col-xs-2" style="padding: 1%;"> <%= df.format(valorProdutoTotal)%> </div>
                <%     }


                %>
            </div>
            <!--<div class="col-lg-12" style="margin-top: 2%;" id="divImpressao">
                <button class="btn btn-success btn-sm pull-right" type="button" id="btnPrint"><i class="ace-icon fa fa-print bigger-120"></i> Imprimir</button>
            </div>-->

            <div class="col-lg-12 col-sm-12 col-xs-12 margin-top-6">
                <p>
                    <strong>Peças: </strong> R$ <%= df.format(valorTotalPecas)%> 
                </p>
                <p>
                    <strong>Serviços: </strong> R$ <%= df.format(TotalServico)%>
                </p>
                <p>
                    <strong>Total: </strong> R$ <%= df.format(TotalServico + valorTotalPecas)%>
                </p>
            </div>




            <div class="row col-lg-12 col-sm-12 col-xs-12 margin-top-6" >
                <div class="col-lg-12">
                    Os serviços acima estão cobertos por garantia contra falhas de montagem ou execução assim o mesmo
                    pelo prazo de três meses da data de entrega do veículo. As peças aplicadas estão cobertas por garantia dada por
                    seus respectivos fabricantes.<br>
                    Demonstrativo para simples conferência sem valor fiscal.
                </div>
            </div>

        </div>
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

        <!-- ace scripts -->
        <script src="assets/js/ace-elements.min.js"></script>
        <script src="assets/js/ace.min.js"></script>
        <script src="assets/js/printThis.js"></script>

        <script>
            jQuery(function ($) {
                /*$('#btnPrint').click(function () {
                 
                 $('#divImpressao').hide();
                 $("#printable").printThis({
                 debug: false, // show the iframe for debugging
                 importCSS: true, // import parent page css
                 importStyle: true, // import style tags
                 printContainer: true, // print outer container/$.selector
                 loadCSS: "/assets/css/print.css", // path to additional css file - use an array [] for multiple                       
                 printDelay: 333, // variable print delay                       
                 copyTagClasses: false, // copy classes from the html & body tag
                 });
                 
                 
                 })*/

            });
        </script>
    </body>
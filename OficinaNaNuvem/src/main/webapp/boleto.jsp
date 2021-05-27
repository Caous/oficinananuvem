<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="pt-br">
  <head>
  
    <title>Boleto</title>
  </head>

  <body>
    
         <object style="width:100%;min-height:645px;" type="application/pdf" data="data:application/pdf;base64,${base64}"/>
   ${base64}
  </body>
</html>

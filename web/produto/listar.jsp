
<%@page import="model.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.ProductDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listagem de produtos</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" >
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body class="container">
        <%
            ProductDao produtoDao = new ProductDao();
            ArrayList <Product> lista = produtoDao.listarTodos();
            request.setAttribute("lista", lista);
            int qtd = produtoDao.amountOfProducts();
            
            
        %>
        <hr>
        <table class="table table-striped table-bordered table-hover ">
            <tr class="text-center font-weight-bold">
                <td>PRODUTO</td>
                <td>DESCRICAO</td>
                <td>PREÇO</td>
                <td>EXCLUIR</td>
                
            </tr>
        <c:forEach var="produto" items="${lista}">
        <tr>
            <td><c:out value=" ${produto.name}" /></td>
            <td><c:out value=" ${produto.description}" /> </td>
            <td><c:out value=" ${produto.price}" /></td>
            <td><a href="<c:out value="${root}" />/produto?action=delete&id=<c:out value='${produto.id}'/>">Deletar</a></td>
            
        </tr>
        
        
        </c:forEach>
        </table>
        <p>Quantidade de produtos cadastrados: <b><%= qtd %></b></p>
        <a href="<c:out value="${root}" />/create-product.jsp" class="btn btn-primary" >Cadastrar novo produto</a>
            
        
        <%--<c:forEach var="i" begin="1" end="10" step="1">
            <c:out value="${2*i}" />
 
            <br />
        </c:forEach>--%>
            
            
        
    </body>
</html>

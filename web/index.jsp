<%-- 
    Document   : index.jsp
    Created on : 24/10/2019, 19:59:32
    Author     : Celestino
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Product"%>
<%@page import="dao.ProductDao"%>
<%@ include file="/include.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>Home - Seja Bem vindo</title>
	
	<meta name="viewport" content="width=device-width, user-scalable=no">
	
</head>

<body>
    <main id="conteudo" class="container-fluid">
		<header class="header" >
			<div class="header-top">
				<a href="${pageContext.request.contextPath}"><img id="logo" src="${pageContext.request.contextPath}/img/logo.png" /></a>

				<div class="menu-link" >
					<a href="" class="active">Home</a>
					<a href="">Sobre</a>
					<a href="">Artigos</a>
					<a href="">Links</a>
					<a href="carrinho.html">Carrinho</a>
					<a href="">Login</a>
				</div>
			</div>
		</header>

		<section class="banner " >
			<div class="banner-img">
				<img src="${pageContext.request.contextPath}/img/produto/banner/notebook-2am-positivo.jpg" >
				<br />
				<a href="" class="descricao-img-banner"><p>Notebook gamer Samsung Odyssey i7 8gb full hd pls</p></a>
				<br />
			</div>
		</section>
	
		
		<section class="container-fluid produtos produtos-main">
			<div class="row">
                            
                            <%
                                ProductDao produtoDao = new ProductDao();
                                ArrayList <Product> listProducts = produtoDao.listarTodos();
                                request.setAttribute("lista", listProducts);
                                int qtd = produtoDao.amountOfProducts();
                                request.setAttribute("qtdProducts", qtd);
                            %>
                            
                            <div class="col-12 ">
                                <c:forEach var="produto" items="${lista}" varStatus="numberItem" >
                                   <div class="card"  title="${produto.description}" style="width: 20rem;">
                                        <div class="card-body">
                                          
                                          <img class="produto" src="${initParam.imageFolder}produto${numberItem.count}.jpg" />
                                          <h6 class="card-title">${produto.name}</h6>
                                          <h6 class="card-subtitle mb-2 text-muted">${produto.description}</h6>
                                          <p class="card-text font-weight-bold">R$${produto.price}</p>
                                          <a href="#" class="btn btn-add-to-cart">Adicionar ao carrinho</a>
                                          <!--<a href="#" class="card-link">Another link</a>-->
                                        </div>
                                    </div>
                                    
                                </c:forEach>
                            </div>
                            
			</div>
                            
		</section>

	</main>
</body>

</html>
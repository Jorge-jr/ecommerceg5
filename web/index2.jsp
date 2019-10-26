<%@page import="java.util.ArrayList"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@page import="dao.*"%>
<%@page import="model.*"%>
<%@page import="javax.servlet.http.*"%>


<!DOCTYPE html>
<html>
<head>
    <title>Home - Seja Bem vindo</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="css/style.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://kit.fontawesome.com/d6dd6a2be1.js" crossorigin="anonymous"></script>

</head>

<body>
    <c:if test= "${empty pagina}">
        <c:redirect url="/Pagina?pagina=1"/>
    </c:if>
    <main id="conteudo">
        <header class="header" >
            <div class="header-top">
                <a href="index.html"><img id="logo" src="https://is2-ssl.mzstatic.com/image/thumb/Purple113/v4/44/a4/32/44a43290-5604-91a2-595c-16b25980c473/AppIcon-0-1x_U007emarketing-0-0-GLES2_U002c0-512MB-sRGB-0-0-0-85-220-0-0-0-10.png/256x256bb.png" /></a>
        <!-- 	border-bottom: 1px solid #ECEFF4; -->

                <div class="menu-link" >
                    <a href="">Home</a>
                    <a href="">Sobre</a>
                    <a href="">Artigos</a>
                    <a href="">Links</a>
                    <a href="carrinho.html">Carrinho</a>
                    <a href="">Login</a>
                </div>
            </div>
        </header>

        <section class="banner" >
            <div class="banner-img">
                    <img src="${pageContext.request.contextPath}/img/produto/banner/notebook-2am-positivo.jpg" >
                    <br />
                    <a href="" class="descricao-img-banner"><p>Notebook gamer Samsung Odyssey i7 8gb full hd pls</p></a>
                    <br />
            </div>
        </section>


        <section class="container-fluid produtos produtos-main">
            <div class="row">


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
             ${pageContext.request.contextPath}
            <p>Pagina: ${pagina == null ? "null" : pagina}</p>
            <div class="index_box">
                <div class="vcr">
                    <a href="Pagina?method=get&pagina=1"><div class="vcr_button"><i class="fas fa-angle-double-left"></i></div></a>
                    <a href=${"Pagina?method=get&pagina="}${pagina == 1 ? 1 : pagina-1}><div class="vcr_button"><i class="fas fa-angle-left"></i></div></a>
                    <input type="text" size="1" name="pagina" value=${pagina}> ${pagina*5-4} a ${pagina==qtdeDePaginas?tamanhoTotal:pagina*5} de ${tamanhoTotal} <%-- https://stackoverflow.com/questions/20998541/get-the-value-of-input-text-when-enter-key-pressed --%>
                    <a href=${pagina == qtdeDePaginas ? "Pagina?method=get&pagina=" : "Pagina?method=get&pagina="}${pagina == qtdeDePaginas ? qtdeDePaginas : pagina+1}><div class="vcr_button"><i class="fas fa-angle-right"></i></div></a>
                    <a href=${"Pagina?method=get&pagina="}${qtdeDePaginas}><div class="vcr_button"><i class="fas fa-angle-double-right"></i></div></a>
                   </div>
            </div>

    </main>
</body>

</html>
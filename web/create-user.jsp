<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
        <script src="js/bootstrap.min.js"></script>
        <title>Create User</title>
    </head>
    <body>
        <div class="container">
            <c:if test="${error != null}">
                <div class="alert alert-danger" role="alert">
                    ${error}
                </div>                
            </c:if>

            <div class="row justify-content-center mt-3">
                <div class="col col-4">
                    <form action="user" method="POST">
                        <div class="form-group">
                            <label for="cpf-input">CPF</label>
                            <input required type="text" class="form-control" name="cpf" id="cpf-input" placeholder="12345678901">
                        </div>
                        <div class="form-group">
                            <label for="email-input">Email</label>
                            <input required type="email" class="form-control" name="email" id="email-input" placeholder="email@example.com">
                        </div>
                        <div class="form-group">
                            <label for="full-name-input">Nome completo</label>
                            <input required type="text" class="form-control" name="fullName" id="full-name-input" placeholder="João da Silva">
                        </div>
                        <div class="form-group">
                            <label for="username-input">Username</label>
                            <input required type="text" class="form-control" name="username" id="username-input" placeholder="joaosilva">
                        </div>
                        <div class="form-group">
                            <label for="password-input">Senha</label>
                            <input required type="password" class="form-control" name="password" id="password-input" placeholder="Senha">
                        </div>
                        <center>
                            <button type="submit" class="btn btn-primary">Criar conta</button>
                        </center>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>

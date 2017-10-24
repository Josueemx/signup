<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Sign up</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="panel panel-primary">
                <div class="panel-heading">Sign up</div>
                <div class="panel-body">
                    <div class="row">
                        <c:forEach var="error" items="${errors}">
                            <p class="bg-danger">${error}</p>
                        </c:forEach>
                    </div>                    
                    
                    <form action="addUser" method="POST">
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" id="name" name="name" value="${name}" required/>
                        </div>
                        <div class="form-group">
                            <p class="bg-danger" style="display: none;" id="username_message"></p>
                        </div>
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" id="username" name="username" value="${username}" autocomplete="off" required/>
                        </div>
                        <div class="form-group">
                            <p style="display: none;" id="password_message"></p>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Password:</label>
                            <input type="password" class="form-control" id="password" name="password" value="${password}" autocomplete="off" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                    </form>
                </div>
            </div> 
        </div>
    </body>
    <script>
        $('#username').keyup(function() {
            $.ajax({ 
                type: 'GET', 
                url: 'http://localhost:8080/signup/getUser', 
                data: {username : $('#username').val()},
                dataType: 'json',
                success: function (data) {
                    if(data.hasMessage=="true"){
                        $('#username_message').show();
                        $('#username_message').html(data.message);
                    }
                    else{
                        $('#username_message').hide();
                    }
                }
            });
        });
        
        $('#password').keyup(function() {
            $.ajax({ 
                type: 'GET', 
                url: 'http://localhost:8080/signup/validatePassword', 
                data: {password : $('#password').val()},
                dataType: 'json',
                success: function (data) {
                    $('#password_message').removeClass('bg-info');
                    $('#password_message').removeClass('bg-success');
                    $('#password_message').removeClass('bg-warning');
                    if($('#password').val().length>0){
                        $('#password_message').show();
                        $('#password_message').addClass(data.textClass);
                        $('#password_message').html("Password security level: "+data.level);
                    }
                    else{
                        $('#password_message').hide();
                    }
                }
            });
        });
    </script>
</html>

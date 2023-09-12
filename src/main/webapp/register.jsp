

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New User</title>
        <%@include file="Components/Common_css_js.jsp" %>

    </head>
    <body>
        <%@include file="Components/navbar.jsp" %>
        <div class="container-fluid">
            
        <div class="row mt-5">
            <div class="col-md-4 offset-md-4">
                <div class="card">
                    <%@include file="Components/message.jsp" %>
                    <div class="card-body px-5">
                        <div class="container text-center">
                            <span style="font-size: 30px;" class="fa fa-user-circle"></span>
                        </div>
                       
                        <h3 class="text-center my-3">Sign up here</h3>
                        
                        <form action="RegisterServlet" method="post">
                            <div class="form-group">
                                <label for="name">User Name</label>
                                <input name="username" type="text" class="form-control" id="name" aria-describedby="emailHelp" placeholder="Enter your name">
                            </div> 
                            <div class="form-group">
                                <label for="email">User Email</label>
                                <input name="useremail" type="Email" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter your Email">
                            </div>
                            <div class="form-group">
                                <label for="password">User Password</label>
                                <input name="userpassword" type="password" class="form-control" id="password" aria-describedby="emailHelp" placeholder="Enter your password">
                            </div>
                            <div class="form-group">
                                <label for="phone">User Phone</label>
                                <input name="userphone" type="number" class="form-control" id="phone" aria-describedby="emailHelp" placeholder="Enter your phone no">
                            </div>
                            <div class="form-group">
                                <label for="address">Address</label>
                                <textarea name="useraddress" style="height: 200px" class="form-control" placeholder="Enter your address"></textarea>
                            </div>

                            <div class="container text-center">
                                <button class="btn btn-outline-success">Register</button>
                                <button class="btn btn-outline-warning">Reset</button>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>

        </div>

    </body>
</html>

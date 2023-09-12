<%@page import="com.mycart.entites.User" %>
<%@page import="com.mycart.entites.Category" %>
<%@page import="com.mycart.dao.CategoryDao" %>
<%@page import="com.mycart.helper.*" %>

<%@page import="java.util.*" %>

<%
    User user = (User)session.getAttribute("current-user");
    if(user == null){
    session.setAttribute("message","you are not logged in !! Login first");
    response.sendRedirect("login.jsp");
    return;
    
    }else{
      if(user.getUserType().equals("normal")){
      session.setAttribute("message","you are not admin ! Do not access this page");
      response.sendRedirect("login.jsp");
      return;
      
    }
    }
%>

<% 
           CategoryDao ctg = new CategoryDao(FactoryProvider.getFactory());
           List<Category>list = ctg.getCategories();
%>
<% 
//         getting count
Map<String,Long> map = Stringhelper.getCounts(FactoryProvider.getFactory());
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin : Panel</title>
        <%@include file="Components/Common_css_js.jsp" %>
    </head>
    <body>
        <%@include file="Components/navbar.jsp" %>

        <div class="container admin">
            <div class="container-fluid mt-3">
                <%@include file="Components/message.jsp" %>
            </div>

            <div class="row mt-3">
                <!--first col-->
                <div class="col-md-4">
                    <!--first box-->
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img class="img-fluid rounded-circle" style="max-width: 100px;" src="Img/team.png" alt="user_icon"/>
                            </div>
                            <h1><%= map.get("userCount") %></h1>
                            <h1 class="text-uppercase text-muted">Users</h1>
                        </div>
                    </div>
                </div>
                <!--second col-->
                <div class="col-md-4">
                    <!--Second Box-->
                    <div class="card">
                        <div class="card-body text-center">
                            <div class="container">
                                <img class="img-fluid rounded-circle" style="max-width: 100px;" src="Img/list.png" alt="user_icon"/>
                            </div>
                            <h1><%= list.size()%></h1>
                            <h1 class="text-uppercase text-muted">Categories</h1>
                        </div>
                    </div>
                </div>
                <!--third col-->
                <div class="col-md-4">
                    <!--third Box-->
                    <div class="card">

                        <div class="card-body text-center">
                            <div class="container">
                                <img class="img-fluid rounded-circle" style="max-width: 100px;" src="Img/product.png" alt="user_icon"/>
                            </div>
                            <h1><%= map.get("ProductCount") %></h1>
                            <h1 class="text-uppercase text-muted">Products</h1>
                        </div>
                    </div>
                </div>
            </div>
            <!--Second row-->
            <div class="row mt-3">
                <!--second :row first col-->
                <div class="col-md-6">
                    <div class="card" data-toggle="modal" data-target="#add-category-modal">

                        <div class="card-body text-center">
                            <div class="container">
                                <img class="img-fluid rounded-circle" style="max-width: 100px;" src="Img/app.png" alt="user_icon"/>
                            </div>
                            <p class="mt-2">Click here to add new category</p>
                            <h1 class="text-uppercase text-muted">Add Category</h1>
                        </div>
                    </div>
                </div> 
                <!--second : row second col-->
                <div class="col-md-6">
                    <div class="card" data-toggle="modal" data-target="#add-product-modal">

                        <div class="card-body text-center">
                            <div class="container">
                                <img class="img-fluid rounded-circle" style="max-width: 100px;" src="Img/add.png" alt="user_icon"/>
                            </div>
                            <p class="mt-2">Click here to add new Product</p>
                            <h1 class="text-uppercase text-muted">Add Products</h1>
                        </div>
                    </div>
                </div> 
            </div>
        </div>
        <!--add category modal-->


        <!-- Modal -->
        <div class="modal fade" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header custom-bg text-white">
                        <h5 class="modal-title" id="exampleModalLongTitle"> Fill Category details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="ProductOperationServlet" method="post">

                            <input type="hidden" name="operation" value="addcategory"/>

                            <div class="form-group">
                                <input type="text" class="form-control" name="catTitle" placeholder="Enter category title" required=""/>

                            </div>
                            <div class="form-group">
                                <textarea style="height: 200px" class="form-control" name="catDescription" placeholder="Enter category description" required=""></textarea>
                            </div>
                            <div class="container text-center">
                                <button class="btn btn-outline-success">Add Category</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
        </div>


        <!--End add category modal-->

        <!--add product modal-->


        <!-- Modal -->
        <div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header custom-bg text-white">
                        <h5 class="modal-title" id="exampleModalLabel">Fill Product details</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
                            <input type="hidden" name="operation" value="addproduct"/>
                            <div class="form-group">
                                <select name="catId" class="form-control">
                                    <%
                                        for(Category ct:list){
                                    %>
                                    <option value="<%= ct.getCatId() %>"><%= ct.getCatTitle() %></option>
                                    <%
                                    }
                                    %>

                                </select>
                            </div>
                            <div class="form-group">
                                <input type="text" name="pname" class="form-control" placeholder="Enter product name" required=""/>
                            </div>
                            <div class="form-group">
                                <textarea style="height: 200px" class="form-control" name="pDesc" placeholder="Enter category description" required=""></textarea>
                            </div>
                            <div class="form-group">
                                <input type="number" name="pprice" class="form-control" placeholder="Enter product price" required=""/>
                            </div>
                            <div class="form-group">
                                <input type="number" name="pdiscount" class="form-control" placeholder="Enter discount" required=""/>
                            </div>
                            <div class="form-group">
                                <input  type="number" name="pquentity" class="form-control" placeholder="Enter quentity" required=""/>
                            </div>
                            <div class="form-group">
                                <input  type="file" name="pphoto" class="form-control" multiple/>
                            </div>
                            <div class="container text-center">
                                <button class="btn btn-outline-success">Add Product</button>
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            </div>
                        </form>

                    </div>

                </div>
            </div>
        </div>


        <!--End product modal--> 

        <%@include file="Components/common_modals.jsp" %>
    </body>
</html>

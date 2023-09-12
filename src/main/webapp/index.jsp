<%@page import="com.mycart.helper.*" %>
<%@page import="com.mycart.dao.*" %>
<%@page import="com.mycart.entites.*" %>
<%@page import="java.util.List" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mycart : Home</title>

        <%@include file="Components/Common_css_js.jsp" %>
    </head>
    <body>
        <%@include file="Components/navbar.jsp" %>
        <div class="container fluid">
            <div class="row mt-3 mx-2">
                <%
                   String cat = request.getParameter("category"); 
                   
                   ProductDao pd = new ProductDao(FactoryProvider.getFactory());
                   List<Product> list = null;
                   if(cat == null || cat.trim().equals("all")){
                   
                    list = pd.getAllProducts();
                    
                    }else{
                    int cid = Integer.parseInt(cat.trim());
                    list = pd.getAllProductsBycatId(cid);
                    }
                 
                   CategoryDao cd = new CategoryDao(FactoryProvider.getFactory());
                   List<Category> lc = cd.getCategories();
                %>

                <!--show Categories-->
                <div class="col-md-2">
                    <div class="list-group">
                        <a href="index.jsp?category=all" class="c-link list-group-item list-group-item-action active">
                            All Category
                        </a>


                        <% 
                              for(Category c:lc){
                      
                     
                        %>
                        <a onclick="this" href="index.jsp?category=<%= c.getCatId() %>" class=" c-link list-group-item list-group-item-action"><%= c.getCatTitle() %></a>
                        
                        <%
                       }
                        %>
                    </div>
                </div>

                <!--show Product-->
                <div class="col-md-10">

                    <!--row-->
                    <div class="row mt-4">

                        <!--col: 12-->
                        <div class="col-md-12">
                            <div class="card-columns">
                                <!--traversing products-->
                                <%  
                        for(Product pr: list){
                                %>
                                <div class="card product-card">
                                    <div class="container text-center">
                                        <img class="card-img-top m-2" style="max-height: 250px;max-width: 100%;width: auto;" src="Img/Products/<%= pr.getpPhoto() %>" alt="Card image cap">
                                    </div>
                                    <div class="card-body">
                                        <h5 class="card-title"><%= pr.getpName() %></h5>
                                        <p class="card-text"><%= Stringhelper.get10Words(pr.getpDesc()) %></p>

                                    </div>
                                    <div class="card-footer text-center">
                                        <button class="btn custom-bg text-white" onclick="add_to_card(<%=pr.getpId()%>,'<%= pr.getpName() %>',<%= pr.getPriceAfterApplingDiscount() %>,<%= pr.getpQuantity() %>)">Buy Now</button>
                                        <button class="btn btn-outline-primary">&#8377;<%= pr.getPriceAfterApplingDiscount()%>/-<span class="text-secondapr.getpPrice()ry discount-label">&#8377;<%= pr.getpPrice() %>,<%= pr.getpDiscount()%>%</span></button>

                                    </div>
                                </div>
                                <%
                                }

                               if(list.size()==0){
                               out.println("<h3>No item in this category</h3>");
                                }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    <%@include file="Components/common_modals.jsp" %>
</html>

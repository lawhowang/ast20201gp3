<% String pageTitle = "EShop"; %>
<%@ include file="header.jsp" %>
<div class="container-fluid p-0">
    <div class="nav-menu col-12 col-md-3 col-lg-2 col-xl-2 p-0">
        <div class="brand"><%=pageTitle%></div>
        <i class="fa fa-bars fa-2x toggle-btn" data-toggle="collapse" data-target="#menu-content"></i>
        <div class="menu-list">
            <ul id="menu-content" class="menu-content collapse out">
                <li class="active">
                    <a href="#">
                        <i class="fas fa-tachometer-alt"></i>Dashboard
                    </a>
                </li>

                <li data-toggle="collapse" data-target="#user" class="collapsed">
                    <a href="#"><i class="fas fa-users"></i>Users<span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="user">
                    <li><a href="#!users/user_list">User List</a></li>
                    <li><a href="#">Add User</a></li>
                </ul>


                <li data-toggle="collapse" data-target="#product" class="collapsed">
                    <a href="#"><i class="fas fa-box"></i>Products<span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="product">
                    <li><a href="#">Categories</a></li>
                    <li><a href="#">Products</a></li>
                </ul>


                <li data-toggle="collapse" data-target="#setting" class="collapsed">
                    <a href="#"><i class="fas fa-cogs"></i>Settings<span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="setting">
                    <li><a href="#">Site Configuration</a></li>
                </ul>
            </ul>
        </div>
    </div>

    <div class="col-12 col-md-9 col-lg-10 col-xl-10 ng-scope float-right">
        <div ng-view></div>
    </div>
</div>
<%@ include file="footer.jsp" %>
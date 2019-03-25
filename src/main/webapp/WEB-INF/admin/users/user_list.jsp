<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="row">
    <div class="col-12 col-md-5 col-lg-3 col-xl-3">
        <div class="input-group mb-3 mt-3">
            <% if ((boolean)request.getAttribute("searchMode")) { %>
            <div class="input-group-prepend">
                <button type="button" class="btn btn-danger" ng-click="cancelSearch()"> <span
                        aria-hidden="true">&times;</span></button>
            </div>
            <% } %>
            <input type="text" class="form-control" placeholder="Search by Username" aria-label="Username"
                aria-describedby="basic-addon2" ng-model="search.username" ng-init="search.username='${searchWord}'">
            <div class="input-group-append">
                <button type="button" class="btn btn-primary" ng-click="searchByUsername(search)">Search</button>
            </div>
        </div>
    </div>
    <div></div>
</div>

<table class="table">
    <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Username</th>
            <th scope="col">Last Login Date</th>
            <th scope="col"></th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td class="align-middle">${user.id}</td>
                <td class="align-middle">${user.username}</td>
                <td class="align-middle">${user.last_login_date}</td>
                <td class="align-middle text-right"><button type="button" class="btn btn-outline-primary">View/Edit</button>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

<div>
    <ul class="pagination justify-content-center">
        <%
        int currPage = (int)request.getAttribute("currPage");
        int maxPages = (int)request.getAttribute("maxPages");

        int lowerBound = currPage - 5;
        int upperBound = currPage + 5;

        if (lowerBound < 1) lowerBound = 1;
        if (upperBound > maxPages) upperBound = maxPages;

        if (currPage - lowerBound < 5) {
            upperBound = upperBound + 5 > maxPages ? maxPages : upperBound + 5;
        }

        if (upperBound - currPage < 5) {
            lowerBound = lowerBound - 5 < 1 ? 1 : lowerBound - 5;
        }
        %>

        <li class="page-item <%= (lowerBound < currPage) ? "" : "disabled" %>">
            <a class="page-link" href="#!users/user_list/<%= currPage - 1 %>" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>

        <% for (int i = lowerBound; i < currPage; i++) { %>
        <li class="page-item"><a class="page-link" href="#!users/user_list/<%= i %>"><%= i %></a></li>
        <% } %>
        <li class="page-item active"><a class="page-link" href=""><%= currPage %></a></li>
        <% for (int i = currPage + 1; i <= upperBound; i++) { %>
        <li class="page-item"><a class="page-link" href="#!users/user_list/<%= i %>"><%= i %></a></li>
        <% } %>

        <li class="page-item <%= (upperBound > currPage) ? "" : "disabled" %>">
            <a class="page-link" href="#!users/user_list/<%= currPage + 1 %>" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</div>
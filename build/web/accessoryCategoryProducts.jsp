<%-- 
    Document   : accessoryCategories
    Created on : Oct 9, 2019, 4:44:32 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/accessoryCategoryListDocumentFragment.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Accessory Categories Page</title>
    </head>
    <body>
        <%@include file="component/dashboardTopbar.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="accessoryCategories"/>
            <%@include file="component/dashboardSidebar.jsp" %>
            <section class="dashboard-main">
                <h1>Accessory Category List & Keywords To Crawl Data</h1>
                <table>
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Domain</th>
                            <th>Title</th>
                            <th>Price</th>
                            <th>Image</th>
                            <th>URL</th>
                            <th>Click Count</th>
                            <th>Delete</th>
                        </tr>
                    </thead>
                    <tbody class="tbody">

                    </tbody>
                </table>
                <div class="pagination">
                    <div></div>
                </div>
                <button type="button" onclick="location.replace('accessoryCategories.jsp');">Back</button>
            </section>
        </main>
        <script type="text/javascript">
            const pageParam = ${param.page == null || param.page == "" ? 0 : param.page};
            const categoryParam = '${param.category == null ? "" : param.category}';

            function loadAccessoryList(category, page = 0) {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        parser = new DOMParser();
                        xmlDoc = parser.parseFromString(this.responseText, "text/xml");
                        let accessories = JSON.parse(sessionStorage.getItem(category));
                        if (!Array.isArray(accessories)) {
                            accessories = [];
                        }
                        const newAccessories = [];
                        xmlDoc.childNodes[0].childNodes.forEach(accessoryNode => {
                            const accessory = {};
                            accessoryNode.childNodes.forEach(node => {
                                accessory[node.tagName] = node.textContent;
                            })
                            newAccessories.push(accessory);
                        });
                        newAccessories.forEach((accessory, i) => {
                            if (accessory && accessory.domain && accessory.category) {
                                accessories[page * 20 + i] = accessory;
                            }
                        });
                        sessionStorage.setItem(category, JSON.stringify(accessories));
                        if (newAccessories.length > 0) {
                            render(newAccessories);
                        } else {

                        }
                    }
                };
                xhttp.open("GET", "MainServlet?action=GetAccessoryListXMLString&category=${param.category}&page=${param.page == null || param.page == "" ? 0 : param.page}", true);
                xhttp.send();
            }

            function countAccessory() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        parser = new DOMParser();
                        sessionStorage.setItem(categoryParam + "Count", this.responseText);
                        renderPagination(Number(this.responseText));
                    }
                };
                xhttp.open("GET", "MainServlet?action=CountAccessory&category=${param.category}");
                xhttp.send();
            }
            
            function deleteAccessory(domain, category, title) {
                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        sessionStorage.removeItem(category);
                        loadAccessoryList(categoryParam, pageParam);
                        countAccessory(categoryParam);
//                        const accessories = JSON.parse(sessionStorage.getItem(category));
//                        const updatedAccessories = accessories.map((accessory, i) => accessory.domain === domain && accessory.category === category && accessory.title === title ? console.log(i, "click", this.responseText) ||
//                                    Object.assign({}, accessory, {clickCount: this.responseText})
//                                    : accessory);
//                                    console.log(updatedAccessories)
//                                    
//                        sessionStorage.setItem(category, JSON.stringify(updatedAccessories));
                    }
                };
                xhttp.open("POST", "MainServlet?action=DeleteAccessory&domain=" + domain + "&category=" + category + "&title=" + title, true);
                xhttp.send();
            }

            function render(accessoryList) {
                const tbodyInnerHTML = accessoryList.map((accessory, i) => accessory ? `
                    <tr>
                        <td>` + (++i) + `</td>
                        <td><a href="` + accessory.domain + `" target="_blank">` + accessory.domain + `</a></td>
                        <td>` + accessory.title + `</td>
                        <td>` + Number(accessory.price).toLocaleString() + `</td>
                        <td><img src="` + accessory.image + `" width="60" height="60"/></td>
                        <td><a href="` + accessory.url + `" target="_blank"/>` + accessory.url + `</a>
                        <td>` + Number(accessory.clickCount).toLocaleString() + `</td>
                        <td>
                            <button type="button" onclick="deleteAccessory('` + accessory.domain + `', '` + accessory.category + `', '` + accessory.title + `')">Delete</button>
                        </td>
                    </tr>
                ` : "").join('');
                document.querySelector('.tbody').innerHTML = tbodyInnerHTML;
            }

            function renderPagination(accessoryCount) {
                let paginationInnerHTML = "";

                const time = Math.ceil(accessoryCount / 20);

                for (let i = 0; i < time; i++) {
                    paginationInnerHTML += `<button onclick='location.href = "accessoryCategoryProducts.jsp?category=${param.category}&page=` + i + `"' class='` + (i === pageParam ? `active` : ``) + `'>` + (i + 1) + `</button>`;
                }
                document.querySelector(".pagination").innerHTML = paginationInnerHTML;
            }

            const accessoryList = JSON.parse(sessionStorage.getItem(categoryParam));

            if (!accessoryList) {
                loadAccessoryList(categoryParam, pageParam);
            } else {
                const currentAccessoryList = accessoryList.slice(pageParam * 20, pageParam * 20 + 20);
                if (currentAccessoryList.length === 0) {
                    loadAccessoryList(categoryParam, pageParam);
                } else {
                    render(currentAccessoryList);
                }
            }

            const accessoryCount = sessionStorage.getItem(categoryParam + "Count");
            if (!accessoryCount || !accessoryCount.match(/^\d+$/)) {
                countAccessory(categoryParam);
            } else {
                renderPagination(Number(accessoryCount));
            }
        </script>
    </body>
</html>

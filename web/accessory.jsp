<%-- 
    Document   : admins
    Created on : Oct 9, 2019, 4:44:32 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/categoryListDocumentFragment.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Macbook Page</title>
    </head>
    <body>
        <%@include file="component/homeTopbar.jsp" %>
        <c:set var="homeNavbarActiveItem" value="accessory"/>
        <%@include file="component/homeNavbar.jsp" %>
        <main class="home-body">          
            <section class="home-main">
                Loading!
            </section>
            <div class="pagination">
                <div></div>
            </div>
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

            function clickAccessory(domain, category, title) {
                const xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        const accessories = JSON.parse(sessionStorage.getItem(category));
                        const updatedAccessories = accessories.map((accessory, i) => accessory.domain === domain && accessory.category === category && accessory.title === title ? console.log(i, "click", this.responseText) ||
                                    Object.assign({}, accessory, {clickCount: this.responseText})
                                    : accessory);
                                    console.log(updatedAccessories)
                                    
                        sessionStorage.setItem(category, JSON.stringify(updatedAccessories));
                    }
                };
                xhttp.open("POST", "MainServlet?action=ClickAccessory&domain=" + domain + "&category=" + category + "&title=" + title, true);
                xhttp.send();
            }

            function render(accessoryList) {
                const homeMainInnerHTML = accessoryList.map(accessory => accessory ? `
                    <div class="home-main-item">
                        <img src="` + accessory.image + `" height="280"/>
                        <p class="home-main-item-title">` + accessory.title + `</p>
                        <p class="home-main-item-description">
                            Shop: <a href="` + accessory.domain + `" target="_blank">` + accessory.domain + `</a>
                        </p>
                        <p class="home-main-item-price">` + Number(accessory.price).toLocaleString() + ` VND</p>
                        <button type="button" onclick="window.open('` + accessory.url + `', '_blank').focus(); clickAccessory('` + accessory.domain + `', '` + accessory.category + `', '` + accessory.title + `');">Go to shop</button>
                    </div>
                ` : "").join('');
                document.querySelector('.home-main').innerHTML = homeMainInnerHTML;
            }

            function renderPagination(accessoryCount) {
                let paginationInnerHTML = "";

                const time = Math.ceil(accessoryCount / 20);

                for (let i = 0; i < time; i++) {
                    paginationInnerHTML += `<button onclick='location.href = "accessory.jsp?category=${param.category}&page=` + i + `"' class='` + (i === pageParam ? `active` : ``) + `'>` + (i + 1) + `</button>`;
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

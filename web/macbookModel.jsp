<%-- 
    Document   : admins
    Created on : Oct 9, 2019, 4:44:32 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/categoryListDocumentFragment.jsp" %>

<c:set var="modelID" value="${param.modelID}" />
<c:if test="${empty modelID}">
    <c:redirect url="macbook.jsp"/>
</c:if>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Macbook Page</title>
    </head>
    <body>
        <%@include file="component/homeTopbar.jsp" %>
        <c:set var="homeNavbarActiveItem" value="macbook"/>
        <%@include file="component/homeNavbar.jsp" %>
        <main class="home-body">
            <div class="home-header">
                <div style="display: flex; align-items: center;">
                    <h1 style="margin: 0; flex: 1"></h1>
                    <img src="" width="75" height="75"/>
                </div>

            </div>
            <section class="home-main"></section>
        </main>
        <script type="text/javascript">
            const modelID = '${param.modelID == null ? "" : param.modelID}';

            function loadMacbookModel() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        parser = new DOMParser();
                        xmlDoc = parser.parseFromString(this.responseText, "text/xml");
                        console.log(xmlDoc);
                        const macbookModel = {};
                        xmlDoc.childNodes[0].childNodes.forEach(node => {
                            macbookModel[node.tagName] = node.textContent;
                            if (node.tagName === "macbookList") {
                                const macbookList = [];
                                node.childNodes.forEach(macbookNode => {
                                    const macbook = {};
                                    macbookNode.childNodes.forEach(n => {
                                        macbook[n.tagName] = n.textContent;
                                    })
                                    macbookList.push(macbook);
                                })
                                macbookModel.macbookList = macbookList;
                            }
                        });
                        console.log(macbookModel);
                        sessionStorage.setItem(modelID, JSON.stringify(macbookModel));
                        render(macbookModel);
                    }
                };
                xhttp.open("GET", "MainServlet?action=GetMacbookModelXMLString&modelID=${param.modelID == null ? "" : param.modelID}");
                xhttp.send();
            }

            function render(macbookModel) {
                const homeHeaderInnerHTML = `
                    <div style="display: flex; align-items: center;">
                        <h1 style="margin: 0; flex: 1">Macbook ` +
                        macbookModel.type.charAt(0).toUpperCase() + macbookModel.type.slice(1) + ` ` +
                        macbookModel.year + ` ` +
                        Number(macbookModel.screenSize) + ` Inch SSD ` +
                        macbookModel.ssd + `GB` +
                        (macbookModel.touchbar === "true" ? ` Touchbar` : ``) + `
                        </h1>
                        <img src="` + macbookModel.thumbnail + `" width="75" height="75"/>
                    </div>
                `;
                document.querySelector('.home-header').innerHTML = homeHeaderInnerHTML;

                const homeMainInnerHTML = macbookModel.macbookList.map(macbook => `
                    <div class="home-main-item">
                        <img src="` + macbook.image + `" height="280"/>
                        <p class="home-main-item-title">` + macbook.title + `</p>
                        <p class="home-main-item-description">
                            Shop: <a href="` + macbook.domain + `" target="_blank">` + macbook.domain + `</a>
                        </p>
                        <p class="home-main-item-price">` + Number(macbook.price).toLocaleString() + ` VND</p>
                        <button type="button" onclick="window.open('` + macbook.url + `', '_blank').focus()">Go to shop</button>
                    </div>
                `).join('');
                document.querySelector('.home-main').innerHTML = homeMainInnerHTML;
            }

            if (modelID) {
                const macbookModel = JSON.parse(sessionStorage.getItem(modelID));

                if (!macbookModel) {
                    loadMacbookModel();
                } else {
                    render(macbookModel)
                }
            } else {
                
            }
        </script>
    </body>
</html>

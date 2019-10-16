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
        <c:set var="homeNavbarActiveItem" value="macbook"/>
        <%@include file="component/homeNavbar.jsp" %>
        <main class="home-body">
            <div class="home-header">
                <h1 class="home-header-title">Filter Macbook Models You Want To Buy</h1>
                <div class="home-header-container">
                    <div class="home-header-item">
                        <label>Macbook type:</label>
                        <select id="selectType" onchange="filter();" value="">
                            <option value="">All type</option>
                            <option value="pro">Macbook Pro</option>
                            <option value="air">Macbook Air</option>
                        </select>
                    </div>
                    <div class="home-header-item">
                        <label>Year:</label>
                        <select id="selectYear" onchange="filter();" value="">
                            <option value="">All year</option>
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                        </select>
                    </div>
                    <div class="home-header-item">
                        <label>SSD Size:</label>
                        <select id="selectSsd" onchange="filter();" value="">
                            <option value="">All SSD size</option>
                            <option value="128">128GB</option>
                            <option value="256">256GB</option>
                            <option value="512">512GB</option>
                            <option value="1000">1TB</option>
                        </select>
                    </div>
                    <div class="home-header-item">
                        <label>Screen Size:</label>
                        <select id="selectScreenSize" onchange="filter();" value="">
                            <option value="">All screen size</option>
                            <option value="13">13 inch</option>
                            <option value="15">15 inch</option>
                        </select>
                    </div>
                    <div class="home-header-item">
                        <label>Touchbar:</label>
                        <select id="selectTouchbar" onchange="filter();" value="">
                            <option value="">All options</option>
                            <option value="true">Touchbar</option>
                            <option value="false">No touchbar</option>
                        </select>
                    </div>
                </div>
            </div>
            <section class="home-main">
                <c:if test="${not empty filteredMacbookModelListDocument}">
                    <x:forEach var="macbookModel" select="$filteredMacbookModelListDocument/macbookModelList/macbookModel" varStatus="counter">
                        <div class="home-main-item">
                            <img src="<x:out select="$macbookModel/thumbnail"/>" height="280"/>
                            <p class="home-main-item-title">
                                Macbook <x:out select="concat(translate(substring($macbookModel/type,1,1), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'),
                                       substring($macbookModel/type, 2),
                                       substring(' ', 1 div not(position()=last()))
                                       )"/> <x:out select="$macbookModel/year"/> <x:out select="substring-before($macbookModel/screenSize, '.')"/> Inch SSD <x:out select="$macbookModel/ssd"/>GB <x:if select="contains($macbookModel/touchbar, 'true')">Touchbar</x:if>
                                </p>
                                <button type="button" onclick="location.href = 'macbookModel.jsp?modelID=<x:out select="$macbookModel/modelID"/>'">Compare price</button>
                        </div>
                    </x:forEach>
                </c:if>
            </section>
        </main>
        <script type="text/javascript">
            function loadMacbookModelList() {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (this.readyState == 4 && this.status == 200) {
                        parser = new DOMParser();
                        xmlDoc = parser.parseFromString(this.responseText, "text/xml");

                        const newMacbookModels = [];

                        xmlDoc.childNodes[0].childNodes.forEach(macbookModelNode => {
                            const macbookModel = {};
                            macbookModelNode.childNodes.forEach(node => {
                                macbookModel[node.tagName] = node.textContent;
                            })
                            newMacbookModels.push(macbookModel);
                        });

                        sessionStorage.setItem("macbookModelList", JSON.stringify(newMacbookModels));
                        if (newMacbookModels.length > 0) {
                            render(newMacbookModels);
                        }
                    }
                };
                xhttp.open("GET", "MainServlet?action=GetMacbookModelListXMLString");
                xhttp.send();
            }

            function render(macbookModelList) {
                const homeMainInnerHTML = macbookModelList.map(macbookModel => `
                    <div class="home-main-item">
                        <img src="` + macbookModel.thumbnail + `" height="280"/>
                        <p class="home-main-item-title">
                            Macbook ` +
                            macbookModel.type.charAt(0).toUpperCase() + macbookModel.type.slice(1) + ` ` +
                            macbookModel.year + ` ` +
                            Number(macbookModel.screenSize) + ` Inch SSD ` +
                            macbookModel.ssd + `GB` +
                            (macbookModel.touchbar === "true" ? ` Touchbar` : ``) + `
                        </p>
                        <button type="button" onclick="location.href = 'macbookModel.jsp?modelID=` + macbookModel.modelID + `'">Compare price</button>
                    </div>
                `).join('');
                document.querySelector('.home-main').innerHTML = homeMainInnerHTML;
            }

            function filter() {
                const type = document.getElementById("selectType").value;
                const year = document.getElementById("selectYear").value;
                const ssd = document.getElementById("selectSsd").value;
                const screenSize = document.getElementById("selectScreenSize").value;
                const touchbar = document.getElementById("selectTouchbar").value;

                sessionStorage.setItem("selectType", type);
                sessionStorage.setItem("selectYear", year);
                sessionStorage.setItem("selectSsd", ssd);
                sessionStorage.setItem("selectScreenSize", screenSize);
                sessionStorage.setItem("selectTouchbar", touchbar);

                const macbookModelList = JSON.parse(sessionStorage.getItem("macbookModelList"));

                if (!macbookModelList) {
                    loadMacbookModelList();
                } else {
                    render(macbookModelList.filter(macbookModel =>
                        macbookModel.type.includes(type)
                                && macbookModel.year.toString().includes(year)
                                && macbookModel.ssd.toString().includes(ssd)
                                && Number(macbookModel.screenSize).toString().includes(screenSize)
                                && macbookModel.touchbar.toString().includes(touchbar)
                    ));
                }
            }

            const macbookModelList = JSON.parse(sessionStorage.getItem("macbookModelList"));

            document.getElementById("selectType").value = sessionStorage.getItem("selectType") == null ? "" : sessionStorage.getItem("selectType");
            document.getElementById("selectYear").value = sessionStorage.getItem("selectYear") == null ? "" : sessionStorage.getItem("selectYear");
            document.getElementById("selectSsd").value = sessionStorage.getItem("selectSsd") == null ? "" : sessionStorage.getItem("selectSsd");
            document.getElementById("selectScreenSize").value = sessionStorage.getItem("selectScreenSize") == null ? "" : sessionStorage.getItem("selectScreenSize");
            document.getElementById("selectTouchbar").value = sessionStorage.getItem("selectTouchbar") == null ? "" : sessionStorage.getItem("selectTouchbar");

            if (!macbookModelList) {
                loadMacbookModelList();
            } else {
                filter();
            }
        </script>
    </body>
</html>

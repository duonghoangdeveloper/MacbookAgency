<%-- 
    Document   : macbookModels
    Created on : Oct 9, 2019, 4:44:32 PM
    Author     : Duong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>

<%@include file="fragment/authFragment.jsp" %>
<%@include file="fragment/macbookModelListDocumentFragment.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/global.css" type="text/css"/>
        <title>Macbook Models Page</title>
    </head>
    <body>
        <%@include file="component/dashboardTopbar.jsp" %>
        <main class="dashboard-body">
            <c:set var="dashboardAsideActiveItem" value="macbookModels"/>
            <%@include file="component/dashboardSidebar.jsp" %>
            <section class="dashboard-main">
                <h1>Model ID: ${param.modelID}</h1>
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
                <button type="button" onclick="location.replace('macbookModels.jsp');">Back</button>
                <!--                <form action="MainServlet" method="POST">
                                    <input type="submit" name="action" value="Crawl"/>
                                </form>-->
            </section>
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

                const tbodyInnerHTML = macbookModel.macbookList.map((macbook, i) => `
                    <tr>
                        <td>` + (++i) + `</td>
                        <td><a href="` + macbook.domain + `" target="_blank">` + macbook.domain + `</a></td>
                        <td>` + macbook.title + `</td>
                        <td>` +  Number(macbook.price).toLocaleString() + `</td>
                        <td><img src="` + macbook.image +`" width="60" height="60"/></td>
                        <td><a href="` + macbook.url +`" target="_blank"/>` + macbook.url +`</a>
                        <td>` +  Number(macbook.clickCount).toLocaleString() + `</td>
                        <td>
                            <form action="MainServlet" method="POST">
                                <button type="submit">Delete</button>
                                <input type="hidden" name="action" value="DeleteMacbook"/>
                                <input type="hidden" name="txtModelID" value="` + macbook.modelID + `"/>
                                <input type="hidden" name="txtCategory" value="` + macbook.category + `"/>
                                <input type="hidden" name="txtDomain" value="` + macbook.domain + `"/>
                            </form>
                        </td>
                    </tr>
                `).join('');
                document.querySelector('.tbody').innerHTML = tbodyInnerHTML;
            }

            if (modelID) {
                const macbookModel = JSON.parse(sessionStorage.getItem(modelID));

                if (!macbookModel) {
                    console.log(1);
                    loadMacbookModel();
                } else {
                    render(macbookModel)
                }
            } else {
                
            }
        </script>
    </body>
</html>

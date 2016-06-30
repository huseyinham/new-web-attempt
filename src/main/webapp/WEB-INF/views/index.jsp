<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>SmiteTroll</title>
        <link rel="stylesheet" href="<c:url value="resources/styles/css/style.css" />">
        <link rel="stylesheet" href="<c:url value="resources/styles/font-awesome/css/font-awesome.css" />">
    </head>
    <body>
        <b>
            <h1>SmiteTroll</h1>
        </b>
        <b>
            <p>Your Randomly Generated Build:</p>
        </b>
        <div class="box"></div>
        <div class="line"></div>

        <div class="god">
            <p class="godname">${godName}</p>
            <div class="underline"></div>
        </div>

         <div class = "refresh">
            <form action="index" method="GET">
                <button class="rfbutton" type="submit"><span>Refresh</span></button>
            </form>
        </div>

        <img class="god_image" src="${godImage}"/>
        
        <div class="item_images">
            <c:set var="itemCount" value="0" scope="page"/>
            <c:forEach items="${items}" var="item">
                <form action="itemReroll" method="POST" class="item_form_buttons">
                    <input class="item_image" type="image" name="rerollIndex" src="${item.itemImage}" value="${itemCount}" />
                </form>
                <c:set var="itemCount" value="${itemCount + 1}" scope="page"/>
            </c:forEach>
        </div>

        <div class="items">
            <div class="itemlist">
                <ul>
                    <c:forEach items="${items}" var="item">
                        <li><c:out value="${item.itemName}" /></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        
        <form action="godReroll" method="POST" class="godReroll">
            <button class="gbutton" type="submit">
                <span>Reroll God</span>
            </button>
        </form>
        
        <div class="relic_images">
            <c:set var="relicCount" value="0" scope="page"/>
            <c:forEach items="${relics}" var="relic">
                <form class="relicButton" action="relicReroll" method="POST">
                    <input class="relic_image" type="image" name="rerollIndex" src="${relic.relicImage}" value="${relicCount}" />
                    <c:set var="relicCount" value="${relicCount + 1}" scope="page"/>
                </form>
            </c:forEach>
        </div>

        <div class="relics">
            <div class="reliclist">
                <ul>
                    <c:forEach items="${relics}" var="relic">
                        <li><c:out value="${relic.relicName}" /></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </body>
</html>
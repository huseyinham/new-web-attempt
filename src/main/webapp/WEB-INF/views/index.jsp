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
            <c:forEach items="${items}" var="item">
                <img class="item_image" src="${item.itemImage}"/>
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

            <div class="itembuttons">
                <form action="itemReroll" method="POST" class="item_form_buttons">
                    <input type="hidden" name="rerollIndex" value="0" />
                    <button class="ibutton" type="submit"/>
                        <span>Reroll</span>
                    </button>
                </form>

                <form action="itemReroll" method="POST" class="item_form_buttons">
                    <input type="hidden" name="rerollIndex" value="1" />
                    <button class="ibutton" type="submit"/>
                        <span>Reroll</span>
                    </button>
                </form>

                <form action="itemReroll" method="POST" class="item_form_buttons">
                    <input type="hidden" name="rerollIndex" value="2" />
                    <button class="ibutton" type="submit"/>
                        <span>Reroll</span>
                    </button>
                </form>

                <form action="itemReroll" method="POST" class="item_form_buttons">
                    <input type="hidden" name="rerollIndex" value="3" />
                    <button class="ibutton" type="submit"/>
                        <span>Reroll</span>
                    </button>
                </form>

                <form action="itemReroll" method="POST" class="item_form_buttons">
                    <input type="hidden" name="rerollIndex" value="4" />
                    <button class="ibutton" type="submit"/>
                        <span>Reroll</span>
                    </button>
                </form>

                <form action="itemReroll" method="POST" class="item_form_buttons">
                    <input type="hidden" name="rerollIndex" value="5" />
                    <button class="ibutton" type="submit"/>
                        <span>Reroll</span>
                    </button>
                </form>
            </div>
        </div>
        
        <form action="godReroll" method="POST" class="godReroll">
            <button class="gbutton" type="submit">
                <span>Reroll God</span>
            </button>
        </form>
        
        <div class="relic_images">
            <c:forEach items="${relics}" var="relic">
                <img class="relic_image" src="${relic.relicImage}"/>
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

            <div class="relicbuttons">
                <form class="relicButton" action="relicReroll" method="POST">
                    <input type="hidden" name="rerollIndex" value="0" />
                    <button class ="rbutton" type="submit"/>
                        <span>Reroll Relic One</span>
                    </button>
                </form>

                <form class="relicButton" action="relicReroll" method="POST">
                    <input type="hidden" name="rerollIndex" value="1" />
                    <button class="rbutton" type="submit"/>
                        <span>Reroll Relic Two</span>
                    </button>
                </form>
            </div>
        </div>
    </body>
</html>
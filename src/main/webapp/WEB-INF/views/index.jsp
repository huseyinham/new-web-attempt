<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/src/main/webapp/WEB-INF/views/style.css" >
    <title>SmiteTroll</title>
    
    <style>
    h1 {
        font-size: 40px;
        font-family: "Times New Roman", Times, serif;
    }

    p {
        font-size: 32px;
        font-family: "Times New Roman", Times, serif;
    }

    .godname {
        font-size: 20px;
        font-family: "Times New Roman", Times, serif;
    }

    .itemlist {
    width: 260px;
        display: inline-block;
        font-size: 20px;
    }

     .itembuttons {
        width: auto;
        vertical-align: top;
        margin: 10px 35px;
        display: inline-block;
     }

    .reliclist {
    width: 165px;
        display: inline-block;
        font-size: 20px;
    }

    .relicbuttons {
        width: auto;
        display: inline-block;
        height: 70px;
        vertical-align: top;
        margin: 15px 25px;
    }

    .relicButton {
        display: inline-block;
        margin: 2px;
        margin-top: 20px;
    }

    .gbutton {
        width: 200px;
        padding: 15px 0px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
        background-color: white;
        color: black;
        background: #e0e0e0;
        border: 2px solid #bdbdbd;
    }

    button.gbutton span:after {
        content: '>>';
        padding-left: 10px;
        opacity: 0;
    }
    button.gbutton:hover span:after {
        opacity: 1;
            transition: all 1s;
    }

    .ibutton {
    width: 100px;
        background-color: #4CAF50; /* Green */
        border: none;
        color: white;
        padding: 5px 0px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        cursor: pointer;
        background-color: white;
        color: black;
        border: 2px solid #4CAF50;
    }

    .rbutton {
        width: 160px;
        background-color: #4CAF50; /* Green */
        border: none;
        color: white;
        padding: 5px 5px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        cursor: pointer;
        background-color: white;
        color: black;
        border: 2px solid #4CAF50;
    }

    li {
        margin: 20px;
    }

    form {
        margin: 12px;
    }


    </style>
    <link rel="stylesheet" href="/src/main/webapp/WEB-INF/resources/font-awesome-4.6.3/css/font-awesome.css">

</head>
<body>

    <b><h1>SmiteTroll</h1></b>
    <b><p>Your Randomly Generated Build:</p></b>

<div class = "god">
    <p class="godname">${godName}</p>
    <form action="godReroll" method="POST">
    <button class="gbutton" type="submit" value="Reroll God"><span>Reroll God</span></button>
    </form>
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
        <form action="itemOneReroll" method="POST">
        <input class="ibutton" type="submit" value="Reroll" />
        </form>

        <form action="itemTwoReroll" method="POST">
        <input class="ibutton" type="submit" value="Reroll" />
        </form>

        <form action="itemThreeReroll" method="POST">
        <input class="ibutton" type="submit" value="Reroll" />
        </form>

        <form action="itemFourReroll" method="POST">
        <input class="ibutton" type="submit" value="Reroll" />
        </form>

        <form action="itemFiveReroll" method="POST">
        <input class="ibutton" type="submit" value="Reroll" />
        </form>

        <form action="itemSixReroll" method="POST">
        <input class="ibutton" type="submit" value="Reroll" />
        </form>
    </div>
</div>

<div class="relics">
    <div class="reliclist">
        <ul>
        <c:forEach items="${relics}" var="relic">
          <li><c:out value="${relic.relicName}" />  </li>
        </c:forEach>
        </ul>
    </div>

    <div class="relicbuttons">
        <form class="relicButton" action="relicOneReroll" method="POST">
        <input class ="rbutton" type="submit" value="Reroll First Relic" />
        </form>

        <form class="relicButton" action="relicTwoReroll" method="POST">
        <input class="rbutton" type="submit" value="Reroll Second Relic" />
        </form>
    </div>
</div>

</body>
</html>
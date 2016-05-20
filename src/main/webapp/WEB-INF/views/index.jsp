<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/style.css" >
    <title>SmiteTroll</title>
</head>
<body>

<b><h1>SmiteTroll</h1></b>
<b><p>Your Randomly Generated Build:</p></b>



${godName}

<ul>
<c:forEach items="${items}" var="item">
  <li><c:out value="${item.itemName}" /></li>
</c:forEach>
</ul>

<ul>
<c:forEach items="${relics}" var="relic">
  <li><c:out value="${relic.relicName}" />  </li>
</c:forEach>
</ul>

<form id = "button1" action="godReroll" method="POST">
<input type="submit" value="Reroll God" />
</form>

<form id = "button2" action="itemOneReroll" method="POST">
<input type="submit" value="Reroll First Item" />
</form>

<form id = "button3" action="itemTwoReroll" method="POST">
<input type="submit" value="Reroll Second Item" />
</form>

<form id = "button4" action="itemThreeReroll" method="POST">
<input type="submit" value="Reroll Third Item" />
</form>

<form id = "button5" action="itemFourReroll" method="POST">
<input type="submit" value="Reroll Fourth Item" />
</form>

<form id = "button6" action="itemFiveReroll" method="POST">
<input type="submit" value="Reroll Fifth Item" />
</form>

<form id = "button7" action="itemSixReroll" method="POST">
<input type="submit" value="Reroll Sixth Item" />
</form>

<form id = "button8" action="relicOneReroll" method="POST">
<input type="submit" value="Reroll First Relic" />
</form>

<form id = "button9" action="relicTwoReroll" method="POST">
<input type="submit" value="Reroll Second Relic" />
</form>

</body>
</html>
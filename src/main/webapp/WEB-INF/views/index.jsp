<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
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
  <li><c:out value="${relic.relicName}" /></li>
</c:forEach>
</ul>

<form action="godReroll" method="POST">
<input type="submit" value="Reroll God" />
</form>

<form action="itemOneReroll" method="POST">
<input type="submit" value="Reroll First Item" />
</form>

<form action="itemTwoReroll" method="POST">
<input type="submit" value="Reroll Second Item" />
</form>

<form action="itemThreeReroll" method="POST">
<input type="submit" value="Reroll Third Item" />
</form>

<form action="itemFourReroll" method="POST">
<input type="submit" value="Reroll Fourth Item" />
</form>

<form action="itemFiveReroll" method="POST">
<input type="submit" value="Reroll Fifth Item" />
</form>

<form action="itemSixReroll" method="POST">
<input type="submit" value="Reroll Sixth Item" />
</form>

<form action="relicOneReroll" method="POST">
<input type="submit" value="Reroll First Relic" />
</form>

<form action="relicTwoReroll" method="POST">
<input type="submit" value="Reroll Second Relic" />
</form>

</body>
</html>
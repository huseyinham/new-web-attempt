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

</body>
</html>
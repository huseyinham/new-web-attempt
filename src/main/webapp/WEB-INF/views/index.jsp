<!DOCTYPE html>
<html>
<head>
    <title>Page Title</title>
</head>
<body>

<h1>My First Heading</h1>
<p>My first paragraph.</p>



${godName}
${itemNames}

<c:forEach items="${itemNames}" var="item">
   <li><c:out value="${item.item_name}" /></li>
</c:forEach>



</body>
</html>
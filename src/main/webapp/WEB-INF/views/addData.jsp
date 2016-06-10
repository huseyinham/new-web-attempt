<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SmiteTroll Data Entry</title>
    <div class="godData">
            <form action="addGodData" method="POST">
                <input type="text" name="godName"/>
                <input type="text" name="godType"/>
                <button class="gAdderButton" type="submit"/><span>Add God</span></button>
            </form>

            <form action="removeGodData" method="POST">
                <input type="text" name="godName"/>
                <button class="gRemoveButton" type="submit"/><span>Remove God</span></button>
            </form>
    </div>
</head>
<body>
</body>
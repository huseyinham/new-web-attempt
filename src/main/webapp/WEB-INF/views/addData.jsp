<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SmiteTroll Data Entry</title>


</head>
<body>
    <div class="godData">
        <form action="addGodData" method="POST">
            <input type="text" name="godName"/>
            <input type="text" name="godType"/>
            <input type="text" name="godImage"/>
            <button class="gAdderButton" type="submit"/><span>Add God</span></button>
        </form>

        <form action="removeGodData" method="POST">
            <input type="text" name="godName"/>
            <button class="gRemoveButton" type="submit"/><span>Remove God</span></button>
        </form>
    </div>

    <div class="itemData">
        <form action="addItemData" method="POST">
            <input type="text" name="itemName"/>
            <input type="text" name="itemType"/>
            <input type="text" name="itemImage"/>
            <button class="iAdderButton" type="submit"/><span>Add Item</span></button>
        </form>

        <form action="removeItemData" method="POST">
            <input type="text" name="itemName"/>
            <button class="iRemoveButton" type="submit"/><span>Remove Item</span></button>
        </form>
    </div>

    <div class="relicData">
        <form action="addRelicData" method="POST">
            <input type="text" name="relicName"/>
            <input type="text" name="relicImage"/>
            <button class="rAdderButton" type="submit"/><span>Add Relic</span></button>
        </form>

        <form action="removeRelicData" method="POST">
            <input type="text" name="relicName"/>
            <button class="rRemoveButton" type="submit"/><span>Remove Relic</span></button>
        </form>
    </div>

    <c:if test="${not empty error}">
    <div class="error">
        Error: ${error}
    </div>
</c:if>
</body>
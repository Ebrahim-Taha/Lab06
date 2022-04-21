<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html>
    
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping List</title>
        
    </head>
    
    <body>
        
        <h1>Shopping List</h1>
        Hello, ${username} <a href="ShoppingList?action=logout">Logout</a>
        <h2>List</h2>
        
        <form action="" method="post">
            
            <label>Add Item:</label>
            <input type="text" name="additem">
            <input type="submit" name="add" value="Add">
            <input type="hidden" name="action" value="add">
            
        </form>
        
        <form action="" method="post">
            
            <ul>
                <c:forEach items="${shoppinglist}" var="item">
                    <input type="radio" name="items" value="${item}">${item}<br>
                </c:forEach>
            </ul>
                
            <input type="submit" value="Delete">
            <input type="hidden" name="action" value="delete">
            
        </form>
        
    </body>
    
</html>

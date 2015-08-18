<html>
    <head>Fruit Picker</head>
    <body>
        <form action="/favorite_fruit" method="POST">
            <p>What's your favorite fruit?</p>
            <#list fruits as fruit>
                <p>
                <input type="radio" name="fruit" value="${fruit}">${fruit}</input>
                </p>
            </#list>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
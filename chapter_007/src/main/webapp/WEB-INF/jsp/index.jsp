﻿<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Tracker</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

<div class="container">
    <h2>Введите данные пользователя</h2>
    <form action="/action_page.php">
        <div class="form-group">
            <label for="firstname">Имя:</label>
            <input type="text" class="form-control" id="firstname" title="Введите имя" placeholder="Иван" name="firstname">
        </div>
        <div class="form-group">
            <label for="lastname">Фамилия:</label>
            <input type="text" class="form-control" id="lastname" title="Введите фамилию" placeholder="Иванов" name="lastname">
        </div>
        <div class="form-group">
            <label for="gender">Пол:</label>
            <input type="text" class="form-control" id="gender" title="Введите пол" placeholder="Муж" name="gender">
        </div>
        <div class="form-group">
            <label for="description">Описание:</label>
            <input type="text" class="form-control" id="description" title="Введите описание" placeholder="Просто Иван" name="description">
        </div>
        <button type="submit" class="btn btn-default" id="submit">Отправить</button>
    </form>
</div>

<div class="container">
    <h2>Список пользователей</h2>
    <table class="table" id="table">
        <thead>
        <tr>
            <th>Firstname</th>
            <th>Lastname</th>
            <th>Gender</th>
            <th>Description</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>John</td>
            <td>Doe</td>
            <td>Male</td>
            <td>John Doe</td>
        </tr>
        <tr>
            <td>Mary</td>
            <td>Moe</td>
            <td>Female</td>
            <td>Mary Moe</td>
        </tr>
        <tr>
            <td>July</td>
            <td>Dooley</td>
            <td>Male</td>
            <td>July Dooley</td>
        </tr>
        </tbody>
    </table>
</div>
<script>
    let inputs = ['#firstname', '#lastname', '#gender', '#description'];

    function User(firstname, lastname, gender, description) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.description = description;
    }

    function validate() {
        let valid = true;
        inputs.forEach(input => {
            if($(input).val() == "") {
                alert($(input).attr('title'));
                valid = false;
            }
        });
        return valid;
    }

    function addRow() {
        let newRow = '<tr>\n';
        inputs.forEach(input => newRow += '<td>' + $(input).val() + '</td>\n');
        newRow += '</tr>\n';
        $('#table tr:last').after(newRow);
    }

    $('#submit').click(function(event){
        event.preventDefault();
        if (!validate()) return;
        let user = new User(
            $('#firstname').val(),
            $('#lastname').val(),
            $('#gender').val(),
            $('#description').val()
        );
        $.post(
            "${pageContext.request.contextPath}/ajax",
            JSON.stringify(user),
            function(data, textStatus, jqXHR) {
                addRow();
            }
        );
    });
</script>
</body>
</html>
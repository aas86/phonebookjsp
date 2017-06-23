<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page import="ru.academits.model.Contact" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.academits.PhoneBook" %>
<!DOCTYPE html>
<html lang="">

<head>
    <%
        List<Contact> contactList = PhoneBook.phoneBookService.getAllContacts();
        request.setAttribute("contactList", contactList);
    %>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="css/phonebook.css"/>
    <title>Phone book</title>
</head>


<body>

<div class="delete-dialog"></div>

<div class="alert" title="Нет выбранных контактов"></div>
<div class="content">
    <div class="filter-container">
        <label>Введите текст:
            <input type="text" class="form-control input-sm"/>
        </label>
        <button class="btn btn-primary">Отфильтровать</button>
        <button class="btn btn-primary">Сбросить фильтр</button>
    </div>


    <table class="table table-bordered contact-table">
        <thead>
        <tr>
            <th>
                <label class="select-all-label">
                    <input type="checkbox" title="Выбрать"/>
                </label>
            </th>
            <th>№</th>
            <th>Фамилия</th>
            <th>Имя</th>
            <th>Телефон</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody>
        <%  int number = 0;
            for ( Contact contact: contactList){
                number++;
        %>
             <tr>
                 <td>
                     <label class="select-me-label">
                         <input type="checkbox" class="select-me"/>
                     </label>
                 </td>
                 <td>
                     <% out.println(number);  %>
                 </td>
                 <td>
                     <% out.println(contact.getLastName());  %>
                 </td>
                 <td>
                     <% out.println(contact.getFirstName());  %>
                 </td>
                 <td>
                     <% out.println(contact.getPhone());  %>
                 </td>
                 <td>
                     <button class='btn btn-primary' type='button'>Удалить</button>
                 </td>
             </tr>
        <%}%>
        </tbody>
    </table>

    <button type="button" class="btn btn-primary">Удалить выбранные</button>

    <br>
    <div class="server-error-message-container">
        <span></span>
    </div>
    <form action="/phonebook/add" method="POST">
        <div>
            <label class="form-label">
                <span class="form-field">Фамилия:</span>
                <input type="text" class="form-control input-sm form-input" name="lastName"/>
                <span class="error-message"></span>
            </label>
        </div>
        <div>
            <label class="form-label">
                <span class="form-field">Имя:</span>
                <input type="text" class="form-control input-sm form-input" name="firstName"/>
                <span class="error-message"></span>
            </label>
        </div>
        <div>
            <label class="form-label">
                <span class="form-field">Телефон:</span>
                <input type="number" class="form-control input-sm form-input" name="phone"/>
                <span class="error-message" data-bind="text: phoneError().message, visible: validation"></span>
            </label>
        </div>
        <button type="submit" class="btn btn-primary">Добавить</button>
    </form>

</div>
</body>
</html>
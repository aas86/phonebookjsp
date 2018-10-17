package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by 437-5 on 17.10.2018.
 */
public class DeleteSelectedServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contactParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        //String[] selectedStudentIds = req.getParameterValues("selected");
        System.out.println(contactParams);
        ArrayList<String> arrayList = contactConverter.convertForDeleteChecked(contactParams);
        contactService.deleteSelectedContacts(arrayList);
        resp.sendRedirect("/phonebook");
    }
}

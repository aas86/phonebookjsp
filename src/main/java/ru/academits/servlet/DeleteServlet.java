package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


/**
 * Created by 437-5 on 16.10.2018.
 */
public class DeleteServlet extends HttpServlet {
    private ContactService contactService = PhoneBook.contactService;
    //private ContactConverter contactConverter = PhoneBook.contactConverter;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contactParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        //System.out.println(contactParams);
       /* int id = contactConverter.convertForDelete(contactParams);*/
        int id = Integer.parseInt(contactParams.substring(contactParams.indexOf("=") + 1));
        contactService.deleteContact(id);


        resp.sendRedirect("/phonebook");
    }
}

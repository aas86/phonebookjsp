package ru.academits.servlet;

import ru.academits.PhoneBook;
import ru.academits.coverter.ContactConverter;
import ru.academits.model.Contact;
import ru.academits.service.ContactService;
import ru.academits.service.ContactValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddContactServlet extends HttpServlet {

    private ContactService contactService = PhoneBook.contactService;
    private ContactConverter contactConverter = PhoneBook.contactConverter;


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String contactParams = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Contact contact = contactConverter.convertFormStringParam(contactParams);

        ContactValidation contactValidation = contactService.addContact(contact);
        req.setAttribute("contactValidation", contactValidation);
        req.setAttribute("contactList", contactService.getAllContacts());
        if (contactValidation.isValid()) {
            req.setAttribute("currentContact", new Contact());
        } else {
            req.setAttribute("currentContact", contact);
        }

        req.getRequestDispatcher("phonebook.jsp").forward(req, resp);
    }
}

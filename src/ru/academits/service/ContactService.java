package ru.academits.service;

import org.apache.commons.lang.StringUtils;
import ru.academits.PhoneBook;
import ru.academits.dao.ContactDao;
import ru.academits.model.Contact;
import java.util.List;


public class ContactService {
    private ContactDao contactDao = PhoneBook.contactDao;

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();
        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public ContactValidation validateContact(Contact contact) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);
        if (StringUtils.isEmpty(contact.getFirstName())){
            contactValidation.setValid(false);
            contactValidation.setFirstNameError("Поле Имя должно быть заполнено.");
            return contactValidation;
        }

        if (StringUtils.isEmpty(contact.getLastName())){
            contactValidation.setValid(false);
            contactValidation.setLastNameError("Поле Фамилия должно быть заполнено.");
            return contactValidation;
        }

        if (StringUtils.isEmpty(contact.getPhone())){
            contactValidation.setValid(false);
            contactValidation.setPhoneError("Поле Телефон должно быть заполнено.");
            return contactValidation;
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setGlobalError("Номер телефона не должен дублировать другие номера в телефонной книге.");
            return contactValidation;
        }
        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact);
        if (contactValidation.isValid()) {
            contactDao.add(contact);
        }
        return contactValidation;
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }
}

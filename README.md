# README #

Это проект - телефонная книга, реализованная на следующих технологиях

клиентская часть: html
серверная часть: jsp + servlets + tomcat
БД: оперативная память, уничтожается при выключении проекта
сборка: maven
запуск: томкат

Что умеет делать проет:
отображать сущетсвующие контакты
добавлять новые контакты

Что нужно добавить в проект:
удаление контакта,
удаление всех выбранных контактов,
пометить контактакт как важный,
фильрация контактов,
выгрузка контактов в excel - подсказка используйте библиотеку: apache poi

По желанию:
Можно изменить клиентскую часть, можно поменять библиотеки и архитектуру - главное проект должен работать

Как собрать проект:
нажмите кнопку mvn package
maven собирает артифакт
собранный артифакт будет размещен в директории target phonebookjsp-1.0-SNAPSHOT

Как запустить проект
Run phonebook@tomcat - данная команда собирает проект и запускает томкат
Debug phonebook@tomcat - данная команда собирает проект в режиме дебаг и запускает томкат

Отладка серверной части:
1) запустите проект в режиме дебаг
2) используйте постман

Отладка клиентской части:
1) запускайте проект в chome
2) используйте инструменты разработчика

Реализованные методы API:
GET {host}/phonebook - возращает HTML страницу со списком контактов
POST {host}/add - добавление нового контакта в формате
Тело запроса:
 firstName="Имя"&lastName="Фамилия"&phone="Телефон"

Как должен выглядеть готовый проект:
Проект должен запускатся 1-2 кнопками: package и RUN tomcat

Чтобы проверит, что другой человек сможет запустить проект без проблем - сделайте checkout в отдельную директорию и запустите его.

На что нужно обратить внимание:
Для отображение страницы в браузере
клиент вызывает url /phone, который маппится на StartServlet, затем StartServlet передает управление в phonebook.jsp
  req.getRequestDispatcher("phonebook.jsp").forward(req, resp);
phonebook.jsp отрабатывает и создает html страницу, томкат отправляет ее в браузер

При добавлении нового контакта
клиент нажимает на кнопку добавить
форма отправляет запрос на url /add со всеми перечисленными параметрами (поля, имеющий аттрибут name)
/add маппитря на сервлет AddContactServlet, сервлет отпрабатывает и делает редирект на url /phonebook, который маппится на стартовую страницу,
далее идет сценарий отображения страницы в браузере

таким образом при выполнении действии, мы всегда остаемся на одной странице

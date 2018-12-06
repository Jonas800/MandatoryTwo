# Mandatory Assignment 2 SWC - KEA 2018

A web application that digitalises the system of assigning students to courses.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
  ```
  1. Start out by downloading the project zip to your local machine https://github.com/Jonas800/MandatoryTwo.
  2. Extract the zip file.
  3. Open your favorite IDE(IntelliJ), hit File --> Open, find the extracted project and open it.
  4. Make sure your IDE has the TwoApplication configured as main.
  5. Hit 'Run'.
  ```

## Deployment

Additional information on how to use the application once it's up and running.
```
  1. Once the application is running, go to localhost:8080/
  2. You will automatically be redirected to a login-page.
  3. You now have the following three options.
    3.1 Login as Student - Email: jonas@jonas.dk - Password: 1234
    3.2 Login as Teacher - Email: alex@alex.dk - Password: 1234
    3.3 Login as Admin   - Email: matti@matti.dk - Password: idiot
  4. Depending on what login-type you chose, you will have different options and rights.
     See 'Functions' for more information regarding application functionality and login-types.
```

## Functions

This section will describe the different functionalities in the application and how to use them.

### Student

As a student you will have the following options once you've logged in:

```
  1. On the index page and in the navigation bar, you will be able to search for course names.
  2. If you want to search for specific keywords in a course(fx. class code, exam form etc.),
     you can navigate to 'Advanced Search' and use the template to find your desired course.
  3. Once you've found a course, you can choose to 'Show more' information or 'Join' the course.
     If you choose to join the course, you will be put on a waiting list until an admin approves you.
```

### Teacher

As a teacher you will have the following options once you've logged in:

```
  1. You can redirect to 'Courses' where you will find a view of all existing courses.
    1.1 On the courses page, you can 'Add a new course' - you will be redirected to a new page where you can fill out required information to create a course.
    1.2 If you want to see every course detail, you can hit the 'Show more' option, which will redirect you to a new page.
    1.3 The 'Students' button will redirect you to a new page, where you can see every student assigned to the specific course.
    1.4 'Edit' redirect to a page with a form, where all the known information on the course are already filled out. You can change any field and save.
    1.5 You can choose to 'Delete' a course.
```

### Admin

As an admin you will have the following options once you've logged in:

```
  1. 'Administrators' shows every admin of the system, where you can create new admins and delete existing ones.
  2. 'Students' shows every student, where you can choose to create new students or delete existing ones.
  3. 'Teachers' shows every teacher, where you can create new a new teacher or choose to delete an existing one.
  4. 'Study programmes' shows you a list of every study programme. You can create new programmes or remove existing ones.
  5. 'Waiting List' shows you a list of every course and a 'Pending' button. If you click the 'Pending' button you will be redirected
     to a waiting list for that specific course. Here you can see all pending students and choose to approve them.
```

## Built With

* [Spring](https://spring.io/) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Bootstrap](https://getbootstrap.com/) - Application design

## Authors

* **Jonas Olsen** - [Jonas800](https://github.com/Jonas800)
* **Matthias Skou** - [MatthiasSkou](https://github.com/MatthiasSkou)

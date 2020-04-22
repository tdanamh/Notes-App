## Team "Abc" ##

We are  Dan Brezeanu and Dana Tudor.


Our project is a Web Application called **"Notes App"**. We wanted to create a website where we can mimic a "to do" list in
order to use it in our every-day life. We consider it is a very useful application for those who don't want to log in on 
multiple devices with different accounts for managing all sorts of applications like calendar and synchronise all the accounts
in order to see everything in one place. For some people, like us, it is a headache to remember all the things you have to do
in the nearest future. Wouldn't it be perfect just to sign up, log in and start typing? Maybe not just "to-do" things, 
maybe ... **all your notes**. Everything in one place, accesible from everywhere and **secure**.

We want to develop a secure application. Since our first thoughts about this project, we focus on security. 

Our project is based on Java Spring Boot. We will use Bootstrap and Javascript for front-end.

### Main functionalities ###



**Register now:** 
  
  A page where you have to choose an email and a password and to confirm your password (the last two fields have to be the same).
If the email is available, you will be redirected to the Sign in page where you will be able to see a message of confirmation
(**Account created successfully!**).

![alt text](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/pic1.png "Register now")



**Sign in:**

  The first page of Notes App where you have to type in your email and password. If the fields correspond to the database, you 
will be redirected to Your notes page. Else your Sign in page will refresh and you will be able to see a message of error 
(**Username or password incorrect!**).

![alt text](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/pic2.png "Sign in")



**Your notes page:**

  The main page of Notes App where you have:
  - an empty note with and empty title and an empty note field (**the possibility to create a new note**).
  - all your available notes (**the possibility to delete any specific note**).
  - a left-menu:

      #### Your notes ####
      #### Profile ####
      #### Shortcuts ####
      
  - a dropdown-menu:
  
      #### Settings ####
      #### Log out ####
  

![alt text](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/pic3.png "Notes")



It will be a different project because it will look as simple as possible, but it will be as complex as you could never think.
We will try to use the latest and the fastest technologies. The most important thing is that we will try our best to create a 
robust application, just by thinking ahead at all the possibilities in order to never have any bug and to create the best
user experience.


We may stuck a little in making the site really **secure**. We may not be fully realistic in this regard. It is hard to achieve
security these days.



## Use case diagrams ##

**_Sign in diagram_**

![Sign_in](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/Sign_in.png)


**_Sign up diagram_**

![Sign_up](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/Sign_up.png)


**_Notes app diagram_**

![Notes_app](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/Notes_app.png)



## State transition diagrams ##

**_Index page diagram_**

![index_page](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/IndexPage.png)

**_Notes page diagram_**

![notes_page](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/NotesPage.png)

**_Admin Login page diagram_**

![admin_login_page](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/AdminLoginPage.png)

**_Admin page diagram_**

![admin_page](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/AdminPage.png)


## Project Architecture diagram ##

![architecture](https://github.com/tdanamh/PROIECT_MDS/blob/master/src/main/resources/static/images/Arhitectura.png)

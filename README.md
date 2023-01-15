# Movie Search - Android App

<img src="/readme/ic_logo.png" align="left"
width="200" hspace="10" vspace="10">

Movie Search App is an application that searches by movie name and displays movie information.
Movie Search App is free.
No registration is required to use the Movie Search App application.

## About

Movie results are displayed by searching by movie name. Then, the page on which information such as the poster, duration and scenario of this movie is displayed is displayed.

In this project, OMDb was used as the database. With the input entered by the user in the search section, a GET request is made to the API with the Retrofit library, and the required data is displayed, and recyclerview is used to display the retrieved data as a list. It is also provided with a GET request to show the detailed information of the required movie.

MVVM architecture is used in this project.

## Features

The android app lets you:
- Show detailed information about the movie by searching by movie name.
- Switch between dark and light themes.
- English and Turkish language support.

## Screenshots

[<img src="/readme/Screenshot_20221223_095934.png" align="left"
width="200"
    hspace="10" vspace="10">](/readme/Screenshot_20221223_095934.png)
[<img src="/readme/Screenshot_20221223_095914.png" align="center"
width="200"
    hspace="10" vspace="10">](/readme/Screenshot_20221223_095914.png)
[<img src="/readme/Screenshot_20221223_100006.png" align="center"
width="200"
    hspace="10" vspace="10">](/readme/Screenshot_20221223_100006.png)

## Permissions

Movie Search App requires the following permissions:
- Full Network Access.
- View Network Connections.

## License

This application is released under BSD-3-Clause license (see [LICENSE](LICENSE)).

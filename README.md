# movie-rating-system
> Java API using Spring Boot Framework for a movie rating system

# Table of Contents

* [First Steps](#first-steps)
* [Available API routes](#available-api-routes)
* [Sample API requests](#sample-api-requests)


# First Steps

**After installing Java SDK and Eclipse, run the following commands:**

Clone the repository using:
```
git clone https://github.com/lucasncc/movie-rating-system
```

After importing the project as a Maven project and starting the API server on eclipse, now you can visit [`localhost:8080`](http://localhost:8080) from your browser to access the API.

# Available API routes

Additional API documentation can be found in the Swagger UI at http://localhost:8080/context-path/swagger-ui.html

| HTTP Request | Route | Description |
|----------|:-------------:|------:|
| GET | /users | List users |
| GET | /users/:id | Get user by id |
| POST | /users/ | Create new user |
| DELETE | /users/:id | Delete user by id |

| GET | /movies/:term | Get movie info by term |
| GET | /movies/id/:id | Get movie info by id |

| GET | /users/:id/comments | Get comments by user id |
| POST | /users/:id/comments | Create new comment by user id |


# Sample API requests

## User Requests

### Create new user

Send **POST** request to http://localhost:8080/users containing the following JSON body:
```
{
    "username": "manoel",
	"password": "platinum123",
    "email": "manoel@itau.com.br",
    "birthDate": "2021-06-22"
}
```

### List users

Send **GET** request to http://localhost:8080/users 

### List user by ID

Send **GET** request to http://localhost:8080/users/:id replacing :id with the user ID number.

### Delete user by ID

Send **DELETE** request to http://localhost:8080/users/:id replacing :id with the user ID number.

### List comments by user ID

Send **GET** request to http://localhost:8080/users/:id/comments replacing :id with the user ID number.


### Create new comment by user ID

Send **POST** request to http://localhost:8080/users/:id/comments replacing :id with the user ID number.

```
{
    "idMovie": "tt0110912",
    "text": "Tarantino's best flick!"
}
```


## Movie Requests (OMDb)

### List Movie by term

Send **GET** request to http://localhost:8080/movies/:term replacing :term with the term to be searched in the movie's title.

### List Movie by id

Send **GET** request to http://localhost:8080/movies/:id replacing :id with the movie's imdb Identification.


Feel free to message me about suggestions and future improvements!

Made by [Lucas Cardoso](https://github.com/lucasncc)



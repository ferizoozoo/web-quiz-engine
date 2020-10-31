# Web Quiz Engine

This web quiz engine is a backend written in java and spring boot and is used for quiz games.

### Installation

The quiz engine needs openjdk 8.0+ and gradle 5.5+ installed to run. 

Install the dependencies and devDependencies and start the server.

```sh
$ git clone https://github.com/ferizoozoo/web-quiz-engine
$ cd web-quiz-engine
$ gradle run
```

### APIs

The following are the APIs to work with:

| Function | Method | API |
| ------ | ------ | ------ |
| Get all the quizzes | GET | /api/quizzes |
| Get a specific quiz with ID | GET | /api/quizzes/{id} |
| Create a quiz | POST | /api/quizzes |
| Solve a quiz | POST | /api/quizzes/{id}/solve |
| Delete a quiz | DELETE | /api/quizzes/{id} |
| Register a new user | POST | /api/register |


### Development
Feel free to contribute to this project!
I'm really excited to hear feedbacks from you and help is always needed :)

### Todos

 - [] Write the /api/quizzes/completed API for completed quizzes by a user. (Note that the user can have multiple quizzes completed!)
 - [] Write tests.
 - [] Enhance the security of this engine by adding a more secure authentication.

License
----

GNU General Public License v3.0
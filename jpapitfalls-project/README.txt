An project full of examples regarding JPA-pitfalls
==================================================

a) Prerequisites

- Java JDK >= 1.8
- Maven >= 3.3.3
- Docker tools >= 1.11.2
- Docker-compose tools >= 1.7.1
- A running docker environment
  (do not forget "boot2docker up")

(lesser versions could work too)

b) Compilation

> cd jpapitfalls-project
> mvn install

c) Start up the system

> cd jpapitfalls-docker/
> docker-compose up

d) Access the web application

Navigate to
    http://DOCKER_IP:8080/jpa-pitfalls/
.
Then select an experiment on the left side and press "Run".

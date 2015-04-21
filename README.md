# Play+Slick introductory exercise

As the name suggests, the exercise is based on the [play-slick-quickstart](http://typesafe.com/activator/template/play-slick-quickstart) template form the [Typesafe Activator](https://www.typesafe.com/get-started).

In this exercise we're using [Play Framework](https://www.playframework.com/) and [Slick](http://slick.typesafe.com/) from the [Typesafe Reactive Platform](http://typesafe.com/products/typesafe-reactive-platform) to build a trivial conference system which stores talks and rooms they take place in. The exercise consists of 11 steps, each of which has a related Git tag in this repository:

- [01-start](#01-start)
- [02-controller-skeleton](#02-controller-skeleton)
- [03-database-schema](#03-database-schema)
- [04-rooms-dao](#04-rooms-dao)
- [05-rooms-controller](#05-rooms-controller)
- [06-rooms-controller-json](#06-rooms-controller-json)
- [07-database-evolutions](#07-database-evolutions)
- [08-talks-dao](#08-talks-dao)
- [09-talks-controller](#09-talks-controller)
- [10-talks-controller-json](#10-talks-controller-json)
- [11-integration-test](#11-integration-test)

To switch to a given step, execute:

```
git checkout -f <tag>
```

To run the application (available on `localhost:9000` by default), execute:

```
./activator run
```

The application uses an embedded H2 database located in `data/conference.h2.db` (see the [configuration file](conf/application.conf)).

## 01-start

This is the starting point with just a simple domain model (`Room`, `Talk`, `TalkWithRoom`).

## 02-controller-skeleton

Introduces a skeleton of the `Conference` controller with empty `getRooms` and `addRoom` methods (empty means returning just `Ok`) with the required routes.

## 03-database-schema

Adds Slick table mappings and a `DatabaseSchema` trait with queries.

> **Note:** if you choose to run the application after completing this step, you'll need to apply the database evolutions (i.e. create the database schema) before performing any API calls. In order to apply the evolutions, just make a `GET` request to `localhost:9000/@evolutions/apply/default` using your favorite HTTP client.

## 04-rooms-dao

Adds a DAO to create a new room and query existing rooms.

## 05-room-controller

Binds the controller room actions to the DAO methods.

> **Note:** The application won't compile after completing this step. This is on purpose, to show that you need to explicitly add JSON support for your case classes, which is the next step.

## 06-room-controller-json

Adds missing JSON support for `Room`.

## 07-database-evolutions

Adds database evolutions.

> **Note:** if you had already run the application before this step, the evolutions should have already been generated automatically.

## 08-talks-dao

Adds a DAO to create a talk and query existing talks with their respective rooms. Shows various ways of accessing the database with Slick: for comprehensions, Slick joins, plain SQL.

## 09-talks-controller

Adds talk actions to the controller and binds them to the DAO methods.

> **Note:** The application won't compile after completing this step. This is on purpose, to show that you need to explicitly add JSON support for your case classes, which is the next step.

## 10-talks-controller-json

Adds missing JSON support for `Talk` and `TalkWithRoom`.

## 11-integration-test

Adds a sample integration tests which uses a `FakeApplication` with an in-memory database and checks whether the `POST /room` call actually creates a room.

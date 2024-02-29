# Spring Boot GraphQL

This application has been created for educational purposes, and it's oversimplified on purpose.  
It's using the very popular "Book<->Author" domain among GraphLQ tutorials for Spring.    
Because many of such tutorials skip some important aspects (like the async nature of GraphQL which can lead to
LazyInitializationException, transactions scopes, N+1 issues), this repository is focused mostly on:

* quick introduction to the `@QueryMapping` and `@MutationMapping` annotations
* showing the importance of `@SchemaMapping` and `@BatchMapping` annotations
* database queries monitoring (for N+1 problem observability)

Application comes with an in-memory H2 database having some sample data.

## H2 database

Go to `http://localhost:8080/h2-console` and enter:

* JDBC_URL: jdbc:h2:mem:testdb
* User Name: sa
* Password: (empty string - a default value)

to get access to H2 database console in your browser.

## Graphiql

Go to http://localhost:8080/graphiql?path=/graphql to open GraphiQL interface.  
You can use the below GraphLQ testing data.

```graphql
query findAllReviewsReturnEntity {
    findAllReviewsReturnEntity {
        review
    }
}

query findAllReviewsReturnEntityUsingFragmentAndAlias {
    reviewsFragments: findAllReviewsReturnEntity {
        ...ReviewView
    }
}

query findBookByIdUsingArgument {
    findBookById(id: "abaf26a0-2acf-43cb-8035-15fb378a43e5") {
        title
    }
}

query findBookByTitle {
    findBookByTitle(title: "architecture") {
        id
        title
    }
}

query findAllBooksAuthorsOnly {
    findAllBooks {
        title
        authors {
            firstName
            lastName
        }
    }
}

query findAllBooksReviewsOnly {
    findAllBooks {
        title
        reviews {
            review
        }
    }
}

query findAllBooks {
    findAllBooks {
        ...BookView
    }
}

query ex {
    throwAnException
}

mutation createBookUsingVariable($bookInput: CreateBookInput!) {
    createBook(input: $bookInput) {
        id
        createdAt
        title
        authors {
            ...AuthorView
        }
    }
}

query resolvers {
    findBookByTitle(title:"Patterns of Enterprise Application Architecture") {
        title
        ping
        firstPublishYear
    }
}

fragment ReviewView on ReviewModel {
    id
    review
}

fragment AuthorView on AuthorModel {
    id
    firstName
    lastName
}

fragment BookView on BookModel {
    id
    title
    authors {
        ...AuthorView
    }
    reviews {
        ...ReviewView
    }
}
```

variables:

```json
{
  "bookInput": {
    "title": "Altimeet: Let's talk about GraphQL and Spring Boot",
    "authorsIds": [
      "5a681dad-ec98-4211-940a-3e837f3c62ed"
    ]
  }
}
```

## Useful libraries

* https://github.com/graphql-java/graphql-java-extended-scalars
* https://github.com/tailrocks/graphql-java-datetime
* datasource-proxy-spring-boot-starter

## Materials and tutorials

* Dan Vega blog and YouTube videos
* Spring GraphQL documentation

## Docs

https://spring.io/projects/spring-graphql/
https://docs.spring.io/spring-graphql/reference/index.html

Spring for GraphQL supports server handling of GraphQL requests over HTTP, WebSocket, and RSocket.

Requests must use HTTP POST with "application/json" as content type and GraphQL request details included as JSON in the
request body, as defined in the proposed GraphQL over HTTP specification. Once the JSON body has been successfully
decoded, the HTTP response status is always 200 (OK), and any errors from GraphQL request execution appear in the "
errors" section of the GraphQL response. The default and preferred choice of media type is "
application/graphql-response+json", but "application/json" is also supported, as described in the specification.

As a protocol GraphQL focuses on the exchange of textual data. This doesn’t include binary data such as images, but
there is a separate, informal graphql-multipart-request-spec that allows file uploads with GraphQL over HTTP.

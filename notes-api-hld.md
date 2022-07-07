# Note API High Level Design

## Components

* A front end serving HTML
* Note API
  - Java, Spring Boot, K8s, Jedis cache
* Redis Database
  - Used for storing notes
  - Used for caching responses for GETs
* Auth Service
  - User provides username password
  - Responds with session token
  - Front end stores token as session variable after user has logged in


## REST API Endpoints

In these examples id is the numerical id of the resource and token is the authentication token received by the out of scope authentication service.

Logged in users are prevented from accessing each other's notes because the key for each list of notes in Redis database is the user id (retrieved from auth service). When Creating a note the list of notes for that user is retrieved by the Note API and the new note added to the deserialised JSON and then overwritten for that user id. For deletes similarly the whole list is loaded, the item with the appropriate id removed and the whole list is overwritten in Redis.

### Create a new note

Examples request:

  POST /api/notes/?token=sdfasdfk

  {
      "value"="this is some note text"
  }

Example response:

  201 Created
  {
      "id"=1,
      "self"="https://feefo.com/api/notes/id/1",
      "value"="this is some note text"
  }

### Delete a note

Example request:

  DELETE /api/notes/id?token=sdfasdfk
  (no body)

Example response:

  200 OK
  (no body)

  404 NOT FOUND
  {
      "message"="Unable to delete note with id=1"
  }

### Get a list of all notes ever created by this user

Example request:

  GET /api/notes/?token=sdfasdfk
  (no body)

Example response:

  200 OK
  {
      [
          {
              "id"=1,
              "self"="https://feefo.com/notes/id/1",
              "value"="this is some note text"
          },
          {
              "id"=2,
              "self"="https://feefo.com/api/notes/id/2",
              "value"="this is another note text"
          }
      ]
  }

### Authentication failure

When token is not provided or not recognised by the authentication service.

Response:

  401 Unauthorized
  {
      "message"="You need to provide correct authentication details"
  }

## Acceptance Criteria Specifications

### Create a note

  Scenario: Creating a new note
  Given: user is logged in
  And: user has entered "some text" in note field
  When: user saves the note
  Then: the note is saved for later viewing

### View all notes

  Scenario: Viewing all notes
  Given: user is logged in
  And: notes "1, 2, 3" exist
  When: user gets all notes
  Then: notes "1, 2, 3" are displayed

### Delete a note

  Scenario: Deleting a note
  Given: user is logged in
  And: note "1" already exists for that user
  When: user deletes note "1"
  Then: note "1" is not available for viewing

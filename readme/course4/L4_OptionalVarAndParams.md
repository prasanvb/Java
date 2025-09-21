# Optional Variables and Parameters

## Overview

In Java, there's no built-in concept of "optional" parameters like in Python or JavaScript. 
However, we can achieve optional behavior using several patterns and techniques.


## 1. Optional Class Variables using `Optional<T>`

Use Java's `Optional<T>` wrapper class to explicitly indicate that a field may or may not have a value.

- `Optional<T>` is a container for a single value of type `T`
- `Optional<T>` is a value type, so it's immutable
- `Optional<T>` is a functional interface, so it can be used as a parameter or return type
- Fields that may legitimately be absent
- API design where you want to be explicit about nullable fields
- When you want to avoid null pointer exceptions

### Example

```java
import java.util.Optional;

class User {
    private String name;  // required
    private Optional<String> email = Optional.empty();  // optional

    public User(String name) { this.name = name; }

    public void setEmail(String email) {
        this.email = Optional.ofNullable(email);
    }

    public Optional<String> getEmail() { return email; }

    @Override
    public String toString() {
        return String.format("User{name='%s', email='%s'}",
                           name, email.orElse("Not provided"));
    }
}
```

### Usage & Output

```java
User user1 = new User("John");
System.out.println(user1); // User{name='John', email='Not provided'}

User user2 = new User("Jane");
user2.setEmail("jane@example.com");
System.out.println(user2); // User{name='Jane', email='jane@example.com'}

// Safe access
if (user2.getEmail().isPresent()) {
    System.out.println("Email: " + user2.getEmail().get());
}
```


## 2. Default Values for Optional Fields

Assign default values to fields and allow them to be overridden. Use `null` for truly optional fields.

- Configuration classes
- Settings or preferences
- When you have sensible defaults

### Example

```java
class DatabaseConfig {
    private String host = "localhost";  // default
    private int port = 5432;           // default
    private String password = null;    // optional

    public DatabaseConfig(String database, String username) {
        this.database = database;
        this.username = username;
    }

    public void setHost(String host) {
        this.host = host != null ? host : "localhost";
    }

    public String getConnectionString() {
        return String.format("jdbc:postgresql://%s:%d/%s", host, port, database);
    }
}
```

### Usage & Output

```java
DatabaseConfig config = new DatabaseConfig("myapp", "admin");
System.out.println(config.getConnectionString());
// jdbc:postgresql://localhost:5432/myapp

config.setHost("prod-server.com");
System.out.println(config.getConnectionString());
// jdbc:postgresql://prod-server.com:5432/myapp
```

## 3. Method Overloading for Optional Parameters

Create multiple versions of the same method with different parameter counts. Each version calls the "master" method with default values for missing parameters.

- Small number of optional parameters (2-3)
- When you want clean, readable method calls
- API design for convenience methods

### Example

```java
class EmailService {
    // Master method with all parameters
    public void sendEmail(String recipient, String subject, String body,
                         String cc, boolean highPriority) {
        System.out.println("To: " + recipient);
        System.out.println("Subject: " + subject);
        if (cc != null) System.out.println("CC: " + cc);
        System.out.println("Priority: " + (highPriority ? "HIGH" : "NORMAL"));
    }

    // Convenience methods
    public void sendEmail(String recipient, String subject, String body) {
        sendEmail(recipient, subject, body, null, false);
    }

    public void sendEmail(String recipient, String subject, String body, String cc) {
        sendEmail(recipient, subject, body, cc, false);
    }
}
```

### Usage & Output

```java
EmailService service = new EmailService();
service.sendEmail("user@example.com", "Hello", "Welcome!");
// To: user@example.com
// Subject: Hello
// Priority: NORMAL

service.sendEmail("user@example.com", "Important", "Read this", "boss@example.com");
// To: user@example.com
// Subject: Important
// CC: boss@example.com
// Priority: NORMAL
```

## 4. Builder Pattern for Multiple Optional Parameters

Use a separate builder class that allows method chaining to set optional parameters. Best for objects with many optional fields.

- Classes with many optional parameters (4+)
- Complex object construction
- When you want fluent, readable APIs

### Example

```java
class EmailBuilder {
    private String recipient, subject, body, cc;
    private boolean highPriority = false;

    public EmailBuilder recipient(String recipient) { this.recipient = recipient; return this; }
    public EmailBuilder subject(String subject) { this.subject = subject; return this; }
    public EmailBuilder body(String body) { this.body = body; return this; }
    public EmailBuilder cc(String cc) { this.cc = cc; return this; }
    public EmailBuilder highPriority(boolean high) { this.highPriority = high; return this; }

    public void send() {
        System.out.println("To: " + recipient);
        System.out.println("Subject: " + subject);
        if (cc != null) System.out.println("CC: " + cc);
        System.out.println("Priority: " + (highPriority ? "HIGH" : "NORMAL"));
    }
}
```

### Usage & Output

```java
// Simple email
new EmailBuilder()
    .recipient("user@example.com")
    .subject("Hello")
    .body("Welcome!")
    .send();
// To: user@example.com
// Subject: Hello
// Priority: NORMAL

// Complex email with optional parameters
new EmailBuilder()
    .recipient("team@example.com")
    .subject("Update")
    .body("Project status")
    .cc("manager@example.com")
    .highPriority(true)
    .send();
// To: team@example.com
// Subject: Update
// CC: manager@example.com
// Priority: HIGH
```

---

## 5. Optional Method Parameters

Use `Optional<T>` as method parameters to explicitly indicate optional values.

- When you want to be explicit about optional parameters
- Functional programming style
- **Note**: Can make APIs verbose, use sparingly

### Example

```java
class DataProcessor {
    public void processData(String data, Optional<String> filter, Optional<Integer> limit) {
        String actualFilter = filter.orElse("default");
        int actualLimit = limit.orElse(100);

        System.out.println("Data: " + data);
        System.out.println("Filter: " + actualFilter);
        System.out.println("Limit: " + actualLimit);
    }
}
```

### Usage & Output

```java
DataProcessor processor = new DataProcessor();

processor.processData("sample", Optional.empty(), Optional.empty());
// Data: sample
// Filter: default
// Limit: 100

processor.processData("data", Optional.of("important"), Optional.of(50));
// Data: data
// Filter: important
// Limit: 50
```

---

## 6. Varargs for Optional Parameters

Use variable arguments (`Object... args`) to accept any number of optional parameters.

- When you have an unknown number of optional parameters
- Logging, debugging, or utility methods
- When parameters are of the same type or can be treated generically

### Example

```java
class Logger {
    public void log(String level, String message, Object... params) {
        System.out.println("[" + level + "] " + message);
        if (params.length > 0) {
            System.out.println("Details:");
            for (Object param : params) {
                System.out.println("  - " + param);
            }
        }
    }
}
```

### Usage & Output

```java
Logger logger = new Logger();

logger.log("INFO", "App started");
// [INFO] App started

logger.log("ERROR", "Connection failed", "Host: localhost", "Port: 5432", "Retries: 3");
// [ERROR] Connection failed
// Details:
//   - Host: localhost
//   - Port: 5432
//   - Retries: 3
```


## Quick Comparison Table

| Pattern                  | Best For                 | Pros                            | Cons                               |
|--------------------------|--------------------------|---------------------------------|------------------------------------|
| `Optional<T>` fields     | Nullable class fields    | Explicit nullability, null-safe | Extra wrapper overhead             |
| Default values           | Configuration classes    | Simple, efficient               | Not explicit about optionality     |
| Method overloading       | 2-3 optional parameters  | Clean API, familiar             | Can lead to many methods           |
| Builder pattern          | Many optional parameters | Fluent API, very readable       | More code, separate class          |
| `Optional<T>` parameters | Explicit optional params | Type-safe, explicit             | Verbose method calls               |
| Varargs                  | Variable optional params | Flexible, simple                | Type safety lost, generic handling |

## Key Takeaways

- Java doesn't have native optional parameters, but multiple patterns exist
- Each pattern has specific use cases and trade-offs
- `Optional<T>` is great for null safety but shouldn't be overused
- Builder pattern excels for complex object construction
- Method overloading is simple and effective for basic cases
- Choose based on complexity, readability, and team preferences
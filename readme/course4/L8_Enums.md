# Enums

**Table of Contents**

1. [Introduction](#introduction)
2. [Basic Enum Declaration](#basic-enum-declaration)
3. [Enum Methods](#enum-methods)
4. [Enum with Fields and Methods](#enum-with-fields-and-methods)
5. [Enum with Constructor](#enum-with-constructor)
6. [Abstract Methods in Enums](#abstract-methods-in-enums)
7. [Enum in Switch Statements](#enum-in-switch-statements)
8. [EnumSet and EnumMap](#enumset-and-enummap)
9. [Best Practices](#best-practices)

## Introduction

An **Enum** (short for enumeration) in Java is a special data type that represents a group of named constants. Enums are used when you have a fixed set of constants that don't change, such as days of the week, months, directions, etc.

## Basic Enum Declaration

### Simple Enum Example

```java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

public class BasicEnumExample {
    public static void main(String[] args) {
        Day today = Day.FRIDAY;
        System.out.println("Today is: " + today);
        System.out.println("Is it weekend? " + (today == Day.SATURDAY || today == Day.SUNDAY));

        // Iterating through all enum values
        System.out.println("\nAll days of the week:");
        for (Day day : Day.values()) {
            System.out.println(day);
        }
    }
}
```

**Output:**

```
Today is: FRIDAY
Is it weekend? false

All days of the week:
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
SATURDAY
SUNDAY
```

## Enum Methods

Java enums come with several built-in methods:

```java
public enum Color {
    RED, GREEN, BLUE, YELLOW
}

public class EnumMethodsExample {
    public static void main(String[] args) {
        Color color = Color.RED;

        // name() - returns the name of the enum constant
        System.out.println("Name: " + color.name());

        // ordinal() - returns the position of enum constant (0-based)
        System.out.println("Ordinal: " + color.ordinal());

        // toString() - returns string representation
        System.out.println("ToString: " + color.toString());

        // values() - returns array of all enum constants
        Color[] colors = Color.values();
        System.out.println("Number of colors: " + colors.length);

        // valueOf() - returns enum constant for given string
        Color parsedColor = Color.valueOf("GREEN");
        System.out.println("Parsed color: " + parsedColor);

        // compareTo() - compares enum constants based on ordinal
        System.out.println("RED compared to BLUE: " + Color.RED.compareTo(Color.BLUE));
    }
}
```

**Output:**

```
Name: RED
Ordinal: 0
ToString: RED
Number of colors: 4
Parsed color: GREEN
RED compared to BLUE: -2
```

## Enum with Fields and Methods

Enums can have fields, constructors, and methods:

```java
public enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6);

    private final double mass;   // in kilograms
    private final double radius; // in meters

    // Constructor
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }

    // Methods
    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    // Universal gravitational constant
    public static final double G = 6.67300E-11;

    public double surfaceGravity() {
        return G * mass / (radius * radius);
    }

    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
}

public class PlanetExample {
    public static void main(String[] args) {
        double earthWeight = 75.0; // kg

        System.out.printf("Weight on different planets (if you weigh %.1f kg on Earth):%n%n", earthWeight);

        for (Planet planet : Planet.values()) {
            double weight = planet.surfaceWeight(earthWeight);
            System.out.printf("%s: %.2f kg%n", planet, weight);
        }

        System.out.printf("%nEarth's surface gravity: %.2f m/s²%n", Planet.EARTH.surfaceGravity());
    }
}
```

**Output:**

```
Weight on different planets (if you weigh 75.0 kg on Earth):

MERCURY: 28.33 kg
VENUS: 68.08 kg
EARTH: 75.00 kg
MARS: 28.42 kg

Earth's surface gravity: 9.80 m/s²
```

## Enum with Constructor

```java
public enum Status {
    ACTIVE("Active", 1),
    INACTIVE("Inactive", 0),
    PENDING("Pending", 2),
    CANCELLED("Cancelled", -1);

    private final String displayName;
    private final int code;

    // Private constructor (enums constructors are always private)
    Status(String displayName, int code) {
        this.displayName = displayName;
        this.code = code;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getCode() {
        return code;
    }

    // Static method to get enum by code
    public static Status getByCode(int code) {
        for (Status status : Status.values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }
}

public class StatusExample {
    public static void main(String[] args) {
        Status userStatus = Status.ACTIVE;

        System.out.println("Status: " + userStatus);
        System.out.println("Display Name: " + userStatus.getDisplayName());
        System.out.println("Code: " + userStatus.getCode());

        // Get status by code
        Status retrievedStatus = Status.getByCode(2);
        System.out.println("Status with code 2: " + retrievedStatus);

        System.out.println("\nAll statuses:");
        for (Status status : Status.values()) {
            System.out.printf("%-10s | %-10s | %d%n",
                status, status.getDisplayName(), status.getCode());
        }
    }
}
```

**Output:**

```
Status: ACTIVE
Display Name: Active
Code: 1
Status with code 2: PENDING

All statuses:
ACTIVE     | Active     | 1
INACTIVE   | Inactive   | 0
PENDING    | Pending    | 2
CANCELLED  | Cancelled  | -1
```

## Abstract Methods in Enums

Enums can have abstract methods that must be implemented by each enum constant:

```java
public enum Operation {
    PLUS {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE {
        @Override
        public double apply(double x, double y) {
            if (y == 0) throw new ArithmeticException("Division by zero");
            return x / y;
        }
    };

    // Abstract method that each enum constant must implement
    public abstract double apply(double x, double y);
}

public class OperationExample {
    public static void main(String[] args) {
        double x = 10.0;
        double y = 3.0;

        System.out.printf("x = %.1f, y = %.1f%n%n", x, y);

        for (Operation op : Operation.values()) {
            double result = op.apply(x, y);
            System.out.printf("%.1f %s %.1f = %.2f%n", x, op, y, result);
        }
    }
}
```

**Output:**

```
x = 10.0, y = 3.0

10.0 PLUS 3.0 = 13.00
10.0 MINUS 3.0 = 7.00
10.0 MULTIPLY 3.0 = 30.00
10.0 DIVIDE 3.0 = 3.33
```

## Enum in Switch Statements

Enums work excellently with switch statements:

```java
public enum TrafficLight {
    RED, YELLOW, GREEN
}

public class TrafficLightExample {
    public static void main(String[] args) {
        TrafficLight light = TrafficLight.RED;

        String action = getAction(light);
        System.out.println("Current light: " + light);
        System.out.println("Action: " + action);

        System.out.println("\nAll traffic light actions:");
        for (TrafficLight tl : TrafficLight.values()) {
            System.out.println(tl + " -> " + getAction(tl));
        }
    }

    public static String getAction(TrafficLight light) {
        switch (light) {
            case RED:
                return "STOP";
            case YELLOW:
                return "CAUTION";
            case GREEN:
                return "GO";
            default:
                return "UNKNOWN";
        }
    }
}
```

**Output:**

```
Current light: RED
Action: STOP

All traffic light actions:
RED -> STOP
YELLOW -> CAUTION
GREEN -> GO
```

## EnumSet and EnumMap

Java provides special collections optimized for enums:

```java
import java.util.*;

public enum Permission {
    READ, WRITE, EXECUTE, DELETE
}

public class EnumCollectionsExample {
    public static void main(String[] args) {
        // EnumSet - efficient set implementation for enums
        EnumSet<Permission> userPermissions = EnumSet.of(Permission.READ, Permission.WRITE);
        EnumSet<Permission> adminPermissions = EnumSet.allOf(Permission.class);
        EnumSet<Permission> noPermissions = EnumSet.noneOf(Permission.class);

        System.out.println("User permissions: " + userPermissions);
        System.out.println("Admin permissions: " + adminPermissions);
        System.out.println("No permissions: " + noPermissions);

        // EnumMap - efficient map implementation with enum keys
        EnumMap<Permission, String> permissionDescriptions = new EnumMap<>(Permission.class);
        permissionDescriptions.put(Permission.READ, "Can view files");
        permissionDescriptions.put(Permission.WRITE, "Can modify files");
        permissionDescriptions.put(Permission.EXECUTE, "Can run files");
        permissionDescriptions.put(Permission.DELETE, "Can remove files");

        System.out.println("\nPermission descriptions:");
        for (Map.Entry<Permission, String> entry : permissionDescriptions.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Check permissions
        System.out.println("\nChecking user permissions:");
        for (Permission permission : Permission.values()) {
            boolean hasPermission = userPermissions.contains(permission);
            System.out.println("Has " + permission + ": " + hasPermission);
        }
    }
}
```

**Output:**

```
User permissions: [READ, WRITE]
Admin permissions: [READ, WRITE, EXECUTE, DELETE]
No permissions: []

Permission descriptions:
READ: Can view files
WRITE: Can modify files
EXECUTE: Can run files
DELETE: Can remove files

Checking user permissions:
Has READ: true
Has WRITE: true
Has EXECUTE: false
Has DELETE: false
```

## Best Practices

### 1. Use Enums for Fixed Sets of Constants

```java
// Good: Use enum for fixed constants
public enum HttpStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

    HttpStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
}

// Avoid: Using string constants
public class BadHttpStatus {
    public static final String OK = "OK";
    public static final String NOT_FOUND = "NOT_FOUND";
    // This approach is error-prone and doesn't provide type safety
}
```

### 2. Override toString() for Better Readability

```java
public enum Priority {
    LOW("Low Priority"),
    MEDIUM("Medium Priority"),
    HIGH("High Priority"),
    CRITICAL("Critical Priority");

    private final String description;

    Priority(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}

public class PriorityExample {
    public static void main(String[] args) {
        Priority taskPriority = Priority.HIGH;
        System.out.println("Task priority: " + taskPriority); // Outputs: Task priority: High Priority
    }
}
```

**Output:**

```
Task priority: High Priority
```

### 3. Use Enum Singleton Pattern

```java
public enum DatabaseConnection {
    INSTANCE;

    private String connectionString;

    private DatabaseConnection() {
        // Initialize connection
        this.connectionString = "jdbc:mysql://localhost:3306/mydb";
    }

    public void connect() {
        System.out.println("Connecting to: " + connectionString);
    }

    public void disconnect() {
        System.out.println("Disconnecting from database");
    }
}

public class SingletonExample {
    public static void main(String[] args) {
        DatabaseConnection db = DatabaseConnection.INSTANCE;
        db.connect();
        db.disconnect();

        // Verify it's the same instance
        DatabaseConnection db2 = DatabaseConnection.INSTANCE;
        System.out.println("Same instance: " + (db == db2));
    }
}
```

**Output:**

```
Connecting to: jdbc:mysql://localhost:3306/mydb
Disconnecting from database
Same instance: true
```

## Summary

Java Enums are powerful tools that provide:

- **Type Safety**: Compile-time checking prevents invalid values
- **Readability**: Code is more self-documenting
- **Maintainability**: Changes to enum constants are centralized
- **Performance**: Efficient implementation with optimized collections
- **Functionality**: Can have fields, methods, and constructors

Enums are ideal for representing fixed sets of constants like states, types, categories, and configuration options. They make your code more robust, readable, and maintainable.

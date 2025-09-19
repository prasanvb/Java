# Java Method References

## Definition
Method references are a shorthand notation for lambda expressions that call a specific method. Introduced in Java 8 as part of functional programming features.

## Syntax
```java
ClassName::methodName
```
Uses the `::` operator to reference methods without invoking them.

## Types of Method References

### 1. Static Method References
- **Format**: `ClassName::staticMethodName`
- **Usage**: References static methods of a class
- **Lambda equivalent**: `(args) -> ClassName.staticMethodName(args)`

### 2. Instance Method References on Specific Objects
- **Format**: `objectInstance::instanceMethodName`
- **Usage**: References instance methods of a particular object
- **Lambda equivalent**: `(args) -> objectInstance.instanceMethodName(args)`

### 3. Instance Method References on Arbitrary Objects
- **Format**: `ClassName::instanceMethodName`
- **Usage**: References instance methods where the first parameter becomes the target of the method
- **Lambda equivalent**: `(obj, args) -> obj.instanceMethodName(args)`

### 4. Constructor References
- **Format**: `ClassName::new`
- **Usage**: References constructors
- **Lambda equivalent**: `(args) -> new ClassName(args)`

## Key Benefits

### Readability
- More concise than lambda expressions
- Eliminates redundant code
- Cleaner syntax for method calls

### Performance
- Potentially more efficient than lambdas
- No additional lambda instance creation
- Direct method invocation

### Reusability
- Easy reference to existing methods
- Promotes code reuse
- Reduces duplication

## When to Use Method References

### Use When
- Lambda expression only calls a single existing method
- Method signature matches functional interface requirements
- Want to improve code readability
- Existing method already performs desired operation

### Avoid When
- Need additional logic beyond method call
- Method parameters need transformation
- Multiple method calls required
- Complex conditional logic needed

## Functional Interface Compatibility
Method references must match the signature of the target functional interface:
- Parameter types must align
- Return type must be compatible
- Exception handling must match

## Common Use Cases
- Stream operations (`map`, `filter`, `forEach`)
- Sorting operations
- Collection processing
- Event handling
- Callback implementations

## Best Practices
- Prefer method references over lambdas when possible
- Use meaningful method names for clarity
- Consider readability over brevity
- Ensure method signature compatibility
- Document complex method reference chains
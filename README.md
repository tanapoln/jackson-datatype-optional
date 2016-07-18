Project for [Jackson](https://github.com/FasterXML/jackson) module (jar)
that adds supports for Optional datatypes included in version 8 which can not be directly
supported by core databind due to baseline being JDK 6, excluding following:


* New Date types are supported by [JSR-310 module](../../../jackson-datatype-jsr310)
* Support for parameter names is via [Parameter Names](../../../jackson-module-parameter-names) module

## Status

As of Jackson 2.5, this module is stable and considered production ready.

## Usage

### Maven dependency

To use module on Maven-based projects, use following dependency:

```xml
<dependency>
  <groupId>com.tanapoln</groupId>
  <artifactId>jackson-datatype-optional</artifactId>
  <version>2.8.0</version>
</dependency>    
```

(or whatever version is most up-to-date at the moment)

### Registering module

Like all standard Jackson modules (libraries that implement Module interface), registration is done as follows:

```java
ObjectMapper mapper = new ObjectMapper();
mapper.registerModule(new OptionalModule());
// Or, the more fluent version: ObjectMapper mapper = new ObjectMapper().registerModule(new OptionalModule());
```

after which functionality is available for all normal Jackson operations:
you can read JSON into supported `Optional` types, as well as write values of such types as JSON, so that for example:

```java
class Contact {
    private final String name;
    private final Optional<String> email;

    public Contact(String name, Optional<String> email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public Optional<String> getEmail() {
        return email;
    }
}

...

Contact nullEmail = new Contact("Example Co.", null);
String nullEmailJson = mapper.writeValueAsString(nullEmail);
// prints: {"name":"Example Co.","email":null}
System.out.println(nullEmailJson);

Contact emptyEmail = new Contact("Example Co.", Optional.empty());
String emptyEmailJson = mapper.writeValueAsString(emptyEmail);
// prints: {"name":"Example Co.","email":null}
System.out.println(emptyEmailJson);

Contact withEmail = new Contact("Example Co.", Optional.of("info@example.com"));
String withEmailJson = mapper.writeValueAsString(withEmail);
// prints:  {"name":"Example Co.","email":"info@example.com"}
System.out.println(withEmailJson);
```

## More

See [Wiki](../../wiki) for more information (javadocs, downloads).

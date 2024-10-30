# Example of workaround  Mongock with GraalVM(Workorund)

## Scope
Mongock currently doesn't provide official support for Graalvm. Although it will be officially supported in the next major release, 
there is a clear(and increasing) demand to have a solution in the short term.

This workaround tries to satisfies this need in the best manner possible.

## Key points
1. **The `ChangeUnitsList`**: You need to add all the ChangeUnits class you want to run(potentially, ase some of them may be already executed and will be ignored, as usual), as shown 
```java
    public static final List<Class<?>> changeUnits = Arrays.asList(
            ACreateCollection.class,
            BInsertDocument.class,
            CInsertAnotherDocument.class
    );
```
2. The feature `ChangeUnitsRegistrationFeature`. You don't need to do anything, just be aware it exists as you will need it at the image's creation time
3. `.setLockGuardEnabled(false)` in the builder.
3. `.sdkmanrc` provided. If you are not using `sdkman`, please ensure you are using graalvm
4. Ensure MongoDB is running


## Steps

- Build jar application with 
```shell
./gradlew build 
```

- build native image executable with
```shell
native-image --no-fallback \
--features=io.flamingock.graalvm.RegistrationFeature \
--initialize-at-build-time=org.slf4j.simple.SimpleLogger,org.slf4j.LoggerFactory,org.slf4j.impl.StaticLoggerBinder \
-jar  build/libs/graalvm-example-2-1.0-SNAPSHOT.jar
```

native-image --no-fallback \
--initialize-at-build-time=org.slf4j.simple.SimpleLogger,org.slf4j.LoggerFactory,org.slf4j.impl.StaticLoggerBinder \
-jar  build/libs/graalvm-example-2-1.0-SNAPSHOT.jar

- run executable with
```shell
./graalvm-example-1.0-SNAPSHOT
```

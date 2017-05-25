ascii-doc
=========

**TODO - NEED TO GET THIS WORKING**

Swagger has support for generating ascii-doc, which can then be easily referenced by our project’s README file.

Swagger2Markup is a Swagger to AsciiDoc or Github Flavored Markdown converter to simplify the generation of an up-to-date RESTful API documentation.

The swagger2markup-maven-plugin configuration that I have tried looks like:

```
<plugin>
    <groupId>io.github.robwin</groupId>
    <artifactId>swagger2markup-maven-plugin</artifactId>
    <version>0.9.3</version>
    <configuration>
        <inputDirectory>src/main/webapp/</inputDirectory>
         <swaggerFile>swagger.json</swaggerFile>
        <outputDirectory>asciidoc</outputDirectory>
        <markupLanguage>asciidoc</markupLanguage>
    </configuration>
    <executions>
        <execution>
            <phase>process-classes</phase>
            <goals>
                <goal>process-swagger</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

In the above configuration src/main/webapp/ is the folder that contains the swagger.json file generated using the swagger-maven-plugin shown in the above section.

During the build stage, the ASCII docs will be stored in the asciidoc folder as configured in the above plugin and it looks like this:

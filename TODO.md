# A TODO list

## Error handling
- Check if `ProblemDetailResponsePayload` definition can be extracted in a components.yaml file, e.g.
```yaml
components:
  schemas:
    ProblemDetailResponsePayload:
      type: object
      required:
        - title
        - status
      properties:
        type:
          type: string
          example: "about:blank"
        title:
          type: string
          example: "Not Found"
        status:
          type: integer
          example: 404
        instance:
          type: string
          example: "/users/1"
        detail:
          type: string
          example: "The requested user was not found."
```
When referencing it inside the domain OpenAPI definitions (e.g. users-api.yaml) the name 
`ProblemDetailResponsePayload` is not preserved, and we get some strange names. This issue was not present in the 
old version of the OpenAPI generator (e.g. 7.10.0). There is also an unresolved [bug](https://github.com/OpenAPITools/openapi-generator/issues/2701) which that points to [another](https://github.com/swagger-api/swagger-parser/issues/1081) (already resolved)
Common API definition must happen in an external library, otherwise it will break the attempt for easy extraction of 
the modules
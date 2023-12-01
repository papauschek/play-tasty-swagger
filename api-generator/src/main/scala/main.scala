import openapi.*
import play.api.libs.json.Json
import openapi.Implicits.*
import parser.Parser

import java.nio.file.{Files, Path}

@main
def main(): Unit = {

  convert()
}

private def convert(): Unit = {
  val routeOperations = Parser.parse("sample-app/conf/routes")

  val pathsAndOperations = routeOperations.map {
    routeOperation =>

      // val parameterSchema = ParameterSchema()
      // val mediaType = MediaTypeSchema("object", "description", properties = Map.empty, required = Seq.empty)

      (routeOperation.path,
       routeOperation.method,
        Operation(
          summary = routeOperation.title,
          description = routeOperation.description,
          parameters = Seq.empty,
          requestBody = None,
          responses = Map.empty,
          deprecated = false)
      )
  }

  val paths = pathsAndOperations.groupBy {
    case (path, _, _) => path
  }.map {
    case (path, operations) =>
      (path, operations.map {
        case (_, method, operation) => (method, operation)
      }.toMap)
  }

  val apiFile = ApiFile(
    openapi = "3.0.0",
    info = Info("title", "1.0.0"),
    paths = paths,
    components = None)

  val json = Json.toJson(apiFile)
  println(Json.prettyPrint(json))
  Files.writeString(Path.of("output/openapi.json"), Json.prettyPrint(json))
}
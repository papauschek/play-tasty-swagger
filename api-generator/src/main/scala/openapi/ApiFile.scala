package openapi

import play.api.libs.json._

object Implicits {
  implicit val ParameterSchemaFormat: Format[ParameterSchema] = Json.format[ParameterSchema]
  implicit val ParameterFormat: Format[Parameter] = Json.format[Parameter]
  implicit val MediaTypeSchemaFormat: Format[MediaTypeSchema] = Json.format[MediaTypeSchema]
  implicit val MediaTypeFormat: Format[MediaType] = Json.format[MediaType]
  implicit val ResponseFormat: Format[Response] = Json.format[Response]
  implicit val ComponentsFormat: Format[Components] = Json.format[Components]
  implicit val RequestBodyFormat: Format[RequestBody] = Json.format[RequestBody]
  implicit val OperationFormat: Format[Operation] = Json.format[Operation]
  //implicit val PathItemFormat: Format[PathItem] = Json.format[PathItem]
  implicit val InfoFormat: Format[Info] = Json.format[Info]
  implicit val ApiFileFormat: Format[ApiFile] = Json.format[ApiFile]
}


case class ApiFile(openapi: String,
                   info: Info,
                   paths: Map[String, Map[String, Operation]],
                   components: Option[Components])

case class Info(title: String,
                version: String)


case class Operation(summary: String,
                     description: String,
                     parameters: Seq[Parameter],
                     requestBody: Option[RequestBody],
                     responses: Map[String, Response],
                     deprecated: Boolean)

case class Parameter (name: String,
                      in: String,
                      description: String,
                      required: Boolean,
                      schema: ParameterSchema)

case class ParameterSchema(`type`: String = "string")

case class RequestBody(content: Map[String, MediaType])

case class MediaType(schema: MediaTypeSchema)

case class MediaTypeSchema(`type`: String,
                           description: String,
                           properties: Map[String, MediaTypeSchema],
                           required: Seq[String],
                           items: Option[MediaTypeSchema] = None)

case class Response(description: String,
                    content: Map[String, MediaType])

case class Components(schemas: Map[String, MediaTypeSchema])
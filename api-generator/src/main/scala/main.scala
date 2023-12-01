import openapi._
import play.api.libs.json.Json
import openapi.Implicits.*

@main
def main(): Unit = {
  convert()
}

private def convert(): Unit = {
  val parameterSchema = ParameterSchema()
  val mediaType = MediaTypeSchema("object", "description", properties = Map.empty, required = Seq.empty)
  val json = Json.toJson(mediaType)
  println(json.toString)
}
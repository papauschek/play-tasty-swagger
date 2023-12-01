package parser

import play.routes.compiler.{RoutesCompiler, RoutesFileParser, Rule, Route}
import java.io.File

object Parser {

  def parse(fileName: String): Seq[RouteOperation] = {
    val rules = parseRoutes(fileName)

    rules.flatMap {
      case route: Route =>

        val commentLines = route.comments.map(_.comment).mkString("\r\n").linesIterator.toSeq

        Some(RouteOperation(
          "/" + route.path.toString,
          route.verb.toString.toLowerCase,
          title = commentLines.headOption.mkString,
          description = commentLines.drop(1).mkString
        ))
      case _ => None
    }
  }

  def parseRoutes(fileName: String): List[Rule] = {
    val file = new File(fileName)
    RoutesFileParser.parse(file) match {
      case Left(errors) => throw new Exception(errors.mkString("\n"))
      case Right(routes) => routes
    }
  }

}

case class RouteOperation(path: String,
                          method: String,
                          title: String,
                          description: String)

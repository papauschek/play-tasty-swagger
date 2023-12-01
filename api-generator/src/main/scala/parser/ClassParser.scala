package parser

import java.nio.file.{Files, Path}
import scala.quoted.*
import scala.tasty.inspector.*

object ClassParser {

  @main
  def parseClasses(): Unit = {

    // load all files recursively
    val projectRoot = Path.of("sample-app")
    val tastyFiles = Files.walk(Path.of(projectRoot.toString, "target")).toArray
      .filter(_.toString.endsWith(".tasty"))
      .map(_.toString)
      .toList

    // TODO: generate this dynamically
    val classPaths = Files.readString(Path.of("output/classpath.txt")).linesIterator.toList

    TastyInspector.inspectAllTastyFiles(tastyFiles, Nil, classPaths)(new MyInspector)

  }

}


case class DocType(name: String, scalaDocs: Option[String])
case class DocParam(name: String)

class MyInspector extends Inspector:

  def inspect(using Quotes)(tastys: List[Tasty[quotes.type]]): Unit =
    import quotes.reflect.*
    import quotes.*

    object TypeTraverser extends TreeAccumulator[List[DocType]]:
      def foldTree(existing: List[DocType], tree: Tree)(owner: Symbol): List[DocType] =
        val r = tree match
          case ClassDef(name, x, trees, valdef, statements) =>
            val t = DocType(name, tree.symbol.docstring)

            val params = for {
              paramClauses <- x.paramss
              param <- paramClauses.params
            } yield parseParam(param)

            println(params)
            List(t)
          case _ =>
            Nil
        foldOverTree(existing ++ r, tree)(owner)
    end TypeTraverser

    def parseParam(tree: Tree): Unit = {
      tree match {
        case ValDef(name, tpe, _) =>
          println((name, tpe))
        case _ =>
          println("nope")
      }
    }

    for tasty <- tastys do

      println(tasty.path)
      val tree: Tree = tasty.ast
      val symbol: Symbol = tree.symbol
      val results = TypeTraverser.foldTree(Nil, tree)(symbol)
      println(results)
//println(processSymbol(symbol))


//println(Printer.TreeStructure.show(tasty.ast))





case class DocClass(name: String, fields: Seq[DocField])

case class DocField(name: String, tpe: String)
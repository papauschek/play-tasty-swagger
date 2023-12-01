import dotty.tools.dotc.plugins._

class Plugin extends StandardPlugin {
  val name: String = "counter"
  override val description: String = "Count method calls"

  def init(options: List[String]): List[PluginPhase] = Nil
    //val setting = new Setting(options.headOption)
    //(new PhaseA(setting)) :: (new PhaseB(setting)) :: Nil
}

/*
object MySBTPlugin extends AutoPlugin {

  object autoImport {
    val hello = inputKey[Unit]("Says hello!")
  }

  override lazy val projectSettings = Seq(
    hello := {
      val args = spaceDelimited("").parsed
      System.out.println(s"Hello, ${args(0)}")
    }
  )

}*/
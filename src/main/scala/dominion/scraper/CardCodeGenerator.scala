package dominion.scraper

import scala.io.Source

object CardCodeGenerator {
  def main(args: Array[String]) = parseTextFile("simple_cards.txt")

  def parseTextFile(filename: String): Unit = Source.fromFile(filename)
    .getLines
    .foreach(parseTextLine)

  def parseTextLine(line: String) = {
    val Array(name, expansion, typesString, cost, text) = line.split("\t")

    val types = typesString.split("\\W+").mkString("\"", "\", \"", "\"")
    val costCoins = cost.split("\\s+")(0).drop(1)
    val costPotions = cost.split("\\s+").lift(1).getOrElse("0P").dropRight(1)

    val KnownEffects = Map(
      "victory" -> "victory",
      "buy" -> "buys",
      "action" -> "actions",
      "card" -> "cards",
      "coin" -> "coins"
    )

    val effects = text.split(",").flatMap { effect =>
      KnownEffects.collect {
        case (effectName, scalaMethod) if effect.toLowerCase.contains(effectName) =>
          scalaMethod + "(" + effect.replaceAll(".*?(\\-*\\d+).*", "$1") + ")"
      }
    }.mkString(".")

    print(s"""val $name = Card("$name", Set($types), $costCoins, $costPotions, "$text") {""")
    println(s""" Effect(_.$effects) }""")
  }
}
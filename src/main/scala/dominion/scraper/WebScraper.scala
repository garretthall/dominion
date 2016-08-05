package dominion.scraper

import java.io.PrintWriter

import scala.io.Source

object WebScraper {
  def main(args: Array[String]) = {
    val regex = """<tr class="card-row(.*?)</tr>""".r
    val regex2 =
      """<td class="card-name"><b><a.*?>(.*?)</a></b></td>
        |<td class="card-expansion"><a.*?>(.*?)</a></td>
        |<td class="card-type">(.*?)</td>
        |<td class="card-cost">(.*?)</td>
        |<td class="card-rules">(.*?)</td>""".stripMargin.replaceAll("\n", "").r

    val html = Source
      .fromFile("dominion.html")
      .getLines
      .mkString("")

    val pw = new PrintWriter("cards.txt")

    regex
      .findAllIn(html)
      .matchData
      .map { matchData =>
        val matched = regex2.findFirstMatchIn(matchData.group(1)).get
        1.to(5)
          .map(matched.group)
          .mkString("\t")
      }
      .foreach(pw.println)
    pw.close
  }
}

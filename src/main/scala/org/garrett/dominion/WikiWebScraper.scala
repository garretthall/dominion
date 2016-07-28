package org.garrett.dominion

import java.io.PrintWriter

import scala.io.Source

object WikiWebScraper {
  def main(args: Array[String]) = {
    val pw = new PrintWriter("cards.txt")

    for (cardName <- getCardNames) {
      println(s"Finding card info for $cardName")
      pw.println(getCardInfo(cardName))
    }

    pw.close()
  }

  def getCardInfo(cardName: String): String = {
    val regex = """\{\{Infobox.*?\}\}""".r
    val html = getHtml(s"http://wiki.dominionstrategy.com/index.php?title=$cardName&action=edit")

    regex
      .findFirstMatchIn(html)
      .get
      .group(0)
  }

  def getHtml(url: String): String = Source
    .fromURL(url)
    .getLines
    .mkString("\t")

  def getCardNames: Set[String] = {
    val regex = """\{\{Card\|(.*?)[}|]""".r
    val html = getHtml("http://wiki.dominionstrategy.com/index.php?title=Template:Navbox_Cards&action=edit")
      // handle the replacements between the html and links the wiki makes
      .replaceAll(" ", "_")
      .replaceAll("'", "%27")
      // Castles redirects to Castle
      .replaceAll("Castles", "Castle")
      .replaceAll("Knights", "Knight")

    regex
      .findAllMatchIn(html)
      .map(_.group(1))
      .toSet
  }
}

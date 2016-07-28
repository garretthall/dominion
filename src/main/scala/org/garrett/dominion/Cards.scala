package org.garrett.dominion

import java.io.PrintWriter

import play.api.libs.json.{JsValue, Json}

import scala.io.Source

case class Card(name: String,
                types: Set[String],
                costCoins: Int,
                costPotions: Int,
                text: String)

object Card {
  implicit val format = Json.format[Card]

  def fromJson(json: String): Set[Card] = Json.parse(json).as[Set[Card]]

  def toJson(cards: Set[Card]): String = Json.prettyPrint(Json.toJson(cards))

  def writeJsonFile(cards: Set[Card], filename: String) = {
    val pw = new PrintWriter(filename)
    pw.println(toJson(cards))
    pw.close()
  }

  def readJsonFile(filename: String) = fromJson(Source.fromFile(filename)
    .getLines
    .mkString(""))

  def parseTextFile(filename: String): Set[Card] = Source.fromFile(filename)
    .getLines
    .map(parseTextLine)
    .toSet

  def parseTextLine(line: String) = {
    val Array(name, expansion, typesString, cost, text) = line.split("\t")
    val types = typesString.split("\\W+").toSet

    val costCoins = cost.split(" ")(0).drop(1).toInt
    val costPotions = cost.split(" ").lift(0).getOrElse("0P").dropRight(1).toInt

    Card(name, types, costCoins, costPotions, text)
  }
}

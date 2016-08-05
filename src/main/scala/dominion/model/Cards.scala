package dominion.model

object Cards {
  val Village = Card("Village", Set("Action"), 3, 0, "+1 Card, +2 Actions.") { Effect(_.cards(1).actions(2)) }
  val Woodcutter = Card("Woodcutter", Set("Action"), 3, 0, "+1 Buy, +2 Coins.") { Effect(_.buys(1).coins(2)) }
  val Festival = Card("Festival", Set("Action"), 5, 0, "+2 Actions, +1 Buy, +2 Coins.") { Effect(_.actions(2).buys(1).coins(2)) }
  val Laboratory = Card("Laboratory", Set("Action"), 5, 0, "+2 Cards, +1 Action.") { Effect(_.cards(2).actions(1)) }
  val Smithy = Card("Smithy", Set("Action"), 4, 0, "+3 Cards.") { Effect(_.cards(3)) }
  val Platinum = Card("Platinum", Set("Treasure"), 9, 0, "5 Coins") { Effect(_.coins(5)) }
  val Colony = Card("Colony", Set("Victory"), 11, 0, "10 Victory") { Effect(_.victory(10)) }
  val Copper = Card("Copper", Set("Treasure"), 0, 0, "1 Coin.") { Effect(_.coins(1)) }
  val Estate = Card("Estate", Set("Victory"), 2, 0, "1 Victory.") { Effect(_.victory(1)) }
  val Silver = Card("Silver", Set("Treasure"), 3, 0, "2 Coins.") { Effect(_.coins(2)) }
  val Duchy = Card("Duchy", Set("Victory"), 5, 0, "3 Victory.") { Effect(_.victory(3)) }
  val Gold = Card("Gold", Set("Treasure"), 6, 0, "3 Coins.") { Effect(_.coins(3)) }
  val Province = Card("Province", Set("Victory"), 8, 0, "6 Victory.") { Effect(_.victory(6)) }
  val Curse = Card("Curse", Set("Curse"), 0, 0, "-1 Victory.") { Effect(_.victory(-1)) }
}
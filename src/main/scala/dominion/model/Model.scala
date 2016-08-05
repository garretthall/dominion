package dominion.model

case class Card(name: String,
                types: Set[String],
                costCoins: Int,
                costPotions: Int,
                text: String)
               (effect: Effect)

case class Effect(play: GameState => GameState)

case class Player(coins: Int,
                  buys: Int,
                  actions: Int,
                  victory: Int,
                  hand: Seq[Card],
                  deck: Seq[Card],
                  discard: Seq[Card]) {

  def shuffle = copy(
    discard = Seq.empty,
    deck = discard
  )

  def draw(n: Int) = copy(
    hand = hand ++ deck.take(n),
    deck = deck.drop(n)
  )
}

case class Supply(cards: Map[Card, Int]) {

}

case class GameState(you: Player,
                     opponent: Player,
                     supply: Supply) {

  def cards(delta: Int) = copy(you.draw(delta))

  def actions(delta: Int) = copy(you.copy(actions = you.actions + delta))

  def coins(delta: Int) = copy(you.copy(coins = you.coins + delta))

  def buys(delta: Int) = copy(you.copy(buys = you.buys + delta))

  def victory(delta: Int) = copy(you.copy(victory = you.victory + delta))
}
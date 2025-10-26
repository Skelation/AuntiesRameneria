# Game Design Elements in Auntie's Rameneria

In the making of Auntie’s Rameneria, we used some principles of game design to make the user experience enjoyable and engaging. This document explains a few examples of how we decided to implemented these.

There needs to be an “I won!” situation for the game to be enjoyable, and personally, playable. The game’s primary goal is simple: to earn as much money as possible before the shift timer runs out. Players must prepare and serve ramen orders efficiently, factoring in both speed and accuracy. The unambiguousness of the objective of the game and the definition of success is essential to its appeal.

To maintain challenge and motivation, the game punishes poor performance. If an order takes too long or is prepared incorrectly, the customer becomes upset and leaves without paying. This consequence system creates a sense of urgency and pushes players to engage in the game. Conversely, good performance is rewarded. Players who deliver accurate orders quickly receive more money, reinforcing positive gameplay and creating a satisfying feedback loop.

The art style plays a key role in the game’s appeal. To ensure that the art style of the game is unique, consistent, and ethically sourced, we commissioned a digital artist, Trisha Das, to design game elements. The style of the game is flat, casual, and parallels the warmth and energy of a busy ramen shop. The expressive characters and vibrant color palette create an inviting and lighthearted atmosphere that draws players in. The heightened saturation of the game really helps in making it more appealing. This aspect is also a nod to cult classic games like Papa’s Pizzeria, from which this game is mainly inspired.

As the game progresses, the difficulty gradually increases. The number of customers increases after a period of time, and recipes call for more steps, requiring players to think ahead and manage their workflow efficiently. This progressive difficulty ensures that players remain challenged and engaged over time.

## References

To understand and implement these elements of game design, we referred to the following sources:
- Baur, Wolfgang (2012). Complete Kobold Guide to Game Design. Open Design LLC. ISBN 978-1936781065.
- Burgun, Keith (2012). Game Design Theory: A New Philosophy for Understanding Games. A K Peters/CRC Press. ISBN 978-1466554207.

# Usage

To launch the game, navigate to the folder and enter `java AuntiesRameneria.java`.
Loading takes some time so its normal if it doesn't launch directly.

# Tests

- Use `<ALT> + 1 or 2 or 3` to switch panes.

- On the kitchen panel, click on ingredients buttons and see how they interact with the selected ramen.
- Use arrow keys to go through the burners.
- When ramen contains noodles and water, press `<ENTER>` to cook it and see the timer as it is getting cooked.
- Pressing `<DELETE>` or `<BACKSPACE>` should reset the ramen to its default state.
- Try making a ramen accordingly to the order and submitting it by clicking on the order while hovering the correct ramen (Ramen should contain noodles, water, be cooked and contain the correct seasoning and toppings).

- On the fridge panel, use arrow keys to through the different drinks.
- Try clicking on an order with the selected drink to add it.
- Try submitting a drink to an order by hovering the correct drink and clicking on the order.

- Try finishing an order by giving it the correct drink and the correct ramen. See how it gives positive feedback to the player by showing the money gained and the multipliers.
- Try getting an order wrong by giving it an incorrect drink. See how it substracts money from the player's balance.

- On the counter panel, there should be as many clients at the counter as there are orders in the order panel.

import java.util.Scanner;
import java.lang.Character;
import java.util.Collections;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class Blackjack
{

	public static void main(String[] args)
	{
		System.out.println("\n=================================================");
		System.out.println("|  Welcome to my casino. Let's play Blackjack!  |");
		System.out.println("=================================================\n");

		Scanner c = new Scanner(System.in);
		Scanner h = new Scanner(System.in);
		String yn;
		String playerInput;
		String[] cards = new String[] {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
		List<String> playerHand = new ArrayList<>();
		List<String> dealerHand = new ArrayList<>();
		String[] deck = new String[16 * cards.length];
		int dealerTotal = 0;
		int playerTotal = 0;
		int cardValue = 0;
		int dealt = 0;
		int currentDealt = 0; /* Ensures player gets dealt first every time */

		//////////////////////////////////////////////////
		/*
		Populate 4 total decks and shuffle them together,
		re-shuffling after 3/4 of the deck has been used.
		*/
		//////////////////////////////////////////////////
		do
		{
			for (int i = 0; i < 16; i++)
			{
				System.arraycopy(cards, 0, deck, i * cards.length, cards.length);
			}
			List<String> shuffled = Arrays.asList(deck);
			Collections.shuffle(shuffled);
			deck = shuffled.toArray(new String[shuffled.size()]);
			dealt = 0;
		}
		while (dealt > ((3/4)*deck.length));


		do
		{

			/* Deal initial cards to player and dealer */
			for (int i = dealt; i < (dealt + 4); i++)
			{

				if (currentDealt%2==0)
				{
					String newCard = String.valueOf(deck[i]);
					playerHand.add(newCard);
					currentDealt++;
				}
				else
				{
					String newCard = String.valueOf(deck[i]);
					dealerHand.add(newCard);
					currentDealt++;
				}

			}
			dealt += 4;

			/* Apply int values to cards and total up player's hand */
			for (int i = 0; i < playerHand.size(); i++)
			{

				if ("A".equals(playerHand.get(i)))
				{
					cardValue = 11;
					playerTotal += cardValue;
				}
				else if("K".equals(playerHand.get(i)))
				{
					cardValue = 10;
					playerTotal += cardValue;
				}
				else if("Q".equals(playerHand.get(i)))
				{
					cardValue = 10;
					playerTotal += cardValue;
				}
				else if("J".equals(playerHand.get(i)))
				{
					cardValue = 10;
					playerTotal += cardValue;
				}
				else
				{
					cardValue = Integer.parseInt(playerHand.get(i));
					playerTotal += cardValue;
				}

			}

			if (playerTotal == 21)
			{
				System.out.println("BLACKJACK! You got lucky this time...");
			}

      boolean ace = playerHand.contains("A");
      if (playerTotal > 21 && ace)
      {
          playerTotal -= 10;
      }

			/* Show player his hand, and dealer face-up card */
			System.out.println("\nYour hand: " + playerHand + "(Total: " + playerTotal +")");
			System.out.println("Dealer's hand: " + "[*,"+dealerHand.get(0)+"] (Total: " + dealerTotal + ")\n");

			//////////////////////////////////////////////////
			/*
			Ask user to hit or stay,
			and add cards to player's hand when player hits
			*/
			//////////////////////////////////////////////////
			do
			{
                /* Require 'h' or 's' input */
				do
				{
					System.out.print("Hit or Stay? (h/s): ");
					playerInput = h.next();
				}
				while(!"h".equals(playerInput) && !"s".equals(playerInput));


				if ("h".equals(playerInput))
				{
					/* Pull next card from deck and add to player's hand */
					String newCard = String.valueOf(deck[dealt]);
					playerHand.add(newCard);
					dealt++;

					/* Apply int value and add new card to player's total */
					if ("A".equals(newCard))
					{
						cardValue = 11;
						playerTotal += cardValue;
					}
					else if("K".equals(newCard))
					{
						cardValue = 10;
						playerTotal += cardValue;
					}
					else if("Q".equals(newCard))
					{
						cardValue = 10;
						playerTotal += cardValue;
					}
					else if("J".equals(newCard))
					{
						cardValue = 10;
						playerTotal += cardValue;
					}
					else
					{
						cardValue = Integer.parseInt(newCard);
						playerTotal += cardValue;
					}

					/* If player's hand exceeds 21 but contains an Ace, designate the Ace a 1 instead of 11 */
					/*boolean ace = playerHand.contains("A");*/
					if (playerTotal > 21 && ace)
					{
						playerTotal -= 10;
					}

                    /* Show player's hand and dealer's face-up card */
                    System.out.println("\nYour hand: " + playerHand + "(Total: " + playerTotal +")");
                    System.out.println("Dealer's hand: " + "[*,"+dealerHand.get(0)+"] (Total: " + dealerTotal + ")\n");
				}

			}
			while(!"s".equals(playerInput) && (playerTotal != 21) && !(playerTotal > 21 && !playerHand.contains("A")));


			if (playerTotal > 21 && !playerHand.contains("A"))
			{
                System.out.println("Bust! Better luck next time...");
			}

			//////////////////////////////
			/*
			When player stays,
			play out dealer hand,
			and declare winner
			*/
			//////////////////////////////
			if ("s".equals(playerInput))
			{

				/* Apply int values for each card and add to dealer's total */
				for (int i = 0; i < dealerHand.size(); i++)
				{
					if ("A".equals(dealerHand.get(i)))
					{
						cardValue = 11;
						dealerTotal += cardValue;
					}
					else if("K".equals(dealerHand.get(i)))
					{
						cardValue = 10;
						dealerTotal += cardValue;
					}
					else if("Q".equals(dealerHand.get(i)))
					{
						cardValue = 10;
						dealerTotal += cardValue;
					}
					else if("J".equals(dealerHand.get(i)))
					{
						cardValue = 10;
						dealerTotal += cardValue;
					}
					else
					{
						cardValue = Integer.parseInt(dealerHand.get(i));
						dealerTotal += cardValue;
					}
				}

				do
				{
					String newCard = String.valueOf(deck[dealt]);
					dealerHand.add(newCard);
					dealt++;

					if ("A".equals(newCard))
					{
						cardValue = 11;
						dealerTotal += cardValue;
					}
					else if("K".equals(newCard))
					{
						cardValue = 10;
						dealerTotal += cardValue;
					}
					else if("Q".equals(newCard))
					{
						cardValue = 10;
						dealerTotal += cardValue;
					}
					else if("J".equals(newCard))
					{
						cardValue = 10;
						dealerTotal += cardValue;
					}
					else
					{
						cardValue = Integer.parseInt(newCard);
						dealerTotal += cardValue;
					}

					boolean ace2 = dealerHand.contains("A");
					if (dealerTotal >= 17 && ace2 && (dealerTotal != 21))
					{
						dealerTotal -= 10;
					}
				}
				while ((dealerTotal <= 17 && !dealerHand.contains("A")) && (dealerTotal != 21));


				System.out.println("---------------------------------------");
				System.out.println("Your hand: " + playerHand + "(Total: " + playerTotal + ")");
				System.out.println("Dealer's hand: " + dealerHand + "(Total: " + dealerTotal + ")");
        System.out.println("---------------------------------------");
			}

			if ((dealerTotal > playerTotal) && (dealerTotal <= 21))
			{
                System.out.println("\nWell what do you know, the house wins again!");
			}
			else if (dealerTotal == 21)
			{
							  System.out.println("HA! Dealer Blackjack!");
			}
			else if (dealerTotal > 21)
			{
                System.out.println("\nDealer busts! You got lucky this time...");
			}
			else if (playerTotal > 21)
			{
								System.out.print("");
			}
			else
			{
                System.out.println("\nYou win for once! Nice!");
      }



				System.out.println("============================================");

			//////////////////////////////
			/* Ask user to continue playing */
			//////////////////////////////
			do
			{
				System.out.print("Play another hand? (y/n): ");
				yn = c.next();
			}
			while(!"y".equals(yn) && !"n".equals(yn));

			System.out.println("============================================");
			/* Reset neccessary variables */
			if ("y".equals(yn))
			{
				playerInput = "";
				playerHand.clear();
				dealerHand.clear();
				playerTotal = 0;
				dealerTotal = 0;
				currentDealt = 0;
			}


		}
		while("y".equals(yn));

	}
}

import java.util.ArrayList;

public class Player {
    private ArrayList<Card> cards = new ArrayList<>(0);
    private int playerNumber;
    private int mana;
    private int health;
    private Card weapon = new Card();
    private final Deck deck;

    public Player(int number, Deck deck, int health) {
        this.playerNumber = number;
        this.deck = deck;
        this.health = health;
    }

    public void giveCard(Card c) {
        cards.add(c);
        System.out.println("Giving card");
        Utility.ConsoleFunctions.wait(300);
    }

    public Card getRandomCard( ) {
        int cardIndex = getRandInt( 0, cards.size( ) - 1 );
        System.out.println( cardIndex );
        return cards.get( cardIndex );
    }

    public void drawCardFromDeck( ) {
        if (deck.getCards().size() > 0) {
            int cardIndex = getRandInt(0, deck.getCards().size() - 1);
            Card c = deck.getCards().get(cardIndex);
            giveCard(c);
            deck.getCards().remove(c);
        }
    }

    public int getRandInt( int min, int max ) {
        return ( int ) ( Math.random( ) * ( ( max - min ) + 1 ) ) + min;
    }

    private ArrayList< Card > getCards( ) {
        return cards;
    }

    public void setCards( ArrayList< Card > cards ) {
        this.cards = cards;
    }

    public int getMana( ) {
        return mana;
    }

    public void setMana( int mana ) {
        this.mana = mana;
    }

    public void decreaseMana( int decrement ) {
        this.mana -= decrement;
    }

    public Card getWeapon( ) {
        return weapon;
    }

    public void setWeapon( Card weapon ) {
        this.weapon = weapon;
    }

    public String displayCards( ) {
        String[] strs = { "", "", "", "", "" };
        for (Card card : cards) {
            final int cardHeight = 5;
            for (int j = 0; j < cardHeight; j++) {
                strs[j] += (card.toString().split("\n")[j] + "\t");
            }
        }

        String str = "";
        for (String s : strs) {
            str += s + "\n";
        }
        return str.substring( 0, str.length( ) - 1 );
    }

    public String displayCardBacks( ) {
        String[] strs = { "", "", "", "", "" };
        for (int i = 0; i < cards.size( ); i++ ) {
            final int cardHeight = 5;
            for (int j = 0; j < cardHeight; j++ ) {
                strs[ j ] += ( cards.get( i ).back.split( "\n" )[ j ] + "\t" );
            }
        }

        String str = "";
        for (String s : strs) {
            str += s + "\n";
        }
        return str.substring( 0, str.length( ) - 1 );
    }

    public void playCard( Board board, Card c, Coordinate pos ) {
        //I hate this but doing cards.remove(c); doesn't work
        Utility.ArrayHelper.remove(cards, c);
        decreaseMana(c.getMana());
        board.placeCard(c, pos);
        System.out.println("Card: " + c.getType().getName() + " played at position: " + (pos.x + 1));
        Utility.ConsoleFunctions.wait(1000);
    }

    public int getHealth( ) {
        return health;
    }

    public void setHealth( int health ) {
        this.health = health;
    }

    public void takeDamage( int damage ) {
        this.health -= damage;
    }

    //returning false when shouldn't be
    public boolean hasCard( Card c ) {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getType().getName().equals(c.getType().getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean isWeapon( Card c ) {
        return Deck.WEAPONS.getCards().contains(c);
    }


    public String displayPlayer( boolean isEnemy ) {
        return isEnemy ?
                "Opponent's Hand\t\tMana: " + Utility.Colors.CYAN + getMana( ) + Utility.Colors.RESET + "\t\tHealth: " + Utility.Colors.RED + getHealth( ) + Utility.Colors.RESET + "\n" +
                        displayCardBacks( ) + "\n" +
                        "Weapon:\n" +
                        weapon.toString( ) :
                "Weapon: \n" +
                        weapon.toString( ) +
                        "\nYour Hand\t\tMana: " + Utility.Colors.CYAN + getMana( ) + Utility.Colors.RESET + "\t\tHealth: " + Utility.Colors.RED + getHealth( ) + Utility.Colors.RESET + "\n" +
                        displayCards( );
    }
}

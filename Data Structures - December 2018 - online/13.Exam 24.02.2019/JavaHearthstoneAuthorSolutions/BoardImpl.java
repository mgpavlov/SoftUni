import com.google.common.collect.Collections2;

import java.util.*;

public class BoardImpl implements Board {

    private HashMap<String, Card> deck;
    private Set<String> deathCards;
    private List<Set<String>> cardsByLevel;

    public BoardImpl() {
        this.deck = new HashMap<>();
        this.deathCards = new HashSet<>();
        this.cardsByLevel = new ArrayList<>(5) {{
            add(new HashSet<>());
            add(new HashSet<>());
            add(new HashSet<>());
            add(new HashSet<>());
            add(new HashSet<>());
        }};
    }

    @Override
    public void draw(Card card) {
        if (!this.deck.containsKey(card.getName())) {
            this.deck.put(card.getName(), card);
            this.cardsByLevel.get(card.getLevel() - 1).add(card.getName());
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public Boolean contains(String name) {
        return this.deck.containsKey(name);
    }

    @Override
    public int count() {
        return this.deck.size();
    }

    @Override
    public void play(String attackerCardName, String attackedCardName) {

        if (!this.deck.containsKey(attackedCardName) || !this.deck.containsKey(attackerCardName)) {
            throw new IllegalArgumentException();
        }
        Card attacker = this.deck.get(attackerCardName);
        Card attacked = this.deck.get(attackedCardName);

        if (attacked.getHealth() <= 0) return;

        if (attacked.getLevel() - attacker.getLevel() != 0) {
            throw new IllegalArgumentException();
        }
        attacked.setHealth(attacked.getHealth() - attacker.getDamage());
        if (attacked.getHealth() <= 0) {
            attacker.setScore(attacker.getScore() + attacked.getLevel());
            this.deathCards.add(attacked.getName());
        }

    }

    @Override
    public void remove(String name) {
        Card card = null;
        if (this.deck.containsKey(name)) {
            card = this.deck.remove(name);
            this.deathCards.remove(card.getName());
        }

        if (card == null) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void removeDeath() {
        for (String deathCard : this.deathCards) {
            this.deck.remove(deathCard);
        }
    }

    @Override
    public Iterable<Card> getBestInRange(int start, int end) {
        List<Card> result = new ArrayList<>(Collections2.filter(this.deck.values(), a -> a.getScore() >= start && a.getScore() <= end));
        result.sort((a, b) -> b.getLevel() - a.getLevel());
        return result;

    }

    @Override
    public Iterable<Card> listCardsByPrefix(String prefix) {
        List<Card> allCards = new ArrayList<>(this.count());
        allCards.addAll(this.deck.values());
        ArrayList<Card> res = new ArrayList<>(Collections2.filter(allCards, a -> a.getName().startsWith(prefix)));
        res.sort((a, b) -> {
            String reveresedA = new StringBuilder(a.getName()).reverse().toString();
            String reveresedB = new StringBuilder(b.getName()).reverse().toString();
            if (reveresedA.compareTo(reveresedB) == 0) {
                return a.getLevel() - b.getLevel();
            }
            return reveresedA.compareTo(reveresedB);
        });
        return res;
    }

    @Override
    public Iterable<Card> searchByLevel(int level) {
        Set<String> cardsByLevel = this.cardsByLevel.get(level - 1);
        List<Card> cards = new ArrayList<>();
        for (String cardName : cardsByLevel) {
            Card card = null;
            if (this.deck.containsKey(cardName)) {
                card = this.deck.get(cardName);
            }
            cards.add(card);
        }
        cards.sort((a, b) -> b.getScore() - a.getScore());
        return cards;
    }

    @Override
    public void heal(int health) {
        List<Card> allCards = new ArrayList<>(this.deck.values());
        allCards.sort(Comparator.comparingInt(Card::getHealth));
        Card toHeal = allCards.get(0);
        toHeal.setHealth(toHeal.getHealth() + health);
        if (toHeal.getHealth() > 0) {
            this.deathCards.remove(toHeal.getName());
        }
    }
}

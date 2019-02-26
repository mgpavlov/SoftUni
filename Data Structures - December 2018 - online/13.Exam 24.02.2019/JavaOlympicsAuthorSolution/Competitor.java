public class Competitor implements Comparable<Competitor> {

    private int id;
    private String name;
    private long totalScore;

    public Competitor(int id, String name) {
        this.totalScore = 0;
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(long totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) return false;
        if (!(obj instanceof Competitor))
            return false;
        if (obj == this)
            return true;

        return this.getId() == ((Competitor) obj).getId();
    }

    @Override
    public int compareTo(Competitor o) {
        if (this.getName().compareTo(o.getName()) == 0) {
            return (int)(this.getTotalScore() - o.getTotalScore());
        }

        return this.getName().compareTo(o.getName());
    }
}

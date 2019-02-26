import com.google.common.collect.Collections2;
import com.google.common.collect.Sets;

import java.util.*;

public class OlympicsImpl implements Olympics {

    private final HashMap<Integer, Competitor> competitorMap;
    private final HashMap<Integer, Competition> competitionMap;
    private final TreeMap<String, TreeSet<Integer>> nameAndIds;

    public OlympicsImpl() {
        this.competitionMap = new HashMap<>();
        this.competitorMap = new HashMap<>();
        this.nameAndIds = new TreeMap<>();
    }

    @Override
    public void addCompetitor(int id, String name) {
        if (!this.competitorMap.containsKey(id)) {
            competitorMap.put(id, new Competitor(id, name));
            nameAndIds.putIfAbsent(name, new TreeSet<>());
            nameAndIds.get(name).add(id);
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void addCompetition(int id, String name, int score) {

        if (!this.competitionMap.containsKey(id)) {

            competitionMap.put(id, new Competition(name, id, score));
        } else {
            throw new IllegalArgumentException();
        }

    }

    @Override
    public void compete(int competitorId, int competitionId) {

        if (!this.competitionMap.containsKey(competitionId) || !this.competitorMap.containsKey(competitorId)) {
            throw new IllegalArgumentException();
        }

        this.competitionMap.get(competitionId).addCompetitor(this.competitorMap.get(competitorId));
    }

    @Override
    public void disqualify(int competitionId, int competitorId) {

        if (!this.competitionMap.containsKey(competitionId) || !this.competitorMap.containsKey(competitorId)) {
            throw new IllegalArgumentException();
        }

        boolean isSuccess = this.competitionMap.get(competitionId).disqualify(this.competitorMap.get(competitorId));

        if (!isSuccess) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public Iterable<Competitor> findCompetitorsInRange(long min, long max) {
        Collection<Competitor> filter = Collections2.filter(this.competitorMap.values(), (c) -> c.getTotalScore() > min && c.getTotalScore() <= max);
        return filter;

    }

    @Override
    public Iterable<Competitor> getByName(String name) {
        if (this.nameAndIds.containsKey(name)) {


            TreeSet<Integer> integers = this.nameAndIds.get(name);
            List<Competitor> res = new ArrayList<>(integers.size());

            for (Integer integer : integers) {
                res.add(this.competitorMap.get(integer));
            }
            return res;
        }

        throw new IllegalArgumentException();
    }

    @Override
    public Iterable<Competitor> searchWithNameLength(int minLength, int maxLength) {

        Set<String> filter = Sets.filter(this.nameAndIds.keySet(), (n) -> n.length() >= minLength && n.length() <= maxLength);
        if (filter.isEmpty()) {
            return Collections.emptyList();
        }

        List<Competitor> res = new ArrayList<>(filter.size());
        for (String s : filter) {
            TreeSet<Integer> integers = this.nameAndIds.get(s);

            for (Integer integer : integers) {
                res.add(this.competitorMap.get(integer));
            }
        }
        res.sort(Comparator.comparingInt(Competitor::getId));
        return res;
    }

    @Override
    public Boolean contains(int competitionId, Competitor comp) {
        if (this.competitionMap.containsKey(competitionId)) {
            return this.competitionMap.get(competitionId).getCompetitors().contains(comp);
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int competitionsCount() {
        return this.competitionMap.size();
    }

    @Override
    public int competitorsCount() {
        return this.competitorMap.size();
    }

    @Override
    public Competition getCompetition(int id) {
        if (this.competitionMap.containsKey(id)) {
            return this.competitionMap.get(id);
        }
        throw new IllegalArgumentException();
    }

}

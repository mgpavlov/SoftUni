package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Engine implements Runnable {
    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }

    public void run() {

        try {
            this.getVillainsName();              // problem 2
            //this.printAllMinionNames();        // problem 7
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*try {
            this.getMinionName();               // problem 3
            //this.addMinion();                 // problem 4
            //this.changeTownNamesCasing();     // problem 5
            //this.removeVillain();             // problem 6
            //this.increaseMinionsAge();        // problem 8
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }*/


        /*try {
            this.increaseAgeStoredProcedure(); // problem 9
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    //Problem 2. Get Villains’ Names
    private void getVillainsName() throws SQLException {
        String query = "select v.name, count(m.minion_id) from villains v join minions_villains m on v.id = m.villain_id group by m.villain_id having  count(m.minion_id) > ? order by count(m.minion_id) desc";
        PreparedStatement preparedStatement = this.connection
                .prepareStatement(query);
        preparedStatement.setInt(1, 3);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            System.out.println(String.format("%s %d", resultSet.getString(1), resultSet.getInt(2)));
        }
    }

    //Problem 3. Get Minion Names
    private void getMinionName() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int vId = Integer.parseInt(reader.readLine());

        PreparedStatement villainNameStatement =
                connection.prepareStatement(
                        "select v.name as name\n" +
                                "from villains as v\n" +
                                "where v.id=?");


        villainNameStatement.setInt(1, vId);
        ResultSet resultSetName = villainNameStatement.executeQuery();

        resultSetName.next();

        try {
            System.out.println("Villain: " + resultSetName.getString("name"));
            PreparedStatement villainAndHisMinions =
                    connection.prepareStatement("select distinct m.name as name,m.age as age\n" +
                            "from minions as m\n" +
                            "join minions_villains as mv on mv.minion_id=m.id\n" +
                            "join villains as v on mv.villain_id=v.id\n" +
                            "where v.id=?");
            villainAndHisMinions.setInt(1, vId);
            ResultSet resultSetVM = villainAndHisMinions.executeQuery();

            int counter = 1;
            if (!resultSetVM.next()) {
                System.out.println("<no minions>");
            } else {
                while (resultSetVM.next()) {
                    System.out.println(counter + ". " + resultSetVM.getString("name") + " " +
                            resultSetVM.getString("age"));
                    counter++;
                }
            }
        } catch (SQLException ex) {
            System.out.println("No villain with ID " + vId + " exists in the database.");
        }
    }

    //Problem 4. Add minion
    private void addMinion() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] minionParams = reader.readLine().split("\\s+");
        String[] villainParams = reader.readLine().split("\\s+");

        String minionName = minionParams[1];
        int minionAge = Integer.parseInt(minionParams[2]);
        String townName = minionParams[3];
        String villainName = villainParams[1];

        if (!this.checksIfEntityExist(townName, "towns")) {
            this.insertTown(townName);
        }
        if (!this.checksIfEntityExist(villainName, "villains")) {
            this.insertVillain(villainName);
        }

        int townId = this.getEntityId(townName, "towns");
        this.insertMinion(minionName, minionAge, townId);

        int minionId = this.getEntityId(minionName, "minions");
        int villainId = this.getEntityId(villainName, "villains");

        this.hireMinion(minionId, villainId);

        System.out.printf("Successfully added %s to be minion of %s.", minionName, villainName);
    }

    private void insertVillain(String villainName) throws SQLException {
        String query = "INSERT INTO villains(name, evilness_factor) VALUES('" + villainName + "', 'evil')";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        preparedStatement.execute();

        System.out.printf("Villain %s was added to the database.", villainName);
        System.out.println();
    }

    private void insertTown(String townName) throws SQLException {
        String query = "INSERT INTO towns (name, country) VALUES ('" + townName + "', NULL)";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        preparedStatement.execute();
        System.out.printf("Town %s was added to the database.", townName);
        System.out.println();
    }

    private boolean checksIfEntityExist(String name, String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName + " WHERE name = ?";
        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    private int getEntityId(String name, String tableName) throws SQLException {
        String query = "SELECT id FROM " + tableName + " WHERE name = '" + name + "'";

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private void insertMinion(String minionName, int age, int townId) throws SQLException {
        String query = String.format("INSERT INTO minions(name, age, town_id) VALUES('%s', %d, %d)", minionName, age, townId);

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);
        preparedStatement.execute();
    }

    private void hireMinion(int minionId, int villainId) throws SQLException {
        String query = String.format("INSERT INTO minions_villains(minion_id, villain_id) VALUES(%d, %d)"
                , minionId, villainId);

        PreparedStatement preparedStatement = this.connection.prepareStatement(query);

        preparedStatement.execute();
    }

    // Problem 5. Change Town Names Casing
    private void changeTownNamesCasing() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String country = reader.readLine();

        PreparedStatement preparedStatement =
                connection.prepareStatement("update towns\n" +
                        "set name=UPPER(name)\n" +
                        "where country = ?;");

        preparedStatement.setString(1, country);
        preparedStatement.executeUpdate();

        PreparedStatement prpGet =
                connection.prepareStatement("select t.name\n" +
                        "from towns as t\n" +
                        "where country = ?;");
        prpGet.setString(1, country);
        ResultSet rsGet = prpGet.executeQuery();


        if (rsGet.isBeforeFirst()) {
            List<String> towns = new ArrayList<>();
            int townsCount = 0;
            while (rsGet.next()) {
                towns.add(rsGet.getString("name"));
                townsCount++;
            }
            System.out.println(townsCount + " town names were affected. ");
            System.out.println("[" + String.join(", ", towns) + "]");
        } else {
            System.out.println("No town names were affected.");
        }

    }

    //Problem 6. *Remove Villain
    private void removeVillain() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int villainId = Integer.parseInt(reader.readLine());
        this.connection.setAutoCommit(false);

        PreparedStatement setForeignKeyChecks = this.connection.prepareStatement(
                "SET FOREIGN_KEY_CHECKS=?;");
        setForeignKeyChecks.setInt(1, 0);
        setForeignKeyChecks.executeUpdate();

        PreparedStatement getVillainName = this.connection.prepareStatement(
                "SELECT v.name FROM villains AS v " +
                        "WHERE v.id = ?");
        getVillainName.setInt(1, villainId);
        ResultSet rs = getVillainName.executeQuery();
        String villainName;

        if (rs.next()) {

            villainName = rs.getString("name");

            PreparedStatement releaseMinions = this.connection.prepareStatement(
                    "DELETE FROM minions_villains WHERE villain_id = ?;");
            releaseMinions.setInt(1, villainId);
            int affectedRows = releaseMinions.executeUpdate();

            PreparedStatement deleteVillain = this.connection.prepareStatement(
                    "DELETE FROM villains WHERE id = ?;");
            deleteVillain.setInt(1, villainId);
            deleteVillain.executeUpdate();

            System.out.println(villainName + " was deleted");
            System.out.println(affectedRows + " minions released");

        } else {
            System.out.println("No such villain was found");
        }

        setForeignKeyChecks.setInt(1, 1);
        setForeignKeyChecks.executeUpdate();
        this.connection.commit();
    }

    //Problem 7. Print All Minion Names
    private void printAllMinionNames() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("select m.name\n" +
                        "from minions as m\n" +
                        "where m.id=?;");

        PreparedStatement stmCount =
                connection.prepareStatement("select count(m.name) as 'count'\n" +
                        "from minions as m;");
        ResultSet rsCount = stmCount.executeQuery();
        rsCount.next();
        int count = rsCount.getInt("count");

        int i2 = count;
        boolean alternate = false;
        int minus = 0;
        if (count % 2 != 0) {
            minus = 1;
        } else {
            minus = 2;
        }
        for (int i = 1; i < count - minus; i++) {
            PreparedStatement stmGet =
                    connection.prepareStatement("select m.name\n" +
                            "from minions as m\n" +
                            "where m.id=?;");
            if (!alternate) {
                stmGet.setInt(1, i);
                alternate = true;
            } else {
                stmGet.setInt(1, i2);
                i2--;
                i--;
                alternate = false;
            }
            ResultSet rsGet = stmGet.executeQuery();
            rsGet.next();
            System.out.println(rsGet.getString("name"));
        }
    }

    //Problem 8. Increase Minions Age
    private void increaseMinionsAge() throws SQLException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        this.connection.setAutoCommit(false);
        int[] ids = Arrays.stream(reader.readLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
        String increaseAge = "UPDATE minions AS m SET m.age = m.age + 1 " +
                "WHERE m.id IN (?);";
        String titleCase = "UPDATE minions AS m SET m.name = " +
                "concat(upper(left(m.name, 1)), substr(m.name, 2)) " +
                "WHERE m.id IN (?);";

        createAndExecuteStatement(this.connection, ids, increaseAge);
        createAndExecuteStatement(this.connection, ids, titleCase);
        printResult(this.connection);
        this.connection.commit();
    }

    private static void printResult(Connection conn) throws SQLException {
        Statement printAllMinions = conn.createStatement();
        ResultSet rs = printAllMinions.executeQuery(
                "SELECT m.name, m.age FROM minions AS m;");
        while (rs.next()) {
            System.out.printf("%s %d\n",
                    rs.getString("name"),
                    rs.getInt("age"));
        }
    }

    private static void createAndExecuteStatement(Connection conn, int[] ids, String stm) throws SQLException {
        // create the same number of placeholders as are the input ids
        StringBuilder sb = new StringBuilder();
        for (int id : ids) {
            sb.append("?,");
        }

        // put them into a query
        stm = stm.replace("?", sb.deleteCharAt(sb.length() - 1));
        PreparedStatement ps = conn.prepareStatement(stm);

        // assign the id values to all placeholders
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            int position = i + 1;
            ps.setInt(position, id);
        }
        ps.executeUpdate();
    }

    //Problem 9.  Increase Age Stored Procedure
    private void increaseAgeStoredProcedure() throws IOException {
        String getOlderSQL = "{CALL usp_get_older (?)}";
        String minionSQL = "SELECT m.name, m.age FROM `minions` AS m WHERE id = ?";

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int id = Integer.parseInt(reader.readLine());

        try (CallableStatement getOlderStoredProcedure = connection.prepareCall(getOlderSQL);
             PreparedStatement minionsStatement = connection.prepareStatement(minionSQL)) {

            getOlderStoredProcedure.setInt(1, id);
            getOlderStoredProcedure.execute();

            minionsStatement.setInt(1, id);
            try (ResultSet minions = minionsStatement.executeQuery()) {
                minions.first();
                System.out.println(minions.getString("name") +
                        " " + minions.getInt("age"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}

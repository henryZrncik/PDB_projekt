package utilities;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

public class Neo4JUtility {

    private static Driver driver = null;

    public static Driver getDriver() {

        if (Neo4JUtility.driver == null) {
            Neo4JUtility.driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "d1"));
        }
        return Neo4JUtility.driver;
    }




}

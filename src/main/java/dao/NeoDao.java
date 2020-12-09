package dao;

import domain.neo.Account;
import domain.neo.Send;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import utilities.Neo4JUtility;

import static org.neo4j.driver.Values.parameters;

public class NeoDao {
    public static void insertAccount(final String message){
        try ( Session session = Neo4JUtility.getDriver().session()) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx ) {
                    Result result = tx.run( "CREATE (a:Greeting {message: $message}) " +
                                    "RETURN a.message + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            });
            System.out.println( greeting );
        }
    }
    public static void createAccount(final Account account){
        try ( Session session = Neo4JUtility.getDriver().session()) {
            String greeting = session.writeTransaction( new TransactionWork<String>() {
                @Override
                public String execute( Transaction tx ) {
                    Result result = tx.run( "CREATE (a: Account {ownerFirstName: $ownerFirstName, ownerLastName: $ownerLastName, accountId: $accountId, ownerIsFrom: $ownerIsFrom}) " +
                                    "RETURN a.ownerFirstName + ', from node ' + id(a)",
                            parameters( "ownerFirstName", account.getOwnerFirstName() ,
                                    "ownerLastName" , account.getOwnerSecondName() ,
                                    "ownerIsFrom", account.getOwnerIsFrom(),
                                    "accountId", account.getAccountId()
                            ) );
                    return result.single().get( 0 ).asString();
                }
            });
            System.out.println( greeting );
        }
    }

    public static Result createTransaction(final Send send){
        try ( Session session = Neo4JUtility.getDriver().session()) {
            Result greeting = session.writeTransaction(new TransactionWork<Result>() {
                @Override
                public Result execute(Transaction tx ) {
                    Result result = tx.run("MATCH (a1 :Account {accountId: $sourceId})" +
                                    " MATCH (a2 : Account {accountId:$destinationId })" +
                                    " CREATE (a1) - [t :TRANSACTION {id: $transactionId , sum:$sum}] -> (a2)  return a1, t, a2",
                            parameters(
                                    "transactionId" , send.getTransactionId(),
                                    "sourceId",send.getSourceAccId(),
                                    "destinationId", send.getDestinationAccId(),
                                    "sum", send.getSum()
                            ) );
                    return result;
                }
            });
            System.out.println( greeting );
            return greeting;
        }
    }
    //    MATCH (n: Account {accountId : 1})-[*]-(m: Account {accountId: 3}) RETURN n.accountId;
    public static Boolean areAccountsConnected(int sourceId, int destinationId){
        try ( Session session = Neo4JUtility.getDriver().session()) {
            Boolean greeting = session.writeTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction tx ) {
                    Result result = tx.run("MATCH (n: Account {accountId : $sourceId})-[*]-(m: Account {accountId: $destinationId}) RETURN n.accountId",
                            parameters(
                                    "sourceId", sourceId,
                                    "destinationId", destinationId
                            ) );
                    if(result.hasNext()){
                        return true;
                    }
                    return false;
                }
            });

            return greeting;
        }

    }
    public static int accountsShortestPath(int sourceId, int destinationId){
        try ( Session session = Neo4JUtility.getDriver().session()) {
            Object greeting = session.writeTransaction(new TransactionWork<Object>() {
                @Override
                public Object execute(Transaction tx ) {
                    Result result = tx.run("MATCH (a1:Account { accountId: $sourceId }),(a2:Account { accountId: $destinationId }), p = shortestPath((a1)-[*..15]-(a2))  RETURN length(p)",
                            parameters(
                                    "sourceId", sourceId,
                                    "destinationId", destinationId
                            ) );
                    if (result.hasNext()){

                        int  o  = result.single().get( 0 ).asInt();
                        System.out.println(o);
                        return  o;

                    }
                    System.out.println("no connection between nodes");
                    return 0;

                }
            });

            return (int) greeting;
        }

    }
}


package API;


import APIdomain.Acc;
import APIdomain.Response;
import APIdomain.Transaction;
import APIdomain.Trends;
import com.google.gson.Gson;
import dao.AccountDao;
import dao.ManipulationDao;
import org.bson.Document;

import javax.print.Doc;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

    @WebServlet("/accTransaction")
    public class AccTransactionS extends HttpServlet {
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            int accountId =Integer.parseInt(request.getParameter("id"));
            System.out.println("API: Get/accTransaction : param id: " + accountId);
            boolean isGoodAcc =  AccountDao.validate(accountId);

            Gson gson = new Gson();
            PrintWriter printWriter = response.getWriter();
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            if (! isGoodAcc){
                Response response1 = new Response("wrong");
                String accJson = gson.toJson(response1);
                printWriter.write(accJson);
                printWriter.close();
                return;
            }
            Document accountMongo = AccountDao.getAccountMongo(accountId);
            List<Document> accountManipulations = (List<Document>) accountMongo.get("accountManipulations");
            List<Transaction> transactions = new LinkedList<>();


            for (Document d : accountManipulations){
                if( (boolean) d.get("isTransaction")){
                    int sum = d.getInteger("total", 0);
                    int destinationAccount = d.getInteger("destinationAccountId", 0);
                    String date = d.getString("date");
                    int transactionId = d.getInteger("transactionId");
                    boolean isIN = d.getBoolean("isIN");
                    transactions.add(new Transaction(transactionId,sum, destinationAccount,date,isIN ));
                }
            }

            String accJson = gson.toJson(transactions);
            printWriter.write(accJson);
            printWriter.close();



        }
    }


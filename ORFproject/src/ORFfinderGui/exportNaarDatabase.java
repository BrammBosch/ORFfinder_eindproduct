package ORFfinderGui;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class exportNaarDatabase extends JFrame {


    private static BufferedReader br;
    private static StringBuilder sb = new StringBuilder();
    public static String everything, legenda;

    public static void bestandLezen() throws IOException {
        try {
            br = new BufferedReader(new FileReader("blastOutput.txt"));
            String line = br.readLine();

            while (line != null){
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }

            everything = sb.toString();
            legenda = "Acc. code\tE-value\tIdentity\tQuery Cover\tTotal Score\n";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            br.close();
        }


    }

        /*
    public class databaseConnectie {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/*naamapplicatie*","*user*","*password*");

            Statement stmt;


            try {
                stmt = con.createStatement();
            } catch (SQLException e2) {
                e2.printStackTrace();
            }


        ResultSet rs=stmt.executeQuery("select * from emp");
            while(rs.next())
                System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        } catch(Exception e){
            System.out.println(e);}

    // https://www.javatpoint.com/example-to-connect-to-the-mysql-databas
    */



    // nieuwe gui
    // vragen welke opslaan
    // opslaan dmv connectie
}

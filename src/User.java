import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
abstract public class User
{
    int ID;
    String username;
    String password;
    String type;///owner or normal
    static User loggedInUser;
    static ArrayList<User> allUsers = new ArrayList<>();
    String securityQuestion;///enums might be needed
    String securityAnswer;
    User(int ID,String username, String password, String type)
    {
        this.ID = ID;
        this.username = username;
        this.password = password;
        this.type = type;
    }
    User(){}
    public static User findUserByID(int ID)
    {
        for(int i=0; i<allUsers.size(); i++)
            if(allUsers.get(i).ID==ID)
                return allUsers.get(i);
        return null;
    }
    public static User findUserByUsername(String username)
    {
        for(int i=0; i<allUsers.size(); i++)
            if(allUsers.get(i).username.equals(username))
                return allUsers.get(i);
        return null;
    }
    public static void getAllUsersFromDataBaseAndHistoryAndOwnedRestaurants()throws SQLException
    {
        allUsers.clear();
        try
        {
            String query = "SELECT * FROM users;";
            Statement statement = SQL.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                int id = resultSet.getInt("ID");
                double charge = resultSet.getDouble("Charge");
                String Class = resultSet.getString("class");
                int location = resultSet.getInt("Location");
                String pass = resultSet.getString("Pass");
                String username = resultSet.getString("Username");
                if(Class.equals("Owner"))
                {
                    Owner inuse = new Owner(id,username,pass,Class);
                    allUsers.add(inuse);
                    try {
                        String query1 = "SELECT * FROM user_restaurant;";
                        Statement statement1 = SQL.connection.createStatement();
                        ResultSet resultSet1 = statement1.executeQuery(query1);
                        while (resultSet1.next()) {
                            if (resultSet1.getInt("userID") == id)///////////
                              inuse.ownedRestaurants.add(Restaurant.findRestaurantByID(resultSet1.getInt("restaurantID")));
                        }
                        resultSet1.close();
                        statement1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                else if(Class.equals("Normal"))
                {
                    Normal inuse = new Normal(id,username,pass,Class,Node.getNodeByID(location),charge);
                    allUsers.add(inuse);
                    try {
                        String query1 = "SELECT * FROM user_order;";
                        Statement statement1 = SQL.connection.createStatement();
                        ResultSet resultSet1 = statement1.executeQuery(query1);
                        while (resultSet1.next()) {
                            if (resultSet1.getInt("userID") == id)///////////
                                inuse.userHistory.add(Order.findOrderByID(resultSet1.getInt("orderID")));
                        }
                        resultSet1.close();
                        statement1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void getOnlyUserFromDtaBase()
    {
        try
        {
            String query = "SELECT * FROM users;";
            Statement statement = SQL.connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                int id = resultSet.getInt("ID");
                double charge = resultSet.getDouble("Charge");
                String Class = resultSet.getString("class");
                int location = resultSet.getInt("Location");
                String pass = resultSet.getString("Pass");
                String username = resultSet.getString("Username");
                if(Class.equals("Owner"))
                {
                    Owner inuse = new Owner(id,username,pass,Class);
                    allUsers.add(inuse);
                }
                else if(Class.equals("Normal"))
                {
                    Normal inuse = new Normal(id,username,pass,Class,Node.getNodeByID(location),charge);
                    allUsers.add(inuse);
                }
            }
            resultSet.close();
            statement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}

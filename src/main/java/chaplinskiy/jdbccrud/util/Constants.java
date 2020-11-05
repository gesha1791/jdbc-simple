package chaplinskiy.jdbccrud.util;

public final class Constants {
    // Connection
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost/crud";
    static final String USER = "root";
    static final String PASSWORD = "admin";

    // Runner view
    public static final String runnerViewMessage = "Hello, please enter number: \n"
            + "1) User\n" + "2) Post\n" + "3) Region\n" + "4) Exit";
    public static final String wrongRunnerMessage = "Wrong number\n" + "Enter number from 1 to 4";

    // Region view
    public static final String regionViewMessage = "Choose option for region, please : \n"
            + "1) Show all regions\n" + "2) Create new region\n" + "3) Delete region\n" + "4) Update region\n"
            + "5) Get region\n" + "6) Back to menu";
    public static final String wrongRegionMessage = "Wrong number\n" + "Enter number from 1 to 6";
    public static final String createUpdateRegionNameMessage = "Please enter region name";
    public static final String idRegionMessage = "Please enter id region";

    // Post view
    public static final String postViewMessage = "Choose option for post, please : \n"
            + "1) Show all posts\n" + "2) Create new post\n" + "3) Delete post\n" + "4) Update post\n"
            + "5) Get post\n" + "6) Back to menu";
    public static final String wrongPostMessage = "Wrong number\n" + "Enter number from 1 to 6";
    public static final String createUpdatePostValueMessage = "Please enter post value";
    public static final String idPostMessage = "Please enter id post";

    // User view
    public static final String userViewMessage = "Choose option for user, please : \n"
            + "1) Show all users\n" + "2) Create new user\n" + "3) Delete user\n" + "4) Update user\n"
            + "5) Get user\n" + "6) Back to menu";
    public static final String wrongUserMessage = "Wrong number\n" + "Enter number from 1 to 6";
    public static final String createFirstNameUser = "Please enter firstName value";
    public static final String createSecondNameUser = "Please enter secondName value";
    public static final String createRegionNameUser = "Please enter region value";
    public static final String idUserMessage = "Please enter id user";
    public static final String countUserPostMessage = "Please enter count posts for user";



    // SQL commands
    // Region database
    public static final String getAllRegionSQL = "SELECT * FROM region";
    public static final String createRegionSQL = "INSERT INTO region (NAME) VALUES (?)";
    public static final String deleteRegionSQL = "DELETE FROM region WHERE ID = (?)";
    public static final String getRegionSQL = "SELECT * FROM region WHERE ID = (?)";
    public static final String updateRegionSQL = "UPDATE region SET name = (?) WHERE ID = (?)";

    // Post database
    public static final String getAllPostSQL = "SELECT * FROM post";
    public static final String createPostSQL = "INSERT INTO post (CONTENT, CREATED, UPDATED) VALUES (?, ?, ?)";
    public static final String deletePostSQL = "DELETE FROM post WHERE ID = (?)";
    public static final String getPostSQL = "SELECT * FROM post WHERE ID = (?)";
    public static final String updatePostSQL = "UPDATE post SET content = ?, updated = ? WHERE ID = ?";

    // User database
    public static final String getAllUserSQL =
            "SELECT user.id, user.firstName, user.lastName, " +
                    "region.name, roleuser.role " +
                    "FROM user " +
                    "JOIN region ON region.id = user.region_id " +
                    "JOIN roleuser ON roleuser.id = user.role_id";

    public static final String getAllPostUserByID = "SELECT post.id, post.content" +
            " FROM user " +
            "JOIN user_post ON user.id = user_post.id_user " +
            "JOIN post ON post.id = user_post.id_post " +
            "WHERE user.id = (?)";

    public static final String getUserById =
            "SELECT user.id, user.firstName, user.lastName, " +
                    "region.name, roleuser.role " +
                    "FROM user " +
                    "JOIN region ON region.id = user.region_id " +
                    "JOIN roleuser ON roleuser.id = user.role_id " +
                    "WHERE user.id = (?)";

    public static final String deleteUserSQL = "DELETE FROM user WHERE ID = (?)";

    public static final String createUserSQL = "INSERT INTO user (firstName, lastName, region_id, role_id) VALUES (?, ?, ?, ?)";
    public static final String updateUserSQL = "UPDATE user SET firstName = (?), lastName = (?), region_id = (?) WHERE ID = (?)";

}

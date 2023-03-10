package dao.constants;

public class UserMysqlQuery {
    public static final String ADD_USER = "INSERT INTO user (login, password, first_name, surname) VALUES (?, ?, ?, ?)";

    public static final String GET_BY_EMAIL = "SELECT * FROM user WHERE login=?";

    public static final String GET_ALL = "SELECT * FROM user ";

    public static final String GET_PAGINATION = "LIMIT ?,?";

    public static final String UPDATE = "UPDATE user SET login=?, password=?, first_name=?, surname=?, role_id=?, blocked=? WHERE id=?";

    public static final String CHANGE_PASSWORD = "UPDATE user SET password=? WHERE id=?";

    public static final String UPDATE_PASSWORD = "UPDATE user SET password=? WHERE id=";

    public static final String DELETE = "DELETE FROM user WHERE id=?";

    public static final String GET_BY_ID = "SELECT * FROM user WHERE id=?";

    public static final String GET_NUMBER_OF_RECORDS = "DELETE user WHERE id=?";

    public static final String SET_USER_ROLE = "";

    public static final String REGISTER_FOR_CRUISE = "DELETE user WHERE id=?";

    public static final String CANCEL_REGISTRATION = "DELETE user WHERE id=?";

    public static final String IS_REGISTERED = "DELETE user WHERE id=?";

    public static final String GET_COUNT = "SELECT COUNT(*) FROM user";

    public static final String ADD_DOCUMENTS = "INSERT INTO user_has_document (user_id, document) VALUES (?, ?)";
}

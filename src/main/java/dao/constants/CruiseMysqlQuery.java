package dao.constants;

public class CruiseMysqlQuery {
    public static final String ADD_CRUISE = "INSERT INTO cruise (ship_id, duration, start_day, paid) VALUES (?, ?, ?, ?, ?, ?, ?)";

    public static final String GET_BY_ID = "SELECT cruise WHERE id=?";

    public static final String GET_ALL = "SELECT cruise.id, ship_id, duration, price, start_day, paid, " +
            "s.name as ship_name, s.capacity as capacity, s.number_of_ports as number_of_ports FROM cruise " +
            "JOIN ship s on s.id = cruise.ship_id ";

    public static final String GET_BY_ALL_FILTERS = "WHERE start_day=? AND (duration >= ? AND duration <= ?) ";

    public static final String GET_BY_DURATION_FILTER = "duration >= ? AND duration <= ? ";

    public static final String GET_BY_START_DAY_FILTER = "WHERE start_day=? ";

    public static final String GET_PAGINATION = "LIMIT ?, ?";

    public static final String UPDATE = "UPDATE cruise WHERE id=?";

    public static final String DELETE = "DELETE cruise WHERE id=?";

    public static final String GET_BY_DURATION = "SELECT cruise WHERE duration=?";

    public static final String GET_BY_DATE = "DELETE cruise WHERE start_day=?";

    public static final String SET_SHIP = "SELECT * FROM cruise SET ship_id=? WHERE id=?";

    public static final String GET_SORTED = "SELECT * FROM cruise ORDER BY ?";

    public static final String GET_CRUISE_COUNT = "SELECT COUNT(*) FROM cruise ";



}

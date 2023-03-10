package services;

import com.google.protobuf.ServiceException;
import dto.CruiseDTO;
import dto.OrderDTO;
import dto.ShipDTO;
import dto.UserDTO;

import java.io.InputStream;
import java.sql.Date;
import java.util.List;

public interface GeneralService {

    UserDTO signIn(String login, String password) throws ServiceException;

    void register(String login, String password, String firstName, String surname) throws ServiceException;

    List<UserDTO> viewAllUsers() throws ServiceException;

//    List<CruiseDTO> viewUserCruisesWithPagination(long userId, int cruisePerPage, int pageNum) throws ServiceException;

    List<CruiseDTO> viewCatalogWithPagination(List<String> filters, int cruisePerPage, int pageNum) throws ServiceException;

    int getCruiseAmount(List<String> filters) throws ServiceException;

    List<UserDTO> viewAllUsersWithPagination(int cruisePerPage, int pageNum)  throws ServiceException;

    UserDTO getChosenUser(long userId) throws ServiceException;

    void updateCruise(CruiseDTO cruise) throws ServiceException;

    List<OrderDTO> getOrdersByUserAdmin(long userId, int cruisePerPage, int pageNum) throws ServiceException;

    CruiseDTO getChosenCruise(long cruiseId) throws ServiceException;

    List<CruiseDTO> viewCatalog(int cruisePerPage, int pageNum) throws ServiceException;

    List<ShipDTO> getAllShips() throws ServiceException;

    boolean addCruiseToUser(long userId, long cruiseId) throws ServiceException;

    List<OrderDTO> viewUserOrdersWithPagination(long loggedUserId, int cruisePerPage, int pageNum) throws ServiceException;

    void updateOrderPaymentStatus(long userId, long cruiseId, Date date) throws ServiceException;

    int getAmountWithFilters(List<String> filters) throws ServiceException;

    int getUserAmount() throws ServiceException;

    void updateUser(UserDTO user) throws ServiceException;

    void changePassword(String login, String oldPassword, String newPassword, String confirmPassword) throws ServiceException;

    void addDocumentsToUser(long userId, InputStream inputStream) throws ServiceException;
}

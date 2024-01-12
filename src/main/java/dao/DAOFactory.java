package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory(){
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory==null)?daoFactory=new DAOFactory():daoFactory;
    }
    public enum DAOTypes{
        CUSTOMER,EMPLOYEE,ITEM,ORDER,PAYMENT,SUPPLIER
    }
    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}

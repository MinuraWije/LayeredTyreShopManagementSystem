package bo;

import bo.custom.impl.CustomerBOImpl;
import bo.custom.impl.EmployeeBOImpl;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.OrderBOImpl;
import bo.custom.impl.PaymentBOImpl;
import bo.custom.impl.PlaceOrderBOImpl;
import bo.custom.impl.SupplierBOImpl;
public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){

    }
    public static BOFactory getBOFactory(){
        return (boFactory==null)?boFactory=new BOFactory():boFactory;
    }
    public enum BOTypes{
        CUSTOMER,EMPLOYEE,ITEM,ORDER,PAYMENT,PLACE_ORDER,SUPPLIER
    }
    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            default:
                return null;
        }
    }
}

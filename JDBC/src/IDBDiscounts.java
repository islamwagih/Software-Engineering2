import java.sql.SQLException;
import java.util.Date;

public interface IDBDiscounts
{

    Double getAllDiscounts ( Date date, int id ) throws SQLException;

    void addDiscountByDate ( Date date , double discount);

    void addDiscountByArea ( String area, double discount );


}

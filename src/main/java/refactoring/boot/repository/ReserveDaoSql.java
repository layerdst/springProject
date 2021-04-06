package refactoring.boot.repository;

public class ReserveDaoSql {
    protected static final String SELECT_DISPAYINFO ="select " +
            "	 c.id as category_id, " +
            "    c.name as category_name, " +
            "    d.create_date as create_date, " +
            "	 d.product_id as product_id, " +
            "	 d.id as display_info_id, " +
            "    d.email as email, " +
            "    d.homepage as homepage, " +
            "    d.modify_date as modify_date, " +
            "    d.opening_hours as opening_hours, " +
            "    d.place_lot as place_lot, " +
            "    d.place_name as place_name, " +
            "    d.place_street as place_street, " +
            "    p.content as product_content, " +
            "    p.description as product_description, " +
            "    p.event as product_event, " +
            "    p.id as product_id, " +
            "    d.tel as telephone " +
            "from display_info d " +
            "inner join product p " +
            "	on d.product_id=p.id " +
            "inner join category c " +
            "	on p.category_id = c.id " +
            "where d.id = :id ";

    protected static final String SELECT_PRICE_LIST ="select " +
            "	id as product_price_id, " +
            "	product_id, " +
            "	price_type_name, " +
            "	price, " +
            "	discount_rate, " +
            "	create_date, " +
            "	modify_date " +
            "from product_price " +
            "where product_id in ( " +
            "	select product_id from display_info where id = :id " +
            "    ) " +
            "order by price";

    protected static final String SELECT_PRODUCT_IMGS_MA ="select  " +
            "	pi.product_id as product_id, " +
            "	pi.id as product_image_id, " +
            "	pi.type as type, " +
            "	f.id as file_info_id, " +
            "	f.file_name as file_name, " +
            "	f.save_file_name as save_file_name, " +
            "	f.content_type as content_type, " +
            "	f.delete_flag as delete_flag, " +
            "	f.create_date as create_date, " +
            "	f.modify_date as modify_date " +
            "from product_image pi " +
            "inner join file_info f " +
            "	on f.id = pi.file_id " +
            "where pi.product_id in ( " +
            "	select product_id from display_info where id = :id " +
            "    ) and pi.type=\"ma\"";

    protected static final String SELECT_DISPLAY_IMG ="select  " +
            "	di.id as display_info_image_id, " +
            "	d.id as display_info_id, " +
            "	f.id as file_id, " +
            "	f.file_name as file_name, " +
            "	f.save_file_name as save_file_name, " +
            "	f.content_type as content_type, " +
            "	f.delete_flag as delete_flag, " +
            "	f.create_date as create_date, " +
            "	f.modify_date as modify_date " +
            "from display_info d " +
            "inner join display_info_image di " +
            "	on d.id = di.display_info_id " +
            "inner join file_info f " +
            "	on f.id = di.file_id " +
            "where d.id = :id ";

    protected static final String SELECT_RESERV = "select display_info_id, " +
            "	product_id, " +
            "	reservation_name, " +
            "    reservation_email, " +
            "    reservation_tel as reservation_telephone, " +
            "    reservation_date, " +
            " 	 create_date, " +
            "    modify_date, " +
            "    cancel_flag as cancel_yn, " +
            "    id as reservation_id  " +
            "from reservation_info " +
            "where id = :id";


    protected static final String SELECT_PRICES = "select count, reservation_info_id, id as reservation_info_price_id, product_price_id " +
                                    "from reservation_info_price " +
                                    "where reservation_info_id = :id ";


    protected static final String INSERT_RESERV_INFO = "insert into reservation_info " +
            "	(id, product_id, display_info_id, " +
            "	reservation_name, reservation_tel, " +
            "	reservation_email, reservation_date, "  +
            " create_date, modify_date ) " +
            " values ( :id,  :product_id , " +
            " :display_info_id, :reservation_name, " +
            " :reservation_tel, :reservation_email, " +
            " DATE_ADD(now(), INTERVAL 5 DAY), now(), now())";


    protected static final String INSERT_RESERV_PRICES = "insert into reservation_info_price "+
            "(reservation_info_id, product_price_id, count) " +
            " values ( :reservation_info_id , :product_price_id , :count )";


    protected static final String SELECT_RESERV_VIEW= "select if(ri.cancel_flag,'true','false') as cancel_yn, " +
            "	ri.create_date as create_date, " +
            "    ri.display_info_id as display_info_id, " +
            "    ri.modify_date as modify_date, " +
            "    ri.product_id as product_id, " +
            "    ri.reservation_date as reservation_date, " +
            "    ri.reservation_email as reservation_email, " +
            "    ri.id as reservation_info_id, " +
            "    ri.reservation_name as reservation_name, " +
            "    ri.reservation_tel as reservation_Telephone, " +
            "	tt.total_price, " +
            "    tt.discount_total_price " +
            "from reservation_info ri " +
            "inner join ( " +
            "		select  " +
            "			rp.reservation_info_id as id, " +
            "			sum(pp.price*rp.count) as total_price, " +
            "			round(sum(pp.price*rp.count*(100-pp.discount_rate)/100)) as discount_total_price " +
            "		from reservation_info_price rp " +
            "		left outer join product_price pp " +
            "			on rp.product_price_id = pp.id  " +
            "		group by rp.reservation_info_id " +
            "	) as tt  " +
            "	on ri.id = tt.id " +
            "where ri.reservation_email = :email " +
            "order by ri.id desc ";


    protected static final String CANCEL_RESERVATION = "update reservation_info " +
            "set  cancel_flag = 1 " +
            "where id = :id and reservation_email = :email ";

    protected static final String CHECK_CANCELFLAG = "select count(id)  " +
            "from reservation_info  " +
            "where id = :id  " +
            "	and reservation_email = :email " +
            "	and cancel_flag=0 " +
            "   and reservation_date > now()" ;

    protected static final String SELELCT_CANCEL_RESERVATION_INFO = "SELECT " +

            " id as reservation_info_id," +
            " product_id, " +
            " display_info_id," +
            " reservation_name, " +
            " reservation_tel as reservation_telephone, " +
            " reservation_email, " +
            " reservation_date, " +
            " cancel_flag as cancel_yn, " +
            " create_date, " +
            " modify_date " +
            " FROM reservation_info " +
            " WHERE id = :id ";

    protected static final String SELECT_CANCEL_PRICES = "SELECT " +
            " id as reservation_info_price_id, " +
            " reservation_info_id, " +
            " product_price_id, " +
            " count " +
            "FROM reservation_info_price " +
            "where reservation_info_id = :id ";


}

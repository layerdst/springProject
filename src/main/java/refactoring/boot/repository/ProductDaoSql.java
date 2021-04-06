package refactoring.boot.repository;

public class ProductDaoSql {

    protected static final String SELECT_CATEGORY_LIST = "select c.id, c.name, count(*) as count from category c " +
            "inner join product p " +
            "on p.category_id = c.id " +
            "inner join display_info d " +
            "on p.id = d.product_id " +
            "group by p.category_id ";

    protected static final String SELECT_DISPLAY_LIST = "select @ROWNUM:=@ROWNUM+1 AS ROWNUM, dt.* from ( "
            + "		select p.category_id as category_id, "
            + "			d.id as display_info_id, "
            + "			p.id as product_id, "
            + "			p.description as product_description, "
            + "			d.place_name, "
            + "			p.content as product_content, "
            + "			pm.type, "
            + "			f.save_file_name as product_image_url "
            + "		from product p "
            + "		inner join display_info d "
            + "			on p.id = d.product_id "
            + "		inner join product_image pm "
            + "			on p.id = pm.product_id "
            + "		inner join file_info f "
            + "			on f.id = pm.file_id "
            + "		where pm.type=\"th\" and p.category_id = :categoryId "
            + "		order by d.id) AS dt "
            + "		,( "
            + "			select @ROWNUM := 0 "
            + "		) tmp "
            + "        limit :start ,4";


    protected static final String SELECT_PROMOTION_LIST = "select pm.id as id ,  " +
            "	pm.product_id as product_id,  " +
            "    f.save_file_name as product_image_url, " +
            "    pi.type as img_type, " +
            "    p.description as product_description, " +
            "    p.content as product_content, " +
            "    d.place_name as place_name " +
            "from promotion pm " +
            "inner join product_image pi " +
            "	on pm.product_id = pi.product_id " +
            "inner join file_info f " +
            "	on pi.file_id = f.id " +
            "inner join product p " +
            "	on pm.product_id = p.id " +
            "inner join display_info d " +
            "	on p.id = d.product_id " +
            "where pi.type=\"th\"";


    protected static final String SELECT_ENTIRE_DISPLAY_LIST = "select @ROWNUM:=@ROWNUM+1 AS ROWNUM, dt.* from ( "
            + "		select p.category_id as category_id, "
            + "			d.id as display_info_id, "
            + "			p.id as product_id, "
            + "			p.description as product_description, "
            + "			d.place_name, "
            + "			p.content as product_content, "
            + "			pm.type, "
            + "			f.save_file_name as product_image_url "
            + "		from product p "
            + "		inner join display_info d "
            + "			on p.id = d.product_id "
            + "		inner join product_image pm "
            + "			on p.id = pm.product_id "
            + "		inner join file_info f "
            + "			on f.id = pm.file_id "
            + "		where pm.type=\"th\" "
            + "		order by d.id) AS dt "
            + "		,( "
            + "			select @ROWNUM := 0 "
            + "		) tmp "
            + "        limit :start ,4";


    protected static final String SELECT_DISPAYINFO = "select " +
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
            "left outer join product p " +
            "	on d.product_id=p.id " +
            "left outer join category c " +
            "	on p.category_id = c.id " +
            "where d.id = :id ";


    protected static final String SELECT_DISPLAYINFO_IMG = "select	" +
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
                                    "left outer join display_info_image di " +
                                    "	on d.id = di.display_info_id " +
                                    "left outer join file_info f " +
                                    "	on f.id = di.file_id " +
                                    "where d.id = :id";

    protected static final String SELECT_PRODUCT_IMGS_ET_LIST = "select @ROWNUM := @ROWNUM+1 AS ROWNUM, alls.* from ( " +
                                                            "	select" +
                                                            "		pi.product_id as product_id, " +
                                                            "		pi.id as product_image_id, " +
                                                            "		pi.type as type, " +
                                                            "		f.id as file_info_id, " +
                                                            "		f.file_name as file_name, " +
                                                            "		f.save_file_name as save_file_name, " +
                                                            "		f.content_type as content_type, " +
                                                            "		f.delete_flag as delete_flag, " +
                                                            "		f.create_date as create_date, " +
                                                            "		f.modify_date as modify_date " +
                                                            "	from product_image pi " +
                                                            "	inner join file_info f " +
                                                            "		on f.id = pi.file_id " +
                                                            "	where pi.product_id in ( " +
                                                            "		select product_id from display_info where id = :id " +
                                                            "		) and pi.type=\"et\"" +
                                                            "	) as alls,  " +
                                                            "    ( " +
                                                            "		select @ROWNUM :=0 " +
                                                            "	) tmp " +
                                                            "    limit 0,1; ";


    protected static final String SELECT_PRODUCT_IMGS_MA_LIST = "select  " +
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

    protected static final String SELECT_PRODUCT_PRICE_LIST = "select " +
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
            "    )";

    protected static final String AVG_SCORE = "select avg(score) " +
            "from reservation_user_comment " +
            "where product_id in ( " +
            "	select product_id from reservation_info where display_info_id = :id " +
            "    ) " +
            "group by product_id";

    protected static final String SELECT_COMMENT_LIST = "select rc.comment as comment, " +
            "	rc.id as comment_id, " +
            "    rc.create_date as create_date, " +
            "    rc.modify_date as modify_date, " +
            "    rc.score as score, " +
            "    rc.product_id as product_id, " +
            "    ri.id as reservation_info_id, " +
            "    ri.reservation_name as reservation_name, " +
            "    ri.reservation_tel as reservation_telephone, " +
            "    ri.reservation_email as reservation_email, " +
            "    ri.reservation_date as reservation_date " +
            "from reservation_user_comment rc " +
            "left outer join reservation_info ri " +
            "	on rc.reservation_info_id = ri.id " +
            "where rc.product_id in ( " +
            "	select product_id from reservation_info where display_info_id= :id " +
            ")";


    protected static final String SELECT_COMMENT_IMG_LIST = "select  " +
            "	ri.id as image_id, " +
            "    ri.reservation_info_id as reservation_info_id, " +
            "    ri.reservation_user_comment_id as reservation_user_comment_id, " +
            "    ri.file_id as file_id, " +
            "    f.file_name as file_name, " +
            "    f.save_file_name as save_file_name, " +
            "    f.content_type as content_type, " +
            "    f.delete_flag as delete_flag, " +
            "    f.create_date as create_date, " +
            "    f.modify_date as modify_date " +
            "from reservation_user_comment_image ri " +
            "left outer join file_info f " +
            "	on ri.file_id = f.id " +
            "where reservation_info_id in ( " +
            "	select id from reservation_info where display_info_id= :id )";

}

package refactoring.boot.repository;

public class ReservationReviewDaoSql {
    protected static final String SELECT_COMMENT_CHECK_INFO = "select ri.id as reservation_id, " +
            "		p.id as product_id, " +
            "        p.description as description " +
            "from reservation_info ri  " +
            "inner join product p " +
            "	on ri.product_id = p.id " +
            "where reservation_date < now() and ri.cancel_flag=0 and ri.id= :id ";




    protected static final String INSERT_COMMENT = "insert into reservation_user_comment "
            + " (id, product_id, reservation_info_id, score, comment, create_date, modify_date) "
            + " values( :id, :product_id , :reservation_info_id , :score , :comment , now(), now())";

    protected static final String SELECT_INSERT_COMMENT = "select  " +
            "comment, " +
            "id as comment_id, " +
            "create_date, " +
            "modify_date, " +
            "product_id, " +
            "reservation_info_id, " +
            "score " +
            "from reservation_user_comment " +
            "where id = :id";

    protected static final String INSERT_FILE_INFO = "insert into file_info (id, file_name, " +
            "save_file_name, content_type, delete_flag, create_date, modify_date) " +
            "values( :id, :file_name, :save_file_name, :content_type, 0, now(), now())";

    protected static final String INSERT_COMMENT_IMG = "insert into reservation_user_comment_image ( reservation_info_id, reservation_user_comment_id, file_id) " +
            "values( :reservation_info_id , :reservation_user_comment_id, :file_id )";


    protected static final String MAX_ID_COMMENT = "select max(id)+1 from reservation_user_comment";
    protected static final String MAX_ID_FILE_INFO = "select max(id)+1 from file_info";

    protected static final String SELECT_INSERT_IMG = "select  " +
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
            "where ri.reservation_user_comment_id = :id " +
            "order by f.create_date desc";


}

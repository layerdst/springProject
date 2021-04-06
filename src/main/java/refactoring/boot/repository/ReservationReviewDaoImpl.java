package refactoring.boot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import refactoring.boot.dto.CommentImagesDto;
import refactoring.boot.dto.ResponseCommentDto;
import refactoring.boot.dto.ResponseReviewDto;
import static refactoring.boot.repository.ReservationReviewDaoSql.*;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ReservationReviewDaoImpl implements ReservationReviewDao  {

    private final PlatformTransactionManager transactionManager;
    private final NamedParameterJdbcTemplate jdbc;

    private RowMapper<ResponseReviewDto> checkMapper = BeanPropertyRowMapper.newInstance(ResponseReviewDto.class);
    private RowMapper<ResponseCommentDto> commentMapper = BeanPropertyRowMapper.newInstance(ResponseCommentDto.class);
    private RowMapper<CommentImagesDto> commenImgMapper = BeanPropertyRowMapper.newInstance(CommentImagesDto.class);

    public ResponseReviewDto getCommentPossible(int id) {

        try {
            return jdbc.queryForObject(SELECT_COMMENT_CHECK_INFO, Collections.singletonMap("id", id), checkMapper);
        }catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    public int insertComment(Map<String,Object> commentArgs, Map<String,Object> fileArgs) {

        int commentMaxId = jdbc.queryForObject(MAX_ID_COMMENT, Collections.emptyMap(), Integer.class);
        int max_id = jdbc.queryForObject(MAX_ID_FILE_INFO,Collections.emptyMap(),Integer.class);

        commentArgs.put("id",commentMaxId);
        System.out.println(commentArgs);
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = this.transactionManager.getTransaction(def);

        try {

            int check_com = jdbc.update(INSERT_COMMENT, commentArgs);
            if(check_com==0) {
                this.transactionManager.rollback(status);
                return 0;
            }

            if(fileArgs!=null) {

                fileArgs.put("id", max_id);

                int fileChk = jdbc.update(INSERT_FILE_INFO,fileArgs);
                if(fileChk==0) {
                    this.transactionManager.rollback(status);
                    return 0;
                }

                Map<String, Object> params = new HashMap<>();
                params.put("file_id",max_id);
                params.put("reservation_info_id", commentArgs.get("reservation_info_id"));
                params.put("reservation_user_comment_id", commentMaxId);

                int imgChk = jdbc.update(INSERT_COMMENT_IMG,params);
                if(imgChk==0) {
                    return 0;
                }

            }

            this.transactionManager.commit(status);

            return commentMaxId;

        }catch(EmptyResultDataAccessException e) {
            e.printStackTrace();
            this.transactionManager.rollback(status);
            return 0;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }


    }


    public ResponseCommentDto getInsertComment(int id) {
        try {
            return jdbc.queryForObject(SELECT_INSERT_COMMENT, Collections.singletonMap("id", id), commentMapper);
        }catch(DataAccessException e) {
            return null;
        }
    }

    public CommentImagesDto getInsertCommentImg(int id) {
        try {
            return jdbc.queryForObject(SELECT_INSERT_IMG, Collections.singletonMap("id", id), commenImgMapper);
        }catch(DataAccessException e) {
            return null;
        }
    }




}

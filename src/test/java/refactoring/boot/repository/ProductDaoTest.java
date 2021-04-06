package refactoring.boot.repository;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ProductDaoTest {

    @Autowired
    public NamedParameterJdbcTemplate jdbcTemplate;

    @Test
    public void jdbc접속(){
//
//        ProductDao dao = new ProductDao(jdbcTemplate);
//
//        Assertions.assertThat(dao.getCategoryList().size()).isEqualTo(5);
//        Assertions.assertThat(dao.getMainDisplayList(1,0).size()).isEqualTo(4);
//        Assertions.assertThat(dao.getMainPromotionList().size()).isEqualTo(12);
//        Assertions.assertThat(dao.getEntireDisplayList(0).size()).isEqualTo(4);



    }


}
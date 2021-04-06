package refactoring.boot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import refactoring.boot.dto.ResponseCommentDto;
import refactoring.boot.dto.ResponseReviewDto;
import refactoring.boot.repository.ReservationReviewDao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReservationReviewServiceImpl implements ReservationReviewService{

    private final ReservationReviewDao reviewDao;

    @Override
    public ResponseReviewDto getCommentPossible(int id) {
        return reviewDao.getCommentPossible(id);
    }

    @Override
    public ResponseCommentDto insertComment(int resrvId, int productId, int score, String comment, MultipartFile file) {
        Map<String, Object> commentArgs = new HashMap<>();
        commentArgs.put("reservation_info_id",resrvId);
        commentArgs.put("product_id",productId);
        commentArgs.put("score",score);
        commentArgs.put("comment",comment);

        int result=0;

        if(file==null) {
            result = reviewDao.insertComment(commentArgs, null);

        }else {
            Date date = new Date();
            String fileName = Long.toString(date.getTime());
            Map<String, Object> fileArgs = new HashMap<>();
            String filePath = fileName+"."+file.getContentType().split("/")[1];
            fileArgs.put("file_name",fileName);
            fileArgs.put("save_file_name", "location/"+filePath);
            fileArgs.put("content_type", file.getContentType());

            result = reviewDao.insertComment(commentArgs,fileArgs);
            saveFile(file,fileName,filePath);
        }

        if(result==0) {
            return null;
        }

        ResponseCommentDto commentDto = reviewDao.getInsertComment(result);
        commentDto.setCommentImage(reviewDao.getInsertCommentImg(result));

        return commentDto;
    }

    private ResponseCommentDto getInsertComment(int id) {
        return reviewDao.getInsertComment(id);
    }

    private boolean saveFile(MultipartFile file, String fileName, String filePath) {
        String path = "c:/tmp/";
        File Folder = new File(path);

        if(!Folder.exists()) {
            try {
                Folder.mkdir();
            }catch(Exception e){
                e.printStackTrace();
                return false;
            }
        }

        try(
                FileOutputStream fos = new FileOutputStream("c:/tmp/" + filePath);
                InputStream is =file.getInputStream();
        ){

            int readCount=0;
            byte[] buffer = new byte[1024];
            while((readCount=is.read(buffer))!=-1) {
                fos.write(buffer,0,readCount);
            }
            return true;

        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

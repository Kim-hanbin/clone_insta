package kr.prd.web.board.model;

import lombok.*;

import java.util.Date;
import java.util.List;

public class BoardData {


    @Getter
    @Setter
    @ToString
    public static class BoardVO {

        private int boardId;
        private int userId;
        private String boardContents;
        private int boardCommentCount;
        private String boardKategorie;
        private Date boardCreateTime;
        private Date boardUpdateTime;



        public long getFileSize() {
            return 0;
        }

        public String getFileName() {
            return null;
        }

        public byte[] getFileData() {
            return new byte[0];
        }

        public String getprofileContentType() {
            return null;
        }

        public long getProfileSize() {
            return 0;
        }

        public Object getProfileName() {
            return null;
        }

        public byte[] getProfileData() {
            return new byte[0];
        }
    }

    @Getter
    @Setter
    @ToString
    public static class BoardLikeVO {

        private int userId;
        private int boardId;
        private int likeNum;

        public Object getBoard_id() {
            return null;
        }

        public boolean getUser_id() {
            return false;
        }
    }

    @Getter
    @Setter
    @ToString
    public static class BoardImgVO {

        private int imgId;
        private int boardId;
        private String fileName;
        private String fileType;
        private long fileSize;

        public Object getProfileName() {
            return null;
        }

        public String getprofileContentType() {
            return null;
        }

        public byte[] getProfileData() {
            return new byte[0];
        }

        public long getProfileSize() {
            return 0;
        }
    }

    @Getter
    @Setter
    public static class ReplyVO {

        private int replyId;
        private int boardId;
        private String replyContents;
        private int replyIsDelete;
        private Date replyCreateTime;
        private Date replyUpdateTime;

        public List<ReplyVO> readReply(int boardId) throws Exception {
            return null;


        }

    }

    @Getter
    @Setter
    public static class ReplyLikeVO {

        private int userId;
        private int replyId;
        private int addLike;

        }



    }



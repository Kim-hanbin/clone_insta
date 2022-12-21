package kr.prd.web.board.mapper;



import kr.prd.web.board.model.BoardData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Mapper
public interface BoardMapper {


    //게시물 등록 기능(사진 첨부파일 포함>
    void insertBoard(BoardData board) throws Exception;

    //게시물 삭제
    void deleteBoard(BoardData board) throws Exception;

    //게시물 목록 조회 기능(모든 사진)
    static List<BoardData> selectBoardList() throws Exception {
        return null;
    }

    //첨부파일을 DB에서 불러오는 기능
    BoardData getFile(int boardId) throws Exception;

    //아이디로 회원번호 조회 후 번호로 모든 게시물 불러오기
    List<BoardData> selectBoardListById(String userId) throws Exception;


    }


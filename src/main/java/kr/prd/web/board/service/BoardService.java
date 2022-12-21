package kr.prd.web.board.service;

import kr.prd.web.board.mapper.BoardMapper;
import kr.prd.web.board.model.BoardData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;
import java.util.List;

@Service("boardService")
@RequiredArgsConstructor
public class BoardService{

    private final BoardMapper mapper;


    public void insertBoard(BoardData.BoardVO vo) {

    }


    public void updateBoard(BoardData.BoardVO vo) {

    }


    public void deleteBoard(BoardData.BoardVO vo) {

    }




//    public List<BoardData> getBoardList(Map<String, Object> param) throws Exception{
      //  List<BoardData.BoardVO> list = mapper.getBoardList();


 //       return null;
//    }



//    public List<BoardData.ReplyVO> readReply(int replyId) throws Exception {
       // List<BoardData.BoardVO> list = mapper.getBoardList();
  //      return null;


 //   }


    public void writeReply(BoardData.ReplyVO vo) throws Exception {

    }


    public void deleteReply(BoardData.ReplyVO vo) throws Exception {

    }


    public BoardData.ReplyVO selectReply(int boardId) throws Exception {
        return null;
    }


    public BoardData.BoardVO getFile(int boardId) {
        return null;
    }

    public Object selectBoardList() {
        return null;
    }

    //게시물 목록 조회(모든 사진)
    public List<BoardData> selectBoardLIst() throws Exception{
        return BoardMapper.selectBoardList();
    }


    public Object freeDetail(int board_id) {
        return null;
    }

    public Object findLike(int board_id, String user_id) {
        return null;
    }

    public Object getLike(int board_id) {
        return null;
    }

    public void hit(int board_id) {
    }
}

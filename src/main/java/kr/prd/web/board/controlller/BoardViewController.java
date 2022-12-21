package kr.prd.web.board.controlller;


import kr.prd.web.board.model.BoardData;
import kr.prd.web.board.service.BoardService;
import kr.prd.web.profile.vo.ProfileData;
import kr.prd.web.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.maven.model.Profile;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/board")
@Slf4j
public class BoardViewController {
    private static final Logger logger = log;

    @Autowired
    private BoardService boardService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) throws Exception{
        //가져온 모든 게시물을 board 모델에 담아 보냄.
        model.addAttribute("board", boardService.selectBoardList());

        return "board/list";
    }

    public ResponseEntity<byte[]> getFile(int boardId) throws Exception{

           logger.info("/board/file" + boardId + " -> POST 발생 요청! ");
        BoardData.BoardVO file = boardService.getFile(boardId);

        //파일을 클라이언트로 전송하기 위해 전송정보를 담을 헤더 설정
    HttpHeaders headers = new HttpHeaders();
    String[] fileType = file.getBoardContents().split("/");

    //전송헤더에 파일 정보와 확장자 셋팅
    headers.setContentType(new MediaType(fileType[0], fileType[1]));

    //전송헤더에 파일 용량 세팅
    headers.setContentLength(file.getFileSize());

    //전송헤더에 파일명 세팅
    headers.setContentDispositionFormData("attachment", file.getFileName());
    return new ResponseEntity<byte[]>(file.getFileData(), HttpStatus.valueOf(file.getFileName()));

}
    //프로필 사진 파일 불러오기 요청
    public ResponseEntity<byte[]> getProfile(@PathVariable int boardId) throws Exception{

        logger.info("/board/profile/" + boardId + " -> POST 요청 발생!");

        int userId = 0;
        BoardData.BoardVO profile = boardService.getFile(userId);
        logger.info("userId로 조회한 파일 profile : " + profile);

        //파일을 클라이언트로 전송하기 위해 전송정보를 담을 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        String[] fileType = profile.getprofileContentType().split("/");

        //전송헤더에 파일 정보와 확장자 세팅
        headers.setContentType(new MediaType(fileType[0], fileType[1]));

        //전송헤더에 파일 용량 세팅
        headers.setContentLength(profile.getProfileSize());

        //전송헤더에 파일명 세팅
        headers.setContentDispositionFormData("attachment", (String) profile.getProfileName());

        return new ResponseEntity<byte[]>(profile.getProfileData(), headers, HttpStatus.OK);
    }

    @GetMapping("/boardDetail")
    public void boardDetail(int board_id, String user_id, Model model) {

        System.out.println("자세히 보기 페이지");

        model.addAttribute("Detail", boardService.freeDetail(board_id));

        BoardData.BoardLikeVO like = new BoardData.BoardLikeVO();

        like.setBoardId(board_id);
        like.setUserId(Integer.parseInt(user_id));

        model.addAttribute("like", boardService.findLike(board_id, user_id));
        model.addAttribute("getLike", boardService.getLike(board_id));
        boardService.hit(board_id);

    }

    @ResponseBody
    @PostMapping("/likeUp")
    public void likeup(@RequestBody BoardData.BoardLikeVO vo) {
        System.out.println("컨트롤러 연결 성공");
        System.out.println(vo.getBoard_id());
        System.out.println(vo.getUser_id());
        BoardViewController service = null;
        service.likeUp(vo.getBoard_id(), vo.getUser_id());

    }

    private void likeUp(Object board_id, boolean user_id) {
    }

    @ResponseBody
    @PostMapping("/likeDown")
    public void likeDown(@RequestBody BoardData.BoardLikeVO vo) {
        System.out.println("좋아요 싫어요!");
        BoardViewController service = null;
        service.likeDown(vo.getBoard_id(), vo.getUser_id());
    }

    private void likeDown(Object board_id, boolean user_id) {
    }


}
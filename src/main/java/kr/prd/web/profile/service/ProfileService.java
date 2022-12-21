package kr.prd.web.profile.service;

import kr.prd.web.common.CommonUtils;
import kr.prd.web.profile.mapper.ProfileMapper;
import kr.prd.web.profile.vo.ProfileData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileMapper mapper; //lombok을 통한 의존성 주입

    public ProfileData.ProfileVO getProfileView(Map<String, Object> param) throws Exception{
        //프로필 화면에 보여줄 정보를 DB에서 꺼내와서 profileVO객체에 담아 controller에 전송
        return mapper.getProfileView(param);
    }

    public int profileFix(ProfileData.ProfileRequest profileRequest) throws Exception {
        //빌더 패턴을 통해 request객체에서 받은 정보를 fix에다가 저장
        //view에서 받은 파일은 만약 원래 파일이 존재한다면 지우고 받은 파일 로컬에 저장
        //결과값 컨트롤러로 넘겨줌
        ProfileData.ProfileFix profileFix = ProfileData.ProfileFix
                .builder()
                .profileIntro(profileRequest.getProfileIntro())
                .userName(profileRequest.getUserName())
                .userNum(profileRequest.getUserNum())
                .build();

        Map<String, Object> param = new HashMap<>();
        param.put("userNum", profileRequest.getUserNum());

        ProfileData.ProfileVO vo = this.getProfileView(param);

        if(profileRequest.getProfileImg() != null && !profileRequest.getProfileImg().isEmpty()) {
            String fullPath = CommonUtils.uploadPath + vo.getStoredFileName();
            File file = new File(fullPath);
            //해당경로에 진짜 존재한다면
            if(file.exists()) {
                //지운다.
                file.delete();
            }
            this.uploadFile(profileRequest, profileFix);
        }

        return this.mapper.profileFix(profileFix);
    }


    public void uploadFile(ProfileData.ProfileRequest profileRequest, ProfileData.ProfileFix profileFix) throws Exception{
        //파일 로컬에 업로드 및 파일이름 저장 메서드
        MultipartFile file = profileRequest.getProfileImg();

        //파일 객체가 존재 할 경우
        if(file!=null && !file.isEmpty()){
            String originFileName = file.getOriginalFilename();

            //확장자
            String ext = originFileName.substring(originFileName.lastIndexOf(".")+1);

            //파일에 부여할 랜덤이름
            String randomName = CommonUtils.getUUID();

            //저장할 파일명 만들기
            String storedFileName = randomName+ "."+ ext;

            //저장할 파일의 전체 경로
            String fullPath = CommonUtils.uploadPath + storedFileName;

            //파일 객체 만들기 - 파일을 컨트롤 하기위함.(생성,삭제 등)
            File newFile = new File(fullPath);

            //저장할 경로가 없다면?
            if(!newFile.getParentFile().exists()) {
                //전체 폴더 경로를 생성해줌 (여기서 전체경로란? 우리가 설정한 fullPath)
                newFile.getParentFile().mkdirs();
            }

            //기존파일을 복사할 빈파일 만들기
            newFile.createNewFile();
            //기존파일을 생성 된 새로운 빈파일로 복사한다.
            file.transferTo(newFile);

            //등록할 파일 이름들을 객체에 저장
            profileFix.setOriginFileName(originFileName);
            profileFix.setStoredFileName(storedFileName);


        }
    }

}

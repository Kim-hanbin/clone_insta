package kr.prd.web.common;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

public class DownloadView extends AbstractView{

	
	public DownloadView() {
		this.setContentType("application/download;charset=utf-8");
	}
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	 
		//컨트롤러에서 넘어온 다운로드 대상을 추출 
		File file=(File)model.get("downFile");
		
		//원본 이름으로 다운하기 위해서 가져옴.
		String originFileName=model.get("fileName").toString();
		//리턴될 view 타입 저장 
		response.setContentType(this.getContentType());
		// 다운로드 대상 파일 길이 
		response.setContentLength((int)file.length());
		//다운할 파일이름 지정
		// 다운로시 한글꺠짐을 방지하기 위해서 파일 이름을 utf-8 타입으로 인코딩한다.
		String fileName=URLEncoder.encode(originFileName,"UTF-8");
		
		//헤더 설정 - 뷰를 보는 방식 Content-Disposition : 파일 다운
		response.setHeader("Content-Disposition","attachment;filename=\""+fileName+"\";");
		//파일을 전송하는 방식 
		response.setHeader("Content-Transfer-Encoding","binary");
		// 리턴 객체가 가지고 있는 스트림 받기
		OutputStream out = response.getOutputStream();
		
		FileInputStream in =null;
		try {
			//현재 파일 읽기
			in=new FileInputStream(file);
			//라이브러리 - 복사하기 
			FileCopyUtils.copy(in,out);
			
		}catch (Exception e) {
		e.printStackTrace();
		
		}finally {
			try {
				
				if(out!=null) {
					out.close();
				}
				
				if(in !=null) {
					in.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}

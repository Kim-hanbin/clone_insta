package kr.prd.web.common.page;

public class PagingVO {
	
	private int totalPage;  // 전체 페이지 수
	private int nowPageNumber ; // 현재 위치하는 페이지 번호
	private int blockPageCount ; // 한블럭당 부여할 페이지  개수
	private int  pagePerRows = 10;  // 한페이지당  보여줄 데이터의 개수
	private int totalRows; // 전체 데이터 개수 
	private int nowBlock; // 현재 블럭 위치 
	private int totalBlock;  // 전체 블럭 개수



	//생성자
	public PagingVO(int nowPageNumber,  int totalRows) {
		this.nowPageNumber = nowPageNumber -1;
		this.totalRows = totalRows;
		this.blockPageCount = 10; // 하나의 블럭당 10개의 페이지 개수를 보여준다. 
	}

	/*SQL 에서 출력 데이터의  범위 시작 위치 정할 때 사용할 것임 */
	public int getBeginPage() {
		//한 페이지의 시작위치 번호 
		return nowPageNumber * pagePerRows + 1;
	}
	
	/*SQL 에서 출력 데이터의  범위  마지막 위치 정할 때 사용할 것임 */
	public int getEndPage() {
		//시작위치에서 페이지당 보여줄 개수를 더하면 된다.
		return   getBeginPage()   +  pagePerRows  - 1;
	}
	
	//전체 페이지 수
	public int totalPage() {
		 //전체 데이터 개수를 실수로 바꾼다.
		double total = (double) totalRows;
		// 전체 개수를 페이지당 보여줄 목록 수로 나눈다. 
		// 10개씩 보여주고 2개가 남았다면  10개 보여주는 페이지 1개와 2개를 보여주는 페이지까지 총 2개가 필요하다.
		// 나누기의 나머지도 보여줘야할 데이터이기 때문에 총페이지 수를 구하기 위해 올림 처리 한다.
		double result =  Math.ceil( total / this.pagePerRows);  
		System.out.println("총 페이지 수 : " +  result);
		return (int) result;
	}
	
	
	 public int nowBlock(){
	        double retVal = ((double)this.nowPageNumber) / this.blockPageCount;
	        // 현재 블럭 = 현제 페이지/ 블러당 페이지 수
	        return (int)(retVal);            
	    }
	 
	    
	 public int totalBlock(int totalPage){
		 double retVal = Math.ceil((double)totalPage / this.blockPageCount);
		 System.out.println("토탈 블럭 :"+retVal);
		 return (int)(retVal);       
	 }
	 
	 public String pageHTML() {
		 
		 this.totalPage = this.totalPage();
		 this.nowBlock   = this.nowBlock();
		 this.totalBlock = this.totalBlock(totalPage); 
		 int pageNumber = 0;
		 StringBuilder sb = new StringBuilder();
		 //만약 첫번째 블럭 이후라면~
		 //이전블럭으로 돌아갈 버튼 만들어주기 
		 if (nowBlock > 0 ) {
			 //이전블럭으로 가기 작성 하기 위함 
			  pageNumber =  (nowBlock -1) * blockPageCount;
			  sb.append("<li class='page-item'>");
			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"goPage(" + pageNumber +");\" >");
			  sb.append("Previous</a></li>");
		 }
		 
		 if(nowPageNumber  >  0) {
			 sb.append("<li class='page-item'>");
  			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"goPage(" + (nowPageNumber -1) +");\" >");
  			  sb.append("First </a></li>");
		 }
		 
		 //페이지 리스트 만들기
		 for(int i = 0; i < blockPageCount ; i++) {
			 pageNumber = (nowBlock * blockPageCount) + i;
			 
			 if(pageNumber == nowPageNumber) {
				  sb.append("<li class='page-item active'>");
				  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"goPage(" + pageNumber +");\"> ");
				  sb.append( (pageNumber + 1)   +    "</a></li>");
			 }else {
				  sb.append("<li class='page-item'>");
				  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"goPage(" + pageNumber +");\">");
				  sb.append( (pageNumber + 1)   +    "</a></li>");
			 }
		   // 페이지를 만드는 루프가 끝나기전에 마지막 페이지번호를 만나면 더이상 반복할 필요 없음 
             if ((nowBlock * blockPageCount) + i + 1 == totalPage) {
            	   break;
             }
		
		 }
		 
		   //다음블럭으로 가기 버튼 만들기 
         if(nowBlock +1 <  totalBlock) {
        	 pageNumber =  (nowBlock + 1) * blockPageCount;
   			  sb.append("<li class='page-item'>");
   			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"goPage(" + pageNumber +");\" >");
   			  sb.append("Next </a></li>");
         }
         //마지막 페이지로 이동 버튼 만들기 
         if( nowPageNumber +1  < totalPage) {
        	  sb.append("<li class='page-item'>");
   			  sb.append("  <a class='page-link'  href=\"javascript:void(0);\"  onclick=\"goPage(" + (totalPage -1) +");\" >");
   			  sb.append("Last </a></li>");
         }
		 
		 return sb.toString();
	 }
}

package kpu.club.domain;

public class BoardVO {

    // 게시판 ID 
    private Integer bbsId;

    // 주제1 
    private String bbsTopic1;

    // 주제2 
    private String bbsTopic2;

    // 게시판 내용 
    private String bbsContent;

    // 생성 시간 
    private String bbsDate;

    // 작성자ID 
    private String bbsPostId;

    // 게시판 추천 수 
    private Integer bbsRecommend;
    
    // 게시판 신고 수
    private Integer bbsReport;

    public Integer getBbsId() {
        return bbsId;
    }

    public void setBbsId(Integer bbsId) {
        this.bbsId = bbsId;
    }

    public String getBbsTopic1() {
        return bbsTopic1;
    }

    public void setBbsTopic1(String bbsTopic1) {
        this.bbsTopic1 = bbsTopic1;
    }

    public String getBbsTopic2() {
        return bbsTopic2;
    }

    public void setBbsTopic2(String bbsTopic2) {
        this.bbsTopic2 = bbsTopic2;
    }

    public String getBbsContent() {
        return bbsContent;
    }

    public void setBbsContent(String bbsContent) {
        this.bbsContent = bbsContent;
    }

    public String getBbsDate() {
        return bbsDate;
    }

    public void setBbsDate(String bbsDate) {
        this.bbsDate = bbsDate;
    }

    public String getBbsPostId() {
        return bbsPostId;
    }

    public void setBbsPostId(String bbsPostId) {
        this.bbsPostId = bbsPostId;
    }

    public Integer getBbsRecommend() {
        return bbsRecommend;
    }

    public void setBbsRecommend(Integer bbsRecommend) {
        this.bbsRecommend = bbsRecommend;
    }

	public Integer getBbsReport() {
		return bbsReport;
	}

	public void setBbsReport(Integer bbsReport) {
		this.bbsReport = bbsReport;
	}

    
    
    
}

package kpu.club.domain;

public class ReportVO {

    // 신고된 게시판ID 
    private Integer reportBoardId;

    // 신고자_ID 
    private String reportReporter;

    public Integer getReportBoardId() {
        return reportBoardId;
    }

    public void setReportBoardId(Integer reportBoardId) {
        this.reportBoardId = reportBoardId;
    }

    public String getReportReporter() {
        return reportReporter;
    }

    public void setReportReporter(String reportReporter) {
        this.reportReporter = reportReporter;
    }

}
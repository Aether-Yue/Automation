package com.automation.until;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import br.eti.kinoshita.testlinkjavaapi.model.ReportTCResultResponse;

/**
 * 测试结果回传TestLink
 */
public class ResultUpload {
//    private static TestLinkAPI api = ApiObject.getAPI(pjname);
    public static void testReport(Boolean flag,Integer planID,String buildName,Integer tcID,String pjname) {
        TestLinkAPI api = ApiObject.getAPI(pjname);
        if(flag){
            ReportTCResultResponse response = api.reportTCResult(tcID, null,
                    planID, ExecutionStatus.PASSED, null, buildName,
                    " 自动化上传结果的备注 ", null, null, null, null, null, null);
            System.out.println("response=" + response);
        }else {
            ReportTCResultResponse response = api.reportTCResult(tcID, null,
                    planID, ExecutionStatus.FAILED, null, buildName,
                    " 自动化上传结果的备注 ", null, null, null, null, null, null);
            System.out.println("response=" + response);
        }
    }
}

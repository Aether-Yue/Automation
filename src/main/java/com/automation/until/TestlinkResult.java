package com.automation.until;

import br.eti.kinoshita.testlinkjavaapi.TestLinkAPI;
import br.eti.kinoshita.testlinkjavaapi.model.TestPlan;
import com.automation.model.ReadProject;

/**
 * 测试结果回传TestLink
 */
public class TestlinkResult {

    public static void testlinkResult(Boolean flag,String case_id,String pjname){
//        String pjname= ReadProject.projectname;
        ReadTestProperties readconf = new ReadTestProperties();
        readconf.setProject_name(pjname);

        String projectName = readconf.readTestProperties("testlink_projectName");
        String planName = readconf.readTestProperties("testlink_planName");
        String buildName = readconf.readTestProperties("testlink_version");

        TestLinkAPI api = ApiObject.getAPI(pjname);
        TestPlan tl = api.getTestPlanByName(planName,projectName);
        Integer planID=tl.getId();
/*
        String[] a=name.split("-");
        String aa=a[1];
        Integer tcID=Integer.parseInt(aa);
*/
        Integer tcID=Integer.parseInt(case_id);
        ResultUpload.testReport(flag,planID,buildName,tcID,pjname);

    }
}

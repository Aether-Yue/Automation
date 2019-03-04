package com.automation.model;

public class ReadProject {

    private static String projectname;
    private static Boolean status=true;
    private static Boolean beginexe_status=true;

    public static String getProjectname(){
            return projectname;
    }

    public static synchronized void setProjectname(String project_name) {
        while (status) {
            status=false;
            projectname = project_name;
        }
    }

    public static void setStatus(Boolean sta){
        status=sta;
    }

    public static synchronized Boolean getBeginexe(){
        Boolean beginexe = false;
        while (beginexe_status) {
            beginexe_status=false;
            beginexe=true;
        }
        return beginexe;
    }

    public static void setBeginexe_status(Boolean bexe){
        beginexe_status=bexe;
    }
}

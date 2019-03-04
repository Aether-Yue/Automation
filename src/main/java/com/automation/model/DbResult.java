package com.automation.model;

/**  
 * @ClassName: DbResult
 * @Description: 封装返回数据
 */
public class DbResult {

    private int retcode = 0;
    private String retmsg = "操作成功";
    private String data = null;

    public DbResult(int retcode, String retmsg, String data){
        this.retcode = retcode;
        this.retmsg = retmsg;
        this.data = data;
    }

    public DbResult(int retcode, String retmsg){
        this.retcode = retcode;
        this.retmsg = retmsg;
    }

    public DbResult(String data){
        this.retmsg = "查询成功";
        this.data = data;
    }

    public DbResult(int retcode){
        this.retcode = retcode;
        this.retmsg = "操作失败";
    }
/*
    public DbResult(String retmsg){
        this.retcode = 0;
        this.retmsg = retmsg;
    }
*/
    public DbResult(){
          
    }  
  
    public int getRetcode() {  
        return retcode;  
    }  
    public void setRetcode(int retcode) {  
        this.retcode = retcode;  
    }  
    public String getRetmsg() {  
        return retmsg;  
    }  
    public void setRetmsg(String retmsg) {  
        this.retmsg = retmsg;  
    }  
    public Object getData() {  
        return data;  
    }  
    public void setData(String data) {
        this.data = data;  
    }  
  
    @Override  
    public String toString() {  
        //return "DbResult [retcode=" + retcode + ", retmsg=" + retmsg + ", data=" + data + "]";
        return  data;
    }  
      
}
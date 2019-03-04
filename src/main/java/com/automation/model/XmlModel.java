package com.automation.model;

import java.util.HashMap;
import java.util.Map;

public class XmlModel {

    /**
     * @input lune
     * xml操作的model信息
     */
    private String elementname;
    private String clicktext;
    private String name;
    private String input;
    private String keys;
    private String expect;
    private String url;
    private String sql;
    private String gettext;
    private String type;
    private String row;
    private String column;
    private String content;
    private String xpath;
    private String level;
    private String comboxid;
    private String poptab;
    private String tableoperation;
    private String node;
    private String nodeop;
    private String multiplediv;
    private String tab;
    private String postgresql;

    /**
     * @return the elementname
     */
    public String getElementname() {
        return elementname;
    }
    /**
     * @param elementname the click to set
     */
    public void setElementname(String elementname) {
        this.elementname = elementname;
    }
    /**
     * @return the click
     */
    public String getClick() {
        return clicktext;
    }
    /**
     * @param clicktext the click to set
     */
    public void setClick(String clicktext) {
        this.clicktext = clicktext;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the input
     */
    public String getinput() {
        return input;
    }
    /**
     * @param input the input to set
     */
    public void setinput(String input) {
        this.input = input;
    }
    /**
     * @return the keys
     */
    public String getkeys() {
        return keys;
    }
    /**
     * @param keys the keys to set
     */
    public void setkeys(String keys) {
        this.keys = keys;
    }
    /**
     * @return the expect
     */
    public String getexpect() {
        return expect;
    }
    /**
     * @param expect the expect to set
     */
    public void setexpect(String expect) {
        this.expect = expect;
    }

    /**
     * @return the url
     */
    public String geturl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void seturl(String url) {
        this.url = url;
    }

    /**
     * @return the postgresql
     */
    public String getpostgresql() {
        return postgresql;
    }
    /**
     * @param postgresql the postgresql to set
     */
    public void setpostgresql(String postgresql) {
        this.postgresql = postgresql;
    }

    /**
     * @return the sql
     */
    public String getsql() {
        return sql;
    }
    /**
     * @param sql the sql to set
     */
    public void setsql(String sql) {
        this.sql = sql;
    }

    /**
     * @return the gettext
     */
    public String getgettext() {
        return gettext;
    }
    /**
     * @param gettext the gettext to set
     */
    public void setGettext(String gettext) {
        this.gettext = gettext;
    }

    /**
     * @return the type
     */
    public String gettype() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void settype(String type) {
        this.type = type;
    }

    /**
     * @return the row
     */
    public String getrow() {
        return row;
    }
    /**
     * @param row the row to set
     */
    public void setrow(String row) {
        this.row = row;
    }

    /**
     * @return the column
     */
    public String getcolumn() {
        return column;
    }
    /**
     * @param column the column to set
     */
    public void setcolumn(String column) {
        this.column = column;
    }

    /**
     * @return the content
     */
    public String getcontent() {
        return content;
    }
    /**
     * @param content the content to set
     */
    public void setcontent(String content) {
        this.content = content;
    }

    /**
     * @return the xpath
     */
    public String getxpath() {
        return xpath;
    }
    /**
     * @param xpath the xpath to set
     */
    public void setxpath(String xpath) {
        this.xpath = xpath;
    }

    /**
     * @return the level
     */
    public String getlevel() {
        return level;
    }
    /**
     * @param level the level to set
     */
    public void setlevel(String level) {
        this.level = level;
    }

    /**
     * @return the id
     */
    public String getid() {
        return comboxid;
    }
    /**
     * @param comboxid the id to set
     */
    public void setid(String comboxid) {
        this.comboxid = comboxid;
    }

    /**
     * @return the poptab
     */
    public String getpoptab() {
        return poptab;
    }
    /**
     * @param poptab the poptab to set
     */
    public void setpoptab(String poptab) {
        this.poptab = poptab;
    }

    /**
     * @return the tableoperation
     */
    public String gettableoperation() {
        return tableoperation;
    }
    /**
     * @param tableoperation the tableoperation to set
     */
    public void settableoperation(String tableoperation) {
        this.tableoperation = tableoperation;
    }

    /**
     * @return the node
     */
    public String getnode() {
        return node;
    }
    /**
     * @param node the node to set
     */
    public void setnode(String node) {
        this.node = node;
    }

    /**
     * @return the nodeop
     */
    public String getnodeop() {
        return nodeop;
    }
    /**
     * @param nodeop the nodeop to set
     */
    public void setnodeop(String nodeop) {
        this.nodeop = nodeop;
    }

    /**
     * @return the multiplediv
     */
    public String getmultiplediv() {
        return multiplediv;
    }
    /**
     * @param multiplediv the multiplediv to set
     */
    public void setmultiplediv(String multiplediv) {
        this.multiplediv = multiplediv;
    }


    /**
     * @return the tab
     */
    public String gettab() {
        return tab;
    }
    /**
     * @param tab the tab to set
     */
    public void settab(String tab) {
        this.tab = tab;
    }


    /*
    * 0 elementname
    * 1 url
    * 2 name
    * 3 keys
    * 4 click
    * 5 gettext
    * 6 expect
    * 7 sql
    * 8 type
    * 9 row
    * 10 column
    * 11 content
    * 12 xpath
    * 13 level
    * 14 id
    * 15 poptab
    * 16 tableoperation
    *17 node
    *18 nodeop
    *19 multiplediv
    *20 tab
    *21 postgresql
    * */
/*
    @Override
    public String toString() {
        return   elementname + "|" + url + "|" + name + "|" + keys + "|" + click + "|" + gettext + "|" + expect + "|" + sql + "|" + type + "|" + row + "|" + column + "|" + content + "|" + xpath + "|" + level + "|" + id + "|" + poptab + "|" + tableoperation + "|" + node + "|" + nodeop + "|" + multiplediv + "|" + tab + "|" + postgresql;
    }
*/
    public Map operateMap(){
        Map<String,String> operateMap = new HashMap<String,String>();
        operateMap.put("elementname",elementname);
        operateMap.put("url",url);
        operateMap.put("name",name);
        operateMap.put("keys",keys);
        operateMap.put("clicktext",clicktext);
        operateMap.put("gettext",gettext);
        operateMap.put("expect",expect);
        operateMap.put("sql",sql);
        operateMap.put("type",type);
        operateMap.put("row",row);
        operateMap.put("column",column);
        operateMap.put("content",content);
        operateMap.put("xpath",xpath);
        operateMap.put("level",level);
        operateMap.put("comboxid",comboxid);
        operateMap.put("poptab",poptab);
        operateMap.put("tableoperation",tableoperation);
        operateMap.put("node",node);
        operateMap.put("nodeop",nodeop);
        operateMap.put("multiplediv",multiplediv);
        operateMap.put("tab",tab);
        operateMap.put("postgresql",postgresql);
        return operateMap;
    }
}

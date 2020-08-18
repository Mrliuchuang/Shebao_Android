package com.example.liuchuang.shebao.Rizhijiku;

/**
 * @author 刘闯
 * @date 2019/3/26 0026.
 * Email：741163103@qq.com
 */

public  class ImageEntry{
    String url;
    String name;
    String shenfenzhenghao;
    String lianxifangshi;
    long id;
    public String getUrl(){
        return this.url;
    }
    public String getName(){
        return this.name;
    }
    public String getshenfenzhenghao(){
        return this.shenfenzhenghao;
    }
    public String getlianxifangshi(){
        return this.lianxifangshi;
    }
    public long getId(){
        return this.id;
    }

    public void setname(String name){
        this.name = name;
    }
    public void setid(long id){
        this.id = id;
    }
    public void seturl(String url){
        this.url = url;
    }
    public void setshenfenzhenghao(String shenfenzhenghao){
        this.shenfenzhenghao = shenfenzhenghao;
    }
    public void setlianxifangshi(String lianxifangshi){
        this.lianxifangshi = lianxifangshi;
    }

}


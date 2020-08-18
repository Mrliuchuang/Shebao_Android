package com.example.liuchuang.shebao.utius;

/**
 * @author 刘闯
 * `@date 2019/3/24 0024.
 * Email：741163103@qq.com
 */

public class ShenfenzhengBean {


    /**
     * ret : 0
     * msg : ok
     * data : {"name":"刘间","sex":"男","nation":"汉","birth":"1996/9/8","address":"辽宁省建平县深井镇小马场村7-070号","id":"211322199609081011","frontimage":"/9j/4AAQSk"}
     */

    private int ret;
    private String msg;
    private DataBean data;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 刘间
         * sex : 男
         * nation : 汉
         * birth : 1996/9/8
         * address : 辽宁省建平县深井镇小马场村7-070号
         * id : 211322199609081011
         * frontimage : /9j/4AAQSk
         */

        private String name;
        private String sex;
        private String nation;
        private String birth;
        private String address;
        private String id;
        private String frontimage;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getBirth() {
            return birth;
        }

        public void setBirth(String birth) {
            this.birth = birth;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFrontimage() {
            return frontimage;
        }

        public void setFrontimage(String frontimage) {
            this.frontimage = frontimage;
        }
    }
}
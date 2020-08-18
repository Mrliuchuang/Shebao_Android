package com.example.liuchuang.shebao.zixun;

import java.util.List;

/**
 * @author 刘闯
 * @date 2019/3/27 0027.
 * Email：741163103@qq.com
 */

public class minsheng {

    /**
     * ERRORCODE : 0
     * RESULT : [{"searchNum":"231560","trend":"上升","rank":"1","id":"5aWz5oCn5Lmw5oi/54yb5aKe","keyword":"女性买房猛增"},{"searchNum":"193613","trend":"上升","rank":"2","id":"56m/5ZKM5pyN6L%2Bb5q2m5aSn6LWP5qix","keyword":"穿和服进武大赏樱"}]
     */

    private String ERRORCODE;
    private List<RESULTBean> RESULT;

    public String getERRORCODE() {
        return ERRORCODE;
    }

    public void setERRORCODE(String ERRORCODE) {
        this.ERRORCODE = ERRORCODE;
    }

    public List<RESULTBean> getRESULT() {
        return RESULT;
    }

    public void setRESULT(List<RESULTBean> RESULT) {
        this.RESULT = RESULT;
    }

    public static class RESULTBean {
        /**
         * searchNum : 231560
         * trend : 上升
         * rank : 1
         * id : 5aWz5oCn5Lmw5oi/54yb5aKe
         * keyword : 女性买房猛增
         */

        private String searchNum;
        private String trend;
        private String rank;
        private String id;
        private String keyword;

        public String getSearchNum() {
            return searchNum;
        }

        public void setSearchNum(String searchNum) {
            this.searchNum = searchNum;
        }

        public String getTrend() {
            return trend;
        }

        public void setTrend(String trend) {
            this.trend = trend;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}

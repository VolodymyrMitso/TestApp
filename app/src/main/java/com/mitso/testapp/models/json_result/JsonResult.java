package com.mitso.testapp.models.json_result;

import java.util.List;

public class JsonResult {

    private Integer resultCount;
    private List<Result> results;

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return
                "JsonResult{" + "\n" +
                "resultCount=" + resultCount + "\n" +
                "results=" + results + "\n" +
                '}';
    }
}
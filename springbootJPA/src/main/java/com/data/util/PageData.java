package com.data.util;


import java.util.List;


public class PageData<T> {
    private Page page;
    private List<T> data;

    public PageData(Page page, List<T> data) {
        this.page = page;
        this.data = data;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}

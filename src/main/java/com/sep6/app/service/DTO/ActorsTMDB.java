package com.sep6.app.service.DTO;

public class ActorsTMDB {
    public ActorsTMDB(){

    }

    int page;
    ActorTMDB[] results;
    int total_pages;
    int total_results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ActorTMDB[] getResults() {
        return results;
    }

    public void setResults(ActorTMDB[] results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}

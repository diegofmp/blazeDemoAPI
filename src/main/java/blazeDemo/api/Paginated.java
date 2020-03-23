package blazeDemo.api;

import java.util.List;

public class Paginated {
    private List<Customer> data;
    private long total;
    private Integer page;
    private Integer items_per_page;
    private long pages;

    public Paginated(List<Customer> data, long total, Integer page, Integer items_per_page, long pages) {
        this.data = data;
        this.total = total;
        this.page = page;
        this.items_per_page = items_per_page;
        this.pages = pages;
    }

    public List<Customer> getData() {
        return data;
    }

    public void setData(List<Customer> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getItems_per_page() {
        return items_per_page;
    }

    public void setItems_per_page(Integer items_per_page) {
        this.items_per_page = items_per_page;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }
        
}

package api;

import lombok.Data;

@Data
public class Page {
    public int number;
    public int size;
    public int totalElements;
    public int totalPages;
}
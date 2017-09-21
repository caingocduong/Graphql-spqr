package com.example.connections;

import java.util.List;
import java.util.function.Predicate;

public class SaleProductConnection {
    private int count;
    private List<SaleProductEdge> edges;
    private PageInfo pageInfo;

    public SaleProductConnection() {}

    public List<SaleProductEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<SaleProductEdge> edges) {
        this.edges = edges;
    }

    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<SaleProductEdge> edgesToReturned(List<SaleProductEdge> allEdges,
                                                 String before, String after,
                                                 int first, int last) throws Exception {

        this.edges = allEdges;

        int afterIndex = applyCursorToAfterEdges(this.edges,after);
        int beforeIndex = applyCursorToBeforeEdges(this.edges, before);
        int beforeCount = this.edges.size();

        if(first != 0){
            if(first < 0){
                throw new Exception("Error!!!");
            }
            if(this.edges.size() > first){
                final int firstTemp = first;
                Predicate<SaleProductEdge> predicate = saleProductEdge -> this.edges.indexOf(saleProductEdge) >= firstTemp;
                this.edges.removeIf(predicate);

            }
        }

        int actualFirst = this.edges.size();

        if(last != 0){
            if(last < 0){
                throw new Exception("Error!!!");
            }
            if(this.edges.size() > last){
                final int lastTemp = last;
                Predicate<SaleProductEdge> predicate = saleProductEdge -> this.edges.indexOf(saleProductEdge) <= lastTemp;
                this.edges.removeIf(predicate);
            }
        }

        int actualLast = this.edges.size();

        hasNextPage(afterIndex,beforeIndex,actualFirst,actualLast);

        hasPreviousPage(afterIndex,beforeIndex,actualFirst,allEdges,beforeCount);

        return this.edges;
    }

    private int applyCursorToAfterEdges(List<SaleProductEdge> allEdges,String after){
        int afterIndex = -1;
        for(SaleProductEdge saleProductEdge : allEdges){
            if(saleProductEdge.getCursor().equals(after)){
                afterIndex = allEdges.indexOf(saleProductEdge);
            }
        }
        if(afterIndex != -1){
            final int afterIndexTemp = afterIndex;
            Predicate<SaleProductEdge> predicate = saleProductEdge -> allEdges.indexOf(saleProductEdge) <= afterIndexTemp;
            allEdges.removeIf(predicate);
        }

        return afterIndex;
    }

    private int applyCursorToBeforeEdges(List<SaleProductEdge> allEdges, String before){
        int beforeIndex = -1;
        for(SaleProductEdge saleProductEdge : allEdges){
            if(saleProductEdge.getCursor().equals(before)){
                beforeIndex = allEdges.indexOf(saleProductEdge);
            }
        }
        if(beforeIndex != -1){
            final int beforeIndexTemp = beforeIndex;
            Predicate<SaleProductEdge> predicate = saleProductEdge -> allEdges.indexOf(saleProductEdge) >= beforeIndexTemp;
            allEdges.removeIf(predicate);
        }

        return beforeIndex;
    }

    private void hasNextPage(int afterIndex, int beforeIndex,
                             int actualFirst, int actualLast){
        if(afterIndex == -1){
            if(beforeIndex == 0){
                this.pageInfo.setHasPreviousPage(false);
            } else {
                if(actualFirst > actualLast){
                    this.pageInfo.setHasPreviousPage(true);
                }
            }
            if(actualFirst <= actualLast){
                this.pageInfo.setHasPreviousPage(false);
            }
        } else {
            this.pageInfo.setHasPreviousPage(true);
        }
    }

    private void hasPreviousPage(int afterIndex, int beforeIndex, int actualFirst,
                                 List<SaleProductEdge> allEdges, int beforeCount ){
        if(afterIndex == allEdges.size() - 1){
            this.pageInfo.setHasNextPage(false);
        } else {
            if(beforeIndex != -1){
                this.pageInfo.setHasNextPage(true);
            } else {
                if(actualFirst < beforeCount){
                    this.pageInfo.setHasNextPage(true);
                }
            }
            if(actualFirst >= beforeCount){
                this.pageInfo.setHasNextPage(false);
            }
        }
    }
}

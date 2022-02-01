package com.ead.notificationhex.core.domain;


// Temos que criar uma paginação especifica s/ vinculo com framework e realizar essa conversão
// Exemplo -> Passando paginação em uma busca de notificações de um usuario, msm passando os parametros na request precisamos criar eles
public class PageInfo {

    private int pageNumber;
    private int pageSize;


    public PageInfo() {
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

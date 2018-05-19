package ua.ithillel.dnipro.common;

import javax.persistence.*;

@Entity
@Table(name = "stock")
public class Stock{

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_ID")
    private int stockId;

    @Column (name = "STOCK_CODE")
    private String stockCode;

    @Column (name = "STOCK_NAME")
    private String stockName;



    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public String toString() {
        return "stockId |" + stockId + "|" +
                " stockCode |" + stockCode + "|" +
                " stockName |" + stockName + "|" ;
    }
}
